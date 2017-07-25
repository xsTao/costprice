package nc.pubitf.mapub.materialpricebase.sca.allocate;

import java.util.Map;

import nc.vo.cmpub.framework.assistant.CMMarAssInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * 物料价格库公共查询接口
 *
 * @since 6.36
 * @version 2014-11-28 上午9:28:36
 * @author zhangchd
 */
public interface IMaterialPriceBasePubQueryServiceForAllocate {

    /**
     * 提供的接口：根据价格库查询[材料]子表的价格
     *
     * @param priceBaseIDs 价格库主键
     * @param materialoid 材料oid
     * @return Map<物料，Map<价格库，价格>>
     * @throws BusinessException
     */
    Map<CMMarAssInfoVO, Map<String, UFDouble>> getStuffPrice(String[] priceBaseIDs, CMMarAssInfoVO[] materialoid)
            throws BusinessException;

}
