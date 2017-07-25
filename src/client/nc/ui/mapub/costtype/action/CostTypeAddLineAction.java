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
 * <b> 成本类型增行Action </b>
 * <p>
 * 设置默认值
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
                    .getStrByID("3810006_0", "03810006-0037")/* 警告 */, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                    .getStrByID("3810006_0", "03810006-0300")/* 请先选择业务单元！ */);
            return;
        }
        super.doAction(e);
        // 设置参照多选
        BillCardPanel billCardPanel = this.getEditor().getBillCardPanel();
        if (null != billCardPanel) {
            /**
             * 物料价格来源
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
            // 设置文本框不可手工输入
            materialrefPane.getUITextField().setEditable(false);
            if (materialrefItem != null) {
                materialrefItem.setComponent(materialrefPane);
            }
            /**
             * 费用价格来源
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
            // 设置文本框不可手工输入
            costrefPane.getUITextField().setEditable(false);
            if (costrefItem != null) {
                costrefItem.setComponent(costrefPane);
            }
            // billCardPanel.setBillData(billCardPanel.getBillData());
        }
    }

    /**
     * 设置数据默认值，可以是Editor中设置的，也可以是代码级别的业务数据设置
     */
    @Override
    protected void setDefaultData(Object obj) {
        // 设置初始化的一系列值
        super.setDefaultValue(obj);

        // LoginContext context = this.getModel().getContext();
        CostTypeVO vo = (CostTypeVO) obj;
        // 设置集团ID
        vo.setPk_group(this.getContext().getPk_group());
        // 设置工厂ID
        vo.setPk_org(this.getContext().getPk_org());
        // 是否考虑废品系数默认为勾选
        vo.setBscrapfactor(UFBoolean.TRUE);
        // 是否考虑损耗系数默认为勾选
        vo.setBshrinkfactor(UFBoolean.TRUE);
        // 根据需求描述不需要设置默认生效期间，而且在集团级会出问题故注掉
        // this.setAccountPeriodAndDefaultDateTime(vo);
        // 设置默认生效日期
        /************* 库里存储的数据与vo里面的一致 ********************/
        /************* 按照要求，库里存储均为东八区 ********************/
        // vo.setCbeginmonth(AppContext.getInstance().getBusiDate().asBegin(TimeZone.getTimeZone("GMT+8:00")));
        vo.setCbeginmonth(AppContext.getInstance().getBusiDate().asBegin(TimeZone.getTimeZone("GMT+8:00")));
        // 设置默认失效期间-9999-12
        /************* 当日期为"9999-12-31 23:59:59"时前台显示不进行时区转换，但后台必须存储为"9999-12-31 23:59:59" ********************/
        vo.setCendmonth(UFDate.getDate(CMMLangConstCM0502.MAX_DATE).asEnd(TimeZone.getTimeZone("GMT+8:00")));

        if (CMBasedocValueCheck.isEmpty(this.getModel().getSelectedData())) {
            vo.setBdefault(UFBoolean.TRUE);
        }
    }
    /**
     * 不需要设置默认生效期间，故注掉
     */
    // /**
    // * 根据业务日期查找相应的会计期间
    // *
    // * @param loginDate
    // * 业务日期
    // * @throws BusinessException
    // */
    // private void setAccountPeriodAndDefaultDateTime(CostTypeVO vo) {
    // String pkOrg = this.getModel().getContext().getPk_org();
    // // 排除集团和组织为空的状况
    // if (CMBasedocValueCheck.isEmpty(pkOrg)) {
    // return;
    // }
    //
    // // 获取当前登陆时间
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
