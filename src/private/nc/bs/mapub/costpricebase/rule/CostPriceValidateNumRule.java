/**
 *
 */
package nc.bs.mapub.costpricebase.rule;

import java.util.HashSet;
import java.util.Set;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMValueCheck;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;
import nc.vo.mapub.costpricebase.entity.CostPriceBodyVO;
import nc.vo.mapub.costpricebase.entity.CostPriceHeadVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2017��7��28�� ����9:14:51
 * @author Administrator
 */
public class CostPriceValidateNumRule implements IRule<CostPriceAggVO> {

    /*
     * (non-Javadoc)
     * @see nc.impl.pubapp.pattern.rule.IRule#process(java.lang.Object[])
     */
    @Override
    public void process(CostPriceAggVO[] vos) {
        // TODO Auto-generated method stub
        if (CMArrayUtil.isEmpty(vos)) {
            return;
        }
        // ��֤����ڼ�������Ƿ���һ�����ڣ���֤�����˺���Ҫ�ر���
        this.checkValueRule(vos);

    }

    /**
     * ��֤����ڼ�������Ƿ���һ�����ڣ���֤�����˺���Ҫ�ر���
     *
     * @param vos
     */
    private void checkValueRule(CostPriceAggVO[] vos) {
        // TODO Auto-generated method stub
        Set<String> errorSet = new HashSet<String>();
        ValidationException ve = new ValidationException();
        for (CostPriceAggVO vo : vos) {
            CostPriceHeadVO head = vo.getParentVO();
            CostPriceBodyVO[] body = vo.getItemVO();
            String vperiod = head.getVperiod();
            String annual = head.getAnnual();
            String vpricecode = head.getVpricelibcode();
            // String vpricelibname = head.getVpricelibname();
            if (CMValueCheck.isEmpty(annual) && CMValueCheck.isEmpty(vperiod)) {
                String errMsg = "����ȡ��͡�����ڼ䡿������һ����ֵ";
                if (!errorSet.contains(errMsg)) {
                    errorSet.add(errMsg);
                }
            }
            if (CMValueCheck.isEmpty(vpricecode)) {
                String errMsg = "���۸���롿����";
                if (!errorSet.contains(errMsg)) {
                    errorSet.add(errMsg);
                }
            }

        }

        ValidationFailure failure =
                new ValidationFailure(nc.vo.jcom.lang.StringUtil.getUnionStr(errorSet.toArray(new String[0]), "\n", ""));
        if (CMValueCheck.isNotEmpty(failure)) {
            ve.addValidationFailure(failure);
        }
        if (ve.getFailures() != null && ve.getFailures().size() > 0) {
            ExceptionUtils.wrappException(ve);
        }
    }

}
