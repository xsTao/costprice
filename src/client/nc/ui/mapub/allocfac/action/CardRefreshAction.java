/**
 * 
 */
package nc.ui.mapub.allocfac.action;

import nc.bd.framework.base.CMStringUtil;
import nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction;

/**
 * @since v6.3
 * @version 2013-1-16 обнГ04:12:04
 * @author xionghuic
 */

public class CardRefreshAction extends DefaultRefreshAction {
    private static final long serialVersionUID = 292667176112521806L;

    @Override
    protected boolean isActionEnable() {
        String pk_org = this.getModel().getContext().getPk_org();
        if (CMStringUtil.isEmpty(pk_org)) {
            return false;
        }
        return true;
    }
}
