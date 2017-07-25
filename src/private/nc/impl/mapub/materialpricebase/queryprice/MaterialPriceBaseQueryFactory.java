package nc.impl.mapub.materialpricebase.queryprice;

import nc.impl.mapub.materialpricebase.MaterialPricePrepareParamOfStuff;
import nc.itf.mapub.materialpricebase.IMaterialPriceBasePrepareParam;
import nc.vo.cmpub.business.enumeration.PriceResTypeEnum;

/**
 * <b> 价格库查询价格的工厂类，处理3种查询情况，分别对应材料、作业、费用3张子表的查询 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-9-11
 * 
 * @author:jilu
 */
public class MaterialPriceBaseQueryFactory {

    /**
     * 根据查询的类型，返回相应的接口实现类
     * 
     * @param priceQueryType
     *            查询类型，采用工厂中的类型参数
     * @return 接口实现
     */
    public static IMaterialPriceBasePrepareParam getPricePrepareParam(PriceResTypeEnum priceQueryType) {

        if (priceQueryType.equals(PriceResTypeEnum.STUFF)) {
            // 返回查询[物料]价格专用实现类
            return new MaterialPricePrepareParamOfStuff();

        }
        // else if (priceQueryType.equals(PriceResTypeEnum.ACTV)) {
        // // 返回查询[作业]价格专用实现类
        // return new PriceBasePrepareParamOfActivity();
        //
        // }
        // else if (priceQueryType.equals(PriceResTypeEnum.FEE)) {
        // // 返回查询[费用]价格专用实现类
        // return new PriceBasePrepareParamOfFactor();
        //
        // }

        // 没有默认值，则返回空
        return null;

    }

}
