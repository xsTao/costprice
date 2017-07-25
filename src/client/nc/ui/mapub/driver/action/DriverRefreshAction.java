package nc.ui.mapub.driver.action;

import java.awt.event.ActionEvent;

import nc.ui.mapub.driver.model.DriverModelDataManager;
import nc.ui.mapub.driver.view.DriverOrgPanel;
import nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction;
import nc.vo.ml.MultiLangContext;

@SuppressWarnings("serial")
public class DriverRefreshAction extends DefaultRefreshAction {
    /**
     * DriverOrgPanel
     */
    private DriverOrgPanel orgpanel;

    public DriverOrgPanel getOrgpanel() {
        return this.orgpanel;
    }

    public void setOrgpanel(DriverOrgPanel orgpanel) {
        this.orgpanel = orgpanel;
    }

    @Override
    public void doAction(ActionEvent e) throws Exception {
        ((DriverModelDataManager) this.getDataManager()).queryData(this.getModel().getContext().getPk_org());
        this.showQueryInfo();
    }

    private Integer langSeq;

    public Integer getLangSeq() {
        if (this.langSeq == null) {
            this.langSeq = MultiLangContext.getInstance().getCurrentLangSeq();
        }
        return this.langSeq;
    }

    @Override
    protected boolean isActionEnable() {
        if (null == this.getOrgpanel().getModel().getContext().getPk_org()
                || this.getOrgpanel().getModel().getContext().getPk_org().trim().length() <= 0) {
            return false;
        }
        return true;
    }
}
