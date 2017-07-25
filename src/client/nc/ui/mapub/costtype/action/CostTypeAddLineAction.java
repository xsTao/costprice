package nc.ui.mapub.costtype.action;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import nc.bd.framework.base.CMStringUtil;
import nc.bd.framework.util.CMBasedocValueCheck;
import nc.bs.uif2.IActionCode;
import nc.ui.cmpub.framework.view.RefMutilChooseRefPane;
import nc.ui.mapub.costtype.view.CostTypeCostPriceSourceBaseData;
import nc.ui.mapub.costtype.view.CostTypeMaterialPriceSourceBaseData;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.actions.batch.BatchAddLineWithDefValueAction;
import nc.ui.uif2.actions.ActionInitializer;
import nc.vo.mapub.costtype.entity.CMMLangConstCM0502;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.uif2.LoginContext;

/**
 * <b> �ɱ���������Action </b>
 * <p>
 * ����Ĭ��ֵ
 */
public class CostTypeAddLineAction extends BatchAddLineWithDefValueAction {

    public CostTypeAddLineAction() {
        super();
        ActionInitializer.initializeAction(this, IActionCode.ADD);
    }

    @Override
    protected void processLineOperate(Object obj) throws Exception {
        super.processLineOperate(obj);
    }

    private static final long serialVersionUID = 1125294236987341266L;

    private LoginContext context;

    public LoginContext getContext() {
        return this.context;
    }

    public void setContext(LoginContext context) {
        this.context = context;
    }

    @Override
    public void doAction(ActionEvent e) throws Exception {
        if (CMStringUtil.isEmpty(this.getContext().getPk_org())
                || this.getContext().getNodeType().toString().equals("ORG_NODE")
                && this.getContext().getPk_org().equals(this.getContext().getPk_group())) {
            MessageDialog.showWarningDlg(this.getContext().getEntranceUI(), nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                    .getStrByID("3810006_0", "03810006-0037")/* ���� */, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                    .getStrByID("3810006_0", "03810006-0300")/* ����ѡ��ҵ��Ԫ�� */);
            return;
        }
        super.doAction(e);
        // ���ò��ն�ѡ
        BillCardPanel billCardPanel = this.getEditor().getBillCardPanel();
        if (null != billCardPanel) {
            /**
             * ���ϼ۸���Դ
             */
            CostTypeMaterialPriceSourceBaseData materialbaseData = new CostTypeMaterialPriceSourceBaseData();
            // materialbaseData.getInitValues(this.getContext());
            BillItem materialrefItem = null;
            Map<String, String> materialmap = materialbaseData.getRefColumn();
            for (Entry<String, String> materialentry : materialmap.entrySet()) {
                if (materialbaseData.getRefColumnPos() == 0) {
                    materialrefItem = billCardPanel.getHeadItem(materialentry.getKey().toString());
                }
                else {
                    materialrefItem = billCardPanel.getBodyItem(materialentry.getKey().toString());
                }
            }

            RefMutilChooseRefPane materialrefPane =
                    new RefMutilChooseRefPane(new Container(), billCardPanel, this.getContext().getPk_org(),
                            materialbaseData);
            // �����ı��򲻿��ֹ�����
            materialrefPane.getUITextField().setEditable(false);
            if (materialrefItem != null) {
                materialrefItem.setComponent(materialrefPane);
            }
            /**
             * ���ü۸���Դ
             */
            CostTypeCostPriceSourceBaseData costbaseData = new CostTypeCostPriceSourceBaseData();
            BillItem costrefItem = null;
            Map<String, String> costmap = costbaseData.getRefColumn();
            for (Entry<String, String> costentry : costmap.entrySet()) {
                if (costbaseData.getRefColumnPos() == 0) {
                    costrefItem = billCardPanel.getHeadItem(costentry.getKey().toString());
                }
                else {
                    costrefItem = billCardPanel.getBodyItem(costentry.getKey().toString());
                }
            }

            RefMutilChooseRefPane costrefPane =
                    new RefMutilChooseRefPane(new Container(), billCardPanel, this.getContext().getPk_org(),
                            costbaseData);
            // �����ı��򲻿��ֹ�����
            costrefPane.getUITextField().setEditable(false);
            if (costrefItem != null) {
                costrefItem.setComponent(costrefPane);
            }
            // billCardPanel.setBillData(billCardPanel.getBillData());
        }
    }

    /**
     * ��������Ĭ��ֵ��������Editor�����õģ�Ҳ�����Ǵ��뼶���ҵ����������
     */
    @Override
    protected void setDefaultData(Object obj) {
        // ���ó�ʼ����һϵ��ֵ
        super.setDefaultValue(obj);

        // LoginContext context = this.getModel().getContext();
        CostTypeVO vo = (CostTypeVO) obj;
        // ���ü���ID
        vo.setPk_group(this.getContext().getPk_group());
        // ���ù���ID
        vo.setPk_org(this.getContext().getPk_org());
        // �Ƿ��Ƿ�Ʒϵ��Ĭ��Ϊ��ѡ
        vo.setBscrapfactor(UFBoolean.TRUE);
        // �Ƿ������ϵ��Ĭ��Ϊ��ѡ
        vo.setBshrinkfactor(UFBoolean.TRUE);
        // ����������������Ҫ����Ĭ����Ч�ڼ䣬�����ڼ��ż���������ע��
        // this.setAccountPeriodAndDefaultDateTime(vo);
        // ����Ĭ����Ч����
        /************* ����洢��������vo�����һ�� ********************/
        /************* ����Ҫ�󣬿���洢��Ϊ������ ********************/
        // vo.setCbeginmonth(AppContext.getInstance().getBusiDate().asBegin(TimeZone.getTimeZone("GMT+8:00")));
        vo.setCbeginmonth(AppContext.getInstance().getBusiDate().asBegin(TimeZone.getTimeZone("GMT+8:00")));
        // ����Ĭ��ʧЧ�ڼ�-9999-12
        /************* ������Ϊ"9999-12-31 23:59:59"ʱǰ̨��ʾ������ʱ��ת��������̨����洢Ϊ"9999-12-31 23:59:59" ********************/
        vo.setCendmonth(UFDate.getDate(CMMLangConstCM0502.MAX_DATE).asEnd(TimeZone.getTimeZone("GMT+8:00")));

        if (CMBasedocValueCheck.isEmpty(this.getModel().getSelectedData())) {
            vo.setBdefault(UFBoolean.TRUE);
        }
    }
    /**
     * ����Ҫ����Ĭ����Ч�ڼ䣬��ע��
     */
    // /**
    // * ����ҵ�����ڲ�����Ӧ�Ļ���ڼ�
    // *
    // * @param loginDate
    // * ҵ������
    // * @throws BusinessException
    // */
    // private void setAccountPeriodAndDefaultDateTime(CostTypeVO vo) {
    // String pkOrg = this.getModel().getContext().getPk_org();
    // // �ų����ź���֯Ϊ�յ�״��
    // if (CMBasedocValueCheck.isEmpty(pkOrg)) {
    // return;
    // }
    //
    // // ��ȡ��ǰ��½ʱ��
    // UFDate loginDate = AppUiContext.getInstance().getBusiDate();
    // String cPeriod = null;
    // try {
    // cPeriod = FIUtil.getAccYearMonthByDate(pkOrg, loginDate);
    // }
    // catch (BusinessException e1) {
    // ExceptionUtils.wrappException(e1);
    // }
    // vo.setCbeginmonth(cPeriod);
    // vo.setCendmonth(CostTypeVO.MAXENDMONTH);
    // }
}
