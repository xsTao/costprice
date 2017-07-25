package nc.ui.mapub.driver.view.dialog;

import java.awt.event.ActionEvent;

import nc.ui.mapub.driver.view.dialog.DriverFormulaCodeRealEditorPanel;
import nc.ui.mapub.driver.view.dialog.DriverFormulaValidata;
import nc.ui.mapub.driver.view.dialog.FormulaDialog;
import nc.ui.pub.formula.dialog.FormulaOKAction;

/**
 * <b> �޸ı���ʱ�ļ���߼� </b>
 * <p>
 * ��ϸ��������
 * </p>
 * ��������:2010-4-20
 * 
 * @author:wangtf
 */
public class DriverFormulaOKAction extends FormulaOKAction {
    /**
     * ���л�id
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
        // ��ʽ��֤��ȷ
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
     * �ָ���ʽ
     * 
     * @param formulaText
     *            ��ʽ
     * @return ͨ�� ; �ָ����ַ���
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
