package nc.ui.mapub.materialpricebase.dialog.pullPrice.view;

import nc.sfbase.client.ClientToolKit;
import nc.ui.cmpub.framework.view.GeneralDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;

/**
 * ���ϼ۸���Դ
 * ȡ�۶Ի���
 * 
 * @since 6.36
 * @version 2014-11-29 ����10:19:35
 * @author zhangchd
 */
public class PullPriceDialog extends GeneralDialog {

    private static final long serialVersionUID = -5112586485832571838L;

    private BillManageModel model;

    private BillForm dialogBillForm;

    /**
     * Ĭ�Ϲ��캯��
     */
    public PullPriceDialog() {
        super(ClientToolKit.getApplet());
        // super(context.getEntranceUI().getTopLevelAncestor());
    }

    @Override
    public void initUI() {
        super.initUI();

        this.setTitle(CMMLangConstMaterialPriceBase.getMSG30());

    }

    public BillManageModel getModel() {
        return this.model;
    }

    public void setModel(BillManageModel model) {
        this.model = model;
    }

    public BillForm getDialogBillForm() {
        return this.dialogBillForm;
    }

    public void setDialogBillForm(BillForm dialogBillForm) {
        this.dialogBillForm = dialogBillForm;
    }

}
