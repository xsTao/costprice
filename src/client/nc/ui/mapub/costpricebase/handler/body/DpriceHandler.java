/**
 *
 */
package nc.ui.mapub.costpricebase.handler.body;

import nc.cmpub.business.adapter.BDAdapter;
import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardPanelEvent;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.mapub.costpricebase.entity.CostPriceBodyVO;
import nc.vo.pub.lang.UFDouble;

/**
 * @since v6.3
 * @version 2017��8��8�� ����8:11:36
 * @author Administrator
 */
public class DpriceHandler extends CMBasedocAbstractHandler {

    /*
     * (non-Javadoc)
     * @see nc.ui.bd.pub.handler.CMBasedocAbstractHandler#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardPanelEvent)
     */
    @Override
    public void beforeEdit(CardPanelEvent e) {
        // TODO Auto-generated method stub

    }

    /*
     * ���� ͨ����ʼ���б��ʱ�����ã�����Ҫͨ��handler�༭���¼����������·��������ã�����BillForm�д���
     * (non-Javadoc)
     * @see nc.ui.bd.pub.handler.CMBasedocAbstractHandler#afterEdit(nc.ui.pubapp.uif2app.event.card.CardPanelEvent)
     */
    @Override
    public void afterEdit(CardPanelEvent ex) {
        // TODO Auto-generated method stub
        // String pk_accountingbook = null;
        // try {
        // pk_accountingbook = BDAdapter.getMainAccountBookByStockOrgId(e.getNewPkOrg());
        // }
        // catch (BusinessException ex) {
        // ExceptionUtils.wrappException(ex);
        // }
        // if (CMStringUtil.isEmpty(pk_accountingbook)) {
        // ExceptionUtils.wrappBusinessException(CMLangConstPub.ERR_NO_ACCOUNTBOOK);
        // }
        //
        // CurrencyRateUtilHelper currencyHelper = CurrencyRateUtilHelper.getInstance();
        // // ȡ��������˲���λ��
        // String pk_currency = currencyHelper.getLocalCurrtypeByAccountingbookID(pk_accountingbook);
        //
        // if (CMStringUtil.isEmpty(pk_currency)) {
        // ExceptionUtils.wrappBusinessException(CMLangConstPub.ERR_NO_ACCOUNTBOOK_SCALE);
        // }

        CardBodyAfterEditEvent e = (CardBodyAfterEditEvent) ex;
        BillItem priceItem = e.getBillCardPanel().getBodyItem(e.getTableCode(), e.getKey());
        if (priceItem == null) {
            return;
        }
        String pk_org = e.getContext().getPk_org();
        int priceScale = BDAdapter.getCurrencyPrecision(pk_org);// ���ݹ�����ѯ���־���
        UFDouble price = (UFDouble) priceItem.getValueObject();
        price = price.setScale(priceScale, UFDouble.ROUND_HALF_UP);
        // ����������֤
        if (price != null && UFDouble.ZERO_DBL.compareTo(price) > 0) {
            e.getBillCardPanel().setBodyValueAt(null, e.getRow(), CostPriceBodyVO.DPRICE);
            ShowStatusBarMsgUtil.showErrorMsgWithClear("", "����ֶ�Ӧ������", e.getContext());
        }

    }

}
