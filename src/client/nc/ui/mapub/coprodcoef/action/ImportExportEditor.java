package nc.ui.mapub.coprodcoef.action;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.trade.excelimport.InputItem;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.ui.uif2.excelimport.DefaultUIF2ImportableEditor;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefHeadVO;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefItemVO;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ExtendedAggregatedValueObject;
import nc.vo.pubapp.AppContext;
import nc.vo.uif2.LoginContext;

/**
 * 1
 * 
 * @since 6.0
 * @version 2014-10-11 下午12:14:16
 * @author zhangshyb
 */
public class ImportExportEditor extends DefaultUIF2ImportableEditor {

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

    /**
     * 导出所有表头/表体/表尾
     */
    @Override
    public List<InputItem> getInputItems() {

        if (this.getBillcardPanelEditor() != null) {
            List<InputItem> result = new ArrayList<InputItem>();
            BillData bd = this.getBillcardPanelEditor().getBillCardPanel().getBillData();

            // 设置表头组织
            BillItem orgItem = bd.getHeadItem(CoprodcoefHeadVO.PK_ORG);
            orgItem.setName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC000-0001685")/*
             * @res
             * "工厂"
             */);
            orgItem.setEdit(true);
            orgItem.setShow(true);

            // orgItem = bd.getHeadItem(ProductcoefficientHeadVO.PK_ORG_V);
            // orgItem.setName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("1014364_0", "0101436401-0327")/*
            // * @res
            // * "工厂版本"
            // */);
            // orgItem.setEdit(true);
            // orgItem.setShow(true);

            // 增加表头其他属性
            result.addAll(this.getInputItemForSinglePosition(bd, IBillItem.HEAD));

            // // 增加表体组织
            // orgItem = bd.getBodyItem(ProductcoefficientItemVO.PK_ORG);
            // orgItem.setName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC000-0001685")/*
            // * @res
            // * "工厂"
            // */);
            // orgItem.setEdit(true);
            // orgItem.setShow(true);
            //
            // orgItem = bd.getBodyItem(ProductcoefficientItemVO.PK_ORG_V);
            // orgItem.setName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("1014364_0", "0101436401-0327")/*
            // * @res
            // * "工厂版本"
            // */);
            // orgItem.setEdit(true);
            // orgItem.setShow(true);

            // 增加相对系数
            orgItem = bd.getBodyItem(CoprodcoefItemVO.NRELCOEFFICIENT);
            orgItem.setEdit(true);
            orgItem.setShow(true);
            orgItem.setNull(true);

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
     * 将根据转换规则处理后的参数VO设置到界面上
     * 默认直接设置到单据模板界面，如有特殊附加需求，重写该方法。
     *
     * @param eavo
     *            根据转换规则处理后的VO
     */
    @Override
    protected void setProcessedVO(ExtendedAggregatedValueObject eavo) {
        if (this.getBillcardPanelEditor() != null) {
            this.setPkOrg4AggImport(eavo, this.getBillcardPanelEditor());
        }

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

    /**
     * 主子表单据，导入时处理组织，触发组织切换事件。
     * 要保证导入的数据中包含pk_org。
     *
     * @param eavo
     * @param editor
     */
    private void setPkOrg4AggImport(ExtendedAggregatedValueObject eavo, IBillCardPanelEditor editor) {
        editor.getBillCardPanel().addNew();
        CircularlyAccessibleValueObject sourceVO = eavo.getParentVO();// 数据来源
        String blurValue = "";// 组织版本导入的数据，编码字段。
        if (null != sourceVO.getAttributeValue("pk_org")) {// 导入的组织不为null时进行赋值
            blurValue = sourceVO.getAttributeValue("pk_org").toString();
        }
        BillData bd = editor.getBillCardPanel().getBillData();
        bd.getHeadItem("pk_group").setValue(AppContext.getInstance().getPkGroup());

        BillItem item = bd.getHeadItem("pk_org_v");// 组织版本
        UIRefPane refPane = (UIRefPane) item.getComponent();// 组织版本参照
        if (refPane.getRefModel() != null) {
            ((ShowUpableBillForm) editor).getBillOrgPanel().setPkOrg(null);
            refPane.getRefModel().clearData();// 清空参照值
            refPane.setBlurValue(blurValue);// 设置新值
            ((ShowUpableBillForm) editor).getBillOrgPanel().setPkOrg(refPane.getRefPK());
        }
    }
}
