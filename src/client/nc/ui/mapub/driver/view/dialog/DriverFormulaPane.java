package nc.ui.mapub.driver.view.dialog;

import java.awt.Container;

import nc.ui.bill.tools.formulaeditor.FormulaRefPane;
import nc.ui.mapub.driver.view.DriverBillForm;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.formulaedit.FormulaEditorDialog;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.uif2.LoginContext;

/**
 * <b> 公式的refpane，用于打开一个编辑公式的对话框 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-3-29
 * 
 * @author:wangtf
 */
public class DriverFormulaPane extends FormulaRefPane {

    /**
     * 显示的公式
     */
    String showText = null;

    /**
     * 实际的公式
     */
    String realFormula = null;

    /**
     * 环境变量
     */
    LoginContext context = null;

    /**
     * 公式对话框
     */
    FormulaDialog dlg = null;

    /**
     * DriverBillForm
     */
    DriverBillForm billform = null;

    /**
     * 序列化id
     */
    private static final long serialVersionUID = -1151116988859681710L;

    /**
     * 构造方法
     * 
     * @param parent
     *            容器
     * @param context
     *            环境变量
     * @param billform
     *            DriverBillForm
     */
    public DriverFormulaPane(Container parent, LoginContext context, DriverBillForm billform) {
        super(parent);

        // 重新构造界面
        this.context = context;
        this.billform = billform;

        this.init();

    }

    private boolean hasLoadDlg = false;

    public boolean isHasLoadDlg() {
        return this.hasLoadDlg;
    }

    @Override
    public FormulaEditorDialog getDlg() {
        // this.dlg = null;
        if (this.dlg == null) {
            this.dlg =
                    new FormulaDialog(this, "/nc/ui/mapub/driver/view/dialog/formulaedit_config.xml", this.getContext());
            this.hasLoadDlg = true;
            // 更新数据
            this.dlg.updateListData();
        }
        return this.dlg;
    }

    /**
     * dlg set method
     * 
     * @param dialog
     *            FormulaDialog
     */
    public void setDlg(FormulaDialog dialog) {
        this.dlg = dialog;
    }

    /**
     * 获取环境变量
     * 
     * @return 环境变量
     */
    public LoginContext getContext() {
        return this.context;
    }

    /**
     * 设置环境变量
     * 
     * @param context
     *            环境变量
     */
    public void setContext(LoginContext context) {
        this.context = context;
    }

    @Override
    public void onButtonClicked() {
        String tmpString = "";
        String codeString = "";
        BillCardPanel cardpanel = this.billform.getBillCardPanel();
        Object formulavalue = cardpanel.getHeadItem(DriverVO.VFORMULAVALUE).getValueObject();
        Object formulacode = cardpanel.getHeadItem(DriverVO.VFORMULACODE).getValueObject();

        if (null != formulavalue) {
            tmpString = formulavalue.toString();
        }
        if (null != formulacode) {
            codeString = formulacode.toString();
        }

        this.getDlg().setFormulaDesc(tmpString);
        ((DriverFormulaCodeRealEditorPanel) ((FormulaDialog) this.getDlg()).getFormulaValueRealEditorPanel())
                .setConvertedText(codeString);

        this.getDlg().showModal();
        if (this.getDlg().getResult() == UIDialog.ID_OK) {
            tmpString = this.getDlg().getFormulaDesc();
            codeString = ((FormulaDialog) this.getDlg()).getFormulacode();
            if (tmpString != null) {
                tmpString = tmpString.replaceAll("\n", "");
            }
            if (tmpString != null) {
                tmpString = tmpString.replaceAll("\r", "");
            }

            this.setShowText(tmpString);
            this.setText(tmpString);
            cardpanel.setHeadItem(DriverVO.VFORMULACODE, codeString);
            this.FormulaRule = tmpString;
        }
        this.getDlg().destroy();
    }

    /**
     * 获得显示的公式
     * 
     * @return 显示的公式
     */
    public String getShowText() {
        return this.showText;
    }

    /**
     * 设置显示的公式
     * 
     * @param showText
     *            显示的公式
     */
    public void setShowText(String showText) {
        this.showText = showText;
    }

    /**
     * 获得实际的公式
     * 
     * @return 实际的公式
     */
    public String getRealFormula() {
        return this.realFormula;
    }

    /**
     * 设置实际的公式
     * 
     * @param realFormula
     *            实际的公式
     */
    public void setRealFormula(String realFormula) {
        this.realFormula = realFormula;
    }
}
