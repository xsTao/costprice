package nc.ui.mapub.driver.view.dialog;

import java.awt.event.ActionEvent;

import nc.ui.mapub.driver.view.dialog.DriverFormulaCodeRealEditorPanel;
import nc.ui.mapub.driver.view.dialog.DriverFormulaValidata;
import nc.ui.mapub.driver.view.dialog.FormulaDialog;
import nc.ui.pub.formula.dialog.FormulaOKAction;

/**
 * <b> 修改保存时的检查逻辑 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-4-20
 * 
 * @author:wangtf
 */
public class DriverFormulaOKAction extends FormulaOKAction {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = -4420613790367164495L;

    /**
     * DriverFormulaValueRealEditorPanel
     */
    private DriverFormulaCodeRealEditorPanel formulaValueEditorPanel;

    @Override
    public void actionPerformed(ActionEvent e) {
        DriverFormulaValidata formulaValidate = new DriverFormulaValidata(this.getFormulaRealEditorPanel());
        if (!formulaValidate.validata()) {
            return;
        }
        // 公式验证正确
        String formulaText = this.getFormulaRealEditorPanel().getConvertedText();
        String[] formulas = this.splitFormulaText(formulaText);

        StringBuffer formulaDescBuf = new StringBuffer();
        for (int i = 0; i < formulas.length; i++) {
            formulaDescBuf.append(formulas[i] + ";");
        }
        if (null == this.getDialog()) {
            return;
        }

        this.getDialog().setFormulaDesc(formulaText);
        ((FormulaDialog) this.getDialog()).setHasEditFormula(true);
        ((FormulaDialog) this.getDialog()).setFormulacode(this.getFormulaValueEditorPanel().getConvertedText());
        this.getDialog().closeOK();
    }

    /**
     * 分隔公式
     * 
     * @param formulaText
     *            公式
     * @return 通过 ; 分隔的字符串
     */
    private String[] splitFormulaText(String formulaText) {
        formulaText = formulaText.replaceAll("\n", "").replaceAll("\t", "");
        return formulaText.split(";");
    }

    /**
     * formulaValueEditorPanel get method
     * 
     * @return DriverFormulaValueRealEditorPanel
     */
    public DriverFormulaCodeRealEditorPanel getFormulaValueEditorPanel() {
        return this.formulaValueEditorPanel;
    }

    /**
     * formulaValueEditorPanel set method
     * 
     * @param formulaValueEditorPanel DriverFormulaValueRealEditorPanel
     */
    public void setFormulaValueEditorPanel(DriverFormulaCodeRealEditorPanel formulaValueEditorPanel) {
        this.formulaValueEditorPanel = formulaValueEditorPanel;
    }

}
