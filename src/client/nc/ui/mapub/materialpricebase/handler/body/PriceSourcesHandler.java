package nc.ui.mapub.materialpricebase.handler.body;

import nc.bd.framework.base.CMValueCheck;
import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardPanelEvent;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.mapub.materialpricebase.enumeration.PriceSourceEnum;

/**
 * ����۸���Դ
 * �༭���¼�
 * 
 * @since 6.36
 * @version 2014-11-28 ����12:22:03
 * @author zhangchd
 */
public class PriceSourcesHandler extends CMBasedocAbstractHandler {

    @Override
    public void beforeEdit(CardPanelEvent e) {
        CardBodyBeforeEditEvent ex = (CardBodyBeforeEditEvent) e;
        ex.setReturnValue(Boolean.TRUE);
    }

    @Override
    public void afterEdit(CardPanelEvent e) {
        CardBodyAfterEditEvent ex = (CardBodyAfterEditEvent) e;

        Object priceSourceObject =
                e.getBillCardPanel().getBodyValueAt(ex.getRow(), MaterialPriceBaseBodyVO.VPRICESOURCENUM);
        // ����۸���Դ�����ֹ�¼�룬��յ���
        if (CMValueCheck.isNotEmpty(priceSourceObject)) {
            if (!String.valueOf(PriceSourceEnum.MANUAL.toIntValue()).equals(priceSourceObject.toString())) {
                e.getBillCardPanel().setBodyValueAt(null, ex.getRow(), MaterialPriceBaseBodyVO.NPRICE);
                e.getBillCardPanel().setBodyValueAt(null, ex.getRow(), MaterialPriceBaseBodyVO.VPRICESOURCEREAL);
                e.getBillCardPanel().setBodyValueAt(null, ex.getRow(), MaterialPriceBaseBodyVO.VPRICESOURCEREALNUM);
            }
            else {
                e.getBillCardPanel().setBodyValueAt(null, ex.getRow(), MaterialPriceBaseBodyVO.VPRICESOURCEREAL);
                e.getBillCardPanel().setBodyValueAt(null, ex.getRow(), MaterialPriceBaseBodyVO.VPRICESOURCEREALNUM);
            }
        }
        else {
            e.getBillCardPanel().setBodyValueAt(null, ex.getRow(), MaterialPriceBaseBodyVO.NPRICE);
            e.getBillCardPanel().setBodyValueAt(null, ex.getRow(), MaterialPriceBaseBodyVO.VPRICESOURCEREAL);
            e.getBillCardPanel().setBodyValueAt(null, ex.getRow(), MaterialPriceBaseBodyVO.VPRICESOURCEREALNUM);
        }

    }
}
