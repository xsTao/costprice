package nc.ui.mapub.driver.view.dialog.formulaVariableTab;

import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.Map;

import nc.ui.mapub.driver.view.dialog.AbstractDriverTabBuilder;
import nc.ui.mapub.driver.view.dialog.DriverFormulaEventSource;
import nc.ui.mapub.driver.view.dialog.DriverOrgFactorDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.formula.dialog.FormulaEventSource.FormulaEventType;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.CMDriverParameterEnum;
import nc.vo.ml.MultiLangUtil;
import nc.vo.pub.formulaedit.FormulaItem;

/**
 * 要素实际金额页签
 *
 * @since v6.5
 * @version 2014年6月11日 上午9:49:36
 * @author shangzhm1
 */
public class FactorTabBuilder extends AbstractDriverTabBuilder {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Map<String, FormulaItem> getAllVariableItems() {
        Map<String, FormulaItem> tableItems = new LinkedHashMap<String, FormulaItem>();
        String[][] svalue = {
                {
                    CMDriverLangConst.getCM_FACTOR(), CMDriverParameterEnum.FACTOR.getCode()
                }, {
                    CMDriverLangConst.getPCCM_FACTOR(), CMDriverParameterEnum.PCCM_FACTOR.getCode()
                }
        };
        for (int i = 0; i < svalue.length; i++) {
            tableItems.put(svalue[i][0], new FormulaItem(svalue[i][0], svalue[i][1], svalue[i][0]));
        }
        return tableItems;
    }

    @Override
    protected void mouseDoubleClicked(MouseEvent e, FormulaItem formulaItem, DriverFormulaEventSource eventSource) {
        // 清空界面提示信息
        ShowStatusBarMsgUtil.showStatusBarMsg(" ", this.getLoginContext());
        String displayName = formulaItem.getDisplayName();
        DriverOrgFactorDialog dialog = new DriverOrgFactorDialog(this.getLoginContext(), displayName);
        if (CMDriverLangConst.getCM_FACTOR().equals(displayName)) {

            if (dialog.showModal() == UIDialog.ID_OK) {
                String factorid = dialog.getAccountBookPanel().getFactorPanel().getRefPK();
                String code =
                        dialog.getAccountBookPanel().getFactorPanel().getRefModel()
                        .getValue("resa_factorasoa.factorcode").toString();
                String name =
                        dialog.getAccountBookPanel().getFactorPanel().getRefModel()
                        .getValue("resa_factorasoa.factorname" + MultiLangUtil.getCurrentLangSeqSuffix())
                        .toString();
                // 写公式编辑器内容
                eventSource.setEventType(FormulaEventType.INSERT_TO_EDITOR);
                String factorText = this.getText(CMDriverLangConst.getCM_FACTOR(), code, name);
                String factorTextCode = this.getCode(CMDriverParameterEnum.FACTOR.getCode(), factorid);
                eventSource.setNewString(factorText);
                eventSource.setNewValueString(factorTextCode);
            }
        }
        else if (CMDriverLangConst.getPCCM_FACTOR().equals(displayName)) {

            if (dialog.showModal() == UIDialog.ID_OK) {
                String factorid = dialog.getAccountBookPanel().getFactorPanel().getRefPK();
                String code = dialog.getAccountBookPanel().getFactorPanel().getRefCode();
                String name = dialog.getAccountBookPanel().getFactorPanel().getRefName();
                // 写公式编辑器内容
                eventSource.setEventType(FormulaEventType.INSERT_TO_EDITOR);
                String factorText = this.getText(CMDriverLangConst.getPCCM_FACTOR(), code, name);
                String factorTextCode = this.getCode(CMDriverParameterEnum.PCCM_FACTOR.getCode(), factorid);
                eventSource.setNewString(factorText);
                eventSource.setNewValueString(factorTextCode);
            }
        }
    }

    @Override
    public String getTabName() {
        return CMDriverLangConst.getFACTORTITLE();
    }

}
