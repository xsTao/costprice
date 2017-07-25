package nc.bs.mapub.coprodcoef.bp;

import nc.bd.business.rule.AddAuditInfoRule;
import nc.bd.business.rule.CheckMaterialRule;
import nc.bd.business.rule.FillAddDataRule;
import nc.bs.mapub.coprodcoef.plugin.bpplugin.CoprodcoefPluginPoint;
import nc.bs.mapub.coprodcoef.rule.CoprodcoefAddAuditInfoRule;
import nc.bs.mapub.coprodcoef.rule.CoprodcoefBodyNegativeCheckRule;
import nc.bs.mapub.coprodcoef.rule.CoprodcoefRepeatCheckRule;
import nc.bs.pubapp.pub.rule.CheckNotNullRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.bs.pubapp.pub.rule.OrgDisabledCheckRule;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.org.IOrgConst;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefHeadVO;

/**
 * 重复业务检查RULE
 *
 * @since 6.0
 * @version 2014-10-11 下午3:14:22
 * @author zhangshyb
 */
public class CoprodcoefInsertBP {

    public CoprodcoefAggVO[] insert(CoprodcoefAggVO[] bills) {

        InsertBPTemplate<CoprodcoefAggVO> bp = new InsertBPTemplate<CoprodcoefAggVO>(CoprodcoefPluginPoint.INSERT);
        this.addBeforeRule(bp.getAroundProcesser());
        this.addAfterRule(bp.getAroundProcesser());
        return bp.insert(bills);
    }

    /**
     * @param processor
     */
    @SuppressWarnings({
        "unchecked", "rawtypes"
    })
    private void addAfterRule(AroundProcesser<CoprodcoefAggVO> processer) {
        // 主组织检查-业务单元
        IRule orgRule = new OrgDisabledCheckRule(CoprodcoefHeadVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE);
        processer.addAfterRule(orgRule);
        processer.addAfterRule(new CoprodcoefRepeatCheckRule());// 判断重复
        processer.addAfterRule(new CheckMaterialRule());// 检查物料是否分配到组织
    }

    /**
     * @param processor
     */
    @SuppressWarnings({
        "rawtypes", "unchecked"
    })
    private void addBeforeRule(AroundProcesser<CoprodcoefAggVO> processer) {
        // 表体检查
        processer.addBeforeRule(new CheckNotNullRule());// 表体不能为空

        // 设置表体中集团、组织和会计期间的值
        IRule<CoprodcoefAggVO> billAddDataRule = new FillAddDataRule();
        processer.addBeforeFinalRule(billAddDataRule);

        // 增加审计信息
        IRule<CoprodcoefAggVO> addAuditRule = new AddAuditInfoRule();
        processer.addBeforeRule(addAuditRule);

        // 补充默认值
        IRule dataRule = new CoprodcoefAddAuditInfoRule();
        processer.addBeforeFinalRule(dataRule);

        // 长度检查
        IRule lengthRule = new FieldLengthCheckRule();
        processer.addBeforeFinalRule(lengthRule);

        processer.addBeforeRule(new CoprodcoefBodyNegativeCheckRule());// 表体非负
    }
}
