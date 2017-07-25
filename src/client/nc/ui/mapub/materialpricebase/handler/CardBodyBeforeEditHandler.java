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
 * @version 2014-11-7 ����4:06:53
 * @author zhangchd
 */
public class CardBodyBeforeEditHandler extends CMBasedocEventHandler implements
        IAppEventHandler<CardBodyBeforeEditEvent> {

    /**
     * ����༭ǰ�¼�
     * (non-Javadoc)
     * 
     * @see nc.ui.pubapp.uif2app.event.IAppEventHandler#handleAppEvent(nc.ui.uif2.AppEvent)
     */
    @Override
    public void handleAppEvent(CardBodyBeforeEditEvent e) {
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
     * ��ʼ��map��key=�����ֶ�����value=�ֶα༭�����ࣩ
     * (non-Javadoc)
     * 
     * @see nc.ui.bd.pub.handler.CMBasedocEventHandler#initMap()
     */
    @Override
    public void initMap() {
        // �����н��п�Ƭ����༭ǰ������ֶδ�����������MAP�У�key=�����ֶ�����value=�ֶα༭�����ࣩ
        Map<String, Class<?>> handlerMap = new HashMap<String, Class<?>>();

        handlerMap.put(MaterialPriceBaseBodyVO.CMATERIALID, CmaterialidHandler.class);

        handlerMap.put(MaterialPriceBaseBodyVO.NPRICE, NpriceHandler.class);

        this.putAllHandler(handlerMap);

    }

}
