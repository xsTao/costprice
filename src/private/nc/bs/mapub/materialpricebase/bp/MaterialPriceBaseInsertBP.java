package nc.bs.mapub.materialpricebase.bp;

import nc.bd.business.rule.AddAuditInfoRule;
import nc.bd.business.rule.CheckMaterialRule;
import nc.bd.business.rule.DeleteAuditRule;
import nc.bd.business.rule.FillAddDataRule;
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
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.org.IOrgConst;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;

/**
 * ���ϼ۸��
 * ����BP
 *
 * @since 6.36
 * @version 2014-11-10 ����2:43:09
 * @author zhangchd
 */
@SuppressWarnings({
    "unchecked", "rawtypes"
})
public class MaterialPriceBaseInsertBP {
    public MaterialPriceBaseAggVO[] insert(MaterialPriceBaseAggVO[] bills) {
        InsertBPTemplate<MaterialPriceBaseAggVO> bp =
                new InsertBPTemplate<MaterialPriceBaseAggVO>(MaterialPriceBasePluginPoint.INSERT);

        // ����ǰ����
        this.addBeforeRule(bp.getAroundProcesser());
        // ���������
        this.addAfterRule(bp.getAroundProcesser());

        return bp.insert(bills);
    }

    /**
     * ����ǰ����
     *
     * @param processer
     */
    private void addBeforeRule(AroundProcesser<MaterialPriceBaseAggVO> processer) {
        // �����岻��Ϊ��
        IRule<MaterialPriceBaseAggVO> checkNotNullRule = new CheckNotNullRule();
        processer.addBeforeRule(checkNotNullRule);
        // ɾ�������Ϣ�����ڸ��ư�ť������ʱ��Ҫ���������Ϣ���޸���Ϣ��
        IRule deleteAuditRule = new DeleteAuditRule();
        processer.addBeforeRule(deleteAuditRule);
        // ���������Ϣ
        IRule<MaterialPriceBaseAggVO> addAuditRule = new AddAuditInfoRule();
        processer.addBeforeRule(addAuditRule);
        // У���ֶγ����Ƿ�Ϸ�
        IRule<MaterialPriceBaseAggVO> fieldLengthCheckRule = new FieldLengthCheckRule();
        processer.addBeforeRule(fieldLengthCheckRule);
        // ����֯���
        processer
        .addBeforeRule(new OrgDisabledCheckRule(MaterialPriceBaseHeadVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE));
        // ���ñ����м��š���֯�ͻ���ڼ��ֵ
        IRule<MaterialPriceBaseAggVO> billAddDataRule = new FillAddDataRule();
        processer.addBeforeFinalRule(billAddDataRule);
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
                new MaterialPriceBaseMarAssistantRule(MaterialPriceBaseBodyVO.CMATERIALID);
        processer.addBeforeRule(marAssistantRule);
        // 7��У�������ϱ��벻���ظ����꡿
        IRule<MaterialPriceBaseAggVO> distinctCelementsRule = new MaterialPriceRepeatRule();
        processer.addBeforeRule(distinctCelementsRule);
    }

    /**
     * ���������
     *
     * @param processer
     */
    private void addAfterRule(AroundProcesser<MaterialPriceBaseAggVO> processer) {
        // ����֯���
        IRule<MaterialPriceBaseAggVO> orgRule =
                new OrgDisabledCheckRule(MaterialPriceBaseHeadVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE);
        processer.addAfterRule(orgRule);
        // У���ͷ�۸������Ψһ�ԡ��꡿
        processer.addAfterRule(new MaterialPriceHeadRepeatRule());
    }
}
