package nc.ui.mapub.materialpricebase.action;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.materialpricebase.IMaterialPriceBaseMaintainService;
import nc.ui.cmpub.business.util.CMImportExportUtil;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.trade.excelimport.InputItem;
import nc.ui.uif2.UIState;
import nc.ui.uif2.excelimport.DefaultUIF2ImportableEditor;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ExtendedAggregatedValueObject;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.uif2.LoginContext;

/**
 * ���ϼ۸��
 * ���뵼��
 *
 * @since 6.36
 * @version 2014-11-7 ����3:44:33
 * @author zhangshyb ����˧�������ĵ��뵼�������뵼���Ĵ���������Ŵ�����д��
 */
public class ImportExportEditor extends DefaultUIF2ImportableEditor {
    // private IMaterialPriceBaseMaintainService materialPriceBaseMaintain;

    private LoginContext context;

    public LoginContext getContext() {
        return this.context;
    }

    public void setContext(LoginContext context) {
        this.context = context;
    }

    private IMaterialPriceBaseMaintainService materialPriceBaseMaintain;

    /**
     * ��ȡ�۸��ķ���
     *
     * @return IMaterialPriceBaseMaintainService
     */
    private IMaterialPriceBaseMaintainService getMaterialPriceBaseMaintainService() {
        if (this.materialPriceBaseMaintain == null) {
            this.materialPriceBaseMaintain = NCLocator.getInstance().lookup(IMaterialPriceBaseMaintainService.class);
        }
        return this.materialPriceBaseMaintain;
    }

    @Override
    public List<InputItem> getInputItems() {

        if (this.getBillcardPanelEditor() != null) {
            List<InputItem> result = new ArrayList<InputItem>();
            BillData bd = this.getBillcardPanelEditor().getBillCardPanel().getBillData();
            // ���ñ�ͷ��֯
            BillItem orgItem = bd.getHeadItem(MaterialPriceBaseHeadVO.PK_ORG);
            orgItem.setName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC000-000003")/*
             * @res
             * "ҵ��Ԫ"
             */);
            orgItem.setEdit(true);
            orgItem.setShow(true);
            orgItem.setNull(true);

            // ���ӱ�����������
            result.addAll(this.getInputItemForSinglePosition(bd, IBillItem.HEAD));

            // ���ӱ�����������
            result.addAll(this.getInputItemForSinglePosition(bd, IBillItem.BODY));

            // ���ӱ�β����
            result.addAll(this.getInputItemForSinglePosition(bd, IBillItem.TAIL));

            return result;
        }
        return null;
    }

    private List<InputItem> getInputItemForSinglePosition(BillData bd, int pos) {
        List<InputItem> result = new ArrayList<InputItem>();
        String[] tabcodes = bd.getTableCodes(pos);
        for (int tab = 0; tab < (tabcodes == null ? 0 : tabcodes.length); tab++) {
            BillItem[] items = bd.getShowItems(pos, tabcodes[tab]);
            String tabname = bd.getTableName(pos, tabcodes[tab]);
            result.addAll(this.billItems2InputItems(items, tabname));
        }
        return result;
    }

    private List<InputItem> billItems2InputItems(BillItem[] items, String tabname) {
        List<InputItem> result = new ArrayList<InputItem>();
        for (int i = 0; i < (items == null ? 0 : items.length); i++) {
            BillItem item = items[i];
            item.setTableName(tabname);
            if (this.isImportable(item)) {
                result.add(new BillItemValue(item));
            }
        }
        return result;
    }

    private boolean isImportable(BillItem item) {
        return item.getDataType() != IBillItem.IMAGE && item.getDataType() != IBillItem.BLANK
                && item.getDataType() != IBillItem.OBJECT;
    }

    /**
     * ������ת���������Ĳ���VO���õ�������
     * Ĭ��ֱ�����õ�����ģ����棬�������⸽��������д�÷�����
     *
     * @param eavo
     *            ����ת����������VO
     * @author modified by shangzhm1 at 2012-7-11
     */
    @Override
    protected void setProcessedVO(ExtendedAggregatedValueObject eavo) {
        CMImportExportUtil.setPkOrg4AggImport(eavo, this.getBillcardPanelEditor());
        super.setProcessedVO(eavo);

    }

    static class BillItemValue implements InputItem {

        private BillItem item = null;

        private Boolean isNotNull = null;

        public BillItemValue(BillItem item) {
            super();
            this.item = item;
        }

        @Override
        public String getItemKey() {

            return this.item.getKey();
        }

        @Override
        public Integer getOrder() {
            return this.item.getReadOrder();
        }

        @Override
        public Integer getPos() {
            return this.item.getPos();
        }

        @Override
        public String getShowName() {
            return this.item.getName();
        }

        @Override
        public String getTabCode() {
            return this.item.getTableCode();
        }

        @Override
        public String getTabName() {
            return this.item.getTableName();
        }

        @Override
        public boolean isEdit() {
            return this.item.isEdit();
        }

        @Override
        public boolean isNotNull() {
            if (this.isNotNull == null) {
                return this.item.isNull();
            }
            return this.isNotNull.booleanValue();
        }

        public void setIsNotNull(boolean isNotNull) {
            this.isNotNull = new Boolean(isNotNull);
        }

        @Override
        public boolean isShow() {
            return this.item.isShow();
        }

        @Override
        public boolean isMultiLang() {
            return this.item.getDataType() == IBillItem.MULTILANGTEXT;
        }

    }

    /**
     * �������
     */
    @Override
    public void save() throws Exception {
        // ȡ����vo����
        IBill[] clientVOs = new IBill[] {
            (IBill) this.getBillcardPanelEditor().getValue()
        };
        ClientBillToServer<IBill> tool = new ClientBillToServer<IBill>();
        // ȡ�ò���VO�����Ѳ���vo������̨
        IBill[] lightVOs = tool.constructInsert(clientVOs);
        IBill[] afterUpdateVOs = null;
        if (this.getMaterialPriceBaseMaintainService() == null) {
            throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                    "0101436401-0362")/*
                     * Service����Ϊ�գ�
                     */);
        }
        afterUpdateVOs = this.getMaterialPriceBaseMaintainService().importInsert((MaterialPriceBaseAggVO[]) lightVOs);
        // clientVOsΪ�����ϵ����ݣ�afterUpdateVOsΪ��̨���صĲ������ݣ�ȡȫvo����
        new ClientBillCombinServer<IBill>().combine(clientVOs, afterUpdateVOs);
        // ���µ������ݼ���model��
        BillManageModel model = (BillManageModel) this.getAppModel();
        model.directlyAdd(clientVOs[0]);
        this.getAppModel().setUiState(UIState.NOT_EDIT);
    }

}
