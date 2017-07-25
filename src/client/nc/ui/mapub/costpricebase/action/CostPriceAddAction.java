/**
 *
 */
package nc.ui.mapub.costpricebase.action;

import java.awt.event.ActionEvent;

import nc.bs.uif2.IActionCode;
import nc.ui.pmpub.action.info.ActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;

/**
 * addAction新增按鈕
 *
 * @since v6.3
 * @version 2017年7月20日 下午3:12:15
 * @author Administrator
 */

public class CostPriceAddAction extends NCAction {

    private static final long serialVersionUID = -545358298533334611L;

    protected AbstractAppModel model = null;

    public CostPriceAddAction() {
        super();
        ActionInitializer.initializeAction(this, IActionCode.ADD);
    }

    @Override
    public void doAction(ActionEvent e) throws Exception {
        this.model.setUiState(UIState.ADD);
    }

    @Override
    protected boolean isActionEnable() {
        return this.model.getUiState() == UIState.NOT_EDIT;
    }

    /**
     * 获得 model 的属性值
     *
     * @return the model
     * @since 2017年7月20日
     * @author Administrator
     */
    public AbstractAppModel getModel() {
        return this.model;
    }

    /**
     * 设置 model 的属性值
     *
     * @param model the model to set
     * @since 2017年7月20日
     * @author Administrator
     */
    public void setModel(AbstractAppModel model) {
        this.model = model;
        model.addAppEventListener(this);
    }

}
