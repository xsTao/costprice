package nc.ui.mapub.coprodcoef.query;

import java.util.List;

import nc.bd.framework.db.CMSqlBuilder;
import nc.ui.bd.pub.filter.AbstractBDLinkageColumnListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.filtereditor.FilterEditorWrapper;
import nc.ui.querytemplate.filtereditor.IFilterEditor;
import nc.ui.querytemplate.value.IFieldValueElement;
import nc.vo.bd.material.MaterialVersionVO;
import nc.vo.bd.material.prod.MaterialProdVO;

/**
 * ���˳�ҵ��Ԫ���ǳɱ�����Ǹ������������
 *
 * @since 6.36
 * @version 2014-12-5 ����4:03:40
 * @author zhangshyb
 */
public class CoprodcoefMaterialByCostObjAndAssSrv extends AbstractBDLinkageColumnListener {

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
    public CoprodcoefMaterialByCostObjAndAssSrv(QueryConditionDLGDelegator dlg, String fatherPath, String childPath) {
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
        // ���˵õ��ǳɱ�����Ǹ�������Ĳ�Ʒ
        FilterEditorWrapper wrapper = new FilterEditorWrapper(editor);
        super.setSingleOrg(fatherValues, wrapper);
        UIRefPane refPane = super.getUIRefPane(wrapper);
        CMSqlBuilder sql = new CMSqlBuilder();
        sql.append("dr = 0");
        sql.and();
        sql.append(MaterialVersionVO.PK_MATERIAL);
        sql.append(" in (");
        sql.select();
        sql.append(MaterialProdVO.PK_MATERIAL);
        sql.from(MaterialProdVO.getDefaultTableName());
        sql.where();
        sql.append(MaterialProdVO.SFCBDX);
        sql.append(" = 'Y'");
        sql.and();
        sql.append(MaterialProdVO.SFFZFW);
        sql.append(" = 'N'");
        sql.and();
        sql.append("dr = 0 )");
        refPane.setWhereString(sql.toString());
        super.setMultiCorp(fatherValues, editor);
        super.processLinkageLogic(fatherValues, editor);
    }
}
// }
