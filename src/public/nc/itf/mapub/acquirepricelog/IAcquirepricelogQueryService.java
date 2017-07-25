/**
 *
 */
package nc.itf.mapub.acquirepricelog;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.mapub.acquirepricelog.entity.AcquirePriceLogVO;
import nc.vo.pub.BusinessException;

/**
 * @since v6.3
 * @version 2015年4月20日 上午9:12:05
 * @author lizhpf
 */
public interface IAcquirepricelogQueryService {

    AcquirePriceLogVO[] queryByQueryScheme(IQueryScheme queryScheme) throws BusinessException;

}
