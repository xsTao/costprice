package nc.bs.mapub.allocfac.bp;

import nc.bs.mapub.allocfac.plugin.bpplugin.AllocfacPluginPoint;
import nc.bs.mapub.allocfac.rule.AllocfacIsUsedCheck;
import nc.bs.mapub.allocfac.rule.CheckAllocfacIsUsed;
import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;

/**
 * 分配系数删除BP
 */
public class AllocfacDeleteBP {

    public void delete(AllocfacAggVO[] bills) {
        // 调用删除模板
        DeleteBPTemplate<AllocfacAggVO> bp = new DeleteBPTemplate<AllocfacAggVO>(AllocfacPluginPoint.DELETE);

        this.addBeforeRule(bp.getAroundProcesser());
        this.addAfterRule(bp.getAroundProcesser());

        bp.delete(bills);
    }

    /**
     * 删除前规则
     *
     * @param processor
     */
    private void addBeforeRule(AroundProcesser<AllocfacAggVO> processer) {
    }

    /**
     * 删除后规则
     *
     * @param aroundProcesser
     */
    private void addAfterRule(AroundProcesser<AllocfacAggVO> processer) {
        // 删除前判断成本动因是否引用此分配系数
        IRule<AllocfacAggVO> rule = new AllocfacIsUsedCheck();
        processer.addAfterRule(rule);

        processer.addAfterRule(new CheckAllocfacIsUsed());
    }
}
