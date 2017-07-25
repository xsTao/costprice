package nc.ui.mapub.materialpricebase.dialog.pullPrice.action;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;

import nc.bd.framework.base.CMValueCheck;
import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.materialpricebase.IMaterialPriceBaseMaintainService;
import nc.ui.mapub.materialpricebase.dialog.errorInfo.view.ErrorInfoDialog;
import nc.ui.mapub.materialpricebase.dialog.pullPrice.util.PullPriceConfirmService;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.progress.IProgressMonitor;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.components.progress.TPAProgressUtil;
import nc.vo.mapub.acquirepricelog.entity.AcquirePriceLogVO;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPullPriceAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPullPriceBodyVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPullPriceHeadVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPullPriceResult;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 取价对话框
 * 确定按钮
 *
 * @since v6.36
 * @version 2014-5-16 下午3:41:28
 * @author zhangchd
 */

public class PullPriceConfirmAction extends NCAction {

    private static final long serialVersionUID = 8927615540322461112L;

    private BillManageModel model;

    private PullPriceConfirmService service;

    private UIDialog dialog;

    private ErrorInfoDialog erroInfoDialog;

    private BatchBillTable erroInfolist;

    private BillForm dialogBillForm;

    private IProgressMonitor progressMonitor;

    private TPAProgressUtil tpaProgressUtil;

    private IMaterialPriceBaseMaintainService pullPriceSrv;

    private AcquirePriceLogVO[] acquirePriceLogVO;

    @Override
    protected boolean isActionEnable() {
        return true;
    }

    /**
     * 默认构造函数
     */
    public PullPriceConfirmAction() {
        // @res "确定(Y)
        // this.setBtnName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("50010006_0", "050010006-0198"));
        this.setBtnName(CMMLangConstMaterialPriceBase.getMSG3());
        this.setCode("CONFIRM_CHILD_WINDOWS");

        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, Event.ALT_MASK));
        this.putValue(Action.SHORT_DESCRIPTION, CMMLangConstMaterialPriceBase.getMSG3());
        // "确定(ALT+Y)"
    }

    @Override
    public void doAction(ActionEvent e) throws Exception {
        this.getDialogBillForm().getBillCardPanel().stopEditing();
        String erroInfo = this.getService().getConfirmBeforeErroInfo();

        if (CMValueCheck.isEmpty(erroInfo)) {
            this.getDialog().closeOK();
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

                        PullPriceConfirmAction.this.pullPriceService();
                    }
                    catch (Exception e3) {
                        this.failed = e3;
                    }
                    return null;
                }

                @Override
                protected void done() {
                    PullPriceConfirmAction.this.progressMonitor.done();
                    PullPriceConfirmAction.this.progressMonitor = null;
                    if (this.failed != null) {
                        ShowStatusBarMsgUtil.showErrorMsgWithClear(CMMLangConstMaterialPriceBase.getMSG25(),
                                this.failed.getMessage(), PullPriceConfirmAction.this.getModel().getContext());
                        return;
                    }

                    AcquirePriceLogVO[] vos = PullPriceConfirmAction.this.getAcquirePriceLogVO();
                    if (CMValueCheck.isNotEmpty(vos)) {
                        PullPriceConfirmAction.this.getErroInfoDialog().getModel().initModel(vos);

                        PullPriceConfirmAction.this.getErroInfoDialog().showModal();
                    }

                }
            };
            sw.execute();

        }
        else {
            MessageDialog.showWarningDlg(this.getDialog(), CMMLangConstMaterialPriceBase.getMSG4(), erroInfo);
        }
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
            materialPullPriceResult =
                    this.getPullPriceSrv().pullPrice(
                            (MaterialPullPriceAggVO) this
                            .getDialogBillForm()
                            .getBillCardPanel()
                            .getBillValueVO(MaterialPullPriceAggVO.class.getName(),
                                    MaterialPullPriceHeadVO.class.getName(),
                                    MaterialPullPriceBodyVO.class.getName()),
                                    new String[] {
                                    ((MaterialPriceBaseAggVO) this.getModel().getSelectedData()).getParentVO()
                                    .getCmaterialpriceid()
                            });

            this.setAcquirePriceLogVO(materialPullPriceResult.getAcquirePriceLogVO());
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
        return materialPullPriceResult;
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

    public UIDialog getDialog() {
        return this.dialog;
    }

    public void setDialog(UIDialog dialog) {
        this.dialog = dialog;
    }

    public BillForm getDialogBillForm() {
        return this.dialogBillForm;
    }

    public void setDialogBillForm(BillForm dialogBillForm) {
        this.dialogBillForm = dialogBillForm;
    }

    public PullPriceConfirmService getService() {
        return this.service;
    }

    public void setService(PullPriceConfirmService service) {
        this.service = service;
    }

    public BillManageModel getModel() {
        return this.model;
    }

    public void setModel(BillManageModel model) {
        this.model = model;
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

    public BatchBillTable getErroInfolist() {
        return this.erroInfolist;
    }

    public void setErroInfolist(BatchBillTable erroInfolist) {
        this.erroInfolist = erroInfolist;
    }

}
