/**
 * 
 */
package nc.ui.mapub.allocfac.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.allocfac.IAllocfacQueryService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.pubapp.uif2app.view.BaseOrgPanel;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

/**
 * ����ϵ����ѯ����
 */

public class AllocfacQryService implements IQueryService {

    private IAllocfacQueryService queryService;

    private BaseOrgPanel orgpanel;

    public BaseOrgPanel getOrgpanel() {
        return this.orgpanel;
    }

    public void setOrgpanel(BaseOrgPanel orgpanel) {
        this.orgpanel = orgpanel;
    }

    @Override
    public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
        // ��ò�ѯ������ֵ
        String qryPk_org = this.getOrgByCondition(queryScheme);
        // �ѻ�õĹ���ֵ������֯����еĹ���
        this.getOrgpanel().setPkOrg(qryPk_org);
        return this.getQueryService().queryByQueryScheme(queryScheme);
    }

    public IAllocfacQueryService getQueryService() {
        if (this.queryService == null) {
            return NCLocator.getInstance().lookup(IAllocfacQueryService.class);
        }
        return this.queryService;
    }

    /**
     * ������ѯ����
     */
    private String getOrgByCondition(IQueryScheme queryScheme) {
        QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
        QueryCondition condition = processor.getQueryCondition(AllocfacHeadVO.PK_ORG);
        String pk_org = condition.getValues()[0];
        return pk_org;
    }
}
