package nc.ui.mapub.coprodcoef.handler;

import nc.bd.framework.util.CMBasedocValueCheck;
import nc.ui.bd.business.ref.RefMoreSelectedUtils;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefItemVO;
import nc.vo.pub.lang.UFDouble;

/**
 * ���ݱ����ֶα༭���¼�
 *
 * @since 6.0
 * @version 2014-10-11 ����12:29:59
 * @author zhangshyb
 */
public class CardBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent> {

    /**
     * ��¼�༭�����ӵ��к�
     */
    // private int[] editAndAddRows;

    @Override
    public void handleAppEvent(CardBodyAfterEditEvent e) {
        String key = e.getKey();

        // String aa = e.getTableCode();
        // ���ϵ���仯ʱ����>=0
        if (key.equals(CoprodcoefItemVO.NRELCOEFFICIENT)) {
            BillItem numItem = e.getBillCardPanel().getBodyItem(e.getTableCode(), e.getKey());
            if (numItem == null) {
                return;
            }
            this.bodyItemNotZeroValidate(e.getBillCardPanel(), numItem, e.getRow());
        }
        else if (key.equals(CoprodcoefItemVO.IPRODUCTTYPE/* .PRODUCTTYPE 2014-10-11 */)) {// �������ϵ���仯��Ĭ��ֵ
            String ptType =
                    e.getBillCardPanel().getBodyValueAt(e.getRow(), CoprodcoefItemVO.IPRODUCTTYPE/*
                                                                                                  * .PRODUCTTYPE
                                                                                                  * 2014-10-11
                                                                                                  */).toString();
            Object relcoefficient =
                    e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), CoprodcoefItemVO.NRELCOEFFICIENT);
            if (CMBasedocValueCheck.isEmpty(relcoefficient)) {
                if (ptType.equals("1") || ptType.equals("2"))// 1��2 ��
                {
                    // �������ϵ��1
                    e.getBillCardPanel().setBodyValueAt(1, e.getRow(), CoprodcoefItemVO.NRELCOEFFICIENT);
                }
                else if (ptType.equals("3"))// ��
                {
                    e.getBillCardPanel().setBodyValueAt(0, e.getRow(), CoprodcoefItemVO.NRELCOEFFICIENT);
                }
            }
        }
        else if (key.equals(CoprodcoefItemVO.CMATERIALID)) {// ���϶�ѡ
            RefMoreSelectedUtils utils = new RefMoreSelectedUtils(e.getBillCardPanel());
            // true-�����У�false-������
            utils.refMoreSelected(e.getRow(), e.getKey(), false);
        }
        // �ɱ�����
        else if (key.equals(CoprodcoefItemVO.CCOSTCENTERID)) {
            // ���ò��ն�ѡ
            // RefMoreSelectedUtils utils = new RefMoreSelectedUtils((BatchBillTable) e.getBillCardPanel().getParent());
            RefMoreSelectedUtils utils = new RefMoreSelectedUtils(e.getBillCardPanel());
            // true-�����У�false-������
            utils.refMoreSelected(e.getRow(), e.getKey(), false);
        }
        // ����Ҫ��
        else if (key.equals(CoprodcoefItemVO.CELEMENTID)) {
            // ���ò��ն�ѡ
            // RefMoreSelectedUtils utils = new RefMoreSelectedUtils((BatchBillTable) e.getBillCardPanel().getParent());
            RefMoreSelectedUtils utils = new RefMoreSelectedUtils(e.getBillCardPanel());
            // true-�����У�false-������
            utils.refMoreSelected(e.getRow(), e.getKey(), false);
        }

    }

    /**
     * �����ֶεķ���У�飬���Ϊ�㣬�����������㣬��������ʾ��Ϣ��
     *
     * @param panel
     *            BillCardPanel
     * @param priceItem
     *            �ֶ�String
     * @param row
     *            row
     */
    public void bodyItemNotZeroValidate(BillCardPanel panel, BillItem priceItem, int row) {
        UFDouble priceDouble = (UFDouble) priceItem.getValueObject(); // �����ϵ��ת����UFDouble������

        if (priceDouble != null && priceDouble.getDouble() < 0) {

            panel.setBodyValueAt(null, row, priceItem.getKey());

            // String erroMessage = String.format("�����ֶε��۲���Ϊ�㣡", priceItem.getName());

            // ��ʾ��ϢΪ�������ֶ�(����\����)����Ϊ�㣡
            // ShowStatusBarMsgUtil.showStatusBarMsg(erroMessage, this.getModel().getContext());

        }

    }

}
