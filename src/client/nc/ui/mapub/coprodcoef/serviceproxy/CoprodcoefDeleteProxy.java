package nc.ui.mapub.coprodcoef.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.coprodcoef.ICoprodcoefMaintain;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;

/**
 * ʾ�����ݵ�ɾ������֧����ɾ��
 * 
 * @since 6.0
 * @version 2014-10-11 ����3:04:31
 * @author zhangshyb
 */
public class CoprodcoefDeleteProxy implements ISingleBillService<CoprodcoefAggVO> {

    @Override
    public CoprodcoefAggVO operateBill(CoprodcoefAggVO bill) throws Exception {
        ICoprodcoefMaintain operator = NCLocator.getInstance().lookup(ICoprodcoefMaintain.class);
        operator.delete(new CoprodcoefAggVO[] {
            bill
        });
        return bill;
    }

}
