/**
 * 
 */
package nc.ui.mapub.driver.view.dialog;

import java.util.Map;
import java.util.Map.Entry;

import nc.ui.pub.formula.dialog.FormulaEventSource;
import nc.ui.pub.formula.dialog.FormulaEventSource.FormulaEventType;
import nc.ui.pub.formula.dialog.FormulaRealEditorPanel;
import nc.vo.mapub.driver.entity.FormulaItemVO;

/**
 * <b> 中文公式编辑panel </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-10-19
 * 
 * @author:leixia
 */
@SuppressWarnings("serial")
public class DriverFormulaCodeRealEditorPanel extends FormulaRealEditorPanel {
    private String formula = null;

    public String getFormula() {
        return this.formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    private int editorIndex = -1;

    public int getEditorIndex() {
        return this.editorIndex;
    }

    public void setEditorIndex(int editorIndex) {
        this.editorIndex = editorIndex;
    }

    @Override
    public void notifyFormulaEvent(FormulaEventSource arg0) {
        if (arg0 instanceof DriverFormulaEventSource) {
            DriverFormulaEventSource eventSource = (DriverFormulaEventSource) arg0;
            eventSource.setNewString(eventSource.getNewValueString());
        }
        if (arg0.getEventType() == FormulaEventType.INSERT_TO_EDITOR) {
            String content = arg0.getNewString();
            // 变量用大括号分隔
            if (arg0.getNewString().length() > 1 && !arg0.getNewString().equals("00")) {
                content = "{" + content + "}";
            }
            String formulacode = this.getFormulaEditor().getText();
            int pos = -1;
            // 拆解公式,生成键值对Map<显示名称+起止位置,后台编码+起止位置>
            Map<FormulaItemVO, FormulaItemVO> formulaMap =
                    new DriverFormulaToolUtil().generateFormulaMap(this.formula, formulacode);
            for (Entry<FormulaItemVO, FormulaItemVO> entry : formulaMap.entrySet()) {
                if (entry.getKey().getStartIndex() < this.editorIndex
                        && (entry.getKey().getEndIndex() >= this.editorIndex - 1 || entry.getKey().getEndIndex() == -1)) {
                    if (entry.getKey().getEndIndex() != -1) {
                        pos = entry.getValue().getEndIndex() + 1;
                    }
                    break;
                }
                else if (this.editorIndex == 0) {
                    pos = 0;
                    break;
                }
            }

            if (pos >= 0 && pos <= this.getFormulaEditor().getText().length()) {
                this.getFormulaEditor().setCaretPosition(pos);
                this.getFormulaEditor().insertText(content, pos);
            }
            else {
                this.getFormulaEditor().appendText(content);
            }
        }
    }

    @Override
    public void initUI() {
        super.initUI();
        this.setVisible(false);
    }

}
