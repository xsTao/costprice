/**
 *
 */
package nc.ui.mapub.costpricebase.query;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.ICriteriaChangedListener;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.vo.mapub.costpricebase.entity.CMMCommonConstCostPriceBase;
import nc.vo.mapub.costpricebase.entity.CostPriceHeadVO;

/**
 * 费用价格查询初始化
 *
 * @since v6.3
 * @version 2017年7月20日 下午7:24:54
 * @author Administrator
 */
public class CostPriceQueryConditionInitializer implements IQueryConditionDLGInitializer {

    private AbstractUIAppModel model = null;

    /**
     * 获得 model 的属性值
     *
     * @return the model
     * @since 2017年7月20日
     * @author Administrator
     */
    public AbstractUIAppModel getModel() {
        return this.model;
    }

    /**
     * 设置 model 的属性值
     *
     * @param model the model to set
     * @since 2017年7月20日
     * @author Administrator
     */
    public void setModel(AbstractUIAppModel model) {
        this.model = model;
    }

    /*
     * (non-Javadoc)
     * @see nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer#initQueryConditionDLG(nc.ui.pubapp.uif2app.query2.
     * QueryConditionDLGDelegator)
     */
    @Override
    public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
        // TODO Auto-generated method stub
        // 过滤有权限的工厂
        condDLGDelegator.registerNeedPermissionOrgFieldCode(CMMCommonConstCostPriceBase.PK_ORG);

        // // 处理冗余字段，过滤 参数1:组织，参数2 子元数据对应路径
        condDLGDelegator.addRedundancyInfo(CostPriceHeadVO.PK_ORG, "itempks.pk_org");
        // // 处理冗余字段，过滤 参数1:集团，参数2 子元数据对应路径
        condDLGDelegator.addRedundancyInfo(CostPriceHeadVO.PK_GROUP, "itempks.pk_group");
        //
        // // 自定义项与自由项,启用成参照,用组织过滤 //modify by zhangchd
        // CMQMarAndDefFilter marAndDefQueryFilter = new CMQMarAndDefFilter(condDLGDelegator);
        // marAndDefQueryFilter.setItemPrefixs(new String[] {
        // "itempks.vbfree"// , "itemid1.vbdef"
        // });
        // marAndDefQueryFilter.addFilterMapsListeners();
        //
        // //
        // // 表体自由辅助属性处理
        // MarAssistantDealer marAssisvb = new MarAssistantDealer();
        // marAssisvb.setPrefix("vbfree");
        // condDLGDelegator.addQueryCondVODealer(marAssisvb);
    }

    public void registerCriteriaEditorListener(ICriteriaChangedListener listener) {

    }

}
