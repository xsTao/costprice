/**
 *
 */
package nc.ui.mapub.costpricebase.handler.body;

import java.util.Map;

import nc.bd.framework.base.CMMapUtil;
import nc.cmpub.business.adapter.BDAdapter;
import nc.pub.templet.converter.util.helper.ExceptionUtils;
import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.mapub.business.ref.FilterFactorRefUtil;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardPanelEvent;
import nc.vo.mapub.costpricebase.entity.CostPriceBodyVO;
/*import  nc.vo.bd.materialpricebase.entity.pub.BDAdapter;*/
import nc.vo.pub.BusinessException;

/*import  nc.vo.bd.materialpricebase.entity.pub.BDAdapter;*/
/**
 * @since v6.3
 * @version 2017��7��27�� ����8:19:44
 * @author Administrator
 */
public class CelementidHandler extends CMBasedocAbstractHandler {

    /*
     * (non-Javadoc)
     * @see nc.ui.bd.pub.handler.CMBasedocAbstractHandler#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardPanelEvent)
     */
    @Override
    public void beforeEdit(CardPanelEvent e) {
        // TODO Auto-generated method stub
        CardBodyBeforeEditEvent beforeEdit = (CardBodyBeforeEditEvent) e;
        // // ���˺���Ҫ�ز��գ�������ĩ������ҵδͣ���Һϲ���ϸ��
        // ���˺���Ҫ�ز��գ�������ĩ������ҵ�ǲ���δͣ���Һϲ���ϸ��v6.5
        BillItem billItem = beforeEdit.getBillCardPanel().getBodyItem(CostPriceBodyVO.CELEMENTID);
        // ���ݹ����Ҷ�Ӧ�Ĳ�����֯
        Map<String, String> financeOrgIDs = null;

        String pk_org = e.getContext().getPk_org();
        try {
            financeOrgIDs = BDAdapter.queryFinanceOrgIDsByStockOrgIDs(new String[] {
                pk_org
            });
        }
        catch (BusinessException e1) {
            ExceptionUtils.wrapException(e1);
        }
        String financeID = pk_org;
        if (CMMapUtil.isNotEmpty(financeOrgIDs)) {
            financeID = financeOrgIDs.get(pk_org);
        }
        // ������������ĺ���Ҫ��ֻ���Ƿǲ����ҷǺϲ���ϸ�ġ�����ҵ���Ҫ�� -
        // ��ĩ��Ҫ�ؿɼ������ǲ���ѡ
        FilterFactorRefUtil.factorRefFilterForFee(financeID, billItem);
        // ������ֵʱ������Ҫ�ز��ɱ༭
        Object material = beforeEdit.getBillCardPanel().getBodyValueAt(beforeEdit.getRow(), CostPriceBodyVO.CELEMENTID);

        if (null != material) {
            beforeEdit.getBillCardPanel().setCellEditable(beforeEdit.getRow(), CostPriceBodyVO.CELEMENTID, false);
        }
        beforeEdit.setReturnValue(Boolean.TRUE);
    }

    /*
     * (non-Javadoc)
     * @see nc.ui.bd.pub.handler.CMBasedocAbstractHandler#afterEdit(nc.ui.pubapp.uif2app.event.card.CardPanelEvent)
     */
    @Override
    public void afterEdit(CardPanelEvent e) {
        // TODO Auto-generated method stub

    }

}
