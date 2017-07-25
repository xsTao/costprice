/**
 * 
 */
package nc.ui.mapub.materialpricebase.dialog.pullPrice.handler;

import java.util.HashMap;
import java.util.Map;

import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.bd.pub.handler.CMBasedocEventHandler;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.mapub.materialpricebase.entity.MaterialPullPriceHeadVO;

/**
 * 取价对话框
 * 表头、表尾编辑前事件
 * 
 * @since v6.36
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

        // 1. 截止期间
        handlerMap.put(MaterialPullPriceHeadVO.CENDDATE, EndDate.class);
        // 2. 开始期间
        handlerMap.put(MaterialPullPriceHeadVO.CBEGINDATE, BeginDate.class);
        // 3. 期间方案
        handlerMap.put(MaterialPullPriceHeadVO.CPERIODSCHEME, CperiodScheme.class);
        // 4. 最新结存价的会计期间
        handlerMap.put(MaterialPullPriceHeadVO.CPERIOD, Cperiod.class);
        // 5. 成本类型
        handlerMap.put(MaterialPullPriceHeadVO.COSTTYPE, CostType.class);

        this.putAllHandler(handlerMap);
    }
}
