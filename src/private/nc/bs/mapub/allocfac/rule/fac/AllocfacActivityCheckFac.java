/**
 *
 */
package nc.bs.mapub.allocfac.rule.fac;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.bd.bdactivity.pub.IBDActivityPubQuery;
import nc.vo.bd.bdactivity.entity.BDActivityVO;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.mapub.allocfac.util.CMMLangConstAllocfac;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2013-3-14 ÉÏÎç09:07:50
 * @author xionghuic
 */
public class AllocfacActivityCheckFac extends AllocfacCheckFac {

    @Override
    public Set<String> getIdSet(AllocfacItemVO[] itemVOS) {
        Set<String> repSet = new HashSet<String>();
        Set<String> idSet = new HashSet<String>();
        for (int i = 0; i < itemVOS.length; i++) {
            if (repSet.contains(itemVOS[i].getCactivityid())) {
                idSet.add(itemVOS[i].getCactivityid());
            }
            else {
                repSet.add(itemVOS[i].getCactivityid());
            }
        }
        return idSet;
    }

    @Override
    public Set<String> getCode(Set<String> idSet) {
        Set<String> codeSet = new HashSet<String>();
        BDActivityVO[] listVO = null;
        try {
            listVO =
                    NCLocator.getInstance().lookup(IBDActivityPubQuery.class)
                            .queryBDActivityVOByPKs(new ArrayList<String>(idSet));
            for (BDActivityVO vo : listVO) {
                codeSet.add(vo.getVactivitycode());
            }
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
        return codeSet;
    }

    @Override
    public void showWrongInfo(String mes) {
        ExceptionUtils.wrappBusinessException(String.format(CMMLangConstAllocfac.SAME_ACTIVITY_ERRO(), mes));
    }
}
