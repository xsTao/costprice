/**
 *
 */
package nc.ui.mapub.materialpricebase.view;

import java.util.HashMap;
import java.util.Map;

import nc.bd.framework.base.CMValueCheck;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillTableBatchCopyListener;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.pub.VOStatus;

/**
 * 1
 *
 * @since v6.3
 * @version 2015年7月11日 上午10:51:44
 * @author shuzhan
 */
public class MaterialPriceBillTableBatchCopy implements IBillTableBatchCopyListener {

    BillCardPanel billCardPanel = null;

    @Override
    public void batchCopyBegin() {

    }

    @Override
    public void batchCopyEnd() {
        if (this.getBillCardPanel().getBodyPanel().getTable().getSelectedRowCount() <= 1) {
            return;
        }
        int[] rows = this.getBillCardPanel().getBodyPanel().getTable().getSelectedRows();
        int[] cols = this.getBillCardPanel().getBodyPanel().getTable().getSelectedColumns();
        Map<String, BillItem> billItemMap = new HashMap<String, BillItem>();
        for (int column : cols) {
            BillItem item =
                    this.getBillCardPanel().getBodyPanel().getTableModel().getBodyItems()[this.getBillCardPanel()
                            .getBodyPanel().getTable().convertColumnIndexToModel(column)];
            billItemMap.put(item.getKey(), item);
        }
        Object vpriceSourceNum =
                this.getBillCardPanel().getBodyValueAt(rows[0], MaterialPriceBaseBodyVO.VPRICESOURCENUM);
        for (int i = rows[1]; i < rows.length; i++) {
            if (billItemMap.containsKey(MaterialPriceBaseBodyVO.VPRICESOURCE)) {
                Object vpriceSourceNumNew =
                        this.getBillCardPanel().getBodyValueAt(i, MaterialPriceBaseBodyVO.VPRICESOURCENUM);
                if (CMValueCheck.isNotEmpty(vpriceSourceNumNew) && !vpriceSourceNum.equals(vpriceSourceNumNew)) {
                    this.getBillCardPanel().setBodyValueAt(VOStatus.UPDATED, i, "status");
                }
                this.getBillCardPanel().setBodyValueAt(vpriceSourceNum, i, MaterialPriceBaseBodyVO.VPRICESOURCENUM);
            }
        }
    }

    /**
     * 获得 billCardPanel 的属性值
     *
     * @return the billCardPanel
     * @since 2015年7月11日
     * @author shuzhan
     */
    public BillCardPanel getBillCardPanel() {
        return this.billCardPanel;
    }

    /**
     * 设置 billCardPanel 的属性值
     *
     * @param billCardPanel the billCardPanel to set
     * @since 2015年7月11日
     * @author shuzhan
     */
    public void setBillCardPanel(BillCardPanel billCardPanel) {
        this.billCardPanel = billCardPanel;
    }

}
