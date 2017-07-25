package nc.ui.mapub.materialpricebase.scale;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;

/**
 * ���ϼ۸��
 * ���ȴ�����
 * 
 * @since 6.36
 * @version 2014-11-7 ����4:21:09
 * @author zhangchd
 */
public class MaterialPriceBaseScaleUtil {
    // ���ÿ�Ƭ����
    public void setCardScale(String pk_group, BillCardPanel bcp) {
        this.setScale(new CardPaneScaleProcessor(pk_group, bcp));
    }

    // �����б���
    public void setListScale(String pk_group, BillListPanel bcp) {
        this.setScale(new ListPaneScaleProcessor(pk_group, bcp));
    }

    // ���ô�ӡ����
    public void setPrintScale(String pk_group, MaterialPriceBaseAggVO[] bills) {
        this.setScale(new BillVOScaleProcessor(pk_group, bills));
    }

    private void setScale(BillScaleProcessor scale) {

        // ���۾���
        scale.setCostPriceCtlInfo(new String[] {
            MaterialPriceBaseBodyVO.NPRICE
        }, PosEnum.body, null);

        scale.process();
    }
}
