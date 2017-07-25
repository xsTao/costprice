package nc.ui.mapub.coprodcoef.query;

import java.util.List;

import nc.ui.bd.pub.filter.CMBasedocCommonByOrgFilter;
import nc.ui.cmpub.business.filter.CMCommonByOrgFilter;
import nc.ui.cmpub.business.filter.FilterMaterialRefUtils;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.filtereditor.FilterEditorWrapper;
import nc.ui.querytemplate.filtereditor.IFilterEditor;
import nc.ui.querytemplate.value.IFieldValueElement;
import nc.vo.mapub.coprodcoef.entity.CMMLangConstCoprodcoef;
import nc.vo.pub.lang.UFBoolean;

/**
 * @since 6.0
 * @version 2014-10-11 下午3:02:57
 * @author zhangshyb
 */
public class CoprodcoefQueryConditionInitializer implements IQueryConditionDLGInitializer {

    @Override
    public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
        // 处理冗余字段，过滤 参数1:组织，参数2 子元数据对应路径
        condDLGDelegator.addRedundancyInfo("pk_org", "itembpks.pk_org");
        // 处理冗余字段，过滤 参数1:集团，参数2 子元数据对应路径
        condDLGDelegator.addRedundancyInfo("pk_group", "itembpks.pk_group");
        // 处理冗余字段，过滤 参数1:会计期间，参数2 子元数据对应路径
        // condDLGDelegator.addRedundancyInfo(CMMLangConstBD0104.CPERIOD, "itemid.cperiod");
        // TODO 初始化查询模板逻辑
        // 过滤有权限的工厂
        condDLGDelegator.registerNeedPermissionOrgFieldCode(CMMLangConstCoprodcoef.PK_ORG);
        // 设置物料
        new CMCommonByOrgFilter(condDLGDelegator, CMMLangConstCoprodcoef.PK_ORG, new String[] {
                CMMLangConstCoprodcoef.CMATERIALID, CMMLangConstCoprodcoef.CM_PRODUCT_CMATERIALID
        }) {
            @Override
            protected void processLinkageLogic(List<IFieldValueElement> fatherValues, IFilterEditor editor) {
                FilterEditorWrapper wrapper = new FilterEditorWrapper(editor);
                super.setSingleOrg(fatherValues, wrapper);
                UIRefPane refPane = super.getUIRefPane(wrapper);
                FilterMaterialRefUtils util = new FilterMaterialRefUtils(refPane);
                util.filterRefByCostObjAndAssService(UFBoolean.TRUE, UFBoolean.FALSE);
                super.setMultiCorp(fatherValues, editor);
                super.processLinkageLogic(fatherValues, editor);
            }
        }.addEditorListener();
        // // 设置联合体参照
        // new CoprodcoefMaterialByCostObjAndAssSrv(condDLGDelegator, CMMLangConstCoprodcoef.PK_ORG,
        // CMMLangConstCoprodcoef.CMATERIALID).addEditorListener();
        // // 设置产品参照
        // new CoprodcoefMaterialByCostObjAndAssSrv(condDLGDelegator, CMMLangConstCoprodcoef.PK_ORG,
        // CMMLangConstCoprodcoef.CM_PRODUCT_CMATERIALID).addEditorListener();
        // // 设置成本中心参照
        // new CoprodcoefCostCenterByOrgFilter(condDLGDelegator, CMMLangConstCoprodcoef.PK_ORG,
        // CMMLangConstCoprodcoef.CCOSTCENTERID).addEditorListener();
        // 联合体、产品支持业务单元选择\成本中心
        new CMBasedocCommonByOrgFilter(condDLGDelegator, CMMLangConstCoprodcoef.PK_ORG, new String[] {
            CMMLangConstCoprodcoef.CCOSTCENTERID
        }).addEditorListener();

        // 核算要素
        new CoprodcoefFactorByFactorChartFilter(condDLGDelegator, CMMLangConstCoprodcoef.PK_FACTORCHART,
                CMMLangConstCoprodcoef.CELEMENTID).addEditorListener();
        new CoprodcoefFactorByElementSystemFilter(condDLGDelegator, CMMLangConstCoprodcoef.PK_ELEMENTSYSTEM,
                CMMLangConstCoprodcoef.CELEMENTID).addEditorListener();
        // 核算要素表
        new CoprodcoefFactorChartByElementSystemFilter(condDLGDelegator, CMMLangConstCoprodcoef.PK_ELEMENTSYSTEM,
                CMMLangConstCoprodcoef.PK_FACTORCHART).addEditorListener();
        // 要素体系
        new CoprodcoefElementSystemFilter(condDLGDelegator, CMMLangConstCoprodcoef.PK_ORG,
                CMMLangConstCoprodcoef.PK_ELEMENTSYSTEM).addEditorListener();
    }

}
