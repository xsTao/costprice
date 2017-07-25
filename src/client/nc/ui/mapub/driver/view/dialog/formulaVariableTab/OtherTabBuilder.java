package nc.ui.mapub.driver.view.dialog.formulaVariableTab;

import java.util.LinkedHashMap;
import java.util.Map;

import nc.ui.mapub.driver.view.dialog.AbstractDriverTabBuilder;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.CMDriverParameterEnum;
import nc.vo.pub.formulaedit.FormulaItem;

/**
 * <b> 其他 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-3-30
 * 
 * @author:wangtf
 */

public class OtherTabBuilder extends AbstractDriverTabBuilder {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = -3527744178674169929L;

    @Override
    public Map<String, FormulaItem> getAllVariableItems() {
        Map<String, FormulaItem> tableItems = new LinkedHashMap<String, FormulaItem>();
        String[][] svalue =
                {
                    {
                        CMDriverLangConst.getUNITVOLUME(),
                        CMDriverLangConst.OTHER + CMDriverLangConst.FORMULA_SPLIT_START
                                + CMDriverParameterEnum.UNITVOLUME.getCode() + CMDriverLangConst.FORMULA_SPLIT_END
                    },
                    {
                        CMDriverLangConst.getUNITWEIGHT(),
                        CMDriverLangConst.OTHER + CMDriverLangConst.FORMULA_SPLIT_START
                                + CMDriverParameterEnum.UNITWEIGHT.getCode() + CMDriverLangConst.FORMULA_SPLIT_END
                    },
                    {
                        CMDriverLangConst.getSTOREUNITNUMBER(),
                        CMDriverLangConst.OTHER + CMDriverLangConst.FORMULA_SPLIT_START
                                + CMDriverParameterEnum.STOREUNITNUM.getCode() + CMDriverLangConst.FORMULA_SPLIT_END
                    },
                    {
                        CMDriverLangConst.getINTOLERANCE(),
                        CMDriverLangConst.OTHER + CMDriverLangConst.FORMULA_SPLIT_START
                                + CMDriverParameterEnum.INTOLERANCE.getCode() + CMDriverLangConst.FORMULA_SPLIT_END
                    },
                    {
                        CMDriverLangConst.getOUTTOLERANCE(),
                        CMDriverLangConst.OTHER + CMDriverLangConst.FORMULA_SPLIT_START
                                + CMDriverParameterEnum.OUTTOLERANCE.getCode() + CMDriverLangConst.FORMULA_SPLIT_END
                    },
                    {
                        CMDriverLangConst.getOUTCLOSELOWERLIMIT(),
                        CMDriverLangConst.OTHER + CMDriverLangConst.FORMULA_SPLIT_START
                                + CMDriverParameterEnum.OUTCLOSELOWERLIMIT.getCode()
                    }
                };
        for (int i = 0; i < svalue.length; i++) {
            tableItems.put(svalue[i][0], new FormulaItem(svalue[i][0], svalue[i][1], svalue[i][0]));
        }
        return tableItems;
    }

    @Override
    public String getTabName() {
        return CMDriverLangConst.getOTHER();
    }

}
