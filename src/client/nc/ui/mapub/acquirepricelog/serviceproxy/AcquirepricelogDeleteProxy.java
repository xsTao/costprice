/**
 *
 */
package nc.ui.mapub.acquirepricelog.serviceproxy;

import java.util.Arrays;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.acquirepricelog.IAcquirepricelogMaintainService;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.mapub.acquirepricelog.entity.AcquirePriceLogVO;

/**
 * @since v6.3
 * @version 2015年4月17日 下午2:49:45
 * @author lizhpf
 */
public class AcquirepricelogDeleteProxy implements ISingleBillService<AcquirePriceLogVO> {

    @Override
    public AcquirePriceLogVO operateBill(AcquirePriceLogVO bill) throws Exception {
        // TODO Auto-generated method stub
        this.delete(new Object[] {
            bill
        });
        return bill;

    }

    public void delete(Object object) throws Exception {
        List<Object> vos = Arrays.asList((Object[]) object);
        NCLocator.getInstance().lookup(IAcquirepricelogMaintainService.class)
        .deleteAcquirepricelog(vos.toArray(new AcquirePriceLogVO[0]));
    }

}
