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
import nc.vo.bd.bdactivity.entity.BDActivityVO;

/**
 * @since v6.3
 * @version 2017年7月24日 下午2:56:20
 * @author Administrator
 */
public class IsActivityShowInterceptor implements ActionInterceptor {

    private IEditor editor;

    /*
     * (non-Javadoc)
     * @see nc.ui.uif2.actions.ActionInterceptor#afterDoActionFailed(javax.swing.Action, java.awt.event.ActionEvent,
     * java.lang.Throwable)
     */
    @Override
    public boolean afterDoActionFailed(Action arg0, ActionEvent arg1, Throwable arg2) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * @see nc.ui.uif2.actions.ActionInterceptor#afterDoActionSuccessed(javax.swing.Action, java.awt.event.ActionEvent)
     */
    @Override
    public void afterDoActionSuccessed(Action arg0, ActionEvent arg1) {
        // TODO Auto-generated method stub
        BillCardPanel billCardPanel = ((ShowUpableBillForm) this.getEditor()).getBillCardPanel();
        if (null != billCardPanel) {
            billCardPanel.getHeadItem(BDActivityVO.PK_ORG);
            billCardPanel.setBillData(billCardPanel.getBillData());
        }
    }

    /*
     * (non-Javadoc)
     * @see nc.ui.uif2.actions.ActionInterceptor#beforeDoAction(javax.swing.Action, java.awt.event.ActionEvent)
     */
    @Override
    public boolean beforeDoAction(Action arg0, ActionEvent arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * 获得 editor 的属性值
     *
     * @return the editor
     * @since 2017年7月24日
     * @author Administrator
     */
    public IEditor getEditor() {
        return this.editor;
    }

    /**
     * 设置 editor 的属性值
     *
     * @param editor the editor to set
     * @since 2017年7月24日
     * @author Administrator
     */
    public void setEditor(IEditor editor) {
        this.editor = editor;
    }

}
