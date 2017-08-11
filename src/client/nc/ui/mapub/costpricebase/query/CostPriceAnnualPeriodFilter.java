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
 * ����Ҫ�ز�ѯ
 *
 * @since v6.3
 * @version 2017��8��4�� ����1:16:23
 * @author Administrator
 */
public class CostPriceAnnualPeriodFilter extends AbstractBDLinkageColumnListener {

    // ��ѯ��
    private QueryConditionDLGDelegator dlg;

    // ����ڼ�����
    private String father;

    // ��Ȼ����ڼ�
    private String child;

    /**
     * ���캯��
     *
     * @param qryCondDLGDelegator
     */
    public CostPriceAnnualPeriodFilter(QueryConditionDLGDelegator qryCondDLGDelegator) {
        super(qryCondDLGDelegator);

    }

    /**
     * ���캯��
     *
     * @param qryCondDLGDelegator ��ѯ�Ի������
     * @param fatherPath ���/����ڼ�
     * @param childPath ����ڼ�/���
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
        // ע��༭�¼�
        this.dlg.registerCriteriaEditorListener(this);
    }

    @Override
    protected void processLinkageLogic(List<IFieldValueElement> fatherValues, IFilterEditor editor) {

        // ��Ȳ�Ϊ�գ����ڼ䲻�ɱ༭������ڼ䲻Ϊ������Ȳ��ɱ༭
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
