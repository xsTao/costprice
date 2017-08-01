/**
 *
 */
package nc.ui.mapub.costpricebase.handler;

import nc.ui.cmpub.business.util.CMBillPanelUtils;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.mapub.costpricebase.entity.CostPriceBodyVO;
import nc.vo.uif2.LoginContext;

/**
 * @since v6.3
 * @version 2017年7月19日 下午4:03:57
 * @author Administrator
 */
public class OrgChangedHandler implements IAppEventHandler<OrgChangedEvent> {

    private BillForm billForm;

    /*
     * (non-Javadoc)
     * @see nc.ui.pubapp.uif2app.event.IAppEventHandler#handleAppEvent(nc.ui.uif2.AppEvent)
     */
    public OrgChangedHandler(BillForm billform) {
        this.billForm = billform;
    }

    @Override
    public void handleAppEvent(OrgChangedEvent e) {
        // TODO Auto-generated method stub
        if (null != this.billForm && this.billForm.isEnabled()) {
            // 在编辑状态下，主组织切换时，清空界面数据，自动表体增行，并设置行号
            this.billForm.addNew();
        }
        // 获取上下文
        LoginContext context = this.billForm.getModel().getContext();
        // if (null != e.getNewPkOrg()) {
        // context.setPk_org(e.getNewPkOrg()); // 设置新的org
        // }
        context.setPk_org(e.getNewPkOrg());
        // BillPanelUtils.setOrgForAllRef(this.billForm.getBillCardPanel(), context); // 设置参照过滤
        CMBillPanelUtils.setOrgForAllRef(this.billForm.getBillCardPanel(), context, new String[] {
            CostPriceBodyVO.CELEMENTID
        });
        // 显示状态栏提示信息
        ShowStatusBarMsgUtil.showStatusBarMsg("", context);

    }

    /**
     * 获得 billFrom 的属性值
     *
     * @return the billFrom
     * @since 2017年7月25日
     * @author Administrator
     */
    public BillForm getBillForm() {
        return this.billForm;
    }

    /**
     * 设置 billFrom 的属性值
     *
     * @param billFrom the billFrom to set
     * @since 2017年7月25日
     * @author Administrator
     */
    public void setBillFrom(BillForm billForm) {
        this.billForm = billForm;
    }

}
