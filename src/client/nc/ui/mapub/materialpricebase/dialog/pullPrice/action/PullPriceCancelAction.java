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
 * ȡ�۶Ի���
 * ȡ����ť
 * 
 * @since 6.36
 * @version 2014-11-29 ����10:28:41
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
     * Ĭ�Ϲ��캯��
     */
    public PullPriceCancelAction() {
        // this.setBtnName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("50010006_0", "050010006-0200")
        // /* @res "ȡ��" */);
        this.setBtnName(CMMLangConstMaterialPriceBase.getMSG7());
        this.setCode("CLOSE_CHILD_WINDOWS");

        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.ALT_MASK));
        this.putValue(Action.SHORT_DESCRIPTION, CMMLangConstMaterialPriceBase.getMSG7());
        // "ȡ��(ALT+C)"
    }

    @Override
    public void doAction(ActionEvent e) throws Exception {

        // �رնԻ���
        this.getDialog().closeCancel();
    }

    public UIDialog getDialog() {
        return this.dialog;
    }

    public void setDialog(UIDialog dialog) {
        this.dialog = dialog;
    }

}
