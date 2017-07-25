/**
 * 
 */
package nc.ui.mapub.materialpricebase.dialog.errorInfo.action;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import nc.ui.uif2.FuncletDialog;
import nc.ui.uif2.NCAction;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;

/**
 * 错误信息对话框
 * 关闭按钮
 * 
 * @since v6.36
 * @version 2014-12-19 上午11:04:21
 * @author zhangchd
 */
public class ErrorInfoCloseAction extends NCAction {

    private static final long serialVersionUID = 6496260624174022341L;

    private FuncletDialog dialog;

    @Override
    protected boolean isActionEnable() {
        return true;
    }

    public ErrorInfoCloseAction() {
        this.setBtnName(CMMLangConstMaterialPriceBase.getMSG1()); // 关闭
        this.setCode("CLOSE_CHILD_WINDOWS");

        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.ALT_MASK));
        this.putValue(Action.SHORT_DESCRIPTION, CMMLangConstMaterialPriceBase.getMSG1());
    }

    /*
     * (non-Javadoc)
     * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
     */
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
