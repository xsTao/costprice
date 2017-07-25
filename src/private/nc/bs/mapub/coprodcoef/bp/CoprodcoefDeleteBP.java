package nc.bs.mapub.coprodcoef.bp;

import nc.bs.mapub.coprodcoef.plugin.bpplugin.CoprodcoefPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;

/**
 * 标准单据删除BP
 * 
 * @since 6.0
 * @version 2014-10-11 下午3:11:26
 * @author zhangshyb
 */
public class CoprodcoefDeleteBP {

    public void delete(CoprodcoefAggVO[] bills) {
        DeleteBPTemplate<CoprodcoefAggVO> bp = new DeleteBPTemplate<CoprodcoefAggVO>(CoprodcoefPluginPoint.DELETE);
        // 增加执行前规则
        this.addBeforeRule(bp.getAroundProcesser());
        // 增加执行后业务规则
        this.addAfterRule(bp.getAroundProcesser());
        bp.delete(bills);
    }

    private void addBeforeRule(AroundProcesser<CoprodcoefAggVO> processer) {
        // processer.addBeforeRule(new SourceTypeCheckRule());// 非自制单据不可删除或修改
    }

    /**
     * 删除后业务规则
     * 
     * @param processer
     */
    private void addAfterRule(AroundProcesser<CoprodcoefAggVO> processer) {
    }
}
