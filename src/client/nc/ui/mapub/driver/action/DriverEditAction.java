package nc.ui.mapub.driver.action;

import java.awt.event.ActionEvent;

import nc.ui.mapub.driver.view.DriverBillForm;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.actions.EditAction;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.DriverAggVO;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <b> 成本动因编辑事件 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-12-30
 *
 * @author:jilu
 */
public class DriverEditAction extends EditAction {

    private static final long serialVersionUID = 8823435161506684622L;

    /**
     * SingleTableBillForm
     */
    private DriverBillForm billform;

    /**
     * get
     *
     * @return SingleTableBillForm
     */
    public DriverBillForm getBillform() {
        return this.billform;
    }

    /**
     * set
     *
     * @param billform
     *            SingleTableBillForm
     */
    public void setBillform(DriverBillForm billform) {
        this.billform = billform;
    }

    @Override
    public void doAction(ActionEvent e) throws Exception {
        String pk_group = ((DriverVO) ((DriverAggVO) this.getModel().getSelectedData()).getParent()).getPk_group();
        if (CMDriverLangConst.GLFLAG.equals(pk_group)) {
            ExceptionUtils.wrappBusinessException(CMDriverLangConst.getSYSTEMCODE_NOT_EDIT());
        }
        super.doAction(e);
        UIRefPane panel =
                (UIRefPane) this.getBillform().getBillCardPanel().getHeadItem(DriverVO.VFORMULAVALUE).getComponent();
        panel.getUITextField().setEditable(false);
        this.billform.showMeUp();
    }

    /**
     * 判断是否被引用
     *
     * @param vo
     *            选择的VO
     * @return 是否能被删除
     */
    // private boolean isReferenced(DriverVO vo) {
    // if (null == vo) {
    // return true;
    // }
    // boolean isReferenced = false;
    //
    // try {
    // isReferenced =
    // NCLocator.getInstance().lookup(IReferenceCheck.class)
    // .isReferenced(vo.getTableName(), vo.getPrimaryKey());
    // }
    // catch (BusinessException e1) {
    // ExceptionUtils.wrappException(e1);
    // }
    //
    // return isReferenced;
    // }
}
