package nc.ui.mapub.costtype.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.costtype.ICostTypeService;
import nc.ui.pubapp.pub.smart.SmartBatchAppModelService;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.uif2.LoginContext;

public class CostTypeSmartBatchAppModelService extends SmartBatchAppModelService {

    private Class<ICostTypeService> costtypeQueryBP;

    private String voClass;

    @Override
    public String getVoClass() {
        return this.voClass;
    }

    @Override
    public void setVoClass(String voClass) {
        this.voClass = voClass;
    }

    @Override
    public BatchOperateVO batchSave(BatchOperateVO batchVO) throws Exception {
        ICostTypeService service = NCLocator.getInstance().lookup(this.costtypeQueryBP);
        return service.batchSaveCostTypeVos(batchVO);
    }

    @Override
    public Object[] queryByDataVisibilitySetting(LoginContext context) throws Exception {
        ICostTypeService service = NCLocator.getInstance().lookup(this.costtypeQueryBP);
        return service.queryCostTypeByDataVisibilitySetting(context);
    }

    @Override
    public Object[] queryByWhereSql(String whereSql) throws Exception {
        ICostTypeService service = NCLocator.getInstance().lookup(this.costtypeQueryBP);
        return service.queryCostTypeVOsByWhereSql(whereSql);
    }

    @SuppressWarnings("unchecked")
    public void setCostServiceItf(String serviceItf) {
        try {
            this.costtypeQueryBP = (Class<ICostTypeService>) Class.forName(serviceItf);
        }
        catch (Exception e) {
            ExceptionUtils.wrappException(e);
        }
    }
}
