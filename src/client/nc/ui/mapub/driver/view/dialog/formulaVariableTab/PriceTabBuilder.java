package nc.ui.mapub.driver.view.dialog.formulaVariableTab;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nc.ui.mapub.driver.view.dialog.AbstractDriverTabBuilder;
import nc.ui.mapub.driver.view.dialog.DriverFormulaEventSource;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.formula.dialog.FormulaEventSource.FormulaEventType;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.CMDriverParameterEnum;
import nc.vo.pub.formulaedit.FormulaItem;

/**
 * <b> 产品价值 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-3-30
 *
 * @author:wangtf
 */
public class PriceTabBuilder extends AbstractDriverTabBuilder {
    /**
     * 对话框高
     */
    private static final int DLG_HEIGHT = 480;

    /**
     * 对话框宽
     */
    private static final int DLG_WIDTH = 640;

    /**
     * 销售组织对话框
     */
    UIDialog saleOrgDialog = null;

    /**
     * 销售组织panel
     */
    SaleOrgPanel saleOrgPanel = null;

    /**
     * 标准成本对话框
     */
    UIDialog standardCostDialog = null;

    /**
     * 标准成本panel
     */
    StandardCostPanel standardCostPanel;

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 2706586234948373561L;

    @Override
    public Map<String, FormulaItem> getAllVariableItems() {
        Map<String, FormulaItem> tableItems = new LinkedHashMap<String, FormulaItem>();
        String[][] svalue = {
            {
                CMDriverLangConst.getPLAN_PRICE(), CMDriverParameterEnum.PLAN_PRICE.getCode()
            }, {
                CMDriverLangConst.getREFERENCE_COST(), CMDriverParameterEnum.REFERENCE_COST.getCode()
            }, {
                CMDriverLangConst.getREFERENCE_SALE_PRICE(), CMDriverParameterEnum.REFERENCE_SALE_PRICE.getCode(),
            }, {
                CMDriverLangConst.getSTANDARD_COST(), CMDriverParameterEnum.STANDARD_COST.getCode()
            }, {
                CMDriverLangConst.getPRICE_LIBRARY(), CMDriverParameterEnum.PRICE_LIBRARY.getCode()
            },
        };
        for (int i = 0; i < svalue.length; i++) {
            tableItems.put(svalue[i][0], new FormulaItem(svalue[i][0], svalue[i][1], svalue[i][0]));
        }
        return tableItems;
    }

    @Override
    public String getTabName() {
        return CMDriverLangConst.getPRICE();
    }

    @Override
    protected void mouseDoubleClicked(MouseEvent e, FormulaItem formulaItem, DriverFormulaEventSource eventSource) {
        // super.mouseDoubleClicked(e, formulaItem, eventSource);
        // 弹出选择销售组织的对话框
        String displayName = formulaItem.getDisplayName();
        if (CMDriverLangConst.getREFERENCE_SALE_PRICE().equals(displayName)) {

            if (this.getSaleOrgDialog().showModal() == UIDialog.ID_OK) {
                // 写公式编辑器内容
                eventSource.setEventType(FormulaEventType.INSERT_TO_EDITOR);
                String saleOrgText = this.getSaleOrgText(CMDriverLangConst.getREFERENCE_SALE_PRICE());
                String saleOrgTextCode = this.getSaleOrgCode(CMDriverParameterEnum.REFERENCE_SALE_PRICE.getCode());
                eventSource.setNewString(saleOrgText);
                eventSource.setNewValueString(saleOrgTextCode);
                // eventSource.setMultiLangs(this.getSaleOrgTexts(((DriverFormulaItem) formulaItem).getMultiLang()));
            }
        }
        else if (CMDriverLangConst.getPRICE_LIBRARY().equals(displayName)) {
            if (this.getStandardCostDialog().showModal() == UIDialog.ID_OK) {
                // 写公式编辑器内容
                eventSource.setEventType(FormulaEventType.INSERT_TO_EDITOR);
                String standardCostText = this.getStandardCostText(CMDriverLangConst.getPRICE_LIBRARY());
                String standardCostTextCode = this.getStandardCostCode(CMDriverParameterEnum.PRICE_LIBRARY.getCode());
                eventSource.setNewString(standardCostText);
                eventSource.setNewValueString(standardCostTextCode);
                // eventSource.setMultiLangs(this.getStandardCostTexts(((DriverFormulaItem)
                // formulaItem).getMultiLang()));
            }
        }
        else {
            super.mouseDoubleClicked(e, formulaItem, eventSource);
        }
    }

    /**
     * 构造销售组织的显示文本
     *
     * @param strprifx 文本前缀
     * @return 显示的文本
     */
    private String getSaleOrgText(String strprifx) {
        List<ListItem> listItems = this.getSaleOrgPanel().getSelectListItmes();
        if (listItems == null || listItems.isEmpty()) {
            return "";
        }
        StringBuffer showText = new StringBuffer();
        showText.append(strprifx + CMDriverLangConst.FORMULA_SPLIT_START);
        showText.append(listItems.get(0).getShowCode() + "~" + listItems.get(0).getShowText());
        for (int i = 1; i < listItems.size(); i++) {
            showText.append("," + listItems.get(i).getShowCode() + "~" + listItems.get(i).getShowText());
        }
        showText.append(CMDriverLangConst.FORMULA_SPLIT_END);
        return showText.toString();
    }

    // private String[] getSaleOrgTexts(String[] multiLangs) {
    // List<ListItem> listItems = this.getSaleOrgPanel().getSelectListItmes();
    // if (listItems == null || listItems.isEmpty()) {
    // return null;
    // }
    // for (int i = 0; i < multiLangs.length; i++) {
    // StringBuffer showText = new StringBuffer();
    // showText.append(multiLangs[i] + CMDriverLangConst.FORMULA_SPLIT_START);
    // for (ListItem listItem : listItems) {
    // showText.append(listItem.getShowCode() + "~" + this.getListName(listItem, i) + ",");
    // }
    // showText.substring(0, showText.length() - 1);
    // showText.append(CMDriverLangConst.FORMULA_SPLIT_END);
    // multiLangs[i] = showText.toString();
    // }
    // return multiLangs;
    // }

    // private String getListName(ListItem listItem, int seq) {
    // String name = listItem.getShowTexts()[seq];
    // if (null == name || name.trim().length() <= 0) {
    // name = listItem.getShowText();
    // }
    // return name;
    // }

    private String getSaleOrgCode(String strprifx) {
        List<ListItem> listItems = this.getSaleOrgPanel().getSelectListItmes();
        if (listItems == null || listItems.isEmpty()) {
            return "";
        }
        StringBuffer showText = new StringBuffer();
        showText.append(strprifx + CMDriverLangConst.FORMULA_SPLIT_START);
        showText.append(listItems.get(0).getKeyValue());
        for (int i = 1; i < listItems.size(); i++) {
            showText.append("," + listItems.get(0).getKeyValue());
        }
        showText.append(CMDriverLangConst.FORMULA_SPLIT_END);
        return showText.toString();
    }

    /**
     * 构造标准成本显示的字符串
     *
     * @param strprifx 文本前缀
     * @return 显示的字符串
     */
    private String getStandardCostText(String strprifx) {
        List<ListItem> listItems = this.getStandardCostPanel().getSelectListItmes();
        if (listItems == null || listItems.isEmpty()) {
            return "";
        }
        StringBuffer showText = new StringBuffer();
        showText.append(strprifx);
        showText.append(CMDriverLangConst.FORMULA_SPLIT_START);
        for (int i = 0; i < listItems.size(); i++) {
            if (i == 0) {
                showText.append(listItems.get(i).getShowCode() + "~" + listItems.get(i).getShowText());
            }
            else {
                showText.append("," + listItems.get(i).getShowCode() + "~" + listItems.get(i).getShowText());
            }
        }
        showText.append(CMDriverLangConst.FORMULA_SPLIT_END);
        return showText.toString();
    }

    // private String[] getStandardCostTexts(String[] multiLangs) {
    // List<ListItem> listItems = this.getStandardCostPanel().getSelectListItmes();
    // if (listItems == null || listItems.isEmpty()) {
    // return null;
    // }
    // for (int i = 0; i < multiLangs.length; i++) {
    // StringBuffer showText = new StringBuffer();
    // showText.append(multiLangs[i] + CMDriverLangConst.FORMULA_SPLIT_START);
    // for (ListItem listItem : listItems) {
    // showText.append(listItem.getShowCode() + "~" + this.getListName(listItem, i) + ",");
    // }
    // showText.substring(0, showText.length() - 1);
    // showText.append(CMDriverLangConst.FORMULA_SPLIT_END);
    // multiLangs[i] = showText.toString();
    // }
    // return multiLangs;
    // }

    private String getStandardCostCode(String strprifx) {
        List<ListItem> listItems = this.getStandardCostPanel().getSelectListItmes();
        if (listItems == null || listItems.isEmpty()) {
            return "";
        }
        // ListItem item = (ListItem) this.getStandardCostPanel().getData().get(StandardCostPanel.SELECTITEM);
        StringBuffer showText = new StringBuffer();
        showText.append(strprifx);
        showText.append(CMDriverLangConst.FORMULA_SPLIT_START);
        for (int i = 0; i < listItems.size(); i++) {
            if (i == 0) {
                showText.append(listItems.get(i).getKeyValue());
            }
            else {
                showText.append("," + listItems.get(i).getKeyValue());
            }
        }
        showText.append(CMDriverLangConst.FORMULA_SPLIT_END);
        return showText.toString();
    }

    /**
     * 获得销售组织对话框
     *
     * @return 销售组织对话框
     */
    private UIDialog getSaleOrgDialog() {
        if (this.saleOrgDialog == null) {
            this.saleOrgDialog = new UIDialog(this, CMDriverLangConst.getSALEORG());
            this.saleOrgDialog.getContentPane().setLayout(new BorderLayout());

            this.saleOrgDialog.setSize(PriceTabBuilder.DLG_WIDTH, PriceTabBuilder.DLG_HEIGHT);
            this.saleOrgDialog.add(this.getSaleOrgPanel(), BorderLayout.CENTER);
        }
        return this.saleOrgDialog;
    }

    /**
     * 获得标准成本对话框
     *
     * @return 标准成本对话框
     */
    private UIDialog getStandardCostDialog() {
        if (this.standardCostDialog == null) {
            this.standardCostDialog = new UIDialog(this, CMDriverLangConst.getSTANDARDCOST());
            this.standardCostDialog.getContentPane().setLayout(new BorderLayout());

            this.standardCostDialog.setSize(PriceTabBuilder.DLG_WIDTH, PriceTabBuilder.DLG_HEIGHT);
            this.standardCostDialog.add(this.getStandardCostPanel(), BorderLayout.CENTER);
        }

        return this.standardCostDialog;
    }

    /**
     * 获取销售组织panel
     *
     * @return 销售组织panel
     */
    private SaleOrgPanel getSaleOrgPanel() {
        if (this.saleOrgPanel == null) {
            this.saleOrgPanel = new SaleOrgPanel(this.getLoginContext(), this.saleOrgDialog);
        }
        return this.saleOrgPanel;
    }

    /**
     * 获取标准成本panel
     *
     * @return 标准成本panel
     */
    private StandardCostPanel getStandardCostPanel() {
        if (this.standardCostPanel == null) {
            this.standardCostPanel = new StandardCostPanel(this.getLoginContext(), this.standardCostDialog);
        }
        return this.standardCostPanel;
    }

}
