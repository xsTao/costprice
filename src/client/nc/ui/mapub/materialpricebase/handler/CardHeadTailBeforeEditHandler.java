/**
 * 
 */
package nc.ui.mapub.materialpricebase.handler;

import java.util.HashMap;
import java.util.Map;

import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.bd.pub.handler.CMBasedocEventHandler;
import nc.ui.mapub.materialpricebase.handler.head.BeginDateHandler;
import nc.ui.mapub.materialpricebase.handler.head.EndDateHandler;
import nc.ui.mapub.materialpricebase.handler.head.PriceSourcesHandler;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;

/**
 * 产成品其他入库分项成本
 * 表头、表尾编辑前事件
 * 
 * @since v6.3SP
 * @version 2013-11-5 下午07:48:55
 * @author zhangchd
 */
public class CardHeadTailBeforeEditHandler extends CMBasedocEventHandler implements
        IAppEventHandler<CardHeadTailBeforeEditEvent> {
    /**
     * 表头、表尾编辑前事件
     * (non-Javadoc)
     * 
     * @see nc.ui.pubapp.uif2app.event.IAppEventHandler#handleAppEvent(nc.ui.uif2.AppEvent)
     */
    @Override
    public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
        CMBasedocAbstractHandler handler = this.getHandler(e.getKey());

        if (handler != null) {
            // 执行相应的处理过程
            handler.beforeEdit(e);
        }
        else {
            e.setReturnValue(Boolean.TRUE);
        }
    }

    /**
     * 初始化map
     * （key=处理字段名，value=字段编辑处理类）
     * (non-Javadoc)
     * 
     * @see nc.ui.bd.pub.handler.CMBasedocEventHandler#initMap()
     */
    @Override
    public void initMap() {
        // 将所有进行卡片表体编辑前处理的字段处理类名放入MAP中（key=处理字段名，value=字段编辑处理类）
        Map<String, Class<?>> handlerMap = new HashMap<String, Class<?>>();

        // 1. 表头业务日期编码
        handlerMap.put(MaterialPriceBaseHeadVO.VPRICESOURCE, PriceSourcesHandler.class);
        // 2. 生效日期
        handlerMap.put(MaterialPriceBaseHeadVO.DBEGINDATE, BeginDateHandler.class);
        // 3. 失效日期
        handlerMap.put(MaterialPriceBaseHeadVO.DENDDATE, EndDateHandler.class);

        this.putAllHandler(handlerMap);
    }
}
