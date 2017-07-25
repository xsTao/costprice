package nc.ui.mapub.materialpricebase.dialog.pullPrice.handler;

import nc.bd.framework.base.CMValueCheck;
import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.bd.ref.model.AccperiodRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardPanelEvent;
import nc.vo.mapub.materialpricebase.entity.MaterialPullPriceHeadVO;

/**
 * 取价对话框
 * 开始期间
 * 
 * @since 6.36
 * @version 2014-12-3 下午3:04:46
 * @author zhangchd
 */
public class BeginDate extends CMBasedocAbstractHandler {

    @Override
    public void beforeEdit(CardPanelEvent ex) {
        CardHeadTailBeforeEditEvent e = (CardHeadTailBeforeEditEvent) ex;
        Object cperiodSchemeObject =
                e.getBillCardPanel().getHeadItem(MaterialPullPriceHeadVO.CPERIODSCHEME).getValueObject();

        UIRefPane refPane =
                (UIRefPane) e.getBillCardPanel().getHeadItem(MaterialPullPriceHeadVO.CBEGINDATE).getComponent();
        AccperiodRefModel refModel = (AccperiodRefModel) refPane.getRefModel();

        if (CMValueCheck.isNotEmpty(cperiodSchemeObject)) {
            refModel.setDefaultpk_accperiodscheme(cperiodSchemeObject.toString());
        }

        e.setReturnValue(Boolean.TRUE);
    }

    @Override
    public void afterEdit(CardPanelEvent e) {
        // CardHeadTailAfterEditEvent ex = (CardHeadTailAfterEditEvent) e;
        // BillCardPanel cardPanel = ex.getBillCardPanel();
    }

}
