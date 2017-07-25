/**
 *
 */
package nc.ui.mapub.acquirepricelog.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.acquirepricelog.IAcquirepricelogQueryService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * @since v6.3
 * @version 2015��4��17�� ����3:28:55
 * @author lizhpf
 */
public class AcquirepricelogQueryProxy implements IQueryService {

    /**
     * ȡ�ò�ѯ������
     *
     * @return IReqPickmMaintainService
     */
    public IAcquirepricelogQueryService getQueryService() {
        return NCLocator.getInstance().lookup(IAcquirepricelogQueryService.class);
    }

    /*
     * (non-Javadoc)
     * @see
     * nc.ui.pubapp.uif2app.query2.model.IQueryService#queryByQueryScheme(nc.ui.querytemplate.querytree.IQueryScheme)
     */
    @Override
    public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
        // TODO Auto-generated method stub
        return this.getQueryService().queryByQueryScheme(queryScheme);
    }

}
