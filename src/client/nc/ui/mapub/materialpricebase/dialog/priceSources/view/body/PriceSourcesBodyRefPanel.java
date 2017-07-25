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
        // ��ʼ���Ի�������
        this.getDataManager().initModel();

        // ������������
        this.getDialogBillForm().getBillCardPanel().getBodyPanel().getTable().setSortEnabled(false);
        this.getDialogBillForm().getBillCardPanel().getBodyPanel().getTable().getTableHeader()
                .setUI(new GroupableTableHeaderUI());

        // ��ʾ�Ի���
        this.getDialog().showModal();

        // if (CostTypePriceSourceRefPane.this.dlg.showModal() == UIDialog.ID_OK) {
        // }
    }

    /**
     * ����֧�ּ������¼��ķ���.
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
