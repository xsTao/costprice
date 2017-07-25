package nc.bs.mapub.costtype.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.costtype.entity.CMMLangConstCM0502;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class CheckDefaultCostTypeRule implements IRule<CostTypeVO> {

    @Override
    public void process(CostTypeVO[] vos) {
        int count = 0;
        if (vos != null && vos.length > 0) {
            // 有且仅有一个默认成本类型
            for (CostTypeVO vo : vos) {
                if (vo != null) {
                    UFBoolean isDefault = vo.getBdefault();
                    if (isDefault.equals(UFBoolean.TRUE)) {
                        count++;
                    }
                }
            }

            if (count != 1) {
                ExceptionUtils.wrappBusinessException(CMMLangConstCM0502.GET_ERRO_BDEFAULT());
            }

        }

    }

}
