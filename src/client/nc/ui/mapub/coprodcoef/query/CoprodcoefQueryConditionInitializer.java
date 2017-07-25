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
 * @version 2014-10-11 ����3:02:57
 * @author zhangshyb
 */
public class CoprodcoefQueryConditionInitializer implements IQueryConditionDLGInitializer {

    @Override
    public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
        // ���������ֶΣ����� ����1:��֯������2 ��Ԫ���ݶ�Ӧ·��
        condDLGDelegator.addRedundancyInfo("pk_org", "itembpks.pk_org");
        // ���������ֶΣ����� ����1:���ţ�����2 ��Ԫ���ݶ�Ӧ·��
        condDLGDelegator.addRedundancyInfo("pk_group", "itembpks.pk_group");
        // ���������ֶΣ����� ����1:����ڼ䣬����2 ��Ԫ���ݶ�Ӧ·��
        // condDLGDelegator.addRedundancyInfo(CMMLangConstBD0104.CPERIOD, "itemid.cperiod");
        // TODO ��ʼ����ѯģ���߼�
        // ������Ȩ�޵Ĺ���
        condDLGDelegator.registerNeedPermissionOrgFieldCode(CMMLangConstCoprodcoef.PK_ORG);
        // ��������
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
        // // �������������
        // new CoprodcoefMaterialByCostObjAndAssSrv(condDLGDelegator, CMMLangConstCoprodcoef.PK_ORG,
        // CMMLangConstCoprodcoef.CMATERIALID).addEditorListener();
        // // ���ò�Ʒ����
        // new CoprodcoefMaterialByCostObjAndAssSrv(condDLGDelegator, CMMLangConstCoprodcoef.PK_ORG,
        // CMMLangConstCoprodcoef.CM_PRODUCT_CMATERIALID).addEditorListener();
        // // ���óɱ����Ĳ���
        // new CoprodcoefCostCenterByOrgFilter(condDLGDelegator, CMMLangConstCoprodcoef.PK_ORG,
        // CMMLangConstCoprodcoef.CCOSTCENTERID).addEditorListener();
        // �����塢��Ʒ֧��ҵ��Ԫѡ��\�ɱ�����
        new CMBasedocCommonByOrgFilter(condDLGDelegator, CMMLangConstCoprodcoef.PK_ORG, new String[] {
            CMMLangConstCoprodcoef.CCOSTCENTERID
        }).addEditorListener();

        // ����Ҫ��
        new CoprodcoefFactorByFactorChartFilter(condDLGDelegator, CMMLangConstCoprodcoef.PK_FACTORCHART,
                CMMLangConstCoprodcoef.CELEMENTID).addEditorListener();
        new CoprodcoefFactorByElementSystemFilter(condDLGDelegator, CMMLangConstCoprodcoef.PK_ELEMENTSYSTEM,
                CMMLangConstCoprodcoef.CELEMENTID).addEditorListener();
        // ����Ҫ�ر�
        new CoprodcoefFactorChartByElementSystemFilter(condDLGDelegator, CMMLangConstCoprodcoef.PK_ELEMENTSYSTEM,
                CMMLangConstCoprodcoef.PK_FACTORCHART).addEditorListener();
        // Ҫ����ϵ
        new CoprodcoefElementSystemFilter(condDLGDelegator, CMMLangConstCoprodcoef.PK_ORG,
                CMMLangConstCoprodcoef.PK_ELEMENTSYSTEM).addEditorListener();
    }

}
