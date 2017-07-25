/**
 *
 */
package nc.ui.mapub.allocfac.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.allocfac.IAllocfacQueryService;
import nc.ui.cmpub.framework.view.CMBillFormFacade;
import nc.ui.cmpub.framework.view.CMBillListViewFacade;
import nc.ui.pubapp.uif2app.query2.model.ModelDataManager;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 界面初始化数据
 * <p>
 * 详细描述功能
 */

public class AllocfacModelDataManage extends ModelDataManager {

    /**
     * 注入
     */
    private CMBillFormFacade editor;

    /**
     * 注入列表模型
     */
    private CMBillListViewFacade listEditor;

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

    @Override
    public void initModel() {
        this.getModel().getContext().getPk_org();
        IAllocfacQueryService service =
                (IAllocfacQueryService) NCLocator.getInstance().lookup(IAllocfacQueryService.class.getName());
        try {
            AllocfacHeadVO headvo = new AllocfacHeadVO();
            headvo.setPk_org(this.getModel().getContext().getPk_org());
            Object[] vos = service.queryAllocByHeadVO(headvo, new String[] {
                AllocfacHeadVO.VCODE
            });
            this.getModel().initModel(vos);

        }
        catch (Exception e) {
            ExceptionUtils.wrappException(e);
        }
    }

    @Override
    public void refresh() {
        if (this.getQueryScheme() != null) {
            this.initModelByQueryScheme(this.getQueryScheme());
        }
        else {
            this.initModel();
        }
    }
}
