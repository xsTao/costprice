package nc.bs.mapub.coprodcoef.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;
import nc.vo.pubapp.util.AuditInfoUtils;

public class CoprodcoefAddAuditInfoRule implements IRule<CoprodcoefAggVO> {

    @Override
    public void process(CoprodcoefAggVO[] vos) {
        AuditInfoUtils.setAddAuditInfo(vos);

    }

}
