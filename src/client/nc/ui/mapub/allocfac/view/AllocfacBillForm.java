package nc.ui.mapub.allocfac.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Action;

import nc.bd.framework.base.CMStringUtil;
import nc.bs.uif2.validation.ValidationException;
import nc.ui.bd.business.ref.FilterActivityRefUtil;
import nc.ui.cmpub.framework.view.CMBillFormFacade;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.actions.FirstLineAction;
import nc.ui.pubapp.uif2app.actions.LastLineAction;
import nc.ui.pubapp.uif2app.actions.NextLineAction;
import nc.ui.pubapp.uif2app.actions.PreLineAction;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.mapub.allocfac.enumeration.AllocfacEnum;

/**
 * 分配系数的表单界面
 * <p>
 * 设置了可显示列，初始化为成本中心
 */

public class AllocfacBillForm extends CMBillFormFacade {

    /**
     * serialVersionUIDid
     */
    private static final long serialVersionUID = -3915644395160288122L;

    @Override
    public boolean isShowTotalLine() {
        return false;
    }

    /**
     * 设置默认值及执行显示公式
     */
    @Override
    protected void setDefaultValue() {
        // 调用父类设置默认值
        super.setDefaultValue();
        // 设置默认值
        this.setDefaultValueFromModel();
        // 执行显示公式
        this.execLoadFormula();
    }

    /**
     * 设置默认值
     */
    private void setDefaultValueFromModel() {
        this.getBillCardPanel().setHeadItem(AllocfacHeadVO.PK_ORG, this.getModel().getContext().getPk_org());
        // 分配内容 初始化显示为“成本中心”,纪录修改
        String defaultIndex = AllocfacEnum.costcenter.getEnumValue().getValue();
        this.getBillCardPanel().setHeadItem(AllocfacHeadVO.IALLOCTYPE, defaultIndex);
        this.setAllRefPaneParam(this.getBillCardPanel());
    }

    /**
     * 初始化业务单元的过滤
     *
     * @param billCardPanel
     *            BillCardPanel
     */
    public void setAllRefPaneParam(BillCardPanel billCardPanel) {
        BillItem item = billCardPanel.getHeadItem("pk_org");
        this.setRefPaneParam(item, null);
        String pk_org = item.getDefaultValue();

        // 增加作业非空核算要素过滤
        item = billCardPanel.getBodyItem(AllocfacItemVO.CACTIVITYID);
        if (CMStringUtil.isNotEmpty(pk_org)) {
            FilterActivityRefUtil.activityRefFilterByFactorNotNull(item, pk_org);
        }
    }

    /**
     * 初始化业务单元的过滤
     *
     * @param item
     *            BillItem
     * @param where
     *            String
     */
    public void setRefPaneParam(BillItem item, String where) {
        if (item == null) {
            return;
        }
        UIRefPane ref = (UIRefPane) item.getComponent();
        ref.setWhereString(where);
    }

    @Override
    public void initUI() {
        super.initUI();
        this.setActions(this.getDefaultActions());
    }

    // 去掉附件管理按钮
    private List<Action> getDefaultActions() {
        List<Action> defaultActions = new ArrayList<Action>();
        BillManageModel billManageModel = (BillManageModel) this.getModel();

        FirstLineAction firstLineAction = new FirstLineAction();
        firstLineAction.setModel(billManageModel);
        firstLineAction.setEnabled(false);
        defaultActions.add(firstLineAction);

        PreLineAction preLineAction = new PreLineAction();
        preLineAction.setModel(billManageModel);
        preLineAction.setEnabled(false);
        defaultActions.add(preLineAction);

        NextLineAction nextLineAction = new NextLineAction();
        nextLineAction.setModel(billManageModel);
        nextLineAction.setEnabled(false);
        defaultActions.add(nextLineAction);

        LastLineAction lastLineAction = new LastLineAction();
        lastLineAction.setModel(billManageModel);
        lastLineAction.setEnabled(false);
        defaultActions.add(lastLineAction);

        return defaultActions;
    }

    @Override
    public boolean validateValue(Object value) throws ValidationException {
        // 模板非空校验
        if (this.isTemplateNotNullValidate()) {
            // 获取子表空行
            Map<String, Integer[]> nullChildrenIndex = null;
            if (null != this.getBlankChildrenFilter()) {
                // 数据过滤子表空行
                nullChildrenIndex = this.getBlankChildrenFilter().filter(this.getBillCardPanel(), value);
            }
            else {
                nullChildrenIndex = new HashMap<String, Integer[]>();
            }
            for (Entry<String, Integer[]> entry : nullChildrenIndex.entrySet()) {
                if (entry.getValue() != null && entry.getValue().length > 0) {
                    int[] rows = new int[entry.getValue().length];
                    for (int i = 0; i < entry.getValue().length; i++) {
                        rows[i] = entry.getValue()[i].intValue();
                    }
                    this.getBillCardPanel().getBodyPanel(entry.getKey()).delLine(rows);
                }
            }
            // BillPanelUtils.validateNotNullField(this.getBillCardPanel(),
            // nullChildrenIndex);
            // this.getBillCardPanel().dataNotNullValidate();

        }
        // 执行验证公式
        boolean pass = this.getBillCardPanel().getBillData().execValidateFormulas();
        return pass;
    }
}
