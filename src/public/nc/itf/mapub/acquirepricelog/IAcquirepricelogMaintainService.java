/**
 *
 */
package nc.itf.mapub.acquirepricelog;

import nc.vo.mapub.acquirepricelog.entity.AcquirePriceLogVO;
import nc.vo.pub.BusinessException;

/**
 * @since v6.3
 * @version 2015年4月21日 下午2:51:19
 * @author lizhpf
 */
public interface IAcquirepricelogMaintainService {
    /**
     * 删除单据
     *
     * @param vos
     * @throws Exception
     */
    void deleteAcquirepricelog(AcquirePriceLogVO[] vos) throws BusinessException;

}
