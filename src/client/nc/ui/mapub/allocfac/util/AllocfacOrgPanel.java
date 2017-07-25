/**
 *
 */
package nc.ui.mapub.allocfac.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import nc.bd.framework.db.CMSqlBuilder;
import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.allocfac.IAllocfacQueryService;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.OrgPanel;
import nc.ui.pubapp.uif2app.view.util.BillPanelUtils;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.ui.uif2.model.AppEventConst;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.allocfac.util.CMMLangConstAllocfac;

/**
 * @since v6.3
 * @version 2015年2月13日 上午10:42:48
 * @author liyjf
 */
@SuppressWarnings("serial")
public class AllocfacOrgPanel extends OrgPanel {
    /**
     * 卡片面板
     */
    private BillForm billFormEditor;

    /**
     * 初始化界面
     */
    @Override
    public void initUI() {
        super.initUI();
        JLabel label = new JLabel("    ");
        this.add(label);
        label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickCount = e.getClickCount();
                if (clickCount > 3) {
                    NCLocator.getInstance().lookup(IAllocfacQueryService.class).cleanBatchLimit();
                    ShowStatusBarMsgUtil.showStatusBarMsg(
                            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0337"),
                            AllocfacOrgPanel.this.getModel().getContext());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        // FactoryOrgRefUtils.transformStockOrgToFactoryOrg(this);
        this.initPermisionFactory();

    }

    /**
     * 过滤用户有权限的组织
     */
    private void initPermisionFactory() {
        // 用户有权限的组织
        String[] hesPermissionOrgs = this.getModel().getContext().getPkorgs();
        String wherePart = null;
        if (hesPermissionOrgs == null || hesPermissionOrgs.length == 0) {
            wherePart = "1=2";
        }
        else {
            CMSqlBuilder sb = new CMSqlBuilder();
            List<String> orgsList = new ArrayList<String>();
            for (String org : hesPermissionOrgs) {
                if (orgsList.size() == 900) {
                    sb.append("pk_org", orgsList.toArray(new String[orgsList.size()]));
                    sb.or();
                    orgsList.clear();
                }
                orgsList.add(org);
            }
            if (orgsList.size() > 0) {
                sb.append("pk_org", orgsList.toArray(new String[orgsList.size()]));
                sb.or();
            }
            wherePart = sb.toString().substring(0, sb.toString().length() - 4);
        }
        this.getRefPane().getRefModel().setWherePart(wherePart);
    }

    @Override
    protected void handleOrgChangedEvent(OrgChangedEvent e) {
        String pk_org = e.getNewPkOrg();
        // 新增状态，需要重置表头表体数据
        if (this.billFormEditor != null && this.billFormEditor.getModel().getUiState() == UIState.ADD) {
            this.billFormEditor.handleEvent(new AppEvent(AppEventConst.UISTATE_CHANGED));
        }
        this.getModel().getContext().setPk_org(e.getNewPkOrg());
        // 所有参照类型主组织过滤
        BillPanelUtils.setOrgForAllRef(this.getBillFormEditor().getBillCardPanel(), this.getModel().getContext());

        this.getBillFormEditor().getBillCardPanel().getHeadItem(AllocfacHeadVO.PK_ORG).setValue(pk_org);
        this.getDataManager().initModel();
        this.showQueryInfo();
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

    public BillForm getBillFormEditor() {
        return this.billFormEditor;
    }

    public void setBillFormEditor(BillForm billFormEditor) {
        this.billFormEditor = billFormEditor;
    }

}
