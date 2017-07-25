package nc.ui.mapub.coprodcoef.view;

import nc.ui.mapub.coprodcoef.listener.BomIDValueChangedListener;
import nc.ui.mapub.coprodcoef.listener.MaterialValueChangedListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefHeadVO;

public class CoprodcoefBillForm extends ShowUpableBillForm {

    private static final long serialVersionUID = 7607382289003263301L;

    @Override
    public void initUI() {
        super.initUI();
        // 联产品增加值改变监听
        UIRefPane refPane =
                (UIRefPane) this.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CMATERIALID).getComponent();
        refPane.addValueChangedListener(new MaterialValueChangedListener(refPane, this, this.getModel()));
        // BOM版本增加值改变监听
        UIRefPane bomRefPane = (UIRefPane) this.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CBOMID).getComponent();
        bomRefPane.addValueChangedListener(new BomIDValueChangedListener(bomRefPane, this, this.getModel()));
    }

    @Override
    public void setValue(Object obj) {
        super.setValue(obj);
        BillItem item = this.getBillCardPanel().getHeadTailItem("cbomid");

        UIRefPane bomRefPane = (UIRefPane) item.getComponent();
        if (bomRefPane.getValueObj() == null) {
            bomRefPane.getUITextField().setValue(null);
        }
    }
}
