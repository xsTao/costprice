package nc.ui.mapub.driver.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.CopyAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.UIState;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 成本动因复制按钮
 *
 * @since v6.3
 * @version 2013-7-23 下午03:37:02
 * @author liyjf
 */
@SuppressWarnings("serial")
public class DriverCopyAction extends CopyAction {
    @Override
    public void doAction(ActionEvent e) throws Exception {
        if (this.getModel().getSelectedData() == null) {
            if (((BillManageModel) this.getModel()).getData() != null) {
                ((BillManageModel) this.getModel()).setSelectedRow(0);
            }
        }

        if (null == ((BillManageModel) this.getModel()).getContext().getPk_org()
                || ((BillManageModel) this.getModel()).getContext().getPk_org().trim().length() <= 0) {
            ExceptionUtils.wrappBusinessException(CMDriverLangConst.getHINT_ORG_IS_NULL());
        }
        super.doAction(e);
    }

    @Override
    protected boolean isActionEnable() {
        if (((BillManageModel) this.getModel()).getData() == null) {
            return false;
        }
        return this.getModel().getUiState() == UIState.NOT_EDIT;
    }
}
