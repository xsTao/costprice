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
 * @version 2017��7��19�� ����4:03:00
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
        // ���������л�ȡֵ
        String pk_org = e.getContext().getPk_org();
        String pk_group = e.getContext().getPk_group();
        Map<String, String> orgOidToVidMap = BDAdapter.getNewVIDSByOrgIDS(new String[] {
                pk_org
        });
        // ��ÿ�Ƭ���
        BillCardPanel panel = e.getBillForm().getBillCardPanel();
        // ���ü��ź͹�������֯����汾
        panel.setHeadItem(CostPriceHeadVO.PK_GROUP, pk_group);
        panel.setHeadItem(CostPriceHeadVO.PK_ORG, pk_org);
        if (CMMapUtil.isNotEmpty(orgOidToVidMap)) {
            panel.setHeadItem(CostPriceHeadVO.PK_ORG_V, orgOidToVidMap.get(pk_org));
        }
        // ��������Ĭ��ֵ
        this.setDefaultDate(panel);

    }

    /**
     * �������ڵ�Ĭ��ֵ
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
     * ��� billFormEditor ������ֵ
     *
     * @return the billFormEditor
     * @since 2017��7��25��
     * @author Administrator
     */
    public BillForm getBillFormEditor() {
        return this.billFormEditor;
    }

    /**
     * ���� billFormEditor ������ֵ
     *
     * @param billFormEditor the billFormEditor to set
     * @since 2017��7��25��
     * @author Administrator
     */
    public void setBillFormEditor(BillForm billFormEditor) {
        this.billFormEditor = billFormEditor;
    }

}
