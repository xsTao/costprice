package nc.ui.mapub.materialpricebase.dialog.pullPrice.handler;

import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardPanelEvent;
import nc.vo.mapub.materialpricebase.entity.MaterialPullPriceHeadVO;

/**
 * 取价对话框
 * 会计期间方案
 * 
 * @since 6.36
 * @version 2014-12-3 下午4:01:49
 * @author zhangchd
 */
public class CperiodScheme extends CMBasedocAbstractHandler {

    @Override
    public void beforeEdit(CardPanelEvent ex) {
        CardHeadTailBeforeEditEvent e = (CardHeadTailBeforeEditEvent) ex;
        e.setReturnValue(Boolean.TRUE);
    }

    @Override
    public void afterEdit(CardPanelEvent e) {
        CardHeadTailAfterEditEvent ex = (CardHeadTailAfterEditEvent) e;
        BillCardPanel cardPanel = ex.getBillCardPanel();
        cardPanel.setHeadItem(MaterialPullPriceHeadVO.CBEGINDATE, null);
        cardPanel.setHeadItem(MaterialPullPriceHeadVO.CENDDATE, null);
        cardPanel.setHeadItem(MaterialPullPriceHeadVO.CPERIOD, null);
    }

}
