package nc.bs.mapub.driver.bp;

import nc.bd.business.rule.AddAuditInfoRule;
import nc.bs.mapub.driver.plugin.bpplugin.DriverPluginPoint;
import nc.bs.mapub.driver.rule.CheckCodeUniqueRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.bs.pubapp.pub.rule.OrgDisabledCheckRule;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.org.IOrgConst;
import nc.vo.mapub.driver.entity.DriverAggVO;
import nc.vo.mapub.driver.entity.DriverVO;

/**
 * ��׼��������BP
 */
public class DriverInsertBP {

    public DriverAggVO[] insert(DriverAggVO[] bills) {

        InsertBPTemplate<DriverAggVO> bp = new InsertBPTemplate<DriverAggVO>(DriverPluginPoint.INSERT);
        // ����ǰ����
        this.addBeforeRule(bp.getAroundProcesser());
        // ���������
        this.addAfterRule(bp.getAroundProcesser());
        return bp.insert(bills);

    }

    /**
     * ���������
     *
     * @param processer
     */
    @SuppressWarnings({
        "rawtypes", "unchecked"
    })
    private void addAfterRule(AroundProcesser<DriverAggVO> processer) {
        // ����֯���
        IRule orgRule = new OrgDisabledCheckRule(DriverVO.PK_ORG, IOrgConst.BUSINESSUNITORGTYPE);
        processer.addAfterRule(orgRule);
    }

    /**
     * ����ǰ����
     *
     * @param processer
     */
    @SuppressWarnings({
        "rawtypes", "unchecked"
    })
    private void addBeforeRule(AroundProcesser<DriverAggVO> processer) {
        // ���ȼ��
        IRule lengthRule = new FieldLengthCheckRule();
        processer.addBeforeFinalRule(lengthRule);

        // ����ʱ���������Ϣ
        processer.addBeforeRule(new AddAuditInfoRule());

        // ����ΨһУ��
        processer.addBeforeRule(new CheckCodeUniqueRule());
    }
}
