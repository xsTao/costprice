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
 * <b> �ɱ�����༭�¼� </b>
 * <p>
 * ��ϸ��������
 * </p>
 * ��������:2010-12-30
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
     * �ж��Ƿ�����
     *
     * @param vo
     *            ѡ���VO
     * @return �Ƿ��ܱ�ɾ��
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
