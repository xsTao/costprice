package nc.bs.mapub.acquirepricelog.bp;

import nc.bd.framework.base.CMArrayUtil;
import nc.bs.mapub.acquirepricelog.plugin.bpplugin.AcquirepricelogPluginPoint;
import nc.impl.pubapp.pattern.data.vo.template.DeleteBPTemplate;
import nc.vo.mapub.acquirepricelog.entity.AcquirePriceLogVO;

/**
 * ��׼����ɾ��BP
 */
public class AcquirepricelogDeleteBP {
    public void delete(AcquirePriceLogVO[] AcquirePriceLogVOs) {
        if (CMArrayUtil.isEmpty(AcquirePriceLogVOs)) {
            return;
        }
        DeleteBPTemplate<AcquirePriceLogVO> bp =
                new DeleteBPTemplate<AcquirePriceLogVO>(AcquirepricelogPluginPoint.DELETE);
        // ������������
        bp.delete(AcquirePriceLogVOs);
    }
}
