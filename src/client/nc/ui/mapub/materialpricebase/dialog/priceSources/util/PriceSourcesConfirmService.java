package nc.ui.mapub.materialpricebase.dialog.priceSources.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bd.framework.base.CMValueCheck;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceSourcesBodyVO;
import nc.vo.mapub.materialpricebase.enumeration.PriceSourceEnum;

/**
 * 价格来源对话框确定按钮服务校验类
 *
 * @since 6.36
 * @version 2014-12-4 上午9:39:30
 * @author zhangchd
 */
public class PriceSourcesConfirmService {
    private BillForm dialogBillForm;

    public PriceSourcesConfirmService() {

    }

    /**
     * 返回错误信息
     *
     * @return String
     */
    public String getConfirmBeforeErroInfo() {
        StringBuffer errInfo = new StringBuffer();

        // 手工录入与其他来源互斥校验
        this.getDialogPriceSourcesMutexErroInfo(errInfo);
        // 非手工录入、标准成本的，业务单元不能为空校验
        this.getDialogOrgIsNotEmptyErroInfo(errInfo);
        // 价格来源重复性校验
        this.getDialogDataRepeatErroInfo(errInfo);

        return errInfo.toString();
    }

    /**
     * 手工录入与其他来源互斥校验
     *
     * @param errInfo
     */
    private void getDialogPriceSourcesMutexErroInfo(StringBuffer errInfo) {

        BillScrollPane bsp = this.getDialogBillForm().getBillCardPanel().getBodyPanel();
        UITable table = bsp.getTable();
        // 总行数
        int rowCount = table.getRowCount();

        if (rowCount > 1) {
            List<String> manualRow = new ArrayList<String>();// 储存手工录入的行号
            int unManualRow = -1;// 储存非手工录入的行号

            // 列值
            int priceSourcescolunm =
                    this.getDialogBillForm().getBillCardPanel().getBillModel()
                    .getBodyColByKey(MaterialPriceSourcesBodyVO.VPRICESOURCE);

            for (int i = 0; i < rowCount; i++) {
                Object priceSourcesObject =
                        this.getDialogBillForm().getBillCardPanel().getBillModel().getValueAt(i, priceSourcescolunm);

                if (CMValueCheck.isNotEmpty(priceSourcesObject)
                        && String.valueOf(PriceSourceEnum.MANUAL.toIntValue()).equals(
                                ((DefaultConstEnum) priceSourcesObject).getValue().toString())) {
                    manualRow.add(String.valueOf(i + 1));
                }
                else if (CMValueCheck.isNotEmpty(priceSourcesObject)) {
                    unManualRow = i + 1;
                }
            }

            if (CMValueCheck.isNotEmpty(manualRow) && unManualRow >= 0) {
                // 说明互斥
                errInfo.append(CMMLangConstMaterialPriceBase.getMSG16());
                errInfo.append(manualRow);
                errInfo.append(CMMLangConstMaterialPriceBase.getMSG17());
                errInfo.append(CMMLangConstMaterialPriceBase.getMSG15());
                errInfo.append("\r\n");
            }
        }
    }

    /**
     * 非手工录入的，业务单元不能为空
     *
     * @param errInfo
     */
    private void getDialogOrgIsNotEmptyErroInfo(StringBuffer errInfo) {

        BillScrollPane bsp = this.getDialogBillForm().getBillCardPanel().getBodyPanel();
        UITable table = bsp.getTable();
        // 总行数
        int rowCount = table.getRowCount();

        // Map<Integer, String> erroRowToSourcesMap = new HashMap<Integer, String>(); // key：错误行号 value：价格来源
        List<String> unmanualOrgEmptyRow = new ArrayList<String>(); // 存储错误行号:非手工录入+标准成本业务单元为空
        List<String> manualAndStdcostOrgNotEmptyRow = new ArrayList<String>(); // 存储错误行号：手工录入+标准成本业务单元不为空 add by
                                                                               // zhangshyb
        if (rowCount > 1) {
            // 列值
            int priceSourcescolunm =
                    this.getDialogBillForm().getBillCardPanel().getBillModel()
                    .getBodyColByKey(MaterialPriceSourcesBodyVO.VPRICESOURCE);
            int orgcolunm =
                    this.getDialogBillForm().getBillCardPanel().getBillModel()
                    .getBodyColByKey(MaterialPriceSourcesBodyVO.PK_ORG);

            for (int i = 0; i < rowCount; i++) {
                Object priceSourcesObject =
                        this.getDialogBillForm().getBillCardPanel().getBillModel().getValueAt(i, priceSourcescolunm);
                Object orgObject = this.getDialogBillForm().getBillCardPanel().getBillModel().getValueAt(i, orgcolunm);
                if (CMValueCheck.isEmpty(priceSourcesObject)) {
                    continue;
                }
                // 非手工录入、非标准成本：业务单元必须有值
                // 手工录入、标准成本：业务单元不能有值 add by zhangshyb
                if (String.valueOf(PriceSourceEnum.MANUAL.toIntValue()).equals(
                        ((DefaultConstEnum) priceSourcesObject).getValue().toString())
                        || String.valueOf(PriceSourceEnum.STDCOST.toIntValue()).equals(
                                ((DefaultConstEnum) priceSourcesObject).getValue().toString())) {
                    if (!CMValueCheck.isEmpty(orgObject)) {
                        manualAndStdcostOrgNotEmptyRow.add(String.valueOf(i + 1));
                        // erroRowToSourcesMap.put(Integer.valueOf(i + 1),
                        // ((DefaultConstEnum) priceSourcesObject).getName());
                    }
                }
                else {
                    if (CMValueCheck.isEmpty(orgObject)) {
                        unmanualOrgEmptyRow.add(String.valueOf(i + 1));
                        // erroRowToSourcesMap.put(Integer.valueOf(i + 1),
                        // ((DefaultConstEnum) priceSourcesObject).getName());
                    }
                }
            }
        }

        // if (CMValueCheck.isNotEmpty(erroRowToSourcesMap)) {
        // for (Map.Entry<Integer, String> rowToSources : erroRowToSourcesMap.entrySet()) {
        if (CMValueCheck.isNotEmpty(unmanualOrgEmptyRow)) {
            errInfo.append(CMMLangConstMaterialPriceBase.getMSG16());
            errInfo.append(unmanualOrgEmptyRow);
            errInfo.append(CMMLangConstMaterialPriceBase.getMSG17());
            // errInfo.append("价格来源 [");
            // errInfo.append(rowToSources.getValue());
            // errInfo.append("] 对应[业务单元]字段没有录入值 ，请修改！");
            errInfo.append(CMMLangConstMaterialPriceBase.getMSG18());
            errInfo.append("\r\n");
        }
        if (CMValueCheck.isNotEmpty(manualAndStdcostOrgNotEmptyRow)) {
            errInfo.append(CMMLangConstMaterialPriceBase.getMSG16());
            errInfo.append(manualAndStdcostOrgNotEmptyRow);
            errInfo.append(CMMLangConstMaterialPriceBase.getMSG17());
            errInfo.append(CMMLangConstMaterialPriceBase.getMSG34());
            errInfo.append("\r\n");
        }
    }

    /**
     * 价格来源与业务单元重复性校验
     *
     * @param errInfo
     */
    private void getDialogDataRepeatErroInfo(StringBuffer errInfo) {
        BillScrollPane bsp = this.getDialogBillForm().getBillCardPanel().getBodyPanel();
        UITable table = bsp.getTable();
        // 总行数
        int rowCount = table.getRowCount();

        // Map<Integer, String> rowToSourcesMap = new HashMap<Integer, String>(); // key：重复行号 value：价格来源
        List<String> priceSourcesRepeatRow = new ArrayList<String>(); // 存储重复行号
        if (rowCount > 1) {
            Set<String> materialRepeatSet = new HashSet<String>(); // 价格来源+业务单元id
            // 列值
            int priceSourcescolunm =
                    this.getDialogBillForm().getBillCardPanel().getBillModel()
                    .getBodyColByKey(MaterialPriceSourcesBodyVO.VPRICESOURCE);
            // int orgcolunm =
            // this.getDialogBillForm().getBillCardPanel().getBillModel()
            // .getBodyColByKey(MaterialPriceSourcesBodyVO.PK_ORG);
            for (int i = 0; i < rowCount; i++) {
                Object priceSourcesObject =
                        this.getDialogBillForm().getBillCardPanel().getBillModel().getValueAt(i, priceSourcescolunm);
                // Object orgObject = this.getDialogBillForm().getBillCardPanel().getBillModel().getValueAt(i,
                // orgcolunm);
                StringBuffer uniqueKey = new StringBuffer();
                if (CMValueCheck.isNotEmpty(priceSourcesObject)) {
                    uniqueKey.append(((DefaultConstEnum) priceSourcesObject).getValue());
                }
                // if (CMValueCheck.isNotEmpty(orgObject)) {
                // uniqueKey.append(((DefaultConstEnum) orgObject).getValue());
                // }

                if (uniqueKey.toString().trim().length() <= 0) {
                    continue;
                }
                if (materialRepeatSet.contains(uniqueKey.toString())) {
                    priceSourcesRepeatRow.add(String.valueOf(i + 1));
                    // rowToSourcesMap.put(Integer.valueOf(i + 1), ((DefaultConstEnum) priceSourcesObject).getName());
                }
                else {
                    materialRepeatSet.add(uniqueKey.toString());
                }
            }
        }

        if (CMValueCheck.isNotEmpty(priceSourcesRepeatRow)) {
            // if (CMValueCheck.isNotEmpty(rowToSourcesMap)) {
            // for (Map.Entry<Integer, String> rowToSources : rowToSourcesMap.entrySet()) {
            errInfo.append(CMMLangConstMaterialPriceBase.getMSG16());
            errInfo.append(priceSourcesRepeatRow);
            errInfo.append(CMMLangConstMaterialPriceBase.getMSG17());
            errInfo.append(CMMLangConstMaterialPriceBase.getMSG19());

            // errInfo.append("[ 价格来源 ]");
            // errInfo.append(rowToSources.getValue());
            // errInfo.append("出现重复，请修改！");
            errInfo.append("\r\n");
            // }
        }

    }

    public BillForm getDialogBillForm() {
        return this.dialogBillForm;
    }

    public void setDialogBillForm(BillForm dialogBillForm) {
        this.dialogBillForm = dialogBillForm;
    }

}
