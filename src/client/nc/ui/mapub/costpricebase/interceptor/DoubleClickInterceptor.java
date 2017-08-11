/**
 *
 */
package nc.ui.mapub.costpricebase.interceptor;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.actions.ActionInterceptor;
import nc.ui.uif2.editor.IEditor;

/**
 * @since v6.3
 * @version 2017��8��10�� ����9:03:36
 * @author Administrator
 */
public class DoubleClickInterceptor implements ActionInterceptor {

    private IEditor editor;

    /*
     * (non-Javadoc)
     * @see nc.ui.uif2.actions.ActionInterceptor#beforeDoAction(javax.swing.Action, java.awt.event.ActionEvent)
     */
    @Override
    public boolean beforeDoAction(Action action, ActionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * @see nc.ui.uif2.actions.ActionInterceptor#afterDoActionSuccessed(javax.swing.Action, java.awt.event.ActionEvent)
     */
    @Override
    public void afterDoActionSuccessed(Action action, ActionEvent e) {
        // TODO Auto-generated method stub
        BillCardPanel cardPanel = ((ShowUpableBillForm) this.getEditor()).getBillCardPanel();
        cardPanel.setEnabled(false);// ���ÿ�Ƭ���ɱ༭
    }

    /**
     * ��� editor ������ֵ
     *
     * @return the editor
     * @since 2017��7��24��
     * @author Administrator
     */
    public IEditor getEditor() {
        return this.editor;
    }

    /**
     * ���� editor ������ֵ
     *
     * @param editor the editor to set
     * @since 2017��7��24��
     * @author Administrator
     */
    public void setEditor(IEditor editor) {
        this.editor = editor;
    }

    /*
     * (non-Javadoc)
     * @see nc.ui.uif2.actions.ActionInterceptor#afterDoActionFailed(javax.swing.Action, java.awt.event.ActionEvent,
     * java.lang.Throwable)
     */
    @Override
    public boolean afterDoActionFailed(Action action, ActionEvent e, Throwable ex) {
        // TODO Auto-generated method stub
        return false;
    }

}
