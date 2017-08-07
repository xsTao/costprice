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
 * 复制拦截器--
 *
 * @since v6.3
 * @version 2017年7月26日 下午3:28:44
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
        // 年度不为空，会计为空
        if (CMValueCheck.isNotEmpty(annualObj) && CMValueCheck.isEmpty(periodObj)) {
            annual.setEdit(true);
            period.setEdit(false);

        }
        // 年度为空，会计不为空
        else if (CMValueCheck.isEmpty(annualObj) && CMValueCheck.isNotEmpty(periodObj)) {
            annual.setEdit(false);
            period.setEdit(true);
        }
        // 年度为空，会计也为空or 年度不为空，会计也不为空
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
        // 获取当前登陆时间
        UFDate serverDate = AppUiContext.getInstance().getBusiDate();

        hvo.setVpricelibcode(null);
        hvo.setVpricelibname(null);
        // 设置空处理
        hvo.setModifier(null);
        hvo.setModifiedtime(null);
        hvo.setCreator(null);
        hvo.setCreatetime(null);

        // 设置默认值
        hvo.setPkGroup(context.getPk_group());
        hvo.setPkOrg(context.getPk_org());
        // 组织最新版本
        Map<String, String> orgVid = BDAdapter.getNewVIDSByOrgIDS(new String[] {
                context.getPk_org()
        });
        if (CMMapUtil.isNotEmpty(orgVid)) {
            hvo.setPkOrgV(orgVid.get(context.getPk_org()));
        }

    }

    /**
     * 获得 editor 的属性值
     *
     * @return the editor
     * @since 2017年8月2日
     * @author Administrator
     */
    public BillForm getEditor() {
        return this.editor;
    }

    /**
     * 设置 editor 的属性值
     *
     * @param editor the editor to set
     * @since 2017年8月2日
     * @author Administrator
     */
    public void setEditor(BillForm editor) {
        this.editor = editor;
    }

    /**
     * 获得 model 的属性值
     *
     * @return the model
     * @since 2017年8月2日
     * @author Administrator
     */
    public BillManageModel getModel() {
        return this.model;
    }

    /**
     * 设置 model 的属性值
     *
     * @param model the model to set
     * @since 2017年8月2日
     * @author Administrator
     */
    public void setModel(BillManageModel model) {
        this.model = model;
    }

}
