package nc.ui.mapub.allocfac.action;

import java.util.Map;

import nc.bd.framework.base.CMMapUtil;
import nc.bs.framework.common.NCLocator;
import nc.pubitf.org.cache.IOrgUnitPubService_C;
import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.uif2.LoginContext;

/**
 * 单据复制时表头表体处理
 * 
 * @since 6.0
 * @version 2011-7-7 下午02:31:23
 * @author
 */

public class CopyActionProcessor implements ICopyActionProcessor<AllocfacAggVO> {

    @Override
    public void processVOAfterCopy(AllocfacAggVO billVO, LoginContext context) {
        try {
            this.processHeadVO(billVO, context);
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
        this.processBodyVO(billVO);
    }

    private void processBodyVO(AllocfacAggVO vo) {
        vo.getParent().setAttributeValue(vo.getMetaData().getParent().getPrimaryAttribute().getName(), null);
        vo.getParent().setAttributeValue("ts", null);
        for (IVOMeta meta : vo.getMetaData().getChildren()) {
            if (vo.getChildren(meta) == null) {
                continue;
            }
            for (ISuperVO childvo : vo.getChildren(meta)) {
                childvo.setAttributeValue(meta.getPrimaryAttribute().getName(), null);
                childvo.setAttributeValue("pk_group", null);
                childvo.setAttributeValue("pk_org", null);
                childvo.setAttributeValue("ts", null);
            }
        }
    }

    private void processHeadVO(AllocfacAggVO vo, LoginContext context) throws BusinessException {
        AllocfacHeadVO hvo = vo.getParentVO();
        // 设置空处理
        hvo.setCreator(null);
        hvo.setCreationtime(null);
        hvo.setModifier(null);
        hvo.setModifiedtime(null);
        hvo.setTs(null);
        // 设置默认值
        hvo.setPk_group(context.getPk_group());
        String pk_org = context.getPk_org();
        hvo.setPk_org(pk_org);
        // 组织最新版本
        Map<String, String> orgVid =
                NCLocator.getInstance().lookup(IOrgUnitPubService_C.class).getNewVIDSByOrgIDS(new String[] {
                    context.getPk_org()
                });
        if (CMMapUtil.isNotEmpty(orgVid)) {
            hvo.setPk_org_v(orgVid.get(context.getPk_org()));
        }
    }
}
