/**
 *
 */
package nc.ui.mapub.costpricebase.view;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.IBillTableBatchCopyListener;

/**
 * @since v6.3
 * @version 2017年7月19日 下午5:13:05
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
     * 获得 billCardPanel 的属性值
     *
     * @return the billCardPanel
     * @since 2017年7月19日
     * @author Administrator
     */
    public BillCardPanel getBillCardPanel() {
        return this.billCardPanel;
    }

    /**
     * 设置 billCardPanel 的属性值
     *
     * @param billCardPanel the billCardPanel to set
     * @since 2017年7月19日
     * @author Administrator
     */
    public void setBillCardPanel(BillCardPanel billCardPanel) {
        this.billCardPanel = billCardPanel;
    }

}
