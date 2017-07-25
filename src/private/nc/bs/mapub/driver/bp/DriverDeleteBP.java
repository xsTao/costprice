package nc.bs.mapub.driver.bp;

import nc.bs.mapub.driver.plugin.bpplugin.DriverPluginPoint;
import nc.bs.mapub.driver.rule.CheckDriverIsUsed;
import nc.bs.mapub.driver.rule.CheckSystemInitRule;
import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.mapub.driver.entity.DriverAggVO;

/**
 * ��׼����ɾ��BP
 */
@SuppressWarnings("unchecked")
public class DriverDeleteBP {

    public void delete(DriverAggVO[] bills) {

        DeleteBPTemplate<DriverAggVO> bp = new DeleteBPTemplate<DriverAggVO>(DriverPluginPoint.DELETE);
        // ����ִ��ǰҵ�����
        this.addBeforeRule(bp.getAroundProcesser());
        // ����ִ�к�ҵ�����
        this.addAfterRule(bp.getAroundProcesser());
        bp.delete(bills);
    }

    /**
     * ɾ��ǰҵ�����
     * 
     * @param processer
     */
    private void addBeforeRule(AroundProcesser<DriverAggVO> processer) {
        // Ԥ�ö���У��
        processer.addBeforeRule(new CheckSystemInitRule());

        processer.addBeforeRule(new CheckDriverIsUsed());
    }

    /**
     * ɾ����ҵ�����
     * 
     * @param processer
     */
    private void addAfterRule(AroundProcesser<DriverAggVO> processer) {
    }
}
