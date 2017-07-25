package nc.impl.mapub.materialpricebase;

import nc.itf.mapub.materialpricebase.IMaterialPriceBasePrepareParam;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;

/**
 * <b> 在价格库中查询[材料]子表的价格时的数据准备 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-9-11
 * 
 * @author:jilu
 */
public class MaterialPricePrepareParamOfStuff implements IMaterialPriceBasePrepareParam {

    @Override
    public String[] getFieldsForSQL() {
        String[] fields =
                {
                    MaterialPriceBaseBodyVO.PK_ORG, MaterialPriceBaseBodyVO.CMATERIALPRICEID,
                    MaterialPriceBaseBodyVO.CMATERIALID, MaterialPriceBaseBodyVO.NPRICE
                };

        return fields;
    }

    @Override
    public String getTableName() {
        return "bd_materialpricebase_b";
    }

}
