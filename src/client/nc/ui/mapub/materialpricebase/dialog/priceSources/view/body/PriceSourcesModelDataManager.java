package nc.ui.mapub.materialpricebase.dialog.priceSources.view.body;

import nc.ui.mapub.materialpricebase.util.PricSourcesQueryService;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pubapp.uif2app.query2.model.ModelDataManager;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.UIState;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceSourcesAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceSourcesBodyVO;

public class PriceSourcesModelDataManager extends ModelDataManager {

    private BillForm dialogBillForm;

    private PricSourcesQueryService service;

    @Override
    public void initModel() {
        MaterialPriceSourcesAggVO[] datas = this.getService().getPriceSourcesByBodyBillForm();
        this.getModel().initModel(datas);

        if (!UIState.EDIT.equals(this.getModel().getUiState())) {
            this.getModel().setUiState(UIState.EDIT);
        }

        // Ԫ���ݹ�����
        BillScrollPane bsp = this.getDialogBillForm().getBillCardPanel().getBodyPanel();
        bsp.getTableModel().loadEditRelationItemValue(0, datas[0].getItemVO().length - 1,
                MaterialPriceSourcesBodyVO.PK_ORG);

    }

    public PricSourcesQueryService getService() {
        return this.service;
    }

    public void setService(PricSourcesQueryService service) {
        this.service = service;
    }

    public BillForm getDialogBillForm() {
        return this.dialogBillForm;
    }

    public void setDialogBillForm(BillForm dialogBillForm) {
        this.dialogBillForm = dialogBillForm;
    }

}
