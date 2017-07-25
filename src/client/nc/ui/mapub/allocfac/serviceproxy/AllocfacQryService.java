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
 * 分配系数查询代理
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
        // 获得查询工厂的值
        String qryPk_org = this.getOrgByCondition(queryScheme);
        // 把获得的工厂值赋给组织面板中的工厂
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
     * 解析查询条件
     */
    private String getOrgByCondition(IQueryScheme queryScheme) {
        QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
        QueryCondition condition = processor.getQueryCondition(AllocfacHeadVO.PK_ORG);
        String pk_org = condition.getValues()[0];
        return pk_org;
    }
}
