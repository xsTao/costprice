package nc.bs.mapub.allocfac.rule.fac;

import java.util.HashSet;
import java.util.Set;

import nc.bd.business.util.FIUtil;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.mapub.allocfac.util.CMMLangConstAllocfac;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.resa.costcenter.CostCenterVO;

/**
 * @since v6.3
 * @version 2013-3-14 ÉÏÎç09:02:31
 * @author xionghuic
 */
public class AllocfacCenterCheckFac extends AllocfacCheckFac {

    @Override
    public Set<String> getIdSet(AllocfacItemVO[] itemVOS) {
        Set<String> repSet = new HashSet<String>();
        Set<String> idSet = new HashSet<String>();
        for (int i = 0; i < itemVOS.length; i++) {
            if (repSet.contains(itemVOS[i].getCcostcenterid())) {
                idSet.add(itemVOS[i].getCcostcenterid());
            }
            else {
                repSet.add(itemVOS[i].getCcostcenterid());
            }
        }
        return idSet;
    }

    @Override
    public Set<String> getCode(Set<String> idSet) {
        Set<String> codeSet = new HashSet<String>();
        CostCenterVO[] vos = null;
        try {
            vos = FIUtil.queryCostCenterVOByPks(idSet.toArray(new String[0]), new String[] {
                "cccode"
            });
            for (CostCenterVO vo : vos) {
                codeSet.add(vo.getCccode());
            }
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
        return codeSet;
    }

    @Override
    public void showWrongInfo(String mes) {
        ExceptionUtils.wrappBusinessException(String.format(CMMLangConstAllocfac.SAME_CENTER_ERRO(), mes));
    }
}
