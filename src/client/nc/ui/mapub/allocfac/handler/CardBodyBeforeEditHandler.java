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
 * 卡片界面编辑事件处理
 *
 * @author mmauto.liubq
 */

public class CardBodyBeforeEditHandler extends CMBasedocEventHandler implements
        IAppEventHandler<CardBodyBeforeEditEvent> {
    /**
     * 模型
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
            // 产品Item
            item = e.getBillCardPanel().getBodyItem(AllocfacItemVO.CMATERIALID);
            UIRefPane refcmaterialid = (UIRefPane) item.getComponent();
            refcmaterialid.setMultiSelectedEnabled(true);

            String pk_group = this.getModel().getContext().getPk_group();
            String pk_org = this.getModel().getContext().getPk_org();
            // 参照产品，需要是为成本对象的产品
            FilterMaterialDefaultRefUtil.materialDefaultRefFilterIsCostObjUnderOrg(item, pk_group, pk_org);
        }
        if (e.getKey().equals(AllocfacItemVO.CSTUFFID)) {
            // 产品Item
            item = e.getBillCardPanel().getBodyItem(AllocfacItemVO.CSTUFFID);
            UIRefPane refcmaterialid = (UIRefPane) item.getComponent();
            refcmaterialid.setMultiSelectedEnabled(true);
        }
        if (e.getKey().equals(AllocfacItemVO.CMARBASECLASSID)) {
            // 基本分类Item
            item = e.getBillCardPanel().getBodyItem(AllocfacItemVO.CMARBASECLASSID);
            // 基本本分类参照时不显示已停用的档案
            CardBodyBeforeEditHandler.baseClassRefFilterByORG(item);
            // 基本分类参照，设置只能选择末级节点
            CardBodyBeforeEditHandler.baseClassRefFilterSelectOnlyLeaf(item, true);
            UIRefPane refcmarcostclassid = (UIRefPane) item.getComponent();
            refcmarcostclassid.setMultiSelectedEnabled(true);
        }

        String pkorgNow = this.getModel().getContext().getPk_org();
        if (e.getKey().equals(AllocfacItemVO.CACTIVITYID)) {
            // 作业Item
            item = e.getBillCardPanel().getBodyItem(AllocfacItemVO.CACTIVITYID);
            UIRefPane refcactivityid = (UIRefPane) item.getComponent();
            refcactivityid.setMultiSelectedEnabled(true);

            FilterActivityRefUtil.activityRefFilterByOrg(item, pkorgNow);
        }

        if (e.getKey().equals(AllocfacItemVO.CMARCOSTCLASSID)) {
            // 成本分类Item
            item = e.getBillCardPanel().getBodyItem(AllocfacItemVO.CMARCOSTCLASSID);
            // 成本分类参照时不显示已停用的档案
            CardBodyBeforeEditHandler.costClassRefFilterByORG(item);
            // 成本分类参照，设置只能选择末级节点
            CardBodyBeforeEditHandler.costClassRefFilterSelectOnlyLeaf(item, true);
            UIRefPane refcmarcostclassid = (UIRefPane) item.getComponent();
            refcmarcostclassid.setMultiSelectedEnabled(true);
        }
        if (e.getKey().equals(AllocfacItemVO.CCOSTCENTERID)) {
            // 成本中心支持参照多选
            item = e.getBillCardPanel().getBodyItem(AllocfacItemVO.CCOSTCENTERID);
            UIRefPane refccostcenterid = (UIRefPane) item.getComponent();
            // 参照支持多选
            refccostcenterid.setMultiSelectedEnabled(true);

        }
    }

    /**
     * 不显示已停用的档案
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
     * 设置只能选择末级节点
     *
     * @param item
     *            BillItem
     * @param onlyLeaf
     *            是否只能选择末级节点
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
