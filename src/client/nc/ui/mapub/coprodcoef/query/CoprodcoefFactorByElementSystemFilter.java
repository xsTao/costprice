package nc.ui.mapub.coprodcoef.query;

import java.util.List;

import nc.ui.bd.pub.filter.AbstractBDLinkageColumnListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.filtereditor.FilterEditorWrapper;
import nc.ui.querytemplate.filtereditor.IFilterEditor;
import nc.ui.querytemplate.value.IFieldValueElement;

public class CoprodcoefFactorByElementSystemFilter extends AbstractBDLinkageColumnListener {
    // 查询框
    private QueryConditionDLGDelegator dlg;

    // 工厂
    private String pk_org;

    // 核算要素
    private String factor;

    /**
     * 构造函数
     *
     * @param qryCondDLGDelegator 查询对话框代理
     * @param fatherPath 工厂
     * @param childPath 核算要素
     */
    public CoprodcoefFactorByElementSystemFilter(QueryConditionDLGDelegator qryCondDLGDelegator, String fatherPath,
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
        // 设置单选
        refPane.setPK(null);
        refPane.setValueObjAlwaysFireValueChange(null);
        editor.setEnable(false);
        super.setMultiCorp(fatherValues, editor);
        super.processLinkageLogic(fatherValues, editor);
    }
}
