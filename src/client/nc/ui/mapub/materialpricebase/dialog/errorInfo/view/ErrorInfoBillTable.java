/**
 *
 */
package nc.ui.mapub.materialpricebase.dialog.errorInfo.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import nc.bs.logging.Logger;
import nc.md.MDBaseQueryFacade;
import nc.md.data.access.DASFacade;
import nc.md.data.access.NCObject;
import nc.md.model.IAttribute;
import nc.md.model.IBean;
import nc.md.model.IBusinessEntity;
import nc.md.model.type.IType;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.bill.BillEditListener2;
import nc.ui.pub.bill.BillItemUISet;
import nc.ui.pub.bill.BillModelCellEditableController;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pub.bill.IBillData;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pub.bill.IBillRelaSortListener;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.AppEventListener;
import nc.ui.uif2.CheckDataPermissionUtil;
import nc.ui.uif2.IShowMsgConstant;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.actions.batch.BatchAddLineAction;
import nc.ui.uif2.actions.batch.BatchCopyLineAction;
import nc.ui.uif2.actions.batch.BatchDelLineAction;
import nc.ui.uif2.actions.batch.BatchInsLineAction;
import nc.ui.uif2.actions.batch.BatchPasteLineAction;
import nc.ui.uif2.actions.batch.BatchPasteToTailAction;
import nc.ui.uif2.components.BorderLayoutPanel;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.ui.uif2.editor.TemplateContainer;
import nc.ui.uif2.editor.value.BillCardPanelBodyVOValueAdapter;
import nc.ui.uif2.editor.value.BillCardPanelMetaDataValueAdapter;
import nc.ui.uif2.editor.value.IComponentValueStrategy;
import nc.ui.uif2.model.AppEventConst;
import nc.ui.uif2.model.BatchBillTableModel;
import nc.ui.uif2.model.RowOperationInfo;
import nc.vo.bd.meta.IBDObject;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.mapub.acquirepricelog.entity.AcquirePriceLogVO;
import nc.vo.pub.BeanHelper;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.bill.BillTabVO;
import nc.vo.pub.bill.BillTempletVO;
import nc.vo.uif2.AppStatusRegisteryCallback;

/**
 * @since v6.3
 * @version 2014-12-21 下午7:43:57
 * @author zhangchd
 */
@SuppressWarnings("rawtypes")
public class ErrorInfoBillTable extends BorderLayoutPanel implements IBillCardPanelEditor, AppEventListener,
BillEditListener {

    private static final long serialVersionUID = 2610759838635731355L;

    protected BillCardPanel billCardPanel;

    private BatchBillTableModel model = null;

    /** nodekey */
    private String nodekey;

    /** 模板容器 */
    private TemplateContainer templateContainer = null;

    // 默认支持元数据的值访问策略，如果不是元数据，请注入相应的策略对象
    protected IComponentValueStrategy componentValueManager = null;

    // 如果是非元数据构造的模版，则必须注入该属性。
    private String voClassName = null;

    // 标识事件是否是由本控件引发的
    protected boolean isFromSelf = false;

    // 是否来自模型的事件处理
    protected boolean handlingModelEvent = false;

    // 注意：如果isBodyAutoAddLine设置为true的话，则必须注射addLineAction用于接管增行操作。
    private boolean bodyAutoAddLine = false;

    // 通过注射传入，用于实现对右键编辑菜单的控制和事件转发
    private BatchAddLineAction addLineAction = null; // 如果实现数据导入导出时，也必须注入此功能。

    private BatchDelLineAction delLineAction = null;

    private BatchInsLineAction insLineAction = null;

    private BatchCopyLineAction copyLineAction = null;

    private BatchPasteLineAction pasteLineAction = null;

    private BatchPasteToTailAction pasteToTailAction = null;

    // 是否允许多选
    private boolean bodyMultiSelectEnable = true;

    // 自定义项配置
    private IBillData userdefitemPreparator;

    // 模型引起的行增加操作
    private boolean isModelAddLine = false;

    // 编辑器引起的行增加操作
    private boolean isEditorAddLine = false;

    // 控件标识，内部使用属性。
    private String beanId = null;

    // 内部使用
    protected ListSelectionListener selectionListener = null;

    // 单据模板表模型的可编辑性设置，如有需要，可以注入。
    private BillModelCellEditableController billModelCellEditableController = null;

    protected BillEditListener2 editListener = new EditListener2();

    // 以下实现数据权限需要
    private String mdUpdOperateCode = null; // 元数据更新操作编码

    private String updOperateCode = null; // 资源对象更新操作编码。

    private String resourceCode = null; // 业务实体资源编码

    class BillTableModelListener implements TableModelListener {

        @Override
        public void tableChanged(TableModelEvent e) {

            // 通过自动方式增加的
            if (e.getType() == TableModelEvent.INSERT) {
                // 用于处理自动增行事件
                if (!ErrorInfoBillTable.this.isModelAddLine && ErrorInfoBillTable.this.addLineAction != null) {
                    ErrorInfoBillTable.this.isEditorAddLine = true;
                    ErrorInfoBillTable.this.getModel().setInAutoAddLineMode(true);
                    ErrorInfoBillTable.this.addLineAction.actionPerformed(null);
                    ErrorInfoBillTable.this.getModel().setInAutoAddLineMode(false);
                    ErrorInfoBillTable.this.isEditorAddLine = false;
                }
            }
        }
    }

    private class BillListSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {

            if (!e.getValueIsAdjusting() && !ErrorInfoBillTable.this.isFromSelf) {
                if (!ErrorInfoBillTable.this.isBodyMultiSelectEnable()) {
                    return;
                }

                int[] rows = ErrorInfoBillTable.this.getBillCardPanel().getBillTable().getSelectedRows();
                // 如果在处理模型事件，那么此事件的触发就是在让界面同步模型的选择,没有必要再去设置模型的选择行
                // 也就是说只有不是在处理模型事件时，才需要调用setSelectedOperaRows
                if (!ErrorInfoBillTable.this.handlingModelEvent) {
                    ErrorInfoBillTable.this.getModel().setSelectedOperaRows(rows);
                }
            }
        }
    }

    // 对于单击编辑的控件（下拉框），会先调用beforeEdit方法，然后调用bodyRowChange，如果
    // 仅在bodyRowChange中同步选择行，则会导致在beforeEdit中获取模型选择行错误。
    class EditListener2 implements BillEditListener2 {
        @Override
        public boolean beforeEdit(BillEditEvent e) {

            int index = e.getRow();
            ErrorInfoBillTable.this.synchornizeIndex(index);

            if (!ErrorInfoBillTable.this.hasEditPermission()) {
                return false;
            }

            return ErrorInfoBillTable.this.beforeEdit(e);
        }

    }

    @Override
    public void initUI() {

        super.initUI();

        this.setBorder(null);

        // 继承自BorderLayoutPanel，不需要设置Layout了
        // setLayout(new BorderLayout());

        this.createBillCardPanel();

        // 设置模型是否可以进行多选
        this.getModel().setInMultiSelectmode(this.isBodyMultiSelectEnable());

        // 获取单据模板模板数据信息
        this.setTemplateVO();

        // 设置单据模板展现信息
        this.setBillCardPanelInfo();

        // 设置是否多选相关的设置信息
        this.processMultiSelect();

        // 设置排序信息
        this.setSortListener();

        // 裁剪右键菜单信息
        this.processPopupMenu();

        this.add(this.billCardPanel);

        // 设置值处理策略
        this.processValueManager();

        // 注册个性信息回调接口
        this.registeCallbak();
    }

    protected void createBillCardPanel() {
        this.billCardPanel = new BillCardPanel();
    }

    protected boolean hasEditPermission() {
        Object obj = this.model.getSelectedData();
        if (this.isNewData(obj)) {
            return true;
        }

        boolean hasp =
                CheckDataPermissionUtil.checkDataPermission(this.updOperateCode, this.mdUpdOperateCode,
                        this.resourceCode, this.model.getContext(), obj);

        if (!hasp) {
            ShowStatusBarMsgUtil.showStatusBarMsg(IShowMsgConstant.getDataPermissionInfo(), this.model.getContext());
            return false;
        }
        return true;
    }

    private boolean isNewData(Object obj) {
        IBDObject bdObject = this.getModel().getBusinessObjectAdapterFactory().createBDObject(obj);
        return bdObject != null && bdObject.getId() == null;
    }

    @Override
    public void handleEvent(AppEvent event) {
        this.doHandleModelEvent(event);
    }

    protected void doHandleModelEvent(AppEvent event) {

        if (event.getType() == AppEventConst.MODEL_INITIALIZED) {
            this.handlingModelEvent = true;
            try {
                this.onModelInit();
            }
            finally {
                this.handlingModelEvent = false;
            }
        }
        else if (event.getType() == AppEventConst.SELECTION_CHANGED) {
            this.handlingModelEvent = true;
            try {
                if (!this.isFromSelf) {
                    this.setSelectedRow(this.model.getSelectedIndex(), -1);
                }
            }
            finally {
                this.handlingModelEvent = false;
            }
        }
        else if (event.getType() == AppEventConst.UISTATE_CHANGED) {
            this.handlingModelEvent = true;
            try {
                // 非编辑到编辑态
                if (this.model.getUiState() == UIState.EDIT) {
                    this.onEdit();
                }
                else {
                    // 编辑态到非编辑态
                    this.onNotEdit();
                }
            }
            finally {
                this.handlingModelEvent = false;
            }

        }
        else if (event.getType() == AppEventConst.DATA_INSERTED) {
            this.handlingModelEvent = true;
            try {
                this.onDataInsert(event);
            }
            finally {
                this.handlingModelEvent = false;
            }
        }
        else if (event.getType() == AppEventConst.DATA_DELETED) {
            this.handlingModelEvent = true;
            try {
                this.onDataDelete(event);
            }
            finally {
                this.handlingModelEvent = false;
            }
        }
        else if (event.getType() == AppEventConst.DATA_UPDATED
                || event.getType() == AppEventConst.SELECTED_DATE_CHANGED) {
            this.handlingModelEvent = true;
            try {
                if (!this.isFromSelf) {
                    this.onDataUpdate(event);
                }
            }
            finally {
                this.handlingModelEvent = false;
            }
        }
    }

    /**
     * BatchSaveAction保存之前会调用该方法判断是否需要保存
     *
     * @return
     */
    public boolean beforeSave() {
        this.billCardPanel.stopEditing();
        return true;
    }

    /**
     * 删行之前被执行
     */
    public void beforeDelete() {
        this.stopEditingNotAutoAddLine();
    }

    /**
     * 此方法不能重写，请将逻辑转移到doAfterEdit()中
     */
    @Override
    final public void afterEdit(BillEditEvent e) {

        this.isFromSelf = true;
        this.doAfterEdit(e);
        int rowIndex = e.getRow();
        if (rowIndex > -1) {
            Object obj = this.getEditingLineVO(rowIndex);
            this.getModel().updateLine(rowIndex, obj);
        }
        this.isFromSelf = false;

    }

    @Override
    public void bodyRowChange(BillEditEvent e) {
        int newIndex = e.getRow();
        this.synchornizeIndex(newIndex);
    }

    public boolean beforeEdit(BillEditEvent e) {
        return true;
    }

    @Override
    public Object getValue() {

        return null;

    }

    @Override
    public void setValue(Object object) {

        this.isModelAddLine = true;
        this.billCardPanel.getBillModel().setSortColumn(
                new String[] {
                        AcquirePriceLogVO.CBMATERIALID, AcquirePriceLogVO.CBPROJECTID, AcquirePriceLogVO.CBVENDORID,
                        AcquirePriceLogVO.CBCUSTOMERID, AcquirePriceLogVO.CBPRODUCTORID, AcquirePriceLogVO.VBFREE1,
                        AcquirePriceLogVO.VBFREE2, AcquirePriceLogVO.VBFREE3, AcquirePriceLogVO.VBFREE4,
                        AcquirePriceLogVO.VBFREE5, AcquirePriceLogVO.VBFREE6, AcquirePriceLogVO.VBFREE7,
                        AcquirePriceLogVO.VBFREE8, AcquirePriceLogVO.VBFREE9, AcquirePriceLogVO.VBFREE10,
                        AcquirePriceLogVO.PRIORITYLEVEL
                });

        if (this.componentValueManager != null) {
            this.componentValueManager.setValue(object);
            if (!(object == null || object.getClass().isArray() && ((Object[]) object).length == 0)) {
                this.billCardPanel.getBillModel().execLoadFormula();
            }
        }
        else {
            Logger.debug("未定义ComponentValueStrategy, 不能设置Editor的值");
        }

        this.isModelAddLine = false;
    }

    public BatchAddLineAction getAddLineAction() {
        return this.addLineAction;
    }

    public void setAddLineAction(BatchAddLineAction addLineAction) {
        this.addLineAction = addLineAction;
    }

    public BatchCopyLineAction getCopyLineAction() {
        return this.copyLineAction;
    }

    public void setCopyLineAction(BatchCopyLineAction copyLineAction) {
        this.copyLineAction = copyLineAction;
    }

    public BatchDelLineAction getDelLineAction() {
        return this.delLineAction;
    }

    public void setDelLineAction(BatchDelLineAction delLineAction) {
        this.delLineAction = delLineAction;
    }

    public BatchInsLineAction getInsLineAction() {
        return this.insLineAction;
    }

    public void setInsLineAction(BatchInsLineAction insLineAction) {
        this.insLineAction = insLineAction;
    }

    public BatchPasteLineAction getPasteLineAction() {
        return this.pasteLineAction;
    }

    public void setPasteLineAction(BatchPasteLineAction pasteLineAction) {
        this.pasteLineAction = pasteLineAction;
    }

    public BatchPasteToTailAction getPasteToTailAction() {
        return this.pasteToTailAction;
    }

    public void setPasteToTailAction(BatchPasteToTailAction pasteToTailAction) {
        this.pasteToTailAction = pasteToTailAction;
    }

    public String getVoClassName() {
        return this.voClassName;
    }

    public void setVoClassName(String voClassName) {
        this.voClassName = voClassName;
    }

    public boolean isBodyAutoAddLine() {
        return this.bodyAutoAddLine;
    }

    public void setIsBodyAutoAddLine(boolean isBodyAutoAddLine) {
        this.bodyAutoAddLine = isBodyAutoAddLine;
        if (this.billCardPanel != null) {
            this.billCardPanel.setBodyAutoAddLine(isBodyAutoAddLine);
        }
    }

    public void setBodyAutoAddLine(boolean isBodyAutoAddLine) {
        this.setIsBodyAutoAddLine(isBodyAutoAddLine);
    }

    public BatchBillTableModel getModel() {
        return this.model;
    }

    public void setModel(BatchBillTableModel model) {
        this.model = model;
        model.addAppEventListener(this);
    }

    public IComponentValueStrategy getComponentValueManager() {
        return this.componentValueManager;
    }

    public void setComponentValueManager(IComponentValueStrategy componentValueManager) {
        this.componentValueManager = componentValueManager;
    }

    public String getNodekey() {
        return this.nodekey;
    }

    public void setNodekey(String nodekey) {
        this.nodekey = nodekey;
    }

    public TemplateContainer getTemplateContainer() {
        return this.templateContainer;
    }

    public void setTemplateContainer(TemplateContainer templateContainer) {
        this.templateContainer = templateContainer;
    }

    @Override
    public BillCardPanel getBillCardPanel() {
        return this.billCardPanel;
    }

    public boolean isBodyMultiSelectEnable() {
        return this.bodyMultiSelectEnable;
    }

    public void setBodyMultiSelectEnable(boolean bodyMultiSelect) {

        this.bodyMultiSelectEnable = bodyMultiSelect;
        if (this.getModel() != null) {
            this.getModel().setInMultiSelectmode(bodyMultiSelect);
        }
    }

    public IBillData getUserdefitemPreparator() {
        return this.userdefitemPreparator;
    }

    public void setUserdefitemPreparator(IBillData userdefitemPreparator) {
        this.userdefitemPreparator = userdefitemPreparator;
    }

    public String getBeanId() {
        return this.beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    protected void setTemplateVO() {

        BillTempletVO template = null;
        if (this.templateContainer == null) {

            this.billCardPanel.setBillType(this.model.getContext().getNodeCode());
            this.billCardPanel.setBusiType(null);
            this.billCardPanel.setOperator(this.model.getContext().getPk_loginUser());
            this.billCardPanel.setCorp(this.model.getContext().getPk_group());
            template =
                    this.billCardPanel.getDefaultTemplet(this.billCardPanel.getBillType(), null,
                            this.billCardPanel.getOperator(), this.billCardPanel.getCorp(), this.nodekey, null);
        }
        else {
            template = this.templateContainer.getTemplate(this.nodekey, null, null);
        }
        if (template == null) {
            Logger.error("没有找到nodekey：" + this.nodekey + "对应的卡片模板");
            throw new IllegalArgumentException(NCLangRes.getInstance().getStrByID("uif2", "BatchBillTable-000000")/* 没有找到设置的单据模板信息 */);
        }

        if (StringUtil.isEmptyWithTrim(this.beanId)) {
            this.beanId = template.getPKBillTemplet() + "_BatchBillTable";
        }

        this.setBillData(template);
    }

    protected void setBillData(BillTempletVO template) {

        this.processTemplateVO(template);
        BillData billdata = new BillData(template, this.getBillStatus());
        if (this.getUserdefitemPreparator() != null) {
            this.getUserdefitemPreparator().prepareBillData(billdata);
        }
        this.processBillData(billdata);
        this.billCardPanel.setBillData(billdata);
    }

    protected void onModelInit() {
        this.synchornizeDataFromModel();
        this.billCardPanel.setEnabled(false);
    }

    protected void processTemplateVO(BillTempletVO templatevo) {
    }

    protected void processBillData(BillData billdata) {
    }

    @SuppressWarnings("unchecked")
    protected HashMap<String, BillItemUISet> getBillStatus() {
        if (StringUtil.isEmptyWithTrim(this.getBeanId())) {
            return null;
        }
        if (this.getModel().getContext() == null || this.getModel().getContext().getStatusRegistery() == null) {
            return null;
        }

        Object statusObj = this.getModel().getContext().getStatusRegistery().getAppStatusObject(this.getBeanId());
        if (statusObj == null || !(statusObj instanceof HashMap)) {
            return null;
        }
        return (HashMap<String, BillItemUISet>) statusObj;
    }

    protected void onDataInsert(AppEvent event) {
        this.isModelAddLine = true;

        RowOperationInfo info = (RowOperationInfo) event.getContextObject();
        int[] indexes = info.getRowIndexes();

        // 不关闭将引起单据模板自动增行，而BatchBillTable的BillTableModelListener不能通知模型增行，导致模型视图不一致。
        this.stopEditingNotAutoAddLine();

        List<Object> allObjs = new ArrayList<Object>();
        List<Object> list = this.getModel().getRows();
        for (int i = 0; i < indexes.length; i++) {
            int index = indexes[i];
            Object obj = list.get(index);

            // 如果是模型引起的增行事件，则增行；不是模型引起而是由Editor引起的，都不需要再增加一行，因为视图上已经增加了。
            if (!this.isEditorAddLine) {
                int rowCount = this.billCardPanel.getBillModel().getRowCount();
                if (rowCount < index + 1) {
                    this.billCardPanel.getBillModel().addLine();
                }
                else {
                    this.billCardPanel.getBillModel().insertRow(index);
                }
            }

            Object billTemplateVO = this.getEditingLineVO(index);
            this.processTemplateData2ModeData(billTemplateVO, obj);

            if (!this.billCardPanel.getBillData().isMeataDataTemplate()) {
                this.setTemplateData(index, obj);
                this.billCardPanel.getBillModel().execLoadFormulaByRow(index);
                this.afterLineInsert(index);
            }
            else {
                allObjs.add(obj);
            }
        }

        if (this.billCardPanel.getBillData().isMeataDataTemplate()) {
            this.setTemplateData(indexes[0], allObjs.toArray(new Object[0]));
            for (int i = 0; i < indexes.length; i++) {
                this.billCardPanel.getBillModel().execLoadFormulaByRow(indexes[i]);
                this.afterLineInsert(indexes[i]);
            }
        }

        this.isModelAddLine = false;

        // 自动增行的时候，不能获取焦点，否则会造成无法响应点击按钮动作的结果。
        if (!this.isEditorAddLine) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    ErrorInfoBillTable.this
                    .getBillCardPanel()
                    .getBodyPanel()
                    .getTable()
                    .getSelectionModel()
                    .setSelectionInterval(ErrorInfoBillTable.this.model.getSelectedIndex(),
                            ErrorInfoBillTable.this.model.getSelectedIndex());
                    ErrorInfoBillTable.this.getBillCardPanel().transferFocusToFirstEditItem();
                    // 下面这一行将选择的列设置为第0列。 如果第0列是非编辑列，就违反了规范中关于焦点定位到可编辑格中的要求。
                    // 实际上在onEdit()调用的setSelectedRow中，调用了billCardPanel.transferFocusToFirstEditItem,已经将选择列设置到了第一个可编辑列之上
                    // 而上面的代码又将选择行，设置到了新增行之上。二者配合，完成任务。
                    // ((BillScrollPane)billCardPanel.getBodySelectedScrollPane()).getTable().changeSelection(model.getSelectedIndex(),
                    // 0, false, false);
                    ((BillScrollPane) ErrorInfoBillTable.this.billCardPanel.getBodySelectedScrollPane()).getTable()
                    .requestFocus();
                }
            });
        }
    }

    protected void stopEditingNotAutoAddLine() {

        if (this.isBodyAutoAddLine()) {
            this.billCardPanel.setBodyAutoAddLine(false);
        }
        if (this.billCardPanel.getBillModel().getRowCount() > 0 && this.billCardPanel.isEnabled()) {
            this.billCardPanel.stopEditing();
        }
        this.billCardPanel.setBodyAutoAddLine(this.isBodyAutoAddLine());
    }

    /**
     * 默认支持CVO，其他类型VO，在此进行处理。
     *
     * @param index
     * @param obj
     */
    protected void setTemplateData(int index, Object obj) {

        if (!this.billCardPanel.getBillData().isMeataDataTemplate()) {
            this.billCardPanel.getBillModel().setBodyRowVO((CircularlyAccessibleValueObject) obj, index);
        }
        else {
            if (obj != null && obj.getClass().isArray()) {
                Object[] objs = (Object[]) obj;
                this.billCardPanel.getBillModel().setBodyRowObjectByMetaData(objs, index);
            }
            else {
                this.billCardPanel.getBillModel().setBodyRowObjectByMetaData(obj, index);
            }
        }
    }

    protected void onDataDelete(AppEvent event) {
        this.isFromSelf = true;
        RowOperationInfo info = (RowOperationInfo) event.getContextObject();
        this.billCardPanel.getBillModel().delLine(info.getRowIndexes());
        this.isFromSelf = false;
    }

    protected void onDataUpdate(AppEvent event) {
        RowOperationInfo info = (RowOperationInfo) event.getContextObject();
        int index = info.getRowIndexes()[0];

        Object obj = this.getModel().getRow(index);
        this.setTemplateData(index, obj);
    }

    protected void onEdit() {
        this.setRowStateToNormal();
        this.billCardPanel.setEnabled(true);
        if (this.getModel().getSelectedIndex() != -1) {
            this.setSelectedRow(this.getModel().getSelectedIndex(), -1);
        }
    }

    protected void onNotEdit() {
        this.billCardPanel.stopEditing();
        this.billCardPanel.setEnabled(false);
        this.synchornizeDataFromModel();
        this.setRowStateToNormal();

        // 没有此处，在IE6中将失去焦点
        // SwingUtilities.invokeLater(new Runnable() {
        // @Override
        // public void run() {
        // billCardPanel.getBillTable().requestFocusInWindow();
        // }
        // });
    }

    protected void setRowStateToNormal() {

        // billCardPanel.updateValue()会清空之前的选择行和列，改为下面这行
        this.billCardPanel.getBillData().updateValue();
    }

    /**
     * 页面被插入某行时会调用此方法，供进行界面元素的可用性设置。
     *
     * @param index
     */
    protected void afterLineInsert(int index) {
    }

    /**
     * 实现编辑后的逻辑
     *
     * @param e
     */
    protected void doAfterEdit(BillEditEvent e) {

    }

    protected Object getEditingLineVO(int rowIndex) {

        Object obj = null;
        if (this.getBillCardPanel().getBillData().isMeataDataTemplate()) {
            Map<String, Object> map = this.getBillCardPanel().getBillModel().getBodyRowValueByMetaData(rowIndex);

            BillTabVO[] tabvos = this.getBillCardPanel().getBillData().getBillTabVOs(IBillItem.BODY);
            IBusinessEntity entity = tabvos[0].getBillMetaDataBusinessEntity();
            obj = DASFacade.newInstanceWithKeyValues(entity, map).getContainmentObject();

            // 避免对象存在而模型不存在（比如外键）而导致属性丢失的情况
            String key = null;
            for (Iterator<String> keyIter = map.keySet().iterator(); keyIter.hasNext();) {
                key = keyIter.next();
                IAttribute attribute = entity.getAttributeByPath(key);
                boolean isMultiText = attribute != null && attribute.getDataType().getTypeType() == IType.MULTILANGUAGE;
                if (map.get(key) != null && BeanHelper.getProperty(obj, key) == null
                        && (attribute == null || !isMultiText)) {
                    BeanHelper.setProperty(obj, key, map.get(key));
                }
            }
        }
        else {
            obj = this.getBillCardPanel().getBillModel().getBodyValueRowVO(rowIndex, this.voClassName);
        }
        return obj;
    }

    protected void registeCallbak() {

        if (this.model.getContext() == null || this.model.getContext().getStatusRegistery() == null) {
            return;
        }
        this.model.getContext().getStatusRegistery().addCallback(new AppStatusRegisteryCallback() {

            @Override
            public Object getID() {
                return ErrorInfoBillTable.this.getBeanId();
            }

            @Override
            public Object getStatusObject() {

                return ErrorInfoBillTable.this.getBillCardPanel().getBillCardUISet();
            }
        });
    }

    protected void synchornizeDataFromModel() {

        int col = this.billCardPanel.getBillTable().getSelectedColumn();

        this.setValue(this.model.getRows().toArray());
        if (this.model.getSelectedIndex() > -1 && this.model.getRows().size() > 0) {
            this.setSelectedRow(this.model.getSelectedIndex(), col);
        }
    }

    private void setSelectedRow(int rowIndex, int col) {

        // if (rowIndex == -1) {
        // return;
        // }
        // if (col == -1) {
        // col = this.getBillCardPanel().getBodyPanel().getTable().getSelectedColumn();
        // }
        // col = col == -1 ? 0 : col;
        this.getBillCardPanel().getBodyPanel().getTable().setRowSelectionInterval(0, 0);

    }

    /**
     * 将单据模板默认数据设置到新增数据中，以模板数据的优先级为最高。
     * update lkp 20100430 根据sj要求将代码默认值作为高优先级处理
     *
     * @param templateData
     * @param modelData
     */
    protected void processTemplateData2ModeData(Object templateData, Object modelData) {
        if (this.getBillCardPanel().getBillData().isMeataDataTemplate()) {
            NCObject tempncvo = NCObject.newInstance(templateData);
            IBean bean;
            try {
                bean = MDBaseQueryFacade.getInstance().getBeanByFullClassName(templateData.getClass().getName());
                NCObject modelvo = NCObject.newInstance(bean, modelData);
                List<IAttribute> attrList = bean.getAttributes();
                if (attrList != null && attrList.size() > 0) {
                    for (IAttribute attr : attrList) {
                        Object templateDefValue = DASFacade.getAttributeValue(tempncvo, attr);
                        Object modelDefValue = DASFacade.getAttributeValue(modelvo, attr);
                        if (modelDefValue == null && templateDefValue != null) {
                            DASFacade.setAttributeValue(modelvo, attr, templateDefValue);
                        }
                    }
                }
            }
            catch (nc.md.model.MetaDataException e) {
            }
        }
        else if (templateData instanceof CircularlyAccessibleValueObject) {
            CircularlyAccessibleValueObject templatecvo = (CircularlyAccessibleValueObject) templateData;
            CircularlyAccessibleValueObject modelcvo = (CircularlyAccessibleValueObject) modelData;
            String[] names = templatecvo.getAttributeNames();
            if (names != null && names.length > 0) {
                for (String name : names) {
                    Object templateDefValue = templatecvo.getAttributeValue(name);
                    Object modelDefValue = modelcvo.getAttributeValue(name);
                    if (modelDefValue == null && templateDefValue != null) {
                        modelcvo.setAttributeValue(name, templateDefValue);
                    }
                }
            }
        }
    }

    private void setBillCardPanelInfo() {

        this.billCardPanel.setBorder(null);
        this.billCardPanel.addEditListener(this);
        this.billCardPanel.addBodyEditListener2(this.editListener);
        this.billCardPanel.setRowNOShow(true);
        this.billCardPanel.getBillModel().setRowEditState(true);

        this.billCardPanel.getBillTable().setCellSelectionEnabled(false);
        this.billCardPanel.getBillTable().setRowSelectionAllowed(true);
        this.billCardPanel.setBodyMultiSelect(false);

        this.billCardPanel.setBodyAutoAddLine(this.isBodyAutoAddLine());

        this.billCardPanel.getBillTable().getModel().addTableModelListener(new BillTableModelListener());

        this.billCardPanel.getBillTable().setCellSelectionEnabled(true);

        if (this.getBillModelCellEditableController() != null) {
            this.billCardPanel.getBillModel().setCellEditableController(this.getBillModelCellEditableController());
        }
    }

    private void processValueManager() {

        if (this.componentValueManager == null) {
            if (this.billCardPanel.getBillData().isMeataDataTemplate()) {
                this.componentValueManager = new BillCardPanelMetaDataValueAdapter();
            }
            else {
                this.componentValueManager = new BillCardPanelBodyVOValueAdapter();
            }
        }

        if (this.componentValueManager != null) {
            this.componentValueManager.setComponent(this.billCardPanel);
        }
    }

    /**
     * 根据当前注射的Action情况，剪裁右键菜单。
     */
    private void processPopupMenu() {
        this.billCardPanel.getBodyPanel().replaceDefaultAction(BillScrollPane.ADDLINE, null);
        this.billCardPanel.getBodyPanel().replaceDefaultAction(BillScrollPane.DELLINE, null);
        this.billCardPanel.getBodyPanel().replaceDefaultAction(BillScrollPane.INSERTLINE, null);
        this.billCardPanel.getBodyPanel().replaceDefaultAction(BillScrollPane.COPYLINE, null);
        this.billCardPanel.getBodyPanel().replaceDefaultAction(BillScrollPane.PASTELINE, null);
        this.billCardPanel.getBodyPanel().replaceDefaultAction(BillScrollPane.PASTELINETOTAIL, null);

        if (this.addLineAction != null) {
            this.billCardPanel.getBodyPanel().addFixAction(this.addLineAction);
        }
        if (this.insLineAction != null) {
            this.billCardPanel.getBodyPanel().addFixAction(this.insLineAction);
        }
        if (this.delLineAction != null) {
            this.billCardPanel.getBodyPanel().addFixAction(this.delLineAction);
        }
        if (this.copyLineAction != null) {
            this.billCardPanel.getBodyPanel().addFixAction(this.copyLineAction);
        }
        if (this.pasteLineAction != null) {
            this.billCardPanel.getBodyPanel().addFixAction(this.pasteLineAction);
        }
        if (this.pasteToTailAction != null) {
            this.billCardPanel.getBodyPanel().addFixAction(this.pasteToTailAction);
        }
    }

    private void synchornizeIndex(int newIndex) {

        if (newIndex == this.model.getSelectedIndex() || newIndex < 0) {
            return;
        }

        // 如果在处理模型事件，那么此事件的触发就是在让界面同步模型的选择,没有必要再去设置模型的选择行
        if (this.handlingModelEvent) {
            return;
        }

        this.isFromSelf = true;
        this.model.setSelectedIndex(newIndex);
        this.isFromSelf = false;
    }

    private void setSortListener() {
        // sort
        this.billCardPanel.getBillModel().addSortRelaObjectListener(new IBillRelaSortListener() {
            @Override
            public List getRelaSortObject() {
                return ErrorInfoBillTable.this.model.getRows();
            }
        });
    }

    protected void processMultiSelect() {

        if (this.isBodyMultiSelectEnable()) {
            this.selectionListener = new BillListSelectionListener();
            this.billCardPanel.getBillTable().getSelectionModel().addListSelectionListener(this.selectionListener);
            this.billCardPanel.getBillTable().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        }
        else {
            this.billCardPanel.getBillTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
    }

    public BillModelCellEditableController getBillModelCellEditableController() {
        return this.billModelCellEditableController;
    }

    public void setBillModelCellEditableController(BillModelCellEditableController billModelCellEditableController) {
        this.billModelCellEditableController = billModelCellEditableController;
    }

    public String getMdUpdOperateCode() {
        return this.mdUpdOperateCode;
    }

    public void setMdUpdOperateCode(String mdUpdOperateCode) {
        this.mdUpdOperateCode = mdUpdOperateCode;
    }

    public String getUpdOperateCode() {
        return this.updOperateCode;
    }

    public void setUpdOperateCode(String updOperateCode) {
        this.updOperateCode = updOperateCode;
    }

    public String getResourceCode() {
        return this.resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }
}
