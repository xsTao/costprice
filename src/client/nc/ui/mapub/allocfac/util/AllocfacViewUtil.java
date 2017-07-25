/**
 * 
 */
package nc.ui.mapub.allocfac.util;

import nc.ui.mapub.allocfac.util.AllocfacBillConst;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.allocfac.enumeration.AllocfacEnum;

/**
 * 分配系数工具类
 * <p>
 * 处理界面子表列的显示与隐藏的
 */
public class AllocfacViewUtil {

    /**
     * 如果界面为空，则设置表体列显示状态为初始状态，只显示成本中心列
     * 
     * @param nowBillCardPanel
     *            BillCardPanel
     * @param nowBillListPanel
     *            BillListPanel
     */
    public static void initBodyColumsVisible(BillCardPanel nowBillCardPanel, BillListPanel nowBillListPanel) {
        // 分配内容 初始化显示为“成本中心”,纪录修改
        String defaultIndex = AllocfacEnum.costcenter.getEnumValue().getValue();
        nowBillCardPanel.setHeadItem(AllocfacHeadVO.IALLOCTYPE, defaultIndex);

        String[] initItemNames = AllocfacBillConst.SHOWITEMNAMES_INIT;
        // 更新卡片界面的列显示情况
        AllocfacViewUtil.updateBillCardColsVisibleByAllocType(nowBillCardPanel, initItemNames);

        // 更新列表界面的列显示情况
        AllocfacViewUtil.updateBillListColsVisibleByAllocType(nowBillListPanel, initItemNames);
    }

    /**
     * 根据当前的分配类型设置卡片界面的列显示情况
     * 
     * @param nowBillCardPanel
     *            BillCardPanel
     * @param showColumKeys
     *            要显示的列keys
     */
    public static void updateBillCardColsVisibleByAllocType(BillCardPanel nowBillCardPanel, String[] showColumKeys) {
        // 首先隐藏所有列（除了系数列）
        String tableCode = AllocfacBillConst.ALLOCFAC_TABLECODE;
        String[] itemKeys = AllocfacBillConst.HIDEITEMNAMES;
        AllocfacViewUtil.setBillCardPanelBodyTableColumsVisible(nowBillCardPanel, tableCode, itemKeys, false);

        // 根据分配类型的选择，设置某些列可显示

        AllocfacViewUtil.setBillCardPanelBodyTableColumsVisible(nowBillCardPanel, tableCode, showColumKeys, true);
    }

    /**
     * 根据当前的分配类型设置列表界面的列显示情况
     * 
     * @param nowBillListPanel
     *            BillListPanel
     * @param showColumKeys
     *            要显示的列keys
     */
    public static void updateBillListColsVisibleByAllocType(BillListPanel nowBillListPanel, String[] showColumKeys) {
        // 首先隐藏所有列（除了系数列）
        String tableCode = AllocfacBillConst.ALLOCFAC_TABLECODE;
        String[] itemKeys = AllocfacBillConst.HIDEITEMNAMES;
        AllocfacViewUtil.setBillListPanelBodyTableColumsVisible(nowBillListPanel, tableCode, itemKeys, false);

        // 根据分配类型的选择，设置某些列可显示
        AllocfacViewUtil.setBillListPanelBodyTableColumsVisible(nowBillListPanel, tableCode, showColumKeys, true);
    }

    /**
     * 设置卡片界面表体的列显示或隐藏
     * 
     * @param billCardPanel
     *            卡片界面Panel
     * @param tableCode
     *            页签的code
     * @param itemKeys
     *            列的key
     * @param isVisible
     *            是否可见
     */
    public static void setBillCardPanelBodyTableColumsVisible(BillCardPanel billCardPanel, String tableCode,
            String[] itemKeys, boolean isVisible) {
        for (String itemKey : itemKeys) {
            if (isVisible) {
                billCardPanel.getBodyPanel(tableCode).showTableCol(itemKey);
            }
            else {
                billCardPanel.getBodyPanel(tableCode).hideTableCol(itemKey);
            }
        }
    }

    /**
     * 设置列表界面表体的列显示或隐藏
     * 
     * @param billListPanel
     *            列表界面Panel
     * @param tableCode
     *            页签的code
     * @param itemKeys
     *            列的key
     * @param isVisible
     *            是否可见
     */
    public static void setBillListPanelBodyTableColumsVisible(BillListPanel billListPanel, String tableCode,
            String[] itemKeys, boolean isVisible) {
        for (String itemKey : itemKeys) {
            if (isVisible) {
                billListPanel.getBodyScrollPane(tableCode).showTableCol(itemKey);
            }
            else {
                billListPanel.getBodyScrollPane(tableCode).hideTableCol(itemKey);
            }
        }
    }
}
