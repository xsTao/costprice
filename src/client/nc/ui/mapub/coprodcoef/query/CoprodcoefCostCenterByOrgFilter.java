package nc.ui.mapub.coprodcoef.query;

import java.util.List;

import nc.ui.bd.pub.filter.AbstractBDLinkageColumnListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.filtereditor.FilterEditorWrapper;
import nc.ui.querytemplate.filtereditor.IFilterEditor;
import nc.ui.querytemplate.value.IFieldValueElement;

/**
 * ���˳�ҵ��Ԫ�ɼ���δͣ�õĳɱ�����
 *
 * @since 6.36
 * @version 2014-12-5 ����4:03:40
 * @author zhangshyb
 */
public class CoprodcoefCostCenterByOrgFilter extends AbstractBDLinkageColumnListener {

    private QueryConditionDLGDelegator dlg;

    private String childPath;

    private String fatherPath;

    /**
     * ���캯����ʼ��
     *
     * @param qryCondDLGDelegator ��ѯģ��Ի������
     * @param fatherPath �ܿط�Χ����
     * @param childPath ��������
     */
    public CoprodcoefCostCenterByOrgFilter(QueryConditionDLGDelegator dlg, String fatherPath, String childPath) {
        super(dlg);
        this.dlg = dlg;
        this.fatherPath = fatherPath;
        this.childPath = childPath;
    }

    /**
     * ��ӱ༭������¼�������Ϊ�������������������������Ϊ��Ʒ����
     */
    public void addEditorListener() {
        this.setFatherPath(this.fatherPath);
        this.setChildPath(this.childPath);
        // ע��༭�¼�
        this.dlg.registerCriteriaEditorListener(this);
    }

    @Override
    public void processLinkageLogic(List<IFieldValueElement> fatherValues, IFilterEditor editor) {
        FilterEditorWrapper wrapper = new FilterEditorWrapper(editor);
        super.setSingleOrg(fatherValues, wrapper);
        UIRefPane refPane = super.getUIRefPane(wrapper);
        // ��ս���ֵ
        refPane.setPK(null);
        refPane.setValueObjAlwaysFireValueChange(null);
        super.setMultiCorp(fatherValues, editor);
        super.processLinkageLogic(fatherValues, editor);
    }
}
