package nc.bs.mapub.materialpricebase.bp;

import nc.bs.mapub.materialpricebase.plugin.bpplugin.MaterialPriceBasePluginPoint;
import nc.bs.mapub.materialpricebase.rule.MaterialPriceReferencedRule;
import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;

/**
 * ���ϼ۸��ɾ��BP
 * 
 * @since 6.36
 * @version 2014-11-10 ����2:41:44
 * @author zhangchd
 */
public class MaterialPriceBaseDeleteBP {
    public void delete(MaterialPriceBaseAggVO[] bills) {
        DeleteBPTemplate<MaterialPriceBaseAggVO> bp =
                new DeleteBPTemplate<MaterialPriceBaseAggVO>(MaterialPriceBasePluginPoint.DELETE);
        // ����ִ��ǰҵ�����
        this.addBeforeRule(bp.getAroundProcesser());
        // ����ִ�к�ҵ�����
        this.addAfterRule(bp.getAroundProcesser());
        bp.delete(bills);
    }

    /**
     * ɾ��ǰҵ�����
     * 
     * @param processer
     */
    private void addBeforeRule(AroundProcesser<MaterialPriceBaseAggVO> processer) {
        // 1. �۸�ⱻ�ɱ��������ò���ɾ��
        // IRule<MaterialPriceBaseAggVO> materialpricebaseIsUsedCheck = new MaterialPriceBaseIsUsedCheck();
        // processer.addBeforeRule(materialpricebaseIsUsedCheck);

        // 1�� ���ɱ����͡��ɱ�BOM����У��
        IRule<MaterialPriceBaseAggVO> materialpricebaseIsUsedCheck = new MaterialPriceReferencedRule();
        processer.addBeforeRule(materialpricebaseIsUsedCheck);
    }

    /**
     * ɾ����ҵ�����
     */
    private void addAfterRule(AroundProcesser<MaterialPriceBaseAggVO> processer) {

    }
}
