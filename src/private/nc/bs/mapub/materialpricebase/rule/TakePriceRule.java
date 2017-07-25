package nc.bs.mapub.materialpricebase.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.mapub.materialpricebase.enumeration.PriceSourceEnum;

/**
 * ȡ��rule
 * 
 * @since 6.0
 * @version 2014-10-12 ����7:21:14
 * @author baoxina
 */
public class TakePriceRule implements IRule<MaterialPriceBaseAggVO> {

    @Override
    public void process(MaterialPriceBaseAggVO[] vos) {
        for (MaterialPriceBaseAggVO aggvo : vos) {
            // Map<CMPriceParamVO, UFDouble> martPriceMap = MaterialPriceBasePriceGetUtil.getPrice(aggvo);

            MaterialPriceBaseBodyVO[] bodyVOs = aggvo.getItemVO();
            for (MaterialPriceBaseBodyVO bodyVO : bodyVOs) {
                // ����۸���Դ���ֹ�¼�������ȡ��
                if (!bodyVO.getAttributeValue(MaterialPriceBaseBodyVO.VPRICESOURCE).equals(
                        PriceSourceEnum.MANUAL.value())) {
                    this.takePrice(bodyVO);
                }
            }
        }
    }

    public void takePrice(MaterialPriceBaseBodyVO bodyVO) {

        bodyVO.setAttributeValue(MaterialPriceBaseBodyVO.NPRICE, 3);
    }

}
