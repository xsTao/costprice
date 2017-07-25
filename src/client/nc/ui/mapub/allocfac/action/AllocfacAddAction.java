/**
 *
 */
package nc.ui.mapub.allocfac.action;

import java.awt.event.ActionEvent;

import nc.bd.framework.base.CMStringUtil;
import nc.bd.framework.base.CMValueCheck;
import nc.ui.cmpub.framework.view.CMBillFormFacade;
import nc.ui.cmpub.framework.view.CMBillListViewFacade;
import nc.ui.mapub.allocfac.util.AllocfacViewUtil;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.view.BaseOrgPanel;
import nc.ui.uif2.actions.AddAction;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.allocfac.util.CMMLangConstAllocfac;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <b> �������� </b>
 * <p>
 * ����ʱ�������ݿɱ༭
 * </p>
 */
public class AllocfacAddAction extends AddAction {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7275330137618621948L;

    /**
     * ע�뿨Ƭģ��
     */
    private CMBillFormFacade editor;

    /**
     * ע���б�ģ��
     */
    private CMBillListViewFacade listEditor;

    /**
     * ��֯panel
     */
    private BaseOrgPanel orgpanel;

    public CMBillListViewFacade getListEditor() {
        return this.listEditor;
    }

    public void setListEditor(CMBillListViewFacade listEditor) {
        this.listEditor = listEditor;
    }

    public CMBillFormFacade getEditor() {
        return this.editor;
    }

    public void setEditor(CMBillFormFacade editor) {
        this.editor = editor;
    }

    /**
     * ����ʱ�������ݿ��Ա༭
     */
    @Override
    public void doAction(ActionEvent e) throws Exception {
        if (CMValueCheck.isEmpty(this.getOrgpanel())) {
            return;
        }
        // ���ڸýڵ����б�������Ҫ�趨�ù���������Ӧ���Ƚ���stopEditing()����ִ�������Ȳ�������˼�������
        this.getOrgpanel().stopEditing();
        String OrgSeledValue = this.getModel().getContext().getPk_org();
        if (e != null && CMStringUtil.isEmpty(OrgSeledValue)) {
            ExceptionUtils.wrappBusinessException(CMMLangConstAllocfac.getHINT_ORG_IS_NULL());
        }
        // ����״̬�趨
        this.initUIState();
        super.doAction(e);
        this.getEditor().setEditable(true);
    }

    /**
     * ����״̬
     */
    private void initUIState() {
        BillItem nowItem = this.getEditor().getBillCardPanel().getHeadItem(AllocfacHeadVO.VCODE);
        if (nowItem != null) {
            // ���ñ���ɱ༭
            nowItem.setEdit(true);
        }
        else {
            ExceptionUtils.wrappBusinessException(CMMLangConstAllocfac.getVCODEE_ITEM());
        }
        nowItem = this.getEditor().getBillCardPanel().getHeadItem(AllocfacHeadVO.IALLOCTYPE);
        if (nowItem != null) {
            nowItem.setEdit(true);
            // ���س��˳ɱ����ı�������ƺ�ϵ��֮���������
            AllocfacViewUtil.initBodyColumsVisible(this.getEditor().getBillCardPanel(), this.getListEditor()
                    .getBillListPanel());
        }
        else {
            ExceptionUtils.wrappBusinessException(CMMLangConstAllocfac.getVALLOCTYPE_ITEM());
        }
    }

    public BaseOrgPanel getOrgpanel() {
        return this.orgpanel;
    }

    public void setOrgpanel(BaseOrgPanel orgpanel) {
        this.orgpanel = orgpanel;
    }
}
