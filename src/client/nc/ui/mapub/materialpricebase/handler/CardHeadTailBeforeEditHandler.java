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
 * ����Ʒ����������ɱ�
 * ��ͷ����β�༭ǰ�¼�
 * 
 * @since v6.3SP
 * @version 2013-11-5 ����07:48:55
 * @author zhangchd
 */
public class CardHeadTailBeforeEditHandler extends CMBasedocEventHandler implements
        IAppEventHandler<CardHeadTailBeforeEditEvent> {
    /**
     * ��ͷ����β�༭ǰ�¼�
     * (non-Javadoc)
     * 
     * @see nc.ui.pubapp.uif2app.event.IAppEventHandler#handleAppEvent(nc.ui.uif2.AppEvent)
     */
    @Override
    public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
        CMBasedocAbstractHandler handler = this.getHandler(e.getKey());

        if (handler != null) {
            // ִ����Ӧ�Ĵ������
            handler.beforeEdit(e);
        }
        else {
            e.setReturnValue(Boolean.TRUE);
        }
    }

    /**
     * ��ʼ��map
     * ��key=�����ֶ�����value=�ֶα༭�����ࣩ
     * (non-Javadoc)
     * 
     * @see nc.ui.bd.pub.handler.CMBasedocEventHandler#initMap()
     */
    @Override
    public void initMap() {
        // �����н��п�Ƭ����༭ǰ������ֶδ�����������MAP�У�key=�����ֶ�����value=�ֶα༭�����ࣩ
        Map<String, Class<?>> handlerMap = new HashMap<String, Class<?>>();

        // 1. ��ͷҵ�����ڱ���
        handlerMap.put(MaterialPriceBaseHeadVO.VPRICESOURCE, PriceSourcesHandler.class);
        // 2. ��Ч����
        handlerMap.put(MaterialPriceBaseHeadVO.DBEGINDATE, BeginDateHandler.class);
        // 3. ʧЧ����
        handlerMap.put(MaterialPriceBaseHeadVO.DENDDATE, EndDateHandler.class);

        this.putAllHandler(handlerMap);
    }
}
