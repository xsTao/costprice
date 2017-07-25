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
 * �ظ�ҵ����RULE
 *
 * @since 6.0
 * @version 2014-10-11 ����3:14:22
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
        // ����֯���-ҵ��Ԫ
        IRule orgRule = new OrgDisabledCheckRule(CoprodcoefHeadVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE);
        processer.addAfterRule(orgRule);
        processer.addAfterRule(new CoprodcoefRepeatCheckRule());// �ж��ظ�
        processer.addAfterRule(new CheckMaterialRule());// ��������Ƿ���䵽��֯
    }

    /**
     * @param processor
     */
    @SuppressWarnings({
        "rawtypes", "unchecked"
    })
    private void addBeforeRule(AroundProcesser<CoprodcoefAggVO> processer) {
        // ������
        processer.addBeforeRule(new CheckNotNullRule());// ���岻��Ϊ��

        // ���ñ����м��š���֯�ͻ���ڼ��ֵ
        IRule<CoprodcoefAggVO> billAddDataRule = new FillAddDataRule();
        processer.addBeforeFinalRule(billAddDataRule);

        // ���������Ϣ
        IRule<CoprodcoefAggVO> addAuditRule = new AddAuditInfoRule();
        processer.addBeforeRule(addAuditRule);

        // ����Ĭ��ֵ
        IRule dataRule = new CoprodcoefAddAuditInfoRule();
        processer.addBeforeFinalRule(dataRule);

        // ���ȼ��
        IRule lengthRule = new FieldLengthCheckRule();
        processer.addBeforeFinalRule(lengthRule);

        processer.addBeforeRule(new CoprodcoefBodyNegativeCheckRule());// ����Ǹ�
    }
}
