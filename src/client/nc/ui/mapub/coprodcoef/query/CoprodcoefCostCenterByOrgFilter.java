package nc.ui.mapub.coprodcoef.query;

import java.util.List;

import nc.ui.bd.pub.filter.AbstractBDLinkageColumnListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.filtereditor.FilterEditorWrapper;
import nc.ui.querytemplate.filtereditor.IFilterEditor;
import nc.ui.querytemplate.value.IFieldValueElement;

/**
 * 过滤出业务单元可见的未停用的成本中心
 *
 * @since 6.36
 * @version 2014-12-5 下午4:03:40
 * @author zhangshyb
 */
public class CoprodcoefCostCenterByOrgFilter extends AbstractBDLinkageColumnListener {

    private QueryConditionDLGDelegator dlg;

    private String childPath;

    private String fatherPath;

    /**
     * 构造函数初始化
     *
     * @param qryCondDLGDelegator 查询模板对话框代理
     * @param fatherPath 管控范围主键
     * @param childPath 物料主键
     */
    public CoprodcoefCostCenterByOrgFilter(QueryConditionDLGDelegator dlg, String fatherPath, String childPath) {
        super(dlg);
        this.dlg = dlg;
        this.fatherPath = fatherPath;
        this.childPath = childPath;
    }

    /**
     * 添加编辑后监听事件，父亲为传入的条件来触发监听，孩子为产品编码
     */
    public void addEditorListener() {
        this.setFatherPath(this.fatherPath);
        this.setChildPath(this.childPath);
        // 注册编辑事件
        this.dlg.registerCriteriaEditorListener(this);
    }

    @Override
    public void processLinkageLogic(List<IFieldValueElement> fatherValues, IFilterEditor editor) {
        FilterEditorWrapper wrapper = new FilterEditorWrapper(editor);
        super.setSingleOrg(fatherValues, wrapper);
        UIRefPane refPane = super.getUIRefPane(wrapper);
        // 清空界面值
        refPane.setPK(null);
        refPane.setValueObjAlwaysFireValueChange(null);
        super.setMultiCorp(fatherValues, editor);
        super.processLinkageLogic(fatherValues, editor);
    }
}
