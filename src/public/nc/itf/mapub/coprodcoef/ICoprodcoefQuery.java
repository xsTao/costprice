package nc.itf.mapub.coprodcoef;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;
import nc.vo.pub.BusinessException;

/**
 * 标准单据VO查询接口
 * 
 * @since 6.0
 * @version 2014-10-11 下午3:08:40
 * @author zhangshyb
 */
public interface ICoprodcoefQuery {

    CoprodcoefAggVO[] queryByQueryScheme(IQueryScheme queryScheme) throws BusinessException;
}
