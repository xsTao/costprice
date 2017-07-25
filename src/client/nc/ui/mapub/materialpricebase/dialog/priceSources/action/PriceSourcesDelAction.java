package nc.ui.mapub.materialpricebase.dialog.priceSources.action;

import java.awt.Event;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import nc.bs.uif2.IActionCode;
import nc.ui.pubapp.uif2app.actions.BodyDelLineAction;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.NCAction;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;

/**
 * 物料价格库
 * 价格来源删除按钮
 * 
 * @since 6.36
 * @version 2014-11-19 上午9:05:30
 * @author zhangchd
 */
public class PriceSourcesDelAction extends BodyDelLineAction {

    private static final long serialVersionUID = -9065287686032675872L;

    private BillForm dialogBillForm;

    public PriceSourcesDelAction() {
        this.setCode(IActionCode.DELLINE);

        NCAction action = new NCAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void doAction(ActionEvent e) throws Exception {
                // do nothing
            }
        };
        action.setBtnName(CMMLangConstMaterialPriceBase.getMSG8());
        action.setCode(IActionCode.DELLINE);
        action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke('D', Event.CTRL_MASK));
        action.putValue(Action.SHORT_DESCRIPTION, CMMLangConstMaterialPriceBase.getMSG8() + "(Alt+D)");
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
