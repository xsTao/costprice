/**
 *
 */
package nc.ui.mapub.costpricebase.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.costpricebase.ICostPriceBaseQueryService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * @since v6.3
 * @version 2017��7��19�� ����3:52:28
 * @author Administrator
 */
public class CostPriceBaseQueryProxy implements IQueryService {

    private ICostPriceBaseQueryService queryService;

    /*
     * (non-Javadoc)
     * @see
     * nc.ui.pubapp.uif2app.query2.model.IQueryService#queryByQueryScheme(nc.ui.querytemplate.querytree.IQueryScheme)
     */
    @Override
    public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
        // TODO Auto-generated method stub
        Object[] bills = this.getQueryService().queryByQueryScheme(queryScheme);
        return bills;
    }

    /**
     * ��ò�ѯ����
     *
     * @return the queryService
     * @since 2017��7��19��
     * @author
     */
    public ICostPriceBaseQueryService getQueryService() {
        if (this.queryService == null) {
            this.queryService = NCLocator.getInstance().lookup(ICostPriceBaseQueryService.class);
        }
        return this.queryService;
    }

}
