package nc.ui.mapub.costtype.action;

import nc.ui.uif2.actions.batch.BatchDelLineAction;
import nc.vo.util.ManageModeUtil;

public class CostTypeDelAction extends BatchDelLineAction {

    private static final long serialVersionUID = 1L;

    @Override
    protected boolean isActionEnable() {
        Object[] sels = this.getModel().getSelectedOperaDatas();
        if (sels == null || sels.length < 1) {
            return false;
        }

        boolean flag = false;
        for (Object obj : sels) {
            if (ManageModeUtil.manageable(obj, this.getModel().getContext())) {
                flag = true;
            }
        }
        // 校验数据权限
        if (!flag) {
            return false;
        }

        return true;
    }
}
