/**
 *
 */
package nc.ui.mapub.costpricebase.interceptor;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.actions.ActionInterceptor;
import nc.vo.mapub.costpricebase.entity.CostPriceHeadVO;

/**
 * @since v6.3
 * @version 2017年7月20日 下午1:29:53
 * @author Administrator
 */
public class AddActionProcessor implements ActionInterceptor {

    private BillManageModel model;

    private BillForm editor;

    private BillItem item;

    /*
     * (non-Javadoc)
     * @see nc.ui.uif2.actions.ActionInterceptor#afterDoActionFailed(javax.swing.Action, java.awt.event.ActionEvent,
     * java.lang.Throwable)
     */
    @Override
    public boolean afterDoActionFailed(Action action, ActionEvent event, Throwable throwable) {
        // TODO Auto-generated method stub

        return true;
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
            Object orgrefItem = billCardPanel.getHeadItem(CostPriceHeadVO.PK_ORG).getValueObject();
            System.out.println(orgrefItem);
            billCardPanel.setBillData(billCardPanel.getBillData());
        }
    }

    /*
     * (non-Javadoc)
     * @see nc.ui.uif2.actions.ActionInterceptor#beforeDoAction(javax.swing.Action, java.awt.event.ActionEvent)
     */
    @Override
    public boolean beforeDoAction(Action action, ActionEvent event) {
        // TODO Auto-generated method stub

        return true;
    }

    /**
     * 获得 model 的属性值
     *
     * @return the model
     * @since 2017年7月20日
     * @author Administrator
     */
    public BillManageModel getModel() {
        return this.model;
    }

    /**
     * 设置 model 的属性值
     *
     * @param model the model to set
     * @since 2017年7月20日
     * @author Administrator
     */
    public void setModel(BillManageModel model) {
        this.model = model;
    }

    /**
     * 获得 editor 的属性值
     *
     * @return the editor
     * @since 2017年7月20日
     * @author Administrator
     */
    public BillForm getEditor() {
        return this.editor;
    }

    /**
     * 设置 editor 的属性值
     *
     * @param editor the editor to set
     * @since 2017年7月20日
     * @author Administrator
     */
    public void setEditor(BillForm editor) {
        this.editor = editor;
    }

    /**
     * 获得 item 的属性值
     *
     * @return the item
     * @since 2017年7月20日
     * @author Administrator
     */
    public BillItem getItem() {
        return this.item;
    }

    /**
     * 设置 item 的属性值
     *
     * @param item the item to set
     * @since 2017年7月20日
     * @author Administrator
     */
    public void setItem(BillItem item) {
        this.item = item;
    }

}
