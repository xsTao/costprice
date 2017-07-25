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
 * 单据表体字段编辑后事件
 *
 * @since 6.0
 * @version 2014-10-11 下午12:29:59
 * @author zhangshyb
 */
public class CardBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent> {

    /**
     * 记录编辑和增加的行号
     */
    // private int[] editAndAddRows;

    @Override
    public void handleAppEvent(CardBodyAfterEditEvent e) {
        String key = e.getKey();

        // String aa = e.getTableCode();
        // 相对系数变化时必须>=0
        if (key.equals(CoprodcoefItemVO.NRELCOEFFICIENT)) {
            BillItem numItem = e.getBillCardPanel().getBodyItem(e.getTableCode(), e.getKey());
            if (numItem == null) {
                return;
            }
            this.bodyItemNotZeroValidate(e.getBillCardPanel(), numItem, e.getRow());
        }
        else if (key.equals(CoprodcoefItemVO.IPRODUCTTYPE/* .PRODUCTTYPE 2014-10-11 */)) {// 根据相对系数变化带默认值
            String ptType =
                    e.getBillCardPanel().getBodyValueAt(e.getRow(), CoprodcoefItemVO.IPRODUCTTYPE/*
                                                                                                  * .PRODUCTTYPE
                                                                                                  * 2014-10-11
                                                                                                  */).toString();
            Object relcoefficient =
                    e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), CoprodcoefItemVO.NRELCOEFFICIENT);
            if (CMBasedocValueCheck.isEmpty(relcoefficient)) {
                if (ptType.equals("1") || ptType.equals("2"))// 1主2 联
                {
                    // 设置相对系数1
                    e.getBillCardPanel().setBodyValueAt(1, e.getRow(), CoprodcoefItemVO.NRELCOEFFICIENT);
                }
                else if (ptType.equals("3"))// 副
                {
                    e.getBillCardPanel().setBodyValueAt(0, e.getRow(), CoprodcoefItemVO.NRELCOEFFICIENT);
                }
            }
        }
        else if (key.equals(CoprodcoefItemVO.CMATERIALID)) {// 物料多选
            RefMoreSelectedUtils utils = new RefMoreSelectedUtils(e.getBillCardPanel());
            // true-新增行，false-复制行
            utils.refMoreSelected(e.getRow(), e.getKey(), false);
        }
        // 成本中心
        else if (key.equals(CoprodcoefItemVO.CCOSTCENTERID)) {
            // 设置参照多选
            // RefMoreSelectedUtils utils = new RefMoreSelectedUtils((BatchBillTable) e.getBillCardPanel().getParent());
            RefMoreSelectedUtils utils = new RefMoreSelectedUtils(e.getBillCardPanel());
            // true-新增行，false-复制行
            utils.refMoreSelected(e.getRow(), e.getKey(), false);
        }
        // 核算要素
        else if (key.equals(CoprodcoefItemVO.CELEMENTID)) {
            // 设置参照多选
            // RefMoreSelectedUtils utils = new RefMoreSelectedUtils((BatchBillTable) e.getBillCardPanel().getParent());
            RefMoreSelectedUtils utils = new RefMoreSelectedUtils(e.getBillCardPanel());
            // true-新增行，false-复制行
            utils.refMoreSelected(e.getRow(), e.getKey(), false);
        }

    }

    /**
     * 表体字段的非零校验，如果为零，则清空输入的零，并给出提示信息。
     *
     * @param panel
     *            BillCardPanel
     * @param priceItem
     *            字段String
     * @param row
     *            row
     */
    public void bodyItemNotZeroValidate(BillCardPanel panel, BillItem priceItem, int row) {
        UFDouble priceDouble = (UFDouble) priceItem.getValueObject(); // 将相对系数转化成UFDouble型数据

        if (priceDouble != null && priceDouble.getDouble() < 0) {

            panel.setBodyValueAt(null, row, priceItem.getKey());

            // String erroMessage = String.format("表体字段单价不能为零！", priceItem.getName());

            // 提示信息为：表体字段(单价\费率)不能为零！
            // ShowStatusBarMsgUtil.showStatusBarMsg(erroMessage, this.getModel().getContext());

        }

    }

}
