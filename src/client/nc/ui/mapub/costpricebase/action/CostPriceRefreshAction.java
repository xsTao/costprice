/**
 *
 */
package nc.ui.mapub.costpricebase.action;

import nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.BatchBillTableModel;
import nc.ui.uif2.model.BillManageModel;

/**
 * @since v6.3
 * @version 2017年8月3日 下午6:58:36
 * @author Administrator
 */
public class CostPriceRefreshAction extends DefaultRefreshAction {

    private static final long serialVersionUID = 1L;
    
    @Override
    protected void showQueryInfo(){
        int size=0;
        if(this.getModel() instanceof BillManageModel){
            size = ((BillManageModel) this.getModel()).getData().size();
        }else if(this.getModel() instanceof BatchBillTableModel){
            size = ((BatchBillTableModel) this.getModel()).getRows().size();
        }
        if (size >= 0) {
            ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607apply_add_0","03607apply_add-0154")/*@res "查询成功，已查到"*/+size+nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607apply_add_0","03607apply_add-0155")/*@res "张单据"*/, this.getModel().getContext());
        }
    }

}
