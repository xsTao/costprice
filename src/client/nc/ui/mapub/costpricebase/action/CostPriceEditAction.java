/**
 *
 */
package nc.ui.mapub.costpricebase.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.pub.power.PowerCheckUtils;
import nc.ui.pubapp.uif2app.actions.EditAction;
import nc.ui.uif2.editor.BillForm;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pub.power.PowerActionEnum;

/**
 * @since v6.3
 * @version 2017年7月24日 下午1:29:39
 * @author Administrator
 */
public class CostPriceEditAction extends EditAction {

    private static final long serialVersionUID = 1L;

    private boolean powerCheck;

    private String billType;

    private String billCodeName;

    private BillForm editor;

    public BillForm getEditor() {
        return this.editor;
    }

    public void setEditor(BillForm editor) {
        this.editor = editor;
    }

    @Override
    public void doAction(ActionEvent e) throws Exception {
        if (this.isPowercheck()) {
            IBill bill = (IBill) this.getModel().getSelectedData();
            PowerCheckUtils.checkHasPermission(new IBill[] {
                    bill
            }, this.getBillType(), PowerActionEnum.EDIT.getActioncode(), this.getBillCodeName());
        }

    }

    public boolean isPowercheck() {
        return this.powerCheck;
    }

    public void setPowercheck(boolean powercheck) {
        this.powerCheck = powercheck;
    }

    public String getBillType() {
        return this.billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillCodeName() {
        return this.billCodeName;
    }

    public void setBillCodeName(String billCodeName) {
        this.billCodeName = billCodeName;
    }

}
