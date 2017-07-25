/**
 *
 */
package nc.ui.mapub.allocfac.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.pubapp.uif2app.query2.model.ModelDataManager;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.util.BillPanelUtils;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.ui.uif2.model.AppEventConst;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.allocfac.util.CMMLangConstAllocfac;

/**
 * 组织切换事件
 *
 * @since 6.0
 * @version 2011-7-7 上午10:41:59
 * @author
 */

public class OrgChangedHandler implements IAppEventHandler<OrgChangedEvent> {

    private ModelDataManager dataManager;

    /**
     * 卡片面板
     */
    private BillForm billFormEditor;

    /**
     * 应用模型实例
     */
    private AbstractAppModel model;

    @Override
    public void handleAppEvent(OrgChangedEvent e) {
        String pk_org = e.getNewPkOrg();
        // 新增状态，需要重置表头表体数据
        if (this.billFormEditor != null && this.billFormEditor.getModel().getUiState() == UIState.ADD) {
            this.billFormEditor.handleEvent(new AppEvent(AppEventConst.UISTATE_CHANGED));
        }
        this.model.getContext().setPk_org(e.getNewPkOrg());
        // 所有参照类型主组织过滤
        BillPanelUtils.setOrgForAllRef(this.getBillFormEditor().getBillCardPanel(), this.getModel().getContext());

        this.getBillFormEditor().getBillCardPanel().getHeadItem(AllocfacHeadVO.PK_ORG).setValue(pk_org);
        this.getDataManager().initModel();
        this.showQueryInfo();
    }

    public ModelDataManager getDataManager() {
        return this.dataManager;
    }

    public void setDataManager(ModelDataManager dataManager) {
        this.dataManager = dataManager;
    }

    /**
     * 获取卡片面板
     *
     * @return 实例
     */
    public BillForm getBillFormEditor() {
        return this.billFormEditor;
    }

    /**
     * 设置卡片面板
     *
     * @param billFormEditor
     *            实例
     */
    public void setBillFormEditor(BillForm billFormEditor) {
        this.billFormEditor = billFormEditor;
    }

    /**
     * 获取应用模型实例
     *
     * @return 实例
     */
    public AbstractAppModel getModel() {
        return this.model;
    }

    /**
     * 设置应用模型实例
     *
     * @param model
     *            实例
     */
    public void setModel(AbstractAppModel model) {
        ((IAppModelEx) model).addAppEventListener(OrgChangedEvent.class, this);
        this.model = model;
    }

    private void showQueryInfo() {
        int size = 0;
        AbstractUIAppModel appModel = this.getModel();
        if (this.getModel() instanceof BillManageModel) {
            size = ((BillManageModel) appModel).getData().size();
        }
        if (size >= 0) {
            // ("该业务单元下共有" + size + "张单据。")
            ShowStatusBarMsgUtil.showStatusBarMsg(CMMLangConstAllocfac.getINFO_QUERY_BY_ORG(null, new String[] {
                    "" + size
            }), this.getModel().getContext());
        }
    }
}
