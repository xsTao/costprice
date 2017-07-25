package nc.ui.mapub.coprodcoef.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.coprodcoef.ICoprodcoefQuery;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * ʾ�����ݵĲ�ѯ����
 * 
 * @since 6.0
 * @version 2014-10-11 ����3:07:37
 * @author zhangshyb
 */
public class CoprodcoefQueryProxy implements IQueryService {

    @Override
    public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
        ICoprodcoefQuery query = NCLocator.getInstance().lookup(ICoprodcoefQuery.class);
        return query.queryByQueryScheme(queryScheme);

    }

}
