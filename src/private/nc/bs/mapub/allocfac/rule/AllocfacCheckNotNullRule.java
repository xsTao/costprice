/**
 *
 */
package nc.bs.mapub.allocfac.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bd.framework.base.CMStringUtil;
import nc.bd.framework.base.CMValueCheck;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.mapub.allocfac.enumeration.AllocfacEnum;
import nc.vo.mapub.allocfac.util.CMMLangConstAllocfac;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 保存前检查
 * 检查不能为空字段是否符合要求
 *
 * @since 6.3
 * @version 2012-09-03
 * @author xionghuic
 */
public class AllocfacCheckNotNullRule implements IRule<AllocfacAggVO> {

    @Override
    public void process(AllocfacAggVO[] vos) {
        for (AllocfacAggVO aggvo : vos) {
            AllocfacHeadVO hvo = (AllocfacHeadVO) aggvo.getParent();
            Integer ialloctype = hvo.getIalloctype();
            // 验证不能为空的字段 是否符合要求
            this.nullValidate(aggvo, ialloctype);
        }
    }

    /**
     * 验证不能为空的字段
     */
    private void nullValidate(AllocfacAggVO aggvo, Integer ialloctype) {
        String msg = "";
        AllocfacHeadVO headVO = aggvo.getParentVO();
        if (CMStringUtil.isEmpty(headVO.getVcode())) {
            ExceptionUtils.wrappBusinessException(CMMLangConstAllocfac.getNULL_HEAD_CODE());
        }
        if (CMStringUtil.isEmpty(headVO.getVname())) {
            ExceptionUtils.wrappBusinessException(CMMLangConstAllocfac.getNULL_HEAD_NAME());
        }
        AllocfacItemVO[] itemVOS = (AllocfacItemVO[]) aggvo.getChildren(AllocfacItemVO.class);
        if (itemVOS == null || itemVOS.length == 0) {
            return;
        }
        if (ialloctype.equals(AllocfacEnum.costcenter.toIntValue())) {
            for (int i = 0; i < itemVOS.length; i++) {
                if (CMStringUtil.isEmpty(itemVOS[i].getCcostcenterid())) {
                    msg = CMMLangConstAllocfac.getNULL_BODY_COSTCENTER();
                    break;
                }
            }
        }
        else if (ialloctype.equals(AllocfacEnum.product.toIntValue())) {
            for (int i = 0; i < itemVOS.length; i++) {
                if (CMStringUtil.isEmpty(itemVOS[i].getCmaterialid())) {
                    msg = CMMLangConstAllocfac.getNULL_BODY_METERIALID();
                    break;
                }
            }
        }
        else if (ialloctype.equals(AllocfacEnum.costclass.toIntValue())) {
            for (int i = 0; i < itemVOS.length; i++) {
                if (CMStringUtil.isEmpty(itemVOS[i].getCmarcostclassid())) {
                    msg = CMMLangConstAllocfac.getNULL_BODY_MARCOSTCLASSID();
                    break;
                }
            }
        }
        else if (ialloctype.equals(AllocfacEnum.productvbfree.toIntValue())) {
            for (int i = 0; i < itemVOS.length; i++) {
                if (CMStringUtil.isEmpty(itemVOS[i].getCmaterialid())) {
                    msg = CMMLangConstAllocfac.getNULL_BODY_METERIALID();
                    break;
                }
            }
        }
        else if (ialloctype.equals(AllocfacEnum.activity.toIntValue())) {
            for (int i = 0; i < itemVOS.length; i++) {
                if (CMStringUtil.isEmpty(itemVOS[i].getCactivityid())) {
                    msg = CMMLangConstAllocfac.getNULL_BODY_ACTIVITYID();
                    break;
                }
            }
        }
        else if (ialloctype.equals(AllocfacEnum.stuff.toIntValue())) {
            for (int i = 0; i < itemVOS.length; i++) {
                if (CMStringUtil.isEmpty(itemVOS[i].getCstuffid())) {
                    msg = CMMLangConstAllocfac.getNULL_BODY_STUFFID();
                    break;
                }
            }
        }
        else if (ialloctype.equals(AllocfacEnum.baseclass.toIntValue())) {
            for (int i = 0; i < itemVOS.length; i++) {
                if (CMStringUtil.isEmpty(itemVOS[i].getCmarbaseclassid())) {
                    msg = CMMLangConstAllocfac.getNULL_BODY_STUFFID();
                    break;
                }
            }
        }
        else {
            this.checkFactor(itemVOS);
            // msg = CMMLangConstAllocfac.getNULL_BODY_ALL();
        }

        /**
         * 抛出异常信息
         */
        if (CMStringUtil.isNotEmpty(msg)) {
            ExceptionUtils.wrappBusinessException(msg);
        }
    }

    /**
     * 系数为空校验
     *
     * @param itemVOs
     * @author shangzhm1
     */
    private void checkFactor(AllocfacItemVO[] itemVOs) {
        List<ValidationFailure> errorList = new ArrayList<ValidationFailure>();
        for (int i = 0; itemVOs.length > 0; i++) {
            if (itemVOs[i] == null) {
                continue;
            }
            UFDouble nfactor = itemVOs[i].getNfactor();
            if (CMValueCheck.isEmpty(nfactor)) {
                String errMsg = String.format(CMMLangConstAllocfac.getERR_FAC_NULL(), i + 1);
                errorList.add(new ValidationFailure(errMsg));
            }
        }
        if (errorList.size() > 0) {
            ExceptionUtils.wrappException(new ValidationException(errorList));
        }
    }
}
