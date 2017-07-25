package nc.bs.mapub.allocfac.bp;

import nc.bd.business.rule.AddAuditInfoRule;
import nc.bd.business.rule.CheckMaterialRule;
import nc.bd.business.rule.DeleteAuditRule;
import nc.bd.business.rule.FillAddDataRule;
import nc.bs.mapub.allocfac.plugin.bpplugin.AllocfacPluginPoint;
import nc.bs.mapub.allocfac.rule.AllocfacCheckIdRule;
import nc.bs.mapub.allocfac.rule.AllocfacCheckNotNullRule;
import nc.bs.mapub.allocfac.rule.AllocfacCheckVBRule;
import nc.bs.mapub.allocfac.rule.AllocfacSaveRule;
import nc.bs.pubapp.pub.rule.CheckNotNullRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.bs.pubapp.pub.rule.OrgDisabledCheckRule;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.org.IOrgConst;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;

/**
 * ����ϵ������BP
 */
public class AllocfacInsertBP {

    public AllocfacAggVO[] insert(AllocfacAggVO[] bills) {
        InsertBPTemplate<AllocfacAggVO> bp = new InsertBPTemplate<AllocfacAggVO>(AllocfacPluginPoint.INSERT);
        this.addBeforeRule(bp.getAroundProcesser());
        this.addAfterRule(bp.getAroundProcesser());
        return bp.insert(bills);
    }

    /**
     * ����ǰ����
     *
     * @param processor
     */
    @SuppressWarnings({
        "unchecked", "rawtypes"
    })
    private void addBeforeRule(AroundProcesser<AllocfacAggVO> processer) {
        // �����岻��Ϊ��
        IRule<AllocfacAggVO> checkNotNullRule = new CheckNotNullRule();
        processer.addBeforeRule(checkNotNullRule);

        // У�����Ϻ���֯���Ŷ��չ�ϵ
        IRule<AllocfacAggVO> mtrAndOrgRule = new CheckMaterialRule();
        processer.addBeforeRule(mtrAndOrgRule);

        // ����ǰ��� ��鲻��Ϊ���ֶ��Ƿ����Ҫ��
        IRule<AllocfacAggVO> checkLegalNullRule = new AllocfacCheckNotNullRule();
        processer.addBeforeRule(checkLegalNullRule);

        // ������ID�����ظ�
        IRule<AllocfacAggVO> checkIdRule = new AllocfacCheckIdRule();
        processer.addBeforeRule(checkIdRule);

        // �������Ʒ���������Ƿ�ȫ������
        IRule<AllocfacAggVO> checkVBRule = new AllocfacCheckVBRule();
        processer.addBeforeRule(checkVBRule);

        // ���ȼ��
        IRule lengthRule = new FieldLengthCheckRule();
        processer.addBeforeFinalRule(lengthRule);

        // ���ñ����м��š���֯�ͻ���ڼ��ֵ
        IRule<AllocfacAggVO> billAddDataRule = new FillAddDataRule();
        processer.addBeforeFinalRule(billAddDataRule);

        // ɾ�������Ϣ�����ڸ��ư�ť������ʱ��Ҫ���������Ϣ���޸���Ϣ��
        IRule deleteAuditRule = new DeleteAuditRule();
        processer.addBeforeRule(deleteAuditRule);

        // ���������Ϣ
        IRule<AllocfacAggVO> addAuditRule = new AddAuditInfoRule();
        processer.addBeforeRule(addAuditRule);
    }

    /**
     * ���������
     *
     * @param processor
     */
    @SuppressWarnings("unchecked")
    private void addAfterRule(AroundProcesser<AllocfacAggVO> processer) {
        // �������Ƿ���ͬ
        IRule<AllocfacAggVO> rule = new AllocfacSaveRule();
        processer.addAfterRule(rule);

        // ���ܴ��ڵ������ڱ༭ʱ���˰���֯ͣ���ˣ�Ӧ���ڱ���ǰУ����֯�Ƿ�ͣ��
        IRule<AllocfacAggVO> orgRule = new OrgDisabledCheckRule(AllocfacHeadVO.PK_ORG, IOrgConst.FACTORYTYPE);
        processer.addAfterRule(orgRule);

    }
}
