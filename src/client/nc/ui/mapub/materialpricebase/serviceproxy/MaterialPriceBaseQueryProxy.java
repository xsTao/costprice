package nc.ui.mapub.materialpricebase.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.materialpricebase.IMaterialPriceBaseQueryService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 材料价格库
 * 
 * @since 6.36
 * @version 2014-11-7 下午4:29:05
 * @author zhangchd
 */
public class MaterialPriceBaseQueryProxy implements IQueryService {
    private IMaterialPriceBaseQueryService queryService;

    /**
     * 获取上期在产成本调整单查询服务
     * 
     * @return IMaterialPriceBaseQueryService
     */
    private IMaterialPriceBaseQueryService getQueryService() {
        if (this.queryService == null) {
            this.queryService = NCLocator.getInstance().lookup(IMaterialPriceBaseQueryService.class);
        }
        return this.queryService;
    }

    @Override
    public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
        Object[] bills = this.getQueryService().queryByQueryScheme(queryScheme);
        return bills;
    }
}
