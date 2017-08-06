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
 * ����Ҫ�ز�ѯ
 *
 * @since v6.3
 * @version 2017��8��4�� ����1:16:23
 * @author Administrator
 */
public class CostPriceFactorFilter extends AbstractBDLinkageColumnListener {

    // ��ѯ��
    private QueryConditionDLGDelegator dlg;

    // ����
    private String pk_org;

    // ����Ҫ��
    private String factor;

    private CMQueryTemplateUtil queryUtil = null;

    /**
     * ���캯��
     *
     * @param qryCondDLGDelegator
     */
    public CostPriceFactorFilter(QueryConditionDLGDelegator qryCondDLGDelegator) {
        super(qryCondDLGDelegator);

    }

    /**
     * ���캯��
     *
     * @param qryCondDLGDelegator ��ѯ�Ի������
     * @param fatherPath ����
     * @param childPath ����Ҫ��
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
        // ע��༭�¼�
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
        // ��ս���ֵ
        refPane.setPK(null);
        refPane.setValueObjAlwaysFireValueChange(null);
        // UIRefPane refPane = this.getUIRefPane(editor);
        Map<String, CMConditionVO> conditionMap = this.getQueryUtil().getConditionVOMap(this.dlg.getQueryScheme());
        String factorChart = null;
        // ȷ������Ҫ�ر��ѯ���������Ҳ�
        if (null != conditionMap.get(CostPriceHeadVO.PK_ORG)) {
            factorChart = conditionMap.get(CostPriceHeadVO.PK_ORG).getSingleValue();
        }
        if (CMStringUtil.isEmpty(factorChart) || CMStringUtil.isEqual(factorChart, "null")) {
            editor.setEnable(false);
        }
        else {
            editor.setEnable(true);
            // ����FactorRefModel�е�factorChart
            ((FactorRefModel) refPane.getRefModel()).setPk_factorchart(factorChart);
            super.setMultiCorp(fatherValues, editor);
            super.processLinkageLogic(fatherValues, editor);
        }
    }
}
