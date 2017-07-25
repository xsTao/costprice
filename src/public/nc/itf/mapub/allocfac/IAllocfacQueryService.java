package nc.itf.mapub.allocfac;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.pub.BusinessException;

/**
 * 分配系数查询
 */
public interface IAllocfacQueryService {
    /**
     * 查询分配系数
     *
     * @param headVO
     *            表头数据
     * @param orderPath
     *            排序
     * @return 分配系数的聚合vo
     * @throws BusinessException
     *             异常
     */
    Object[] queryAllocByHeadVO(AllocfacHeadVO headVO, String[] orderPath) throws BusinessException;

    /**
     * 查询方案查询分配系数
     *
     * @param queryScheme
     *            查询方案
     * @return
     * @throws BusinessException
     */
    Object[] queryByQueryScheme(IQueryScheme queryScheme) throws BusinessException;

    void cleanBatchLimit();
}
