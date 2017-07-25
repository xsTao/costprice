/**
 *
 */
package nc.ui.mapub.driver.view.dialog.formulaVariableTab;

import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.Map;

import nc.bd.framework.db.CMSqlBuilder;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.mapub.allocfac.ref.AllocfacRefModel;
import nc.ui.mapub.driver.view.dialog.AbstractDriverTabBuilder;
import nc.ui.mapub.driver.view.dialog.DriverFormulaEventSource;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.formula.dialog.FormulaEventSource.FormulaEventType;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.allocfac.enumeration.AllocfacEnum;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.CMDriverParameterEnum;
import nc.vo.ml.MultiLangUtil;
import nc.vo.pub.formulaedit.FormulaItem;

/**
 * <b> 分配系数 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-3-30
 *
 * @author:wangtf
 */

@SuppressWarnings("serial")
public class AllocfacTabBuilder extends AbstractDriverTabBuilder {

    @Override
    public Map<String, FormulaItem> getAllVariableItems() {
        Map<String, FormulaItem> tableItems = new LinkedHashMap<String, FormulaItem>();
        String[][] svalue =
            {
                {
                    CMDriverLangConst.getCENTER_ALLOCFAC(),
                    CMDriverParameterEnum.ALLOC_FAC.getCode() + CMDriverLangConst.FORMULA_SPLIT_START
                    + AllocfacEnum.costcenter.toIntValue() + CMDriverLangConst.FORMULA_SPLIT_END
                },
                {
                    CMDriverLangConst.getPRODUCT_ALLOCFAC(),
                    CMDriverParameterEnum.ALLOC_FAC.getCode() + CMDriverLangConst.FORMULA_SPLIT_START
                    + AllocfacEnum.product.toIntValue() + CMDriverLangConst.FORMULA_SPLIT_END
                },
                {
                    CMDriverLangConst.getPRODUCTFREE_ALLOCFAC(),
                    CMDriverParameterEnum.ALLOC_FAC.getCode() + CMDriverLangConst.FORMULA_SPLIT_START
                    + AllocfacEnum.productvbfree.toIntValue() + CMDriverLangConst.FORMULA_SPLIT_END
                },
                {
                    CMDriverLangConst.getCOSTCLASS_ALLOCFAC(),
                    CMDriverParameterEnum.ALLOC_FAC.getCode() + CMDriverLangConst.FORMULA_SPLIT_START
                    + AllocfacEnum.costclass.toIntValue() + CMDriverLangConst.FORMULA_SPLIT_END
                },
                {
                    CMDriverLangConst.getBASECLASS_ALLOCFAC(),
                    CMDriverParameterEnum.ALLOC_FAC.getCode() + CMDriverLangConst.FORMULA_SPLIT_START
                    + AllocfacEnum.baseclass.toIntValue() + CMDriverLangConst.FORMULA_SPLIT_END
                },
                {
                    CMDriverLangConst.getSTUFF_ALLOCFAC(),
                    CMDriverParameterEnum.ALLOC_FAC.getCode() + CMDriverLangConst.FORMULA_SPLIT_START
                    + AllocfacEnum.stuff.toIntValue() + CMDriverLangConst.FORMULA_SPLIT_END
                }
            };
        for (int i = 0; i < svalue.length; i++) {
            tableItems.put(svalue[i][0], new FormulaItem(svalue[i][0], svalue[i][1], svalue[i][0]));
        }
        return tableItems;
    }

    @Override
    public String getTabName() {
        return CMDriverLangConst.getALLOCFAC();
    }

    @Override
    protected void mouseDoubleClicked(MouseEvent e, FormulaItem formulaItem, DriverFormulaEventSource eventSource) {
        // 弹出选择物料的对话框
        String displayName = formulaItem.getDisplayName();
        if (CMDriverLangConst.getCENTER_ALLOCFAC().equals(displayName)) {
            UIRefPane allocfacPanel =
                    this.getAllocfacPanel(CMDriverLangConst.getCENTER_ALLOCFAC(), AllocfacEnum.costcenter.toIntValue());
            allocfacPanel.showModel();
            AbstractRefModel refModel = allocfacPanel.getRefModel();
            String allocfacId = refModel.getPkValue();
            if (allocfacId != null) {
                String code = refModel.getValue(AllocfacHeadVO.VCODE).toString();
                String name =
                        refModel.getValue(AllocfacHeadVO.VNAME + MultiLangUtil.getCurrentLangSeqSuffix()).toString();
                // 写公式编辑器内容
                eventSource.setEventType(FormulaEventType.INSERT_TO_EDITOR);
                String allocfacText = this.getText(displayName, code, name);
                String allocfacTextCode = this.getCode(formulaItem.getInputSig(), allocfacId);
                eventSource.setNewString(allocfacText);
                eventSource.setNewValueString(allocfacTextCode);
            }
        }
        else if (CMDriverLangConst.getPRODUCT_ALLOCFAC().equals(displayName)) {
            UIRefPane allocfacPanel =
                    this.getAllocfacPanel(CMDriverLangConst.getPRODUCT_ALLOCFAC(), AllocfacEnum.product.toIntValue());
            allocfacPanel.showModel();
            AbstractRefModel refModel = allocfacPanel.getRefModel();
            String allocfacId = refModel.getPkValue();
            if (allocfacId != null) {
                String code = refModel.getValue(AllocfacHeadVO.VCODE).toString();
                String name =
                        refModel.getValue(AllocfacHeadVO.VNAME + MultiLangUtil.getCurrentLangSeqSuffix()).toString();
                // 写公式编辑器内容
                eventSource.setEventType(FormulaEventType.INSERT_TO_EDITOR);
                String allocfacText = this.getText(displayName, code, name);
                String allocfacTextCode = this.getCode(formulaItem.getInputSig(), allocfacId);
                eventSource.setNewString(allocfacText);
                eventSource.setNewValueString(allocfacTextCode);
            }
        }
        else if (CMDriverLangConst.getPRODUCTFREE_ALLOCFAC().equals(displayName)) {
            UIRefPane allocfacPanel =
                    this.getAllocfacPanel(CMDriverLangConst.getPRODUCTFREE_ALLOCFAC(),
                            AllocfacEnum.productvbfree.toIntValue());
            allocfacPanel.showModel();
            AbstractRefModel refModel = allocfacPanel.getRefModel();
            String allocfacId = refModel.getPkValue();
            if (allocfacId != null) {
                String code = refModel.getValue(AllocfacHeadVO.VCODE).toString();
                String name =
                        refModel.getValue(AllocfacHeadVO.VNAME + MultiLangUtil.getCurrentLangSeqSuffix()).toString();
                // 写公式编辑器内容
                eventSource.setEventType(FormulaEventType.INSERT_TO_EDITOR);
                String allocfacText = this.getText(displayName, code, name);
                String allocfacTextCode = this.getCode(formulaItem.getInputSig(), allocfacId);
                eventSource.setNewString(allocfacText);
                eventSource.setNewValueString(allocfacTextCode);
            }
        }
        else if (CMDriverLangConst.getCOSTCLASS_ALLOCFAC().equals(displayName)) {
            UIRefPane allocfacPanel =
                    this.getAllocfacPanel(CMDriverLangConst.getCOSTCLASS_ALLOCFAC(),
                            AllocfacEnum.costclass.toIntValue());
            allocfacPanel.showModel();
            AbstractRefModel refModel = allocfacPanel.getRefModel();
            String allocfacId = refModel.getPkValue();
            if (allocfacId != null) {
                String code = refModel.getValue(AllocfacHeadVO.VCODE).toString();
                String name =
                        refModel.getValue(AllocfacHeadVO.VNAME + MultiLangUtil.getCurrentLangSeqSuffix()).toString();
                // 写公式编辑器内容
                eventSource.setEventType(FormulaEventType.INSERT_TO_EDITOR);
                String allocfacText = this.getText(displayName, code, name);
                String allocfacTextCode = this.getCode(formulaItem.getInputSig(), allocfacId);
                eventSource.setNewString(allocfacText);
                eventSource.setNewValueString(allocfacTextCode);
            }
        }
        else if (CMDriverLangConst.getBASECLASS_ALLOCFAC().equals(displayName)) {
            UIRefPane allocfacPanel =
                    this.getAllocfacPanel(CMDriverLangConst.getBASECLASS_ALLOCFAC(),
                            AllocfacEnum.baseclass.toIntValue());
            allocfacPanel.showModel();
            AbstractRefModel refModel = allocfacPanel.getRefModel();
            String allocfacId = refModel.getPkValue();
            if (allocfacId != null) {
                String code = refModel.getValue(AllocfacHeadVO.VCODE).toString();
                String name =
                        refModel.getValue(AllocfacHeadVO.VNAME + MultiLangUtil.getCurrentLangSeqSuffix()).toString();
                // 写公式编辑器内容
                eventSource.setEventType(FormulaEventType.INSERT_TO_EDITOR);
                String allocfacText = this.getText(displayName, code, name);
                String allocfacTextCode = this.getCode(formulaItem.getInputSig(), allocfacId);
                eventSource.setNewString(allocfacText);
                eventSource.setNewValueString(allocfacTextCode);
            }
        }
        else if (CMDriverLangConst.getSTUFF_ALLOCFAC().equals(displayName)) {
            UIRefPane allocfacPanel =
                    this.getAllocfacPanel(CMDriverLangConst.getSTUFF_ALLOCFAC(), AllocfacEnum.stuff.toIntValue());
            allocfacPanel.showModel();
            AbstractRefModel refModel = allocfacPanel.getRefModel();
            String allocfacId = refModel.getPkValue();
            if (allocfacId != null) {
                String code = refModel.getValue(AllocfacHeadVO.VCODE).toString();
                String name =
                        refModel.getValue(AllocfacHeadVO.VNAME + MultiLangUtil.getCurrentLangSeqSuffix()).toString();
                // 写公式编辑器内容
                eventSource.setEventType(FormulaEventType.INSERT_TO_EDITOR);
                String allocfacText = this.getText(displayName, code, name);
                String allocfacTextCode = this.getCode(formulaItem.getInputSig(), allocfacId);
                eventSource.setNewString(allocfacText);
                eventSource.setNewValueString(allocfacTextCode);
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
    private UIRefPane getAllocfacPanel(String title, int type) {
        UIRefPane allocfacPanel = new UIRefPane();
        allocfacPanel.setRefModel(new AllocfacRefModel());
        allocfacPanel.setMultiSelectedEnabled(false);
        allocfacPanel.getRefModel().setRefTitle(title);

        CMSqlBuilder where = new CMSqlBuilder();
        where.append(AllocfacHeadVO.PK_ORG, this.getLoginContext().getPk_org());
        where.and();
        where.append(AllocfacHeadVO.IALLOCTYPE, type);
        where.appendDr();
        allocfacPanel.setWhereString(where.toString());

        return allocfacPanel;
    }
}
