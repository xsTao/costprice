package nc.ui.mapub.driver.action;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.uif2.actions.ActionInterceptor;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.DriverAggVO;
import nc.vo.mapub.driver.entity.DriverVO;

/**
 * 预置成本动因不允许编辑删除
 *
 * @since 6.0
 * @version 2011-8-23 上午10:17:11
 * @author chengjieb
 */
public class UpdateInteceptor implements ActionInterceptor {

    /**
     * BillManageModel
     */
    private BillManageModel model;

    @Override
    public boolean beforeDoAction(Action action, ActionEvent e) {
        // 选择删除修改预置成本动因时，给出不允许删除修改提示
        Object[] datas = this.getModel().getSelectedOperaDatas();
        DriverVO[] driverVO = new DriverVO[datas.length];
        for (int i = 0; i < datas.length; i++) {
            driverVO[i] = (DriverVO) ((DriverAggVO) datas[i]).getParent();
        }
        // 循环比较选择的成本动因和系统预置成本动因是否相同
        for (int i = 0; i < driverVO.length; i++) {
            if (CMDriverLangConst.GLFLAG.equals(driverVO[i].getPk_group())) {
                // 如果是选择的是编辑操作，提示成本动因不允许编辑，否则提示成本动因不允许删除
                if (DriverEditAction.class.isInstance(action)) {
                    MessageDialog.showHintDlg(this.getModel().getContext().getEntranceUI(),
                            CMDriverLangConst.getHINT(), CMDriverLangConst.getSYSTEMCODE_NOT_EDIT());
                }
                else {
                    MessageDialog.showHintDlg(this.getModel().getContext().getEntranceUI(),
                            CMDriverLangConst.getHINT(), CMDriverLangConst.getSYSTEMCODE_NOT_DELETE());

                }
                return false;
            }

        }
        return true;
    }

    @Override
    public boolean afterDoActionFailed(Action action, ActionEvent e, Throwable ex) {
        return false;
    }

    @Override
    public void afterDoActionSuccessed(Action action, ActionEvent e) {

    }

    /**
     * model get method
     *
     * @return BillManageModel
     */
    public BillManageModel getModel() {
        return this.model;
    }

    /**
     * model set method
     *
     * @param model
     */
    public void setModel(BillManageModel model) {
        this.model = model;
    }

}
