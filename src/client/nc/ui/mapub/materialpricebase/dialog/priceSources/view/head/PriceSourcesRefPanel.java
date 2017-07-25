package nc.ui.mapub.materialpricebase.dialog.priceSources.view.head;

import nc.ui.mapub.materialpricebase.dialog.priceSources.view.UIRefPaneBorder;
import nc.ui.pub.beans.RefEditEvent;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.UITextField;
import nc.ui.pub.beans.table.GroupableTableHeaderUI;
import nc.ui.pubapp.uif2app.view.BillForm;

/**
 * 物料价格来源
 * 价格来源参照面板
 * 
 * @since 6.36
 * @version 2014-11-19 上午8:56:43
 * @author zhangchd
 */
public class PriceSourcesRefPanel extends UIRefPane {

    private static final long serialVersionUID = -601631865382765918L;

    private PriceSourcesFuncletDialog dialog;

    private PriceSourcesModelDataManager dataManager;

    private BillForm dialogBillForm;

    public PriceSourcesRefPanel() {
        super();

        // if (!this.getUIButton().isEnabled()) {
        // 添加必填项(星号)显示
        final UITextField uiTf = this.getUITextField();
        uiTf.setShowMustInputHint(uiTf.isEnabled());
        // }

    }

    @Override
    public void onButtonClicked() {
        // 初始化对话框内容
        this.getDataManager().initModel();

        // 设置列网格线
        this.getDialogBillForm().getBillCardPanel().getBodyPanel().getTable().setSortEnabled(false);
        this.getDialogBillForm().getBillCardPanel().getBodyPanel().getTable().getTableHeader()
                .setUI(new GroupableTableHeaderUI());
        // this.getDialogBillForm().getBillCardPanel().getBodyPanel().getTable().getTableHeader()
        // .setBorder(new NCLineBorder(UIManager.getColor("Table.borderColor")));
        // this.getDialogBillForm().getBillCardPanel().getBodyPanel().getTable().getTableHeader()
        // .setBorder(new NCLineBorder(UIManager.getColor("TableHeader.cellBorder")));
        // this.getDialogBillForm().getBillCardPanel().getBodyPanel().getTable()
        // .setBorder(new NCLineBorder(UIManager.getColor("TableHeader.cellBorder")));

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
        // this.getUIButton().setEnabled(true);
        super.fireRefEdit(event);

        // this.setBorder(new CompoundBorder(this.BORDER_OUT_CENTERPNL, this.BORDER_IN_CENTERPNL));
        // this.setBorder(BorderFactory.createLineBorder(Style.getNCBorderColor()));
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
