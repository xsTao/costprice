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
 * @version 2017年7月28日 下午3:41:45
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
    @Override
    public void afterDoActionSuccessed(Action action, ActionEvent e) {
        // TODO Auto-generated method stub
        BillCardPanel billCardPanel = ((ShowUpableBillForm) this.getEditor()).getBillCardPanel();

        if (null != billCardPanel) {
            BillItem annual = billCardPanel.getHeadItem(CostPriceHeadVO.ANNUAL);
            BillItem period = billCardPanel.getHeadItem(CostPriceHeadVO.VPERIOD);
            Object annualObj = annual.getValueObject();
            Object periodObj = period.getValueObject();
            billCardPanel.setBillData(billCardPanel.getBillData());
            // 设置会计期间和年度的可编辑
            // 年度不为空，会计为空
            if (CMValueCheck.isNotEmpty(annualObj) && CMValueCheck.isEmpty(periodObj)) {
                annual.setEdit(true);
                period.setEdit(false);

            }
            // 年度为空，会计不为空
            else if (CMValueCheck.isEmpty(annualObj) && CMValueCheck.isNotEmpty(periodObj)) {
                annual.setEdit(false);
                period.setEdit(true);
            }
            // 年度为空，会计也为空or 年度不为空，会计也不为空
            else {
                annual.setEdit(true);
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
     * 获得 editor 的属性值
     *
     * @return the editor
     * @since 2017年8月2日
     * @author Administrator
     */
    public BillForm getEditor() {
        return this.editor;
    }

    /**
     * 设置 editor 的属性值
     *
     * @param editor the editor to set
     * @since 2017年8月2日
     * @author Administrator
     */
    public void setEditor(BillForm editor) {
        this.editor = editor;
    }

    /**
     * 获得 model 的属性值
     *
     * @return the model
     * @since 2017年8月2日
     * @author Administrator
     */
    public BillManageModel getModel() {
        return this.model;
    }

    /**
     * 设置 model 的属性值
     *
     * @param model the model to set
     * @since 2017年8月2日
     * @author Administrator
     */
    public void setModel(BillManageModel model) {
        this.model = model;
    }

}
