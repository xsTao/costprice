package nc.ui.mapub.materialpricebase.handler.head;

import nc.bd.framework.base.CMValueCheck;
import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardPanelEvent;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.pub.AggregatedValueObject;

/**
 * * 物料价格来源对话框
 * 价格来源事件
 * 
 * @since 6.36
 * @version 2014-11-22 下午4:41:37
 * @author zhangchd
 */
public class PriceSourcesHandler extends CMBasedocAbstractHandler {

    /**
     * 业价格来源编辑前事件
     */
    @Override
    public void beforeEdit(CardPanelEvent ex) {
        CardHeadTailBeforeEditEvent e = (CardHeadTailBeforeEditEvent) ex;

        // UIRefPane ref =
        // (UIRefPane) e.getBillCardPanel().getHeadItem(MaterialPriceBaseHeadVO.VPRICESOURCE).getComponent();
        // ref.getUITextField().setEditable(false);

        e.setReturnValue(Boolean.TRUE);
    }

    /**
     * 价格来源编辑后处理逻辑
     */
    @Override
    public void afterEdit(CardPanelEvent e) {
        CardHeadTailAfterEditEvent ex = (CardHeadTailAfterEditEvent) e;
        // BillCardPanel cardPanel = ex.getBillCardPanel();
        // 清空表体价格来源为空的单价
        AggregatedValueObject aggVO =
                ex.getBillCardPanel()
                        .getBillData()
                        .getBillValueVO(MaterialPriceBaseAggVO.class.getName(),
                                MaterialPriceBaseHeadVO.class.getName(), MaterialPriceBaseBodyVO.class.getName());

        MaterialPriceBaseBodyVO[] childVOs = (MaterialPriceBaseBodyVO[]) aggVO.getChildrenVO();
        if (CMValueCheck.isNotEmpty(childVOs)) {
            for (MaterialPriceBaseBodyVO childVO : childVOs) {
                // 价格来源
                String priceSourceString = childVO.getVpricesourcenum();
                if (CMValueCheck.isEmpty(priceSourceString)) {
                    childVO.setNprice(null); // 单价
                    childVO.setVpricesourcereal(null);// 实际价格来源
                    childVO.setVpricesourcerealnum(null);// 实际价格来源数
                }
            }

            // 重新设值，保证界面数据刷新
            ex.getBillCardPanel().getBillData().setBodyValueVO(aggVO.getChildrenVO());
        }

    }
}
