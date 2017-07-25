package nc.bs.mapub.allocfac.bp;

import nc.bs.mapub.allocfac.plugin.bpplugin.AllocfacPluginPoint;
import nc.bs.mapub.allocfac.rule.AllocfacIsUsedCheck;
import nc.bs.mapub.allocfac.rule.CheckAllocfacIsUsed;
import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;

/**
 * ����ϵ��ɾ��BP
 */
public class AllocfacDeleteBP {

    public void delete(AllocfacAggVO[] bills) {
        // ����ɾ��ģ��
        DeleteBPTemplate<AllocfacAggVO> bp = new DeleteBPTemplate<AllocfacAggVO>(AllocfacPluginPoint.DELETE);

        this.addBeforeRule(bp.getAroundProcesser());
        this.addAfterRule(bp.getAroundProcesser());

        bp.delete(bills);
    }

    /**
     * ɾ��ǰ����
     *
     * @param processor
     */
    private void addBeforeRule(AroundProcesser<AllocfacAggVO> processer) {
    }

    /**
     * ɾ�������
     *
     * @param aroundProcesser
     */
    private void addAfterRule(AroundProcesser<AllocfacAggVO> processer) {
        // ɾ��ǰ�жϳɱ������Ƿ����ô˷���ϵ��
        IRule<AllocfacAggVO> rule = new AllocfacIsUsedCheck();
        processer.addAfterRule(rule);

        processer.addAfterRule(new CheckAllocfacIsUsed());
    }
}
