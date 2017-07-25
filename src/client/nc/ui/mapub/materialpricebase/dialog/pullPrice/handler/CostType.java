package nc.ui.mapub.materialpricebase.dialog.pullPrice.handler;

import nc.bd.framework.base.CMValueCheck;
import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.mapub.business.ref.FilterCostTypeRefUtil;
import nc.ui.mapub.costtype.model.CostTypeRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardPanelEvent;
import nc.vo.mapub.materialpricebase.entity.MaterialPullPriceHeadVO;

/**
 * 取数对话框
 * 成本类型
 *
 * @since 6.36
 * @version 2014-12-4 上午9:54:29
 * @author zhangchd
 */
public class CostType extends CMBasedocAbstractHandler {
    @Override
    public void beforeEdit(CardPanelEvent ex) {
        CardHeadTailBeforeEditEvent e = (CardHeadTailBeforeEditEvent) ex;
        // 业务单元
        String pk_org = "";
        Object pk_orgObject = e.getBillCardPanel().getHeadItem(MaterialPullPriceHeadVO.PK_ORG).getValueObject();
        if (CMValueCheck.isNotEmpty(pk_orgObject)) {
            pk_org = pk_orgObject.toString();
        }
        // 集团
        String pk_group = "";
        Object pk_groupObject = e.getBillCardPanel().getHeadItem(MaterialPullPriceHeadVO.PK_GROUP).getValueObject();
        if (CMValueCheck.isNotEmpty(pk_groupObject)) {
            pk_group = pk_groupObject.toString();
        }
        UIRefPane refPane =
                (UIRefPane) e.getBillCardPanel().getHeadItem(MaterialPullPriceHeadVO.COSTTYPE).getComponent();
        CostTypeRefModel refModel = (CostTypeRefModel) refPane.getRefModel();
        //
        refModel.setPk_org(pk_org);
        // refModel.setPk_group(pk_group);

        FilterCostTypeRefUtil.filterRefByDate(refPane, pk_org, pk_group);

        e.setReturnValue(Boolean.TRUE);
    }

    @Override
    public void afterEdit(CardPanelEvent e) {
        // CardHeadTailAfterEditEvent ex = (CardHeadTailAfterEditEvent) e;
        // BillCardPanel cardPanel = ex.getBillCardPanel();
    }
}
