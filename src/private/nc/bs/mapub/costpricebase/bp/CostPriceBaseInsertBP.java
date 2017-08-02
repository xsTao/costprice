/**
 *
 */
package nc.bs.mapub.costpricebase.bp;

import nc.bd.business.rule.AddAuditInfoRule;
import nc.bd.business.rule.DeleteAuditRule;
import nc.bd.business.rule.FillAddDataRule;
import nc.bs.mapub.costpricebase.plugin.bpplugin.CostPriceBasePluginPoint;
import nc.bs.mapub.costpricebase.rule.CostPriceHeadRepeatRule;
import nc.bs.mapub.costpricebase.rule.CostPriceRepeatRule;
import nc.bs.mapub.costpricebase.rule.CostPriceValidateNumRule;
import nc.bs.pubapp.pub.rule.CheckNotNullRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.bs.pubapp.pub.rule.OrgDisabledCheckRule;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.org.IOrgConst;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;
import nc.vo.mapub.costpricebase.entity.CostPriceHeadVO;

/**
 * @since v6.3
 * @version 2017年7月26日 上午9:55:34
 * @author Administrator
 */
public class CostPriceBaseInsertBP {

    public CostPriceAggVO[] insert(CostPriceAggVO[] bills) {
        InsertBPTemplate<CostPriceAggVO> bp = new InsertBPTemplate<CostPriceAggVO>(CostPriceBasePluginPoint.INSERT);
        // 插入之前规则
        this.addBeforeRule(bp.getAroundProcesser());
        // 插入之后规则
        this.addAfterRule(bp.getAroundProcesser());

        return bp.insert(bills);
    }

    /**
     * @param aroundProcesser
     */
    @SuppressWarnings({
        "unchecked", "rawtypes"
    })
    private void addBeforeRule(AroundProcesser<CostPriceAggVO> processer) {
        // TODO Auto-generated method stub

        // 检查表体不能为空

        IRule<CostPriceAggVO> checkNotNullRule = new CheckNotNullRule();
        processer.addBeforeRule(checkNotNullRule);
        // 删除审计信息（用于复制按钮，复制时需要清除创建信息和修改信息）
        IRule deleteAuditRule = new DeleteAuditRule();
        processer.addBeforeRule(deleteAuditRule);
        // 增加审计信息
        IRule<CostPriceAggVO> addAuditRule = new AddAuditInfoRule();
        processer.addBeforeRule(addAuditRule);
        // 校验字段长度是否合法
        IRule<CostPriceAggVO> fieldLengthCheckRule = new FieldLengthCheckRule();
        processer.addBeforeRule(fieldLengthCheckRule);
        // 主组织检查
        processer.addBeforeRule(new OrgDisabledCheckRule(CostPriceHeadVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE));
        // 设置表体中集团、组织和会计期间的值
        IRule<CostPriceAggVO> billAddDataRule = new FillAddDataRule();
        processer.addBeforeFinalRule(billAddDataRule);

        IRule<CostPriceAggVO> checkLegalNullRule = new CostPriceValidateNumRule();
        processer.addBeforeRule(checkLegalNullRule);
        //
        IRule<CostPriceAggVO> distinctCelementsRule = new CostPriceRepeatRule();
        processer.addBeforeRule(distinctCelementsRule);
    }

    /**
     * 新增后规则
     *
     * @param processer
     */
    @SuppressWarnings("unchecked")
    private void addAfterRule(AroundProcesser<CostPriceAggVO> processer) {
        // 主组织检查
        IRule<CostPriceAggVO> orgRule = new OrgDisabledCheckRule(CostPriceHeadVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE);
        processer.addAfterRule(orgRule);
        // 校验表头价格库编码的唯一性【完】
        processer.addAfterRule(new CostPriceHeadRepeatRule());
    }
}
