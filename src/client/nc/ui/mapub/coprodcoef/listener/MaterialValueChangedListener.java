package nc.ui.mapub.coprodcoef.listener;

import nc.ui.bd.ref.RefValueObject;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.ui.pubapp.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.EventCurrentThread;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefHeadVO;

import org.apache.commons.lang.StringUtils;

/**
 * 联产品值改变事件
 *
 * @since 6.36
 * @version 2014-12-11 下午8:18:49
 * @author zhangshyb
 */
public class MaterialValueChangedListener implements nc.ui.pub.beans.ValueChangedListener {

    private UIRefPane refPane;

    private BillForm billForm;

    // private AbstractUIAppModel model;

    public MaterialValueChangedListener(UIRefPane refPane, BillForm billForm, AbstractUIAppModel model) {
        this.refPane = refPane;
        this.billForm = billForm;
        // this.model = model;
    }

    @Override
    public void valueChanged(ValueChangedEvent event) {
        // 事件开始
        EventCurrentThread.start();
        // BillCardPanel cardPanel = (BillCardPanel) this.billForm.getBillCardPanel();
        // // bom版本
        // BillItem bomItem = cardPanel.getHeadItem(CoprodcoefHeadVO.CBOMID);
        String[] newPks = null;
        if (event.getNewValue() instanceof RefValueObject) {
            newPks = ((RefValueObject) event.getNewValue()).getPks();
        }
        else {
            if (event.getNewValue() instanceof String[]) {
                newPks = (String[]) event.getNewValue();
            }
            else {
                newPks = new String[] {
                        (String) event.getNewValue()
                };
            }
        }
        String newPk = null;
        if (newPks != null && newPks.length > 0) {
            newPk = newPks[0];
        }
        String[] oldPks = (String[]) event.getOldValue();
        String oldPk = null;
        if (oldPks != null) {
            oldPk = oldPks[0];
        }
        if (!StringUtils.equals(oldPk, newPk)) {
            // if (this.billForm.getModel().getUiState() == UIState.NOT_EDIT) {
            // this.fireMaterialChangedEvent(oldPk, newPk);
            // }
            // else {
            if (StringUtils.isEmpty(oldPk)) {
                this.fireMaterialChangedEvent(oldPk, newPk);
            }
            else {
                int dialogReturn = 0;
                // 判断是否需要进行事件派发，如果点击“是”才进行事件派发
                // if (bomItem.getValueObject() != null || vos != null) {
                dialogReturn =
                        MessageDialog.showYesNoDlg(this.billForm.getBillCardPanel(), nc.vo.ml.NCLangRes4VoTransl
                                .getNCLangRes().getStrByID("3810006_0", "03810006-0297")/* @res "确认修改" */,
                                nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0299")/*
                                                                                                                    * @res
                                                                                                                    * "是否修改联合体编码，联合体编码修改将会清空生产BOM版本号和表体信息?"
                                                                                                                    */,
                                UIDialog.ID_YES);
                // }
                if (UIDialog.ID_YES == dialogReturn) {
                    this.fireMaterialChangedEvent(oldPk, newPk);
                }
                else if (UIDialog.ID_NO == dialogReturn) {
                    this.refPane.setPK(oldPk);
                }
            }
        }
        // }
        // }

        // 事件结束
        EventCurrentThread.end();
    }

    /**
     * 派发成本BOM成本类型改变事件
     *
     * @author leixjc
     */
    void fireMaterialChangedEvent(String oldCostType, String newCostType) {
        // 清空表体数据
        BillCardPanel cardPanel = (BillCardPanel) this.billForm.getBillCardPanel();
        cardPanel.getBillModel().setBodyDataVO(null);
        // 清空BOM版本号
        cardPanel.setHeadItem(CoprodcoefHeadVO.CBOMID, null);
        // 增行
        this.billForm.getBillCardPanel().addLine();
    }
}
