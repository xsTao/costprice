/**
 *
 */
package nc.ui.mapub.allocfac.scale;

import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;

/**
 * 特殊处理系数打印精度
 */
public class AllocfacPrintScaleUtil {

    /**
     * 表头金额的列---将系数用金额处理
     */
    String[] headMoneyKeys = new String[] {
        AllocfacItemVO.NFACTOR
    };

    public AllocfacPrintScaleUtil() {

    }

    /**
     * 构造函数
     *
     * @param headMoneyKeys 精度处理的字段，传入null则取默认值nfactor
     */
    public AllocfacPrintScaleUtil(String[] headMoneyKeys) {
        this.headMoneyKeys = headMoneyKeys;
    }

    /**
     * 设置打印精度
     *
     * @param pk_group
     * @param pk_org
     * @param vos
     */
    public void setPrintScale(String pk_group, AggregatedValueObject[] vos) {
        this.setScale(new AllocfacBillScaleProcessor(pk_group, vos));
    }

    private void setScale(BillScaleProcessor processor) {
        // 金额
        processor.setMnyFromOrgLocCurrCtlInfo(this.headMoneyKeys, PosEnum.body, null, "pk_org1", PosEnum.body, null);
        processor.process();
    }

    /**
     * 获得
     * String[]
     */
    public String[] getHeadMoneyKeys() {
        return this.headMoneyKeys;
    }

    public void setHeadMoneyKeys(String[] headMoneyKeys) {
        this.headMoneyKeys = headMoneyKeys;
    }
}
