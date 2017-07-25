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
import nc.ui.resa.refmodel.FactorChartRefModel;
import nc.vo.cmpub.framework.report.CMConditionVO;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefHeadVO;

public class CoprodcoefFactorChartByElementSystemFilter extends AbstractBDLinkageColumnListener {
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
    public CoprodcoefFactorChartByElementSystemFilter(QueryConditionDLGDelegator qryCondDLGDelegator,
            String fatherPath, String childPath) {
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
        // refPane.getUITextField().setValue(null);
        // refPane.setText(null);
        // 清空界面值
        refPane.setPK(null);
        refPane.setValueObjAlwaysFireValueChange(null);
        // refPane.getText();
        // UIRefPane refPane = this.getUIRefPane(editor);
        Map<String, CMConditionVO> conditionMap = this.getQueryUtil().getConditionVOMap(this.dlg.getQueryScheme());
        String elementSystem = null;
        String pk_org = null;
        // String pk_ca = null;
        pk_org = conditionMap.get(CoprodcoefHeadVO.PK_ORG).getSingleValue();
        // 确保核算要素体系查询条件已在右侧
        if (null != conditionMap.get(CoprodcoefHeadVO.PK_ELEMENTSYSTEM)) {
            elementSystem = conditionMap.get(CoprodcoefHeadVO.PK_ELEMENTSYSTEM).getSingleValue();
        }
        // try {
        // pk_ca =
        // NCLocator.getInstance().lookup(IControlAreaPubService.class)
        // .queryControlAreaByPk_org(refPane.getRefModel().getPk_org(), null).getPk_controlarea();
        // }
        // catch (BusinessException e) {
        // ExceptionUtils.wrappException(e);
        // }
        if (CMStringUtil.isEmpty(elementSystem) || CMStringUtil.isEqual(elementSystem, "null")) {
            editor.setEnable(false);
        }
        else {
            editor.setEnable(true);
            FactorChartRefModel refModel = (FactorChartRefModel) refPane.getRefModel();
            refModel.setPk_org(pk_org, elementSystem);
            refPane.setNotLeafSelectedEnabled(true);
            // 设置单选
            refPane.setMultiSelectedEnabled(false);
            super.setMultiCorp(fatherValues, editor);
            super.processLinkageLogic(fatherValues, editor);
        }
    }
}
