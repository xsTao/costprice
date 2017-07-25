/**
 *
 */
package nc.ui.mapub.materialpricebase.interceptor;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.actions.ActionInterceptor;
import nc.ui.uif2.editor.IEditor;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;

/**
 * @since v6.3
 * @version 2015年5月11日 下午4:40:48
 * @author zhangshyb
 */
public class HideOrgsInterceptor implements ActionInterceptor {
    /**
     * 卡片界面
     */
    private IEditor editor;

    @Override
    public boolean beforeDoAction(Action action, ActionEvent e) {
        BillCardPanel billCardPanel = ((ShowUpableBillForm) this.getEditor()).getBillCardPanel();
        if (billCardPanel != null) {
            // 业务单元
            BillItem orgItem = billCardPanel.getBillData().getHeadItem(MaterialPriceBaseHeadVO.PK_ORG);
            orgItem.setName(null);
            orgItem.setShow(false);
            orgItem.getComponent().setVisible(false);
        }
        return true;
    }

    @Override
    public void afterDoActionSuccessed(Action action, ActionEvent e) {
        BillCardPanel billCardPanel = ((ShowUpableBillForm) this.getEditor()).getBillCardPanel();
        if (billCardPanel != null) {
            // 业务单元
            BillItem orgItem = billCardPanel.getBillData().getHeadItem(MaterialPriceBaseHeadVO.PK_ORG);
            orgItem.setName(null);
            orgItem.setShow(false);
            orgItem.getComponent().setVisible(false);
        }
    }

    @Override
    public boolean afterDoActionFailed(Action action, ActionEvent e, Throwable ex) {
        BillCardPanel billCardPanel = ((ShowUpableBillForm) this.getEditor()).getBillCardPanel();
        if (billCardPanel != null) {
            // 业务单元
            BillItem orgItem = billCardPanel.getBillData().getHeadItem(MaterialPriceBaseHeadVO.PK_ORG);
            orgItem.setName(null);
            orgItem.setShow(false);
            orgItem.getComponent().setVisible(false);
        }
        return true;
    }

    /**
     * set editor
     *
     * @param editor
     *            the editor to set
     */
    public void setEditor(IEditor editor) {
        this.editor = editor;
    }

    /**
     * get editor
     *
     * @return the editor
     */
    public IEditor getEditor() {
        return this.editor;
    }

}
