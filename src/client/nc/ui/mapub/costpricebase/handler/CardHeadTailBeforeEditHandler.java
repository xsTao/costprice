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
 * @version 2017��7��27�� ����6:24:30
 * @author Administrator
 */
public class CardHeadTailBeforeEditHandler extends CMBasedocEventHandler implements
IAppEventHandler<CardHeadTailBeforeEditEvent> {

    /*
     * ��ͷ����β�༭ǰ�¼�
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
     * ��ʼ��map
     * ��key=�����ֶ�����value=�ֶα༭�����ࣩ
     * (non-Javadoc)
     * @see nc.ui.bd.pub.handler.CMBasedocEventHandler#initMap()
     */
    @Override
    public void initMap() {
        // TODO Auto-generated method stub
        // �����н��п�Ƭ����༭ǰ������ֶδ�����������MAP�У�key=�����ֶ�����value=�ֶα༭�����ࣩ
        Map<String, Class<?>> handlerMap = new HashMap<String, Class<?>>();

        // handlerMap.put(CostPriceHeadVO.CREATETIME, CreateTimeHandler.class);
        this.putAllHandler(handlerMap);
    }

}
