package nc.ui.mapub.costtype.eventhandler;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.resa.factor.chart.IFactorVersionQueryService;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.resa.factor.FactorChartVO;

/**
 * �ɱ����ͱ༭���¼�������Ҫ����Ĭ�ϳɱ����͵��߼���ϵ
 */

public class CostTypeListBodyAfterEventHandler implements IAppEventHandler<CardBodyAfterEditEvent> {

    @Override
    public void handleAppEvent(CardBodyAfterEditEvent e) {
        /**
         * Ҫ����ϵ�ı�ʱ��Ҫ�ر�Ͷ��ձ�����Ϊ��
         */
        if (CostTypeVO.PK_ELEMENTSYSTEM.equals(e.getKey())) {
            try {
                if (e.getContext().getPk_group().equals(e.getContext().getPk_org())) {
                    IFactorVersionQueryService queryService =
                            NCLocator.getInstance().lookup(IFactorVersionQueryService.class);
                    FactorChartVO factorChart =
                            queryService.getPolicyFactorChart(e.getContext().getPk_group(),
                                    String.valueOf(e.getValue()), null, new String[] {
                                        FactorChartVO.PK_FACTORCHART
                                    });
                    if (factorChart != null) {
                        e.getBillCardPanel().setBodyValueAt(factorChart.getPk_factorchart(), e.getRow(),
                                CostTypeVO.PK_FACTORCHART);
                    }
                    else {
                        e.getBillCardPanel().setBodyValueAt(null, e.getRow(), CostTypeVO.PK_FACTORCHART);
                    }
                }
                else {
                    e.getBillCardPanel().setBodyValueAt(null, e.getRow(), CostTypeVO.PK_FACTORCHART);
                }
                e.getBillCardPanel().setBodyValueAt(null, e.getRow(), CostTypeVO.PK_MATERIALDOCVIEW);
                e.getBillCardPanel().getBillModel().loadLoadRelationItemValue(e.getRow());
            }
            catch (BusinessException e1) {
                ExceptionUtils.wrappBusinessException(e1.toString());
            }
        }
        /**
         * Ҫ�ر�ı�ʱ�����ձ�����Ϊ��
         */
        if (CostTypeVO.PK_FACTORCHART.equals(e.getKey())) {
            e.getBillCardPanel().setBodyValueAt(null, e.getRow(), CostTypeVO.PK_MATERIALDOCVIEW);
        }
    }
}
