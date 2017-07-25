package nc.bs.mapub.driver.bp;

import nc.bd.business.rule.AddAuditInfoRule;
import nc.bs.mapub.driver.plugin.bpplugin.DriverPluginPoint;
import nc.bs.mapub.driver.rule.CheckCodeUniqueRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.bs.pubapp.pub.rule.OrgDisabledCheckRule;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.org.IOrgConst;
import nc.vo.mapub.driver.entity.DriverAggVO;
import nc.vo.mapub.driver.entity.DriverVO;

/**
 * 标准单据新增BP
 */
public class DriverInsertBP {

    public DriverAggVO[] insert(DriverAggVO[] bills) {

        InsertBPTemplate<DriverAggVO> bp = new InsertBPTemplate<DriverAggVO>(DriverPluginPoint.INSERT);
        // 新增前规则
        this.addBeforeRule(bp.getAroundProcesser());
        // 新增后规则
        this.addAfterRule(bp.getAroundProcesser());
        return bp.insert(bills);

    }

    /**
     * 新增后规则
     *
     * @param processer
     */
    @SuppressWarnings({
        "rawtypes", "unchecked"
    })
    private void addAfterRule(AroundProcesser<DriverAggVO> processer) {
        // 主组织检查
        IRule orgRule = new OrgDisabledCheckRule(DriverVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE);
        processer.addAfterRule(orgRule);
    }

    /**
     * 新增前规则
     *
     * @param processer
     */
    @SuppressWarnings({
        "rawtypes", "unchecked"
    })
    private void addBeforeRule(AroundProcesser<DriverAggVO> processer) {
        // 长度检查
        IRule lengthRule = new FieldLengthCheckRule();
        processer.addBeforeFinalRule(lengthRule);

        // 新增时增加审计信息
        processer.addBeforeRule(new AddAuditInfoRule());

        // 编码唯一校验
        processer.addBeforeRule(new CheckCodeUniqueRule());
    }
}
