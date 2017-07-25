/**
 * 
 */
package nc.ui.mapub.allocfac.handler;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;

public class AddHandler implements IAppEventHandler<AddEvent> {
    @Override
    public void handleAppEvent(AddEvent e) {
        String pk_group = e.getContext().getPk_group();
        String pk_org = e.getContext().getPk_org();
        BillCardPanel panel = e.getBillForm().getBillCardPanel();
        // ��������֯Ĭ��ֵ
        panel.setHeadItem("pk_group", pk_group);
        panel.setHeadItem("pk_org", pk_org);
    }
}
