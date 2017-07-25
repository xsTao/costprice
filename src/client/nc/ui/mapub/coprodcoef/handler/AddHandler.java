package nc.ui.mapub.coprodcoef.handler;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.vo.mapub.coprodcoef.entity.enumeration.BillStatusEnum;
import nc.vo.pub.lang.UFDate;

/**
 * @since 6.0
 * @version 2014-10-11 ����12:28:30
 * @author zhangshyb
 */
public class AddHandler implements IAppEventHandler<AddEvent> {

    @Override
    public void handleAppEvent(AddEvent e) {
        String pk_group = e.getContext().getPk_group();
        String pk_org = e.getContext().getPk_org();
        BillCardPanel panel = e.getBillForm().getBillCardPanel();

        // ���ñ�ͷĬ��ֵ?
        panel.setHeadItem("pk_group", pk_group); // ����
        panel.setHeadItem("pk_org", pk_org); // ��֯?
        // ����״̬??
        panel.setHeadItem("ibillstatus", BillStatusEnum.FREE);
        panel.setHeadItem("creationtime", new UFDate()); // ��������
        // e.getBillForm().getBillCardPanel().getHeadItem(CoprodcoefHeadVO.PK_FACTORCHART).setEdit(false);
        // e.getBillForm().getBillCardPanel().getBodyItem(CoprodcoefItemVO.CELEMENTID).setEdit(false);
    }

}
