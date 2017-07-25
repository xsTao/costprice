/**
 *
 */
package nc.ui.mapub.acquirepricelog.query;

import nc.ui.bd.pub.filter.QCustomerFilter;
import nc.ui.bd.pub.filter.QProductFilter;
import nc.ui.bd.pub.filter.QProjectFilter;
import nc.ui.bd.pub.filter.QSupplierFilter;
import nc.ui.cmpub.business.filter.CMAccPeriodByOrgFilter;
import nc.ui.cmpub.business.filter.CMCommonByOrgFilter;
import nc.ui.cmpub.business.filter.CMQMarAndDefFilter;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.vo.mapub.acquirepricelog.entity.AcquirePriceLogVO;

/**
 * @since v6.3
 * @version 2015��4��17�� ����4:05:21
 * @author lizhpf
 */
public class AcquirepricelogQueryConditionInitializer implements IQueryConditionDLGInitializer {

    /*
     * (non-Javadoc)
     * @see nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer#initQueryConditionDLG(nc.ui.pubapp.uif2app.query2.
     * QueryConditionDLGDelegator)
     */
    @Override
    public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
        // TODO Auto-generated method stub
        // ���ù���Ȩ��
        // condDLGDelegator.setPowerEnable(true);
        // ������Ȩ�޵Ĺ���
        condDLGDelegator.registerNeedPermissionOrgFieldCode(AcquirePriceLogVO.PK_ORG);
        // ���ݹ��������ڼ䷽��
        new CMAccPeriodByOrgFilter(condDLGDelegator, AcquirePriceLogVO.PK_ORG, AcquirePriceLogVO.CPERIOD)
        .addEditorListener();
        // ���ݹ������˲��ϡ����˳ɱ�����
        new CMCommonByOrgFilter(condDLGDelegator, AcquirePriceLogVO.PK_ORG, new String[] {
            AcquirePriceLogVO.CBMATERIALID, AcquirePriceLogVO.CCOSTCENTERID
        }).addEditorListener();
        // AcquirePriceLogVO.CCOSTCENTERID,
        // �Զ�������������,���óɲ���,����֯����
        CMQMarAndDefFilter marAndDefQueryFilter = new CMQMarAndDefFilter(condDLGDelegator);
        marAndDefQueryFilter.setItemPrefixs(new String[] {
                "vfree"
        });
        marAndDefQueryFilter.addFilterMapsListeners();
        // �Զ���������ɸ������Բ��մ���
        MarAssistantDealer marAssistantDealer = new MarAssistantDealer();
        marAssistantDealer.setPrefix("vfree");
        condDLGDelegator.addQueryCondVODealer(marAssistantDealer);
        // �ͻ�
        new QCustomerFilter(condDLGDelegator, "ccustomerid").addEditorListener();
        // ��Ӧ��
        QSupplierFilter supplierFilter = new QSupplierFilter(condDLGDelegator, "cvendorid");
        supplierFilter.setPk_orgCode(AcquirePriceLogVO.PK_ORG);
        supplierFilter.addEditorListener();
        // ��������
        new QProductFilter(condDLGDelegator, AcquirePriceLogVO.PK_ORG, "cproductorid").addEditorListener();
        // ��Ŀ
        new QProjectFilter(condDLGDelegator, AcquirePriceLogVO.PK_ORG, "cprojectid").addEditorListener();

    }

}
