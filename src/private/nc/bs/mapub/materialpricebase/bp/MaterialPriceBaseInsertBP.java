package nc.bs.mapub.materialpricebase.bp;

import nc.bd.business.rule.AddAuditInfoRule;
import nc.bd.business.rule.CheckMaterialRule;
import nc.bd.business.rule.DeleteAuditRule;
import nc.bd.business.rule.FillAddDataRule;
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
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.org.IOrgConst;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;

/**
 * 材料价格库
 * 新增BP
 *
 * @since 6.36
 * @version 2014-11-10 下午2:43:09
 * @author zhangchd
 */
@SuppressWarnings({
    "unchecked", "rawtypes"
})
public class MaterialPriceBaseInsertBP {
    public MaterialPriceBaseAggVO[] insert(MaterialPriceBaseAggVO[] bills) {
        InsertBPTemplate<MaterialPriceBaseAggVO> bp =
                new InsertBPTemplate<MaterialPriceBaseAggVO>(MaterialPriceBasePluginPoint.INSERT);

        // 新增前规则
        this.addBeforeRule(bp.getAroundProcesser());
        // 新增后规则
        this.addAfterRule(bp.getAroundProcesser());

        return bp.insert(bills);
    }

    /**
     * 新增前规则
     *
     * @param processer
     */
    private void addBeforeRule(AroundProcesser<MaterialPriceBaseAggVO> processer) {
        // 检查表体不能为空
        IRule<MaterialPriceBaseAggVO> checkNotNullRule = new CheckNotNullRule();
        processer.addBeforeRule(checkNotNullRule);
        // 删除审计信息（用于复制按钮，复制时需要清除创建信息和修改信息）
        IRule deleteAuditRule = new DeleteAuditRule();
        processer.addBeforeRule(deleteAuditRule);
        // 增加审计信息
        IRule<MaterialPriceBaseAggVO> addAuditRule = new AddAuditInfoRule();
        processer.addBeforeRule(addAuditRule);
        // 校验字段长度是否合法
        IRule<MaterialPriceBaseAggVO> fieldLengthCheckRule = new FieldLengthCheckRule();
        processer.addBeforeRule(fieldLengthCheckRule);
        // 主组织检查
        processer
        .addBeforeRule(new OrgDisabledCheckRule(MaterialPriceBaseHeadVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE));
        // 设置表体中集团、组织和会计期间的值
        IRule<MaterialPriceBaseAggVO> billAddDataRule = new FillAddDataRule();
        processer.addBeforeFinalRule(billAddDataRule);
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
                new MaterialPriceBaseMarAssistantRule(MaterialPriceBaseBodyVO.CMATERIALID);
        processer.addBeforeRule(marAssistantRule);
        // 7、校验表体材料编码不能重复【完】
        IRule<MaterialPriceBaseAggVO> distinctCelementsRule = new MaterialPriceRepeatRule();
        processer.addBeforeRule(distinctCelementsRule);
    }

    /**
     * 新增后规则
     *
     * @param processer
     */
    private void addAfterRule(AroundProcesser<MaterialPriceBaseAggVO> processer) {
        // 主组织检查
        IRule<MaterialPriceBaseAggVO> orgRule =
                new OrgDisabledCheckRule(MaterialPriceBaseHeadVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE);
        processer.addAfterRule(orgRule);
        // 校验表头价格库编码的唯一性【完】
        processer.addAfterRule(new MaterialPriceHeadRepeatRule());
    }
}
