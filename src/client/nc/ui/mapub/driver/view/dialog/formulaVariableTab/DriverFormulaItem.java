package nc.ui.mapub.driver.view.dialog.formulaVariableTab;

import nc.vo.pub.formulaedit.FormulaItem;

@SuppressWarnings("serial")
public class DriverFormulaItem extends FormulaItem {
    private String[] multiLang;

    public String[] getMultiLang() {
        return this.multiLang;
    }

    public DriverFormulaItem(String displayName, String inputSig, String hintMsg, String[] multiLang) {
        super(displayName, inputSig, hintMsg);
        this.multiLang = multiLang;
    }
}
