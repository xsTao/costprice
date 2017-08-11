/**
 *
 */
package nc.bs.mapub.costpricebase.bp;

import nc.bd.business.rule.FillAddDataRule;
import nc.bd.business.rule.UpdateAuditInfoRule;
import nc.bs.mapub.costpricebase.plugin.bpplugin.CostPriceBasePluginPoint;
import nc.bs.mapub.costpricebase.rule.CostPriceHeadRepeatRule;
import nc.bs.mapub.costpricebase.rule.CostPriceRepeatRule;
import nc.bs.mapub.costpricebase.rule.CostPriceValidateNumRule;
import nc.bs.pubapp.pub.rule.CheckNotNullRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.bs.pubapp.pub.rule.OrgDisabledCheckRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.org.IOrgConst;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;
import nc.vo.mapub.costpricebase.entity.CostPriceHeadVO;

/**
 * @since v6.3
 * @version 2017��7��26�� ����1:56:45
 * @author Administrator
 */
public class CostPriceBaseUpdateBP {

    public CostPriceAggVO[] update(CostPriceAggVO[] bills, CostPriceAggVO[] originBills) {
        UpdateBPTemplate<CostPriceAggVO> bp = new UpdateBPTemplate<CostPriceAggVO>(CostPriceBasePluginPoint.UPDATE);
        // ִ��ǰ����
        this.addBeforeRule(bp.getAroundProcesser());
        // ִ�к����
        this.addAfterRule(bp.getAroundProcesser());
        return bp.update(bills, originBills);
    }

    /**
     * @param aroundProcesser
     */
    @SuppressWarnings({
        "unchecked"
    })
    private void addBeforeRule(CompareAroundProcesser<CostPriceAggVO> processer) {
        // TODO Auto-generated method stub
        // �����岻��Ϊ��
        IRule<CostPriceAggVO> checkNotNullRule = new CheckNotNullRule();
        processer.addBeforeRule(checkNotNullRule);

        // ���������Ϣ
        IRule<CostPriceAggVO> updateAuditRule = new UpdateAuditInfoRule();
        processer.addBeforeRule(updateAuditRule);
        // У���ֶγ����Ƿ�Ϸ�
        IRule<CostPriceAggVO> fieldLengthCheckRule = new FieldLengthCheckRule();
        processer.addBeforeRule(fieldLengthCheckRule);

        // ����֯���
        processer.addBeforeRule(new OrgDisabledCheckRule(CostPriceHeadVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE));
        // ���ñ����м��š���֯�ͻ���ڼ��ֵ
        IRule<CostPriceAggVO> billAddDataRule = new FillAddDataRule();
        processer.addBeforeFinalRule(billAddDataRule);

        // ����ֵ���
        IRule<CostPriceAggVO> checkLegalNullRule = new CostPriceValidateNumRule();
        processer.addBeforeRule(checkLegalNullRule);
        //
        IRule<CostPriceAggVO> distinctCelementsRule = new CostPriceRepeatRule();
        processer.addBeforeRule(distinctCelementsRule);

    }

    /**
     * @param aroundProcesser
     */
    @SuppressWarnings("unchecked")
    private void addAfterRule(CompareAroundProcesser<CostPriceAggVO> processer) {
        // TODO Auto-generated method stub
        IRule<CostPriceAggVO> orgRule = new OrgDisabledCheckRule(CostPriceHeadVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE);
        processer.addAfterRule(orgRule);
        // У���ͷ�۸������Ψһ��
        processer.addAfterRule(new CostPriceHeadRepeatRule());
    }
}
