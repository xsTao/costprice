/**
 *
 */
package nc.ui.mapub.costpricebase.handler;

import nc.ui.er.expensetype.view.BillPanelUtils;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.uif2.LoginContext;

/**
 * @since v6.3
 * @version 2017��7��19�� ����4:03:57
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
            // �ڱ༭״̬�£�����֯�л�ʱ����ս������ݣ��Զ��������У��������к�
            this.billForm.addNew();
        }
        // ��ȡ������
        LoginContext context = this.billForm.getModel().getContext();
        context.setPk_org(e.getNewPkOrg()); // �����µ�org
        BillPanelUtils.setOrgForAllRef(this.billForm.getBillCardPanel(), context); // ���ò��չ���
        // ��ʾ״̬����ʾ��Ϣ
        ShowStatusBarMsgUtil.showStatusBarMsg("", context);

    }

    /**
     * ��� billFrom ������ֵ
     *
     * @return the billFrom
     * @since 2017��7��25��
     * @author Administrator
     */
    public BillForm getBillForm() {
        return this.billForm;
    }

    /**
     * ���� billFrom ������ֵ
     *
     * @param billFrom the billFrom to set
     * @since 2017��7��25��
     * @author Administrator
     */
    public void setBillFrom(BillForm billForm) {
        this.billForm = billForm;
    }

}
