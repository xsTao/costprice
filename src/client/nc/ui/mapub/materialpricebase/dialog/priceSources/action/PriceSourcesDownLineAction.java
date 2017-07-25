package nc.ui.mapub.materialpricebase.dialog.priceSources.action;

import java.awt.Event;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import nc.ui.pub.beans.UITable;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pubapp.uif2app.actions.AbstractBodyTableExtendAction;
import nc.ui.pubapp.uif2app.actions.intf.ICardPanelDefaultActionProcessor;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.NCAction;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;

/**
 * ÏÂÒÆ°´Å¥
 * 
 * @since 6.36
 * @version 2014-11-21 ÉÏÎç9:59:39
 * @author zhangchd
 */
public class PriceSourcesDownLineAction extends AbstractBodyTableExtendAction implements
        ICardPanelDefaultActionProcessor {

    private static final long serialVersionUID = -8863201753173176550L;

    private BillForm dialogBillForm;

    private int type;

    public PriceSourcesDownLineAction() {
        this.setCode("DOWNLINE");

        NCAction action = new NCAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void doAction(ActionEvent e) throws Exception {
                // do nothing
            }
        };

        action.setBtnName(CMMLangConstMaterialPriceBase.getMSG9());
        action.setCode("DOWNLINE");
        action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke('I', Event.ALT_MASK));
        action.putValue(Action.SHORT_DESCRIPTION, CMMLangConstMaterialPriceBase.getMSG9() + "(Alt+I)");

        this.putActionValue(action);
    }

    @Override
    public void doAction() {

        BillScrollPane bsp = this.getCardPanel().getBodyPanel();
        UITable table = bsp.getTable();
        BillModel tableModel = bsp.getTableModel();
        int[] rows = table.getSelectedRows();

        final int col = bsp.getTable().getSelectedColumn() < 0 ? 0 : bsp.getTable().getSelectedColumn();

        for (int row : rows) {
            if (row + 1 < table.getRowCount()) {
                tableModel.moveRow(row, row, row + 1);
                // table.setRowSelectionInterval(row + 1, row + 1);
                table.changeSelection(row + 1, col, false, false);

            }

        }
        this.getCardPanel().stopEditing();
    }

    @Override
    protected boolean isActionEnable() {
        return true;
    }

    @Override
    public int getType() {
        return this.type;
    }

    // private int setType(int type) {
    // return this.type = type;
    // }

    public BillForm getDialogBillForm() {
        return this.dialogBillForm;
    }

    public void setDialogBillForm(BillForm dialogBillForm) {
        this.dialogBillForm = dialogBillForm;
        super.setCardPanel(this.getDialogBillForm().getBillCardPanel());
    }
}
