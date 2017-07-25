package nc.bs.mapub.coprodcoef.bp;

import nc.bd.business.rule.CheckMaterialRule;
import nc.bd.business.rule.FillUpdateDataRule;
import nc.bd.business.rule.UpdateAuditInfoRule;
import nc.bs.mapub.coprodcoef.plugin.bpplugin.CoprodcoefPluginPoint;
import nc.bs.mapub.coprodcoef.rule.CoprodcoefBodyNegativeCheckRule;
import nc.bs.mapub.coprodcoef.rule.CoprodcoefRepeatCheckRule;
import nc.bs.pubapp.pub.rule.CheckNotNullRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.bs.pubapp.pub.rule.OrgDisabledCheckRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.org.IOrgConst;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefHeadVO;

public class CoprodcoefUpdateBP {

    public CoprodcoefAggVO[] update(CoprodcoefAggVO[] bills, CoprodcoefAggVO[] originBills) {
        UpdateBPTemplate<CoprodcoefAggVO> bp = new UpdateBPTemplate<CoprodcoefAggVO>(CoprodcoefPluginPoint.UPDATE);
        this.addBeforeRule(bp.getAroundProcesser());
        this.addAfterRule(bp.getAroundProcesser());
        return bp.update(bills, originBills);
    }

    @SuppressWarnings({
        "unchecked", "rawtypes"
    })
    private void addAfterRule(CompareAroundProcesser<CoprodcoefAggVO> processer) {
        // 主组织检查
        IRule orgRule = new OrgDisabledCheckRule(CoprodcoefHeadVO.PK_ORG, IOrgConst.FACTORYTYPE);
        processer.addAfterRule(orgRule);

        processer.addAfterRule(new CoprodcoefRepeatCheckRule());// 判断重复
        processer.addAfterRule(new CheckMaterialRule());// 检查物料是否分配到组织
    }

    @SuppressWarnings({
        "unchecked", "rawtypes"
    })
    private void addBeforeRule(CompareAroundProcesser<CoprodcoefAggVO> processer) {
        processer.addBeforeRule(new CheckNotNullRule());// 表体不能为空

        // 设置表体中集团、组织和会计期间的值
        IRule<CoprodcoefAggVO> billAddDataRule = new FillUpdateDataRule();
        processer.addBeforeFinalRule(billAddDataRule);
        // 增加审计信息
        IRule<CoprodcoefAggVO> addAuditRule = new UpdateAuditInfoRule();
        processer.addBeforeRule(addAuditRule);

        // 长度检查
        IRule lengthRule = new FieldLengthCheckRule();
        processer.addBeforeFinalRule(lengthRule);

        processer.addBeforeRule(new CoprodcoefBodyNegativeCheckRule());// 表体非负
    }

}
