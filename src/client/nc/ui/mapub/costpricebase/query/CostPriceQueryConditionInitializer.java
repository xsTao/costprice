/**
 *
 */
package nc.ui.mapub.costpricebase.query;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.ICriteriaChangedListener;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.vo.mapub.costpricebase.entity.CMMCommonConstCostPriceBase;
import nc.vo.mapub.costpricebase.entity.CostPriceHeadVO;

/**
 * ���ü۸��ѯ��ʼ��
 *
 * @since v6.3
 * @version 2017��7��20�� ����7:24:54
 * @author Administrator
 */
public class CostPriceQueryConditionInitializer implements IQueryConditionDLGInitializer {

    private AbstractUIAppModel model = null;

    /**
     * ��� model ������ֵ
     *
     * @return the model
     * @since 2017��7��20��
     * @author Administrator
     */
    public AbstractUIAppModel getModel() {
        return this.model;
    }

    /**
     * ���� model ������ֵ
     *
     * @param model the model to set
     * @since 2017��7��20��
     * @author Administrator
     */
    public void setModel(AbstractUIAppModel model) {
        this.model = model;
    }

    /*
     * (non-Javadoc)
     * @see nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer#initQueryConditionDLG(nc.ui.pubapp.uif2app.query2.
     * QueryConditionDLGDelegator)
     */
    @Override
    public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
        // TODO Auto-generated method stub
        // ������Ȩ�޵Ĺ���
        condDLGDelegator.registerNeedPermissionOrgFieldCode(CMMCommonConstCostPriceBase.PK_ORG);

        // // ���������ֶΣ����� ����1:��֯������2 ��Ԫ���ݶ�Ӧ·��
        condDLGDelegator.addRedundancyInfo(CostPriceHeadVO.PK_ORG, "itempks.pk_org");
        // // ���������ֶΣ����� ����1:���ţ�����2 ��Ԫ���ݶ�Ӧ·��
        condDLGDelegator.addRedundancyInfo(CostPriceHeadVO.PK_GROUP, "itempks.pk_group");
        //
        // // �Զ�������������,���óɲ���,����֯���� //modify by zhangchd
        // CMQMarAndDefFilter marAndDefQueryFilter = new CMQMarAndDefFilter(condDLGDelegator);
        // marAndDefQueryFilter.setItemPrefixs(new String[] {
        // "itempks.vbfree"// , "itemid1.vbdef"
        // });
        // marAndDefQueryFilter.addFilterMapsListeners();
        //
        // //
        // // �������ɸ������Դ���
        // MarAssistantDealer marAssisvb = new MarAssistantDealer();
        // marAssisvb.setPrefix("vbfree");
        // condDLGDelegator.addQueryCondVODealer(marAssisvb);
    }

    public void registerCriteriaEditorListener(ICriteriaChangedListener listener) {

    }

}
