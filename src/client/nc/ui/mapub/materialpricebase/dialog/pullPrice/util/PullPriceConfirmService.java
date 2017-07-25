package nc.ui.mapub.materialpricebase.dialog.pullPrice.util;

import nc.bd.framework.base.CMValueCheck;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;

/**
 * 取价确定按钮校验服务类
 *
 * @since 6.36
 * @version 2014-12-4 上午9:38:53
 * @author zhangchd
 */
public class PullPriceConfirmService {
    private BillManageModel model;

    private BillForm dialogBillForm;

    public PullPriceConfirmService() {

    }

    public String getConfirmBeforeErroInfo() {
        StringBuffer errInfo = new StringBuffer();

        // 1.非空校验
        this.getValidateErroInfo(errInfo);
        // 2.开始期间小于截止期间校验
        this.getDimensionErroInfo(errInfo);

        return errInfo.toString();
    }

    /**
     * 2.开始期间小于截止期间校验
     *
     * @param errInfo
     */
    private void getDimensionErroInfo(StringBuffer errInfo) {
        BillItem[] headtailitems = this.getDialogBillForm().getBillCardPanel().getHeadShowItems();
        if (headtailitems != null) {
            String dbegindate = null;
            String denddate = null;
            for (BillItem headtailitem : headtailitems) {
                if ("cbegindate".equals(headtailitem.getKey())) {
                    if (CMValueCheck.isNotEmpty(headtailitem.getValueObject())) {
                        dbegindate = headtailitem.getValueObject().toString();
                    }

                }
                else if ("cenddate".equals(headtailitem.getKey())) {
                    if (CMValueCheck.isNotEmpty(headtailitem.getValueObject())) {
                        denddate = headtailitem.getValueObject().toString();
                    }

                }
            }
            if (dbegindate != null && denddate != null) {
                if (dbegindate.compareTo(denddate) > 0) {
                    if (CMValueCheck.isNotEmpty(errInfo.toString())) {
                        errInfo.append("\r\n");
                    }

                    errInfo.append(CMMLangConstMaterialPriceBase.getMSG27());
                    errInfo.append("\r\n");
                }
            }

        }
    }

    /**
     * 1.非空校验
     *
     * @param errInfo
     */
    private void getValidateErroInfo(StringBuffer errInfo) {
        BillItem[] headtailitems = this.getDialogBillForm().getBillCardPanel().getHeadShowItems();
        if (headtailitems != null) {
            StringBuffer message = null;
            for (int i = 0; i < headtailitems.length; i++) {
                if (headtailitems[i].isNull()) {
                    if (CMValueCheck.isEmpty(headtailitems[i].getValueObject())) {
                        if (message == null) {
                            message = new StringBuffer();
                        }
                        String comma = nc.ui.ml.NCLangRes.getInstance().getString("_bill", ",", "0_bill0034");// 逗号
                        message.append("[");
                        message.append(headtailitems[i].getName());
                        message.append("]");
                        message.append(comma);
                    }
                }
            }
            if (message != null) {
                message.deleteCharAt(message.length() - 1);
                errInfo.append(CMMLangConstMaterialPriceBase.getMSG28());
                errInfo.append("\r\n");
                errInfo.append(message.toString());
                errInfo.append("\r\n");
            }
        }
    }

    public BillForm getDialogBillForm() {
        return this.dialogBillForm;
    }

    public void setDialogBillForm(BillForm dialogBillForm) {
        this.dialogBillForm = dialogBillForm;
    }

    public BillManageModel getModel() {
        return this.model;
    }

    public void setModel(BillManageModel model) {
        this.model = model;
    }
}
