package nc.bs.mapub.materialpricebase.bp;

import nc.bs.mapub.materialpricebase.plugin.bpplugin.MaterialPriceBasePluginPoint;
import nc.bs.mapub.materialpricebase.rule.MaterialPriceReferencedRule;
import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;

/**
 * 材料价格库删除BP
 * 
 * @since 6.36
 * @version 2014-11-10 下午2:41:44
 * @author zhangchd
 */
public class MaterialPriceBaseDeleteBP {
    public void delete(MaterialPriceBaseAggVO[] bills) {
        DeleteBPTemplate<MaterialPriceBaseAggVO> bp =
                new DeleteBPTemplate<MaterialPriceBaseAggVO>(MaterialPriceBasePluginPoint.DELETE);
        // 增加执行前业务规则
        this.addBeforeRule(bp.getAroundProcesser());
        // 增加执行后业务规则
        this.addAfterRule(bp.getAroundProcesser());
        bp.delete(bills);
    }

    /**
     * 删除前业务规则
     * 
     * @param processer
     */
    private void addBeforeRule(AroundProcesser<MaterialPriceBaseAggVO> processer) {
        // 1. 价格库被成本动因引用不能删除
        // IRule<MaterialPriceBaseAggVO> materialpricebaseIsUsedCheck = new MaterialPriceBaseIsUsedCheck();
        // processer.addBeforeRule(materialpricebaseIsUsedCheck);

        // 1、 被成本类型、成本BOM引用校验
        IRule<MaterialPriceBaseAggVO> materialpricebaseIsUsedCheck = new MaterialPriceReferencedRule();
        processer.addBeforeRule(materialpricebaseIsUsedCheck);
    }

    /**
     * 删除后业务规则
     */
    private void addAfterRule(AroundProcesser<MaterialPriceBaseAggVO> processer) {

    }
}
