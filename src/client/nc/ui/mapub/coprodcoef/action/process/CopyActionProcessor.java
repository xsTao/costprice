package nc.ui.mapub.coprodcoef.action.process;

import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefHeadVO;
import nc.vo.mapub.coprodcoef.entity.enumeration.BillStatusEnum;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.uif2.LoginContext;

/**
 * @since 6.0
 * @version 2014-10-11 ÉÏÎç11:18:25
 * @author zhangshyb
 */
public class CopyActionProcessor implements ICopyActionProcessor<CoprodcoefAggVO> {

    @Override
    public void processVOAfterCopy(CoprodcoefAggVO billVO, LoginContext context) {
        this.processHeadVO(billVO, context);
        this.processBodyVO(billVO);
    }

    private void processBodyVO(CoprodcoefAggVO vo) {
        vo.getParent().setAttributeValue(vo.getMetaData().getParent().getPrimaryAttribute().getName(), null);
        vo.getParent().setAttributeValue("ts", null);
        for (IVOMeta meta : vo.getMetaData().getChildren()) {
            for (ISuperVO childvo : vo.getChildren(meta)) {
                childvo.setAttributeValue(meta.getPrimaryAttribute().getName(), null);
                childvo.setAttributeValue("pk_group", null);
                childvo.setAttributeValue("pk_org", null);
                childvo.setAttributeValue("ts", null);
            }
        }
    }

    private void processHeadVO(CoprodcoefAggVO vo, LoginContext context) {
        // UFDateTime datetime = ServerTimeProxy.getInstance().getServerTime();
        CoprodcoefHeadVO hvo = vo.getParentVO();
        hvo.setModifier(null);
        hvo.setModifiedtime(null);
        hvo.setCreator(null);
        hvo.setCreationtime(null);
        hvo.setPk_group(context.getPk_group());
        hvo.setPk_org(context.getPk_org());
        hvo.setAttributeValue("ibillstatus", BillStatusEnum.FREE);
    }

}
