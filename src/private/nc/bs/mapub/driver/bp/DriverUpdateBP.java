package nc.bs.mapub.driver.bp;

import nc.bd.business.rule.UpdateAuditInfoRule;
import nc.bs.mapub.driver.plugin.bpplugin.DriverPluginPoint;
import nc.bs.mapub.driver.rule.CheckCodeUniqueRule;
import nc.bs.mapub.driver.rule.CheckSystemInitRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.bs.pubapp.pub.rule.OrgDisabledCheckRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.org.IOrgConst;
import nc.vo.mapub.driver.entity.DriverAggVO;
import nc.vo.mapub.driver.entity.DriverVO;

/**
 * 修改保存的BP
 */
public class DriverUpdateBP {

    public DriverAggVO[] update(DriverAggVO[] bills, DriverAggVO[] originBills) {
        // 调用修改模板
        UpdateBPTemplate<DriverAggVO> bp = new UpdateBPTemplate<DriverAggVO>(DriverPluginPoint.UPDATE);
        // 执行前规则
        this.addBeforeRule(bp.getAroundProcesser());
        // 执行后规则
        this.addAfterRule(bp.getAroundProcesser());
        return bp.update(bills, originBills);
    }

    /**
     * 修改后业务规则
     * 
     * @param processer
     */
    @SuppressWarnings({
        "rawtypes", "unchecked"
    })
    private void addAfterRule(CompareAroundProcesser<DriverAggVO> processer) {
        // 主组织检查
        IRule orgRule = new OrgDisabledCheckRule(DriverVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE);
        processer.addAfterRule(orgRule);
    }

    /**
     * 修改前业务规则
     * 
     * @param processer
     */
    @SuppressWarnings({
        "rawtypes", "unchecked"
    })
    private void addBeforeRule(CompareAroundProcesser<DriverAggVO> processer) {
        // 长度检查
        IRule lengthRule = new FieldLengthCheckRule();
        processer.addBeforeFinalRule(lengthRule);

        // 审计信息
        processer.addBeforeRule(new UpdateAuditInfoRule());

        // 预置动因校验
        processer.addBeforeRule(new CheckSystemInitRule());

        // 编码唯一校验
        processer.addBeforeRule(new CheckCodeUniqueRule());
    }
}
