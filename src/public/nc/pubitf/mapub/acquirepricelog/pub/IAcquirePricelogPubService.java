/**
 *
 */
package nc.pubitf.mapub.acquirepricelog.pub;

import nc.vo.mapub.acquirepricelog.entity.AcquirePriceLogVO;
import nc.vo.mapub.framework.entity.acquireprice.AcquirePriceParam;
import nc.vo.pub.BusinessException;

/**
 * @since v6.3
 * @version 2014-12-19 下午1:41:01
 * @author shuzhan
 */
public interface IAcquirePricelogPubService {

    /**
     * 删除日志
     *
     * @param priceParam 非空参数:acquireType(取价类型),pk_group,pk_org
     * @throws BusinessException
     */
    public void deletePriceLog(AcquirePriceParam priceParam) throws BusinessException;

    /**
     * 删除日志
     * 
     * @throws BusinessException
     */
    public void deletePriceLog() throws BusinessException;

    /**
     * 根据条件查询日志
     *
     * @param priceParam 非空参数:acquireType(取价类型),pk_group,pk_org
     * @return
     * @throws BusinessException
     */
    public AcquirePriceLogVO[] queryPriceLogVOSByCondition(AcquirePriceParam priceParam) throws BusinessException;
}
