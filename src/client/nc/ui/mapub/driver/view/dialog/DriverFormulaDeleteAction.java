package nc.ui.mapub.driver.view.dialog;

import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.AbstractAction;

import nc.ui.pub.formula.dialog.FormulaRealEditorPanel;
import nc.ui.pub.formula.dialog.IFormulaActionWithDialog;
import nc.ui.pub.formulaedit.FormulaEditorDialog;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.FormulaItemVO;

/**
 * <b> 成本动因公式编辑器删除按钮 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-10-19
 * 
 * @author:leixia
 */
@SuppressWarnings("serial")
public class DriverFormulaDeleteAction extends AbstractAction implements IFormulaActionWithDialog {

    /**
     * 构造方法
     */
    public DriverFormulaDeleteAction() {
        this.putValue("Name", CMDriverLangConst.getREF_BTN_DELETE());
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
        FormulaRealEditorPanel realEditorPanel = this.getFormulaRealEditorPanel();
        String formula = realEditorPanel.getConvertedText();
        String newformula = "";
        String formulacode = this.getFormulaValueEditorPanel().getConvertedText();
        String newformulacode = "";
        if (null == formula || formula.trim().length() <= 0) {
            this.getFormulaValueEditorPanel().setConvertedText(null);
            return;
        }
        // 拆解公式,生成键值对Map<显示名称+起止位置,后台编码+起止位置>
        Map<FormulaItemVO, FormulaItemVO> formulaMap =
                new DriverFormulaToolUtil().generateFormulaMap(formula, formulacode);
        int mousePos = realEditorPanel.getFormulaEditor().getCaretPosition();
        for (Entry<FormulaItemVO, FormulaItemVO> entry : formulaMap.entrySet()) {
            if ((entry.getKey().getStartIndex() < mousePos || entry.getKey().getStartIndex() == 0)
                    && (entry.getKey().getEndIndex() >= mousePos - 1 || entry.getKey().getEndIndex() == -1)) {
                newformula = formula.substring(0, entry.getKey().getStartIndex());
                if (entry.getKey().getEndIndex() != -1) {
                    newformula = newformula + formula.substring(entry.getKey().getEndIndex() + 1);
                }
                mousePos = entry.getKey().getStartIndex();
                newformulacode = formulacode.substring(0, entry.getValue().getStartIndex());
                if (entry.getKey().getEndIndex() != -1) {
                    newformulacode = newformulacode + formulacode.substring(entry.getValue().getEndIndex() + 1);
                }
                break;
            }
        }
        this.getFormulaRealEditorPanel().setConvertedText(newformula);
        realEditorPanel.getFormulaEditor().setCaretPosition(mousePos);
        this.getFormulaValueEditorPanel().setConvertedText(newformulacode);
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
