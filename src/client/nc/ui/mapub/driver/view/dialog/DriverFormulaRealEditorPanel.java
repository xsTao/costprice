package nc.ui.mapub.driver.view.dialog;

import nc.ui.mapub.driver.view.dialog.DriverFormulaCodeRealEditorPanel;
import nc.ui.pub.formula.dialog.FormulaEventSource;
import nc.ui.pub.formula.dialog.FormulaEventSource.FormulaEventType;
import nc.ui.pub.formula.dialog.FormulaRealEditorPanel;

/**
 * <b> 公式编辑panel </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-10-28
 * 
 * @author:leixia
 */
@SuppressWarnings("serial")
public class DriverFormulaRealEditorPanel extends FormulaRealEditorPanel {

    private DriverFormulaCodeRealEditorPanel formulaCodeEditorPanel;

    public DriverFormulaCodeRealEditorPanel getformulaCodeEditorPanel() {
        return this.formulaCodeEditorPanel;
    }

    public void setformulaCodeEditorPanel(DriverFormulaCodeRealEditorPanel formulaCodeEditorPanel) {
        this.formulaCodeEditorPanel = formulaCodeEditorPanel;
    }

    @Override
    public void initUI() {
        super.initUI();
        this.getFormulaEditor().setEnabled(false);
    }

    @Override
    public void notifyFormulaEvent(FormulaEventSource arg0) {
        if (arg0.getEventType() == FormulaEventType.INSERT_TO_EDITOR) {
            String content = arg0.getNewString();
            // 变量用大括号分隔
            if (arg0.getNewString().length() > 1 && !arg0.getNewString().equals("00")) {
                content = "{" + content + "}";
            }
            this.getFormulaEditor().requestFocus();
            // 不支持覆盖修改，Code同步存在问题
            // String sel = this.getFormulaEditor().getSelectedText();
            // if (sel != null) {
            // this.getFormulaEditor().replaceSelection(content);
            // }
            // else {

            int pos = this.getFormulaEditor().getCaretPosition();
            int insertPos = -1;
            String forumla = this.getFormulaEditor().getText();
            this.formulaCodeEditorPanel.setFormula(forumla);
            this.formulaCodeEditorPanel.setEditorIndex(pos);
            if (pos >= 0 && pos < this.getFormulaEditor().getText().length()) {
                if (pos != 0) {
                    for (int i = pos; i < forumla.length(); i++) {
                        if (insertPos == -1
                                && (this.checkCharIsSingel(forumla.charAt(i - 1)) || this.checkCharIsSingel(forumla
                                        .charAt(i)))) {
                            insertPos = i;
                        }
                        if (forumla.charAt(i) == '{') {
                            if (insertPos == -1) {
                                insertPos = i;
                            }
                            break;
                        }
                        if (forumla.charAt(i - 1) == '}') {
                            insertPos = i;
                            break;
                        }
                    }
                }
                else {
                    insertPos = 0;
                }
                // if (insertPos != pos) {
                this.getFormulaEditor().setCaretPosition(insertPos);
                // }
                this.getFormulaEditor().insertText(content, insertPos);
            }
            else {
                this.getFormulaEditor().appendText(content);
            }
            // }
        }
    }

    private boolean checkCharIsSingel(char aChar) {
        if (aChar == '+' || aChar == '-' || aChar == '*' || aChar == '/' || aChar == '(' || aChar == ')') {
            return true;
        }
        return false;
    }
}
