package nc.ui.mapub.materialpricebase.interceptor;

import java.awt.event.ActionEvent;

import javax.swing.Action;

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

public class EditActionProcessor implements ActionInterceptor {
    private BillManageModel model;

    private BillForm editor;

    private PriceSourcesRefPanel priceRefPanel;

    private PriceSourcesBodyRefPanel priceBodyRefPanel;

    // // 编辑后监听
    private CardListener billCardAfterEditListener = null;

    private BillItem item;

    class BillItemValueChangedListener implements ValueChangedListener {

        @Override
        public void valueChanged(ValueChangedEvent event) {
            BillEditEvent be =
                    new BillEditEvent(EditActionProcessor.this.getPriceRefPanel(), event.getOldValue(),
                            event.getNewValue(), EditActionProcessor.this.item.getKey(), -1, IBillItem.HEAD);
            EditActionProcessor.this.billCardAfterEditListener =
                    new CardPanelEventTransformer(
                            ((ShowUpableBillForm) EditActionProcessor.this.editor).getBillCardPanel(),
                            (IAppModelEx) ((ShowUpableBillForm) EditActionProcessor.this.editor).getModel())
                            .getListener();
            EditActionProcessor.this.billCardAfterEditListener.afterEdit(be);

        }

    }

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

            // 表头
            BillItem materialHeadrefItem = billCardPanel.getHeadItem(MaterialPriceBaseHeadVO.VPRICESOURCE);
            this.item = materialHeadrefItem;
            // 表头价格来源增加值变化事件
            this.getPriceRefPanel().addValueChangedListener(new BillItemValueChangedListener());
            materialHeadrefItem.setComponent(this.getPriceRefPanel());
            billCardPanel.setBillData(billCardPanel.getBillData());

            if (materialHeadrefItem.getValueCache() != null) {
                billCardPanel.setHeadItem(MaterialPriceBaseBodyVO.VPRICESOURCE, materialHeadrefItem.getValueCache()
                        .toString());

            }

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
