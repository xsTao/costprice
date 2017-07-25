/**
 * 
 */
package nc.ui.mapub.allocfac.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.pubapp.uif2app.view.BaseOrgPanel;
import nc.ui.uif2.DefaultExceptionHanler;
import nc.ui.uif2.IExceptionHandler;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.actions.ShowMeUpAction;
import nc.ui.uif2.components.CommonConfirmDialogUtils;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;

public class AllocfacReturnAction extends ShowMeUpAction {
    private static final long serialVersionUID = -5576822112248718938L;

    public BaseOrgPanel orgPanel;

    private DefaultExceptionHanler exceptionHandler = new DefaultExceptionHanler();

    private NCAction saveAction;

    @Override
    public void doAction(ActionEvent e) throws Exception {
        if (this.canBeReturned()) {
            super.doAction(e);
            String pk_org = this.getOrgPanel().getModel().getContext().getPk_org();
            BillManageModel model = (BillManageModel) this.getModel();
            for (Object data : model.getData()) {
                AllocfacAggVO aggvo = (AllocfacAggVO) data;
                AllocfacHeadVO hvo = (AllocfacHeadVO) aggvo.getParent();
                if (!pk_org.equals(hvo.getPk_org())) {
                    ((UIRefPane) this.getOrgPanel().getComponent(1)).setPK(null);
                    this.getModel().getContext().setPk_org(null);
                    return;
                }
            }
        }
    }

    public BaseOrgPanel getOrgPanel() {
        return this.orgPanel;
    }

    public void setOrgPanel(BaseOrgPanel orgPanel) {
        this.orgPanel = orgPanel;
    }

    public NCAction getSaveAction() {
        return this.saveAction;
    }

    @Override
    public void setModel(AbstractUIAppModel model) {
        model.addAppEventListener(this);
        super.setModel(model);
    }

    public void setSaveAction(NCAction saveAction) {
        this.saveAction = saveAction;
    }

    @Override
    protected boolean isActionEnable() {
        if (this.getModel() instanceof IAppModelEx) {
            AppUiState uiState = ((IAppModelEx) this.getModel()).getAppUiState();
            if (uiState == AppUiState.ADD || uiState == AppUiState.EDIT) {
                return false;
            }
            return true;
        }
        return super.isActionEnable();
    }

    private boolean canBeReturned() {
        if (this.getModel().getUiState() == UIState.ADD || this.getModel().getUiState() == UIState.EDIT) {
            int i = CommonConfirmDialogUtils.showConfirmSaveDialog(this.getModel().getContext().getEntranceUI());
            this.exceptionHandler.setContext(this.getModel().getContext());
            switch (i) {
                case UIDialog.ID_YES: {
                    IExceptionHandler handler = null;
                    try {
                        handler = this.saveAction.getExceptionHandler();
                        // ±£´æ²Ù×÷
                        if (handler != null) {
                            this.saveAction.setExceptionHandler(null);
                        }
                    }
                    catch (Exception e) {
                        this.exceptionHandler.handlerExeption(e);
                        return false;
                    }
                    try {
                        this.saveAction.actionPerformed(new ActionEvent(this.saveAction, 0, null));
                    }
                    catch (Exception e) {
                        if (handler != null) {
                            handler.handlerExeption(e);
                        }
                        else {
                            this.exceptionHandler.handlerExeption(e);
                        }
                        return false;
                    }
                    finally {
                        this.saveAction.setExceptionHandler(handler);
                    }
                    return true;
                }
                case UIDialog.ID_NO: {
                    return true;
                }
                case UIDialog.ID_CANCEL: {
                    return false;
                }
                default:
                    return true;
            }
        }
        return true;
    }
}
