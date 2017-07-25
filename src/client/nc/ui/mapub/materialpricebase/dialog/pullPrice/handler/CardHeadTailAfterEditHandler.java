/**
 * 
 */
package nc.ui.mapub.materialpricebase.dialog.pullPrice.handler;

import java.util.HashMap;
import java.util.Map;

import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.bd.pub.handler.CMBasedocEventHandler;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.mapub.materialpricebase.entity.MaterialPullPriceHeadVO;

/**
 * ȡ�۶Ի���
 * ��ͷ����β�༭���¼�
 * 
 * @since v6.36
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
