package nc.ui.mapub.driver.action;

import java.awt.event.ActionEvent;

import nc.ui.mapub.driver.view.DriverBillForm;
import nc.ui.pubapp.uif2app.actions.DeleteAction;
import nc.ui.uif2.model.IMultiRowSelectModel;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.DriverAggVO;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 成本动因删除按钮
 *
 * @since 6.0
 * @version 2011-8-26 下午02:41:53
 * @author chengjieb
 */
@SuppressWarnings("serial")
public class DriverDeleteAction extends DeleteAction {

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
        Object[] tempDatas = ((IMultiRowSelectModel) this.getModel()).getSelectedOperaDatas();
        for (Object tempData : tempDatas) {
            String pk_group = ((DriverVO) ((DriverAggVO) tempData).getParent()).getPk_group();
            if (CMDriverLangConst.GLFLAG.equals(pk_group)) {
                ExceptionUtils.wrappBusinessException(CMDriverLangConst.getSYSTEMCODE_NOT_DELETE());
            }
        }
        super.doAction(e);
    }
}
