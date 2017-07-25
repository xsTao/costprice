package nc.ui.mapub.materialpricebase.query;

import nc.ui.bd.pub.filter.QCustomerFilter;
import nc.ui.bd.pub.filter.QProductFilter;
import nc.ui.bd.pub.filter.QProjectFilter;
import nc.ui.bd.pub.filter.QSupplierFilter;
import nc.ui.cmpub.business.filter.CMCommonByOrgFilter;
import nc.ui.cmpub.business.filter.CMQMarAndDefFilter;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.vo.mapub.materialpricebase.entity.CMMCommonConstMaterialPriceBase;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;

/**
 * ���ϼ۸���ѯ���ʼ��
 *
 * @since 6.36
 * @version 2014-11-10 ����1:45:45
 * @author zhangchd
 */
public class MaterialPriceBaseQueryConditionInitializer implements IQueryConditionDLGInitializer {

    /**
     * ģ��
     */
    private AbstractUIAppModel model;

    public AbstractUIAppModel getModel() {
        return this.model;
    }

    public void setModel(AbstractUIAppModel model) {
        this.model = model;
    }

    @Override
    public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
        // ������Ȩ�޵Ĺ���
        condDLGDelegator.registerNeedPermissionOrgFieldCode(CMMCommonConstMaterialPriceBase.PK_ORG);

        // ���������ֶΣ����� ����1:��֯������2 ��Ԫ���ݶ�Ӧ·��
        condDLGDelegator.addRedundancyInfo(MaterialPriceBaseHeadVO.PK_ORG, "itempks.pk_org");
        // ���������ֶΣ����� ����1:���ţ�����2 ��Ԫ���ݶ�Ӧ·��
        condDLGDelegator.addRedundancyInfo(MaterialPriceBaseHeadVO.PK_GROUP, "itempks.pk_group");

        // ���ݹ����������ϣ����ϻ������࣬���ϳɱ����� //modify by zhangchd
        new CMCommonByOrgFilter(condDLGDelegator, CMMCommonConstMaterialPriceBase.PK_ORG, new String[] {
            CMMCommonConstMaterialPriceBase.CMATERIALID, CMMCommonConstMaterialPriceBase.PK_MARBASCLASS,
            CMMCommonConstMaterialPriceBase.PK_MARCOSTCLASS
        }).addEditorListener();

        // �Զ�������������,���óɲ���,����֯���� //modify by zhangchd
        CMQMarAndDefFilter marAndDefQueryFilter = new CMQMarAndDefFilter(condDLGDelegator);
        marAndDefQueryFilter.setItemPrefixs(new String[] {
            "itempks.vbfree"// , "itemid1.vbdef"
        });
        marAndDefQueryFilter.addFilterMapsListeners();

        // �ͻ�
        QCustomerFilter customerFilter = new QCustomerFilter(condDLGDelegator, MaterialPriceBaseBodyVO.CCUSTOMERID);
        customerFilter.addEditorListener();
        // ��Ӧ��
        QSupplierFilter supplierFilter = new QSupplierFilter(condDLGDelegator, MaterialPriceBaseBodyVO.CVENDORID);
        // supplierFilter.setPk_orgCode(StuffLangConst.PK_ORG);
        supplierFilter.addEditorListener();

        // ��������
        QProductFilter productFilter =
                new QProductFilter(condDLGDelegator, MaterialPriceBaseBodyVO.PK_ORG,
                        MaterialPriceBaseBodyVO.CPRODUCTORID);
        productFilter.addEditorListener();

        // ��Ŀ
        QProjectFilter projectFilter =
                new QProjectFilter(condDLGDelegator, MaterialPriceBaseBodyVO.PK_ORG, MaterialPriceBaseBodyVO.CPROJECTID);
        projectFilter.addEditorListener();

        // �������ɸ������Դ���
        MarAssistantDealer marAssisvb = new MarAssistantDealer();
        marAssisvb.setPrefix("vbfree");
        condDLGDelegator.addQueryCondVODealer(marAssisvb);
    }

}
