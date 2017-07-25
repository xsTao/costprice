package nc.impl.mapub.coprodcoef;

import nc.itf.mapub.coprodcoef.ICoprodcoefQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class CoprodcoefQueryImpl implements ICoprodcoefQuery {
    @Override
    public CoprodcoefAggVO[] queryByQueryScheme(IQueryScheme queryScheme) throws BusinessException {
        CoprodcoefAggVO[] bills = null;
        try {
            this.preQuery(queryScheme);
            CmBillQuery<CoprodcoefAggVO> query = new CmBillQuery<CoprodcoefAggVO>(CoprodcoefAggVO.class);
            bills = query.query(queryScheme, null);
        }
        catch (Exception e) {
            ExceptionUtils.marsh(e);
        }
        return bills;
    }

    /**
     * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
     * 
     * @param queryScheme
     */
    protected void preQuery(IQueryScheme queryScheme) {
        // 查询之前对queryScheme进行加工，加入自己的逻辑
    }

}
