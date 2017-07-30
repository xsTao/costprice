/**
 *
 */
package nc.ui.mapub.costpricebase.interceptor;

import java.util.Map;

import nc.bd.framework.base.CMMapUtil;
import nc.cmpub.business.adapter.BDAdapter;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;
import nc.vo.mapub.costpricebase.entity.CostPriceHeadVO;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDate;
import nc.vo.uif2.LoginContext;

/**
 * ����������--
 *
 * @since v6.3
 * @version 2017��7��26�� ����3:28:44
 * @author Administrator
 */
public class CopyActionProcessor implements ICopyActionProcessor<CostPriceAggVO> {

    /*
     * (non-Javadoc)
     * @see
     * nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor#processVOAfterCopy(nc.vo.pubapp.pattern.model.entity.bill
     * .AbstractBill, nc.vo.uif2.LoginContext)
     */
    @Override
    public void processVOAfterCopy(CostPriceAggVO billVO, LoginContext context) {
        // TODO Auto-generated method stub
        this.processHeadVO(billVO, context);
        this.processBodyVO(billVO);

    }

    /**
     * @param context
     */
    private void processBodyVO(CostPriceAggVO vo) {
        // TODO Auto-generated method stub
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
            }
        }
    }

    /**
     * @param billVO
     * @param context
     */
    private void processHeadVO(CostPriceAggVO billVO, LoginContext context) {
        // TODO Auto-generated method stub
        CostPriceHeadVO hvo = billVO.getParentVO();
        // ��ȡ��ǰ��½ʱ��
        UFDate serverDate = AppUiContext.getInstance().getBusiDate();

        hvo.setVpricelibcode(null);
        hvo.setVpricelibname(null);
        // ���ÿմ���
        hvo.setModifier(null);
        hvo.setModifiedtime(null);
        hvo.setCreator(null);
        hvo.setCreatetime(null);

        // ����Ĭ��ֵ
        hvo.setPkGroup(context.getPk_group());
        hvo.setPkOrg(context.getPk_org());
        // ��֯���°汾
        Map<String, String> orgVid = BDAdapter.getNewVIDSByOrgIDS(new String[] {
                context.getPk_org()
        });
        if (CMMapUtil.isNotEmpty(orgVid)) {
            hvo.setPkOrgV(orgVid.get(context.getPk_org()));
        }
        // hvo.setAnnual();
    }

}
