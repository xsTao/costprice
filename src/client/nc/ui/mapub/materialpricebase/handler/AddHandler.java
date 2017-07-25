package nc.ui.mapub.materialpricebase.handler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import nc.bd.framework.base.CMMapUtil;
import nc.bd.framework.base.CMValueCheck;
import nc.bs.framework.common.NCLocator;
import nc.cmpub.business.adapter.BDAdapter;
import nc.itf.org.IOrgUnitQryService;
import nc.pubitf.org.cache.IAccountingBookPubService_C;
import nc.pubitf.org.cache.ILiabilityBookPubService_C;
import nc.pubitf.uapbd.CurrencyRateUtilHelper;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.ui.uif2.editor.BillForm;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ���ϼ۸��
 * �����¼�
 *
 * @since 6.36
 * @version 2014-11-7 ����3:57:19
 * @author zhangchd
 */
public class AddHandler implements IAppEventHandler<AddEvent> {

    private BillForm billFormEditor;

    private IAccountingBookPubService_C iAccountingBookPubService_C;

    private ILiabilityBookPubService_C iLiabilityBookPubService_C;

    @Override
    public void handleAppEvent(AddEvent e) {
        String pk_group = e.getContext().getPk_group();
        String pk_org = e.getContext().getPk_org();
        Map<String, String> orgOidToVidMap = BDAdapter.getNewVIDSByOrgIDS(new String[] {
            pk_org
        });
        BillCardPanel panel = e.getBillForm().getBillCardPanel();
        // ��������֯Ĭ��ֵ
        panel.setHeadItem(MaterialPriceBaseHeadVO.PK_GROUP, pk_group);
        panel.setHeadItem(MaterialPriceBaseHeadVO.PK_ORG, pk_org);
        if (CMMapUtil.isNotEmpty(orgOidToVidMap)) {
            panel.setHeadItem(MaterialPriceBaseHeadVO.PK_ORG_V, orgOidToVidMap.get(pk_org));
        }

        // ���ñ���Ϊ��֯����
        this.setCcurrencyid(pk_org);

        // ��������Ĭ��ֵ
        this.setDefaultDates(panel);

        // �����ҳǩ�Զ���������
        // this.dealAutoLine(panel);

    }

    // �����ҳǩ�Զ���������
    // private void dealAutoLine(BillCardPanel panel) {
    // String[] bodyTabcodes = panel.getBillData().getBodyBaseTableCodes();
    // if (bodyTabcodes == null || bodyTabcodes.length == 0) {
    // return;
    // }
    // for (String tabcode : bodyTabcodes) {
    // panel.setBodyAutoAddLine(tabcode, true);
    // }
    // }

    /**
     * ��������Ĭ��ֵ
     *
     * @param panel
     */
    private void setDefaultDates(BillCardPanel panel) {
        // ��ȡ��ǰ��½ʱ��
        UFDate loginDate = AppContext.getInstance().getBusiDate();

        Calendar c = Calendar.getInstance();
        c.set(loginDate.getYear(), loginDate.getMonth() - 1, loginDate.getDay(), 0, 0, 0);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // ������Ч����
        panel.setHeadItem(MaterialPriceBaseHeadVO.DBEGINDATE, df.format(c.getTime()));

        c.set(9999, 11, 31, 23, 59, 59);
        // ����ʧЧ����
        panel.setHeadItem(MaterialPriceBaseHeadVO.DENDDATE, df.format(c.getTime()));
    }

    /**
     * ���ñ���Ϊ��֯����
     *
     * @param pk_org
     */
    private void setCcurrencyid(String pk_org) {
        if (pk_org == null) {
            this.getBillFormEditor().getBillCardPanel().setHeadItem(MaterialPriceBaseHeadVO.CCRRENCYID, null);
            return;
        }
        OrgVO orgVO = null; // ����ҵ��ԪVO
        Map<String, String> pkMainAccountBookMap = null; // key: ������֯ID value: ���˲�ID
        String pk_ccurrencyid = null; // ��������˲���λ��
        try {
            orgVO = NCLocator.getInstance().lookup(IOrgUnitQryService.class).getOrg(pk_org);
            CurrencyRateUtilHelper currencyHelper = CurrencyRateUtilHelper.getInstance();

            if (UFBoolean.TRUE.equals(orgVO.getAttributeValue("orgtype5"))) { // ������֯
                pkMainAccountBookMap =
                        this.getIAccountingBookPubService_C().queryAccountingBookIDByFinanceOrgIDWithMainAccountBook(
                                new String[] {
                                    pk_org
                                });
                if (pkMainAccountBookMap == null || pkMainAccountBookMap.size() == 0
                        || pkMainAccountBookMap.get(pk_org) == null) {
                    ExceptionUtils.wrappBusinessException(CMMLangConstMaterialPriceBase.getMSG31());
                }

                // ȡ��������˲���λ��
                pk_ccurrencyid = currencyHelper.getLocalCurrtypeByAccountingbookID(pkMainAccountBookMap.get(pk_org));

            }
            else if (UFBoolean.TRUE.equals(orgVO.getAttributeValue("orgtype33"))) { // ����
                String pk_bookString = BDAdapter.getMainAccountBookByStockOrgId(pk_org);
                if (CMValueCheck.isEmpty(pk_bookString)) {
                    ExceptionUtils.wrappBusinessException(CMMLangConstMaterialPriceBase.GET_HIT_NOSCHEME());
                }

                // ȡ��������˲���λ��
                pk_ccurrencyid = currencyHelper.getLocalCurrtypeByAccountingbookID(pk_bookString);

            }
            else if (UFBoolean.TRUE.equals(orgVO.getAttributeValue("orgtype15"))) { // ��������
                String pk_bookString =
                        this.getILiabilityBookPubService_C().queryLiabilityBookIDByLiaCenterIDWithMainLiabilityBook(
                                pk_org);
                if (CMValueCheck.isEmpty(pk_bookString)) {
                    ExceptionUtils.wrappBusinessException(CMMLangConstMaterialPriceBase.getMSG32());
                }

                // ȡ���κ����˲���λ��
                pk_ccurrencyid = currencyHelper.getLocalCurrtypeByLiabilitybookID(pk_bookString);

            }
            else {
                ExceptionUtils.wrappBusinessException(CMMLangConstMaterialPriceBase.getMSG33());
            }
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }

        this.getBillFormEditor().getBillCardPanel().setHeadItem(MaterialPriceBaseHeadVO.CCRRENCYID, pk_ccurrencyid);
    }

    public BillForm getBillFormEditor() {
        return this.billFormEditor;
    }

    public void setBillFormEditor(BillForm billFormEditor) {
        this.billFormEditor = billFormEditor;
    }

    private IAccountingBookPubService_C getIAccountingBookPubService_C() {
        if (this.iAccountingBookPubService_C == null) {
            this.iAccountingBookPubService_C = NCLocator.getInstance().lookup(IAccountingBookPubService_C.class);
        }
        return this.iAccountingBookPubService_C;
    }

    private ILiabilityBookPubService_C getILiabilityBookPubService_C() {
        if (this.iLiabilityBookPubService_C == null) {
            this.iLiabilityBookPubService_C = NCLocator.getInstance().lookup(ILiabilityBookPubService_C.class);
        }
        return this.iLiabilityBookPubService_C;
    }

}
