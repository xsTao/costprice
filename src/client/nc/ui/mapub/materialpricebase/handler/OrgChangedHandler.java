package nc.ui.mapub.materialpricebase.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.util.BillPanelUtils;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.uif2.LoginContext;

/**
 * ��֯�л��¼�
 * 
 * @since 6.36
 * @version 2014-11-7 ����4:07:20
 * @author zhangchd
 */
public class OrgChangedHandler implements IAppEventHandler<OrgChangedEvent> {
    private BillForm billForm;

    public OrgChangedHandler(BillForm billForm) {
        this.billForm = billForm;
    }

    @Override
    public void handleAppEvent(OrgChangedEvent e) {
        if (this.billForm != null && this.billForm.isEditable()) {
            // �ڱ༭״̬�£�����֯�л�ʱ����ս������ݣ��Զ��������У��������к�
            this.billForm.addNew();
        }
        this.billForm.getModel().getContext().setPk_org(e.getNewPkOrg());

        LoginContext context = this.billForm.getModel().getContext();
        // ���в��չ���
        BillPanelUtils.setOrgForAllRef(this.billForm.getBillCardPanel(), context);

        ShowStatusBarMsgUtil.showStatusBarMsg(" ", context);
    }
}
