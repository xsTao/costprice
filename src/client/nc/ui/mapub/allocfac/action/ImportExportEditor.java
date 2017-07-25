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
 * 分配系数导入导出
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
            // 设置表头组织
            BillItem orgItem = bd.getHeadItem(AllocfacHeadVO.PK_ORG);
            orgItem.setName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC000-000003")/*
             * @res
             * "业务单元"
             */);
            orgItem.setEdit(true);
            orgItem.setShow(true);
            orgItem.setNull(true);

            // 增加表头其他属性
            result.addAll(this.getInputItemForSinglePosition(bd, IBillItem.HEAD));

            // 表体成本中心
            BillItem costcenterItem = bd.getBodyItem(AllocfacItemVO.CCOSTCENTERID);
            costcenterItem.setEdit(true);
            costcenterItem.setShow(true);
            costcenterItem.setNull(false);

            // 表体产品
            BillItem cmaterialvidItem = bd.getBodyItem(AllocfacItemVO.CMATERIALID);
            cmaterialvidItem.setEdit(true);
            cmaterialvidItem.setShow(true);
            cmaterialvidItem.setNull(false);
            // 表体成本分类
            BillItem cmarcostclassidItem = bd.getBodyItem(AllocfacItemVO.CMARCOSTCLASSID);
            cmarcostclassidItem.setEdit(true);
            cmarcostclassidItem.setShow(true);
            cmarcostclassidItem.setNull(false);
            // 表体启用的自由辅助属性
            this.setShowAssItems(bd);
            // 表体作业
            BillItem cactiviryidItem = bd.getBodyItem(AllocfacItemVO.CACTIVITYID);
            cactiviryidItem.setEdit(true);
            cactiviryidItem.setShow(true);
            cactiviryidItem.setNull(false);
            // 表体材料
            BillItem stuffItem = bd.getBodyItem(AllocfacItemVO.CSTUFFID);
            stuffItem.setEdit(true);
            stuffItem.setShow(true);
            stuffItem.setNull(false);
            // 表体基本分类
            BillItem cmarbaseclassidItem = bd.getBodyItem(AllocfacItemVO.CMARBASECLASSID);
            cmarbaseclassidItem.setEdit(true);
            cmarbaseclassidItem.setShow(true);
            cmarbaseclassidItem.setNull(false);

            // 增加表体其他属性
            result.addAll(this.getInputItemForSinglePosition(bd, IBillItem.BODY));

            // 增加表尾属性
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
     * 设置启用的自由辅助性字段按辅助属性结构的最大集合显示
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
     * 将根据转换规则处理后的参数VO设置到界面上
     * 默认直接设置到单据模板界面，如有特殊附加需求，重写该方法。
     */
    @Override
    protected void setProcessedVO(ExtendedAggregatedValueObject eavo) {
        if (this.getBillcardPanelEditor() != null) {
            // 通过参照和导入的orgCode取得pk_org
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
        // 再次填充，重新设置由于组织切换清空的参照值
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
