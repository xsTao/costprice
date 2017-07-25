/**
 * 
 */
package nc.ui.mapub.driver.view.dialog.formulaVariableTab;

import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.Map;

import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.bd.ref.model.MaterialDefaultRefModel;
import nc.ui.mapub.driver.view.dialog.AbstractDriverTabBuilder;
import nc.ui.mapub.driver.view.dialog.DriverFormulaEventSource;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.formula.dialog.FormulaEventSource.FormulaEventType;
import nc.vo.bd.material.MaterialVO;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.CMDriverParameterEnum;
import nc.vo.ml.MultiLangUtil;
import nc.vo.pub.formulaedit.FormulaItem;

/**
 * <b> BOM定额 </b>
 * 
 * @since v6.3
 * @version 2014-1-26 下午03:32:09
 * @author liyjf
 */
public class BomTabBuilder extends AbstractDriverTabBuilder {
    /**
     * 对话框高
     */
    // private static final int DLG_HEIGHT = 480;

    /**
     * 对话框宽
     */
    // private static final int DLG_WIDTH = 640;

    /**
     * 物料refpanel
     */
    UIRefPane materialPanel = null;

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1856422533757856436L;

    @Override
    public Map<String, FormulaItem> getAllVariableItems() {
        Map<String, FormulaItem> tableItems = new LinkedHashMap<String, FormulaItem>();
        String[][] svalue =
                {
                    {
                        CMDriverLangConst.getBOM_STUFF_CONSUME_QUOTA(),
                        CMDriverParameterEnum.BOM_STUFF_CONSUME_QUOTA.getCode()
                    },
                    {
                        CMDriverLangConst.getMAIN_STUFF_CONSUME_QUOTA(),
                        CMDriverParameterEnum.MAIN_STUFF_CONSUME_QUOTA.getCode()
                    },
                    {
                        CMDriverLangConst.getASSIN_STUFF_BOM_QUOTA(),
                        CMDriverParameterEnum.ASSIN_STUFF_BOM_QUOTA.getCode()
                    },
                    {
                        CMDriverLangConst.getRT_STUFF_CONSUME_QUOTA(),
                        CMDriverParameterEnum.RT_STUFF_CONSUME_QUOTA.getCode()
                    },
                    {
                        CMDriverLangConst.getMO_STUFF_CONSUME_QUOTA(),
                        CMDriverParameterEnum.MO_STUFF_CONSUME_QUOTA.getCode()
                    },
                    {
                        CMDriverLangConst.getACTUAL_STUFF_NUMBER(), CMDriverParameterEnum.ACTUAL_STUFF_NUMBER.getCode()
                    },
                    {
                        CMDriverLangConst.getASSIGN_STUFF_ACTUAL_NUMBER(),
                        CMDriverParameterEnum.ASSIGN_STUFF_ACTUAL_NUMBER.getCode()
                    },
                    {
                        CMDriverLangConst.getASSIGN_STUFF_ACTUAL_MONEY(),
                        CMDriverParameterEnum.ASSIGN_STUFF_ACTUAL_MONEY.getCode()
                    }
                };
        for (int i = 0; i < svalue.length; i++) {
            tableItems.put(svalue[i][0], new FormulaItem(svalue[i][0], svalue[i][1], svalue[i][0]));
        }
        return tableItems;
    }

    @Override
    public String getTabName() {
        return CMDriverLangConst.getSTUFF_NUMBER();
    }

    @Override
    protected void mouseDoubleClicked(MouseEvent e, FormulaItem formulaItem, DriverFormulaEventSource eventSource) {
        // 弹出选择物料的对话框
        String displayName = formulaItem.getDisplayName();
        if (CMDriverLangConst.getASSIGN_STUFF_ACTUAL_NUMBER().equals(displayName)) {
            this.getMaterialPanel().showModel();
            AbstractRefModel refModel = this.getMaterialPanel().getRefModel();
            String materialvId = refModel.getPkValue();
            if (materialvId != null) {
                String code = refModel.getValue(MaterialVO.CODE).toString();
                String name = refModel.getValue(MaterialVO.NAME + MultiLangUtil.getCurrentLangSeqSuffix()).toString();
                // 写公式编辑器内容
                eventSource.setEventType(FormulaEventType.INSERT_TO_EDITOR);
                String materialText = this.getText(CMDriverLangConst.getASSIGN_STUFF_ACTUAL_NUMBER(), code, name);
                String materialTextCode =
                        this.getCode(CMDriverParameterEnum.ASSIGN_STUFF_ACTUAL_NUMBER.getCode(), materialvId);
                eventSource.setNewString(materialText);
                eventSource.setNewValueString(materialTextCode);
            }
        }
        else if (CMDriverLangConst.getASSIGN_STUFF_ACTUAL_MONEY().equals(displayName)) {
            this.getMaterialPanel().showModel();
            AbstractRefModel refModel = this.getMaterialPanel().getRefModel();
            String materialvId = refModel.getPkValue();
            String code = refModel.getValue(MaterialVO.CODE).toString();
            String name = refModel.getValue(MaterialVO.NAME + MultiLangUtil.getCurrentLangSeqSuffix()).toString();
            if (materialvId != null) {
                // 写公式编辑器内容
                eventSource.setEventType(FormulaEventType.INSERT_TO_EDITOR);
                String materialText = this.getText(CMDriverLangConst.getASSIGN_STUFF_ACTUAL_MONEY(), code, name);
                String materialTextCode =
                        this.getCode(CMDriverParameterEnum.ASSIGN_STUFF_ACTUAL_MONEY.getCode(), materialvId);
                eventSource.setNewString(materialText);
                eventSource.setNewValueString(materialTextCode);
            }
        }
        else if (CMDriverLangConst.getASSIN_STUFF_BOM_QUOTA().equals(displayName)) {
            this.getMaterialPanel().showModel();
            AbstractRefModel refModel = this.getMaterialPanel().getRefModel();
            String materialvId = refModel.getPkValue();
            String code = refModel.getValue(MaterialVO.CODE).toString();
            String name = refModel.getValue(MaterialVO.NAME + MultiLangUtil.getCurrentLangSeqSuffix()).toString();
            if (materialvId != null) {
                // 写公式编辑器内容
                eventSource.setEventType(FormulaEventType.INSERT_TO_EDITOR);
                String materialText = this.getText(CMDriverLangConst.getASSIN_STUFF_BOM_QUOTA(), code, name);
                String materialTextCode =
                        this.getCode(CMDriverParameterEnum.ASSIN_STUFF_BOM_QUOTA.getCode(), materialvId);
                eventSource.setNewString(materialText);
                eventSource.setNewValueString(materialTextCode);
            }
        }
        else {
            super.mouseDoubleClicked(e, formulaItem, eventSource);
        }
    }

    /**
     * 获取物料panel
     * 
     * @return 物料panel
     */
    private UIRefPane getMaterialPanel() {
        if (this.materialPanel == null) {
            this.materialPanel = new UIRefPane();
            this.materialPanel.setRefModel(new MaterialDefaultRefModel());
            this.materialPanel.getRefModel().setRefTitle(CMDriverLangConst.getMATERIAL_ITEM());
            this.materialPanel.setMultiSelectedEnabled(false);
            ((MaterialDefaultRefModel) this.materialPanel.getRefModel()).setAssginShowData(true);
        }
        this.materialPanel.getRefModel().setPk_org(this.getLoginContext().getPk_org());
        return this.materialPanel;
    }

}
