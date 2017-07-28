/**
 *
 */
package nc.ui.mapub.costpricebase.handler.head;

import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardPanelEvent;
import nc.vo.mapub.costpricebase.entity.CostPriceHeadVO;

/**
 * @since v6.3
 * @version 2017年7月28日 下午2:58:00
 * @author Administrator
 */
public class AnnualHandler extends CMBasedocAbstractHandler {

    /*
     * (non-Javadoc)
     * @see nc.ui.bd.pub.handler.CMBasedocAbstractHandler#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardPanelEvent)
     */
    @Override
    public void beforeEdit(CardPanelEvent e) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see nc.ui.bd.pub.handler.CMBasedocAbstractHandler#afterEdit(nc.ui.pubapp.uif2app.event.card.CardPanelEvent)
     */
    @Override
    public void afterEdit(CardPanelEvent e) {
        // TODO Auto-generated method stub
        CardHeadTailAfterEditEvent beforeEdit = (CardHeadTailAfterEditEvent) e;
        BillCardPanel panel = beforeEdit.getBillCardPanel();
        BillItem billItem = panel.getHeadItem(beforeEdit.getKey());
        if (panel.getHeadItem(CostPriceHeadVO.ANNUAL).getValueObject() != null) {
            /* panel.getHeadItem(CostPriceHeadVO.VPERIOD).setEdit(false); */
        }
        else {
            panel.getHeadItem(CostPriceHeadVO.VPERIOD).setEdit(true);
        }

    }

}
