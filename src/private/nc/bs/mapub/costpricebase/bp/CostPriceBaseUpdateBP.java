/**
 *
 */
package nc.bs.mapub.costpricebase.bp;

import nc.bd.business.rule.FillAddDataRule;
import nc.bd.business.rule.UpdateAuditInfoRule;
import nc.bs.mapub.costpricebase.plugin.bpplugin.CostPriceBasePluginPoint;
import nc.bs.mapub.costpricebase.rule.CostPriceHeadRepeatRule;
import nc.bs.mapub.costpricebase.rule.CostPriceRepeatRule;
import nc.bs.mapub.costpricebase.rule.CostPriceValidateNumRule;
import nc.bs.pubapp.pub.rule.CheckNotNullRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.bs.pubapp.pub.rule.OrgDisabledCheckRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.org.IOrgConst;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;
import nc.vo.mapub.costpricebase.entity.CostPriceHeadVO;

/**
 * @since v6.3
 * @version 2017年7月26日 下午1:56:45
 * @author Administrator
 */
public class CostPriceBaseUpdateBP {

    public CostPriceAggVO[] update(CostPriceAggVO[] bills, CostPriceAggVO[] originBills) {
        UpdateBPTemplate<CostPriceAggVO> bp = new UpdateBPTemplate<CostPriceAggVO>(CostPriceBasePluginPoint.UPDATE);
        // 执行前规则
        this.addBeforeRule(bp.getAroundProcesser());
        // 执行后规则
        this.addAfterRule(bp.getAroundProcesser());
        return bp.update(bills, originBills);
    }

    /**
     * @param aroundProcesser
     */
    @SuppressWarnings({
        "unchecked"
    })
    private void addBeforeRule(CompareAroundProcesser<CostPriceAggVO> processer) {
        // TODO Auto-generated method stub
        // 检查表体不能为空
        IRule<CostPriceAggVO> checkNotNullRule = new CheckNotNullRule();
        processer.addBeforeRule(checkNotNullRule);

        // 增加审计信息
        IRule<CostPriceAggVO> updateAuditRule = new UpdateAuditInfoRule();
        processer.addBeforeRule(updateAuditRule);
        // 校验字段长度是否合法
        IRule<CostPriceAggVO> fieldLengthCheckRule = new FieldLengthCheckRule();
        processer.addBeforeRule(fieldLengthCheckRule);

        // 主组织检查
        processer.addBeforeRule(new OrgDisabledCheckRule(CostPriceHeadVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE));
        // 设置表体中集团、组织和会计期间的值
        IRule<CostPriceAggVO> billAddDataRule = new FillAddDataRule();
        processer.addBeforeFinalRule(billAddDataRule);

        // 输入值检查
        IRule<CostPriceAggVO> checkLegalNullRule = new CostPriceValidateNumRule();
        processer.addBeforeRule(checkLegalNullRule);
        //
        IRule<CostPriceAggVO> distinctCelementsRule = new CostPriceRepeatRule();
        processer.addBeforeRule(distinctCelementsRule);

    }

    /**
     * @param aroundProcesser
     */
    @SuppressWarnings("unchecked")
    private void addAfterRule(CompareAroundProcesser<CostPriceAggVO> processer) {
        // TODO Auto-generated method stub
        IRule<CostPriceAggVO> orgRule = new OrgDisabledCheckRule(CostPriceHeadVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE);
        processer.addAfterRule(orgRule);
        // 校验表头价格库编码的唯一性
        processer.addAfterRule(new CostPriceHeadRepeatRule());
    }
}
