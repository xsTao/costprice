package nc.ui.mapub.costtype.view;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pub.beans.constenum.IConstEnum;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.util.ManageModeUtil;

/**
 * <b> 成本类型批更新界面 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-4-6
 *
 * @author:wangtf
 */
public class CostTypeBatchBillTable extends BatchBillTable {

    /**
     * 序列化id
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
                    // 物料
                    if (costTypeVO.getVmaterialpricesourcenum() != null
                            && costTypeVO.getVmaterialpricesourcenum().length() > 0) {

                        this.stringConversion(costTypeVO.getVmaterialpricesourcenum(), materialenums,
                                CostTypeVO.VMATERIALPRICESOURCE, j);
                    }

                    // 费用
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
        // 设置审计不可修改
        this.getBillCardPanel().getBodyItem(CostTypeVO.CREATOR).setEdit(false);
        this.getBillCardPanel().getBodyItem(CostTypeVO.CREATIONTIME).setEdit(false);
        this.getBillCardPanel().getBodyItem(CostTypeVO.MODIFIER).setEdit(false);
        this.getBillCardPanel().getBodyItem(CostTypeVO.MODIFIEDTIME).setEdit(false);
        // this.getBillCardPanel().getBodyItem(CostTypeVO.PK_FACTORCHART).setEdit(false);
        // this.getBillCardPanel().getBodyItem(CostTypeVO.PK_MATERIALDOCVIEW).setEdit(false);
        // this.setAccPeriodBillItemEditor();
        // 设置可以选择多行
        // this.getBillCardPanel().getBillTable().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

    }

    /**
     * 简要说明 处理计量单位显示PK的问题
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
     * 当前节点不可为维护数据用蓝色标识
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
    // * 设置生效会计期间和失效会计期间
    // */
    // private void setAccPeriodBillItemEditor() {
    //
    // // 生效会计期间
    // BillItem startAccountperiod = this.getBillCardPanel().getBodyItem(CostTypeVO.CBEGINMONTH);
    // // 创建自定义编辑器
    // AccPeriodBillItemEditor startItemEditor = new AccPeriodBillItemEditor(startAccountperiod);
    // // 将自定义编辑器设置到对应BillItem上
    // startAccountperiod.setItemEditor(startItemEditor);
    // // 重画BillCardPanel, 是设置生效并自动增加编辑后事件
    // // this.getBillCardPanel().setBillData(this.getBillCardPanel().getBillData());
    //
    // // 失效会计期间
    // BillItem endAccountperiod = this.getBillCardPanel().getBodyItem(CostTypeVO.CENDMONTH);
    // // 创建自定义编辑器
    // AccPeriodBillItemEditor endPeriodItemEditor = new AccPeriodBillItemEditor(endAccountperiod);
    // // 将自定义编辑器设置到对应BillItem上
    // endAccountperiod.setItemEditor(endPeriodItemEditor);
    //
    // // 重画BillCardPanel, 是设置生效并自动增加编辑后事件
    // // this.getBillCardPanel().setBillData(this.getBillCardPanel().getBillData());
    //
    // }

    /**
     * 将后台数据-数字转换成文字显示在前台
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
