/**
 *
 */
package nc.ui.mapub.costpricebase.handler;

import java.util.HashMap;
import java.util.Map;

import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.bd.pub.handler.CMBasedocEventHandler;
import nc.ui.mapub.costpricebase.handler.body.DpriceHandler;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.mapub.costpricebase.entity.CostPriceBodyVO;

/**
 * @since v6.3
 * @version 2017年8月8日 下午7:58:54
 * @author Administrator
 */
public class CardBodyAfterEditHandler extends CMBasedocEventHandler implements IAppEventHandler<CardBodyAfterEditEvent> {

    /*
     * (non-Javadoc)
     * @see nc.ui.bd.pub.handler.CMBasedocEventHandler#initMap()
     */
    @Override
    public void initMap() {

        // 将卡片编辑后需要处理的字段加入MAP中（key=处理字段名，value=字段编辑处理类）
        Map<String, Class<?>> handlerMap = new HashMap<String, Class<?>>();
        handlerMap.put(CostPriceBodyVO.DPRICE, DpriceHandler.class);

        this.putAllHandler(handlerMap);

    }

    /*
     * (non-Javadoc)
     * @see nc.ui.pubapp.uif2app.event.IAppEventHandler#handleAppEvent(nc.ui.uif2.AppEvent)
     */
    @Override
    public void handleAppEvent(CardBodyAfterEditEvent e) {
        // TODO Auto-generated method stub
        CMBasedocAbstractHandler handler = this.getHandler(e.getKey());
        if (null != handler) {
            // 进行编辑后处理
            handler.afterEdit(e);
        }

    }

}
