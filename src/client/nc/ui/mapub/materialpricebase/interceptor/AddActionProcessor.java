package nc.ui.mapub.materialpricebase.interceptor;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.bd.framework.base.CMValueCheck;
import nc.ui.mapub.materialpricebase.dialog.priceSources.view.body.PriceSourcesBodyRefPanel;
import nc.ui.mapub.materialpricebase.dialog.priceSources.view.head.PriceSourcesRefPanel;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.ui.pub.beans.ValueChangedListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.event.card.CardPanelEventTransformer;
import nc.ui.pubapp.uif2app.event.card.CardPanelEventTransformer.CardListener;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.actions.ActionInterceptor;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;

public class AddActionProcessor implements ActionInterceptor {
    private BillManageModel model;

    private BillForm editor;

    private PriceSourcesRefPanel priceRefPanel;

    private PriceSourcesBodyRefPanel priceBodyRefPanel;

    // // 编辑前监听
    // private CardListener billCardBeforeEditListener = null;
    //
    // // 编辑后监听
    private CardListener billCardAfterEditListener = null;

    //
    // private BillItemtRefEditListener headTailRefEditListener;

    //
    private BillItem item;

    class BillItemValueChangedListener implements ValueChangedListener {

        @Override
        public void valueChanged(ValueChangedEvent event) {
            BillEditEvent be =
                    new BillEditEvent(AddActionProcessor.this.getPriceRefPanel(), event.getOldValue(),
                            event.getNewValue(), AddActionProcessor.this.item.getKey(), -1, IBillItem.HEAD);
            AddActionProcessor.this.billCardAfterEditListener =
                    new CardPanelEventTransformer(
                            ((ShowUpableBillForm) AddActionProcessor.this.editor).getBillCardPanel(),
                            (IAppModelEx) ((ShowUpableBillForm) AddActionProcessor.this.editor).getModel())
                            .getListener();
            AddActionProcessor.this.billCardAfterEditListener.afterEdit(be);

        }

    }

    //
    // class BillItemtRefEditListener implements RefEditListener {
    //
    // @Override
    // public boolean beforeEdit(RefEditEvent e) {
    // if (AddActionProcessor.this.billCardBeforeEditListener == null) {
    // AddActionProcessor.this.billCardBeforeEditListener =
    // new CardPanelEventTransformer(
    // ((ShowUpableBillForm) AddActionProcessor.this.editor).getBillCardPanel(),
    // (IAppModelEx) ((ShowUpableBillForm) AddActionProcessor.this.editor).getModel())
    // .getListener();
    //
    // }
    //
    // if (AddActionProcessor.this.billCardBeforeEditListener != null) {
    // return AddActionProcessor.this.billCardBeforeEditListener.beforeEdit(new BillItemEvent(
    // AddActionProcessor.this.item));
    // }
    // return false;
    // }
    //
    // }

    @Override
    public boolean beforeDoAction(Action action, ActionEvent e) {
        return true;
    }

    @Override
    public void afterDoActionSuccessed(Action action, ActionEvent e) {
        BillCardPanel billCardPanel = ((ShowUpableBillForm) this.getEditor()).getBillCardPanel();
        if (null != billCardPanel) {

            // 表体
            BillItem materialBodyrefItem = billCardPanel.getBodyItem(MaterialPriceBaseBodyVO.VPRICESOURCE);
            materialBodyrefItem.setComponent(this.getPriceBodyRefPanel());
            // 表体价格来源增加值变化事件
            // this.getPriceBodyRefPanel().addValueChangedListener(new BillItemValueChangedListener());

            // 表头
            BillItem materialHeadrefItem = billCardPanel.getHeadItem(MaterialPriceBaseHeadVO.VPRICESOURCE);
            Object orgrefItem = billCardPanel.getHeadItem(MaterialPriceBaseHeadVO.PK_ORG).getValueObject();
            // if (this.headTailRefEditListener == null) {
            this.item = materialHeadrefItem;
            // this.headTailRefEditListener = new BillItemtRefEditListener();
            // this.getPriceRefPanel().addRefEditListener(this.headTailRefEditListener);
            // }
            // 表头价格来源增加值变化事件
            this.getPriceRefPanel().addValueChangedListener(new BillItemValueChangedListener());
            if (CMValueCheck.isEmpty(orgrefItem)) {
                this.getPriceRefPanel().setRefEditable(false);
                this.getPriceRefPanel().getUITextField().setShowMustInputHint(false);
            }
            materialHeadrefItem.setComponent(this.getPriceRefPanel());
            billCardPanel.setBillData(billCardPanel.getBillData());

        }

    }

    @Override
    public boolean afterDoActionFailed(Action action, ActionEvent e, Throwable ex) {
        return true;
    }

    public BillForm getEditor() {
        return this.editor;
    }

    public void setEditor(BillForm editor) {
        this.editor = editor;
    }

    public BillManageModel getModel() {
        return this.model;
    }

    public void setModel(BillManageModel model) {
        this.model = model;
    }

    public PriceSourcesRefPanel getPriceRefPanel() {
        return this.priceRefPanel;
    }

    public void setPriceRefPanel(PriceSourcesRefPanel priceRefPanel) {
        this.priceRefPanel = priceRefPanel;
    }

    public PriceSourcesBodyRefPanel getPriceBodyRefPanel() {
        return this.priceBodyRefPanel;
    }

    public void setPriceBodyRefPanel(PriceSourcesBodyRefPanel priceBodyRefPanel) {
        this.priceBodyRefPanel = priceBodyRefPanel;
    }

}
