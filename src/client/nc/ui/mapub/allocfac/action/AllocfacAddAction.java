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
 * <b> 新增操作 </b>
 * <p>
 * 新增时分配内容可编辑
 * </p>
 */
public class AllocfacAddAction extends AddAction {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7275330137618621948L;

    /**
     * 注入卡片模型
     */
    private CMBillFormFacade editor;

    /**
     * 注入列表模型
     */
    private CMBillListViewFacade listEditor;

    /**
     * 组织panel
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
     * 新增时分配内容可以编辑
     */
    @Override
    public void doAction(ActionEvent e) throws Exception {
        if (CMValueCheck.isEmpty(this.getOrgpanel())) {
            return;
        }
        // 由于该节点在列表界面就需要设定好工厂，所以应该先进行stopEditing()，再执行新增等操作，因此加在这里
        this.getOrgpanel().stopEditing();
        String OrgSeledValue = this.getModel().getContext().getPk_org();
        if (e != null && CMStringUtil.isEmpty(OrgSeledValue)) {
            ExceptionUtils.wrappBusinessException(CMMLangConstAllocfac.getHINT_ORG_IS_NULL());
        }
        // 输入状态设定
        this.initUIState();
        super.doAction(e);
        this.getEditor().setEditable(true);
    }

    /**
     * 输入状态
     */
    private void initUIState() {
        BillItem nowItem = this.getEditor().getBillCardPanel().getHeadItem(AllocfacHeadVO.VCODE);
        if (nowItem != null) {
            // 设置编码可编辑
            nowItem.setEdit(true);
        }
        else {
            ExceptionUtils.wrappBusinessException(CMMLangConstAllocfac.getVCODEE_ITEM());
        }
        nowItem = this.getEditor().getBillCardPanel().getHeadItem(AllocfacHeadVO.IALLOCTYPE);
        if (nowItem != null) {
            nowItem.setEdit(true);
            // 隐藏除了成本中心编码和名称和系数之外的所有列
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
