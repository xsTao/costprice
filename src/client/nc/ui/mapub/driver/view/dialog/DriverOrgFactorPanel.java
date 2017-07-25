package nc.ui.mapub.driver.view.dialog;

import java.awt.FlowLayout;
import java.util.Map;

import nc.bd.business.util.FIUtil;
import nc.bd.framework.base.CMMapUtil;
import nc.bd.framework.db.CMSqlBuilder;
import nc.bs.framework.common.NCLocator;
import nc.cmpub.business.adapter.BDAdapter;
import nc.pubitf.org.IAccountingBookPubService;
import nc.pubitf.org.cache.IAccountingBookPubService_C;
import nc.ui.org.ref.AccountingBookDefaultRefModel;
import nc.ui.org.ref.LiabilityBookDefaultRefModel;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.ui.pub.beans.ValueChangedListener;
import nc.ui.resa.refmodel.FactorRefModel4Financial;
import nc.ui.resa.refmodel.FactorRefModel4Liacenter;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.uif2.LoginContext;

/**
 * 财务核算账簿和责任核算账簿共用panel
 *
 * @since v6.5
 * @version 2014年6月12日 上午9:46:19
 * @author shangzhm1
 */
public class DriverOrgFactorPanel extends UIPanel implements ValueChangedListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 环境变量
     */
    private LoginContext loginContext;

    /**
     *
     */
    private String displayName;

    private UIRefPane factorPanel;

    private UIRefPane liaFactorPanel;

    /**
     * 财务核算账簿或责任核算账簿panel
     */
    private UIRefPane orgPane;

    public DriverOrgFactorPanel(LoginContext context, String name) {
        this.loginContext = context;
        this.displayName = name;
        this.initialize();
    }

    /**
     * 初始化
     */
    private void initialize() {

        this.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
        this.add(this.getUpPane());
        this.add(this.getCenterPane());

    }

    /**
     * 页签名+财务核算账簿或责任核算账簿的panel
     *
     * @return
     */
    private UIPanel getUpPane() {
        UIPanel northPane = new UIPanel();
        UILabel label = new UILabel();
        if (CMDriverLangConst.getCM_FACTOR().equals(this.displayName)) {
            label.setText(CMDriverLangConst.getACCOUNTINGBOOK_NAME());
        }
        else if (CMDriverLangConst.getPCCM_FACTOR().equals(this.displayName)) {
            label.setText(CMDriverLangConst.getLIABILITYBOOK_NAME());
        }
        northPane.setLayout(new FlowLayout());
        northPane.add(label);
        northPane.add(this.getOrgPane());
        return northPane;
    }

    /**
     * 页签名+核算要素panel
     *
     * @return
     */
    private UIPanel getCenterPane() {
        UIPanel southPane = new UIPanel();
        UILabel label = new UILabel();
        label.setText("    " + CMDriverLangConst.getFACTORNAME());
        southPane.setLayout(new FlowLayout());
        southPane.add(label);
        southPane.add(this.getFactorPanel());
        return southPane;
    }

    /**
     * 财务核算账簿或责任核算账簿的panel
     *
     * @return
     */
    private UIRefPane getOrgPane() {
        if (this.orgPane == null) {
            this.orgPane = new UIRefPane();
            this.orgPane.setMultiSelectedEnabled(false);
            try {
                if (CMDriverLangConst.getCM_FACTOR().equals(this.displayName)) {
                    this.orgPane.setRefModel(new AccountingBookDefaultRefModel());
                    // key-业务单元,value-财务组织
                    Map<String, String> orgFinanceIDs = BDAdapter.queryFinanceOrgIDsByStockOrgIDs(new String[] {
                        this.loginContext.getPk_org()
                    });
                    String financeid = orgFinanceIDs.get(this.loginContext.getPk_org());
                    String[] accountorgs =
                            NCLocator.getInstance().lookup(IAccountingBookPubService.class)
                            .queryAccountingBookIDSByFinanceOrgID(financeid);

                    CMSqlBuilder wherePart = new CMSqlBuilder();
                    if (accountorgs != null && accountorgs.length > 0) {
                        Map<String, String> accMap =
                                NCLocator.getInstance().lookup(IAccountingBookPubService_C.class)
                                .queryAccountingBookIDByFinanceOrgIDWithMainAccountBook(new String[] {
                                            financeid
                                });
                        wherePart.append("pk_accountingbook", accountorgs);
                        // 默认主财务核算账簿
                        if (CMMapUtil.isNotEmpty(accMap)) {
                            this.orgPane.setPK(accMap.get(financeid));
                        }

                    }
                    else {
                        wherePart.append("pk_accountingbook is null or pk_accountingbook='~'");
                    }
                    this.orgPane.getRefModel().setWherePart(wherePart.toString());

                }
                else if (CMDriverLangConst.getPCCM_FACTOR().equals(this.displayName)) {
                    this.orgPane.setRefModel(new LiabilityBookDefaultRefModel());
                    String pk_liabilitybook = FIUtil.getLiabilityBookByPkOrg(this.loginContext.getPk_org());
                    CMSqlBuilder wherePart = new CMSqlBuilder();
                    if (pk_liabilitybook != null && pk_liabilitybook.trim().length() > 0 && pk_liabilitybook != "~") {

                        wherePart.append("pk_liabilitybook", pk_liabilitybook);
                        // 默认主责任核算账簿
                        this.orgPane.setPK(pk_liabilitybook);

                    }
                    else {
                        wherePart.append(" pk_liabilitybook is null or pk_liabilitybook='~' ");
                    }
                    this.orgPane.getRefModel().setWherePart(wherePart.toString());

                }
            }
            catch (BusinessException e) {
                ExceptionUtils.wrappException(e);
            }

            this.orgPane.addValueChangedListener(this);

        }
        return this.orgPane;
    }

    public UIRefPane getFactorPanel() {
        if (CMDriverLangConst.getCM_FACTOR().equals(this.displayName)) {
            if (this.factorPanel == null) {
                this.factorPanel = new UIRefPane();
                this.factorPanel.setMultiSelectedEnabled(false);
                this.factorPanel.setNotLeafSelectedEnabled(false);
                this.factorPanel.addValueChangedListener(this);
                this.factorPanel.setRefModel(new FactorRefModel4Financial());
            }
            if (this.orgPane.getRefPK() != null) {
                ((FactorRefModel4Financial) this.factorPanel.getRefModel()).setPk_accbook(this.orgPane.getRefPK());
            }
        }
        else if (CMDriverLangConst.getPCCM_FACTOR().equals(this.displayName)) {
            if (this.liaFactorPanel == null) {
                this.liaFactorPanel = new UIRefPane();
                this.liaFactorPanel.setMultiSelectedEnabled(false);
                this.liaFactorPanel.setNotLeafSelectedEnabled(false);
                this.liaFactorPanel.addValueChangedListener(this);
                this.liaFactorPanel.setRefModel(new FactorRefModel4Liacenter());
            }
            if (this.orgPane.getRefPK() != null) {
                ((FactorRefModel4Liacenter) this.liaFactorPanel.getRefModel()).setPk_liabook(this.orgPane.getRefPK());
            }
            return this.liaFactorPanel;
        }

        return this.factorPanel;
    }

    @Override
    public void valueChanged(ValueChangedEvent event) {
        // TODO
    }

}
