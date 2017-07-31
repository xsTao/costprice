/**
 *
 */
package nc.ui.mapub.costpricebase.handler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import nc.bd.framework.base.CMMapUtil;
import nc.cmpub.business.adapter.BDAdapter;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.ui.uif2.editor.BillForm;
import nc.vo.mapub.costpricebase.entity.CostPriceHeadVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;

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
        // 从上下文中获取值
        String pk_org = e.getContext().getPk_org();
        String pk_group = e.getContext().getPk_group();
        Map<String, String> orgOidToVidMap = BDAdapter.getNewVIDSByOrgIDS(new String[] {
                pk_org
        });
        // 获得卡片面板
        BillCardPanel panel = e.getBillForm().getBillCardPanel();
        // 设置集团和工厂（组织、多版本
        panel.setHeadItem(CostPriceHeadVO.PK_GROUP, pk_group);
        panel.setHeadItem(CostPriceHeadVO.PK_ORG, pk_org);
        if (CMMapUtil.isNotEmpty(orgOidToVidMap)) {
            panel.setHeadItem(CostPriceHeadVO.PK_ORG_V, orgOidToVidMap.get(pk_org));
        }
        // 设置日期默认值
        this.setDefaultDate(panel);

    }

    /**
     * 设置日期的默认值
     *
     * @param panel
     */
    private void setDefaultDate(BillCardPanel panel) {
        // TODO Auto-generated method stub
        UFDate localDate = AppContext.getInstance().getBusiDate();
        Calendar c = Calendar.getInstance();
        c.set(localDate.getYear(), localDate.getMonth() - 1, localDate.getDay(), 0, 0, 0);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        panel.setHeadItem(CostPriceHeadVO.CREATETIME, sdf.format(c.getTime()));

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
