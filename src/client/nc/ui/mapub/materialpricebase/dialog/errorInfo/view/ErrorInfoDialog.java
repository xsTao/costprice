package nc.ui.mapub.materialpricebase.dialog.errorInfo.view;

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
import nc.ui.uif2.FuncletDialog;
import nc.ui.uif2.UIFMenuFactory;
import nc.ui.uif2.model.AbstractBatchAppModel;
import nc.vo.bd.pub.NODE_TYPE;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;

/**
 * 取价错误信息对话框
 * 
 * @since 6.36
 * @version 2014-12-12 上午9:38:34
 * @author zhangchd
 */
public class ErrorInfoDialog extends FuncletDialog {

    private static final long serialVersionUID = -6022596124593102744L;

    private AbstractBatchAppModel model;

    private List<Action> bottomActions;

    /**
     * 内Border
     */
    private Border BORDER_IN_CENTERPNL = new EmptyBorder(0, 0, 1, 0);

    /**
     * 外Border
     */
    private Border BORDER_OUT_CENTERPNL = new MatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY);

    public ErrorInfoDialog() {
        super(ClientToolKit.getApplet());
    }

    @Override
    public void initUI() {
        this.setMenufactory(new UIFMenuFactory(this.getModel().getContext()));
        NODE_TYPE nodeType = this.getModel().getContext().getNodeType();
        this.setXmlFilePath("nc/ui/mapub/materialpricebase/dialog/errorInfo/config/errorInfo_config.xml");

        this.getModel().getContext().setNodeType(nodeType);
        this.getModel().getContext().setFuncInfo(this.getModel().getContext().getFuncInfo());

        super.initUI();
        this.setTitle(CMMLangConstMaterialPriceBase.getMSG2()); // 取价错误信息

        this.setSize(ClientToolKit.getUserWidth() / 2 + 400, ClientToolKit.getUserHeight() / 2 + 100);

        this.getBottomPanel().setBorder(new CompoundBorder(this.BORDER_OUT_CENTERPNL, this.BORDER_IN_CENTERPNL));
    }

    @Override
    protected JPanel getBtnPanel() {
        if (this.btnPanel == null) {
            this.btnPanel = new JPanel();
            UIButton button = null;
            for (Action action : this.getBottomActions()) {
                button = new UIButton();
                button.setAction(action);
                this.btnPanel.add(button);
            }
        }
        return this.btnPanel;
    }

    public AbstractBatchAppModel getModel() {
        return this.model;
    }

    public void setModel(AbstractBatchAppModel model) {
        this.model = model;
    }

    public List<Action> getBottomActions() {
        return this.bottomActions;
    }

    public void setBottomActions(List<Action> bottomActions) {
        this.bottomActions = bottomActions;
    }
}
