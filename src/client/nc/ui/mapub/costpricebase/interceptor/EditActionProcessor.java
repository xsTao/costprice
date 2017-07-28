/**
 *
 */
package nc.ui.mapub.costpricebase.interceptor;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.uif2.actions.ActionInterceptor;

/**
 * @since v6.3
 * @version 2017年7月28日 下午3:41:45
 * @author Administrator
 */
public class EditActionProcessor implements ActionInterceptor {

    /*
     * (non-Javadoc)
     * @see nc.ui.uif2.actions.ActionInterceptor#beforeDoAction(javax.swing.Action, java.awt.event.ActionEvent)
     */
    @Override
    public boolean beforeDoAction(Action action, ActionEvent e) {
        // TODO Auto-generated method stub
        return true;
    }

    /*
     * (non-Javadoc)
     * @see nc.ui.uif2.actions.ActionInterceptor#afterDoActionSuccessed(javax.swing.Action, java.awt.event.ActionEvent)
     */
    @Override
    public void afterDoActionSuccessed(Action action, ActionEvent e) {
        // TODO Auto-generated method stub

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
