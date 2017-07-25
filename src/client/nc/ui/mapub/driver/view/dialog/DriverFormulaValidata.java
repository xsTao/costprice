package nc.ui.mapub.driver.view.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.formula.dialog.AbstractFormulaRealEditorPanel;
import nc.ui.pub.formula.dialog.IFormulaRealEditorPanel;
import nc.vo.mapub.driver.entity.CMDriverLangConst;

/**
 * <b> �ɱ�����ʽ��� </b>
 * <p>
 * ���ɱ������Ƿ���Ϲ淶�� ������ʽ�﷨���ͻ�׼��������Լ��
 * </p>
 * ��������:2010-4-20
 *
 * @author:wangtf
 */
public class DriverFormulaValidata {
    /**
     * ��ʽpanel
     */
    IFormulaRealEditorPanel formulaRealEditorPanel;

    /**
     * ���췽��
     *
     * @param formulaRealEditorPanel
     *            ��ʽ��panel
     */
    public DriverFormulaValidata(IFormulaRealEditorPanel formulaRealEditorPanel) {
        this.formulaRealEditorPanel = formulaRealEditorPanel;
    }

    /**
     * �������
     *
     * @return true ����ͨ�� false ��������
     */
    public boolean validata() {

        String formulaText = this.getFormulaRealEditorPanel().getConvertedText();

        // �滻[], Ϊ""
        formulaText = formulaText.replaceAll("\\[", "");
        formulaText = formulaText.replaceAll("\\]", "");
        // ���ų���Ϊ���ж�
        formulaText = formulaText.replaceAll("\\(%\\)", "");

        // �жϣ����Ƿ��������
        if (!this.checkMatch(formulaText)) {
            return false;
        }

        // �жϴ����ŵĳ�������Ϊ�� ����Ʒ����*2/(����Ʒ����-����Ʒ����)
        if (!this.checkDivZero(formulaText)) {
            return false;
        }

        // �滻ָ�����ַ�
        String replaedString = this.replaceSpecialString(formulaText);
        // �ָ��������ĸ������֣��������ı��������Լ��
        List<String> splitString = new ArrayList<String>();
        if (!this.splitWithCaculateSign(replaedString, splitString)) {
            return false;
        }
        String formulaOldText = this.setWildcard(formulaText);
        formulaText = formulaOldText.replaceAll("\\(", "");
        formulaText = formulaText.replaceAll("\\)", "");
        String[] formulas = this.splitFormulaText(formulaText);
        if (this.getFormulaRealEditorPanel().getFormulaParse().checkExpressArray(formulas)) {
            String msg = this.IsNumOrFloat(splitString);
            if (msg != null) {
                // ��ʽ�����ٰ���һ����ʽ����
                MessageDialog.showErrorDlg((AbstractFormulaRealEditorPanel) this.getFormulaRealEditorPanel(),
                        CMDriverLangConst.getFORMULA_VALIDATE(), msg);
                return false;
            }
            if (!this.businessValidate(formulaOldText)) {
                return false;
            }
        }
        else {
            // �﷨����
            MessageDialog.showErrorDlg((AbstractFormulaRealEditorPanel) this.getFormulaRealEditorPanel(),
                    CMDriverLangConst.getFORMULA_VALIDATE(), CMDriverLangConst.getFORMULA_VALIDATE_FAIL_GRAMMER());
            return false;
        }
        return true;
    }

    /**
     * ��{����}�滻Ϊ$�����������з���Ӱ���ж�
     *
     * @param formula ��ʽ
     * @return �滻��ʽ
     */
    private String setWildcard(String formula) {
        int cusera = formula.indexOf("{");
        if (cusera < 0) {
            return formula;
        }
        StringBuffer newFormula = new StringBuffer();
        boolean noReplace = true;
        for (int i = 0; i < formula.length(); i++) {
            if (formula.charAt(i) == '{') {
                noReplace = false;
                continue;
            }
            if (formula.charAt(i) == '}') {
                newFormula = newFormula.append("$");
                noReplace = true;
            }
            else if (noReplace) {
                newFormula = newFormula.append(formula.charAt(i));
            }
        }
        return newFormula.toString();
    }

    /**
     * �ж������Ƿ�������
     *
     * @param formulaText
     * @return
     */
    private boolean checkMatch(String formulaText) {
        byte[] bytes = formulaText.getBytes();
        byte left = '(';
        byte right = ')';
        int count = 0;

        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == left || bytes[i] == right) {
                count++;
            }
        }
        if (count % 2 != 0) {
            // ���ű����������
            MessageDialog.showErrorDlg((AbstractFormulaRealEditorPanel) this.getFormulaRealEditorPanel(),
                    CMDriverLangConst.getFORMULA_VALIDATE(), CMDriverLangConst.getFORMULA_VALIDATE_FAIL_MATCH());
            return false;
        }
        return true;
    }

    /**
     * �жϳ�������Ϊ��
     *
     * @param formulaText
     * @return
     */
    private boolean checkDivZero(String formulaText) {
        String[] formulas = formulaText.split("/\\(");
        // �����ֳ���
        if (formulas.length > 1) {
            String div = formulas[1].substring(0, formulas[1].lastIndexOf(")"));
            String[] caculateSign = {
                "\\-", "\\*"
            };
            if (div.indexOf("-") > 0) {
                String[] divs = div.split(caculateSign[0]);
                if (divs[0].equals(divs[1])) {
                    // ��������Ϊ��
                    MessageDialog.showErrorDlg((AbstractFormulaRealEditorPanel) this.getFormulaRealEditorPanel(),
                            CMDriverLangConst.getFORMULA_VALIDATE(), CMDriverLangConst.getFORMULA_VALIDATE_FAIL_ZERO());
                    return false;
                }
            }
            if (div.indexOf("*") > 0) {
                String[] divs = div.split(caculateSign[1]);
                for (int i = 0; i < divs.length; i++) {
                    if (divs[i].equals("0")) {
                        // ��������Ϊ��
                        MessageDialog.showErrorDlg((AbstractFormulaRealEditorPanel) this.getFormulaRealEditorPanel(),
                                CMDriverLangConst.getFORMULA_VALIDATE(),
                                CMDriverLangConst.getFORMULA_VALIDATE_FAIL_ZERO());
                        return false;
                    }
                }

            }
        }
        return true;
    }

    /**
     * �ָ��ַ���
     *
     * @param formulaText
     *            �ָ����ַ���
     * @return �ָ��ŵ��ַ���
     */
    private String[] splitFormulaText(String formulaText) {
        formulaText = formulaText.replaceAll("\n", "").replaceAll("\t", "");
        return formulaText.split(";");
    }

    /**
     * �ָ��ַ������������
     *
     * @param formula
     *            ��ʽ
     * @param list
     * @return �ָ��õ��ַ���
     */
    private boolean splitWithCaculateSign(String formula, List<String> list) {
        String[] caculateSign = {
            "\\+", "\\-", "\\*", "/"
        };
        list.add(formula);
        for (int i = 0; i < caculateSign.length; i++) {
            for (int j = list.size() - 1; j >= 0; j--) {
                String[] formulaParts = list.get(j).split(caculateSign[i]);
                list.remove(j);
                if (i == 3
                        && formulaParts.length > 1
                        && (this.isNum(formulaParts[1]) && Integer.parseInt(formulaParts[1]) == 0 || this
                                .isFloat(formulaParts[1]) && Float.parseFloat(formulaParts[1]) == 0)) {
                    // ��������Ϊ��
                    MessageDialog.showErrorDlg((AbstractFormulaRealEditorPanel) this.getFormulaRealEditorPanel(),
                            CMDriverLangConst.getFORMULA_VALIDATE(), CMDriverLangConst.getFORMULA_VALIDATE_FAIL_ZERO());
                    return false;
                }
                this.addArrayToList(formulaParts, list);

            }

        }
        return true;
    }

    /**
     * �滻һЩӰ��У����ֶ�Ϊָ���ַ� ֻΪ��У�飬��Ӱ�����ձ��浽���ݿ��е�����
     *
     * @param formula
     *            ԭʼ��ʽ
     * @return �滻֮��Ĺ�ʽ
     */
    private String replaceSpecialString(String formula) {
        String[][] replaceString =
                {
                    {
                        "\n", ""
                    },
                    {
                        "\t", ""
                    },
                    {
                        CMDriverLangConst.getON_PRODUCT_RATE_SHOWINFO().replaceAll("\\*", "\\\\*"),
                        CMDriverLangConst.getON_PRODUCT_RATE()
                    },
                    {
                        CMDriverLangConst.getWASTEPRODUCT_RATE_SHOWINFO().replaceAll("\\*", "\\\\*"),
                        CMDriverLangConst.getWASTEPRODUCT_RATE()
                    },
                    {
                        CMDriverLangConst.getJOINT_WASTEPRODUCT_RATE_SHOWINFO().replaceAll("\\*", "\\\\*"),
                        CMDriverLangConst.getJOINT_WASTEPRODUCT_RATE()
                    }, {
                        "\\(", ""
                    }, {
                        "\\)", ""
                    }
                };

        for (int i = 0; i < replaceString.length; i++) {
            formula = formula.replaceAll(replaceString[i][0], replaceString[i][1]);
        }

        return formula;

    }

    /**
     * �������е�������ӵ�list��
     *
     * @param arrayData
     *            ����
     * @param list
     *            �б�
     */
    private void addArrayToList(String[] arrayData, List<String> list) {
        if (arrayData == null || arrayData.length <= 0) {
            return;
        }

        if (list == null) {
            list = new ArrayList<String>();
        }

        for (int i = 0; i < arrayData.length; i++) {
            if (null != arrayData[i] && arrayData[i].trim().length() > 0) {
                list.add(arrayData[i]);
            }
        }
    }

    /**
     * ��ù�ʽ��panel
     *
     * @return ��ʽ��panel
     */
    private IFormulaRealEditorPanel getFormulaRealEditorPanel() {
        return this.formulaRealEditorPanel;
    }

    /**
     * ҵ����
     *
     * @param formulas
     *            ��ʽ
     * @return true ����ҵ����� false ������ҵ�����
     */
    private boolean businessValidate(String formulas) {
        String message = null;
        message = this.checkFormulaRule(formulas);
        if (null != message && message.trim().length() > 0) {
            MessageDialog.showErrorDlg((AbstractFormulaRealEditorPanel) this.getFormulaRealEditorPanel(),
                    CMDriverLangConst.getFORMULA_VALIDATE(), message);
            return false;
        }
        return true;
    }

    private String checkFormulaRule(String formulas) {
        String message = null;
        for (int i = 0; i < formulas.length(); i++) {
            if (formulas.charAt(i) == '$') {
                if (i != 0 && !this.checkCharIsSingel(formulas.charAt(i - 1))) {
                    message = CMDriverLangConst.getFORMULA_VALIDATE_FAIL_JOINNUM();
                    break;
                }
                else if (i < formulas.length() - 1 && !this.checkCharIsSingel(formulas.charAt(i + 1))) {
                    message = CMDriverLangConst.getFORMULA_VALIDATE_FAIL_JOINNUM();
                    break;
                }
            }
            else if (formulas.charAt(i) == '(') {
                if (i != 0 && !this.checkCharIsSingelLeft(formulas.charAt(i - 1))) {
                    message = CMDriverLangConst.getFORMULA_VALIDATE_FAIL_LEFT1();
                    break;
                }
                if (i == formulas.length() - 1) {
                    message = CMDriverLangConst.getFORMULA_VALIDATE_FAIL_LEFT2();
                    break;
                }
                if (this.checkCharIsSingelRight(formulas.charAt(i + 1))) {
                    message = CMDriverLangConst.getFORMULA_VALIDATE_FAIL_LEFT3();
                    break;
                }
            }
            else if (formulas.charAt(i) == ')') {
                if (i == 0) {
                    message = CMDriverLangConst.getFORMULA_VALIDATE_FAIL_RIGHT1();
                    break;
                }
                if (this.checkCharIsSingelLeft(formulas.charAt(i - 1))) {
                    message = CMDriverLangConst.getFORMULA_VALIDATE_FAIL_RIGHT2();
                    break;
                }
                if (i < formulas.length() - 1 && !this.checkCharIsSingelRight(formulas.charAt(i + 1))) {
                    message = CMDriverLangConst.getFORMULA_VALIDATE_FAIL_RIGHT3();
                    break;
                }
            }
        }
        return message;
    }

    private boolean checkCharIsSingel(char aChar) {
        if (aChar == '+' || aChar == '-' || aChar == '*' || aChar == '/' || aChar == '(' || aChar == ')') {
            return true;
        }
        return false;
    }

    private boolean checkCharIsSingelLeft(char aChar) {
        if (aChar == '+' || aChar == '-' || aChar == '*' || aChar == '/' || aChar == '(') {
            return true;
        }
        return false;
    }

    private boolean checkCharIsSingelRight(char aChar) {
        if (aChar == '+' || aChar == '-' || aChar == '*' || aChar == '/' || aChar == ')') {
            return true;
        }
        return false;
    }

    /**
     * �ж�Ϊ��ֵ�ı���ֱ�������ų�����Ҫ�����ҵ���жϣ�
     *
     * @param formulas �õ��ı�����ֵ
     * @return
     */
    private String IsNumOrFloat(List<String> formulas) {
        for (int i = 0; i < formulas.size(); i++) {
            if (this.isNum(formulas.get(i))) {
                formulas.remove(formulas.get(i));
                i = i - 1;
                continue;
            }
            if (this.isFloat(formulas.get(i))) {
                // �ж�000.9
                String dot = ".";
                if (formulas.get(i).indexOf(dot) >= 0) {
                    String formus = formulas.get(i).substring(0, formulas.get(i).indexOf(dot));
                    if (formus.indexOf("0") != formus.lastIndexOf("0") && Integer.parseInt(formus) == 0) {
                        return CMDriverLangConst.getFORMULA_VALIDATE_FAIL_NUM();
                    }
                }
                formulas.remove(formulas.get(i));
                i = i - 1;
            }
        }
        if (formulas.size() == 0) {

            return CMDriverLangConst.getFORMULA_VALIDATE_VARABAL_NULL();
        }
        return null;
    }

    /**
     * �жϱ����Ƿ�������
     *
     * @param baseFormula
     * @return
     */
    private boolean isNum(String baseFormula) {
        Pattern pattern = Pattern.compile("^-?\\d+$");
        Matcher matcher = pattern.matcher(baseFormula);
        if (matcher.find()) {
            return true;
        }
        return false;

    }

    /**
     * �жϱ����Ƿ��Ǹ�������
     *
     * @param baseFormula
     * @return
     */
    private boolean isFloat(String baseFormula) {
        Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?");
        Matcher matcher = pattern.matcher(baseFormula);
        if (matcher.find()) {
            return true;
        }

        return false;

    }
}
