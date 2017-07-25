/**
 * 
 */
package nc.bs.mapub.allocfac.rule.fac;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.mapub.allocfac.rule.fac.AllocfacCheckFac;
import nc.bs.framework.common.NCLocator;
import nc.pubitf.uapbd.IMaterialPubService_C;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.mapub.allocfac.util.CMMLangConstAllocfac;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @author liyjf
 */
public class AllocfacStuffCheckFac extends AllocfacCheckFac {

    @Override
    public Set<String> getIdSet(AllocfacItemVO[] itemVOS) {
        Set<String> repSet = new HashSet<String>();
        Set<String> idSet = new HashSet<String>();
        for (int i = 0; i < itemVOS.length; i++) {
            if (repSet.contains(itemVOS[i].getCstuffid())) {
                idSet.add(itemVOS[i].getCstuffid());
            }
            else {
                repSet.add(itemVOS[i].getCstuffid());
            }
        }
        return idSet;
    }

    @Override
    public Set<String> getCode(Set<String> idSet) {
        Map<String, MaterialVO> idToVO = new HashMap<String, MaterialVO>();
        try {
            idToVO =
                    NCLocator.getInstance().lookup(IMaterialPubService_C.class)
                            .queryMaterialBaseInfoByPks(idSet.toArray(new String[0]), new String[] {
                                "code"
                            });
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
        Set<String> codeSet = new HashSet<String>();
        for (MaterialVO vo : idToVO.values()) {
            codeSet.add(vo.getCode());
        }
        return codeSet;
    }

    @Override
    public void showWrongInfo(String mes) {
        ExceptionUtils.wrappBusinessException(String.format(CMMLangConstAllocfac.SAME_STUFF_ERRO(), mes));
    }
}
