package nc.ui.mapub.materialpricebase.dialog.priceSources.handler;

import java.util.ArrayList;
import java.util.List;

import nc.cmpub.business.enumeration.CMPubPriceSourceEnum;
import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.beans.constenum.IConstEnum;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardPanelEvent;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceSourcesBodyVO;

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
        CardBodyBeforeEditEvent e = (CardBodyBeforeEditEvent) ex;
        BillCardPanel cardPanel = e.getBillCardPanel();
        BillItem priceSourceItem = cardPanel.getBodyItem(e.getKey());

        UIComboBox combox = (UIComboBox) priceSourceItem.getComponent();
        combox.removeAllItems();
        List<IConstEnum> items = new ArrayList<IConstEnum>();
        // items.add(new DefaultConstEnum(null, null));
        items.add(new DefaultConstEnum(CMPubPriceSourceEnum.MANUAL.getEnumValue().getValue(),
                CMPubPriceSourceEnum.MANUAL.getName()));
        items.add(new DefaultConstEnum(CMPubPriceSourceEnum.PLAN.getEnumValue().getValue(), CMPubPriceSourceEnum.PLAN
                .getName()));
        items.add(new DefaultConstEnum(CMPubPriceSourceEnum.REFERENCE.getEnumValue().getValue(),
                CMPubPriceSourceEnum.REFERENCE.getName()));
        items.add(new DefaultConstEnum(CMPubPriceSourceEnum.PINGJUNCAIGOURUKU.getEnumValue().getValue(),
                CMPubPriceSourceEnum.PINGJUNCAIGOURUKU.getName()));
        items.add(new DefaultConstEnum(CMPubPriceSourceEnum.FORWARD.getEnumValue().getValue(),
                CMPubPriceSourceEnum.FORWARD.getName()));
        items.add(new DefaultConstEnum(CMPubPriceSourceEnum.STDCOST.getEnumValue().getValue(),
                CMPubPriceSourceEnum.STDCOST.getName()));
        combox.addItems(items.toArray(new IConstEnum[items.size()]));
        e.setReturnValue(Boolean.TRUE);
    }

    /**
     * �۸���Դ�༭�����߼�
     */
    @Override
    public void afterEdit(CardPanelEvent e) {
        CardBodyAfterEditEvent ex = (CardBodyAfterEditEvent) e;
        BillCardPanel cardPanel = ex.getBillCardPanel();

        // Object orgbillObject = cardPanel.getBodyValueAt(ex.getRow(), MaterialPriceSourcesBodyVO.PK_ORG);
        //
        // if (CMValueCheck.isNotEmpty(orgbillObject)
        // && Integer.valueOf(1) == cardPanel.getBodyValueAt(ex.getRow(), MaterialPriceSourcesBodyVO.VPRICESOURCE)) {
        // ex.getBillCardPanel().setBodyValueAt(null, ex.getRow(), MaterialPriceSourcesBodyVO.VPRICESOURCE);
        // }
        //
        cardPanel.setBodyValueAt(null, ex.getRow(), MaterialPriceSourcesBodyVO.PK_ORG);
        cardPanel.setBodyValueAt(null, ex.getRow(), MaterialPriceSourcesBodyVO.CORGCODE);

    }
}
