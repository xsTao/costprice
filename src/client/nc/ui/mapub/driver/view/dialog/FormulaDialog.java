package nc.ui.mapub.driver.view.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nc.ui.mapub.driver.view.dialog.AbstractDriverTabBuilder;
import nc.ui.mapub.driver.view.dialog.DriverDefaultFormulaEditorPanel;
import nc.ui.pub.formula.dialog.FormulaVariablePanel;
import nc.ui.pub.formula.dialog.IFormulaActionWithDialog;
import nc.ui.pub.formula.dialog.IFormulaEditorPanel;
import nc.ui.pub.formula.dialog.IFormulaRealEditorPanel;
import nc.ui.pub.formula.dialog.IFormulaTabBuilder;
import nc.ui.pub.formula.dialog.IFormulaTabbedPanel;
import nc.ui.pub.formula.dialog.ReplaceConvertor;
import nc.ui.pub.formulaedit.FormulaEditorDialog;
import nc.ui.pub.formulaedit.FormulaWordSorter;
import nc.ui.pub.formulaparse.FormulaParse;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.uif2.LoginContext;

/**
 * <b>��ʽ�Ի��� </b>
 * <p>
 * Ϊ�˴���Longincontext
 * </p>
 * ��������:2010-3-31
 * 
 * @author:wangtf
 */
public class FormulaDialog extends FormulaEditorDialog {
    /**
     * ���л�id
     */
    private static final long serialVersionUID = -8637379483728890440L;

    // �༭���
    private boolean hasEditFormula = false;

    public boolean isHasEditFormula() {
        return this.hasEditFormula;
    }

    public void setHasEditFormula(boolean hasEditFormula) {
        this.hasEditFormula = hasEditFormula;
    }

    /**
     * ��ʽ�༭����
     */
    private static final int HEIGHT = 600;

    /**
     * ��ʽ�༭����
     */
    private static final int WIDTH = 750;

    /**
     * ��������
     */
    private LoginContext context;

    /**
     * �����ļ�·��
     */
    private String configXmlFilePath;

    /**
     * IFormulaEditorPanel
     */
    private IFormulaEditorPanel formulaEditorPanel;

    /**
     * ��ʽcode
     */
    private String formulacode = "";

    /**
     * ��ʽ
     */
    private String formulaDesc;

    /**
     * formulaDescWithDummyMap
     */
    private String formulaDescWithDummyMap;

    /**
     * ���췽��
     * 
     * @param parent
     *            ����
     * @param configXmlFilePath
     *            xml·��
     * @param context
     *            ��������
     */
    public FormulaDialog(Container parent, String configXmlFilePath, LoginContext context) {
        super(parent, configXmlFilePath);
        this.configXmlFilePath = configXmlFilePath;
        this.context = context;
        this.initialize(new FormulaParse());
    }

    /**
     * ��ʼ��
     * 
     * @param parse
     *            FormulaParse
     */
    private void initialize(FormulaParse parse) {
        this.setTitle(CMDriverLangConst.getREFMODELTITLE());
        this.formulaEditorPanel = new DriverDefaultFormulaEditorPanel();
        this.formulaEditorPanel.initUI(this.configXmlFilePath, parse);
        this.setLayout(new BorderLayout());
        this.add((Component) this.formulaEditorPanel, "Center");
        this.setSize(new Dimension(FormulaDialog.WIDTH, FormulaDialog.HEIGHT));
        this.initUI();
    }

    @Override
    public void initUI() {
        this.addPropertyToButtons();
    }

    /**
     * add Buttons
     */
    private void addPropertyToButtons() {
        if (null == this.formulaEditorPanel) {
            return;
        }
        List<IFormulaActionWithDialog> actions = this.formulaEditorPanel.getFormulaDialogActionList();
        if (actions != null) {
            IFormulaActionWithDialog action;
            for (Iterator<IFormulaActionWithDialog> i = actions.iterator(); i.hasNext(); action.setDialog(this)) {
                action = i.next();
            }
        }
    }

    @Override
    public boolean isBuilderExist(String tabName, int option) {
        switch (option) {
            case 0:
                if (this.getFormulaFunctionPanel() != null) {
                    return this.getFormulaFunctionPanel().isTabExist(tabName);
                }
            case 1:
                if (this.getFormulaVariablePanel() != null) {
                    return this.getFormulaVariablePanel().isTabExist(tabName);
                }
            default:
                return true;
        }
    }

    @Override
    public int getTabNumber(int option) {
        switch (option) {
            case 0:
                if (this.getFormulaFunctionPanel() != null) {
                    return this.getFormulaFunctionPanel().getTabNum();
                }
            case 1:
                if (this.getFormulaVariablePanel() != null) {
                    return this.getFormulaVariablePanel().getTabNum();
                }
            default:
                return 0;
        }
    }

    @Override
    public void addCustomTabBuilder(int index, IFormulaTabBuilder builder, int option) {
        switch (option) {
            case 0:
                if (this.getFormulaFunctionPanel() != null) {
                    this.getFormulaFunctionPanel().addTabBuilderToTabbedPane(index, builder);
                }
                break;

            case 1:
                if (this.getFormulaVariablePanel() != null) {
                    this.getFormulaVariablePanel().addTabBuilderToTabbedPane(index, builder);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void addCustomTabBuilder(IFormulaTabBuilder builder, int option) {
        this.addCustomTabBuilder(this.getTabNumber(option), builder, option);
    }

    @Override
    public void setCustomTabBuilder(int index, IFormulaTabBuilder builder, int option) {
        if (index < this.getTabNumber(option)) {
            this.removeTabBuilder(index, option);
            this.addCustomTabBuilder(index, builder, option);
        }
    }

    @Override
    public void removeTabBuilder(int index, int option) {
        switch (option) {
            case 0: // '\0'
                if (this.getFormulaFunctionPanel() != null) {
                    this.getFormulaFunctionPanel().removeTabBuilder(index);
                }
                break;

            case 1: // '\001'
                if (this.getFormulaVariablePanel() != null) {
                    this.getFormulaVariablePanel().removeTabBuilder(index);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public String getFormulaDescWithDummyMap() {
        return this.formulaDescWithDummyMap;
    }

    @Override
    public void setDummyInputSigMap(Map<String, String> mapInputSig) {
        this.formulaEditorPanel.getFormulaRealEditorPanel().addConvertor(new ReplaceConvertor(mapInputSig));
    }

    @Override
    public String getFormulaDesc() {
        return this.formulaDesc;
    }

    @Override
    public void setFormulaDesc(String formulaDesc) {
        this.formulaDesc = formulaDesc;
        this.getFormulaRealEditorPanel().setConvertedText(formulaDesc);
    }

    @Override
    public int getBuilderIndex(String tabname, int option) {
        switch (option) {
            case 0: // '\0'
                return this.getFormulaFunctionPanel().getBuilderIndex(tabname);

            case 1: // '\001'
                return this.getFormulaVariablePanel().getBuilderIndex(tabname);
            default:
        }
        return -1;
    }

    @Override
    public void setSelectedTab(String tabName, int option) {
        switch (option) {
            case 0: // '\0'
                this.getFormulaFunctionPanel().setSelectedTab(tabName);
                break;

            case 1: // '\001'
                this.getFormulaVariablePanel().setSelectedTab(tabName);
                break;
            default:
        }
    }

    /**
     * ��û�������
     * 
     * @return ��������
     */
    public LoginContext getContext() {
        return this.context;
    }

    /**
     * ���û�������
     * 
     * @param context
     *            ��������
     */
    public void setContext(LoginContext context) {
        this.context = context;
    }

    /**
     * ���±���ҳ�������
     */
    public void updateListData() {
        List<IFormulaTabBuilder> listBuilder =
                ((FormulaVariablePanel) this.getFormulaVariablePanel()).getCustomerTabBuilders();

        for (IFormulaTabBuilder formulaTabBuilder : listBuilder) {
            ((AbstractDriverTabBuilder) formulaTabBuilder).setLoginContext(this.getContext());

            ((AbstractDriverTabBuilder) formulaTabBuilder).setUIListData();

        }
    }

    /**
     * formulacode get method
     * 
     * @return String
     */
    public String getFormulacode() {
        return this.formulacode;
    }

    /**
     * formulacode set method
     * 
     * @param formulacode
     *            String
     */
    public void setFormulacode(String formulacode) {
        this.formulacode = formulacode;
    }

    /**
     * FormulaValueRealEditorPanel get method
     * 
     * @return IFormulaRealEditorPanel
     */
    public IFormulaRealEditorPanel getFormulaValueRealEditorPanel() {
        return ((DriverDefaultFormulaEditorPanel) this.formulaEditorPanel).getFormulaValueRealEditorPanel();
    }

    @Override
    public IFormulaTabbedPanel getFormulaFunctionPanel() {
        return ((DriverDefaultFormulaEditorPanel) this.formulaEditorPanel).getFormulaFunctionPanel();
    }

    @Override
    public IFormulaRealEditorPanel getFormulaRealEditorPanel() {
        return ((DriverDefaultFormulaEditorPanel) this.formulaEditorPanel).getFormulaRealEditorPanel();
    }

    @Override
    public IFormulaTabbedPanel getFormulaVariablePanel() {
        return ((DriverDefaultFormulaEditorPanel) this.formulaEditorPanel).getFormulaVariablePanel();
    }

    @Override
    public FormulaWordSorter getFormulaWordSorter() {
        return ((DriverDefaultFormulaEditorPanel) this.formulaEditorPanel).getFormulaRealEditorPanel()
                .getFormulaWordSorter();
    }
}
