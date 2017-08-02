/**
 *
 */
package nc.bs.mapub.costpricebase.bp;

import nc.bd.business.rule.AddAuditInfoRule;
import nc.bd.business.rule.DeleteAuditRule;
import nc.bd.business.rule.FillAddDataRule;
import nc.bs.mapub.costpricebase.plugin.bpplugin.CostPriceBasePluginPoint;
import nc.bs.mapub.costpricebase.rule.CostPriceHeadRepeatRule;
import nc.bs.mapub.costpricebase.rule.CostPriceRepeatRule;
import nc.bs.mapub.costpricebase.rule.CostPriceValidateNumRule;
import nc.bs.pubapp.pub.rule.CheckNotNullRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.bs.pubapp.pub.rule.OrgDisabledCheckRule;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.org.IOrgConst;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;
import nc.vo.mapub.costpricebase.entity.CostPriceHeadVO;

/**
 * @since v6.3
 * @version 2017��7��26�� ����9:55:34
 * @author Administrator
 */
public class CostPriceBaseInsertBP {

    public CostPriceAggVO[] insert(CostPriceAggVO[] bills) {
        InsertBPTemplate<CostPriceAggVO> bp = new InsertBPTemplate<CostPriceAggVO>(CostPriceBasePluginPoint.INSERT);
        // ����֮ǰ����
        this.addBeforeRule(bp.getAroundProcesser());
        // ����֮�����
        this.addAfterRule(bp.getAroundProcesser());

        return bp.insert(bills);
    }

    /**
     * @param aroundProcesser
     */
    @SuppressWarnings({
        "unchecked", "rawtypes"
    })
    private void addBeforeRule(AroundProcesser<CostPriceAggVO> processer) {
        // TODO Auto-generated method stub

        // �����岻��Ϊ��

        IRule<CostPriceAggVO> checkNotNullRule = new CheckNotNullRule();
        processer.addBeforeRule(checkNotNullRule);
        // ɾ�������Ϣ�����ڸ��ư�ť������ʱ��Ҫ���������Ϣ���޸���Ϣ��
        IRule deleteAuditRule = new DeleteAuditRule();
        processer.addBeforeRule(deleteAuditRule);
        // ���������Ϣ
        IRule<CostPriceAggVO> addAuditRule = new AddAuditInfoRule();
        processer.addBeforeRule(addAuditRule);
        // У���ֶγ����Ƿ�Ϸ�
        IRule<CostPriceAggVO> fieldLengthCheckRule = new FieldLengthCheckRule();
        processer.addBeforeRule(fieldLengthCheckRule);
        // ����֯���
        processer.addBeforeRule(new OrgDisabledCheckRule(CostPriceHeadVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE));
        // ���ñ����м��š���֯�ͻ���ڼ��ֵ
        IRule<CostPriceAggVO> billAddDataRule = new FillAddDataRule();
        processer.addBeforeFinalRule(billAddDataRule);

        IRule<CostPriceAggVO> checkLegalNullRule = new CostPriceValidateNumRule();
        processer.addBeforeRule(checkLegalNullRule);
        //
        IRule<CostPriceAggVO> distinctCelementsRule = new CostPriceRepeatRule();
        processer.addBeforeRule(distinctCelementsRule);
    }

    /**
     * ���������
     *
     * @param processer
     */
    @SuppressWarnings("unchecked")
    private void addAfterRule(AroundProcesser<CostPriceAggVO> processer) {
        // ����֯���
        IRule<CostPriceAggVO> orgRule = new OrgDisabledCheckRule(CostPriceHeadVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE);
        processer.addAfterRule(orgRule);
        // У���ͷ�۸������Ψһ�ԡ��꡿
        processer.addAfterRule(new CostPriceHeadRepeatRule());
    }
}
