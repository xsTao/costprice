/**
 *
 */
package nc.itf.mapub.acquirepricelog;

import nc.vo.mapub.acquirepricelog.entity.AcquirePriceLogVO;
import nc.vo.pub.BusinessException;

/**
 * @since v6.3
 * @version 2015��4��21�� ����2:51:19
 * @author lizhpf
 */
public interface IAcquirepricelogMaintainService {
    /**
     * ɾ������
     *
     * @param vos
     * @throws Exception
     */
    void deleteAcquirepricelog(AcquirePriceLogVO[] vos) throws BusinessException;

}
