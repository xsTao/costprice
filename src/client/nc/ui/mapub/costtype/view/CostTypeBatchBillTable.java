package nc.ui.mapub.costtype.view;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pub.beans.constenum.IConstEnum;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.util.ManageModeUtil;

/**
 * <b> �ɱ����������½��� </b>
 * <p>
 * ��ϸ��������
 * </p>
 * ��������:2010-4-6
 *
 * @author:wangtf
 */
public class CostTypeBatchBillTable extends BatchBillTable {

    /**
     * ���л�id
     */
    private static final long serialVersionUID = 4106486523118227712L;

    @Override
    public void setValue(Object object) {
        super.setValue(object);
        if (!(object == null || object.getClass().isArray() && ((Object[]) object).length == 0)) {
            Object[] objs = null;
            if (object.getClass().isArray()) {
                objs = (Object[]) object;
            }
            else {
                objs = new Object[] {
                        object
                };
            }

            CostTypeVO[] costTypeVOs = new CostTypeVO[objs.length];

            for (int i = 0; i < objs.length; i++) {
                if (objs[i] instanceof CostTypeVO) {
                    costTypeVOs[i] = (CostTypeVO) objs[i];
                }
            }

            CostTypeMaterialPriceSourceBaseData materialbaseData = new CostTypeMaterialPriceSourceBaseData();
            List<IConstEnum> materialenums = new ArrayList<IConstEnum>();
            if ("38100105".equals(this.getModel().getContext().getNodeCode())) {
                materialenums = materialbaseData.getRefreshValues4Group();
            }
            else {
                materialenums = materialbaseData.getRefreshValues(this.getModel().getContext().getPk_org());
            }
            int j = 1;
            for (CostTypeVO costTypeVO : costTypeVOs) {
                if (costTypeVO != null) {
                    // ����
                    if (costTypeVO.getVmaterialpricesourcenum() != null
                            && costTypeVO.getVmaterialpricesourcenum().length() > 0) {

                        this.stringConversion(costTypeVO.getVmaterialpricesourcenum(), materialenums,
                                CostTypeVO.VMATERIALPRICESOURCE, j);
                    }

                    // ����
                    // if (costTypeVO.getVcostpricesourcenum() != null && costTypeVO.getVcostpricesourcenum().length() >
                    // 0) {
                    // CostTypeCostPriceSourceBaseData costbaseData = new CostTypeCostPriceSourceBaseData();
                    // List<IConstEnum> costenums = new ArrayList<IConstEnum>();
                    // costenums = costbaseData.getRefreshValues(this.getModel().getContext().getPk_org());
                    // this.stringConversion(costTypeVO.getVcostpricesourcenum(), costenums,
                    // CostTypeVO.VCOSTPRICESOURCE, j);
                    // }
                    j++;
                }

            }

        }
    }

    @Override
    public void initUI() {
        super.initUI();
        // ������Ʋ����޸�
        this.getBillCardPanel().getBodyItem(CostTypeVO.CREATOR).setEdit(false);
        this.getBillCardPanel().getBodyItem(CostTypeVO.CREATIONTIME).setEdit(false);
        this.getBillCardPanel().getBodyItem(CostTypeVO.MODIFIER).setEdit(false);
        this.getBillCardPanel().getBodyItem(CostTypeVO.MODIFIEDTIME).setEdit(false);
        // this.getBillCardPanel().getBodyItem(CostTypeVO.PK_FACTORCHART).setEdit(false);
        // this.getBillCardPanel().getBodyItem(CostTypeVO.PK_MATERIALDOCVIEW).setEdit(false);
        // this.setAccPeriodBillItemEditor();
        // ���ÿ���ѡ�����
        // this.getBillCardPanel().getBillTable().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

    }

    /**
     * ��Ҫ˵�� ���������λ��ʾPK������
     *
     * @see nc.ui.uif2.editor.BatchBillTable#synchornizeDataFromModel()
     */
    @Override
    protected void synchornizeDataFromModel() {
        super.synchornizeDataFromModel();
        this.billCardPanel.getBillModel().loadLoadRelationItemValue();
        this.renderTable();
    }

    /**
     * ��ǰ�ڵ㲻��Ϊά����������ɫ��ʶ
     */
    private void renderTable() {
        BillModel model = this.getBillCardPanel().getBillModel();
        List<Object> billdata = this.getModel().getRows();
        int colCount = model.getColumnCount();
        for (int i = 0; i < model.getRowCount(); i++) {
            Object obj = billdata.get(i);
            if (!ManageModeUtil.manageable(obj, this.getModel().getContext())) {
                for (int j = 0; j < colCount; j++) {
                    model.setBackground(ManageModeUtil.NE_BG_COLOR, i, j);
                }
            }
        }
    }

    // /**
    // * ������Ч����ڼ��ʧЧ����ڼ�
    // */
    // private void setAccPeriodBillItemEditor() {
    //
    // // ��Ч����ڼ�
    // BillItem startAccountperiod = this.getBillCardPanel().getBodyItem(CostTypeVO.CBEGINMONTH);
    // // �����Զ���༭��
    // AccPeriodBillItemEditor startItemEditor = new AccPeriodBillItemEditor(startAccountperiod);
    // // ���Զ���༭�����õ���ӦBillItem��
    // startAccountperiod.setItemEditor(startItemEditor);
    // // �ػ�BillCardPanel, ��������Ч���Զ����ӱ༭���¼�
    // // this.getBillCardPanel().setBillData(this.getBillCardPanel().getBillData());
    //
    // // ʧЧ����ڼ�
    // BillItem endAccountperiod = this.getBillCardPanel().getBodyItem(CostTypeVO.CENDMONTH);
    // // �����Զ���༭��
    // AccPeriodBillItemEditor endPeriodItemEditor = new AccPeriodBillItemEditor(endAccountperiod);
    // // ���Զ���༭�����õ���ӦBillItem��
    // endAccountperiod.setItemEditor(endPeriodItemEditor);
    //
    // // �ػ�BillCardPanel, ��������Ч���Զ����ӱ༭���¼�
    // // this.getBillCardPanel().setBillData(this.getBillCardPanel().getBillData());
    //
    // }

    /**
     * ����̨����-����ת����������ʾ��ǰ̨
     *
     * @param toconver
     * @param list
     * @param toSet
     * @param i
     */
    private void stringConversion(String toconver, List<IConstEnum> list, String toSet, int i) {

        String[] sss = toconver.split("\\,");
        StringBuffer string = new StringBuffer();
        String value = null;
        for (String ss : sss) {
            for (IConstEnum enums : list.toArray(new IConstEnum[list.size()])) {
                if (ss.equals(enums.getValue().toString())) {
                    string.append(enums.getName());
                    string.append(",");
                }
            }
        }

        if (string.length() > 1) {
            value = string.substring(0, string.length() - 1);
            this.getBillCardPanel().getBillModel()
            .setValueAt(value, this.getBillCardPanel().getBillModel().getEditRow() + i, toSet);

        }

    }

}
