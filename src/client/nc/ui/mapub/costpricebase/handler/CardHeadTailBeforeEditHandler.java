/**
 *
 */
package nc.ui.mapub.costpricebase.handler;

import java.util.HashMap;
import java.util.Map;

import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.bd.pub.handler.CMBasedocEventHandler;
import nc.ui.ecpubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;

/**
 * @since v6.3
 * @version 2017年7月27日 下午6:24:30
 * @author Administrator
 */
public class CardHeadTailBeforeEditHandler extends CMBasedocEventHandler implements
IAppEventHandler<CardHeadTailBeforeEditEvent> {

    /*
     * 表头、表尾编辑前事件
     * (non-Javadoc)
     * @see nc.ui.ecpubapp.uif2app.event.IAppEventHandler#handleAppEvent(nc.ui.uif2.AppEvent)
     */
    @Override
    public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
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
     * 初始化map
     * （key=处理字段名，value=字段编辑处理类）
     * (non-Javadoc)
     * @see nc.ui.bd.pub.handler.CMBasedocEventHandler#initMap()
     */
    @Override
    public void initMap() {
        // TODO Auto-generated method stub
        // 将所有进行卡片表体编辑前处理的字段处理类名放入MAP中（key=处理字段名，value=字段编辑处理类）
        Map<String, Class<?>> handlerMap = new HashMap<String, Class<?>>();

        // handlerMap.put(CostPriceHeadVO.CREATETIME, CreateTimeHandler.class);
        this.putAllHandler(handlerMap);
    }

}
