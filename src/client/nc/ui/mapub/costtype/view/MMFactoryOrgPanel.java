package nc.ui.mapub.costtype.view;

import java.util.HashSet;
import java.util.Set;

import nc.itf.org.IOrgConst;
import nc.ui.bd.pub.view.CMOrgPanel;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.org.util.OrgTypeManager;
import nc.vo.uif2.LoginContext;

/**
 * ���Ի����������õ�����֯���ǹ���ʱ������ʾ�� ��������ʾ�в��ǹ�������֯(���޸�)
 * 
 * @author zhangweix
 */
public class MMFactoryOrgPanel extends CMOrgPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MMFactoryOrgPanel() {
        // this.setLabelName(NCLangRes.getInstance().getString("common", "����",
        // "UC000-0001685"));
        this.setLabelName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC000-000003")/* @res "ҵ��Ԫ" */);
    }

    /**
     * ��ʼ��Ĭ����֯
     */
    @Override
    protected void initDefaultOrg() {
        String defaultOrg = this.getModel().getContext().getPk_org();
        if (defaultOrg != null) {
            // ֱ�����ò��յ�ֵ,�޷�������֯�ı��¼�,�޸�ΪsetPkOrg����
            // this.getRefPane().setPK(this.getModel().getContext().getPk_org());

            if (OrgTypeManager.getInstance().isTypeOfByPk(defaultOrg, IOrgConst.FACTORYTYPE)) {
                // this.setPkOrg(defaultOrg);
                this.getRefPane().setPK(defaultOrg);
            }
            else {
                // this.setPkOrg(null);
                this.getRefPane().setPK(null);
            }
        }
    }

    @Override
    protected String[] getNeedShowOrgPks() {
        LoginContext context = this.getModel().getContext();
        String[] orgPKs = context.getFuncInfo().getFuncPermissionPkorgs();
        Set<String> orgSet = new HashSet<String>();
        // �������null�����ʾȫ����֯�����е� orgPKs Ϊ nullʱ������һ��������
        if (orgPKs != null && orgPKs.length > 0) {
            for (String orgpk : orgPKs) {

                Boolean isFactory = OrgTypeManager.getInstance().isTypeOfByPk(orgpk, IOrgConst.FACTORYTYPE);
                if (isFactory != null && isFactory.booleanValue()) {
                    orgSet.add(orgpk);

                }
            }
        }
        return orgSet.toArray(new String[0]);
    }

    private CostTypeBatchBillTable BatchBillTable;

    public CostTypeBatchBillTable getBatchBillTable() {
        return this.BatchBillTable;
    }

    public void setBatchBillTable(CostTypeBatchBillTable BatchBillTable) {
        this.BatchBillTable = BatchBillTable;
    }

    @Override
    protected void handleOrgChangedEvent(OrgChangedEvent e) {
        // ������֯�¼��ı�ʱ��ʼ���ֶεĲ��ն�ѡ
        // BillCardPanel billCardPanel = this.getBatchBillTable().getBillCardPanel();
        // if (null != billCardPanel) {
        // /**
        // * ���ϼ۸���Դ
        // */
        // CTMaterialPriceSourceBaseData materialbaseData = new CTMaterialPriceSourceBaseData();
        // BillItem materialrefItem = null;
        // Map<String, String> materialmap = materialbaseData.getRefColumn();
        // for (Entry<String, String> materialentry : materialmap.entrySet()) {
        // if (materialbaseData.getRefColumnPos() == 0) {
        // materialrefItem = billCardPanel.getHeadItem(materialentry.getKey().toString());
        // }
        // else {
        // materialrefItem = billCardPanel.getBodyItem(materialentry.getKey().toString());
        // }
        // }
        //
        // CMUIRefPaneForEnum materialrefPane =
        // new CMUIRefPaneForEnum(new Container(), materialbaseData, billCardPanel, materialrefItem);
        // if (materialrefItem != null) {
        // materialrefItem.setComponent(materialrefPane);
        // }
        // /**
        // * ���ü۸���Դ
        // */
        // CTCostPriceSourceBaseData costbaseData = new CTCostPriceSourceBaseData();
        // BillItem costrefItem = null;
        // Map<String, String> costmap = costbaseData.getRefColumn();
        // for (Entry<String, String> costentry : costmap.entrySet()) {
        // if (costbaseData.getRefColumnPos() == 0) {
        // costrefItem = billCardPanel.getHeadItem(costentry.getKey().toString());
        // }
        // else {
        // costrefItem = billCardPanel.getBodyItem(costentry.getKey().toString());
        // }
        // }
        //
        // CMUIRefPaneForEnum costrefPane =
        // new CMUIRefPaneForEnum(new Container(), costbaseData, billCardPanel, costrefItem);
        // if (costrefItem != null) {
        // costrefItem.setComponent(costrefPane);
        // }
        // billCardPanel.setBillData(billCardPanel.getBillData());
        // }

        String pk_org = this.getRefPane().getRefPK();
        this.getModel().getContext().setPk_org(pk_org);
        if (pk_org != null) {
            this.getDataManager().initModel();
        }
        ShowStatusBarMsgUtil.showStatusBarMsg(" ", this.getModel().getContext());
    }
}
