package nc.bs.mapub.allocfac.bp;

import nc.bd.business.rule.AddAuditInfoRule;
import nc.bd.business.rule.CheckMaterialRule;
import nc.bd.business.rule.DeleteAuditRule;
import nc.bd.business.rule.FillAddDataRule;
import nc.bs.mapub.allocfac.plugin.bpplugin.AllocfacPluginPoint;
import nc.bs.mapub.allocfac.rule.AllocfacCheckIdRule;
import nc.bs.mapub.allocfac.rule.AllocfacCheckNotNullRule;
import nc.bs.mapub.allocfac.rule.AllocfacCheckVBRule;
import nc.bs.mapub.allocfac.rule.AllocfacSaveRule;
import nc.bs.pubapp.pub.rule.CheckNotNullRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.bs.pubapp.pub.rule.OrgDisabledCheckRule;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.org.IOrgConst;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;

/**
 * 分配系数新增BP
 */
public class AllocfacInsertBP {

    public AllocfacAggVO[] insert(AllocfacAggVO[] bills) {
        InsertBPTemplate<AllocfacAggVO> bp = new InsertBPTemplate<AllocfacAggVO>(AllocfacPluginPoint.INSERT);
        this.addBeforeRule(bp.getAroundProcesser());
        this.addAfterRule(bp.getAroundProcesser());
        return bp.insert(bills);
    }

    /**
     * 新增前规则
     *
     * @param processor
     */
    @SuppressWarnings({
        "unchecked", "rawtypes"
    })
    private void addBeforeRule(AroundProcesser<AllocfacAggVO> processer) {
        // 检查表体不能为空
        IRule<AllocfacAggVO> checkNotNullRule = new CheckNotNullRule();
        processer.addBeforeRule(checkNotNullRule);

        // 校验物料和组织集团对照关系
        IRule<AllocfacAggVO> mtrAndOrgRule = new CheckMaterialRule();
        processer.addBeforeRule(mtrAndOrgRule);

        // 保存前检查 检查不能为空字段是否符合要求
        IRule<AllocfacAggVO> checkLegalNullRule = new AllocfacCheckNotNullRule();
        processer.addBeforeRule(checkLegalNullRule);

        // 检查表体ID不能重复
        IRule<AllocfacAggVO> checkIdRule = new AllocfacCheckIdRule();
        processer.addBeforeRule(checkIdRule);

        // 检查表体产品辅助属性是否全部启用
        IRule<AllocfacAggVO> checkVBRule = new AllocfacCheckVBRule();
        processer.addBeforeRule(checkVBRule);

        // 长度检查
        IRule lengthRule = new FieldLengthCheckRule();
        processer.addBeforeFinalRule(lengthRule);

        // 设置表体中集团、组织和会计期间的值
        IRule<AllocfacAggVO> billAddDataRule = new FillAddDataRule();
        processer.addBeforeFinalRule(billAddDataRule);

        // 删除审计信息（用于复制按钮，复制时需要清除创建信息和修改信息）
        IRule deleteAuditRule = new DeleteAuditRule();
        processer.addBeforeRule(deleteAuditRule);

        // 增加审计信息
        IRule<AllocfacAggVO> addAuditRule = new AddAuditInfoRule();
        processer.addBeforeRule(addAuditRule);
    }

    /**
     * 新增后规则
     *
     * @param processor
     */
    @SuppressWarnings("unchecked")
    private void addAfterRule(AroundProcesser<AllocfacAggVO> processer) {
        // 检查编码是否相同
        IRule<AllocfacAggVO> rule = new AllocfacSaveRule();
        processer.addAfterRule(rule);

        // 可能存在单据正在编辑时别人把组织停用了，应该在保存前校验组织是否停用
        IRule<AllocfacAggVO> orgRule = new OrgDisabledCheckRule(AllocfacHeadVO.PK_ORG, IOrgConst.FACTORYTYPE);
        processer.addAfterRule(orgRule);

    }
}
