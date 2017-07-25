package nc.ui.mapub.materialpricebase.dialog.priceSources.view.body;

import nc.ui.mapub.materialpricebase.dialog.priceSources.view.UIRefPaneBorder;
import nc.ui.pub.beans.RefEditEvent;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.table.GroupableTableHeaderUI;
import nc.ui.pubapp.uif2app.view.BillForm;

public class PriceSourcesBodyRefPanel extends UIRefPane {

    private static final long serialVersionUID = -5106021901910249827L;

    private PriceSourcesFuncletDialog dialog;

    private PriceSourcesModelDataManager dataManager;

    private BillForm dialogBillForm;

    public PriceSourcesBodyRefPanel() {
        super();

    }

    @Override
    public void onButtonClicked() {
        // 初始化对话框内容
        this.getDataManager().initModel();

        // 设置列网格线
        this.getDialogBillForm().getBillCardPanel().getBodyPanel().getTable().setSortEnabled(false);
        this.getDialogBillForm().getBillCardPanel().getBodyPanel().getTable().getTableHeader()
                .setUI(new GroupableTableHeaderUI());

        // 显示对话框
        this.getDialog().showModal();

        // if (CostTypePriceSourceRefPane.this.dlg.showModal() == UIDialog.ID_OK) {
        // }
    }

    /**
     * 用来支持监听器事件的方法.
     */
    @Override
    public void fireRefEdit(final RefEditEvent event) {
        this.getUITextField().setEditable(false);

        super.fireRefEdit(event);
        this.setBorder(new UIRefPaneBorder());
    }

    public PriceSourcesFuncletDialog getDialog() {
        return this.dialog;
    }

    public void setDialog(PriceSourcesFuncletDialog dialog) {
        this.dialog = dialog;
    }

    public PriceSourcesModelDataManager getDataManager() {
        return this.dataManager;
    }

    public void setDataManager(PriceSourcesModelDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public BillForm getDialogBillForm() {
        return this.dialogBillForm;
    }

    public void setDialogBillForm(BillForm dialogBillForm) {
        this.dialogBillForm = dialogBillForm;
    }

}
