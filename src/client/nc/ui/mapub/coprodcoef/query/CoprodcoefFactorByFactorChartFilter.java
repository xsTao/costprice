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
 * ͨ���������˹��������ܿط�Χ������Ҫ�ر��Ӧ�ĺ���Ҫ��
 *
 * @since 6.0
 * @version 2011-8-2 ����02:32:25
 * @author shangzhm1
 *         copy from cm by liwzh
 */

public class CoprodcoefFactorByFactorChartFilter extends AbstractBDLinkageColumnListener {
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
    public CoprodcoefFactorByFactorChartFilter(QueryConditionDLGDelegator qryCondDLGDelegator, String fatherPath,
            String childPath) {
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
        // ��ս���ֵ
        refPane.setPK(null);
        refPane.setValueObjAlwaysFireValueChange(null);
        // UIRefPane refPane = this.getUIRefPane(editor);
        Map<String, CMConditionVO> conditionMap = this.getQueryUtil().getConditionVOMap(this.dlg.getQueryScheme());
        String factorChart = null;
        // ȷ������Ҫ�ر��ѯ���������Ҳ�
        if (null != conditionMap.get(CoprodcoefHeadVO.PK_FACTORCHART)) {
            factorChart = conditionMap.get(CoprodcoefHeadVO.PK_FACTORCHART).getSingleValue();
        }
        if (CMStringUtil.isEmpty(factorChart) || CMStringUtil.isEqual(factorChart, "null")) {
            editor.setEnable(false);
        }
        else {
            editor.setEnable(true);
            // ����FactorRefModel�е�factorChart
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
