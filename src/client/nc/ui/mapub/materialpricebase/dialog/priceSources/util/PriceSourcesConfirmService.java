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
 * �۸���Դ�Ի���ȷ����ť����У����
 *
 * @since 6.36
 * @version 2014-12-4 ����9:39:30
 * @author zhangchd
 */
public class PriceSourcesConfirmService {
    private BillForm dialogBillForm;

    public PriceSourcesConfirmService() {

    }

    /**
     * ���ش�����Ϣ
     *
     * @return String
     */
    public String getConfirmBeforeErroInfo() {
        StringBuffer errInfo = new StringBuffer();

        // �ֹ�¼����������Դ����У��
        this.getDialogPriceSourcesMutexErroInfo(errInfo);
        // ���ֹ�¼�롢��׼�ɱ��ģ�ҵ��Ԫ����Ϊ��У��
        this.getDialogOrgIsNotEmptyErroInfo(errInfo);
        // �۸���Դ�ظ���У��
        this.getDialogDataRepeatErroInfo(errInfo);

        return errInfo.toString();
    }

    /**
     * �ֹ�¼����������Դ����У��
     *
     * @param errInfo
     */
    private void getDialogPriceSourcesMutexErroInfo(StringBuffer errInfo) {

        BillScrollPane bsp = this.getDialogBillForm().getBillCardPanel().getBodyPanel();
        UITable table = bsp.getTable();
        // ������
        int rowCount = table.getRowCount();

        if (rowCount > 1) {
            List<String> manualRow = new ArrayList<String>();// �����ֹ�¼����к�
            int unManualRow = -1;// ������ֹ�¼����к�

            // ��ֵ
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
                // ˵������
                errInfo.append(CMMLangConstMaterialPriceBase.getMSG16());
                errInfo.append(manualRow);
                errInfo.append(CMMLangConstMaterialPriceBase.getMSG17());
                errInfo.append(CMMLangConstMaterialPriceBase.getMSG15());
                errInfo.append("\r\n");
            }
        }
    }

    /**
     * ���ֹ�¼��ģ�ҵ��Ԫ����Ϊ��
     *
     * @param errInfo
     */
    private void getDialogOrgIsNotEmptyErroInfo(StringBuffer errInfo) {

        BillScrollPane bsp = this.getDialogBillForm().getBillCardPanel().getBodyPanel();
        UITable table = bsp.getTable();
        // ������
        int rowCount = table.getRowCount();

        // Map<Integer, String> erroRowToSourcesMap = new HashMap<Integer, String>(); // key�������к� value���۸���Դ
        List<String> unmanualOrgEmptyRow = new ArrayList<String>(); // �洢�����к�:���ֹ�¼��+��׼�ɱ�ҵ��ԪΪ��
        List<String> manualAndStdcostOrgNotEmptyRow = new ArrayList<String>(); // �洢�����кţ��ֹ�¼��+��׼�ɱ�ҵ��Ԫ��Ϊ�� add by
                                                                               // zhangshyb
        if (rowCount > 1) {
            // ��ֵ
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
                // ���ֹ�¼�롢�Ǳ�׼�ɱ���ҵ��Ԫ������ֵ
                // �ֹ�¼�롢��׼�ɱ���ҵ��Ԫ������ֵ add by zhangshyb
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
            // errInfo.append("�۸���Դ [");
            // errInfo.append(rowToSources.getValue());
            // errInfo.append("] ��Ӧ[ҵ��Ԫ]�ֶ�û��¼��ֵ �����޸ģ�");
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
     * �۸���Դ��ҵ��Ԫ�ظ���У��
     *
     * @param errInfo
     */
    private void getDialogDataRepeatErroInfo(StringBuffer errInfo) {
        BillScrollPane bsp = this.getDialogBillForm().getBillCardPanel().getBodyPanel();
        UITable table = bsp.getTable();
        // ������
        int rowCount = table.getRowCount();

        // Map<Integer, String> rowToSourcesMap = new HashMap<Integer, String>(); // key���ظ��к� value���۸���Դ
        List<String> priceSourcesRepeatRow = new ArrayList<String>(); // �洢�ظ��к�
        if (rowCount > 1) {
            Set<String> materialRepeatSet = new HashSet<String>(); // �۸���Դ+ҵ��Ԫid
            // ��ֵ
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

            // errInfo.append("[ �۸���Դ ]");
            // errInfo.append(rowToSources.getValue());
            // errInfo.append("�����ظ������޸ģ�");
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
