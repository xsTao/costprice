package nc.bs.mapub.materialpricebase.bp;

import nc.bd.business.rule.CheckMaterialRule;
import nc.bd.business.rule.UpdateAuditInfoRule;
import nc.bs.mapub.materialpricebase.plugin.bpplugin.MaterialPriceBasePluginPoint;
import nc.bs.mapub.materialpricebase.rule.AccountSchemeRule;
import nc.bs.mapub.materialpricebase.rule.AddLockRule;
import nc.bs.pubapp.pub.rule.CheckNotNullRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.bs.pubapp.pub.rule.FillUpdateDataRule;
import nc.bs.pubapp.pub.rule.OrgDisabledCheckRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.org.IOrgConst;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;

public class MaterialPriceBaseLockBP {
    public MaterialPriceBaseAggVO[] lock(MaterialPriceBaseAggVO[] bills, MaterialPriceBaseAggVO[] originBills) {
        // 调用修改模板
        UpdateBPTemplate<MaterialPriceBaseAggVO> bp =
                new UpdateBPTemplate<MaterialPriceBaseAggVO>(MaterialPriceBasePluginPoint.LOCK);
        // 执行前规则
        this.addBeforeRule(bp.getAroundProcesser());
        // 执行后规则
        this.addAfterRule(bp.getAroundProcesser());
        return bp.update(bills, originBills);
    }

    /**
     * 修改前业务规则
     * 
     * @param processer
     */
    @SuppressWarnings("unchecked")
    private void addBeforeRule(CompareAroundProcesser<MaterialPriceBaseAggVO> processer) {
        // 检查表体不能为空
        IRule<MaterialPriceBaseAggVO> checkNotNullRule = new CheckNotNullRule();
        processer.addBeforeRule(checkNotNullRule);

        // 检查物料是否分配到了组织
        IRule<MaterialPriceBaseAggVO> checkMaterialRule = new CheckMaterialRule();
        processer.addBeforeRule(checkMaterialRule);

        // 校验字段长度是否合法
        IRule<MaterialPriceBaseAggVO> fieldLengthCheckRule = new FieldLengthCheckRule();
        processer.addBeforeRule(fieldLengthCheckRule);

        // 设置表体中集团、组织和会计期间的值
        IRule<MaterialPriceBaseAggVO> billUpdateDataRule = new FillUpdateDataRule();
        processer.addBeforeFinalRule(billUpdateDataRule);

        // 增加锁定信息
        IRule<MaterialPriceBaseAggVO> addLockRule = new AddLockRule();
        processer.addBeforeFinalRule(addLockRule);

        // 增加审计信息
        IRule<MaterialPriceBaseAggVO> updateAuditInfoRule = new UpdateAuditInfoRule();
        processer.addBeforeFinalRule(updateAuditInfoRule);
    }

    /**
     * 修改后业务规则
     * 
     * @param processer
     */
    @SuppressWarnings("unchecked")
    private void addAfterRule(CompareAroundProcesser<MaterialPriceBaseAggVO> processer) {

        // 工厂对应的财务组织主账簿不为空
        IRule<MaterialPriceBaseAggVO> accountSchemeRule = new AccountSchemeRule();
        processer.addAfterRule(accountSchemeRule);

        // 可能存在单据正在编辑时别人把组织停用了
        IRule<MaterialPriceBaseAggVO> orgRule =
                new OrgDisabledCheckRule(MaterialPriceBaseHeadVO.PK_ORG, IOrgConst.FACTORYTYPE);
        processer.addAfterRule(orgRule);

    }

}
