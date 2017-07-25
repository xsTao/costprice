package nc.ui.mapub.driver.view;

import java.util.HashMap;
import java.util.Map;

import nc.ui.mapub.driver.view.dialog.DriverFormulaPane;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.mapub.driver.entity.DriverVO;

/**
 * <b> 分配系数的表单界面 </b>
 *
 * @since 6.0
 * @version 2012-9-26 下午03:49:45
 * @author liyjf
 */
public class DriverBillForm extends ShowUpableBillForm {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = -6726499505896826548L;

    public DriverBillForm() {
        this.setShowOrgPanel(false);
    }

    /**
     * Map<String,DriverFormulaPane>
     */
    private Map<String, DriverFormulaPane> formulaPaneMap = new HashMap<String, DriverFormulaPane>();

    @Override
    protected void setDefaultValue() {
        super.setDefaultValue();
        this.setDefaultValueFromModel();
        this.execLoadFormula();
    }

    /**
     * 设置默认值
     */
    private void setDefaultValueFromModel() {
        this.billCardPanel.setHeadItem(DriverVO.PK_ORG, this.getModel().getContext().getPk_org());
    }

    @Override
    protected void onAdd() {
        super.onAdd();
        this.getBillCardPanel().getHeadItem(DriverVO.VFORMULAVALUE).setEdit(true);
    }

    @Override
    protected void onEdit() {
        super.onEdit();
        this.getBillCardPanel().getHeadItem(DriverVO.VFORMULAVALUE).setEdit(true);
    }

    /**
     * formulaPane get method
     *
     * @return DriverFormulaPane
     */
    public DriverFormulaPane getFormulaPane(String pk_org) {
        DriverFormulaPane formulaPane = this.formulaPaneMap.get(pk_org);
        if (null == formulaPane) {
            formulaPane =
                    new DriverFormulaPane(this.getModel().getContext().getEntranceUI(), this.getModel().getContext(),
                            this);
            this.formulaPaneMap.put(pk_org, formulaPane);
        }
        return formulaPane;
    }
}
