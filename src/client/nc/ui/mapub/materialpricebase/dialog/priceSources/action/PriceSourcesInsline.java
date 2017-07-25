package nc.ui.mapub.materialpricebase.dialog.priceSources.action;

import java.awt.Event;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import nc.bs.uif2.IActionCode;
import nc.ui.pubapp.uif2app.actions.BodyInsertLineAction;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.NCAction;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;

/**
 * * 物料价格库
 * 价格来源插入按钮
 * 
 * @since 6.36
 * @version 2014-11-20 下午2:56:30
 * @author zhangchd
 */
public class PriceSourcesInsline extends BodyInsertLineAction {

    private static final long serialVersionUID = -9117433590027769556L;

    private BillForm dialogBillForm;

    public PriceSourcesInsline() {
        this.setCode(IActionCode.INSLINE);

        NCAction action = new NCAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void doAction(ActionEvent e) throws Exception {
                // do nothing
            }
        };

        action.setBtnName(CMMLangConstMaterialPriceBase.getMSG10());
        action.setCode(IActionCode.INSLINE);
        action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke('I', Event.ALT_MASK));
        action.putValue(Action.SHORT_DESCRIPTION, CMMLangConstMaterialPriceBase.getMSG10() + "(Alt+I)");

        this.putActionValue(action);
    }

    @Override
    protected boolean isActionEnable() {
        return true;
    }

    public BillForm getDialogBillForm() {
        return this.dialogBillForm;
    }

    public void setDialogBillForm(BillForm dialogBillForm) {
        this.dialogBillForm = dialogBillForm;
        super.setCardPanel(this.getDialogBillForm().getBillCardPanel());
    }
}
