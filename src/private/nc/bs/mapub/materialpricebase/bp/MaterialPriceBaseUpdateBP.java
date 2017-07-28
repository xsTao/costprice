package nc.bs.mapub.materialpricebase.bp;

import nc.bd.business.rule.CheckMaterialRule;
import nc.bd.business.rule.FillUpdateDataRule;
import nc.bd.business.rule.UpdateAuditInfoRule;
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
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.org.IOrgConst;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;

/**
 * ���ϼ۸��
 * �޸�BP
 *
 * @since 6.36
 * @version 2014-11-10 ����2:50:20
 * @author zhangchd
 */
public class MaterialPriceBaseUpdateBP {

    public MaterialPriceBaseAggVO[] update(MaterialPriceBaseAggVO[] bills, MaterialPriceBaseAggVO[] originBills) {
        // �����޸�ģ��
        UpdateBPTemplate<MaterialPriceBaseAggVO> bp =
                new UpdateBPTemplate<MaterialPriceBaseAggVO>(MaterialPriceBasePluginPoint.UPDATE);
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

        // ���������Ϣ
        IRule<MaterialPriceBaseAggVO> updateAuditInfoRule = new UpdateAuditInfoRule();
        processer.addBeforeFinalRule(updateAuditInfoRule);

        // У���ֶγ����Ƿ�Ϸ�
        IRule<MaterialPriceBaseAggVO> fieldLengthCheckRule = new FieldLengthCheckRule();
        processer.addBeforeRule(fieldLengthCheckRule);
        // ����֯���
        processer
                .addBeforeRule(new OrgDisabledCheckRule(MaterialPriceBaseHeadVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE));
        // ���ñ����м��š���֯�ͻ���ڼ��ֵ
        IRule<MaterialPriceBaseAggVO> billUpdateDataRule = new FillUpdateDataRule();
        processer.addBeforeFinalRule(billUpdateDataRule);
        // ��������Ƿ���䵽����֯
        IRule<MaterialPriceBaseAggVO> checkMaterialRule = new CheckMaterialRule();
        processer.addBeforeRule(checkMaterialRule);
        // 1����֤��ͷ�۸����롢�۸�����ơ����֡���Ч����
        // 2����֤������ϱ��롢����(�۸���ԴΪ���ֹ�¼��) ���꡿
        IRule<MaterialPriceBaseAggVO> checkLegalNullRule = new MaterialPriceValidateNumRule();
        processer.addBeforeRule(checkLegalNullRule);
        // 3��У��ʧЧʱ���Ƿ���ڻ������Чʱ�䡾�꡿
        IRule<MaterialPriceBaseAggVO> checkDateValidate = new CheckDateValidate();
        processer.addBeforeRule(checkDateValidate);
        // 5��������Ӧ�Ĳ�����֯���˲���Ϊ��
        IRule<MaterialPriceBaseAggVO> accountSchemeRule = new AccountSchemeRule();
        processer.addBeforeRule(accountSchemeRule);
        // 6���������ϸ�������У��
        IRule<MaterialPriceBaseAggVO> marAssistantRule =
                new MaterialPriceBaseMarAssistantRule<MaterialPriceBaseAggVO>(MaterialPriceBaseBodyVO.CMATERIALID);
        processer.addBeforeRule(marAssistantRule);
        // 7��У�������ϱ��벻���ظ����꡿
        IRule<MaterialPriceBaseAggVO> distinctCelementsRule = new MaterialPriceRepeatRule();
        processer.addBeforeRule(distinctCelementsRule);
    }

    /**
     * �޸ĺ�ҵ�����
     *
     * @param processer
     */
    @SuppressWarnings({
        "unchecked"
    })
    private void addAfterRule(CompareAroundProcesser<MaterialPriceBaseAggVO> processer) {
        // ���ܴ��ڵ������ڱ༭ʱ���˰���֯ͣ����
        IRule<MaterialPriceBaseAggVO> orgRule =
                new OrgDisabledCheckRule(MaterialPriceBaseHeadVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE);
        processer.addAfterRule(orgRule);
        // У���ͷ�۸������Ψһ�ԡ��꡿
        processer.addAfterRule(new MaterialPriceHeadRepeatRule());
        // �������ϸ�������У��
        // IRule<MaterialPriceBaseAggVO> cmMarAssistantRule = new
        // CMMarAssistantRule(MaterialPriceBaseBodyVO.CMATERIALID);
        // processer.addAfterRule(cmMarAssistantRule);
    }
}
