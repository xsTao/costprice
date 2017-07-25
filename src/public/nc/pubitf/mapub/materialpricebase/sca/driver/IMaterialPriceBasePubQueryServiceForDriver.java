package nc.pubitf.mapub.materialpricebase.sca.driver;

import java.util.List;

import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

/**
 * <b> 价格库查询接口</b>
 * <p>
 * 价格库查询接口，提供材料价格、作业价格、费用费率的查询接口
 * </p>
 * 创建日期:2012-9-14
 *
 * @author:xionghuic
 */
public interface IMaterialPriceBasePubQueryServiceForDriver {

    /**
     * 查询组织下的价格库
     *
     * @param pkOrg 组织
     * @param fields 需查询的字段
     * @return 价格库表头VO
     * @throws BusinessException 业务异常
     * @author xionghuic(for 成本动因)
     */
    List<MaterialPriceBaseHeadVO> queryMaterialPriceBaseHeadVOByOrg(String pkOrg, UFDate date) throws BusinessException;
}
