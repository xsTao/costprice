package nc.bs.mapub.driver.rule;

import nc.bd.framework.base.CMArrayUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.DriverAggVO;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 检查是否预置动因
 *
 * @since 6.0
 * @version 2012-8-31 上午10:26:39
 * @author liyjf
 */
@SuppressWarnings("rawtypes")
public class CheckSystemInitRule implements IRule {

    @Override
    public void process(Object[] vos) {
        if (CMArrayUtil.isEmpty(vos)) {
            return;
        }
        for (Object vo : vos) {
            String pk_group = ((DriverVO) ((DriverAggVO) vo).getParent()).getPk_group();

            if (CMDriverLangConst.GLFLAG.equals(pk_group)) {
                ExceptionUtils.wrappBusinessException(CMDriverLangConst.getHINT_DRIVER_SYSTEMINIT());
            }
        }
    }

}
