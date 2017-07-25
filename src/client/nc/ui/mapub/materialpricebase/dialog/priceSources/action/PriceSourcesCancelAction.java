package nc.ui.mapub.materialpricebase.dialog.priceSources.action;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import nc.ui.uif2.FuncletDialog;
import nc.ui.uif2.NCAction;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;

public class PriceSourcesCancelAction extends NCAction {

    private static final long serialVersionUID = 8927615540322461112L;

    private FuncletDialog dialog;

    @Override
    protected boolean isActionEnable() {
        return true;
    }

    /**
     * 默认构造函数
     */
    public PriceSourcesCancelAction() {
        // this.setBtnName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("50010006_0", "050010006-0200")
        // /* @res "取消" */);
        this.setBtnName(CMMLangConstMaterialPriceBase.getMSG7());
        this.setCode("CLOSE_CHILD_WINDOWS");

        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.ALT_MASK));
        this.putValue(Action.SHORT_DESCRIPTION, CMMLangConstMaterialPriceBase.getMSG7());
        // "取消(ALT+C)"
    }

    @Override
    public void doAction(ActionEvent e) throws Exception {

        // 关闭对话框
        this.getDialog().closeOK();
    }

    public FuncletDialog getDialog() {
        return this.dialog;
    }

    public void setDialog(FuncletDialog dialog) {
        this.dialog = dialog;
    }

}
