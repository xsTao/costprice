/**
 *
 */
package nc.bs.mapub.allocfac.rule.fac;

import java.util.HashSet;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.uapbd.IMaterialBaseClassPubService;
import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.mapub.allocfac.util.CMMLangConstAllocfac;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2013-3-14 ÉÏÎç09:07:20
 * @author xionghuic
 */
public class AllocfacBaseClassCheckFac extends AllocfacCheckFac {

    @Override
    public Set<String> getIdSet(AllocfacItemVO[] itemVOS) {
        Set<String> repSet = new HashSet<String>();
        Set<String> idSet = new HashSet<String>();
        for (int i = 0; i < itemVOS.length; i++) {
            if (repSet.contains(itemVOS[i].getCmarbaseclassid())) {
                idSet.add(itemVOS[i].getCmarbaseclassid());
            }
            else {
                repSet.add(itemVOS[i].getCmarbaseclassid());
            }
        }
        return idSet;
    }

    @Override
    public Set<String> getCode(Set<String> idSet) {
        Set<String> codeSet = new HashSet<String>();
        try {
            MarBasClassVO[] vos =
                    NCLocator.getInstance().lookup(IMaterialBaseClassPubService.class)
                            .queryMaterialBaseClassByPks(idSet.toArray(new String[0]), false);
            for (MarBasClassVO vo : vos) {
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
        ExceptionUtils.wrappBusinessException(String.format(CMMLangConstAllocfac.SAME_BASECLASS_ERRO(), mes));
    }
}
