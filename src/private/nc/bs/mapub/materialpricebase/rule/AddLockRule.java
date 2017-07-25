package nc.bs.mapub.materialpricebase.rule;

import java.util.HashSet;
import java.util.Set;

import nc.bd.framework.base.CMArrayUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 添加锁定信息的规则
 *
 * @since 6.0
 * @version 2014-10-8 下午2:24:18
 * @author baoxina
 */
public class AddLockRule implements IRule<MaterialPriceBaseAggVO> {

    @Override
    public void process(MaterialPriceBaseAggVO[] vos) {
        if (CMArrayUtil.isEmpty(vos)) {
            ExceptionUtils.wrappBusinessException(CMMLangConstMaterialPriceBase.CHECK_NULL_VO());
        }
        Set<String> lockedMaterial = new HashSet<String>();
        for (MaterialPriceBaseAggVO vo : vos) {
            if (vo.getParentVO().getAttributeValue(MaterialPriceBaseHeadVO.BLOCKINGFLAG).equals(UFBoolean.FALSE)) {
                vo.getParentVO().setAttributeValue(MaterialPriceBaseHeadVO.BLOCKINGFLAG, UFBoolean.TRUE);
            }
            else {
                lockedMaterial.add((String) vo.getParentVO().getAttributeValue("vpricecode"));
            }
        }
        String[] lockedMaterialArray = lockedMaterial.toArray(new String[0]);
        if (CMArrayUtil.isEmpty(lockedMaterialArray)) {
            return;
        }
        StringBuffer lockMaterialMsg = new StringBuffer();
        for (int i = 0; i < lockedMaterialArray.length; i++) {
            if (i < lockedMaterialArray.length - 1) {
                lockMaterialMsg.append(lockedMaterialArray[i]).append(",");
            }
            if (i == lockedMaterialArray.length - 1) {
                lockMaterialMsg.append(lockedMaterialArray[i]);
            }
        }
        ExceptionUtils
        .wrappBusinessException(CMMLangConstMaterialPriceBase.LOCKED_PRICEBASE(lockMaterialMsg.toString()));

    }

}
