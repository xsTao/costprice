package nc.ui.mapub.costtype.action;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.uif2.IActionCode;
import nc.ui.cmpub.framework.view.RefMutilChooseRefPane;
import nc.ui.mapub.costtype.view.CostTypeCostPriceSourceBaseData;
import nc.ui.mapub.costtype.view.CostTypeMaterialPriceSourceBaseData;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.uif2.UIState;
import nc.ui.uif2.actions.ActionInitializer;
import nc.ui.uif2.editor.BatchBillTable;
import nc.vo.uif2.LoginContext;
import nc.vo.util.ManageModeUtil;

public class CostTypeEditAction extends nc.ui.pubapp.uif2app.actions.batch.BatchEditAction {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private LoginContext context;

    public LoginContext getContext() {
        return this.context;
    }

    public void setContext(LoginContext context) {
        this.context = context;
    }

    /**
     * ���
     * BatchBillTable
     */
    public BatchBillTable getEditor() {
        return this.editor;
    }

    public void setEditor(BatchBillTable editor) {
        this.editor = editor;
    }

    private BatchBillTable editor = null;

    @Override
    public void doAction(ActionEvent e) throws Exception {
        this.getModel().setUiState(UIState.EDIT);
        ActionInitializer.initializeAction(this, IActionCode.EDIT);
        // ҵ��Ԫ���ڵ㲻���޸ļ��ż�����
        List<Object> objs = new ArrayList<Object>();
        objs = this.getEditor().getModel().getRows();
        int rowCount = this.getEditor().getModel().getRowCount();
        for (int i = 0; i < rowCount; i++) {
            if (!ManageModeUtil.manageable(objs.get(i), this.getModel().getContext())) {
                BillItem[] items = this.getEditor().getBillCardPanel().getBodyItems();
                for (BillItem item : items) {
                    // �����в��ɱ༭
                    this.getEditor().getBillCardPanel().getBillModel().setCellEditable(i, item.getKey(), false);
                }
            }
        }
        // ���ò��ն�ѡ
        BillCardPanel billCardPanel = this.getEditor().getBillCardPanel();
        if (null != billCardPanel) {
            /**
             * ���ϼ۸���Դ
             */
            CostTypeMaterialPriceSourceBaseData materialbaseData = new CostTypeMaterialPriceSourceBaseData();
            // materialbaseData.getInitValues(this.getContext());
            BillItem materialrefItem = null;
            Map<String, String> materialmap = materialbaseData.getRefColumn();
            for (Entry<String, String> materialentry : materialmap.entrySet()) {
                if (materialbaseData.getRefColumnPos() == 0) {
                    materialrefItem = billCardPanel.getHeadItem(materialentry.getKey().toString());
                }
                else {
                    materialrefItem = billCardPanel.getBodyItem(materialentry.getKey().toString());
                }
            }

            RefMutilChooseRefPane materialrefPane =
                    new RefMutilChooseRefPane(new Container(), billCardPanel, this.getContext().getPk_org(),
                            materialbaseData);
            // �����ı��򲻿��ֹ�����
            materialrefPane.getUITextField().setEditable(false);
            if (materialrefItem != null) {
                materialrefItem.setComponent(materialrefPane);
            }
            /**
             * ���ü۸���Դ
             */
            CostTypeCostPriceSourceBaseData costbaseData = new CostTypeCostPriceSourceBaseData();
            BillItem costrefItem = null;
            Map<String, String> costmap = costbaseData.getRefColumn();
            for (Entry<String, String> costentry : costmap.entrySet()) {
                if (costbaseData.getRefColumnPos() == 0) {
                    costrefItem = billCardPanel.getHeadItem(costentry.getKey().toString());
                }
                else {
                    costrefItem = billCardPanel.getBodyItem(costentry.getKey().toString());
                }
            }

            RefMutilChooseRefPane costrefPane =
                    new RefMutilChooseRefPane(new Container(), billCardPanel, this.getContext().getPk_org(),
                            costbaseData);
            // �����ı��򲻿��ֹ�����
            costrefPane.getUITextField().setEditable(false);
            if (costrefItem != null) {
                costrefItem.setComponent(costrefPane);
            }
            // billCardPanel.setBillData(billCardPanel.getBillData());
        }
    }

    @Override
    protected boolean isActionEnable() {
        Object[] sels = this.getModel().getSelectedOperaDatas();
        if (sels == null || sels.length < 1) {
            return false;
        }

        boolean flag = false;
        for (Object obj : sels) {
            if (ManageModeUtil.manageable(obj, this.getModel().getContext())) {
                flag = true;
            }
        }
        // У������Ȩ��
        if (!flag) {
            return false;
        }

        return true;
    }
}
