/**
 *
 */
package nc.ui.mapub.costpricebase.query;

import java.util.List;

import nc.ui.bd.pub.filter.AbstractBDLinkageColumnListener;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.filtereditor.IFilterEditor;
import nc.ui.querytemplate.value.IFieldValueElement;

/**
 * 核算要素查询
 *
 * @since v6.3
 * @version 2017年8月4日 下午1:16:23
 * @author Administrator
 */
public class CostPriceAnnualPeriodFilter extends AbstractBDLinkageColumnListener {

    // 查询框
    private QueryConditionDLGDelegator dlg;

    // 会计期间或年度
    private String father;

    // 年度或会计期间
    private String child;

    /**
     * 构造函数
     *
     * @param qryCondDLGDelegator
     */
    public CostPriceAnnualPeriodFilter(QueryConditionDLGDelegator qryCondDLGDelegator) {
        super(qryCondDLGDelegator);

    }

    /**
     * 构造函数
     *
     * @param qryCondDLGDelegator 查询对话框代理
     * @param fatherPath 年度/会计期间
     * @param childPath 会计期间/年度
     */
    public CostPriceAnnualPeriodFilter(QueryConditionDLGDelegator qryCondDLGDelegator, String fatherPath,
            String childPath) {
        super(qryCondDLGDelegator);
        this.father = fatherPath;
        this.child = childPath;
        this.dlg = qryCondDLGDelegator;
    }

    public void addEditorListener() {
        this.setFatherPath(this.father);
        this.setChildPath(this.child);
        // 注册编辑事件
        this.dlg.registerCriteriaEditorListener(this);
    }

    @Override
    protected void processLinkageLogic(List<IFieldValueElement> fatherValues, IFilterEditor editor) {

        // 年度不为空，则期间不可编辑，会计期间不为空则年度不可编辑
        if (fatherValues.size() > 0) {
            editor.setEnable(false);
            editor.clearData();
        }
        else {
            editor.setEnable(true);
            editor.clearData();
            super.processLinkageLogic(fatherValues, editor);
        }
    }
}
