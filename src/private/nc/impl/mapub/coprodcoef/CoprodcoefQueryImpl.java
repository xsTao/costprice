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
     * ������ʵ�֣���ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
     * 
     * @param queryScheme
     */
    protected void preQuery(IQueryScheme queryScheme) {
        // ��ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
    }

}
