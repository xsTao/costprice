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
 * ����ϵ��������
 * <p>
 * ��������ӱ��е���ʾ�����ص�
 */
public class AllocfacViewUtil {

    /**
     * �������Ϊ�գ������ñ�������ʾ״̬Ϊ��ʼ״̬��ֻ��ʾ�ɱ�������
     * 
     * @param nowBillCardPanel
     *            BillCardPanel
     * @param nowBillListPanel
     *            BillListPanel
     */
    public static void initBodyColumsVisible(BillCardPanel nowBillCardPanel, BillListPanel nowBillListPanel) {
        // �������� ��ʼ����ʾΪ���ɱ����ġ�,��¼�޸�
        String defaultIndex = AllocfacEnum.costcenter.getEnumValue().getValue();
        nowBillCardPanel.setHeadItem(AllocfacHeadVO.IALLOCTYPE, defaultIndex);

        String[] initItemNames = AllocfacBillConst.SHOWITEMNAMES_INIT;
        // ���¿�Ƭ���������ʾ���
        AllocfacViewUtil.updateBillCardColsVisibleByAllocType(nowBillCardPanel, initItemNames);

        // �����б���������ʾ���
        AllocfacViewUtil.updateBillListColsVisibleByAllocType(nowBillListPanel, initItemNames);
    }

    /**
     * ���ݵ�ǰ�ķ����������ÿ�Ƭ���������ʾ���
     * 
     * @param nowBillCardPanel
     *            BillCardPanel
     * @param showColumKeys
     *            Ҫ��ʾ����keys
     */
    public static void updateBillCardColsVisibleByAllocType(BillCardPanel nowBillCardPanel, String[] showColumKeys) {
        // �������������У�����ϵ���У�
        String tableCode = AllocfacBillConst.ALLOCFAC_TABLECODE;
        String[] itemKeys = AllocfacBillConst.HIDEITEMNAMES;
        AllocfacViewUtil.setBillCardPanelBodyTableColumsVisible(nowBillCardPanel, tableCode, itemKeys, false);

        // ���ݷ������͵�ѡ������ĳЩ�п���ʾ

        AllocfacViewUtil.setBillCardPanelBodyTableColumsVisible(nowBillCardPanel, tableCode, showColumKeys, true);
    }

    /**
     * ���ݵ�ǰ�ķ������������б���������ʾ���
     * 
     * @param nowBillListPanel
     *            BillListPanel
     * @param showColumKeys
     *            Ҫ��ʾ����keys
     */
    public static void updateBillListColsVisibleByAllocType(BillListPanel nowBillListPanel, String[] showColumKeys) {
        // �������������У�����ϵ���У�
        String tableCode = AllocfacBillConst.ALLOCFAC_TABLECODE;
        String[] itemKeys = AllocfacBillConst.HIDEITEMNAMES;
        AllocfacViewUtil.setBillListPanelBodyTableColumsVisible(nowBillListPanel, tableCode, itemKeys, false);

        // ���ݷ������͵�ѡ������ĳЩ�п���ʾ
        AllocfacViewUtil.setBillListPanelBodyTableColumsVisible(nowBillListPanel, tableCode, showColumKeys, true);
    }

    /**
     * ���ÿ�Ƭ������������ʾ������
     * 
     * @param billCardPanel
     *            ��Ƭ����Panel
     * @param tableCode
     *            ҳǩ��code
     * @param itemKeys
     *            �е�key
     * @param isVisible
     *            �Ƿ�ɼ�
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
     * �����б������������ʾ������
     * 
     * @param billListPanel
     *            �б����Panel
     * @param tableCode
     *            ҳǩ��code
     * @param itemKeys
     *            �е�key
     * @param isVisible
     *            �Ƿ�ɼ�
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
