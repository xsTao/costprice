package nc.ui.mapub.allocfac.action;

import java.util.ArrayList;
import java.util.List;

import nc.bd.framework.base.CMStringUtil;
import nc.bs.framework.common.NCLocator;
import nc.cmpub.framework.assistant.CMAssInfoUtil;
import nc.itf.org.IOrgUnitQryService;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.view.BaseOrgPanel;
import nc.ui.pubapp.uif2app.view.util.BillPanelUtils;
import nc.ui.trade.excelimport.InputItem;
import nc.ui.uif2.excelimport.DefaultUIF2ImportableEditor;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ExtendedAggregatedValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.trade.excelimport.processor.IVOProcessor;
import nc.vo.trade.excelimport.processor.VOProcessor;

/**
 * ����ϵ�����뵼��
 */

public class ImportExportEditor extends DefaultUIF2ImportableEditor {
    public BaseOrgPanel orgPanel;

    public BaseOrgPanel getOrgPanel() {
        return this.orgPanel;
    }

    public void setOrgPanel(BaseOrgPanel orgPanel) {
        this.orgPanel = orgPanel;
    }

    @Override
    public List<InputItem> getInputItems() {

        if (this.getBillcardPanelEditor() != null) {
            List<InputItem> result = new ArrayList<InputItem>();
            BillData bd = this.getBillcardPanelEditor().getBillCardPanel().getBillData();
            // ���ñ�ͷ��֯
            BillItem orgItem = bd.getHeadItem(AllocfacHeadVO.PK_ORG);
            orgItem.setName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC000-000003")/*
             * @res
             * "ҵ��Ԫ"
             */);
            orgItem.setEdit(true);
            orgItem.setShow(true);
            orgItem.setNull(true);

            // ���ӱ�ͷ��������
            result.addAll(this.getInputItemForSinglePosition(bd, IBillItem.HEAD));

            // ����ɱ�����
            BillItem costcenterItem = bd.getBodyItem(AllocfacItemVO.CCOSTCENTERID);
            costcenterItem.setEdit(true);
            costcenterItem.setShow(true);
            costcenterItem.setNull(false);

            // �����Ʒ
            BillItem cmaterialvidItem = bd.getBodyItem(AllocfacItemVO.CMATERIALID);
            cmaterialvidItem.setEdit(true);
            cmaterialvidItem.setShow(true);
            cmaterialvidItem.setNull(false);
            // ����ɱ�����
            BillItem cmarcostclassidItem = bd.getBodyItem(AllocfacItemVO.CMARCOSTCLASSID);
            cmarcostclassidItem.setEdit(true);
            cmarcostclassidItem.setShow(true);
            cmarcostclassidItem.setNull(false);
            // �������õ����ɸ�������
            this.setShowAssItems(bd);
            // ������ҵ
            BillItem cactiviryidItem = bd.getBodyItem(AllocfacItemVO.CACTIVITYID);
            cactiviryidItem.setEdit(true);
            cactiviryidItem.setShow(true);
            cactiviryidItem.setNull(false);
            // �������
            BillItem stuffItem = bd.getBodyItem(AllocfacItemVO.CSTUFFID);
            stuffItem.setEdit(true);
            stuffItem.setShow(true);
            stuffItem.setNull(false);
            // �����������
            BillItem cmarbaseclassidItem = bd.getBodyItem(AllocfacItemVO.CMARBASECLASSID);
            cmarbaseclassidItem.setEdit(true);
            cmarbaseclassidItem.setShow(true);
            cmarbaseclassidItem.setNull(false);

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
     * �������õ����ɸ������ֶΰ��������Խṹ����󼯺���ʾ
     *
     * @param bd
     * @author shangzhm1
     */
    private void setShowAssItems(BillData bd) {
        List<String> showAssInfoCodes = new CMAssInfoUtil().getShowAssInfoCode();
        if (showAssInfoCodes != null && showAssInfoCodes.size() > 0) {
            for (String showAssInfoCode : showAssInfoCodes) {
                BillItem freeItem = bd.getBodyItem(showAssInfoCode);
                freeItem.setEdit(true);
                freeItem.setShow(true);
                freeItem.setNull(false);
            }
        }

    }

    /**
     * ������ת���������Ĳ���VO���õ�������
     * Ĭ��ֱ�����õ�����ģ����棬�������⸽��������д�÷�����
     */
    @Override
    protected void setProcessedVO(ExtendedAggregatedValueObject eavo) {
        if (this.getBillcardPanelEditor() != null) {
            // ͨ�����պ͵����orgCodeȡ��pk_org
            String orgCode = eavo.getParentVO().getAttributeValue(AllocfacHeadVO.PK_ORG).toString();
            BillData bd = this.getBillcardPanelEditor().getBillCardPanel().getBillData();
            BillItem orgItem = bd.getBodyItem(AllocfacItemVO.PK_ORG);
            ((UIRefPane) orgItem.getComponent()).setBlurValue(orgCode);
            String pk_org = ((UIRefPane) orgItem.getComponent()).getRefPK();
            this.getOrgPanel().getModel().getContext().setPk_org(pk_org);
            ((UIRefPane) this.getOrgPanel().getComponent(1)).setPK(pk_org);

            if (CMStringUtil.isNotEmpty(pk_org)) {
                String pkGroup = "";
                try {
                    pkGroup = NCLocator.getInstance().lookup(IOrgUnitQryService.class).getOrg(pk_org).getPk_group();
                }
                catch (BusinessException e) {
                    ExceptionUtils.wrappException(e);
                }
                BillItem groupItem = bd.getHeadItem(AllocfacItemVO.PK_GROUP);
                ((UIRefPane) groupItem.getComponent()).setPK(pkGroup);
            }
            BillPanelUtils.setOrgForAllRef(this.getBillcardPanelEditor().getBillCardPanel(), this.getOrgPanel()
                    .getModel().getContext());
        }
        // �ٴ���䣬��������������֯�л���յĲ���ֵ
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

        @SuppressWarnings("boxing")
        @Override
        public Integer getOrder() {
            return this.item.getReadOrder();
        }

        @SuppressWarnings("boxing")
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

    @Override
    protected IVOProcessor createVOProcessor() {
        return new VOProcessor(new AllocfacTypeVOConversionsFactory());
    }

}
