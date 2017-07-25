package nc.ui.mapub.driver.view.dialog;

import java.awt.event.ActionEvent;

import nc.ui.mapub.driver.view.dialog.DriverFormulaValidata;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.formula.dialog.AbstractFormulaRealEditorPanel;
import nc.ui.pub.formula.dialog.FormulaValidateAction;
import nc.vo.mapub.driver.entity.CMDriverLangConst;

/**
 * <b> 成本动因的公式校验 </b>
 * <p>
 * 主要是扩展业务校验
 * </p>
 * 创建日期:2010-4-17
 * 
 * @author:wangtf
 */
public class DriverFormulaValidateAction extends FormulaValidateAction {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 6715843932457446067L;

    @Override
    public void actionPerformed(ActionEvent e) {
        DriverFormulaValidata formulaValidate = new DriverFormulaValidata(this.getFormulaRealEditorPanel());
        if (formulaValidate.validata()) {
            // 公式验证通过
            MessageDialog.showHintDlg((AbstractFormulaRealEditorPanel) this.getFormulaRealEditorPanel(),
                    CMDriverLangConst.getFORMULA_VALIDATE(), CMDriverLangConst.getFORMULA_VALIDATE_SUCCESS());

        }
    }

}
