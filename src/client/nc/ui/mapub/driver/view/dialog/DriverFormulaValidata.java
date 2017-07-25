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
 * <b> 成本动因公式检查 </b>
 * <p>
 * 检查成本动因是否符合规范， 包括公式语法检查和基准对象兼容性检查
 * </p>
 * 创建日期:2010-4-20
 *
 * @author:wangtf
 */
public class DriverFormulaValidata {
    /**
     * 公式panel
     */
    IFormulaRealEditorPanel formulaRealEditorPanel;

    /**
     * 构造方法
     *
     * @param formulaRealEditorPanel
     *            公式的panel
     */
    public DriverFormulaValidata(IFormulaRealEditorPanel formulaRealEditorPanel) {
        this.formulaRealEditorPanel = formulaRealEditorPanel;
    }

    /**
     * 检查数据
     *
     * @return true 检验通过 false 存在问题
     */
    public boolean validata() {

        String formulaText = this.getFormulaRealEditorPanel().getConvertedText();

        // 替换[], 为""
        formulaText = formulaText.replaceAll("\\[", "");
        formulaText = formulaText.replaceAll("\\]", "");
        // 干扰除数为零判断
        formulaText = formulaText.replaceAll("\\(%\\)", "");

        // 判断（）是否完整配对
        if (!this.checkMatch(formulaText)) {
            return false;
        }

        // 判断带括号的除数不能为零 副产品产量*2/(联产品产量-联产品产量)
        if (!this.checkDivZero(formulaText)) {
            return false;
        }

        // 替换指定的字符
        String replaedString = this.replaceSpecialString(formulaText);
        // 分隔出变量的各个部分，用于最后的变量兼容性检查
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
                // 公式中至少包含一个公式变量
                MessageDialog.showErrorDlg((AbstractFormulaRealEditorPanel) this.getFormulaRealEditorPanel(),
                        CMDriverLangConst.getFORMULA_VALIDATE(), msg);
                return false;
            }
            if (!this.businessValidate(formulaOldText)) {
                return false;
            }
        }
        else {
            // 语法错误
            MessageDialog.showErrorDlg((AbstractFormulaRealEditorPanel) this.getFormulaRealEditorPanel(),
                    CMDriverLangConst.getFORMULA_VALIDATE(), CMDriverLangConst.getFORMULA_VALIDATE_FAIL_GRAMMER());
            return false;
        }
        return true;
    }

    /**
     * 将{变量}替换为$，消除变量中符号影响判断
     *
     * @param formula 公式
     * @return 替换后公式
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
     * 判断括号是否完成配对
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
            // 括号必须完整配对
            MessageDialog.showErrorDlg((AbstractFormulaRealEditorPanel) this.getFormulaRealEditorPanel(),
                    CMDriverLangConst.getFORMULA_VALIDATE(), CMDriverLangConst.getFORMULA_VALIDATE_FAIL_MATCH());
            return false;
        }
        return true;
    }

    /**
     * 判断除数不能为零
     *
     * @param formulaText
     * @return
     */
    private boolean checkDivZero(String formulaText) {
        String[] formulas = formulaText.split("/\\(");
        // 有这种除数
        if (formulas.length > 1) {
            String div = formulas[1].substring(0, formulas[1].lastIndexOf(")"));
            String[] caculateSign = {
                "\\-", "\\*"
            };
            if (div.indexOf("-") > 0) {
                String[] divs = div.split(caculateSign[0]);
                if (divs[0].equals(divs[1])) {
                    // 除数不能为零
                    MessageDialog.showErrorDlg((AbstractFormulaRealEditorPanel) this.getFormulaRealEditorPanel(),
                            CMDriverLangConst.getFORMULA_VALIDATE(), CMDriverLangConst.getFORMULA_VALIDATE_FAIL_ZERO());
                    return false;
                }
            }
            if (div.indexOf("*") > 0) {
                String[] divs = div.split(caculateSign[1]);
                for (int i = 0; i < divs.length; i++) {
                    if (divs[i].equals("0")) {
                        // 除数不能为零
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
     * 分隔字符串
     *
     * @param formulaText
     *            分隔的字符串
     * @return 分隔号的字符串
     */
    private String[] splitFormulaText(String formulaText) {
        formulaText = formulaText.replaceAll("\n", "").replaceAll("\t", "");
        return formulaText.split(";");
    }

    /**
     * 分隔字符串根据运算符
     *
     * @param formula
     *            公式
     * @param list
     * @return 分隔好的字符串
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
                    // 除数不能为零
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
     * 替换一些影响校验的字段为指定字符 只为了校验，不影响最终保存到数据库中的数据
     *
     * @param formula
     *            原始公式
     * @return 替换之后的公式
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
     * 把数组中的数据添加到list中
     *
     * @param arrayData
     *            数组
     * @param list
     *            列表
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
     * 获得公式的panel
     *
     * @return 公式的panel
     */
    private IFormulaRealEditorPanel getFormulaRealEditorPanel() {
        return this.formulaRealEditorPanel;
    }

    /**
     * 业务检查
     *
     * @param formulas
     *            公式
     * @return true 满足业务规则 false 不满足业务规则
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
     * 判断为数值的变量直接跳过排除不需要后面的业务判断，
     *
     * @param formulas 得到的变量数值
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
                // 判断000.9
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
     * 判断变量是否是整数
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
     * 判断变量是否是浮点型数
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
