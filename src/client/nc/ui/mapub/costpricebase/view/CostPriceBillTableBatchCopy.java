/**
 *
 */
package nc.ui.mapub.costpricebase.view;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.IBillTableBatchCopyListener;

/**
 * @since v6.3
 * @version 2017��7��19�� ����5:13:05
 * @author
 */
public class CostPriceBillTableBatchCopy implements IBillTableBatchCopyListener {

    private BillCardPanel billCardPanel = null;

    /*
     * (non-Javadoc)
     * @see nc.ui.pub.bill.IBillTableBatchCopyListener#batchCopyBegin()
     */
    @Override
    public void batchCopyBegin() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see nc.ui.pub.bill.IBillTableBatchCopyListener#batchCopyEnd()
     */
    @Override
    public void batchCopyEnd() {
        // TODO Auto-generated method stub

    }

    /**
     * ��� billCardPanel ������ֵ
     *
     * @return the billCardPanel
     * @since 2017��7��19��
     * @author Administrator
     */
    public BillCardPanel getBillCardPanel() {
        return this.billCardPanel;
    }

    /**
     * ���� billCardPanel ������ֵ
     *
     * @param billCardPanel the billCardPanel to set
     * @since 2017��7��19��
     * @author Administrator
     */
    public void setBillCardPanel(BillCardPanel billCardPanel) {
        this.billCardPanel = billCardPanel;
    }

}
