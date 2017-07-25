package nc.ui.mapub.driver.view.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.border.LineBorder;

import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.formula.dialog.FormulaEventSource;
import nc.ui.pub.formula.dialog.FormulaEventSource.FormulaEventType;
import nc.ui.pub.formula.dialog.IFormulaEventListener;

/**
 * <b> 运算符按钮 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-4-20
 * 
 * @author:wangtf
 */
public class DriverFormulaOperatorPanel extends UIPanel {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = -8109346213142017196L;

    /**
     * 按钮panel
     */
    private UIPanel operatorPanel;

    /**
     * 运算按钮
     */
    private UIButton[] caculateAndLogicBts;

    /**
     * 运算符
     */
    private String[] caculateAndLogics = {
        "+", "-", "*", "/", "(", ")"
    };

    /**
     * 监听
     */
    private List<IFormulaEventListener> listeners;

    /**
     * 构造方法
     */
    public DriverFormulaOperatorPanel() {
        this.setLayout(new BorderLayout());
        this.add(this.getPanelOperate(), BorderLayout.CENTER);
    }

    /**
     * 获取按钮面板
     * 
     * @return 按钮面板
     */
    private UIPanel getPanelOperate() {
        if (this.operatorPanel == null) {
            this.operatorPanel = new nc.ui.pub.beans.UIPanel();
            this.operatorPanel.setName("UIPanelOprate");
            this.operatorPanel.setPreferredSize(new java.awt.Dimension(10, 200));
            this.operatorPanel.setBorder(new LineBorder(this.operatorPanel.getBackground(), 5));
            this.operatorPanel.setLayout(new java.awt.GridLayout(3, 4));
            this.operatorPanel.setBounds(6, 110, 186, 75);
            UIButton[] bts = this.getCalculatAndLogicButtons();
            if (bts != null) {
                for (int i = 0; i < bts.length; i++) {
                    this.getPanelOperate().add(bts[i], bts[i].getName());
                }
            }
        }
        return this.operatorPanel;
    }

    /**
     * 获取按钮引用
     * 
     * @return 按钮
     */
    private UIButton[] getCalculatAndLogicButtons() {
        if (this.caculateAndLogicBts == null) {
            this.caculateAndLogicBts = this.createButtons(this.getCalculatAndLogicBtnProps());
        }
        return this.caculateAndLogicBts;
    }

    /**
     * 获取按钮文本
     * 
     * @return 按钮的文本
     */
    private String[][] getCalculatAndLogicBtnProps() {
        String[] caculateAndLogicsTemp = this.caculateAndLogics;
        String[][] textAndCmds = new String[caculateAndLogicsTemp.length][3];
        for (int i = 0; i < textAndCmds.length; i++) {
            textAndCmds[i][0] = caculateAndLogicsTemp[i];
            textAndCmds[i][1] = caculateAndLogicsTemp[i];
            textAndCmds[i][2] = caculateAndLogicsTemp[i];
        }
        return textAndCmds;
    }

    /**
     * 创建按钮
     * 
     * @param textAndCmds
     *            按钮文本
     * @return 按钮
     */
    private UIButton[] createButtons(String[][] textAndCmds) {
        if (textAndCmds == null || textAndCmds.length == 0) {
            return null;
        }
        int size = textAndCmds.length;
        UIButton[] bts = new UIButton[size];
        for (int i = 0; i < size; i++) {
            bts[i] = new UIButton();
            bts[i].setText(textAndCmds[i][0]);
            bts[i].setName(textAndCmds[i][1]);
            bts[i].setActionCommand(textAndCmds[i][2]);
            bts[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (DriverFormulaOperatorPanel.this.getListeners() != null) {
                        // for (IFormulaEventListener listener : getListeners())
                        // {
                        // FormulaEventSource eventSource = new
                        // FormulaEventSource();
                        // listener.notifyFormulaEvent(eventSource);
                        // }
                        for (IFormulaEventListener listener : DriverFormulaOperatorPanel.this.getListeners()) {
                            FormulaEventSource eventSource = new FormulaEventSource();
                            eventSource.setEventSource(e.getSource());
                            String name = ((UIButton) e.getSource()).getName();
                            eventSource.setEventType(FormulaEventType.INSERT_TO_EDITOR);
                            eventSource.setNewString(name);
                            listener.notifyFormulaEvent(eventSource);
                        }
                    }
                }
            });
        }
        return bts;
    }

    /**
     * 设置监听
     * 
     * @param listeners
     *            监听
     */
    public void setListeners(List<IFormulaEventListener> listeners) {
        this.listeners = listeners;
    }

    /**
     * 获取监听
     * 
     * @return 监听
     */
    public List<IFormulaEventListener> getListeners() {
        return this.listeners;
    }
}
