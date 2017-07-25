package nc.ui.mapub.costtype.model;

import java.util.TimeZone;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.mapub.costtype.ICostTypeService;
import nc.ui.pubapp.uif2app.model.BatchModelDataManager;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;

/**
 * 成本类型数据模型管理器
 * <p>
 * 初始化操作以及显示失效操作
 */

public class CostTypeModelDataManager extends BatchModelDataManager {
    /**
     * 显示失效
     */
    private boolean showLegal = false;

    @Override
    public void initModel() {
        // 获取客户端的时间
        UFDate currentDate = WorkbenchEnvironment.getInstance().getBusiDate().asBegin(TimeZone.getTimeZone("GMT+8:00"));
        String pk_org = this.getModel().getContext().getPk_org();
        String pk_group = this.getModel().getContext().getPk_group();
        if (null == pk_org || pk_org.trim().length() == 0) {
            this.getModel().getContext().setPk_org(pk_group);
        }
        ICostTypeService costTypeService =
                (ICostTypeService) NCLocator.getInstance().lookup(ICostTypeService.class.getName());
        ISuperVO[] vos =
                costTypeService.queryCostTypeByDate(this.getModel().getContext(), currentDate,
                        UFBoolean.valueOf(!this.showLegal));
        this.model.initModel(vos);
    }

    /**
     * 刷新
     */
    @Override
    public void refresh() {
        this.initModel();
    }

    public boolean isShowLegal() {
        return this.showLegal;
    }

    public void setShowLegal(boolean showLegal) {
        this.showLegal = showLegal;
    }
}
