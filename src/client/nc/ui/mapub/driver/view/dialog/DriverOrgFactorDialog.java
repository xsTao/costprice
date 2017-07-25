package nc.ui.mapub.driver.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import nc.ui.pub.beans.UIDialog;
import nc.ui.uif2.DefaultExceptionHanler;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.uif2.LoginContext;

/**
 * 财务核算账簿或责任核算账簿和核算要素组装的Dialog
 * 
 * @since v6.5
 * @version 2014年6月12日 下午2:48:01
 * @author shangzhm1
 */
public class DriverOrgFactorDialog extends UIDialog implements ActionListener {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 取消按钮
     */
    private JButton cancelBtn;

    /**
     * 确定按钮
     */
    private JButton okBtn;

    private LoginContext loginContext;

    private DriverOrgFactorPanel bookPane;

    @SuppressWarnings("deprecation")
    public DriverOrgFactorDialog(LoginContext context, String displayname) {
        this.loginContext = context;
        this.bookPane = new DriverOrgFactorPanel(this.loginContext, displayname);
        this.init();
    }

    public DriverOrgFactorPanel getAccountBookPanel() {
        return this.bookPane;
    }

    private void init() {

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.getAccountBookPanel(), BorderLayout.CENTER);
        this.getContentPane().add(this.getSouthPane(), BorderLayout.SOUTH);

    }

    private JPanel getSouthPane() {
        JPanel btnPanel = new JPanel();
        btnPanel.setPreferredSize(new Dimension(400, 50));
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // 增加“确定”、“取消”按钮
        btnPanel.add(this.getOkBtn());
        btnPanel.add(this.getCancelBtn());

        this.getOkBtn().addActionListener(this);
        this.getCancelBtn().addActionListener(this);
        return btnPanel;
    }

    /**
     * 获取确定按钮
     * 
     * @return 按钮实例
     */
    protected JButton getOkBtn() {
        if (this.okBtn == null) {
            this.okBtn = new JButton();
            this.okBtn.setPreferredSize(new Dimension(70, 25));
            this.okBtn.setText(CMDriverLangConst.getCONFIRM());
            this.okBtn.setToolTipText(CMDriverLangConst.getCONFIRM());
            // this.okBtn.setMnemonic(KeyEvent.VK_Y);
        }
        return this.okBtn;
    }

    /**
     * 获取取消按钮
     * 
     * @return 实例
     */
    protected JButton getCancelBtn() {
        if (this.cancelBtn == null) {
            this.cancelBtn = new JButton();
            this.cancelBtn.setPreferredSize(new Dimension(70, 25));
            this.cancelBtn.setText(CMDriverLangConst.getCANCEL());
            this.cancelBtn.setToolTipText(CMDriverLangConst.getCANCEL());
            // this.cancelBtn.setMnemonic(KeyEvent.VK_C);
        }
        return this.cancelBtn;
    }

    /*
     * (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == this.getCancelBtn()) {
                this.closeCancel();
            }
            if (e.getSource() == this.getOkBtn()) {
                this.closeOK();
            }
        }
        catch (Exception e1) {
            new DefaultExceptionHanler().handlerExeption(e1);
        }

    }
}
