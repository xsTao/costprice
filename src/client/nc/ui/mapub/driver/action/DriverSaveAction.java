package nc.ui.mapub.driver.action;

import java.awt.event.ActionEvent;

import nc.ui.mapub.driver.view.DriverBillForm;
import nc.ui.mapub.driver.view.dialog.DriverFormulaPane;
import nc.ui.mapub.driver.view.dialog.FormulaDialog;
import nc.ui.pubapp.uif2app.actions.DifferentVOSaveAction;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <b> 保存方法 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-4-2
 *
 * @author:wangtf
 */
public class DriverSaveAction extends DifferentVOSaveAction {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = -4504520502101826669L;

    @Override
    public void doAction(ActionEvent e) throws Exception {
        // add by szm at 2015-7-9
        if (null == ((DriverBillForm) this.getEditor()).getBillCardPanel().getHeadItem(DriverVO.PK_ORG)
                .getValueObject()) {
            ExceptionUtils.wrappBusinessException(CMDriverLangConst.getHINT_ORG_IS_NULL());
        }

        String pk_org =
                ((DriverBillForm) this.getEditor()).getBillCardPanel().getHeadItem(DriverVO.PK_ORG).getValueObject()
                        .toString();
        DriverFormulaPane formulaPane = ((DriverBillForm) this.getEditor()).getFormulaPane(pk_org);
        if (null == formulaPane) {
            ExceptionUtils.wrappBusinessException(CMDriverLangConst.getERR_FORMULA());
        }
        // 抄写工公式编码
        if (formulaPane.isHasLoadDlg()) {
            if (((FormulaDialog) formulaPane.getDlg()).isHasEditFormula()) {
                ((DriverBillForm) this.getEditor()).getBillCardPanel().getHeadItem(DriverVO.VFORMULACODE)
                        .setValue(((FormulaDialog) formulaPane.getDlg()).getFormulacode());
                ((FormulaDialog) formulaPane.getDlg()).setHasEditFormula(false);
            }
        }
        super.doAction(e);

    }
}
