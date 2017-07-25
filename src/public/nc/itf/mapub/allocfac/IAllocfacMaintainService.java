/**
 * 
 */
package nc.itf.mapub.allocfac;

import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.pub.BusinessException;

/**
 * 分配系数增删改查操作接口
 */
public interface IAllocfacMaintainService {
    /**
     * 分配系数的插入操作
     * 
     * @param vo
     *            消耗单聚合vo
     * @return
     *         消耗单
     * @throws BusinessException
     */
    AllocfacAggVO[] insert(AllocfacAggVO[] vos) throws BusinessException;

    /**
     * 分配系数修改操作
     * 
     * @return StuffAggVO[]
     */
    AllocfacAggVO[] update(AllocfacAggVO[] vos) throws BusinessException;

    /**
     * 删除分配系数
     * 
     * @param vos
     * @throws BusinessException
     */
    void allocDelete(AllocfacAggVO[] vos) throws BusinessException;

    public String getDatabaseCFG(boolean needDetail) throws BusinessException;
}
