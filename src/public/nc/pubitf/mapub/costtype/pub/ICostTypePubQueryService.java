package nc.pubitf.mapub.costtype.pub;

import java.util.Map;

import nc.vo.pub.ISuperVO;

public interface ICostTypePubQueryService {

    /**
     * add by xuyanga on 2011-06-16
     * 得到工厂下的默认有效的成本类型；
     *
     * @param pk_org
     * @param accPeriod
     * @return
     */
    ISuperVO getDefaultCostType(String pk_org, String accPeriod);

    /**
     * add by zhangweix on 2012-04-23
     * 得到工厂下的默认有效的成本类型: 走缓存机制查询
     *
     * @param pk_org
     * @param accPeriod
     * @return
     */
    ISuperVO getDefaultCostType_C(String pk_org, String accPeriod);

    /**
     * add by xuyanga on 2011-06-16
     * 得到工厂下的有效且已计算的所有成本类型；
     *
     * @param pk_org
     * @param accPeriod
     * @return
     */
    ISuperVO[] getAllCostType(String pk_org, String accPeriod);

    /**
     * 根据PK查询成本类型VO
     *
     * @param costTypePK
     *            String
     * @return ISuperVO
     */
    ISuperVO getCostTypeVoByPK(String costTypePK);

    /**
     * 根据PK查询成本类型id和code的map
     *
     * @param costTypePK
     *            String
     * @return ISuperVO
     */
    Map<String, String> getCostTypeMapByPK(String[] costTypePKS);

    /**
     * 根据生效、失效区间获得指定工厂下的默认成本类型
     *
     * @param pk_org 工厂
     * @param cPeriod 当前期间
     * @return 成本类型VO，如未查到则为null
     */
    ISuperVO getDefaultCostTypeByPeriod(String pk_org, String cPeriod);

    /**
     * 根据生效、失效区间获得指定工厂下的默认成本类型
     *
     * @param pk_org 工厂
     * @param cPeriod 当前期间
     * @return 成本类型VO，如未查到则为null
     */
    Boolean isCostTypeLegal(String pk_org, String cPeriod, String costtype, String pk_group);
}
