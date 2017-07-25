package nc.ui.mapub.costtype.action;

import java.awt.Event;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import nc.vo.mapub.costtype.entity.CMMLangConstCM0502;

/**
 * <b> 转生产BOM动作</b>
 * <p>
 * 转生产BOM动作
 * </p>
 * 创建日期:2011-5-13
 * 
 * @author:liyjf
 */
public class ToBomAction extends AbstractToOtherFuncAction {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8720434043848589018L;

    /**
     * 构造方法
     */
    public ToBomAction() {
        super();
        this.setCode("ToBom");
        this.setBtnName(CMMLangConstCM0502.GET_BTN_TOBOM());
        this.putValue(Action.SHORT_DESCRIPTION, CMMLangConstCM0502.GET_BTN_TOBOM() + "(Alt+J)");
        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke('J', Event.ALT_MASK));
    }

    @Override
    public void doAction(ActionEvent e) throws Exception {
        // 打开画面
        this.openFuncNode("10140BOMM", "0202", null);
    }
}
