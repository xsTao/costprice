/**
 *
 */
package nc.ui.mapub.costpricebase.interceptor;

import java.util.Map;

import nc.bd.framework.base.CMMapUtil;
import nc.bd.framework.base.CMValueCheck;
import nc.cmpub.business.adapter.BDAdapter;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;
import nc.vo.mapub.costpricebase.entity.CostPriceHeadVO;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDate;
import nc.vo.uif2.LoginContext;

/**
 * ����������--
 *
 * @since v6.3
 * @version 2017��7��26�� ����3:28:44
 * @author Administrator
 */
public class CopyActionProcessor implements ICopyActionProcessor<CostPriceAggVO> {
    private BillForm editor;

    // BillCardPanel billCardPanel = ((ShowUpableBillForm) this.getEditor()).getBillCardPanel();

    private BillManageModel model;

    /*
     * (non-Javadoc)
     * @see
     * nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor#processVOAfterCopy(nc.vo.pubapp.pattern.model.entity.bill
     * .AbstractBill, nc.vo.uif2.LoginContext)
     */
    @Override
    public void processVOAfterCopy(CostPriceAggVO billVO, LoginContext context) {
        // TODO Auto-generated method stub
        this.processHeadVO(billVO, context);
        this.processBodyVO(billVO);

        BillCardPanel billCardPanel = ((ShowUpableBillForm) this.getEditor()).getBillCardPanel();
        BillItem annual = billCardPanel.getHeadItem(CostPriceHeadVO.ANNUAL);
        BillItem period = billCardPanel.getHeadItem(CostPriceHeadVO.VPERIOD);
        Object annualObj = annual.getValueObject();
        Object periodObj = period.getValueObject();
        // ��Ȳ�Ϊ�գ����Ϊ��
        if (CMValueCheck.isNotEmpty(annualObj) && CMValueCheck.isEmpty(periodObj)) {
            annual.setEdit(true);
            period.setEdit(false);

        }
        // ���Ϊ�գ���Ʋ�Ϊ��
        else if (CMValueCheck.isEmpty(annualObj) && CMValueCheck.isNotEmpty(periodObj)) {
            annual.setEdit(false);
            period.setEdit(true);
        }
        // ���Ϊ�գ����ҲΪ��or ��Ȳ�Ϊ�գ����Ҳ��Ϊ��
        else {
            annual.setEdit(true);
            period.setEdit(true);
        }

    }

    /**
     * @param context
     */
    private void processBodyVO(CostPriceAggVO vo) {
        // TODO Auto-generated method stub
        vo.getParent().setAttributeValue(vo.getMetaData().getParent().getPrimaryAttribute().getName(), null);
        vo.getParent().setAttributeValue("ts", null);
        for (IVOMeta meta : vo.getMetaData().getChildren()) {
            if (vo.getChildren(meta) == null) {
                continue;
            }
            for (ISuperVO childvo : vo.getChildren(meta)) {
                childvo.setAttributeValue(meta.getPrimaryAttribute().getName(), null);
                childvo.setAttributeValue("pk_group", null);
                childvo.setAttributeValue("pk_org", null);
            }
        }
    }

    /**
     * @param billVO
     * @param context
     */
    private void processHeadVO(CostPriceAggVO billVO, LoginContext context) {
        // TODO Auto-generated method stub

        CostPriceHeadVO hvo = billVO.getParentVO();
        // ��ȡ��ǰ��½ʱ��
        UFDate serverDate = AppUiContext.getInstance().getBusiDate();

        hvo.setVpricelibcode(null);
        hvo.setVpricelibname(null);
        // ���ÿմ���
        hvo.setModifier(null);
        hvo.setModifiedtime(null);
        hvo.setCreator(null);
        hvo.setCreatetime(null);

        // ����Ĭ��ֵ
        hvo.setPkGroup(context.getPk_group());
        hvo.setPkOrg(context.getPk_org());
        // ��֯���°汾
        Map<String, String> orgVid = BDAdapter.getNewVIDSByOrgIDS(new String[] {
                context.getPk_org()
        });
        if (CMMapUtil.isNotEmpty(orgVid)) {
            hvo.setPkOrgV(orgVid.get(context.getPk_org()));
        }

    }

    /**
     * ��� editor ������ֵ
     *
     * @return the editor
     * @since 2017��8��2��
     * @author Administrator
     */
    public BillForm getEditor() {
        return this.editor;
    }

    /**
     * ���� editor ������ֵ
     *
     * @param editor the editor to set
     * @since 2017��8��2��
     * @author Administrator
     */
    public void setEditor(BillForm editor) {
        this.editor = editor;
    }

    /**
     * ��� model ������ֵ
     *
     * @return the model
     * @since 2017��8��2��
     * @author Administrator
     */
    public BillManageModel getModel() {
        return this.model;
    }

    /**
     * ���� model ������ֵ
     *
     * @param model the model to set
     * @since 2017��8��2��
     * @author Administrator
     */
    public void setModel(BillManageModel model) {
        this.model = model;
    }

}
