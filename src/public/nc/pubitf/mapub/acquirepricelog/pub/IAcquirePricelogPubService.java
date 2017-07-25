/**
 *
 */
package nc.pubitf.mapub.acquirepricelog.pub;

import nc.vo.mapub.acquirepricelog.entity.AcquirePriceLogVO;
import nc.vo.mapub.framework.entity.acquireprice.AcquirePriceParam;
import nc.vo.pub.BusinessException;

/**
 * @since v6.3
 * @version 2014-12-19 ����1:41:01
 * @author shuzhan
 */
public interface IAcquirePricelogPubService {

    /**
     * ɾ����־
     *
     * @param priceParam �ǿղ���:acquireType(ȡ������),pk_group,pk_org
     * @throws BusinessException
     */
    public void deletePriceLog(AcquirePriceParam priceParam) throws BusinessException;

    /**
     * ɾ����־
     * 
     * @throws BusinessException
     */
    public void deletePriceLog() throws BusinessException;

    /**
     * ����������ѯ��־
     *
     * @param priceParam �ǿղ���:acquireType(ȡ������),pk_group,pk_org
     * @return
     * @throws BusinessException
     */
    public AcquirePriceLogVO[] queryPriceLogVOSByCondition(AcquirePriceParam priceParam) throws BusinessException;
}
