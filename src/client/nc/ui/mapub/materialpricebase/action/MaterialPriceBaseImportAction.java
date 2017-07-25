/**
 *
 */
package nc.ui.mapub.materialpricebase.action;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import nc.funcnode.ui.AbstractFunclet;
import nc.itf.trade.excelimport.ImportableInfo;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.trade.excelimport.DataImporter;
import nc.ui.trade.excelimport.ExcelImportInfo;
import nc.ui.trade.excelimport.ExcelImporter;
import nc.ui.trade.excelimport.ImportProgressDlg;

import org.flexdock.util.SwingUtility;

/**
 * @since v6.3
 * @version 2015年4月7日 上午11:19:05
 * @author zhangshyb
 */
@SuppressWarnings("serial")
public class MaterialPriceBaseImportAction extends nc.ui.uif2.excelimport.ImportAction {
    @Override
    public void doAction(ActionEvent e) throws Exception {

        final JComponent parent = this.getModel().getContext().getEntranceUI();
        ImportableInfo info = super.getImportableEditor().getImportableInfo();
        if (info != null && !info.isImportable()) {
            MessageDialog.showErrorDlg(parent,
                    NCLangRes.getInstance().getStrByID("uif2", "ExceptionHandlerWithDLG-000000")/* 错误 */, NCLangRes
                            .getInstance().getStrByID("uif2", "ImportAction-000000", null, new String[] {
                                info.getCannotImportReason()
                            })/* 不可导入：{0} */);
            return;
        }
        final ExcelImporter i = new ExcelImporter();
        final ExcelImportInfo importInfo = i.importFromExcel(parent, super.getImportableEditor().getInputItems());
        if (importInfo == null) {
            return;
        }
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                DataImporter importer =
                        new DataImporter(MaterialPriceBaseImportAction.this.getImportableEditor(), importInfo, i
                                .getLogFileName());
                final ImportProgressDlg dlg = new ImportProgressDlg(parent, importer);
                dlg.setModal(false);
                SwingUtility.centerOnScreen(dlg);
                dlg.setVisible(true);
                dlg.start();
                dlg.setFunclet((AbstractFunclet) MaterialPriceBaseImportAction.this.getModel().getContext()
                        .getEntranceUI());
            }
        });

    }

}
