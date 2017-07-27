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
 * @version 2017年7月27日 下午8:19:44
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
        // // 过滤核算要素参照，必须是末级非作业未停用且合并明细的
        // 过滤核算要素参照，必须是末级非作业非材料未停用且合并明细的v6.5
        BillItem billItem = beforeEdit.getBillCardPanel().getBodyItem(CostPriceBodyVO.CELEMENTID);
        // 根据工厂找对应的财务组织
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
        // 不含材料子项的核算要素只能是非材料且非合并明细的、非作业类的要素 -
        // 非末级要素可见，但是不可选
        FilterFactorRefUtil.factorRefFilterForFee(financeID, billItem);
        // 材料有值时，核算要素不可编辑
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
