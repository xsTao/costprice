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
 * addAction�������o
 *
 * @since v6.3
 * @version 2017��7��20�� ����3:12:15
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
     * ��� model ������ֵ
     *
     * @return the model
     * @since 2017��7��20��
     * @author Administrator
     */
    public AbstractAppModel getModel() {
        return this.model;
    }

    /**
     * ���� model ������ֵ
     *
     * @param model the model to set
     * @since 2017��7��20��
     * @author Administrator
     */
    public void setModel(AbstractAppModel model) {
        this.model = model;
        model.addAppEventListener(this);
    }

}
