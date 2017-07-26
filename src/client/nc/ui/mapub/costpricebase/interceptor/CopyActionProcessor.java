/**
 *
 */
package nc.ui.mapub.costpricebase.interceptor;

import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;
import nc.vo.uif2.LoginContext;

/**
 * 复制拦截器--
 *
 * @since v6.3
 * @version 2017年7月26日 下午3:28:44
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
        this.processBodyVO(context);

    }

    /**
     * @param context
     */
    private void processBodyVO(LoginContext context) {
        // TODO Auto-generated method stub

    }

    /**
     * @param billVO
     * @param context
     */
    private void processHeadVO(CostPriceAggVO billVO, LoginContext context) {
        // TODO Auto-generated method stub

    }

}
