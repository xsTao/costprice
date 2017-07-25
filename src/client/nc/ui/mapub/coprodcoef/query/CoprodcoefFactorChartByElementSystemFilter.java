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
    // ��ѯ��
    private QueryConditionDLGDelegator dlg;

    // ����
    private String pk_org;

    // ����Ҫ��
    private String factor;

    private CMQueryTemplateUtil queryUtil = null;

    private CMQueryTemplateUtil getQueryUtil() {
        if (null == this.queryUtil) {
            this.queryUtil = new CMQueryTemplateUtil();
        }
        return this.queryUtil;
    }

    /**
     * ���캯��
     *
     * @param qryCondDLGDelegator ��ѯ�Ի������
     * @param fatherPath ����
     * @param childPath ����Ҫ��
     */
    public CoprodcoefFactorChartByElementSystemFilter(QueryConditionDLGDelegator qryCondDLGDelegator,
            String fatherPath, String childPath) {
        super(qryCondDLGDelegator);
        this.pk_org = fatherPath;
        this.factor = childPath;
        this.dlg = qryCondDLGDelegator;
    }

    /**
     * ��ӱ༭������¼�������Ϊ�������������������������Ϊ��Ʒ����
     */
    public void addEditorListener() {
        this.setFatherPath(this.pk_org);
        this.setChildPath(this.factor);
        // ע��༭�¼�
        this.dlg.registerCriteriaEditorListener(this);
    }

    @Override
    protected void processLinkageLogic(List<IFieldValueElement> fatherValues, IFilterEditor editor) {
        FilterEditorWrapper wrapper = new FilterEditorWrapper(editor);
        super.setSingleOrg(fatherValues, wrapper);
        UIRefPane refPane = super.getUIRefPane(wrapper);
        // refPane.getUITextField().setValue(null);
        // refPane.setText(null);
        // ��ս���ֵ
        refPane.setPK(null);
        refPane.setValueObjAlwaysFireValueChange(null);
        // refPane.getText();
        // UIRefPane refPane = this.getUIRefPane(editor);
        Map<String, CMConditionVO> conditionMap = this.getQueryUtil().getConditionVOMap(this.dlg.getQueryScheme());
        String elementSystem = null;
        String pk_org = null;
        // String pk_ca = null;
        pk_org = conditionMap.get(CoprodcoefHeadVO.PK_ORG).getSingleValue();
        // ȷ������Ҫ����ϵ��ѯ���������Ҳ�
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
            // ���õ�ѡ
            refPane.setMultiSelectedEnabled(false);
            super.setMultiCorp(fatherValues, editor);
            super.processLinkageLogic(fatherValues, editor);
        }
    }
}
