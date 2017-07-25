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
 * @version 2015年4月17日 下午4:05:21
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
        // 设置工厂权限
        // condDLGDelegator.setPowerEnable(true);
        // 过滤有权限的工厂
        condDLGDelegator.registerNeedPermissionOrgFieldCode(AcquirePriceLogVO.PK_ORG);
        // 根据工厂过滤期间方案
        new CMAccPeriodByOrgFilter(condDLGDelegator, AcquirePriceLogVO.PK_ORG, AcquirePriceLogVO.CPERIOD)
        .addEditorListener();
        // 根据工厂过滤材料、过滤成本中心
        new CMCommonByOrgFilter(condDLGDelegator, AcquirePriceLogVO.PK_ORG, new String[] {
            AcquirePriceLogVO.CBMATERIALID, AcquirePriceLogVO.CCOSTCENTERID
        }).addEditorListener();
        // AcquirePriceLogVO.CCOSTCENTERID,
        // 自定义项与自由项,启用成参照,用组织过滤
        CMQMarAndDefFilter marAndDefQueryFilter = new CMQMarAndDefFilter(condDLGDelegator);
        marAndDefQueryFilter.setItemPrefixs(new String[] {
                "vfree"
        });
        marAndDefQueryFilter.addFilterMapsListeners();
        // 自定义项和自由辅助属性参照处理
        MarAssistantDealer marAssistantDealer = new MarAssistantDealer();
        marAssistantDealer.setPrefix("vfree");
        condDLGDelegator.addQueryCondVODealer(marAssistantDealer);
        // 客户
        new QCustomerFilter(condDLGDelegator, "ccustomerid").addEditorListener();
        // 供应商
        QSupplierFilter supplierFilter = new QSupplierFilter(condDLGDelegator, "cvendorid");
        supplierFilter.setPk_orgCode(AcquirePriceLogVO.PK_ORG);
        supplierFilter.addEditorListener();
        // 生产厂商
        new QProductFilter(condDLGDelegator, AcquirePriceLogVO.PK_ORG, "cproductorid").addEditorListener();
        // 项目
        new QProjectFilter(condDLGDelegator, AcquirePriceLogVO.PK_ORG, "cprojectid").addEditorListener();

    }

}
