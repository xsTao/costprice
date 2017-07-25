package nc.ui.mapub.driver.view.dialog;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;

import nc.ui.pub.beans.UIList;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIScrollPane;
import nc.ui.pub.formula.dialog.FormulaEventSource;
import nc.ui.pub.formula.dialog.FormulaEventSource.FormulaEventType;
import nc.ui.pub.formula.dialog.FormulaRealEditorPanel;
import nc.ui.pub.formula.dialog.IFormulaEventListener;
import nc.ui.pub.formula.dialog.IFormulaTabBuilder;
import nc.ui.pub.formula.manager.IKeyWord;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.DriverQueryCondition;
import nc.vo.pub.formulaedit.FormulaItem;
import nc.vo.uif2.LoginContext;

/**
 * 用于显示数据库表和其中字段的界面，通常展示在变量区域中 并可以根据表名进行过滤操作 since V60
 *
 * @author wangtf
 */
public abstract class AbstractDriverTabBuilder extends UIPanel implements IFormulaTabBuilder {
    /**
     * 查询条件 不是每个tab也都需要
     */
    private DriverQueryCondition queryCondition;

    /**
     * 序列化id ss
     */
    private static final long serialVersionUID = 413127735206256115L;

    /**
     * 环境变量
     */
    private LoginContext loginContext;

    /**
     * 数据库表的主界面
     */
    private UIPanel variablePanel = null;

    /**
     * 存放数据库表list的UIScrollPane
     */
    private UIScrollPane variableListScrollPane = null;

    /**
     * 数据库表的tableUIList,显示数据库表的List
     */
    private UIList variableUIList = null;

    /**
     * 实际操作的界面
     */
    private FormulaRealEditorPanel formulaRealEditorPanel;

    /**
     * 作为主题对象注册的观察者list
     */
    private List<IFormulaEventListener> listeners;

    /**
     * 初始化数据
     *
     * @see nc.ui.pub.formula.dialog.IFormulaTabBuilder#initUI()
     */
    @Override
    public void initUI() {
        this.setLayout(new BorderLayout());
        this.add(this.getVariablePanel(), BorderLayout.CENTER);

        // this.setUIListData();
    }

    /**
     * 设置列表中的数据
     */
    public void setUIListData() {
        Map<String, FormulaItem> allTableItems = this.getAllVariableItems();
        this.updateModel(this.variableUIList, allTableItems);

    }

    /**
     * 显示列表的控件
     *
     * @return UIList
     */
    private UIList getVariableUIList() {
        if (this.variableUIList == null) {
            this.variableUIList = new UIList();
            this.variableUIList.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    AbstractDriverTabBuilder.this.mouseClick(e);
                }
            });
            if (this.variableUIList.getVisibleRowCount() >= 1) {
                this.variableUIList.setSelectedIndex(0);
            }
        }
        return this.variableUIList;
    }

    /**
     * panel
     *
     * @return UIScrollPane
     */
    private UIScrollPane getVariableListScrollPane() {
        if (this.variableListScrollPane == null) {
            this.variableListScrollPane = new UIScrollPane(this.getVariableUIList());
        }
        return this.variableListScrollPane;
    }

    /**
     * 构造列表中的数据
     *
     * @return model中的数据
     */
    public abstract Map<String, FormulaItem> getAllVariableItems();

    /**
     * tab页签名称
     *
     * @return 页签名称
     */
    @Override
    public abstract String getTabName();

    /**
     * UIPanel
     *
     * @return UIPanel
     */
    private UIPanel getVariablePanel() {
        if (this.variablePanel == null) {
            this.variablePanel = new UIPanel();
            this.variablePanel.setLayout(new BorderLayout());
            this.variablePanel.add(this.getVariableListScrollPane(), BorderLayout.CENTER);

        }
        return this.variablePanel;
    }

    /**
     * 更新List的模型，根据Items
     *
     * @param list
     *            更新的list
     * @param itemsMap
     *            list项目
     */
    @SuppressWarnings({
        "rawtypes", "unchecked"
    })
    private void updateModel(UIList list, Map<String, FormulaItem> itemsMap) {
        DefaultListModel listModel = new DefaultListModel();
        List<String> itemsList = new ArrayList<String>();

        if (itemsMap == null) {
            list.setModel(listModel);
            return;

        }
        itemsList.addAll(itemsMap.keySet());
        // Collections.sort(itemsList);
        for (String item : itemsList) {
            listModel.addElement(item);
        }
        list.setModel(listModel);

    }

    /**
     * 返回其wordtype用于区分关键字与函数，默认为函数实现。 有两种结果：IKeyWord.FUNCTION_TYPE,IKeyWord.WORD_TYPE 用来指明该选项卡为变量还是函数
     *
     * @return IKeyWord.FUNCTION_TYPE,IKeyWord.WORD_TYPE
     */
    @Override
    public int getWordType() {
        return IKeyWord.WORD_TYPE;
    }

    /**
     * 获得监听
     *
     * @return 监听
     */
    @Override
    public List<IFormulaEventListener> getListeners() {
        return this.listeners;
    }

    /**
     * 设置监听
     *
     * @param listeners
     *            监听
     */
    @Override
    public void setListeners(List<IFormulaEventListener> listeners) {
        this.listeners = listeners;
    }

    @Override
    public Map<String, FormulaItem> getName2FormulaItemMap() {
        return this.getAllVariableItems();
    }

    @Override
    public void setWordType(int wordType) {

    }

    /**
     * 公式的边界界面
     *
     * @return 公式的编辑界面
     */
    public FormulaRealEditorPanel getFormulaRealEditorPanel() {
        return this.formulaRealEditorPanel;
    }

    /**
     * 公式的边界界面
     *
     * @param formulaRealEditorPanel
     *            公式的边界界面
     */
    public void setFormulaRealEditorPanel(FormulaRealEditorPanel formulaRealEditorPanel) {
        this.formulaRealEditorPanel = formulaRealEditorPanel;
    }

    /**
     * 获得环境变量
     *
     * @return 环境变量
     */
    public LoginContext getLoginContext() {
        return this.loginContext;
    }

    /**
     * 设置环境变量
     *
     * @param loginContext
     *            环境变量
     */
    public void setLoginContext(LoginContext loginContext) {
        this.loginContext = loginContext;
    }

    /**
     * 鼠标点击时间，子类可以覆盖
     *
     * @param e
     *            鼠标事件
     */
    private void mouseClick(MouseEvent e) {
        String tableName = (String) this.getVariableUIList().getSelectedValue();
        FormulaItem formulaItem = this.getName2FormulaItemMap().get(tableName);
        if (formulaItem != null) {
            DriverFormulaEventSource eventSource = new DriverFormulaEventSource();
            eventSource.setEventSource(this);
            int count = e.getClickCount();
            if (count == 1) {
                // 提示信息至提示界面中
                this.mouseOneClicked(e, formulaItem, eventSource);
            }
            else if (count == 2) {
                this.mouseDoubleClicked(e, formulaItem, eventSource);
            }
            if (this.getListeners() != null) {
                for (IFormulaEventListener listener : this.getListeners()) {
                    listener.notifyFormulaEvent(eventSource);
                }
            }
        }
    }

    /**
     * 鼠标单击事件
     *
     * @param e
     *            单击时间
     * @param formulaItem
     *            公式项目
     * @param eventSource
     *            事件源
     */
    protected void mouseOneClicked(MouseEvent e, FormulaItem formulaItem, FormulaEventSource eventSource) {

        // 触发查找数据表的所有字段,并给予提示信息至提示界面中
        eventSource.setEventType(FormulaEventType.MESSAGE_TO_HINTPANEL);
        eventSource.setNewString(formulaItem.getHintMsg());
    }

    /**
     * 鼠标双击事件
     *
     * @param e
     *            双击事件
     * @param formulaItem
     *            公式项目
     * @param eventSource
     *            事件源
     */
    protected void mouseDoubleClicked(MouseEvent e, FormulaItem formulaItem, DriverFormulaEventSource eventSource) {

        // 将表的名称添加到编辑器界面中
        eventSource.setEventType(FormulaEventType.INSERT_TO_EDITOR);
        eventSource.setNewString(formulaItem.getDisplayName());
        eventSource.setNewValueString(formulaItem.getInputSig());
        // 暂不支持多语切换
        // eventSource.setMultiLangs(((DriverFormulaItem) formulaItem).getMultiLang());
    }

    /**
     * 获取查询条件
     *
     * @return 查询条件
     */
    protected DriverQueryCondition getQueryCondtion() {
        if (this.queryCondition == null) {
            this.queryCondition = new DriverQueryCondition();
        }
        this.queryCondition.setPk_org(this.getLoginContext().getPk_org());
        this.queryCondition.setPk_group(this.getLoginContext().getPk_group());

        return this.queryCondition;
    }

    /**
     * 构造物料的显示文本
     *
     * @param strprifx
     *            文本前缀
     * @return 显示的文本
     */
    protected String getText(String strprifx, String code, String name) {
        StringBuffer showText = new StringBuffer();
        showText.append(strprifx + CMDriverLangConst.FORMULA_SPLIT_START);
        showText.append(code + "~" + name);
        showText.append(CMDriverLangConst.FORMULA_SPLIT_END);
        return showText.toString();
    }

    protected String getCode(String strprifx, String id) {
        StringBuffer showText = new StringBuffer();
        showText.append(strprifx + CMDriverLangConst.FORMULA_SPLIT_START);
        showText.append(id);
        showText.append(CMDriverLangConst.FORMULA_SPLIT_END);
        return showText.toString();
    }

}
