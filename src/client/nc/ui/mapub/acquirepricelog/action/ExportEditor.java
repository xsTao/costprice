/**
 *
 */
package nc.ui.mapub.acquirepricelog.action;

import java.util.List;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.trade.excelimport.InputItem;
import nc.ui.uif2.excelimport.DefaultUIF2ImportableEditor;
import nc.vo.uif2.LoginContext;

/**
 * @since v6.3
 * @version 2015年4月24日 下午1:28:14
 * @author lizhpf
 */
public class ExportEditor extends DefaultUIF2ImportableEditor {
    private UIRefPane orgPanel;

    public UIRefPane getOrgPanel() {
        return this.orgPanel;
    }

    public void setOrgPanel(UIRefPane orgPanel) {
        this.orgPanel = orgPanel;
    }

    private LoginContext context;

    public LoginContext getContext() {
        return this.context;
    }

    public void setContext(LoginContext context) {
        this.context = context;
    }

    @Override
    public List<InputItem> getInputItems() {

        return super.getInputItems();
    }

}
