package nc.ui.mapub.materialpricebase.handler.head;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import nc.bd.framework.base.CMValueCheck;
import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardPanelEvent;
import nc.vo.pub.lang.UFDate;

/**
 * 物料价格来源
 * 生效日期事件
 * 
 * @since 6.36
 * @version 2014-11-27 上午9:56:54
 * @author zhangchd
 */
public class BeginDateHandler extends CMBasedocAbstractHandler {

    @Override
    public void beforeEdit(CardPanelEvent ex) {
        CardHeadTailBeforeEditEvent e = (CardHeadTailBeforeEditEvent) ex;
        e.setReturnValue(Boolean.TRUE);
    }

    @Override
    public void afterEdit(CardPanelEvent ex) {
        CardHeadTailAfterEditEvent e = (CardHeadTailAfterEditEvent) ex;
        BillCardPanel cardPanel = e.getBillCardPanel();
        BillItem beginDateItem = cardPanel.getHeadItem(e.getKey());
        Object beginDateObject = beginDateItem.getValueObject();

        if (CMValueCheck.isNotEmpty(beginDateObject)) {
            UFDate beginDate = (UFDate) beginDateObject;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Calendar c = Calendar.getInstance();
            c.set(beginDate.getYear(), beginDate.getMonth() - 1, beginDate.getDay(), 0, 0, 0);

            cardPanel.setHeadItem(e.getKey(), df.format(c.getTime()));

        }

    }

}
