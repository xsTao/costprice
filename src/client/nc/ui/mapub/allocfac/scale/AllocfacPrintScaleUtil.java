/**
 *
 */
package nc.ui.mapub.allocfac.scale;

import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;

/**
 * ���⴦��ϵ����ӡ����
 */
public class AllocfacPrintScaleUtil {

    /**
     * ��ͷ������---��ϵ���ý���
     */
    String[] headMoneyKeys = new String[] {
        AllocfacItemVO.NFACTOR
    };

    public AllocfacPrintScaleUtil() {

    }

    /**
     * ���캯��
     *
     * @param headMoneyKeys ���ȴ�����ֶΣ�����null��ȡĬ��ֵnfactor
     */
    public AllocfacPrintScaleUtil(String[] headMoneyKeys) {
        this.headMoneyKeys = headMoneyKeys;
    }

    /**
     * ���ô�ӡ����
     *
     * @param pk_group
     * @param pk_org
     * @param vos
     */
    public void setPrintScale(String pk_group, AggregatedValueObject[] vos) {
        this.setScale(new AllocfacBillScaleProcessor(pk_group, vos));
    }

    private void setScale(BillScaleProcessor processor) {
        // ���
        processor.setMnyFromOrgLocCurrCtlInfo(this.headMoneyKeys, PosEnum.body, null, "pk_org1", PosEnum.body, null);
        processor.process();
    }

    /**
     * ���
     * String[]
     */
    public String[] getHeadMoneyKeys() {
        return this.headMoneyKeys;
    }

    public void setHeadMoneyKeys(String[] headMoneyKeys) {
        this.headMoneyKeys = headMoneyKeys;
    }
}
