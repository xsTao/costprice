package nc.ui.mapub.materialpricebase.dialog.priceSources.handler;

import java.util.ArrayList;
import java.util.List;

import nc.bd.framework.base.CMValueCheck;
import nc.itf.org.IOrgConst;
import nc.ui.bd.pub.handler.CMBasedocAbstractHandler;
import nc.ui.org.ref.OrgAndOrgTypeCompositeRefTreeModelOnlyEnableData;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.UIRefPaneTextField;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardPanelEvent;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceSourcesBodyVO;
import nc.vo.mapub.materialpricebase.enumeration.PriceSourceEnum;

/**
 * 物料价格来源对话框
 * 业务单元事件
 * 
 * @since 6.36
 * @version 2014-11-22 下午3:51:47
 * @author zhangchd
 */
public class OrgHandler extends CMBasedocAbstractHandler {

    /**
     * 业务单元编辑前事件
     */
    @Override
    public void beforeEdit(CardPanelEvent ex) {
        CardBodyBeforeEditEvent e = (CardBodyBeforeEditEvent) ex;

        // 价格来源是手工录入,标准成本，业务单元不可编辑
        Object priceSourceObject =
                e.getBillCardPanel().getBodyValueAt(e.getRow(), MaterialPriceSourcesBodyVO.VPRICESOURCE);

        BillItem orgbillItem = ex.getBillCardPanel().getBodyItem(MaterialPriceSourcesBodyVO.PK_ORG);
        UIRefPane ref = (UIRefPane) orgbillItem.getComponent();

        UIRefPaneTextField text = (UIRefPaneTextField) ref.getUITextField();
        text.setShowPopup(false);

        OrgAndOrgTypeCompositeRefTreeModelOnlyEnableData refModel =
                (OrgAndOrgTypeCompositeRefTreeModelOnlyEnableData) ref.getRefModel();

        if (CMValueCheck.isNotEmpty(priceSourceObject)) {
            if (String.valueOf(PriceSourceEnum.MANUAL.toIntValue()).equals(priceSourceObject.toString())) {
                e.setReturnValue(Boolean.FALSE);
                return;
            }

            if (String.valueOf(PriceSourceEnum.STDCOST.toIntValue()).equals(priceSourceObject.toString())) {
                e.setReturnValue(Boolean.FALSE);
                return;
            }

            if (String.valueOf(PriceSourceEnum.PLAN.toIntValue()).equals(priceSourceObject.toString())
                    || String.valueOf(PriceSourceEnum.REFERENCE.toIntValue()).equals(priceSourceObject.toString())) {
                refModel.setRefTitle(CMMLangConstMaterialPriceBase.getMSG13());

                refModel.setOrgtypeIDs(new String[] {
                    IOrgConst.FINANCEORGTYPE, IOrgConst.COSTREGIONTYPE
                });
            }
            if (String.valueOf(PriceSourceEnum.PINGJUNCAIGOURUKU.toIntValue()).equals(priceSourceObject.toString())
                    || String.valueOf(PriceSourceEnum.FORWARD.toIntValue()).equals(priceSourceObject.toString())) {
                refModel.setRefTitle(CMMLangConstMaterialPriceBase.getMSG14());

                refModel.setOrgtypeIDs(new String[] {
                    IOrgConst.COSTREGIONTYPE
                });
            }
        }
        else {
            refModel.setRefTitle(CMMLangConstMaterialPriceBase.getMSG13());

            refModel.setOrgtypeIDs(new String[] {
                IOrgConst.FINANCEORGTYPE, IOrgConst.COSTREGIONTYPE
            });
        }

        ref.setMultiSelectedEnabled(true);

        e.setReturnValue(Boolean.TRUE);
    }

    /**
     * 业务单元编辑后处理逻辑
     */
    @Override
    public void afterEdit(CardPanelEvent e) {
        CardBodyAfterEditEvent ex = (CardBodyAfterEditEvent) e;
        BillCardPanel cardPanel = ex.getBillCardPanel();
        Object priceSourceObject = cardPanel.getBodyValueAt(ex.getRow(), MaterialPriceSourcesBodyVO.VPRICESOURCE);
        Integer priceSource = null;
        if (CMValueCheck.isNotEmpty(priceSourceObject)) {
            priceSource = Integer.valueOf(priceSourceObject.toString());
        }

        UIRefPane ref = (UIRefPane) ex.getBillCardPanel().getBodyItem(ex.getKey()).getComponent();

        String[] refPKs = ref.getRefPKs();
        if (CMValueCheck.isEmpty(refPKs)) {
            // 价格来源
            ex.getBillCardPanel().setBodyValueAt(null, ex.getRow(), MaterialPriceSourcesBodyVO.VPRICESOURCE);
            ex.getBillCardPanel().setBodyValueAt(null, ex.getRow(), ex.getKey());
            return;
        }

        BillScrollPane bsp = cardPanel.getBodyPanel();
        UITable table = bsp.getTable();
        boolean isInsert = false;

        List<Integer> addRowIndexes = this.getAddRowIndex(ex.getRow(), refPKs.length);
        if (ex.getRow() == ex.getBillCardPanel().getBillModel().getRowCount() - 1) {
            for (int i = 0; i < addRowIndexes.size(); i++) {
                ex.getBillCardPanel().getBodyPanel().addLine();
            }
        }
        else {
            for (int i = 0; i < addRowIndexes.size() - 1; i++) {
                table.setRowSelectionInterval(ex.getRow(), ex.getRow());
                ex.getBillCardPanel().getBodyPanel().insertLine();
            }
            isInsert = true;
        }

        addRowIndexes =
                isInsert ? this.getInsertRowIndex(ex.getRow(), refPKs.length) : this.getAddRowIndex(ex.getRow(),
                        refPKs.length);
        if (CMValueCheck.isNotEmpty(addRowIndexes)) {
            for (int i = 0; i < addRowIndexes.size(); i++) {
                // 价格来源
                ex.getBillCardPanel().setBodyValueAt(priceSource, addRowIndexes.get(i).intValue(),
                        MaterialPriceSourcesBodyVO.VPRICESOURCE);
                ex.getBillCardPanel().setBodyValueAt(refPKs[i], addRowIndexes.get(i).intValue(), ex.getKey());
            }
        }

        int startrow = ex.getRow();
        int endrow = ex.getRow() + refPKs.length - 1;
        BillModel model = ex.getBillCardPanel().getBillModel();
        // 加载关联项
        model.loadEditRelationItemValue(startrow, endrow, ex.getKey());
        model.loadLoadRelationItemValue(startrow, endrow);

        // 执行编辑公式
        for (int i = startrow; i <= endrow; i++) {
            model.execEditFormulaByKey(i, ex.getKey());
        }

    }

    private List<Integer> getAddRowIndex(int currentRow, int length) {
        List<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < length; i++) {
            indexes.add(Integer.valueOf(currentRow + i));
        }
        return indexes;
    }

    private List<Integer> getInsertRowIndex(int currentRow, int length) {
        List<Integer> indexes = new ArrayList<Integer>();
        for (int i = length; i > 0; i--) {
            indexes.add(Integer.valueOf(currentRow + i - 1));
        }
        return indexes;
    }

}
