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
 * <b> ��ʽ��refpane�����ڴ�һ���༭��ʽ�ĶԻ��� </b>
 * <p>
 * ��ϸ��������
 * </p>
 * ��������:2010-3-29
 * 
 * @author:wangtf
 */
public class DriverFormulaPane extends FormulaRefPane {

    /**
     * ��ʾ�Ĺ�ʽ
     */
    String showText = null;

    /**
     * ʵ�ʵĹ�ʽ
     */
    String realFormula = null;

    /**
     * ��������
     */
    LoginContext context = null;

    /**
     * ��ʽ�Ի���
     */
    FormulaDialog dlg = null;

    /**
     * DriverBillForm
     */
    DriverBillForm billform = null;

    /**
     * ���л�id
     */
    private static final long serialVersionUID = -1151116988859681710L;

    /**
     * ���췽��
     * 
     * @param parent
     *            ����
     * @param context
     *            ��������
     * @param billform
     *            DriverBillForm
     */
    public DriverFormulaPane(Container parent, LoginContext context, DriverBillForm billform) {
        super(parent);

        // ���¹������
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
            // ��������
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
     * ��ȡ��������
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
     * �����ʾ�Ĺ�ʽ
     * 
     * @return ��ʾ�Ĺ�ʽ
     */
    public String getShowText() {
        return this.showText;
    }

    /**
     * ������ʾ�Ĺ�ʽ
     * 
     * @param showText
     *            ��ʾ�Ĺ�ʽ
     */
    public void setShowText(String showText) {
        this.showText = showText;
    }

    /**
     * ���ʵ�ʵĹ�ʽ
     * 
     * @return ʵ�ʵĹ�ʽ
     */
    public String getRealFormula() {
        return this.realFormula;
    }

    /**
     * ����ʵ�ʵĹ�ʽ
     * 
     * @param realFormula
     *            ʵ�ʵĹ�ʽ
     */
    public void setRealFormula(String realFormula) {
        this.realFormula = realFormula;
    }
}
