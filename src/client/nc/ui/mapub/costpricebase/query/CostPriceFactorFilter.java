/**
 *
 */
package nc.ui.mapub.costpricebase.query;

import java.util.List;
import java.util.Map;

import nc.bd.framework.base.CMStringUtil;
import nc.cmpub.framework.query.CMQueryTemplateUtil;
import nc.ui.bd.pub.filter.AbstractBDLinkageColumnListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.filtereditor.FilterEditorWrapper;
import nc.ui.querytemplate.filtereditor.IFilterEditor;
import nc.ui.querytemplate.value.IFieldValueElement;
import nc.ui.resa.refmodel.FactorRefModel;
import nc.vo.cmpub.framework.report.CMConditionVO;
import nc.vo.mapub.costpricebase.entity.CostPriceHeadVO;

/**
 * 核算要素查询
 *
 * @since v6.3
 * @version 2017年8月4日 下午1:16:23
 * @author Administrator
 */
public class CostPriceFactorFilter extends AbstractBDLinkageColumnListener {

    // 查询框
    private QueryConditionDLGDelegator dlg;

    // 工厂
    private String pk_org;

    // 核算要素
    private String factor;

    private CMQueryTemplateUtil queryUtil = null;

    /**
     * 构造函数
     *
     * @param qryCondDLGDelegator
     */
    public CostPriceFactorFilter(QueryConditionDLGDelegator qryCondDLGDelegator) {
        super(qryCondDLGDelegator);

    }

    /**
     * 构造函数
     *
     * @param qryCondDLGDelegator 查询对话框代理
     * @param fatherPath 工厂
     * @param childPath 核算要素
     */
    public CostPriceFactorFilter(QueryConditionDLGDelegator qryCondDLGDelegator, String fatherPath, String childPath) {
        super(qryCondDLGDelegator);
        this.pk_org = fatherPath;
        this.factor = childPath;
        this.dlg = qryCondDLGDelegator;
    }

    public void addEditorListener() {
        this.setFatherPath(this.pk_org);
        this.setChildPath(this.factor);
        // 注册编辑事件
        this.dlg.registerCriteriaEditorListener(this);
    }

    private CMQueryTemplateUtil getQueryUtil() {
        if (null == this.queryUtil) {
            this.queryUtil = new CMQueryTemplateUtil();
        }
        return this.queryUtil;
    }

    @Override
    protected void processLinkageLogic(List<IFieldValueElement> fatherValues, IFilterEditor editor) {
        FilterEditorWrapper wrapper = new FilterEditorWrapper(editor);
        super.setSingleOrg(fatherValues, wrapper);
        UIRefPane refPane = super.getUIRefPane(wrapper);
        // 清空界面值
        refPane.setPK(null);
        refPane.setValueObjAlwaysFireValueChange(null);
        // UIRefPane refPane = this.getUIRefPane(editor);
        Map<String, CMConditionVO> conditionMap = this.getQueryUtil().getConditionVOMap(this.dlg.getQueryScheme());
        String factorChart = null;
        // 确保核算要素表查询条件已在右侧
        if (null != conditionMap.get(CostPriceHeadVO.PK_ORG)) {
            factorChart = conditionMap.get(CostPriceHeadVO.PK_ORG).getSingleValue();
        }
        if (CMStringUtil.isEmpty(factorChart) || CMStringUtil.isEqual(factorChart, "null")) {
            editor.setEnable(false);
        }
        else {
            editor.setEnable(true);
            // 设置FactorRefModel中的factorChart
            ((FactorRefModel) refPane.getRefModel()).setPk_factorchart(factorChart);
            super.setMultiCorp(fatherValues, editor);
            super.processLinkageLogic(fatherValues, editor);
        }
    }
}
