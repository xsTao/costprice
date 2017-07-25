package nc.ui.mapub.materialpricebase.handler.body;

import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardPanelEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;
import nc.vo.cmpub.framework.assistant.CMAssInfoItemVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;

/**
 * 材料价格库
 * 表体材料编码编辑事件
 * 
 * @since 6.36
 * @version 2014-11-7 下午3:48:56
 * @author zhangchd
 */
public class CmaterialidHandler extends CMBasedocAbstractHandler {

    /**
     * 表体材料编码编辑前事件
     */
    @Override
    public void beforeEdit(CardPanelEvent ex) {
        CardBodyBeforeEditEvent e = (CardBodyBeforeEditEvent) ex;

        // 设置支持多选
        BillItem item = e.getBillCardPanel().getBodyItem(e.getKey());
        UIRefPane refPanel = (UIRefPane) item.getComponent();
        refPanel.setMultiSelectedEnabled(true);

        e.setReturnValue(Boolean.TRUE);
    }

    /**
     * 字段编辑后处理逻辑
     */
    @Override
    public void afterEdit(CardPanelEvent e) {
        CardBodyAfterEditEvent ex = (CardBodyAfterEditEvent) e;

        BillCardPanel cardPanel = ex.getBillCardPanel();
        // 参照多选工具类
        RefMoreSelectedUtils utils = new RefMoreSelectedUtils(cardPanel);
        // 为true则新增行，为false则复制行
        int[] rows = utils.refMoreSelected(ex.getRow(), ex.getKey(), true);

        // 表头价格来源
        // BillItem vpricesourceItem = e.getBillCardPanel().getHeadItem(MaterialPriceBaseHeadVO.VPRICESOURCE);
        // BillItem vpricesourcenNumItem = e.getBillCardPanel().getHeadItem(MaterialPriceBaseHeadVO.VPRICESOURCENUM);

        for (int row : rows) {
            // String materialid =
            // null == ex.getBillCardPanel().getBodyValueAt(row, MaterialPriceBaseBodyVO.CMATERIALID) ? null
            // : String.valueOf(ex.getBillCardPanel().getBodyValueAt(row,
            // MaterialPriceBaseBodyVO.CMATERIALID));

            // 设置对应自由辅助属性为空 add by zhangchd V631EHP2 2013.12.27
            this.setAssValue(ex, row);

            // if (CMValueCheck.isNotEmpty(materialid)) {
            // if (vpricesourceItem.getValueObject() != null && vpricesourcenNumItem.getValueObject() != null) {
            // ex.getBillCardPanel().setBodyValueAt(vpricesourceItem.getValueObject().toString(), row,
            // MaterialPriceBaseBodyVO.VPRICESOURCE);
            //
            // ex.getBillCardPanel().setBodyValueAt(vpricesourcenNumItem.getValueObject().toString(), row,
            // MaterialPriceBaseBodyVO.VPRICESOURCENUM);
            // }
            //
            // }
            // else {
            // ex.getBillCardPanel().setBodyValueAt(PriceSourceEnum.MANUAL.getName(), row,
            // MaterialPriceBaseBodyVO.VPRICESOURCE);
            //
            // ex.getBillCardPanel().setBodyValueAt(Integer.valueOf(PriceSourceEnum.MANUAL.toIntValue()), row,
            // MaterialPriceBaseBodyVO.VPRICESOURCENUM);
            ex.getBillCardPanel().setBodyValueAt(null, row, MaterialPriceBaseBodyVO.VPRICESOURCE);
            ex.getBillCardPanel().setBodyValueAt(null, row, MaterialPriceBaseBodyVO.VPRICESOURCENUM);
            // }
            ex.getBillCardPanel().setBodyValueAt(null, row, MaterialPriceBaseBodyVO.NPRICE);
        }
    }

    /**
     * 设置对应自由辅助属性为空 add by zhangchd V631EHP2 2013.12.27
     * 
     * @param e
     * @param row
     */
    private void setAssValue(CardBodyAfterEditEvent e, int row) {
        String[] freeField =
                new String[] {
                    MaterialPriceBaseBodyVO.CCUSTOMERID, MaterialPriceBaseBodyVO.CPRODUCTORID,
                    MaterialPriceBaseBodyVO.CPROJECTID, MaterialPriceBaseBodyVO.CVENDORID, CMAssInfoItemVO.VBFREE1,
                    CMAssInfoItemVO.VBFREE2, CMAssInfoItemVO.VBFREE3, CMAssInfoItemVO.VBFREE4, CMAssInfoItemVO.VBFREE5,
                    CMAssInfoItemVO.VBFREE6, CMAssInfoItemVO.VBFREE7, CMAssInfoItemVO.VBFREE8, CMAssInfoItemVO.VBFREE9,
                    CMAssInfoItemVO.VBFREE10
                };
        for (String filed : freeField) {
            e.getBillCardPanel().setBodyValueAt(null, row, filed);
        }

    }
}
