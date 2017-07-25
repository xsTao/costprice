package nc.pubitf.mapub.driver.cm.pub;

import java.util.List;

import nc.vo.pub.BusinessException;

/**
 * 成本动因公共接口
 * 
 * @since v6.3
 * @version 2013-7-9 下午02:41:12
 * @author liyjf
 */
public interface IDriverQueryForPub {

    /**
     * 数据是否被成本动因引用
     * 
     * @param pk_org
     *            工厂
     * @param driverType
     *            动因类型，参照CMDriverParameterEnum
     * @param idList
     *            数据IDList
     * @return
     * @throws BusinessException
     */
    boolean isUsedInDriver(String pk_org, String driverType, List<String> idList) throws BusinessException;
}
