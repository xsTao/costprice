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
 * 取消锁定信息的规则
 *
 * @since 6.0
 * @version 2014-10-8 下午2:42:35
 * @author baoxina
 */
public class CancleLockRule implements IRule<MaterialPriceBaseAggVO> {

    @Override
    public void process(MaterialPriceBaseAggVO[] vos) {
        if (CMArrayUtil.isEmpty(vos)) {
            ExceptionUtils.wrappBusinessException(CMMLangConstMaterialPriceBase.CHECK_NULL_VO());
        }
        Set<String> unlockedMaterial = new HashSet<String>();
        for (MaterialPriceBaseAggVO vo : vos) {
            if (vo.getParentVO().getAttributeValue(MaterialPriceBaseHeadVO.BLOCKINGFLAG).equals(UFBoolean.TRUE)) {
                vo.getParentVO().setAttributeValue(MaterialPriceBaseHeadVO.BLOCKINGFLAG, UFBoolean.FALSE);
            }
            else {
                unlockedMaterial.add((String) vo.getParentVO().getAttributeValue("vpricecode"));
            }
        }
        String[] unlockedMaterialArray = unlockedMaterial.toArray(new String[0]);
        if (CMArrayUtil.isEmpty(unlockedMaterialArray)) {
            return;
        }
        StringBuffer unlockMaterialMsg = new StringBuffer();
        for (int i = 0; i < unlockedMaterialArray.length; i++) {
            if (i < unlockedMaterialArray.length - 1) {
                unlockMaterialMsg.append(unlockedMaterialArray[i]).append(",");
            }
            if (i == unlockedMaterialArray.length - 1) {
                unlockMaterialMsg.append(unlockedMaterialArray[i]);
            }
        }
        ExceptionUtils.wrappBusinessException(CMMLangConstMaterialPriceBase.UNLOCKED_PRICEBASE(unlockMaterialMsg
                .toString()));

    }

}
