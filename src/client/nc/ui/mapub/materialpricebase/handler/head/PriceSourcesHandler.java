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
 * * ���ϼ۸���Դ�Ի���
 * �۸���Դ�¼�
 * 
 * @since 6.36
 * @version 2014-11-22 ����4:41:37
 * @author zhangchd
 */
public class PriceSourcesHandler extends CMBasedocAbstractHandler {

    /**
     * ҵ�۸���Դ�༭ǰ�¼�
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
     * �۸���Դ�༭�����߼�
     */
    @Override
    public void afterEdit(CardPanelEvent e) {
        CardHeadTailAfterEditEvent ex = (CardHeadTailAfterEditEvent) e;
        // BillCardPanel cardPanel = ex.getBillCardPanel();
        // ��ձ���۸���ԴΪ�յĵ���
        AggregatedValueObject aggVO =
                ex.getBillCardPanel()
                        .getBillData()
                        .getBillValueVO(MaterialPriceBaseAggVO.class.getName(),
                                MaterialPriceBaseHeadVO.class.getName(), MaterialPriceBaseBodyVO.class.getName());

        MaterialPriceBaseBodyVO[] childVOs = (MaterialPriceBaseBodyVO[]) aggVO.getChildrenVO();
        if (CMValueCheck.isNotEmpty(childVOs)) {
            for (MaterialPriceBaseBodyVO childVO : childVOs) {
                // �۸���Դ
                String priceSourceString = childVO.getVpricesourcenum();
                if (CMValueCheck.isEmpty(priceSourceString)) {
                    childVO.setNprice(null); // ����
                    childVO.setVpricesourcereal(null);// ʵ�ʼ۸���Դ
                    childVO.setVpricesourcerealnum(null);// ʵ�ʼ۸���Դ��
                }
            }

            // ������ֵ����֤��������ˢ��
            ex.getBillCardPanel().getBillData().setBodyValueVO(aggVO.getChildrenVO());
        }

    }
}
