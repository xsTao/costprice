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
        // ����֯���
        IRule orgRule = new OrgDisabledCheckRule(CoprodcoefHeadVO.PK_ORG, IOrgConst.FACTORYTYPE);
        processer.addAfterRule(orgRule);

        processer.addAfterRule(new CoprodcoefRepeatCheckRule());// �ж��ظ�
        processer.addAfterRule(new CheckMaterialRule());// ��������Ƿ���䵽��֯
    }

    @SuppressWarnings({
        "unchecked", "rawtypes"
    })
    private void addBeforeRule(CompareAroundProcesser<CoprodcoefAggVO> processer) {
        processer.addBeforeRule(new CheckNotNullRule());// ���岻��Ϊ��

        // ���ñ����м��š���֯�ͻ���ڼ��ֵ
        IRule<CoprodcoefAggVO> billAddDataRule = new FillUpdateDataRule();
        processer.addBeforeFinalRule(billAddDataRule);
        // ���������Ϣ
        IRule<CoprodcoefAggVO> addAuditRule = new UpdateAuditInfoRule();
        processer.addBeforeRule(addAuditRule);

        // ���ȼ��
        IRule lengthRule = new FieldLengthCheckRule();
        processer.addBeforeFinalRule(lengthRule);

        processer.addBeforeRule(new CoprodcoefBodyNegativeCheckRule());// ����Ǹ�
    }

}
