package nc.ui.mapub.driver.handler;

import nc.ui.mapub.driver.view.DriverBillForm;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListHeadDoubleClickEvent;
import nc.ui.uif2.model.BillManageModel;

public class DriverDoubleClickEventHandler implements IAppEventHandler<ListHeadDoubleClickEvent> {

    private BillManageModel model;

    private DriverBillForm billform;

    public BillManageModel getModel() {
        return this.model;
    }

    public void setModel(BillManageModel model) {
        this.model = model;
    }

    public DriverBillForm getBillform() {
        return this.billform;
    }

    public void setBillform(DriverBillForm billform) {
        this.billform = billform;
    }

    @Override
    public void handleAppEvent(ListHeadDoubleClickEvent e) {
        if (this.getModel().getSelectedData() == null && this.getBillform().getBillCardPanel().getBillData() != null) {
            this.getModel().setSelectedRow(0);
        }
    }
}
