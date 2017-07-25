package nc.ui.mapub.materialpricebase.handler.body;

import nc.bd.framework.base.CMValueCheck;
import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardPanelEvent;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.mapub.materialpricebase.enumeration.PriceSourceEnum;
import nc.vo.pub.lang.UFDouble;

/**
 * ���ϼ۸��
 * ���嵥�۱༭�¼�
 * 
 * @since 6.36
 * @version 2014-11-7 ����3:51:23
 * @author zhangchd
 */
public class NpriceHandler extends CMBasedocAbstractHandler {
    /**
     * �ֶα༭ǰ�����߼�
     */
    @Override
    public void beforeEdit(CardPanelEvent e) {
        CardBodyBeforeEditEvent ex = (CardBodyBeforeEditEvent) e;
        // ��ͷ�۸���Դ
        Object headPriceSourceObject =
                e.getBillCardPanel().getHeadItem(MaterialPriceBaseBodyVO.VPRICESOURCENUM).getValueObject();

        // ����۸���Դ
        Object priceSourceObject =
                e.getBillCardPanel().getBodyValueAt(ex.getRow(), MaterialPriceBaseBodyVO.VPRICESOURCENUM);
        // ����۸���Դ�����ֹ�¼�룬���۲��ɱ༭
        if (CMValueCheck.isNotEmpty(priceSourceObject)) {
            if (!String.valueOf(PriceSourceEnum.MANUAL.toIntValue()).equals(priceSourceObject.toString())) {
                ex.setReturnValue(Boolean.FALSE);
                return;
            }
        }
        else if (CMValueCheck.isNotEmpty(headPriceSourceObject)) {
            if (!String.valueOf(PriceSourceEnum.MANUAL.toIntValue()).equals(headPriceSourceObject.toString())) {
                ex.setReturnValue(Boolean.FALSE);
                return;
            }
        }

        ex.setReturnValue(Boolean.TRUE);
    }

    /**
     * �ֶα༭�����߼�
     */
    @Override
    public void afterEdit(CardPanelEvent ex) {
        CardBodyAfterEditEvent e = (CardBodyAfterEditEvent) ex;
        BillItem priceItem = e.getBillCardPanel().getBodyItem(e.getTableCode(), e.getKey());
        if (priceItem == null) {
            return;
        }
        // ���۸�ת����UFDouble������
        UFDouble priceDouble = (UFDouble) priceItem.getValueObject();
        if (priceDouble != null && UFDouble.ZERO_DBL.compareTo(priceDouble) > 0) {
            e.getBillCardPanel().setBodyValueAt(null, e.getRow(), priceItem.getKey());

            ShowStatusBarMsgUtil.showErrorMsg("", CMMLangConstMaterialPriceBase.GET_ERRO_BODYITEMNPRICEZERO(),
                    e.getContext());

        }

    }

}
