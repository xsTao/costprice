package nc.ui.mapub.materialpricebase.dialog.pullPrice.action;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Action;
import javax.swing.SwingWorker;

import nc.bd.framework.base.CMValueCheck;
import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.mapub.materialpricebase.IMaterialPriceBaseMaintainService;
import nc.ui.mapub.materialpricebase.dialog.errorInfo.view.ErrorInfoDialog;
import nc.ui.mapub.materialpricebase.dialog.priceSources.util.PriceSourcesEnumMap;
import nc.ui.mapub.materialpricebase.dialog.pullPrice.util.PullPriceService;
import nc.ui.mapub.materialpricebase.util.PricSourcesQueryService;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.progress.IProgressMonitor;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillUIUtil;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.components.progress.TPAProgressUtil;
import nc.vo.mapub.acquirepricelog.entity.AcquirePriceLogVO;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPullPriceHeadVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPullPriceResult;
import nc.vo.mapub.materialpricebase.enumeration.PriceSourceEnum;
import nc.vo.pub.BusinessException;
import nc.vo.pub.bill.BillTempletBodyVO;
import nc.vo.pub.bill.BillTempletVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.sm.UserVO;

/**
 * 物料价格来源
 * 取价按钮
 *
 * @since 6.36
 * @version 2014-11-29 上午10:15:46
 * @author zhangchd
 */
public class PullPriceAction extends NCAction {

    private static final long serialVersionUID = -5867722643195938539L;

    private PricSourcesQueryService service;

    private PullPriceService pullService;

    private BillManageModel model;

    private BillForm editor;

    private BillManageModel dialogModel;

    private UIDialog dialog;

    private ErrorInfoDialog erroInfoDialog;

    private IProgressMonitor progressMonitor;

    private TPAProgressUtil tpaProgressUtil;

    private IMaterialPriceBaseMaintainService pullPriceSrv;

    private AcquirePriceLogVO[] acquirePriceLogVO;

    public PullPriceAction() {
        // "取价"
        String cancelname = CMMLangConstMaterialPriceBase.getMSG21();
        // 按钮名称
        this.setBtnName(cancelname);
        // 按钮编码
        this.setCode("FillData");
        // 按钮事件
        this.putValue(Action.SHORT_DESCRIPTION, cancelname);
    }

    @Override
    protected boolean isActionEnable() {
        boolean pullPriceable = false;
        if (UIState.ADD.equals(this.getModel().getUiState()) || UIState.EDIT.equals(this.getModel().getUiState())) {
            pullPriceable = true;
        }
        int rowCount = this.getModel().getRowCount();
        // getRowCount();
        if (rowCount >= 1 || pullPriceable) {
            return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
     */
    @Override
    public void doAction(ActionEvent e) throws Exception {
        Integer[] selectCount = this.getModel().getSelectedOperaRows();
        if (selectCount.length > 1) {
            ShowStatusBarMsgUtil.showErrorMsg(CMMLangConstMaterialPriceBase.getMSG4(),
                    CMMLangConstMaterialPriceBase.getMSG22(), this.getModel().getContext());
            return;
        }
        // 界面选择的数据
        Object selectData = this.getModel().getSelectedData();
        if (CMValueCheck.isNotEmpty(selectData)) {
            ShowStatusBarMsgUtil.showStatusBarMsg(" ", this.getModel().getContext());
            // 价格来源集合
            Set<String> priceSourceSet = this.getService().getVBPullPriceByBillFormDatas(selectData);

            if (CMValueCheck.isEmpty(priceSourceSet)) {
                // 直接进行取价
                // 1.取价前检查
                // 判断按照表头或表体取价的来源是否只有：手工录入，如果是则报错，不取价
                String erroInfo = this.getPullService().getPullPriceBeforeErroInfo(selectData);
                if (CMValueCheck.isNotEmpty(erroInfo)) {
                    ShowStatusBarMsgUtil.showErrorMsg(CMMLangConstMaterialPriceBase.getMSG4(), erroInfo, this
                            .getModel().getContext());
                    return;
                }

                // 执行取价
                if (this.progressMonitor == null) {
                    this.progressMonitor = this.getTpaProgressUtil().getTPAProgressMonitor();
                }
                else if (!this.progressMonitor.isDone()) {
                    // 操作太频繁直接返回
                    ShowStatusBarMsgUtil.showStatusBarMsg(CMMLangConstMaterialPriceBase.getMSG23(), this.getModel()
                            .getContext());
                    return;
                }

                // 开始任务,总任务数未知
                this.progressMonitor.beginTask(CMMLangConstMaterialPriceBase.getMSG24(),
                        IProgressMonitor.UNKNOWN_TOTAL_TASK);
                this.progressMonitor.setProcessInfo(CMMLangConstMaterialPriceBase.getMSG24());

                SwingWorker<Object[], Object> sw = new SwingWorker<Object[], Object>() {
                    private Exception failed = null;

                    @Override
                    protected Object[] doInBackground() throws Exception {
                        try {

                            PullPriceAction.this.pullPriceService();
                        }
                        catch (Exception e3) {
                            this.failed = e3;
                        }
                        return null;
                    }

                    @Override
                    protected void done() {
                        PullPriceAction.this.progressMonitor.done();
                        PullPriceAction.this.progressMonitor = null;
                        if (this.failed != null) {
                            ShowStatusBarMsgUtil.showErrorMsgWithClear(CMMLangConstMaterialPriceBase.getMSG25(),
                                    this.failed.getMessage(), PullPriceAction.this.getModel().getContext());
                            return;
                        }

                        AcquirePriceLogVO[] vos = PullPriceAction.this.getAcquirePriceLogVO();
                        if (CMValueCheck.isNotEmpty(vos)) {
                            PullPriceAction.this.getErroInfoDialog().getModel().initModel(vos);

                            PullPriceAction.this.getErroInfoDialog().showModal();
                        }

                    }
                };
                sw.execute();
            }
            else {
                // 打开取价参数对话框
                this.getPullPriceParaDialog(priceSourceSet, selectData);
            }

        }
        else {
            ShowStatusBarMsgUtil.showErrorMsg(CMMLangConstMaterialPriceBase.getMSG4(),
                    CMMLangConstMaterialPriceBase.getMSG22(), this.getModel().getContext());
        }

    }

    /**
     * 打开取价参数对话框
     *
     * @param priceSourceSet
     * @param selectData
     */
    private void getPullPriceParaDialog(Set<String> priceSourceSet, Object selectData) {
        Set<String> vbSet = new HashSet<String>(); // 显示
        Set<String> hdSet = new HashSet<String>(); // 隐藏
        hdSet.add("biaozhunchengben");
        hdSet.add("pingjuncaigouruku");
        hdSet.add("zuixinjiecunjia");
        hdSet.add("mapub_materialpullprice");
        for (String priceSource : priceSourceSet) {
            if (String.valueOf(PriceSourceEnum.PINGJUNCAIGOURUKU.toIntValue()).equals(priceSource)
                    || String.valueOf(PriceSourceEnum.FORWARD.toIntValue()).equals(priceSource)) {
                vbSet.add("mapub_materialpullprice");
            }
            vbSet.add(PriceSourcesEnumMap.getPullPriceDisplayEnum().get(priceSource));
        }
        hdSet.removeAll(vbSet);
        BillCardPanel billCardPanel = this.getEditor().getBillCardPanel();
        if (null != billCardPanel) {
            UserVO uservo = WorkbenchEnvironment.getInstance().getLoginUser();
            BillTempletVO templetVO =
                    BillUIUtil.getDefaultTempletStatic("38100110", uservo.getCuserid(), uservo.getPk_group(),
                            new String[] {
                                "3810011002"
                            })[0];
            if (CMValueCheck.isNotEmpty(templetVO)) {
                // getBillData().getBillTempletVO();
                BillTempletBodyVO[] vos = templetVO.getBodyVO();

                for (BillTempletBodyVO vo : vos) {
                    String key = vo.getTable_code();

                    if (hdSet.contains(key)) {
                        vo.setListshowflag(Boolean.FALSE);
                        vo.setShowflag(Boolean.FALSE);
                    }

                }
                billCardPanel.setBillData(new BillData(templetVO));
            }
        }
        // 初始化数据
        this.getDialogModel().initModel(this.getService().getMaterialPullPriceAggVOByBillFormDatas(selectData));
        // 设置状态
        if (!UIState.EDIT.equals(this.getDialogModel().getUiState())) {
            this.getDialogModel().setUiState(UIState.EDIT);
        }
        UIRefPane refPane =
                (UIRefPane) this.getEditor().getBillCardPanel().getHeadItem(MaterialPullPriceHeadVO.CPERIODSCHEME)
                        .getComponent();
        refPane.getUITextField().setSelectallWhenFocus(false);
        // 显示对话框
        this.dialog.showModal();

    }

    /**
     * 执行取价
     */
    private void pullPriceService() {
        MaterialPullPriceResult materialPullPriceResult = this.getMaterialPullPriceResult();

        if (CMValueCheck.isNotEmpty(materialPullPriceResult)) {
            MaterialPriceBaseAggVO[] materialPriceBaseAggVOs = materialPullPriceResult.getMaterialPriceBaseAggVO();
            // 1、执行前台界面更新
            this.getModel().directlyUpdate(materialPriceBaseAggVOs);

            // 2、 刷新状态栏
            ShowStatusBarMsgUtil
                    .showStatusBarMsg(materialPullPriceResult.getResultInfo(), this.getModel().getContext());

        }
        else {
            ShowStatusBarMsgUtil.showErrorMsg(CMMLangConstMaterialPriceBase.getMSG4(),
                    CMMLangConstMaterialPriceBase.getMSG26(), this.getModel().getContext());
        }
    }

    private MaterialPullPriceResult getMaterialPullPriceResult() {

        MaterialPullPriceResult materialPullPriceResult = null;
        try {
            materialPullPriceResult = this.getPullPriceSrv().pullPrice(null, new String[] {
                ((MaterialPriceBaseAggVO) this.getModel().getSelectedData()).getParentVO().getCmaterialpriceid()
            });

            this.setAcquirePriceLogVO(materialPullPriceResult.getAcquirePriceLogVO());
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
        return materialPullPriceResult;
    }

    public BillManageModel getDialogModel() {
        return this.dialogModel;
    }

    public void setDialogModel(BillManageModel dialogModel) {
        this.dialogModel = dialogModel;
    }

    public UIDialog getDialog() {
        return this.dialog;
    }

    public void setDialog(UIDialog dialog) {
        this.dialog = dialog;
    }

    public BillManageModel getModel() {
        return this.model;
    }

    public void setModel(BillManageModel model) {
        this.model = model;
        model.addAppEventListener(this);
    }

    public BillForm getEditor() {
        return this.editor;
    }

    public void setEditor(BillForm editor) {
        this.editor = editor;
    }

    public PricSourcesQueryService getService() {
        return this.service;
    }

    public void setService(PricSourcesQueryService service) {
        this.service = service;
    }

    public PullPriceService getPullService() {
        return this.pullService;
    }

    public void setPullService(PullPriceService pullService) {
        this.pullService = pullService;
    }

    public ErrorInfoDialog getErroInfoDialog() {
        return this.erroInfoDialog;
    }

    public void setErroInfoDialog(ErrorInfoDialog erroInfoDialog) {
        this.erroInfoDialog = erroInfoDialog;
    }

    public final AcquirePriceLogVO[] getAcquirePriceLogVO() {
        return this.acquirePriceLogVO;
    }

    public void setAcquirePriceLogVO(AcquirePriceLogVO[] acquirePriceLogVO) {
        this.acquirePriceLogVO = acquirePriceLogVO;
    }

    public TPAProgressUtil getTpaProgressUtil() {
        if (this.tpaProgressUtil == null) {
            this.tpaProgressUtil = new TPAProgressUtil();
            this.tpaProgressUtil.setContext(this.getModel().getContext());
        }

        return this.tpaProgressUtil;
    }

    /**
     * 取价接口服务
     *
     * @return IMaterialPriceBaseMaintainService
     */
    private IMaterialPriceBaseMaintainService getPullPriceSrv() {
        if (this.pullPriceSrv == null) {
            this.pullPriceSrv = NCLocator.getInstance().lookup(IMaterialPriceBaseMaintainService.class);
        }
        return this.pullPriceSrv;
    }
}
