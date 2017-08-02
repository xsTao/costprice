/**
 *
 */
package nc.ui.mapub.costpricebase.handler;

import java.util.HashMap;
import java.util.Map;

import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.bd.pub.handler.CMBasedocEventHandler;
import nc.ui.mapub.costpricebase.handler.body.CelementidHandler;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.mapub.costpricebase.entity.CostPriceBodyVO;

/**
 * @since v6.3
 * @version 2017年7月26日 下午8:16:59
 * @author Administrator
 */
public class CardBodyBeforeEditHandler extends CMBasedocEventHandler implements
IAppEventHandler<CardBodyBeforeEditEvent> {

    /*
     * (non-Javadoc)
     * @see nc.ui.ecpubapp.uif2app.event.IAppEventHandler#handleAppEvent(nc.ui.uif2.AppEvent)
     */
    @Override
    public void handleAppEvent(CardBodyBeforeEditEvent e) {
        // TODO Auto-generated method stub
        CMBasedocAbstractHandler handler = this.getHandler(e.getKey());
        if (null != handler) {
            handler.beforeEdit(e);
        }
        else {
            e.setReturnValue(Boolean.TRUE);
        }
    }

    /*
     * (non-Javadoc)
     * @see nc.ui.bd.pub.handler.CMBasedocEventHandler#initMap()
     */
    @Override
    public void initMap() {
        // TODO Auto-generated method stub
        Map<String, Class<?>> handlerMap = new HashMap<String, Class<?>>();
        handlerMap.put(CostPriceBodyVO.CELEMENTID, CelementidHandler.class);
        this.putAllHandler(handlerMap);

    }

}
