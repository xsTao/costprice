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
 * ȡ�۶Ի���
 * ��ͷ����β�༭ǰ�¼�
 * 
 * @since v6.36
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

        // 1. ��ֹ�ڼ�
        handlerMap.put(MaterialPullPriceHeadVO.CENDDATE, EndDate.class);
        // 2. ��ʼ�ڼ�
        handlerMap.put(MaterialPullPriceHeadVO.CBEGINDATE, BeginDate.class);
        // 3. �ڼ䷽��
        handlerMap.put(MaterialPullPriceHeadVO.CPERIODSCHEME, CperiodScheme.class);
        // 4. ���½��۵Ļ���ڼ�
        handlerMap.put(MaterialPullPriceHeadVO.CPERIOD, Cperiod.class);
        // 5. �ɱ�����
        handlerMap.put(MaterialPullPriceHeadVO.COSTTYPE, CostType.class);

        this.putAllHandler(handlerMap);
    }
}
