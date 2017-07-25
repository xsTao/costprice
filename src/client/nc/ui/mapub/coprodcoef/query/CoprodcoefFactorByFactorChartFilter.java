package nc.ui.mapub.coprodcoef.query;

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
import nc.vo.mapub.coprodcoef.entity.CoprodcoefHeadVO;

/**
 * 通过工厂过滤工厂所属管控范围关联的要素表对应的核算要素
 *
 * @since 6.0
 * @version 2011-8-2 下午02:32:25
 * @author shangzhm1
 *         copy from cm by liwzh
 */

public class CoprodcoefFactorByFactorChartFilter extends AbstractBDLinkageColumnListener {
    // 查询框
    private QueryConditionDLGDelegator dlg;

    // 工厂
    private String pk_org;

    // 核算要素
    private String factor;

    private CMQueryTemplateUtil queryUtil = null;

    private CMQueryTemplateUtil getQueryUtil() {
        if (null == this.queryUtil) {
            this.queryUtil = new CMQueryTemplateUtil();
        }
        return this.queryUtil;
    }

    /**
     * 构造函数
     *
     * @param qryCondDLGDelegator 查询对话框代理
     * @param fatherPath 工厂
     * @param childPath 核算要素
     */
    public CoprodcoefFactorByFactorChartFilter(QueryConditionDLGDelegator qryCondDLGDelegator, String fatherPath,
            String childPath) {
        super(qryCondDLGDelegator);
        this.pk_org = fatherPath;
        this.factor = childPath;
        this.dlg = qryCondDLGDelegator;
    }

    /**
     * 添加编辑后监听事件，父亲为传入的条件来触发监听，孩子为产品编码
     */
    public void addEditorListener() {
        this.setFatherPath(this.pk_org);
        this.setChildPath(this.factor);
        // 注册编辑事件
        this.dlg.registerCriteriaEditorListener(this);
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
        if (null != conditionMap.get(CoprodcoefHeadVO.PK_FACTORCHART)) {
            factorChart = conditionMap.get(CoprodcoefHeadVO.PK_FACTORCHART).getSingleValue();
        }
        if (CMStringUtil.isEmpty(factorChart) || CMStringUtil.isEqual(factorChart, "null")) {
            editor.setEnable(false);
        }
        else {
            editor.setEnable(true);
            // 设置FactorRefModel中的factorChart
            ((FactorRefModel) refPane.getRefModel()).setPk_factorchart(factorChart);
            // CMSqlBuilder sql = new CMSqlBuilder();
            // sql.append("resa_factor.dr = 0");
            // sql.and();
            // sql.append(FactorVO.getDefaultTableName() + "." + FactorVO.SEALFLAG + " = 'N'");
            // sql.and();
            // sql.append(FactorVO.getDefaultTableName() + "." + FactorVO.PK_FACTORCHART + " = '" + factorChart + "'");
            // refPane.setWhereString(sql.toString());
            // refPane.setMultiSelectedEnabled(false);
            super.setMultiCorp(fatherValues, editor);
            super.processLinkageLogic(fatherValues, editor);
        }
    }
}
