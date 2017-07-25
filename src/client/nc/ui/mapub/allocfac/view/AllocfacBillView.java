/**
 *
 */
package nc.ui.mapub.allocfac.view;

import nc.ui.cmpub.framework.view.CMBillListViewFacade;
import nc.ui.mapub.allocfac.util.AllocfacBillConst;
import nc.ui.pub.bill.BillListPanel;

/**
 * �б����
 * <p>
 * ��Ҫ�������б����Ŀ���ʾ�У���ʼ����
 */
public class AllocfacBillView extends CMBillListViewFacade {

    private static final long serialVersionUID = 5314786040446907291L;

    @Override
    public void initUI() {
        super.initUI();

        // �����ǰ�б���û�����ݣ���������ʾ�ɱ���������
        if (this.getBillListPanel().getHeadTable().getRowCount() <= 0) {
            AllocfacBillView.hideAllButCostCenterItem(this.getBillListPanel());
        }
        else {
            // �����ǰ�б��������ݣ�����ʾ��ѡ�������
            AllocfacBillView.hideAllBodyItem(this.getBillListPanel());
        }
    }

    /**
     * ���س��˳ɱ����ı�������ƺ�ϵ��֮���������
     *
     * @param billListPanel
     *            BillListPanel
     */
    public static void hideAllButCostCenterItem(BillListPanel billListPanel) {
        String tableCode = AllocfacBillConst.ALLOCFAC_TABLECODE;
        String[] initItemNames = AllocfacBillConst.SHOWITEMNAMES_INIT;
        AllocfacBillView.hideAllBodyItem(billListPanel);
        // ��ʾ�ɱ����ı������������
        AllocfacBillView.showBodyItem(billListPanel, tableCode, initItemNames);
    }

    /**
     * ���ñ������е���Ŀ������
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
     * ���ñ������е���Ŀ������
     *
     * @param billListPanel
     *            BillListPanel
     * @param tableCode
     *            ��ǩcode
     * @param itemNames
     *            ����������
     */
    public static void hideAllBodyItem(BillListPanel billListPanel, String tableCode, String[] itemNames) {
        for (String itemName : itemNames) {
            billListPanel.getBodyScrollPane(tableCode).hideTableCol(itemName);
        }
    }

    /**
     * ������Ŀ������ʾ
     *
     * @param billListPanel
     *            BillListPanel
     * @param tableCode
     *            �б�����
     * @param itemNames
     *            ������
     */
    public static void showBodyItem(BillListPanel billListPanel, String tableCode, String[] itemNames) {
        for (String itemName : itemNames) {
            billListPanel.getBodyScrollPane(tableCode).showTableCol(itemName);
        }
    }
}
