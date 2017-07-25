package nc.ui.mapub.driver.action;

import java.awt.event.ActionEvent;

import nc.ui.mapub.driver.view.DriverBillForm;
import nc.ui.mapub.driver.view.DriverOrgPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.actions.AddAction;
import nc.ui.uif2.UIState;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <b> 成本动因增加按钮 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-11-24
 *
 * @author:leixia
 */
@SuppressWarnings("serial")
public class DriverAddAction extends AddAction {
    /**
     * DriverOrgPanel
     */
    private DriverOrgPanel orgpanel;

    /**
     * SingleTableBillForm
     */
    private DriverBillForm billform;

    @Override
    public void doAction(ActionEvent e) throws Exception {
        if (null == this.getOrgpanel()) {
            return;
        }

        if (null == this.getOrgpanel().getModel().getContext().getPk_org()
                || this.getOrgpanel().getModel().getContext().getPk_org().trim().length() <= 0) {
            ExceptionUtils.wrappBusinessException(CMDriverLangConst.getHINT_ORG_IS_NULL());
        }
        this.model.setOtherUiState(UIState.ADD);
        super.doAction(e);
        UIRefPane panel =
                (UIRefPane) this.getBillform().getBillCardPanel().getHeadItem(DriverVO.VFORMULAVALUE).getComponent();
        panel.getUITextField().setEditable(false);
        String pk_group = this.getOrgpanel().getModel().getContext().getPk_group();
        this.getBillform().getBillCardPanel().setHeadItem(DriverVO.PK_GROUP, pk_group);
    }

    /**
     * orgpanel get method
     *
     * @return CMOrgPanel
     */
    public DriverOrgPanel getOrgpanel() {
        return this.orgpanel;
    }

    /**
     * orgpanel set method
     *
     * @param orgpanel CMOrgPanel
     */
    public void setOrgpanel(DriverOrgPanel orgpanel) {
        this.orgpanel = orgpanel;
    }

    public DriverBillForm getBillform() {
        return this.billform;
    }

    public void setBillform(DriverBillForm billform) {
        this.billform = billform;
    }

}
