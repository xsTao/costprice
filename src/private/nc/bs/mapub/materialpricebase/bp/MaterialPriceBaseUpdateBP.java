package nc.bs.mapub.materialpricebase.bp;

import nc.bd.business.rule.CheckMaterialRule;
import nc.bd.business.rule.FillUpdateDataRule;
import nc.bd.business.rule.UpdateAuditInfoRule;
import nc.bs.mapub.materialpricebase.plugin.bpplugin.MaterialPriceBasePluginPoint;
import nc.bs.mapub.materialpricebase.rule.AccountSchemeRule;
import nc.bs.mapub.materialpricebase.rule.CheckDateValidate;
import nc.bs.mapub.materialpricebase.rule.MaterialPriceBaseMarAssistantRule;
import nc.bs.mapub.materialpricebase.rule.MaterialPriceHeadRepeatRule;
import nc.bs.mapub.materialpricebase.rule.MaterialPriceRepeatRule;
import nc.bs.mapub.materialpricebase.rule.MaterialPriceValidateNumRule;
import nc.bs.pubapp.pub.rule.CheckNotNullRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.bs.pubapp.pub.rule.OrgDisabledCheckRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.org.IOrgConst;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;

/**
 * 材料价格库
 * 修改BP
 *
 * @since 6.36
 * @version 2014-11-10 下午2:50:20
 * @author zhangchd
 */
public class MaterialPriceBaseUpdateBP {

    public MaterialPriceBaseAggVO[] update(MaterialPriceBaseAggVO[] bills, MaterialPriceBaseAggVO[] originBills) {
        // 调用修改模板
        UpdateBPTemplate<MaterialPriceBaseAggVO> bp =
                new UpdateBPTemplate<MaterialPriceBaseAggVO>(MaterialPriceBasePluginPoint.UPDATE);
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

        // 增加审计信息
        IRule<MaterialPriceBaseAggVO> updateAuditInfoRule = new UpdateAuditInfoRule();
        processer.addBeforeFinalRule(updateAuditInfoRule);

        // 校验字段长度是否合法
        IRule<MaterialPriceBaseAggVO> fieldLengthCheckRule = new FieldLengthCheckRule();
        processer.addBeforeRule(fieldLengthCheckRule);
        // 主组织检查
        processer
                .addBeforeRule(new OrgDisabledCheckRule(MaterialPriceBaseHeadVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE));
        // 设置表体中集团、组织和会计期间的值
        IRule<MaterialPriceBaseAggVO> billUpdateDataRule = new FillUpdateDataRule();
        processer.addBeforeFinalRule(billUpdateDataRule);
        // 检查物料是否分配到了组织
        IRule<MaterialPriceBaseAggVO> checkMaterialRule = new CheckMaterialRule();
        processer.addBeforeRule(checkMaterialRule);
        // 1、验证表头价格库编码、价格库名称、币种、生效日期
        // 2、验证表体材料编码、单价(价格来源为：手工录入) 【完】
        IRule<MaterialPriceBaseAggVO> checkLegalNullRule = new MaterialPriceValidateNumRule();
        processer.addBeforeRule(checkLegalNullRule);
        // 3、校验失效时间是否大于或等于生效时间【完】
        IRule<MaterialPriceBaseAggVO> checkDateValidate = new CheckDateValidate();
        processer.addBeforeRule(checkDateValidate);
        // 5、工厂对应的财务组织主账簿不为空
        IRule<MaterialPriceBaseAggVO> accountSchemeRule = new AccountSchemeRule();
        processer.addBeforeRule(accountSchemeRule);
        // 6、表体物料辅助属性校验
        IRule<MaterialPriceBaseAggVO> marAssistantRule =
                new MaterialPriceBaseMarAssistantRule<MaterialPriceBaseAggVO>(MaterialPriceBaseBodyVO.CMATERIALID);
        processer.addBeforeRule(marAssistantRule);
        // 7、校验表体材料编码不能重复【完】
        IRule<MaterialPriceBaseAggVO> distinctCelementsRule = new MaterialPriceRepeatRule();
        processer.addBeforeRule(distinctCelementsRule);
    }

    /**
     * 修改后业务规则
     *
     * @param processer
     */
    @SuppressWarnings({
        "unchecked"
    })
    private void addAfterRule(CompareAroundProcesser<MaterialPriceBaseAggVO> processer) {
        // 可能存在单据正在编辑时别人把组织停用了
        IRule<MaterialPriceBaseAggVO> orgRule =
                new OrgDisabledCheckRule(MaterialPriceBaseHeadVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE);
        processer.addAfterRule(orgRule);
        // 校验表头价格库编码的唯一性【完】
        processer.addAfterRule(new MaterialPriceHeadRepeatRule());
        // 表体物料辅助属性校验
        // IRule<MaterialPriceBaseAggVO> cmMarAssistantRule = new
        // CMMarAssistantRule(MaterialPriceBaseBodyVO.CMATERIALID);
        // processer.addAfterRule(cmMarAssistantRule);
    }
}
