/**
 *
 */
package nc.ui.mapub.costpricebase.handler;

import java.util.HashMap;
import java.util.Map;

import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.bd.pub.handler.CMBasedocEventHandler;
import nc.ui.mapub.costpricebase.handler.head.AnnualHandler;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.mapub.costpricebase.entity.CostPriceHeadVO;

/**
 * @since v6.3
 * @version 2017年7月27日 下午9:20:59
 * @author Administrator
 */
public class CardHeadTailAfterEditHandler extends CMBasedocEventHandler implements
        IAppEventHandler<CardHeadTailAfterEditEvent> {

    /*
     * (non-Javadoc)
     * @see nc.ui.pubapp.uif2app.event.IAppEventHandler#handleAppEvent(nc.ui.uif2.AppEvent)
     */
    @Override
    public void handleAppEvent(CardHeadTailAfterEditEvent e) {
        // TODO Auto-generated method stub
        CMBasedocAbstractHandler handler = this.getHandler(e.getKey());
        if (null != handler) {
            handler.afterEdit(e);
        }

    }

    /*
     * 初始化map
     * (non-Javadoc)
     * @see nc.ui.bd.pub.handler.CMBasedocEventHandler#initMap()
     */
    @Override
    public void initMap() {

        // TODO Auto-generated method stub
        Map<String, Class<?>> handlerMap = new HashMap<String, Class<?>>();
        handlerMap.put(CostPriceHeadVO.ANNUAL, AnnualHandler.class);
        this.putAllHandler(handlerMap);
    }

}
