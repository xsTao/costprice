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
 * Ԥ�óɱ���������༭ɾ��
 *
 * @since 6.0
 * @version 2011-8-23 ����10:17:11
 * @author chengjieb
 */
public class UpdateInteceptor implements ActionInterceptor {

    /**
     * BillManageModel
     */
    private BillManageModel model;

    @Override
    public boolean beforeDoAction(Action action, ActionEvent e) {
        // ѡ��ɾ���޸�Ԥ�óɱ�����ʱ������������ɾ���޸���ʾ
        Object[] datas = this.getModel().getSelectedOperaDatas();
        DriverVO[] driverVO = new DriverVO[datas.length];
        for (int i = 0; i < datas.length; i++) {
            driverVO[i] = (DriverVO) ((DriverAggVO) datas[i]).getParent();
        }
        // ѭ���Ƚ�ѡ��ĳɱ������ϵͳԤ�óɱ������Ƿ���ͬ
        for (int i = 0; i < driverVO.length; i++) {
            if (CMDriverLangConst.GLFLAG.equals(driverVO[i].getPk_group())) {
                // �����ѡ����Ǳ༭��������ʾ�ɱ���������༭��������ʾ�ɱ���������ɾ��
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
