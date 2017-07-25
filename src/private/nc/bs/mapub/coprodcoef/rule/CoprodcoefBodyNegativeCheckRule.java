package nc.bs.mapub.coprodcoef.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.coprodcoef.entity.CMMLangConstCoprodcoef;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefItemVO;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ���帺�����
 *
 * @since 6.0
 * @version 2014-10-11 ����7:30:29
 * @author zhangshyb
 */
public class CoprodcoefBodyNegativeCheckRule implements IRule<CoprodcoefAggVO> {

    @Override
    public void process(CoprodcoefAggVO[] bills) {
        // ���ϵ������Ϊ����
        this.negativeCheck(bills);
    }

    private void negativeCheck(CoprodcoefAggVO[] bills) {
        for (CoprodcoefAggVO bill : bills) {
            for (IVOMeta meta : bill.getMetaData().getChildren()) {
                for (int i = 0; bill.getChildren(meta) != null && i < bill.getChildren(meta).length; i++) {
                    ISuperVO childvo = bill.getChildren(meta)[i];
                    // ���ϵ������Ϊ����
                    if (childvo.getAttributeValue(CoprodcoefItemVO.NRELCOEFFICIENT) != null
                            && Double.parseDouble(childvo.getAttributeValue(CoprodcoefItemVO.NRELCOEFFICIENT)
                                    .toString()) < 0) {
                        ExceptionUtils.wrappBusinessException(CMMLangConstCoprodcoef.getMSG_OPPOSITESUM());
                    }
                }
            }
        }
    }
}
