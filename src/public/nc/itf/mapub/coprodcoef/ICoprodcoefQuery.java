package nc.itf.mapub.coprodcoef;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;
import nc.vo.pub.BusinessException;

/**
 * ��׼����VO��ѯ�ӿ�
 * 
 * @since 6.0
 * @version 2014-10-11 ����3:08:40
 * @author zhangshyb
 */
public interface ICoprodcoefQuery {

    CoprodcoefAggVO[] queryByQueryScheme(IQueryScheme queryScheme) throws BusinessException;
}
