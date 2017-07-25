package nc.bs.mapub.allocfac.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.mapub.driver.cm.pub.IDriverQueryForPub;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.allocfac.util.CMMLangConstAllocfac;
import nc.vo.mapub.driver.entity.CMDriverParameterEnum;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2013-2-27 ÉÏÎç09:45:56
 * @author xionghuic
 */
public class AllocfacIsUsedCheck implements IRule<AllocfacAggVO> {

    @Override
    public void process(AllocfacAggVO[] vos) {
        if (vos == null || vos.length == 0) {
            return;
        }
        String pk_org = vos[0].getParentVO().getPk_org();
        List<String> idList = new ArrayList<String>();
        for (AllocfacAggVO vo : vos) {
            AllocfacHeadVO hvo = (AllocfacHeadVO) vo.getParent();
            idList.add(hvo.getCallocfacid());
        }
        boolean isLegal = false;
        try {
            isLegal =
                    NCLocator.getInstance().lookup(IDriverQueryForPub.class)
                    .isUsedInDriver(pk_org, CMDriverParameterEnum.ALLOC_FAC.getCode(), idList);
            if (!isLegal) {
                ExceptionUtils.wrappBusinessException(CMMLangConstAllocfac.getUSED_IN_DRIVER());
            }

        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
    }
}
