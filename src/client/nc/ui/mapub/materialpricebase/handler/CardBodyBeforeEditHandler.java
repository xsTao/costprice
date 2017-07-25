package nc.ui.mapub.materialpricebase.handler;

import java.util.HashMap;
import java.util.Map;

import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.bd.pub.handler.CMBasedocEventHandler;
import nc.ui.mapub.materialpricebase.handler.body.CmaterialidHandler;
import nc.ui.mapub.materialpricebase.handler.body.NpriceHandler;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;

/**
 * @since 6.36
 * @version 2014-11-7 下午4:06:53
 * @author zhangchd
 */
public class CardBodyBeforeEditHandler extends CMBasedocEventHandler implements
        IAppEventHandler<CardBodyBeforeEditEvent> {

    /**
     * 表体编辑前事件
     * (non-Javadoc)
     * 
     * @see nc.ui.pubapp.uif2app.event.IAppEventHandler#handleAppEvent(nc.ui.uif2.AppEvent)
     */
    @Override
    public void handleAppEvent(CardBodyBeforeEditEvent e) {
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
     * 初始化map（key=处理字段名，value=字段编辑处理类）
     * (non-Javadoc)
     * 
     * @see nc.ui.bd.pub.handler.CMBasedocEventHandler#initMap()
     */
    @Override
    public void initMap() {
        // 将所有进行卡片表体编辑前处理的字段处理类名放入MAP中（key=处理字段名，value=字段编辑处理类）
        Map<String, Class<?>> handlerMap = new HashMap<String, Class<?>>();

        handlerMap.put(MaterialPriceBaseBodyVO.CMATERIALID, CmaterialidHandler.class);

        handlerMap.put(MaterialPriceBaseBodyVO.NPRICE, NpriceHandler.class);

        this.putAllHandler(handlerMap);

    }

}
