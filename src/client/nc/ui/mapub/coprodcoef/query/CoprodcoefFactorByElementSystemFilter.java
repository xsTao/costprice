package nc.ui.mapub.coprodcoef.query;

import java.util.List;

import nc.ui.bd.pub.filter.AbstractBDLinkageColumnListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.filtereditor.FilterEditorWrapper;
import nc.ui.querytemplate.filtereditor.IFilterEditor;
import nc.ui.querytemplate.value.IFieldValueElement;

public class CoprodcoefFactorByElementSystemFilter extends AbstractBDLinkageColumnListener {
    // ��ѯ��
    private QueryConditionDLGDelegator dlg;

    // ����
    private String pk_org;

    // ����Ҫ��
    private String factor;

    /**
     * ���캯��
     *
     * @param qryCondDLGDelegator ��ѯ�Ի������
     * @param fatherPath ����
     * @param childPath ����Ҫ��
     */
    public CoprodcoefFactorByElementSystemFilter(QueryConditionDLGDelegator qryCondDLGDelegator, String fatherPath,
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
        // ���õ�ѡ
        refPane.setPK(null);
        refPane.setValueObjAlwaysFireValueChange(null);
        editor.setEnable(false);
        super.setMultiCorp(fatherValues, editor);
        super.processLinkageLogic(fatherValues, editor);
    }
}
