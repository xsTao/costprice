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
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;

/**
 * ����Ʒ����������ɱ�
 * ��ͷ����β�༭���¼�
 * 
 * @since v6.3SP
 * @version 2013-11-5 ����07:12:17
 * @author zhangchd
 */
public class CardHeadTailAfterEditHandler extends CMBasedocEventHandler implements
        IAppEventHandler<CardHeadTailAfterEditEvent> {
    /**
     * ��ͷ����β�༭���¼�����
     * (non-Javadoc)
     * 
     * @see nc.ui.pubapp.uif2app.event.IAppEventHandler#handleAppEvent(nc.ui.uif2.AppEvent)
     */
    @Override
    public void handleAppEvent(CardHeadTailAfterEditEvent e) {
        CMBasedocAbstractHandler handler = this.getHandler(e.getKey());

        if (handler != null) {
            // ִ����Ӧ�Ĵ������
            handler.afterEdit(e);
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
        // 4. �۸���Դ
        handlerMap.put(MaterialPriceBaseHeadVO.VPRICESOURCE, PriceSourcesHandler.class);

        this.putAllHandler(handlerMap);
    }

}
