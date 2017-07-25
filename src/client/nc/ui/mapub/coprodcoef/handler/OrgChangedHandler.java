package nc.ui.mapub.coprodcoef.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.util.BillPanelUtils;
import nc.vo.uif2.LoginContext;

/**
 * ��֯�л��¼�
 *
 * @since 6.0
 * @version 2014-10-11 ����12:45:47
 * @author zhangshyb
 */
public class OrgChangedHandler implements IAppEventHandler<OrgChangedEvent> {

    private BillForm billfrom;

    public OrgChangedHandler(BillForm bill) {
        this.billfrom = bill;
    }

    @Override
    public void handleAppEvent(OrgChangedEvent e) {
        if (this.billfrom.isEditable()) {
            // �ڱ༭״̬�£�����֯�л�ʱ����ս������ݣ��Զ��������У��������к�
            this.billfrom.addNew();
        }
        LoginContext context = this.billfrom.getModel().getContext();
        // ���в��չ���
        BillPanelUtils.setOrgForAllRef(this.billfrom.getBillCardPanel(), context);
        UIRefPane ref =
                (UIRefPane) this.billfrom.getBillCardPanel().getBodyItem("itembpks", "ccostcenterid").getComponent();
        ref.setPk_org(context.getPk_org());
    }

}
