package nc.ui.mapub.allocfac.handler;

import nc.ui.bd.business.ref.FilterActivityRefUtil;
import nc.ui.bd.business.ref.FilterMaterialDefaultRefUtil;
import nc.ui.bd.pub.handler.CMBasedocEventHandler;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.bd.material.marcostclass.MarCostClassVO;
import nc.vo.bd.pub.IPubEnumConst;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;

/**
 * ��Ƭ����༭�¼�����
 *
 * @author mmauto.liubq
 */

public class CardBodyBeforeEditHandler extends CMBasedocEventHandler implements
        IAppEventHandler<CardBodyBeforeEditEvent> {
    /**
     * ģ��
     */
    private BillManageModel model;

    public BillManageModel getModel() {
        return this.model;
    }

    public void setModel(BillManageModel model) {
        this.model = model;
    }

    @Override
    public void handleAppEvent(CardBodyBeforeEditEvent e) {
        e.setReturnValue(true);
        BillItem item;

        if (e.getKey().equals(AllocfacItemVO.CMATERIALID)) {
            // ��ƷItem
            item = e.getBillCardPanel().getBodyItem(AllocfacItemVO.CMATERIALID);
            UIRefPane refcmaterialid = (UIRefPane) item.getComponent();
            refcmaterialid.setMultiSelectedEnabled(true);

            String pk_group = this.getModel().getContext().getPk_group();
            String pk_org = this.getModel().getContext().getPk_org();
            // ���ղ�Ʒ����Ҫ��Ϊ�ɱ�����Ĳ�Ʒ
            FilterMaterialDefaultRefUtil.materialDefaultRefFilterIsCostObjUnderOrg(item, pk_group, pk_org);
        }
        if (e.getKey().equals(AllocfacItemVO.CSTUFFID)) {
            // ��ƷItem
            item = e.getBillCardPanel().getBodyItem(AllocfacItemVO.CSTUFFID);
            UIRefPane refcmaterialid = (UIRefPane) item.getComponent();
            refcmaterialid.setMultiSelectedEnabled(true);
        }
        if (e.getKey().equals(AllocfacItemVO.CMARBASECLASSID)) {
            // ��������Item
            item = e.getBillCardPanel().getBodyItem(AllocfacItemVO.CMARBASECLASSID);
            // �������������ʱ����ʾ��ͣ�õĵ���
            CardBodyBeforeEditHandler.baseClassRefFilterByORG(item);
            // ����������գ�����ֻ��ѡ��ĩ���ڵ�
            CardBodyBeforeEditHandler.baseClassRefFilterSelectOnlyLeaf(item, true);
            UIRefPane refcmarcostclassid = (UIRefPane) item.getComponent();
            refcmarcostclassid.setMultiSelectedEnabled(true);
        }

        String pkorgNow = this.getModel().getContext().getPk_org();
        if (e.getKey().equals(AllocfacItemVO.CACTIVITYID)) {
            // ��ҵItem
            item = e.getBillCardPanel().getBodyItem(AllocfacItemVO.CACTIVITYID);
            UIRefPane refcactivityid = (UIRefPane) item.getComponent();
            refcactivityid.setMultiSelectedEnabled(true);

            FilterActivityRefUtil.activityRefFilterByOrg(item, pkorgNow);
        }

        if (e.getKey().equals(AllocfacItemVO.CMARCOSTCLASSID)) {
            // �ɱ�����Item
            item = e.getBillCardPanel().getBodyItem(AllocfacItemVO.CMARCOSTCLASSID);
            // �ɱ��������ʱ����ʾ��ͣ�õĵ���
            CardBodyBeforeEditHandler.costClassRefFilterByORG(item);
            // �ɱ�������գ�����ֻ��ѡ��ĩ���ڵ�
            CardBodyBeforeEditHandler.costClassRefFilterSelectOnlyLeaf(item, true);
            UIRefPane refcmarcostclassid = (UIRefPane) item.getComponent();
            refcmarcostclassid.setMultiSelectedEnabled(true);
        }
        if (e.getKey().equals(AllocfacItemVO.CCOSTCENTERID)) {
            // �ɱ�����֧�ֲ��ն�ѡ
            item = e.getBillCardPanel().getBodyItem(AllocfacItemVO.CCOSTCENTERID);
            UIRefPane refccostcenterid = (UIRefPane) item.getComponent();
            // ����֧�ֶ�ѡ
            refccostcenterid.setMultiSelectedEnabled(true);

        }
    }

    /**
     * ����ʾ��ͣ�õĵ���
     *
     * @param item
     *            BillItem
     */
    public static void costClassRefFilterByORG(BillItem item) {
        if (item == null) {
            return;
        }

        UIRefPane ref = (UIRefPane) item.getComponent();

        String where = MarCostClassVO.ENABLESTATE + " = " + IPubEnumConst.ENABLESTATE_ENABLE;

        ref.setWhereString(where);

    }

    public static void baseClassRefFilterByORG(BillItem item) {
        if (item == null) {
            return;
        }

        UIRefPane ref = (UIRefPane) item.getComponent();

        String where = MarBasClassVO.ENABLESTATE + " = " + IPubEnumConst.ENABLESTATE_ENABLE;

        ref.setWhereString(where);

    }

    /**
     * ����ֻ��ѡ��ĩ���ڵ�
     *
     * @param item
     *            BillItem
     * @param onlyLeaf
     *            �Ƿ�ֻ��ѡ��ĩ���ڵ�
     */
    public static void costClassRefFilterSelectOnlyLeaf(BillItem item, boolean onlyLeaf) {
        if (item == null) {
            return;
        }

        UIRefPane ref = (UIRefPane) item.getComponent();
        ref.setNotLeafSelectedEnabled(!onlyLeaf);

    }

    public static void baseClassRefFilterSelectOnlyLeaf(BillItem item, boolean onlyLeaf) {
        if (item == null) {
            return;
        }

        UIRefPane ref = (UIRefPane) item.getComponent();
        ref.setNotLeafSelectedEnabled(!onlyLeaf);

    }

    @Override
    public void initMap() {
    }
}
