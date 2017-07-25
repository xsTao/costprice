package nc.ui.mapub.allocfac.query;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;

/**
 * ����ϵ����ѯ����
 */

public class AllocfacQueryConditionDLGInitializer implements IQueryConditionDLGInitializer {

    @Override
    public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {

        // ������Ȩ�޵Ĺ���
        condDLGDelegator.registerNeedPermissionOrgFieldCode(AllocfacHeadVO.PK_ORG);

    }
}
