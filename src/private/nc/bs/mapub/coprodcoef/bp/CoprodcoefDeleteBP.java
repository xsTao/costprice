package nc.bs.mapub.coprodcoef.bp;

import nc.bs.mapub.coprodcoef.plugin.bpplugin.CoprodcoefPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;

/**
 * ��׼����ɾ��BP
 * 
 * @since 6.0
 * @version 2014-10-11 ����3:11:26
 * @author zhangshyb
 */
public class CoprodcoefDeleteBP {

    public void delete(CoprodcoefAggVO[] bills) {
        DeleteBPTemplate<CoprodcoefAggVO> bp = new DeleteBPTemplate<CoprodcoefAggVO>(CoprodcoefPluginPoint.DELETE);
        // ����ִ��ǰ����
        this.addBeforeRule(bp.getAroundProcesser());
        // ����ִ�к�ҵ�����
        this.addAfterRule(bp.getAroundProcesser());
        bp.delete(bills);
    }

    private void addBeforeRule(AroundProcesser<CoprodcoefAggVO> processer) {
        // processer.addBeforeRule(new SourceTypeCheckRule());// �����Ƶ��ݲ���ɾ�����޸�
    }

    /**
     * ɾ����ҵ�����
     * 
     * @param processer
     */
    private void addAfterRule(AroundProcesser<CoprodcoefAggVO> processer) {
    }
}
