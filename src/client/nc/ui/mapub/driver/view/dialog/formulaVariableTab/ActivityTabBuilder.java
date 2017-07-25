package nc.ui.mapub.driver.view.dialog.formulaVariableTab;

import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.Map;

import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.bd.ref.model.ActivityRefModel;
import nc.ui.mapub.driver.view.dialog.AbstractDriverTabBuilder;
import nc.ui.mapub.driver.view.dialog.DriverFormulaEventSource;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.formula.dialog.FormulaEventSource.FormulaEventType;
import nc.vo.bd.bdactivity.entity.BDActivityVO;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.CMDriverParameterEnum;
import nc.vo.ml.MultiLangUtil;
import nc.vo.pub.formulaedit.FormulaItem;

/**
 * <b> ��ҵ </b>
 * <p>
 * ��ϸ��������
 * </p>
 * ��������:2010-3-30
 *
 * @author:wangtf
 */

@SuppressWarnings("serial")
public class ActivityTabBuilder extends AbstractDriverTabBuilder {

    /**
     * ��ҵrefpanel
     */
    UIRefPane activityPanel = null;

    @Override
    public Map<String, FormulaItem> getAllVariableItems() {
        Map<String, FormulaItem> tableItems = new LinkedHashMap<String, FormulaItem>();
        String[][] svalue =
            {
                {
                    CMDriverLangConst.getBOM_ACTIVITY_NUMBER(), CMDriverParameterEnum.BOM_ACTIVITY_NUMBER.getCode()
                },
                {
                    CMDriverLangConst.getRT_ACTIVITY_NUMBER(), CMDriverParameterEnum.RT_ACTIVITY_NUMBER.getCode()
                },
                {
                    CMDriverLangConst.getACTUAL_ACTIVITY_NUMBER(),
                    CMDriverParameterEnum.ACTUAL_ACTIVITY_NUMBER.getCode()
                },
                {
                    CMDriverLangConst.getACTUAL_ACTIVITY_FINISH_NUMBER(),
                    CMDriverParameterEnum.ACTUAL_ACTIVITY_FINISH_NUMBER.getCode()
                }
            };
        for (int i = 0; i < svalue.length; i++) {
            tableItems.put(svalue[i][0], new FormulaItem(svalue[i][0], svalue[i][1], svalue[i][0]));
        }
        return tableItems;
    }

    @Override
    public String getTabName() {
        return CMDriverLangConst.getACTIVITYTITLE();
    }

    @Override
    protected void mouseDoubleClicked(MouseEvent e, FormulaItem formulaItem, DriverFormulaEventSource eventSource) {
        // ����ѡ�����ϵĶԻ���
        String displayName = formulaItem.getDisplayName();
        if (CMDriverLangConst.getBOM_ACTIVITY_NUMBER().equals(displayName)) {
            this.getActivityPanel().showModel();
            AbstractRefModel refModel = this.getActivityPanel().getRefModel();
            String activityId = refModel.getPkValue();
            if (activityId != null) {
                String code = refModel.getValue(BDActivityVO.VACTIVITYCODE).toString();
                String name =
                        refModel.getValue(BDActivityVO.VACTIVITYNAME + MultiLangUtil.getCurrentLangSeqSuffix())
                                .toString();
                // д��ʽ�༭������
                eventSource.setEventType(FormulaEventType.INSERT_TO_EDITOR);
                String activityText = this.getText(CMDriverLangConst.getBOM_ACTIVITY_NUMBER(), code, name);
                String activityTextCode = this.getCode(CMDriverParameterEnum.BOM_ACTIVITY_NUMBER.getCode(), activityId);
                eventSource.setNewString(activityText);
                eventSource.setNewValueString(activityTextCode);
            }
        }
        else if (CMDriverLangConst.getRT_ACTIVITY_NUMBER().equals(displayName)) {
            this.getActivityPanel().showModel();
            AbstractRefModel refModel = this.getActivityPanel().getRefModel();
            String activityId = refModel.getPkValue();
            if (activityId != null) {
                String code = refModel.getValue(BDActivityVO.VACTIVITYCODE).toString();
                String name =
                        refModel.getValue(BDActivityVO.VACTIVITYNAME + MultiLangUtil.getCurrentLangSeqSuffix())
                                .toString();
                // д��ʽ�༭������
                eventSource.setEventType(FormulaEventType.INSERT_TO_EDITOR);
                String activityText = this.getText(CMDriverLangConst.getRT_ACTIVITY_NUMBER(), code, name);
                String activityTextCode = this.getCode(CMDriverParameterEnum.RT_ACTIVITY_NUMBER.getCode(), activityId);
                eventSource.setNewString(activityText);
                eventSource.setNewValueString(activityTextCode);
            }
        }
        else if (CMDriverLangConst.getACTUAL_ACTIVITY_NUMBER().equals(displayName)) {
            this.getActivityPanel().showModel();
            AbstractRefModel refModel = this.getActivityPanel().getRefModel();
            String activityId = refModel.getPkValue();
            if (activityId != null) {
                String code = refModel.getValue(BDActivityVO.VACTIVITYCODE).toString();
                String name =
                        refModel.getValue(BDActivityVO.VACTIVITYNAME + MultiLangUtil.getCurrentLangSeqSuffix())
                                .toString();
                // д��ʽ�༭������
                eventSource.setEventType(FormulaEventType.INSERT_TO_EDITOR);
                String activityText = this.getText(CMDriverLangConst.getACTUAL_ACTIVITY_NUMBER(), code, name);
                String activityTextCode =
                        this.getCode(CMDriverParameterEnum.ACTUAL_ACTIVITY_NUMBER.getCode(), activityId);
                eventSource.setNewString(activityText);
                eventSource.setNewValueString(activityTextCode);
            }
        }
        else if (CMDriverLangConst.getACTUAL_ACTIVITY_FINISH_NUMBER().equals(displayName)) {
            this.getActivityPanel().showModel();
            AbstractRefModel refModel = this.getActivityPanel().getRefModel();
            String activityId = refModel.getPkValue();
            if (activityId != null) {
                String code = refModel.getValue(BDActivityVO.VACTIVITYCODE).toString();
                String name =
                        refModel.getValue(BDActivityVO.VACTIVITYNAME + MultiLangUtil.getCurrentLangSeqSuffix())
                        .toString();
                // д��ʽ�༭������
                eventSource.setEventType(FormulaEventType.INSERT_TO_EDITOR);
                String activityText = this.getText(CMDriverLangConst.getACTUAL_ACTIVITY_FINISH_NUMBER(), code, name);
                String activityTextCode =
                        this.getCode(CMDriverParameterEnum.ACTUAL_ACTIVITY_FINISH_NUMBER.getCode(), activityId);
                eventSource.setNewString(activityText);
                eventSource.setNewValueString(activityTextCode);
            }
        }
        else {
            super.mouseDoubleClicked(e, formulaItem, eventSource);
        }
    }

    /**
     * ��ȡ����panel
     *
     * @return ����panel
     */
    private UIRefPane getActivityPanel() {
        if (this.activityPanel == null) {
            this.activityPanel = new UIRefPane();
            this.activityPanel.setRefModel(new ActivityRefModel());
            this.activityPanel.getRefModel().setRefTitle(CMDriverLangConst.getACTIVITY_ITEM());
            this.activityPanel.setMultiSelectedEnabled(false);
        }
        ((ActivityRefModel) this.activityPanel.getRefModel()).setPk_orgs(new String[] {
            this.getLoginContext().getPk_org()
        });
        return this.activityPanel;
    }

}
