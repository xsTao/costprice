package nc.ui.mapub.driver.view.dialog;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import nc.ui.mapub.driver.view.dialog.DriverFormulaCodeRealEditorPanel;
import nc.ui.pub.formula.dialog.FormulaRealEditorPanel;
import nc.ui.pub.formula.dialog.IFormulaActionWithDialog;
import nc.ui.pub.formulaedit.FormulaEditorDialog;
import nc.vo.mapub.driver.entity.CMDriverLangConst;

/**
 * <b> 成本动因公式编辑器清除按钮 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-10-19
 * 
 * @author:leixia
 */
@SuppressWarnings("serial")
public class DriverFormulaClearAction extends AbstractAction implements IFormulaActionWithDialog {

    /**
     * 构造方法
     */
    public DriverFormulaClearAction() {
        this.putValue("Name", CMDriverLangConst.getREF_BTN_CLEAR());
    }

    /**
     * FormulaRealEditorPanel
     */
    private FormulaRealEditorPanel formulaRealEditorPanel;

    /**
     * FormulaEditorDialog
     */
    private FormulaEditorDialog dialog;

    /**
     * DriverFormulaValueRealEditorPanel
     */
    private DriverFormulaCodeRealEditorPanel formulaValueEditorPanel;

    @Override
    public void actionPerformed(ActionEvent e) {
        this.getDialog().setFormulaDesc(null);
        this.getFormulaValueEditorPanel().setConvertedText(null);
    }

    @Override
    public void setDialog(FormulaEditorDialog arg0) {
        this.dialog = arg0;
    }

    /**
     * formulaRealEditorPanel get method
     * 
     * @return FormulaRealEditorPanel
     */
    public FormulaRealEditorPanel getFormulaRealEditorPanel() {
        return this.formulaRealEditorPanel;
    }

    /**
     * formulaRealEditorPanel get method
     * 
     * @param formulaRealEditorPanel FormulaRealEditorPanel
     */
    public void setFormulaRealEditorPanel(FormulaRealEditorPanel formulaRealEditorPanel) {
        this.formulaRealEditorPanel = formulaRealEditorPanel;
    }

    /**
     * formulaValueEditorPanel get method
     * 
     * @return DriverFormulaCodeRealEditorPanel
     */
    public DriverFormulaCodeRealEditorPanel getFormulaValueEditorPanel() {
        return this.formulaValueEditorPanel;
    }

    /**
     * formulaValueEditorPanel set method
     * 
     * @param formulaValueEditorPanel DriverFormulaCodeRealEditorPanel
     */
    public void setFormulaValueEditorPanel(DriverFormulaCodeRealEditorPanel formulaValueEditorPanel) {
        this.formulaValueEditorPanel = formulaValueEditorPanel;
    }

    /**
     * dialog get method
     * 
     * @return FormulaEditorDialog
     */
    public FormulaEditorDialog getDialog() {
        return this.dialog;
    }

}
