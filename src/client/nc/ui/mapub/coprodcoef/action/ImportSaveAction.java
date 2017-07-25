package nc.ui.mapub.coprodcoef.action;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.coprodcoef.ICoprodcoefMaintain;
import nc.ui.pubapp.uif2app.actions.DifferentVOSaveAction;
import nc.ui.uif2.UIState;
import nc.vo.mapub.coprodcoef.entity.CMMLangConstCoprodcoef;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.uif2.LoginContext;

/**
 * @since 6.0
 * @version 2014-10-11 下午12:16:46
 * @author zhangshyb
 */
public class ImportSaveAction extends DifferentVOSaveAction {

    private static final long serialVersionUID = -6062213396471084216L;

    private LoginContext context;

    public LoginContext getContext() {
        return this.context;
    }

    public void setContext(LoginContext context) {
        this.context = context;
    }

    @Override
    protected void doAddSave(Object value) throws Exception {
        // 取界面vo数据
        IBill[] clientVOs = new IBill[] {
            (IBill) value
        };

        ClientBillToServer<IBill> tool = new ClientBillToServer<IBill>();

        // 取得差异VO，并把差异vo传到后台
        IBill[] lightVOs = tool.constructInsert(clientVOs);

        IBill[] afterUpdateVOs = null;

        if (this.getService() == null) {
            throw new BusinessException(CMMLangConstCoprodcoef.getErrNullService());
        }
        afterUpdateVOs = NCLocator.getInstance().lookup(ICoprodcoefMaintain.class).insert((CoprodcoefAggVO[]) lightVOs);

        // clientVOs为界面上的数据，afterUpdateVOs为后台返回的差异数据，取全vo数据
        new ClientBillCombinServer<IBill>().combine(clientVOs, afterUpdateVOs);

        this.getModel().setUiState(UIState.NOT_EDIT);
        this.getModel().directlyAdd(clientVOs[0]);
    }
}
