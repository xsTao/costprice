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
 * �ö���ť
 * 
 * @since 6.36
 * @version 2014-11-21 ����10:19:52
 * @author zhangchd
 */
public class PriceSourcesTopLineAction extends AbstractBodyTableExtendAction implements
        ICardPanelDefaultActionProcessor {

    private static final long serialVersionUID = 3923480208549335855L;

    private BillForm dialogBillForm;

    private int type;

    public PriceSourcesTopLineAction() {
        this.setCode("TOPLINE");

        NCAction action = new NCAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void doAction(ActionEvent e) throws Exception {
                // do nothing
            }
        };

        action.setBtnName(CMMLangConstMaterialPriceBase.getMSG11());
        action.setCode("TOPLINE");
        action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke('I', Event.ALT_MASK));
        action.putValue(Action.SHORT_DESCRIPTION, CMMLangConstMaterialPriceBase.getMSG11() + "(Alt+I)");

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
        if (row > 0) {
            tableModel.moveRow(row, row, 0);
            // table.setRowSelectionInterval(0, 0);
            table.changeSelection(0, col, false, false);
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
