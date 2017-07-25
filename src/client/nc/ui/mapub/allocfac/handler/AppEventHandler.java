package nc.ui.mapub.allocfac.handler;

import nc.bd.framework.base.CMStringUtil;
import nc.ui.cmpub.framework.util.CMBillListPanelUtil;
import nc.ui.cmpub.framework.view.CMBillFormFacade;
import nc.ui.cmpub.framework.view.CMBillListViewFacade;
import nc.ui.mapub.allocfac.util.AllocfacViewUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.uif2.AppEvent;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.mapub.allocfac.util.AllocfacItemUtil;
import nc.vo.pub.AggregatedValueObject;

/**
 * ���ݷ������ݵ�ѡ����±�������Ӧ�еĿɱ༭״̬
 * <p>
 * selection changed �¼��Ĵ���
 */

public class AppEventHandler implements IAppEventHandler<AppEvent> {
    private CMBillFormFacade cardEditor;

    private CMBillListViewFacade listEditor;

    public CMBillListViewFacade getListEditor() {
        return this.listEditor;
    }

    public void setListEditor(CMBillListViewFacade listEditor) {
        this.listEditor = listEditor;
    }

    public CMBillFormFacade getCardEditor() {
        return this.cardEditor;
    }

    public void setCardEditor(CMBillFormFacade cardEditor) {
        this.cardEditor = cardEditor;
    }

    @Override
    public void handleAppEvent(AppEvent e) {
        this.updateBodyColumsVisibleByAllocType();
    }

    /**
     * ���ݷ������ݵ�ѡ����±�������Ӧ�еĿɱ༭״̬
     */
    public void updateBodyColumsVisibleByAllocType() {
        BillCardPanel nowBillCardPanel = this.getCardEditor().getBillCardPanel();
        BillListPanel nowBillListPanel = this.getListEditor().getBillListPanel();
        String allocType = null;

        // ��һ�δ򿪽ڵ㣬������ʾ���ݲ���ȷ
        AggregatedValueObject aggVO =
                nowBillListPanel.getBillValueVO(0, AllocfacAggVO.class.getName(), AllocfacHeadVO.class.getName(),
                        AllocfacItemVO.class.getName());
        if (aggVO != null && aggVO.getParentVO() != null) {
            AllocfacHeadVO headVO = (AllocfacHeadVO) aggVO.getParentVO();
            if (headVO.getIalloctype() == null) {
                return;
            }
            if (CMStringUtil.isEmpty(headVO.getIalloctype().toString())) {
                return;
            }
            allocType = headVO.getIalloctype().toString();
        }

        if (nowBillCardPanel.isShowing()) {
            // �����ǰ��ʾ���ǿ�Ƭ���棬�������Itemȡ���������ͣ�ֵΪinteger�͵�
            BillItem nowItem = nowBillCardPanel.getHeadTailItem(AllocfacHeadVO.IALLOCTYPE);
            if (nowItem == null || nowItem.getValueObject() == null) {
                AllocfacViewUtil.initBodyColumsVisible(nowBillCardPanel, nowBillListPanel);
                return;
            }
            allocType = nowItem.getValueObject().toString();
        }
        else if (nowBillListPanel.isShowing()) {
            // �����ǰ��ʾ�����б���棬��ӽ�������ȡ���������ͣ�ͨ��ת��,ֵΪinteger�͵�
            if (nowBillListPanel.getHeadTable().getSelectedRow() < 0) {
                AllocfacViewUtil.initBodyColumsVisible(nowBillCardPanel, nowBillListPanel);
                return;
            }
            CMBillListPanelUtil util = new CMBillListPanelUtil(nowBillListPanel);
            allocType =
                    util.getHeadValue(nowBillListPanel.getHeadTable().getSelectedRow(), AllocfacHeadVO.IALLOCTYPE)
                    .toString();
        }

        if (allocType == null) {
            return;
        }

        String[] showColumKeys = AllocfacItemUtil.getAllocContentColumByContent(allocType);

        if (showColumKeys == null || showColumKeys.length == 0) {
            return;
        }

        // ���¿�Ƭ���������ʾ���
        AllocfacViewUtil.updateBillCardColsVisibleByAllocType(nowBillCardPanel, showColumKeys);

        // �����б���������ʾ���
        AllocfacViewUtil.updateBillListColsVisibleByAllocType(nowBillListPanel, showColumKeys);

        // ���������load�������Ĳ��ջ���ʾPK
        this.getCardEditor().getBillCardPanel().getBillModel().loadLoadRelationItemValue();
        this.getListEditor().getBillListPanel().getBodyBillModel().loadLoadRelationItemValue();
        this.getCardEditor().updateUI();
        this.getListEditor().updateUI();
    }
}
