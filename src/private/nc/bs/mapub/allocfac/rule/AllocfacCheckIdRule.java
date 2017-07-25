/**
 *
 */
package nc.bs.mapub.allocfac.rule;

import nc.bd.framework.base.CMArrayUtil;
import nc.bs.mapub.allocfac.rule.fac.AllocfacCreatFac;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.allocfac.enumeration.AllocfacEnum;

/**
 * @since v6.3
 * @version 2013-3-14 ÉÏÎç08:49:34
 * @author xionghuic
 */
public class AllocfacCheckIdRule implements IRule<AllocfacAggVO> {

    @Override
    public void process(AllocfacAggVO[] vos) {
        if (CMArrayUtil.isEmpty(vos)) {
            return;
        }
        AllocfacHeadVO hvo = (AllocfacHeadVO) vos[0].getParent();
        Integer ialloctype = hvo.getIalloctype();
        if (ialloctype.equals(AllocfacEnum.costcenter.toIntValue())) {
            AllocfacCreatFac.creatCenterfac().validate(vos);
        }
        else if (ialloctype.equals(AllocfacEnum.product.toIntValue())) {
            AllocfacCreatFac.creatProductfac().validate(vos);
        }
        else if (ialloctype.equals(AllocfacEnum.costclass.toIntValue())) {
            AllocfacCreatFac.creatCostClassfac().validate(vos);
        }
        else if (ialloctype.equals(AllocfacEnum.baseclass.toIntValue())) {
            AllocfacCreatFac.creatBaseClassfac().validate(vos);
        }
        else if (ialloctype.equals(AllocfacEnum.productvbfree.toIntValue())) {
            AllocfacCreatFac.creatProductVbfreefac().validate(vos);
        }
        else if (ialloctype.equals(AllocfacEnum.activity.toIntValue())) {
            AllocfacCreatFac.creatActivity().validate(vos);
        }
        else if (ialloctype.equals(AllocfacEnum.stuff.toIntValue())) {
            AllocfacCreatFac.creatStufffac().validate(vos);
        }
    }
}
