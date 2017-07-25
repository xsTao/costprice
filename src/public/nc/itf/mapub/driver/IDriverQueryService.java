package nc.itf.mapub.driver;

import java.util.List;

import nc.vo.bd.bdactivity.entity.BDActivityVO;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.driver.entity.DriverQueryCondition;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.org.OrgVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.resa.factor.FactorAsoaVO;

public interface IDriverQueryService {

    /**
     * 获取分配系数
     * 
     * @param queryCondition
     *            查询的参数
     * @return 分配系数
     */
    AllocfacHeadVO[] queryAllocfacHeadVO(DriverQueryCondition queryCondition);

    /**
     * 获取作业
     * 
     * @param queryCondition
     *            查询参数
     * @return 作业
     */
    BDActivityVO[] queryActivity(DriverQueryCondition queryCondition);

    /**
     * 获取核算要素
     * 
     * @param queryCondition
     *            查询参数
     * @param businessDate
     *            业务日期
     * @return 核算要素
     */
    List<FactorAsoaVO> queryFactor(DriverQueryCondition queryCondition, UFDate businessDate);

    // /**
    // * 获取价格库数据
    // *
    // * @param queryCondition
    // * 查询条件
    // * @return 价格库数据
    // */
    // List<PriceBaseHeadVO> queryPriceLibrary(DriverQueryCondition queryCondition);

    /**
     * 查询销售组织
     * 
     * @param queryCondition
     *            查询条件
     * @return 销售组织
     */
    List<OrgVO> querySaleOrgs(DriverQueryCondition queryCondition);

    /**
     * 通过组织过滤
     * 
     * @param pk_org
     *            组织主键
     * @return 成本动因vo数组
     */
    DriverVO[] queryDriverByOrg(String pk_org, String sortby);

}
