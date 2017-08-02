/**
 *
 */
package nc.ui.mapub.costpricebase.interceptor;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.bd.framework.base.CMValueCheck;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.actions.ActionInterceptor;
import nc.vo.mapub.costpricebase.entity.CostPriceHeadVO;

/**
 * @since v6.3
 * @version 2017��7��28�� ����3:41:45
 * @author Administrator
 */
public class EditActionProcessor implements ActionInterceptor {

    private BillManageModel model;

    private BillForm editor;

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
    @SuppressWarnings("null")
    @Override
    public void afterDoActionSuccessed(Action action, ActionEvent e) {
        // TODO Auto-generated method stub
        BillCardPanel billCardPanel = ((ShowUpableBillForm) this.getEditor()).getBillCardPanel();
        BillItem annual = billCardPanel.getHeadItem(CostPriceHeadVO.ANNUAL);
        BillItem period = billCardPanel.getHeadItem(CostPriceHeadVO.VPERIOD);
        if (null != billCardPanel) {
            Object annualObj = billCardPanel.getHeadItem(CostPriceHeadVO.ANNUAL).getValueObject();
            billCardPanel.setBillData(billCardPanel.getBillData());
            // ���û���ڼ����ȵĿɱ༭

            if (CMValueCheck.isNotEmpty(annualObj)) {
                annual.setEdit(true);
            }
            else {
                period.setEdit(true);
            }

        }
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

    /**
     * ��� editor ������ֵ
     *
     * @return the editor
     * @since 2017��8��2��
     * @author Administrator
     */
    public BillForm getEditor() {
        return this.editor;
    }

    /**
     * ���� editor ������ֵ
     *
     * @param editor the editor to set
     * @since 2017��8��2��
     * @author Administrator
     */
    public void setEditor(BillForm editor) {
        this.editor = editor;
    }

    /**
     * ��� model ������ֵ
     *
     * @return the model
     * @since 2017��8��2��
     * @author Administrator
     */
    public BillManageModel getModel() {
        return this.model;
    }

    /**
     * ���� model ������ֵ
     *
     * @param model the model to set
     * @since 2017��8��2��
     * @author Administrator
     */
    public void setModel(BillManageModel model) {
        this.model = model;
    }

}
