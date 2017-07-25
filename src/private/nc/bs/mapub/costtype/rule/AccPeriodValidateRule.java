/**
 *
 */
package nc.bs.mapub.costtype.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bd.framework.base.CMArrayUtil;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2015��5��18�� ����9:22:43
 * @author zhangshyb
 */
public class AccPeriodValidateRule implements IRule<CostTypeVO> {

    @Override
    public void process(CostTypeVO[] vos) {
        // У�飺Ҫ�������϶��ձ���Ҫ������Ӧ��Ҫ�ر��뵥����Ҫ�ر���һ��
        try {
            if (CMArrayUtil.isNotEmpty(vos)) {
                // ���Ƚ�������У�飬�ж��Ƿ� ʧЧ�ڼ� >= ��Ч�ڼ� ����������¼2010-04-15�޸�
                this.accPeriodValidate(vos);
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
    public void accPeriodValidate(CostTypeVO[] costTypeVOs) throws ValidationException {

        List<ValidationFailure> failure = new ArrayList<ValidationFailure>();
        StringBuffer errStrNum = new StringBuffer();

        // ����У�飬ֻҪ�����д���ģ��������������
        for (int i = 0; i < costTypeVOs.length; i++) {
            UFDate beginDate = costTypeVOs[i].getCbeginmonth();
            UFDate endDate = costTypeVOs[i].getCendmonth();
            // t1����Ҫ�пմ�����Ϊt1���Ƚ��б���ķǿ�У�飬�˴�t1�طǿ�
            int compResult = beginDate.compareTo(endDate);
            if (compResult > 0) {
                // Ϊ���������к���׼��,ÿ���кź�����","������1,2,3(��)
                errStrNum.append(costTypeVOs[i].getVcosttypecode() + "[" + costTypeVOs[i].getVcosttypename() + "]");
                errStrNum.append(",");
            }
        }

        if (errStrNum.length() > 0) {
            errStrNum.deleteCharAt(errStrNum.length() - 1); // Ҫȥ�����һ��","

            // ���������Ĵ��󾯸���Ϣ,����У�龯��
            failure.add(new ValidationFailure(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                    "03810006-0312")/*
                     * @res
                     * "����[��Ч�ڼ�]��������[ʧЧ�ڼ�]��"
                     */
                    + errStrNum.toString()));
            throw new ValidationException(failure); // У�龯�����
        }

    }

}
