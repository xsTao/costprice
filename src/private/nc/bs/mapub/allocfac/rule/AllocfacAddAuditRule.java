package nc.bs.mapub.allocfac.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.pubapp.util.AuditInfoUtils;

/**
 * ���Ӵ�����Ϣ
 */
public class AllocfacAddAuditRule implements IRule<AllocfacAggVO> {

    @Override
    public void process(AllocfacAggVO[] vos) {
        if (vos == null || vos.length == 0) {
            return;
        }
        AuditInfoUtils.setAddAuditInfo(vos);
    }
}
