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
 * ÖÃµ×°´Å¥
 * 
 * @since 6.36
 * @version 2014-11-21 ÉÏÎç10:24:00
 * @author zhangchd
 */
public class PriceSourcesBottomLineAction extends AbstractBodyTableExtendAction implements
        ICardPanelDefaultActionProcessor {

    private static final long serialVersionUID = 478092219154425126L;

    private BillForm dialogBillForm;

    private int type;

    public PriceSourcesBottomLineAction() {
        this.setCode("BOTTOMLINE");

        NCAction action = new NCAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void doAction(ActionEvent e) throws Exception {
                // do nothing
            }
        };

        action.setBtnName(CMMLangConstMaterialPriceBase.getMSG6());
        action.setCode("BOTTOMLINE");
        action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke('I', Event.ALT_MASK));
        action.putValue(Action.SHORT_DESCRIPTION, CMMLangConstMaterialPriceBase.getMSG6() + "(Alt+I)");

        this.putActionValue(action);
    }

    @Override
    public void doAction() {

        BillScrollPane bsp = this.getCardPanel().getBodyPanel();
        UITable table = bsp.getTable();
        BillModel tableModel = bsp.getTableModel();
        int row = table.getSelectedRow();

        final int col = bsp.getTable().getSelectedColumn() < 0 ? 0 : bsp.getTable().getSelectedColumn();

        this.getCardPanel().stopEditing();
        if (row >= 0) {
            tableModel.moveRow(row, row, table.getRowCount() - 1);
            // table.setRowSelectionInterval(table.getRowCount() - 1, table.getRowCount() - 1);
            table.changeSelection(table.getRowCount() - 1, col, false, false);
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
