package nc.ui.mapub.materialpricebase.action;

import java.awt.event.ActionEvent;

import nc.ui.mapub.materialpricebase.serviceproxy.MaterialPriceBaseMaintainProxy;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;

/**
 * ���ϼ۸��ȡ�۰�ť
 *
 * @since 6.0
 * @version 2014-10-12 ����6:31:02
 * @author baoxina
 */
public class MaterialPriceBaseTakePriceAction extends NCAction {

    private static final long serialVersionUID = 4636899460178937750L;

    private AbstractAppModel model;

    private ShowUpableBillListView listView;

    private BillForm billFormEditor;

    private MaterialPriceBaseMaintainProxy service;

    public MaterialPriceBaseMaintainProxy getService() {
        return this.service;
    }

    public void setService(MaterialPriceBaseMaintainProxy service) {
        this.service = service;
    }

    public ShowUpableBillListView getListView() {
        return this.listView;
    }

    public void setListView(ShowUpableBillListView listView) {
        this.listView = listView;
    }

    public BillForm getBillFormEditor() {
        return this.billFormEditor;
    }

    public void setBillFormEditor(BillForm billFormEditor) {
        this.billFormEditor = billFormEditor;
    }

    public AbstractAppModel getModel() {
        return this.model;
    }

    public void setModel(AbstractAppModel model) {
        this.model = model;
        model.addAppEventListener(this);
    }

    public MaterialPriceBaseTakePriceAction() {
        super();
        // modify by zhangchd
        // CMActionInitializer.initializeAction(this, "TAKEPRICR");
    }

    @Override
    public void doAction(ActionEvent e) throws Exception {
        Object[] value = ((BillManageModel) this.model).getSelectedOperaDatas();
        IBill[] clientVOs = new IBill[value.length];
        for (int i = 0; i < value.length; i++) {
            clientVOs[i] = (IBill) value[i];
        }
        IBill[] afterUpdateVOs = null;
        if (this.getService() == null) {
            throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                    "03810006-0314")/*
                     * service����Ϊ�ա�
                     */);
        }
        afterUpdateVOs = this.getService().takePrice(clientVOs);
        // clientVOsΪ�����ϵ����ݣ�afterUpdateVOsΪ��̨���صĲ�������
        new ClientBillCombinServer<IBill>().combine(clientVOs, afterUpdateVOs);
        // �����ǲο�differentVOSaveAction����
        this.getModel().directlyUpdate(clientVOs);

    }

    @Override
    protected boolean isActionEnable() {
        return this.model.getUiState() == UIState.NOT_EDIT && this.model.getSelectedData() != null;
    }
}
