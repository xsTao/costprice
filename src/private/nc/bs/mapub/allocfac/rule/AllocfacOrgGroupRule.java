package nc.bs.mapub.allocfac.rule;

import nc.bd.framework.base.CMStringUtil;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.org.IOrgUnitQryService;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 集团组织主子表保持一致
 */
public class AllocfacOrgGroupRule implements IRule<AllocfacAggVO> {
    @Override
    public void process(AllocfacAggVO[] vos) {
        for (AllocfacAggVO vo : vos) {
            try {
                this.setImportOrgGroup(vo);
            }
            catch (BusinessException e) {
                ExceptionUtils.wrappException(e);
            }
        }
    }

    private void setImportOrgGroup(AllocfacAggVO vo) throws BusinessException {
        AllocfacHeadVO headVo = vo.getParentVO();
        if (CMStringUtil.isEmpty(headVo.getPk_group())) {
            String pk_group =
                    NCLocator.getInstance().lookup(IOrgUnitQryService.class).getOrg(headVo.getPk_org()).getPk_group()
                            .toString();
            headVo.setPk_group(pk_group);
        }
        AllocfacItemVO[] itemVos = (AllocfacItemVO[]) vo.getChildren(AllocfacItemVO.class);
        if (itemVos != null && itemVos.length > 0) {
            for (AllocfacItemVO itemVo : itemVos) {
                itemVo.setPk_group(headVo.getPk_group());
                itemVo.setPk_org(headVo.getPk_org());
                itemVo.setPk_org_v(headVo.getPk_org_v());
            }
        }
    }
}
