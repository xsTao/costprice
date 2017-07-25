package nc.bs.mapub.driver.rule;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.uap.bd.refcheck.IReferenceCheck;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.DriverAggVO;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2013-2-27 上午10:35:42
 * @author xionghuic
 *         modified by shangzhm1 at 2015-3-24 档案被引用校验
 * @since v6.36
 */
public class CheckDriverIsUsed implements IRule<DriverAggVO> {

    @Override
    public void process(DriverAggVO[] vos) {
        try {
            IReferenceCheck refcheck = NCLocator.getInstance().lookup(IReferenceCheck.class);
            for (int i = 0; i < vos.length; i++) {
                DriverVO vo = vos[i].getParentVO();
                // 检查档案是否被引用
                if (refcheck.isReferenced(DriverVO.getDefaultTableName(), vo.getPrimaryKey())) {
                    ExceptionUtils.wrappBusinessException(CMDriverLangConst.getUSED_IN_ALLOCDEF());
                }

            }
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }

    }
}
