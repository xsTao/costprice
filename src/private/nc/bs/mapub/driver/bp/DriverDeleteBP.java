package nc.bs.mapub.driver.bp;

import nc.bs.mapub.driver.plugin.bpplugin.DriverPluginPoint;
import nc.bs.mapub.driver.rule.CheckDriverIsUsed;
import nc.bs.mapub.driver.rule.CheckSystemInitRule;
import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.mapub.driver.entity.DriverAggVO;

/**
 * 标准单据删除BP
 */
@SuppressWarnings("unchecked")
public class DriverDeleteBP {

    public void delete(DriverAggVO[] bills) {

        DeleteBPTemplate<DriverAggVO> bp = new DeleteBPTemplate<DriverAggVO>(DriverPluginPoint.DELETE);
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
    private void addBeforeRule(AroundProcesser<DriverAggVO> processer) {
        // 预置动因校验
        processer.addBeforeRule(new CheckSystemInitRule());

        processer.addBeforeRule(new CheckDriverIsUsed());
    }

    /**
     * 删除后业务规则
     * 
     * @param processer
     */
    private void addAfterRule(AroundProcesser<DriverAggVO> processer) {
    }
}
