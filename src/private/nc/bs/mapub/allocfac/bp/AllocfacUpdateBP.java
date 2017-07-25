/**
 *
 */
package nc.bs.mapub.allocfac.bp;

import nc.bd.business.rule.CheckMaterialRule;
import nc.bd.business.rule.UpdateAuditInfoRule;
import nc.bs.mapub.allocfac.plugin.bpplugin.AllocfacPluginPoint;
import nc.bs.mapub.allocfac.rule.AllocfacCheckIdRule;
import nc.bs.mapub.allocfac.rule.AllocfacCheckNotNullRule;
import nc.bs.mapub.allocfac.rule.AllocfacCheckVBRule;
import nc.bs.mapub.allocfac.rule.AllocfacSaveRule;
import nc.bs.pubapp.pub.rule.CheckNotNullRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.bs.pubapp.pub.rule.FillUpdateDataRule;
import nc.bs.pubapp.pub.rule.OrgDisabledCheckRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.org.IOrgConst;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;

/**
 * ����ϵ���޸�BP
 */
public class AllocfacUpdateBP {

    public AllocfacAggVO[] update(AllocfacAggVO[] bills, AllocfacAggVO[] originBills) {
        // �����޸�ģ��
        UpdateBPTemplate<AllocfacAggVO> bp = new UpdateBPTemplate<AllocfacAggVO>(AllocfacPluginPoint.UPDATE);
        this.addBeforeRule(bp.getAroundProcesser());
        this.addAfterRule(bp.getAroundProcesser());
        return bp.update(bills, originBills);
    }

    @SuppressWarnings({
        "unchecked", "rawtypes"
    })
    private void addBeforeRule(CompareAroundProcesser<AllocfacAggVO> processer) {
        // �����岻��Ϊ��
        IRule<AllocfacAggVO> checkNotNullRule = new CheckNotNullRule();
        processer.addBeforeRule(checkNotNullRule);

        // У�����Ϻ���֯���Ŷ��չ�ϵ
        IRule<AllocfacAggVO> mtrAndOrgRule = new CheckMaterialRule();
        processer.addBeforeRule(mtrAndOrgRule);
        // �������Ƿ���ͬ
        IRule<AllocfacAggVO> rule = new AllocfacSaveRule();
        processer.addBeforeRule(rule);

        // ����ǰ��� ��鲻��Ϊ���ֶ��Ƿ����Ҫ��
        // IRule<AllocfacAggVO> checkLegalNullRule = new AllocfacCheckNotNullRule();
        // processer.addBeforeRule(checkLegalNullRule);
        //

        // �޸�ʱ�жϳɱ������Ƿ����ô˷���ϵ��
        // IRule<AllocfacAggVO> rule = new AllocfacIsUsedCheck();
        // processer.addBeforeRule(rule);

        // ������ID�����ظ�
        IRule<AllocfacAggVO> checkIdRule = new AllocfacCheckIdRule();
        processer.addBeforeRule(checkIdRule);

        // �������Ʒ���������Ƿ�ȫ������
        IRule<AllocfacAggVO> checkVBRule = new AllocfacCheckVBRule();
        processer.addBeforeRule(checkVBRule);

        // ����֯���
        IRule orgRule = new OrgDisabledCheckRule(AllocfacHeadVO.PK_ORG, IOrgConst.FACTORYTYPE);
        processer.addBeforeFinalRule(orgRule);

        // ���ȼ��
        IRule lengthRule = new FieldLengthCheckRule();
        processer.addBeforeFinalRule(lengthRule);

        // ���ñ����м��š���֯�ͻ���ڼ��ֵ
        IRule<AllocfacAggVO> fillUpdateDataRule = new FillUpdateDataRule();
        processer.addBeforeFinalRule(fillUpdateDataRule);

        // ���������Ϣ
        IRule<AllocfacAggVO> updateAuditInfoRule = new UpdateAuditInfoRule();
        processer.addBeforeRule(updateAuditInfoRule);
    }

    /**
     * @param processer
     */
    private void addAfterRule(CompareAroundProcesser<AllocfacAggVO> processer) {

        // ������� ��鲻��Ϊ���ֶ��Ƿ����Ҫ��
        IRule<AllocfacAggVO> checkLegalNullRule = new AllocfacCheckNotNullRule();
        processer.addAfterRule(checkLegalNullRule);
    }
}
