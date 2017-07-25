package nc.bs.mapub.materialpricebase.bp;

import nc.bd.business.rule.CheckMaterialRule;
import nc.bd.business.rule.UpdateAuditInfoRule;
import nc.bs.mapub.materialpricebase.plugin.bpplugin.MaterialPriceBasePluginPoint;
import nc.bs.mapub.materialpricebase.rule.AccountSchemeRule;
import nc.bs.mapub.materialpricebase.rule.AddLockRule;
import nc.bs.pubapp.pub.rule.CheckNotNullRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.bs.pubapp.pub.rule.FillUpdateDataRule;
import nc.bs.pubapp.pub.rule.OrgDisabledCheckRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.org.IOrgConst;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;

public class MaterialPriceBaseLockBP {
    public MaterialPriceBaseAggVO[] lock(MaterialPriceBaseAggVO[] bills, MaterialPriceBaseAggVO[] originBills) {
        // �����޸�ģ��
        UpdateBPTemplate<MaterialPriceBaseAggVO> bp =
                new UpdateBPTemplate<MaterialPriceBaseAggVO>(MaterialPriceBasePluginPoint.LOCK);
        // ִ��ǰ����
        this.addBeforeRule(bp.getAroundProcesser());
        // ִ�к����
        this.addAfterRule(bp.getAroundProcesser());
        return bp.update(bills, originBills);
    }

    /**
     * �޸�ǰҵ�����
     * 
     * @param processer
     */
    @SuppressWarnings("unchecked")
    private void addBeforeRule(CompareAroundProcesser<MaterialPriceBaseAggVO> processer) {
        // �����岻��Ϊ��
        IRule<MaterialPriceBaseAggVO> checkNotNullRule = new CheckNotNullRule();
        processer.addBeforeRule(checkNotNullRule);

        // ��������Ƿ���䵽����֯
        IRule<MaterialPriceBaseAggVO> checkMaterialRule = new CheckMaterialRule();
        processer.addBeforeRule(checkMaterialRule);

        // У���ֶγ����Ƿ�Ϸ�
        IRule<MaterialPriceBaseAggVO> fieldLengthCheckRule = new FieldLengthCheckRule();
        processer.addBeforeRule(fieldLengthCheckRule);

        // ���ñ����м��š���֯�ͻ���ڼ��ֵ
        IRule<MaterialPriceBaseAggVO> billUpdateDataRule = new FillUpdateDataRule();
        processer.addBeforeFinalRule(billUpdateDataRule);

        // ����������Ϣ
        IRule<MaterialPriceBaseAggVO> addLockRule = new AddLockRule();
        processer.addBeforeFinalRule(addLockRule);

        // ���������Ϣ
        IRule<MaterialPriceBaseAggVO> updateAuditInfoRule = new UpdateAuditInfoRule();
        processer.addBeforeFinalRule(updateAuditInfoRule);
    }

    /**
     * �޸ĺ�ҵ�����
     * 
     * @param processer
     */
    @SuppressWarnings("unchecked")
    private void addAfterRule(CompareAroundProcesser<MaterialPriceBaseAggVO> processer) {

        // ������Ӧ�Ĳ�����֯���˲���Ϊ��
        IRule<MaterialPriceBaseAggVO> accountSchemeRule = new AccountSchemeRule();
        processer.addAfterRule(accountSchemeRule);

        // ���ܴ��ڵ������ڱ༭ʱ���˰���֯ͣ����
        IRule<MaterialPriceBaseAggVO> orgRule =
                new OrgDisabledCheckRule(MaterialPriceBaseHeadVO.PK_ORG, IOrgConst.FACTORYTYPE);
        processer.addAfterRule(orgRule);

    }

}
