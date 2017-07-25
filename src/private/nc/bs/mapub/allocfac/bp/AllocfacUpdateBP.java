/**
 *
 */
package nc.bs.mapub.allocfac.bp;

import nc.bd.business.rule.CheckMaterialRule;
import nc.bd.business.rule.UpdateAuditInfoRule;
import nc.bs.mapub.allocfac.plugin.bpplugin.AllocfacPluginPoint;
import nc.bs.mapub.allocfac.rule.AllocfacCheckIdRule;
import nc.bs.mapub.allocfac.rule.AllocfacCheckNotNullRule;
import nc.bs.mapub.allocfac.rule.AllocfacCheckVBRule;
import nc.bs.mapub.allocfac.rule.AllocfacSaveRule;
import nc.bs.pubapp.pub.rule.CheckNotNullRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.bs.pubapp.pub.rule.FillUpdateDataRule;
import nc.bs.pubapp.pub.rule.OrgDisabledCheckRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.org.IOrgConst;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;

/**
 * 分配系数修改BP
 */
public class AllocfacUpdateBP {

    public AllocfacAggVO[] update(AllocfacAggVO[] bills, AllocfacAggVO[] originBills) {
        // 调用修改模板
        UpdateBPTemplate<AllocfacAggVO> bp = new UpdateBPTemplate<AllocfacAggVO>(AllocfacPluginPoint.UPDATE);
        this.addBeforeRule(bp.getAroundProcesser());
        this.addAfterRule(bp.getAroundProcesser());
        return bp.update(bills, originBills);
    }

    @SuppressWarnings({
        "unchecked", "rawtypes"
    })
    private void addBeforeRule(CompareAroundProcesser<AllocfacAggVO> processer) {
        // 检查表体不能为空
        IRule<AllocfacAggVO> checkNotNullRule = new CheckNotNullRule();
        processer.addBeforeRule(checkNotNullRule);

        // 校验物料和组织集团对照关系
        IRule<AllocfacAggVO> mtrAndOrgRule = new CheckMaterialRule();
        processer.addBeforeRule(mtrAndOrgRule);
        // 检查编码是否相同
        IRule<AllocfacAggVO> rule = new AllocfacSaveRule();
        processer.addBeforeRule(rule);

        // 保存前检查 检查不能为空字段是否符合要求
        // IRule<AllocfacAggVO> checkLegalNullRule = new AllocfacCheckNotNullRule();
        // processer.addBeforeRule(checkLegalNullRule);
        //

        // 修改时判断成本动因是否引用此分配系数
        // IRule<AllocfacAggVO> rule = new AllocfacIsUsedCheck();
        // processer.addBeforeRule(rule);

        // 检查表体ID不能重复
        IRule<AllocfacAggVO> checkIdRule = new AllocfacCheckIdRule();
        processer.addBeforeRule(checkIdRule);

        // 检查表体产品辅助属性是否全部启用
        IRule<AllocfacAggVO> checkVBRule = new AllocfacCheckVBRule();
        processer.addBeforeRule(checkVBRule);

        // 主组织检查
        IRule orgRule = new OrgDisabledCheckRule(AllocfacHeadVO.PK_ORG, IOrgConst.FACTORYTYPE);
        processer.addBeforeFinalRule(orgRule);

        // 长度检查
        IRule lengthRule = new FieldLengthCheckRule();
        processer.addBeforeFinalRule(lengthRule);

        // 设置表体中集团、组织和会计期间的值
        IRule<AllocfacAggVO> fillUpdateDataRule = new FillUpdateDataRule();
        processer.addBeforeFinalRule(fillUpdateDataRule);

        // 增加审计信息
        IRule<AllocfacAggVO> updateAuditInfoRule = new UpdateAuditInfoRule();
        processer.addBeforeRule(updateAuditInfoRule);
    }

    /**
     * @param processer
     */
    private void addAfterRule(CompareAroundProcesser<AllocfacAggVO> processer) {

        // 保存后检查 检查不能为空字段是否符合要求
        IRule<AllocfacAggVO> checkLegalNullRule = new AllocfacCheckNotNullRule();
        processer.addAfterRule(checkLegalNullRule);
    }
}
