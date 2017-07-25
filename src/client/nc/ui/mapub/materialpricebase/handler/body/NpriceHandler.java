package nc.ui.mapub.materialpricebase.handler.body;

import nc.bd.framework.base.CMValueCheck;
import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardPanelEvent;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.mapub.materialpricebase.enumeration.PriceSourceEnum;
import nc.vo.pub.lang.UFDouble;

/**
 * 材料价格库
 * 表体单价编辑事件
 * 
 * @since 6.36
 * @version 2014-11-7 下午3:51:23
 * @author zhangchd
 */
public class NpriceHandler extends CMBasedocAbstractHandler {
    /**
     * 字段编辑前处理逻辑
     */
    @Override
    public void beforeEdit(CardPanelEvent e) {
        CardBodyBeforeEditEvent ex = (CardBodyBeforeEditEvent) e;
        // 表头价格来源
        Object headPriceSourceObject =
                e.getBillCardPanel().getHeadItem(MaterialPriceBaseBodyVO.VPRICESOURCENUM).getValueObject();

        // 表体价格来源
        Object priceSourceObject =
                e.getBillCardPanel().getBodyValueAt(ex.getRow(), MaterialPriceBaseBodyVO.VPRICESOURCENUM);
        // 表体价格来源不是手工录入，单价不可编辑
        if (CMValueCheck.isNotEmpty(priceSourceObject)) {
            if (!String.valueOf(PriceSourceEnum.MANUAL.toIntValue()).equals(priceSourceObject.toString())) {
                ex.setReturnValue(Boolean.FALSE);
                return;
            }
        }
        else if (CMValueCheck.isNotEmpty(headPriceSourceObject)) {
            if (!String.valueOf(PriceSourceEnum.MANUAL.toIntValue()).equals(headPriceSourceObject.toString())) {
                ex.setReturnValue(Boolean.FALSE);
                return;
            }
        }

        ex.setReturnValue(Boolean.TRUE);
    }

    /**
     * 字段编辑后处理逻辑
     */
    @Override
    public void afterEdit(CardPanelEvent ex) {
        CardBodyAfterEditEvent e = (CardBodyAfterEditEvent) ex;
        BillItem priceItem = e.getBillCardPanel().getBodyItem(e.getTableCode(), e.getKey());
        if (priceItem == null) {
            return;
        }
        // 将价格转化成UFDouble型数据
        UFDouble priceDouble = (UFDouble) priceItem.getValueObject();
        if (priceDouble != null && UFDouble.ZERO_DBL.compareTo(priceDouble) > 0) {
            e.getBillCardPanel().setBodyValueAt(null, e.getRow(), priceItem.getKey());

            ShowStatusBarMsgUtil.showErrorMsg("", CMMLangConstMaterialPriceBase.GET_ERRO_BODYITEMNPRICEZERO(),
                    e.getContext());

        }

    }

}
