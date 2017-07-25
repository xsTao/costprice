package nc.pubitf.mapub.materialpricebase.pub;

import java.util.List;
import java.util.Map;

import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.vo.cmpub.business.aquireprice.AquirePriceParamVO;
import nc.vo.mapub.framework.entity.acquireprice.AcquirePriceParam;
import nc.vo.mapub.framework.entity.acquireprice.AcquirePriceReturn;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * 价格库公共查询接口
 * 价格库查询接口，提供材料价格、作业价格、费用费率的查询接口
 * 创建日期:2012-9-14
 * 
 * @author:xionghuic
 */
public interface IMaterialPriceBasePubQueryServiceForPrice {

    /**
     * 提供的接口：根据价格库查询VO，查询返回子表的价格Map<key, 价格>，并不分查询的是什么价格
     * 注： 1、查询VO中一定要设置来源类型：ResType，否则查不出该VO的价格
     * 2、结果Map中的key可用PriceBaseQueryPriceUtil类中的getMapKey()方法获取，价格如果没取到，则价格为null
     * 
     * @param priceParamVOs
     *            查询参数VO数组
     * @return Map<String, UFDouble> Map<key, 价格>
     * @throws BusinessException
     *             异常
     */
    Map<String, UFDouble> getAllPriceMap(AquirePriceParamVO[] priceParamVOs) throws BusinessException;

    /**
     * 按价格库取价
     * 
     * @param priceParams
     * @return
     * @throws BusinessException
     */
    Map<AcquirePriceParam, AcquirePriceReturn> getPriceByMarAss(List<AcquirePriceParam> priceParams)
            throws BusinessException;

    /**
     * 查询物料价格库表头VO
     * 成本类型、成本BOM使用
     * 
     * @param pkorg 业务单元
     * @param group 集团
     * @param date 日期
     * @return VO数组
     * @throws BusinessException
     */
    List<DefaultConstEnum> queryMaterialPriceBaseHeadVO(String[] pkorg, String group, UFDate date)
            throws BusinessException;
}
