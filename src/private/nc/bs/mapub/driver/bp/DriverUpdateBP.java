package nc.bs.mapub.driver.bp;

import nc.bd.business.rule.UpdateAuditInfoRule;
import nc.bs.mapub.driver.plugin.bpplugin.DriverPluginPoint;
import nc.bs.mapub.driver.rule.CheckCodeUniqueRule;
import nc.bs.mapub.driver.rule.CheckSystemInitRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.bs.pubapp.pub.rule.OrgDisabledCheckRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.org.IOrgConst;
import nc.vo.mapub.driver.entity.DriverAggVO;
import nc.vo.mapub.driver.entity.DriverVO;

/**
 * �޸ı����BP
 */
public class DriverUpdateBP {

    public DriverAggVO[] update(DriverAggVO[] bills, DriverAggVO[] originBills) {
        // �����޸�ģ��
        UpdateBPTemplate<DriverAggVO> bp = new UpdateBPTemplate<DriverAggVO>(DriverPluginPoint.UPDATE);
        // ִ��ǰ����
        this.addBeforeRule(bp.getAroundProcesser());
        // ִ�к����
        this.addAfterRule(bp.getAroundProcesser());
        return bp.update(bills, originBills);
    }

    /**
     * �޸ĺ�ҵ�����
     * 
     * @param processer
     */
    @SuppressWarnings({
        "rawtypes", "unchecked"
    })
    private void addAfterRule(CompareAroundProcesser<DriverAggVO> processer) {
        // ����֯���
        IRule orgRule = new OrgDisabledCheckRule(DriverVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE);
        processer.addAfterRule(orgRule);
    }

    /**
     * �޸�ǰҵ�����
     * 
     * @param processer
     */
    @SuppressWarnings({
        "rawtypes", "unchecked"
    })
    private void addBeforeRule(CompareAroundProcesser<DriverAggVO> processer) {
        // ���ȼ��
        IRule lengthRule = new FieldLengthCheckRule();
        processer.addBeforeFinalRule(lengthRule);

        // �����Ϣ
        processer.addBeforeRule(new UpdateAuditInfoRule());

        // Ԥ�ö���У��
        processer.addBeforeRule(new CheckSystemInitRule());

        // ����ΨһУ��
        processer.addBeforeRule(new CheckCodeUniqueRule());
    }
}
