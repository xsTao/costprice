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
 * 材料价格库查询框初始化
 *
 * @since 6.36
 * @version 2014-11-10 下午1:45:45
 * @author zhangchd
 */
public class MaterialPriceBaseQueryConditionInitializer implements IQueryConditionDLGInitializer {

    /**
     * 模型
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
        // 过滤有权限的工厂
        condDLGDelegator.registerNeedPermissionOrgFieldCode(CMMCommonConstMaterialPriceBase.PK_ORG);

        // 处理冗余字段，过滤 参数1:组织，参数2 子元数据对应路径
        condDLGDelegator.addRedundancyInfo(MaterialPriceBaseHeadVO.PK_ORG, "itempks.pk_org");
        // 处理冗余字段，过滤 参数1:集团，参数2 子元数据对应路径
        condDLGDelegator.addRedundancyInfo(MaterialPriceBaseHeadVO.PK_GROUP, "itempks.pk_group");

        // 根据工厂过滤物料，物料基本分类，物料成本分类 //modify by zhangchd
        new CMCommonByOrgFilter(condDLGDelegator, CMMCommonConstMaterialPriceBase.PK_ORG, new String[] {
            CMMCommonConstMaterialPriceBase.CMATERIALID, CMMCommonConstMaterialPriceBase.PK_MARBASCLASS,
            CMMCommonConstMaterialPriceBase.PK_MARCOSTCLASS
        }).addEditorListener();

        // 自定义项与自由项,启用成参照,用组织过滤 //modify by zhangchd
        CMQMarAndDefFilter marAndDefQueryFilter = new CMQMarAndDefFilter(condDLGDelegator);
        marAndDefQueryFilter.setItemPrefixs(new String[] {
            "itempks.vbfree"// , "itemid1.vbdef"
        });
        marAndDefQueryFilter.addFilterMapsListeners();

        // 客户
        QCustomerFilter customerFilter = new QCustomerFilter(condDLGDelegator, MaterialPriceBaseBodyVO.CCUSTOMERID);
        customerFilter.addEditorListener();
        // 供应商
        QSupplierFilter supplierFilter = new QSupplierFilter(condDLGDelegator, MaterialPriceBaseBodyVO.CVENDORID);
        // supplierFilter.setPk_orgCode(StuffLangConst.PK_ORG);
        supplierFilter.addEditorListener();

        // 生产厂商
        QProductFilter productFilter =
                new QProductFilter(condDLGDelegator, MaterialPriceBaseBodyVO.PK_ORG,
                        MaterialPriceBaseBodyVO.CPRODUCTORID);
        productFilter.addEditorListener();

        // 项目
        QProjectFilter projectFilter =
                new QProjectFilter(condDLGDelegator, MaterialPriceBaseBodyVO.PK_ORG, MaterialPriceBaseBodyVO.CPROJECTID);
        projectFilter.addEditorListener();

        // 表体自由辅助属性处理
        MarAssistantDealer marAssisvb = new MarAssistantDealer();
        marAssisvb.setPrefix("vbfree");
        condDLGDelegator.addQueryCondVODealer(marAssisvb);
    }

}
