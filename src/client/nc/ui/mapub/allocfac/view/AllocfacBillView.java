/**
 *
 */
package nc.ui.mapub.allocfac.view;

import nc.ui.cmpub.framework.view.CMBillListViewFacade;
import nc.ui.mapub.allocfac.util.AllocfacBillConst;
import nc.ui.pub.bill.BillListPanel;

/**
 * 列表界面
 * <p>
 * 主要设置了列表界面的可显示列（初始化）
 */
public class AllocfacBillView extends CMBillListViewFacade {

    private static final long serialVersionUID = 5314786040446907291L;

    @Override
    public void initUI() {
        super.initUI();

        // 如果当前列表内没有数据，则设置显示成本中心属性
        if (this.getBillListPanel().getHeadTable().getRowCount() <= 0) {
            AllocfacBillView.hideAllButCostCenterItem(this.getBillListPanel());
        }
        else {
            // 如果当前列表内有数据，则显示所选择的数据
            AllocfacBillView.hideAllBodyItem(this.getBillListPanel());
        }
    }

    /**
     * 隐藏除了成本中心编码和名称和系数之外的所有列
     *
     * @param billListPanel
     *            BillListPanel
     */
    public static void hideAllButCostCenterItem(BillListPanel billListPanel) {
        String tableCode = AllocfacBillConst.ALLOCFAC_TABLECODE;
        String[] initItemNames = AllocfacBillConst.SHOWITEMNAMES_INIT;
        AllocfacBillView.hideAllBodyItem(billListPanel);
        // 显示成本中心编码和名称两列
        AllocfacBillView.showBodyItem(billListPanel, tableCode, initItemNames);
    }

    /**
     * 设置标题所有的项目都隐藏
     *
     * @param billListPanel
     *            BillListPanel
     */
    public static void hideAllBodyItem(BillListPanel billListPanel) {
        String tableCode = AllocfacBillConst.ALLOCFAC_TABLECODE;
        String[] itemNames = AllocfacBillConst.HIDEITEMNAMES;
        AllocfacBillView.hideAllBodyItem(billListPanel, tableCode, itemNames);
    }

    /**
     * 设置标题所有的项目都隐藏
     *
     * @param billListPanel
     *            BillListPanel
     * @param tableCode
     *            标签code
     * @param itemNames
     *            隐藏列名称
     */
    public static void hideAllBodyItem(BillListPanel billListPanel, String tableCode, String[] itemNames) {
        for (String itemName : itemNames) {
            billListPanel.getBodyScrollPane(tableCode).hideTableCol(itemName);
        }
    }

    /**
     * 设置项目可以显示
     *
     * @param billListPanel
     *            BillListPanel
     * @param tableCode
     *            列表名称
     * @param itemNames
     *            列名称
     */
    public static void showBodyItem(BillListPanel billListPanel, String tableCode, String[] itemNames) {
        for (String itemName : itemNames) {
            billListPanel.getBodyScrollPane(tableCode).showTableCol(itemName);
        }
    }
}
