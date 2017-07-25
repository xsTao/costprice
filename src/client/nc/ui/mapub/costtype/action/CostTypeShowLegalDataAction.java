package nc.ui.mapub.costtype.action;

import java.awt.event.ActionEvent;

import nc.ui.mapub.costtype.model.CostTypeModelDataManager;
import nc.ui.uif2.UIFCheckBoxAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractUIAppModel;

/**
 * <b> �ɱ�������ʾʧЧAction </b>
 * <p>
 * �ɱ�������ʾʧЧAction
 * </p>
 * ��������:2010-1-22
 *
 * @author:��ΰ
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
                                                                                                            * "��ʾʧЧ"
                                                                                                            */);
        this.setCode("showLegal");
        // ����ˡ���ʾʧЧ����ť��Tips����ݼ� ��¼ 2010-04-15�޸�
        // ��ݼ��ᵼ�½�����ʾ�����⣬������ȷ�ϣ�����ȥ��
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
