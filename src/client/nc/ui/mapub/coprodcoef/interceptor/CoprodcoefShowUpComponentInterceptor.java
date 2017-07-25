/**
 *
 */
package nc.ui.mapub.coprodcoef.interceptor;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.bd.framework.base.CMValueCheck;
import nc.ui.mapub.coprodcoef.view.CoprodcoefBillForm;
import nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefHeadVO;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefItemVO;

/**
 * @since v6.3
 * @version 2015年5月15日 下午4:13:15
 * @author zhangshyb
 */
public class CoprodcoefShowUpComponentInterceptor extends ShowUpComponentInterceptor {
    @Override
    public void afterDoActionSuccessed(Action action, ActionEvent e) {

        if (this.getShowUpComponent().getClass().isAssignableFrom(CoprodcoefBillForm.class)) {
            CoprodcoefBillForm billForm = (CoprodcoefBillForm) this.getShowUpComponent();
            if (CMValueCheck.isEmpty(billForm.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.PK_ELEMENTSYSTEM)
                    .getValueObject())) {
                billForm.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.PK_FACTORCHART).setEdit(false);
            }
            else {
                billForm.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.PK_FACTORCHART).setEdit(true);
            }
            if (CMValueCheck.isEmpty(billForm.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.PK_FACTORCHART)
                    .getValueObject())) {

                billForm.getBillCardPanel().getBodyItem(CoprodcoefItemVO.CELEMENTID).setEdit(false);
            }
            else {
                billForm.getBillCardPanel().getBodyItem(CoprodcoefItemVO.CELEMENTID).setEdit(true);
            }
        }

        this.getShowUpComponent().showMeUp();
    }

}
