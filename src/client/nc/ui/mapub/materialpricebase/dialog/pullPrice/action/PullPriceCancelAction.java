package nc.ui.mapub.materialpricebase.dialog.pullPrice.action;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import nc.ui.pub.beans.UIDialog;
import nc.ui.uif2.NCAction;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;

/**
 * 取价对话框
 * 取消按钮
 * 
 * @since 6.36
 * @version 2014-11-29 上午10:28:41
 * @author zhangchd
 */
public class PullPriceCancelAction extends NCAction {

    private static final long serialVersionUID = 8927615540322461112L;

    private UIDialog dialog;

    @Override
    protected boolean isActionEnable() {
        return true;
    }

    /**
     * 默认构造函数
     */
    public PullPriceCancelAction() {
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
        this.getDialog().closeCancel();
    }

    public UIDialog getDialog() {
        return this.dialog;
    }

    public void setDialog(UIDialog dialog) {
        this.dialog = dialog;
    }

}
