/**
 *
 */
package nc.ui.mapub.costpricebase.view;

import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

/**
 * ���ü۸�⿨Ƭ�����ʼ��
 *
 * @since v6.3
 * @version 2017��7��19�� ����2:20:53
 * @author
 */
public class CostPriceBillForm extends ShowUpableBillForm {

    private static final long serialVersionUID = 1911037728443216074L;

    //
    // @Override
    // protected void onEdit() {
    // super.onEdit();
    // }
    //
    // @Override
    // protected void onNotEdit() {
    // super.onNotEdit();
    // }

    @Override
    public void initUI() {
        super.initUI();
        CostPriceBillTableBatchCopy billTableBatchCopy = new CostPriceBillTableBatchCopy();
        billTableBatchCopy.setBillCardPanel(this.getBillCardPanel());
        /* this.getBillCardPanel().getBodyPanel().addBatchCopyListener(billTableBatchCopy); */
    }

}
