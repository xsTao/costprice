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
 * ��������˲������κ����˲��ͺ���Ҫ����װ��Dialog
 * 
 * @since v6.5
 * @version 2014��6��12�� ����2:48:01
 * @author shangzhm1
 */
public class DriverOrgFactorDialog extends UIDialog implements ActionListener {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * ȡ����ť
     */
    private JButton cancelBtn;

    /**
     * ȷ����ť
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

        // ���ӡ�ȷ��������ȡ������ť
        btnPanel.add(this.getOkBtn());
        btnPanel.add(this.getCancelBtn());

        this.getOkBtn().addActionListener(this);
        this.getCancelBtn().addActionListener(this);
        return btnPanel;
    }

    /**
     * ��ȡȷ����ť
     * 
     * @return ��ťʵ��
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
     * ��ȡȡ����ť
     * 
     * @return ʵ��
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
