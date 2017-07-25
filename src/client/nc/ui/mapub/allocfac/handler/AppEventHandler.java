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
 * 根据分配内容的选择更新表体中相应列的可编辑状态
 * <p>
 * selection changed 事件的处理
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
     * 根据分配内容的选择更新表体中相应列的可编辑状态
     */
    public void updateBodyColumsVisibleByAllocType() {
        BillCardPanel nowBillCardPanel = this.getCardEditor().getBillCardPanel();
        BillListPanel nowBillListPanel = this.getListEditor().getBillListPanel();
        String allocType = null;

        // 第一次打开节点，表体显示内容不正确
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
            // 如果当前显示的是卡片界面，则根据其Item取出分配类型，值为integer型的
            BillItem nowItem = nowBillCardPanel.getHeadTailItem(AllocfacHeadVO.IALLOCTYPE);
            if (nowItem == null || nowItem.getValueObject() == null) {
                AllocfacViewUtil.initBodyColumsVisible(nowBillCardPanel, nowBillListPanel);
                return;
            }
            allocType = nowItem.getValueObject().toString();
        }
        else if (nowBillListPanel.isShowing()) {
            // 如果当前显示的是列表界面，则从界面表格中取出分配类型，通过转化,值为integer型的
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

        // 更新卡片界面的列显示情况
        AllocfacViewUtil.updateBillCardColsVisibleByAllocType(nowBillCardPanel, showColumKeys);

        // 更新列表界面的列显示情况
        AllocfacViewUtil.updateBillListColsVisibleByAllocType(nowBillListPanel, showColumKeys);

        // 如果不重新load，则表体的参照会显示PK
        this.getCardEditor().getBillCardPanel().getBillModel().loadLoadRelationItemValue();
        this.getListEditor().getBillListPanel().getBodyBillModel().loadLoadRelationItemValue();
        this.getCardEditor().updateUI();
        this.getListEditor().updateUI();
    }
}
