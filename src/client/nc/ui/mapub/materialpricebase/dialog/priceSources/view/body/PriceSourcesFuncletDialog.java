package nc.ui.mapub.materialpricebase.dialog.priceSources.view.body;

import java.awt.Color;
import java.util.List;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import nc.sfbase.client.ClientToolKit;
import nc.ui.pub.beans.UIButton;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.FuncletDialog;
import nc.ui.uif2.UIFMenuFactory;
import nc.vo.bd.pub.NODE_TYPE;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;

/**
 * 物料价格库
 * 取价来源对话框
 * 
 * @since 6.36
 * @version 2014-11-19 上午8:55:57
 * @author zhangchd
 */
public class PriceSourcesFuncletDialog extends FuncletDialog {

    private static final long serialVersionUID = 9169173884048250961L;

    private BillManageModel model;

    private BillForm dialogBillForm;

    private List<Action> bottomActions;

    /**
     * 内Border
     */
    private Border BORDER_IN_CENTERPNL = new EmptyBorder(0, 0, 1, 0);

    /**
     * 外Border
     */
    private Border BORDER_OUT_CENTERPNL = new MatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY);

    public PriceSourcesFuncletDialog() {
        super(ClientToolKit.getApplet());
    }

    @Override
    public void initUI() {
        this.setMenufactory(new UIFMenuFactory(this.getModel().getContext()));
        NODE_TYPE nodeType = this.getModel().getContext().getNodeType();
        this.setXmlFilePath("nc/ui/mapub/materialpricebase/dialog/priceSources/config/priceSources_body_config.xml");

        this.getModel().getContext().setNodeType(nodeType);
        this.getModel().getContext().setFuncInfo(this.getModel().getContext().getFuncInfo());

        super.initUI();
        this.setTitle(CMMLangConstMaterialPriceBase.getMSG20());
        this.setSize(600, 400);

        this.getBottomPanel().setBorder(new CompoundBorder(this.BORDER_OUT_CENTERPNL, this.BORDER_IN_CENTERPNL));
        // 初始化model
        // this.getModel().initModel(null);
        //
        // if (!UIState.EDIT.equals(this.getModel().getUiState())) {
        // this.getModel().setUiState(UIState.EDIT);
        // }

    }

    @Override
    protected JPanel getBtnPanel() {
        if (this.btnPanel == null) {
            this.btnPanel = new JPanel();
            // this.btnPanel.setPreferredSize(new Dimension(150, 30));
            // ActionsBar bar = null;
            UIButton button = null;
            for (Action action : this.getBottomActions()) {
                // bar = new ActionsBar();
                // bar.setOpaque(false);
                // bar.setShowActionText(true);
                // bar.addAction(action);
                // this.btnPanel.add(bar);
                button = new UIButton();
                button.setAction(action);
                this.btnPanel.add(button);
            }

        }
        return this.btnPanel;
    }

    public BillManageModel getModel() {
        return this.model;
    }

    public void setModel(BillManageModel model) {
        this.model = model;
    }

    public BillForm getDialogBillForm() {
        return this.dialogBillForm;
    }

    public void setDialogBillForm(BillForm dialogBillForm) {
        this.dialogBillForm = dialogBillForm;
    }

    public List<Action> getBottomActions() {
        return this.bottomActions;
    }

    public void setBottomActions(List<Action> bottomActions) {
        this.bottomActions = bottomActions;
    }
}
