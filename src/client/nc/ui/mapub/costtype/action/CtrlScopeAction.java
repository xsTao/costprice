package nc.ui.mapub.costtype.action;

import java.awt.Event;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import nc.ui.uif2.NCAction;
import nc.vo.mapub.costtype.entity.CMMLangConstCM0502;

/**
 * <b> ת�ܿط�Χ����</b>
 * <p>
 * ת�ܿط�Χ����
 * </p>
 * ��������:2011-5-13
 * 
 * @author:liyjf
 */
public class CtrlScopeAction extends NCAction {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8720434043848589018L;

    /**
     * ���췽��
     */
    public CtrlScopeAction() {
        super();
        this.setCode("CtrlScope");
        this.setBtnName(CMMLangConstCM0502.GET_BTN_CTRLSCOPE());
        this.putValue(Action.SHORT_DESCRIPTION, CMMLangConstCM0502.GET_BTN_CTRLSCOPE() + "(Alt+H)");
        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke('H', Event.ALT_MASK));
    }

    /**
     * ��Ҫ˵��
     * 
     * @param e
     *            ActionEvent
     * @throws Exception
     *             Exception
     * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
     */
    @Override
    public void doAction(ActionEvent e) throws Exception {

    }

}
