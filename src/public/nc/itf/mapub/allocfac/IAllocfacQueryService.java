package nc.itf.mapub.allocfac;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.pub.BusinessException;

/**
 * ����ϵ����ѯ
 */
public interface IAllocfacQueryService {
    /**
     * ��ѯ����ϵ��
     *
     * @param headVO
     *            ��ͷ����
     * @param orderPath
     *            ����
     * @return ����ϵ���ľۺ�vo
     * @throws BusinessException
     *             �쳣
     */
    Object[] queryAllocByHeadVO(AllocfacHeadVO headVO, String[] orderPath) throws BusinessException;

    /**
     * ��ѯ������ѯ����ϵ��
     *
     * @param queryScheme
     *            ��ѯ����
     * @return
     * @throws BusinessException
     */
    Object[] queryByQueryScheme(IQueryScheme queryScheme) throws BusinessException;

    void cleanBatchLimit();
}
