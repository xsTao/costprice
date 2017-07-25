package nc.bs.mapub.allocfac.rule;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.uap.bd.refcheck.IReferenceCheck;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.allocfac.util.CMMLangConstAllocfac;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2015年4月18日 上午10:14:29
 * @author liyjf
 */
public class CheckAllocfacIsUsed implements IRule<AllocfacAggVO> {

    @Override
    public void process(AllocfacAggVO[] vos) {
        try {
            IReferenceCheck refcheck = NCLocator.getInstance().lookup(IReferenceCheck.class);
            for (int i = 0; i < vos.length; i++) {
                AllocfacHeadVO vo = vos[i].getParentVO();
                // 检查档案是否被引用
                if (refcheck.isReferenced(AllocfacHeadVO.getDefaultTableName(), vo.getPrimaryKey())) {
                    ExceptionUtils.wrappBusinessException(CMMLangConstAllocfac.getERR_HAS_USED());
                }

            }
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }

    }
}
