package nc.ui.mapub.driver.view.dialog;

import java.awt.BorderLayout;
import java.util.List;

import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.formula.dialog.DefaultFormulaEditorPanel;
import nc.ui.pub.formula.dialog.FormulaButtonPanel;
import nc.ui.pub.formula.dialog.IFormulaActionWithDialog;
import nc.ui.pub.formula.dialog.IFormulaRealEditorPanel;
import nc.ui.pub.formula.dialog.IFormulaTabbedPanel;
import nc.vo.pub.formulaset.FormulaParseFather;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * <b> DefaultFormulaEditorPanel</b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-10-19
 * 
 * @author:leixia
 */
@SuppressWarnings("serial")
public class DriverDefaultFormulaEditorPanel extends DefaultFormulaEditorPanel {

    /**
     * XmlBeanFactory
     */
    private XmlBeanFactory beanFactory;

    @Override
    public void initUI(String configXmlFilePath, FormulaParseFather formulaParse) {
        ClassLoader classLoader = DefaultFormulaEditorPanel.class.getClassLoader();
        Thread.currentThread().setContextClassLoader(classLoader);
        ClassPathResource res = new ClassPathResource(configXmlFilePath, classLoader);
        this.beanFactory = new XmlBeanFactory(res, null);
        this.beanFactory.setBeanClassLoader(classLoader);
        UIPanel container = (UIPanel) this.beanFactory.getBean("formulaContainer");
        this.setLayout(new BorderLayout());
        this.add(container, "Center");
    }

    /**
     * formulavalueRealEditorPanel get method 简要说明
     * 
     * @see nc.ui.pub.formula.dialog.DefaultFormulaEditorPanel#getFormulaRealEditorPanel()
     * @return IFormulaRealEditorPanel
     */
    public IFormulaRealEditorPanel getFormulaValueRealEditorPanel() {
        return (IFormulaRealEditorPanel) this.beanFactory.getBean("formulavalueRealEditorPanel");
    }

    @Override
    public List<IFormulaActionWithDialog> getFormulaDialogActionList() {
        return ((FormulaButtonPanel) this.beanFactory.getBean("formulaButtonPanel")).getActionsWithDialog();
    }

    @Override
    public IFormulaTabbedPanel getFormulaFunctionPanel() {
        return (IFormulaTabbedPanel) this.beanFactory.getBean("formulaFunctionPanel");
    }

    @Override
    public IFormulaRealEditorPanel getFormulaRealEditorPanel() {
        return (IFormulaRealEditorPanel) this.beanFactory.getBean("formulaRealEditorPanel");
    }

    @Override
    public IFormulaTabbedPanel getFormulaVariablePanel() {
        return (IFormulaTabbedPanel) this.beanFactory.getBean("formulaVariablePanel");
    }

}
