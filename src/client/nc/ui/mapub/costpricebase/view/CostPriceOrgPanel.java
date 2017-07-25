package nc.ui.mapub.costpricebase.view;

import java.util.ArrayList;
import java.util.List;

import nc.bd.framework.db.CMSqlBuilder;
import nc.ui.bd.pub.ref.IOrgPanelVisitElement;
import nc.ui.bd.pub.ref.IOrgPanelVisitor;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.view.OrgPanel;
import nc.ui.uif2.editor.BillForm;
import nc.vo.ml.MultiLangContext;

public class CostPriceOrgPanel extends OrgPanel implements IOrgPanelVisitElement {

    private static final long serialVersionUID = -6726060709667087759L;

    private Integer langSeq;

    public Integer getLangSeq() {
        if (this.langSeq == null) {
            this.langSeq = MultiLangContext.getInstance().getCurrentLangSeq();
        }
        return this.langSeq;
    }

    /**
     * SingleTableBillForm
     */
    private BillForm billform;

    public BillForm getBillform() {
        return this.billform;
    }

    public void setBillform(BillForm billform) {
        this.billform = billform;
    }

    /**
     * 初始化界面
     */
    @Override
    public void initUI() {
        super.initUI();
        // FactoryOrgRefUtils.transformStockOrgToFactoryOrg(this);
        /* this.initPermisionFactory(); */

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
        // BillItem formulavalueItem = this.getBillform().getBillCardPanel().getHeadItem(DriverVO.VFORMULAVALUE);
        // formulavalueItem.setComponent(this.getBillform().getFormulaPane(e.getNewPkOrg()));
        // formulavalueItem.setEdit(false);
        this.getBillform().getBillCardPanel().setBillData(this.getBillform().getBillCardPanel().getBillData());

        // ((DriverModelDataManager) this.getDataManager()).queryData(e.getNewPkOrg());
    }

    @Override
    public void accept(IOrgPanelVisitor visitor) {
        visitor.visitOrgPanel(this.getLabel(), this.getRefPane());
    }

}
