/**
 *
 */
package nc.ui.mapub.costpricebase.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.ui.uif2.editor.BillForm;

/**
 * @since v6.3
 * @version 2017年7月19日 下午4:03:00
 * @author Administrator
 */
public class AddHandler implements IAppEventHandler<AddEvent> {

    private BillForm billFormEditor;

    /*
     * (non-Javadoc)
     * @see nc.ui.pubapp.uif2app.event.IAppEventHandler#handleAppEvent(nc.ui.uif2.AppEvent)
     */
    @Override
    public void handleAppEvent(AddEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     * 获得 billFormEditor 的属性值
     *
     * @return the billFormEditor
     * @since 2017年7月25日
     * @author Administrator
     */
    public BillForm getBillFormEditor() {
        return this.billFormEditor;
    }

    /**
     * 设置 billFormEditor 的属性值
     *
     * @param billFormEditor the billFormEditor to set
     * @since 2017年7月25日
     * @author Administrator
     */
    public void setBillFormEditor(BillForm billFormEditor) {
        this.billFormEditor = billFormEditor;
    }

}
