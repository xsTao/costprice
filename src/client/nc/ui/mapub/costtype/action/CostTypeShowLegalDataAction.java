package nc.ui.mapub.costtype.action;

import java.awt.event.ActionEvent;

import nc.ui.mapub.costtype.model.CostTypeModelDataManager;
import nc.ui.uif2.UIFCheckBoxAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractUIAppModel;

/**
 * <b> 成本类型显示失效Action </b>
 * <p>
 * 成本类型显示失效Action
 * </p>
 * 创建日期:2010-1-22
 *
 * @author:张伟
 */
public class CostTypeShowLegalDataAction extends UIFCheckBoxAction {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1301050670082527056L;

    /**
     * model
     */
    private AbstractUIAppModel model = null;

    /**
     * dataManager
     */
    private CostTypeModelDataManager dataManager = null;

    /**
     * Constructor
     */
    public CostTypeShowLegalDataAction() {
        this.setBtnName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0092")/*
                                                                                                            * @res
                                                                                                            * "显示失效"
                                                                                                            */);
        this.setCode("showLegal");
        // 添加了“显示失效”按钮的Tips及快捷键 纪录 2010-04-15修改
        // 快捷键会导致界面显示有问题，跟需求确认，将其去掉
        // this.putValue(Action.SHORT_DESCRIPTION, CMMLangConstCM0502.GET_BTN_LEGAL() + "(ALT+L)");
        // this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke('L', Event.ALT_MASK));
    }

    @Override
    public void doAction(ActionEvent e) throws Exception {
        if (this.isSelected()) {
            this.getDataManager().setShowLegal(true);
        }
        else {
            this.getDataManager().setShowLegal(false);
        }

        this.getDataManager().refresh();
    }

    @Override
    protected boolean isActionEnable() {

        boolean isInitUIState = this.getModel().getUiState() == UIState.INIT;
        boolean isNotEditUIState = this.getModel().getUiState() == UIState.NOT_EDIT;
        return isInitUIState || isNotEditUIState;
    }

    /**
     * return model
     *
     * @return model
     */
    public AbstractUIAppModel getModel() {
        return this.model;
    }

    /**
     * to set model
     *
     * @param model
     *            AbstractUIAppModel
     */
    public void setModel(AbstractUIAppModel model) {
        this.model = model;
        this.model.addAppEventListener(this);
    }

    /**
     * return dataManager
     *
     * @return dataManager
     */
    public CostTypeModelDataManager getDataManager() {
        return this.dataManager;
    }

    /**
     * to set dataManager
     *
     * @param dataManager
     *            IAppModelDataManagerEx
     */
    public void setDataManager(CostTypeModelDataManager dataManager) {
        this.dataManager = dataManager;
    }
}
