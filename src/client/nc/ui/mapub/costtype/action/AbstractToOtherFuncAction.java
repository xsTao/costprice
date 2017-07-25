package nc.ui.mapub.costtype.action;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.sm.funcreg.FuncRegisterVO;

/**
 * 关联功能打开其他节点
 * 
 * @since 6.0
 * @version 2011-9-26 上午09:25:09
 * @author liwzh
 */
public abstract class AbstractToOtherFuncAction extends NCAction {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -606584191880391001L;

    /**
     * MMBillManageModel
     */
    private AbstractAppModel model = null;

    public AbstractAppModel getModel() {
        return this.model;
    }

    public void setModel(AbstractAppModel model) {
        this.model = model;
    }

    /**
     * 打开节点
     * 
     * @param funCode 节点号
     * @param key 打开时的初始化类型--必须是数字字符，不传数据时传null即可
     * @param data Object 数据可能有很多种，不传数据时可为null，（vo对象，vo对象数组，聚合vo对象，聚合vo数据）
     */
    public void openFuncNode(String funCode, String key, Object data) {
        FuncletInitData initData = new FuncletInitData();
        if (key != null) {
            initData.setInitType(Integer.valueOf(key).intValue());
        }
        initData.setInitData(data);
        WorkbenchEnvironment instance = WorkbenchEnvironment.getInstance();
        FuncRegisterVO funvo = instance.getFuncRegisterVO(funCode);
        FuncletWindowLauncher.openFuncNodeInTabbedPane(null, funvo, initData, null, true);
    }

    /**
     * add by hupeng 此方法为不带数据关联
     * 
     * @param funCode 节点号
     */
    public void openFuncNode(String funCode) {
        this.openFuncNode(funCode, null, null);
    }
}
