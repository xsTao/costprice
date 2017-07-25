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
 * ������ʾ���ݿ��������ֶεĽ��棬ͨ��չʾ�ڱ��������� �����Ը��ݱ������й��˲��� since V60
 *
 * @author wangtf
 */
public abstract class AbstractDriverTabBuilder extends UIPanel implements IFormulaTabBuilder {
    /**
     * ��ѯ���� ����ÿ��tabҲ����Ҫ
     */
    private DriverQueryCondition queryCondition;

    /**
     * ���л�id ss
     */
    private static final long serialVersionUID = 413127735206256115L;

    /**
     * ��������
     */
    private LoginContext loginContext;

    /**
     * ���ݿ���������
     */
    private UIPanel variablePanel = null;

    /**
     * ������ݿ��list��UIScrollPane
     */
    private UIScrollPane variableListScrollPane = null;

    /**
     * ���ݿ���tableUIList,��ʾ���ݿ���List
     */
    private UIList variableUIList = null;

    /**
     * ʵ�ʲ����Ľ���
     */
    private FormulaRealEditorPanel formulaRealEditorPanel;

    /**
     * ��Ϊ�������ע��Ĺ۲���list
     */
    private List<IFormulaEventListener> listeners;

    /**
     * ��ʼ������
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
     * �����б��е�����
     */
    public void setUIListData() {
        Map<String, FormulaItem> allTableItems = this.getAllVariableItems();
        this.updateModel(this.variableUIList, allTableItems);

    }

    /**
     * ��ʾ�б�Ŀؼ�
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
     * �����б��е�����
     *
     * @return model�е�����
     */
    public abstract Map<String, FormulaItem> getAllVariableItems();

    /**
     * tabҳǩ����
     *
     * @return ҳǩ����
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
     * ����List��ģ�ͣ�����Items
     *
     * @param list
     *            ���µ�list
     * @param itemsMap
     *            list��Ŀ
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
     * ������wordtype�������ֹؼ����뺯����Ĭ��Ϊ����ʵ�֡� �����ֽ����IKeyWord.FUNCTION_TYPE,IKeyWord.WORD_TYPE ����ָ����ѡ�Ϊ�������Ǻ���
     *
     * @return IKeyWord.FUNCTION_TYPE,IKeyWord.WORD_TYPE
     */
    @Override
    public int getWordType() {
        return IKeyWord.WORD_TYPE;
    }

    /**
     * ��ü���
     *
     * @return ����
     */
    @Override
    public List<IFormulaEventListener> getListeners() {
        return this.listeners;
    }

    /**
     * ���ü���
     *
     * @param listeners
     *            ����
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
     * ��ʽ�ı߽����
     *
     * @return ��ʽ�ı༭����
     */
    public FormulaRealEditorPanel getFormulaRealEditorPanel() {
        return this.formulaRealEditorPanel;
    }

    /**
     * ��ʽ�ı߽����
     *
     * @param formulaRealEditorPanel
     *            ��ʽ�ı߽����
     */
    public void setFormulaRealEditorPanel(FormulaRealEditorPanel formulaRealEditorPanel) {
        this.formulaRealEditorPanel = formulaRealEditorPanel;
    }

    /**
     * ��û�������
     *
     * @return ��������
     */
    public LoginContext getLoginContext() {
        return this.loginContext;
    }

    /**
     * ���û�������
     *
     * @param loginContext
     *            ��������
     */
    public void setLoginContext(LoginContext loginContext) {
        this.loginContext = loginContext;
    }

    /**
     * �����ʱ�䣬������Ը���
     *
     * @param e
     *            ����¼�
     */
    private void mouseClick(MouseEvent e) {
        String tableName = (String) this.getVariableUIList().getSelectedValue();
        FormulaItem formulaItem = this.getName2FormulaItemMap().get(tableName);
        if (formulaItem != null) {
            DriverFormulaEventSource eventSource = new DriverFormulaEventSource();
            eventSource.setEventSource(this);
            int count = e.getClickCount();
            if (count == 1) {
                // ��ʾ��Ϣ����ʾ������
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
     * ��굥���¼�
     *
     * @param e
     *            ����ʱ��
     * @param formulaItem
     *            ��ʽ��Ŀ
     * @param eventSource
     *            �¼�Դ
     */
    protected void mouseOneClicked(MouseEvent e, FormulaItem formulaItem, FormulaEventSource eventSource) {

        // �����������ݱ�������ֶ�,��������ʾ��Ϣ����ʾ������
        eventSource.setEventType(FormulaEventType.MESSAGE_TO_HINTPANEL);
        eventSource.setNewString(formulaItem.getHintMsg());
    }

    /**
     * ���˫���¼�
     *
     * @param e
     *            ˫���¼�
     * @param formulaItem
     *            ��ʽ��Ŀ
     * @param eventSource
     *            �¼�Դ
     */
    protected void mouseDoubleClicked(MouseEvent e, FormulaItem formulaItem, DriverFormulaEventSource eventSource) {

        // �����������ӵ��༭��������
        eventSource.setEventType(FormulaEventType.INSERT_TO_EDITOR);
        eventSource.setNewString(formulaItem.getDisplayName());
        eventSource.setNewValueString(formulaItem.getInputSig());
        // �ݲ�֧�ֶ����л�
        // eventSource.setMultiLangs(((DriverFormulaItem) formulaItem).getMultiLang());
    }

    /**
     * ��ȡ��ѯ����
     *
     * @return ��ѯ����
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
     * �������ϵ���ʾ�ı�
     *
     * @param strprifx
     *            �ı�ǰ׺
     * @return ��ʾ���ı�
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
