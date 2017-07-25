package nc.ui.mapub.coprodcoef.handler;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.vo.mapub.coprodcoef.entity.enumeration.BillStatusEnum;
import nc.vo.pub.lang.UFDate;

/**
 * @since 6.0
 * @version 2014-10-11 下午12:28:30
 * @author zhangshyb
 */
public class AddHandler implements IAppEventHandler<AddEvent> {

    @Override
    public void handleAppEvent(AddEvent e) {
        String pk_group = e.getContext().getPk_group();
        String pk_org = e.getContext().getPk_org();
        BillCardPanel panel = e.getBillForm().getBillCardPanel();

        // 设置表头默认值?
        panel.setHeadItem("pk_group", pk_group); // 集团
        panel.setHeadItem("pk_org", pk_org); // 组织?
        // 单据状态??
        panel.setHeadItem("ibillstatus", BillStatusEnum.FREE);
        panel.setHeadItem("creationtime", new UFDate()); // 单据日期
        // e.getBillForm().getBillCardPanel().getHeadItem(CoprodcoefHeadVO.PK_FACTORCHART).setEdit(false);
        // e.getBillForm().getBillCardPanel().getBodyItem(CoprodcoefItemVO.CELEMENTID).setEdit(false);
    }

}
