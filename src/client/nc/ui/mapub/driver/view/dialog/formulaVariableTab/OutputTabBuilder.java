package nc.ui.mapub.driver.view.dialog.formulaVariableTab;

import java.util.LinkedHashMap;
import java.util.Map;

import nc.ui.mapub.driver.view.dialog.AbstractDriverTabBuilder;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.CMDriverParameterEnum;
import nc.vo.pub.formulaedit.FormulaItem;

/**
 * <b> 产出量的页签 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-3-30
 * 
 * @author:wangtf
 */
public class OutputTabBuilder extends AbstractDriverTabBuilder {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 8539278425991274209L;

    @Override
    public Map<String, FormulaItem> getAllVariableItems() {
        Map<String, FormulaItem> tableItems = new LinkedHashMap<String, FormulaItem>();
        String[][] svalue =
                {
                    {
                        CMDriverLangConst.getQUALIFIED_NUMBER(), CMDriverParameterEnum.QUALIFIED_NUMBER.getCode(),
                        CMDriverLangConst.getQUALIFIED_NUMBER()
                    },
                    {
                        CMDriverLangConst.getON_PRODUCT_RATE(), CMDriverParameterEnum.ON_PRODUCT_RATE.getCode(),
                        CMDriverLangConst.getON_PRODUCT_RATE_SHOWINFO()
                    },
                    {
                        CMDriverLangConst.getINPRO_APPRONUM(), CMDriverParameterEnum.INPRO_APPRONUM.getCode(),
                        CMDriverLangConst.getINPRO_APPRONUM()
                    },
                    {
                        CMDriverLangConst.getJOINT_QUALIFIED_NUMBER(),
                        CMDriverParameterEnum.JOINT_QUALIFIED_NUMBER.getCode(),
                        CMDriverLangConst.getJOINT_QUALIFIED_NUMBER()
                    },
                    {
                        CMDriverLangConst.getBY_PRODUCT_NUMBER(), CMDriverParameterEnum.BY_PRODUCT_NUMBER.getCode(),
                        CMDriverLangConst.getBY_PRODUCT_NUMBER()
                    },
                    {
                        CMDriverLangConst.getWASTEPRODUCT_NUMBER(),
                        CMDriverParameterEnum.WASTEPRODUCT_NUMBER.getCode(), CMDriverLangConst.getWASTEPRODUCT_NUMBER()
                    },
                    {
                        CMDriverLangConst.getWASTEPRODUCT_RATE(), CMDriverParameterEnum.WASTEPRODUCT_RATE.getCode(),
                        CMDriverLangConst.getWASTEPRODUCT_RATE_SHOWINFO()
                    },
                    {
                        CMDriverLangConst.getJOINT_WASTEPRODUCT_NUMBER(),
                        CMDriverParameterEnum.JOINT_WASTEPRODUCT_NUMBER.getCode(),
                        CMDriverLangConst.getJOINT_WASTEPRODUCT_NUMBER()
                    },
                    {
                        CMDriverLangConst.getJOINT_WASTEPRODUCT_RATE(),
                        CMDriverParameterEnum.JOINT_WASTEPRODUCT_RATE.getCode(),
                        CMDriverLangConst.getJOINT_WASTEPRODUCT_RATE_SHOWINFO()
                    },
                // {
                // CMDriverLangConst.getBOM_JOINTBY_OUTPUT_QUOTA(),
                // CMDriverParameterEnum.BOM_JOINTBY_OUTPUT_QUOTA.getCode(),
                // CMDriverLangConst.getBOM_JOINTBY_OUTPUT_QUOTA()
                // }
                };

        for (int i = 0; i < svalue.length; i++) {
            tableItems.put(svalue[i][0], new FormulaItem(svalue[i][2], svalue[i][1], svalue[i][2]));
        }
        return tableItems;
    }

    @Override
    public String getTabName() {
        return CMDriverLangConst.getOUTPUT();
    }

}
