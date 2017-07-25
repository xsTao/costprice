/**
 *
 */
package nc.bs.mapub.costtype.rule;

import java.util.TimeZone;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMValueCheck;
import nc.bs.uif2.validation.ValidationException;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.costtype.entity.CMMLangConstCM0502;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2015��5��25�� ����1:44:07
 * @author zhangshyb
 */
public class AsBeginAndAsEndRule implements IRule<CostTypeVO> {

    @Override
    public void process(CostTypeVO[] vos) {
        // У�飺Ҫ�������϶��ձ���Ҫ������Ӧ��Ҫ�ر��뵥����Ҫ�ر���һ��
        try {
            if (CMArrayUtil.isNotEmpty(vos)) {
                // ���Ƚ�������У�飬�ж��Ƿ� ʧЧ�ڼ� >= ��Ч�ڼ� ����������¼2010-04-15�޸�
                this.asBeginAndAsEnd(vos);
            }
        }
        catch (ValidationException e) {
            ExceptionUtils.wrappException(e);
        }
    }

    /**
     * ����ʱ��������У�飬�ж��Ƿ� ʧЧ�ڼ� >= ��Ч�ڼ�
     *
     * @param costTypeVOs
     *            CostTypeVO[]
     * @throws ValidationException
     *             �쳣
     */
    public void asBeginAndAsEnd(CostTypeVO[] costTypeVOs) throws ValidationException {
        for (CostTypeVO costTypeVO : costTypeVOs) {
            UFDate beginDate = costTypeVO.getCbeginmonth().asBegin(TimeZone.getTimeZone("GMT+8:00"));
            costTypeVO.setCbeginmonth(beginDate);
            UFDate endDate = costTypeVO.getCendmonth();
            if (CMValueCheck.isEmpty(endDate)) {
                costTypeVO.setCendmonth(UFDate.getDate(CMMLangConstCM0502.MAX_DATE).asEnd(
                        TimeZone.getTimeZone("GMT+8:00")));
            }
            else {
                costTypeVO.setCendmonth(endDate.asEnd(TimeZone.getTimeZone("GMT+8:00")));
            }
        }
    }

}
