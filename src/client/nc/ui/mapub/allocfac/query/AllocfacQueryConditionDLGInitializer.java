package nc.ui.mapub.allocfac.query;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;

/**
 * 分配系数查询过滤
 */

public class AllocfacQueryConditionDLGInitializer implements IQueryConditionDLGInitializer {

    @Override
    public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {

        // 过滤有权限的工厂
        condDLGDelegator.registerNeedPermissionOrgFieldCode(AllocfacHeadVO.PK_ORG);

    }
}
