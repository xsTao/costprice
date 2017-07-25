/**
 *
 */
package nc.bs.mapub.allocfac.rule.fac;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.uapbd.IMaterialCostClassPubService;
import nc.vo.bd.material.marcostclass.MarCostClassVO;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.mapub.allocfac.util.CMMLangConstAllocfac;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2013-3-14 ÉÏÎç09:07:20
 * @author xionghuic
 */
public class AllocfacCostClassCheckFac extends AllocfacCheckFac {

    @Override
    public Set<String> getIdSet(AllocfacItemVO[] itemVOS) {
        Set<String> repSet = new HashSet<String>();
        Set<String> idSet = new HashSet<String>();
        for (int i = 0; i < itemVOS.length; i++) {
            if (repSet.contains(itemVOS[i].getCmarcostclassid())) {
                idSet.add(itemVOS[i].getCmarcostclassid());
            }
            else {
                repSet.add(itemVOS[i].getCmarcostclassid());
            }
        }
        return idSet;
    }

    @Override
    public Set<String> getCode(Set<String> idSet) {
        Set<String> codeSet = new HashSet<String>();
        Map<String, MarCostClassVO> idToVOMap = null;
        try {
            idToVOMap =
                    NCLocator.getInstance().lookup(IMaterialCostClassPubService.class)
                            .queryMarCostClassByIDs(idSet.toArray(new String[0]));
            for (MarCostClassVO vo : idToVOMap.values()) {
                codeSet.add(vo.getCode());
            }
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
        return codeSet;
    }

    @Override
    public void showWrongInfo(String mes) {
        ExceptionUtils.wrappBusinessException(String.format(CMMLangConstAllocfac.SAME_COSTCLASS_ERRO(), mes));
    }
}
