package nc.itf.mapub.coprodcoef;

import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;
import nc.vo.pub.BusinessException;

/**
 * 标准单据业务操作接口,增删改查
 * 
 * @since 6.0
 * @version 2014-10-11 下午12:27:01
 * @author zhangshyb
 */
public interface ICoprodcoefMaintain {
    /**
     * 删除单据
     * 
     * @param vo
     * @throws Exception
     */
    void delete(CoprodcoefAggVO[] vo) throws BusinessException;

    /**
     * 新增单据
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    CoprodcoefAggVO[] insert(CoprodcoefAggVO[] vo) throws BusinessException;

    /**
     * 更新单据
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    CoprodcoefAggVO[] update(CoprodcoefAggVO[] fullBills) throws BusinessException;
}
