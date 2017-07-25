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
 * …œ“∆∞¥≈•
 * 
 * @since 6.36
 * @version 2014-11-20 œ¬ŒÁ3:23:08
 * @author zhangchd
 */
public class PriceSourcesUpLineAction extends AbstractBodyTableExtendAction implements ICardPanelDefaultActionProcessor {

    private static final long serialVersionUID = -6580080332080462993L;

    private BillForm dialogBillForm;

    private int type;

    public PriceSourcesUpLineAction() {
        this.setCode("UPLINE");

        NCAction action = new NCAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void doAction(ActionEvent e) throws Exception {
                // do nothing
            }
        };

        action.setBtnName(CMMLangConstMaterialPriceBase.getMSG12());
        action.setCode("UPLINE");
        action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke('I', Event.ALT_MASK));
        action.putValue(Action.SHORT_DESCRIPTION, CMMLangConstMaterialPriceBase.getMSG12() + "(Alt+I)");

        this.putActionValue(action);
    }

    @Override
    public void doAction() {

        BillScrollPane bsp = this.getCardPanel().getBodyPanel();
        UITable table = bsp.getTable();
        BillModel tableModel = bsp.getTableModel();
        int[] rows = table.getSelectedRows();

        final int col = bsp.getTable().getSelectedColumn() < 0 ? 0 : bsp.getTable().getSelectedColumn();

        this.getCardPanel().stopEditing();

        for (int row : rows) {
            int moveRow;
            if (row <= 0) {
                moveRow = 0;
            }
            else {
                moveRow = row - 1;
            }

            tableModel.moveRow(row, row, moveRow);
            if (moveRow < table.getRowCount()) {
                // table.setRowSelectionInterval(moveRow, moveRow);
                table.changeSelection(moveRow, col, false, false);
            }
            else {
                // table.setRowSelectionInterval(table.getRowCount() - 1, table.getRowCount() - 1);
                table.changeSelection(table.getRowCount() - 1, col, false, false);
            }
        }
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
