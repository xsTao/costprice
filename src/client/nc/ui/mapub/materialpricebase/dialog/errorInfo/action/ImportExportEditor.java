package nc.ui.mapub.materialpricebase.dialog.errorInfo.action;

import java.util.List;

import nc.itf.trade.excelimport.ExportDataInfo;
import nc.ui.pub.bill.BillData;
import nc.ui.trade.excelimport.InputItem;
import nc.ui.uif2.excelimport.DefaultUIF2ImportableEditor;
import nc.ui.uif2.model.BatchBillTableModel;
import nc.vo.pub.ExtendedAggregatedValueObject;

/**
 * 错误信息对话框
 * 导入导出编辑器
 * 
 * @since 6.36
 * @version 2014-12-15 上午9:17:09
 * @author zhangchd
 */
public class ImportExportEditor extends DefaultUIF2ImportableEditor {
    /**
     * 导出数据
     */
    @Override
    public ExportDataInfo getValue(List<InputItem> exportItems) {

        Object[] vos = this.getAllObject();
        BillData billData = this.getBillcardPanelEditor().getBillCardPanel().getBillData();
        ExtendedAggregatedValueObject[] aggvos =
                this.getDataConvertor().convertDataFromEditorData(billData, vos, exportItems);
        return new ExportDataInfo(this.beforeExport(aggvos));
    }

    private Object[] getAllObject() {
        Object[] resultObjects = null;
        if (this.getAppModel() instanceof BatchBillTableModel) {
            BatchBillTableModel bm = (BatchBillTableModel) this.getAppModel();
            resultObjects = bm.getRows().toArray(new Object[bm.getRows().size()]);
        }
        return resultObjects;
    }
}
