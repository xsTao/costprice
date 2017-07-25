package nc.ui.mapub.allocfac.handler;

import nc.ui.bd.pub.handler.CMBasedocEventHandler;
import nc.ui.cmpub.framework.view.CMBillFormFacade;
import nc.ui.mapub.allocfac.util.AllocfacBillConst;
import nc.ui.mapub.allocfac.util.AllocfacViewUtil;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.mapub.allocfac.util.AllocfacItemUtil;
import nc.vo.mapub.allocfac.util.CMMLangConstAllocfac;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ��Ƭ����༭�¼�����
 *
 * @author mmauto.liubq
 */

public class CardHeadTailAfterEditHandler extends CMBasedocEventHandler implements
IAppEventHandler<CardHeadTailAfterEditEvent> {
    private BillManageModel model;

    private CMBillFormFacade editor;

    public CMBillFormFacade getEditor() {
        return this.editor;
    }

    public void setEditor(CMBillFormFacade editor) {
        this.editor = editor;
    }

    public BillManageModel getModel() {
        return this.model;
    }

    public void setModel(BillManageModel model) {
        this.model = model;
    }

    @Override
    public void handleAppEvent(CardHeadTailAfterEditEvent e) {
        if (AllocfacHeadVO.IALLOCTYPE.equals(e.getKey())) {
            /** �жϱ������еĳɱ����ı����ϵ�� */
            AllocfacItemVO[] itemVOs =
                    (AllocfacItemVO[]) this.editor.getBillCardPanel().getBillModel()
                    .getBodyValueVOs(AllocfacItemVO.class.getName());
            boolean isFilled = false;
            for (int i = 0; i < itemVOs.length; i++) {
                if (itemVOs[i].getCmaterialid() != null || itemVOs[i].getNfactor() != null
                        || itemVOs[i].getCcostcenterid() != null || itemVOs[i].getCmarcostclassid() != null
                        || itemVOs[i].getCactivityid() != null) {
                    isFilled = true;
                    break;
                }
            }
            if (isFilled) {
                // ��ʾ�����ķ������ݽ���ձ����У��Ƿ������
                int intResult =
                        MessageDialog.showYesNoDlg(this.editor, CMMLangConstAllocfac.getINFOUPDATEALLOCCONTENT_HINT(),
                                CMMLangConstAllocfac.getINFOUPDATEALLOCCONTENT());
                if (intResult == UIDialog.ID_YES) {
                    // ѡ���ǡ�����ձ�������
                    this.editor.getBillCardPanel().getBillModel().clearBodyData();
                }
                else {
                    // ѡ�񡰷񡱣�����ԭ��ֵ
                    Object oldValue = e.getOldValue();
                    this.editor.getBillCardPanel().setHeadItem(AllocfacHeadVO.IALLOCTYPE, oldValue);
                    return;
                }
            }
            // ��ձ�������
            this.editor.getBillCardPanel().getBillModel().clearBodyData();
            // ���ݷ������ݵ�ѡ����±�������Ӧ�еĿɱ༭״̬
            this.updateBodyStatusByAllocContent();
        }
    }

    /**
     * ���ݷ������ݵ�ѡ����±�������Ӧ�еĿɱ༭״̬
     */
    public void updateBodyStatusByAllocContent() {
        BillCardPanel nowBillCardPanel = this.getEditor().getBillCardPanel();
        // �������������У�����ϵ���У�
        String tableCode = AllocfacBillConst.ALLOCFAC_TABLECODE;
        String[] itemKeys = AllocfacBillConst.HIDEITEMNAMES;
        AllocfacViewUtil.setBillCardPanelBodyTableColumsVisible(nowBillCardPanel, tableCode, itemKeys, false);

        // �������͵�ѡ������ĳ�п���ʾ
        BillItem nowItem = nowBillCardPanel.getHeadItem(AllocfacHeadVO.IALLOCTYPE);
        if (nowItem.getValueObject() == null) {
            ExceptionUtils.wrappBusinessException(CMMLangConstAllocfac.getALLOCCONTENT_ERROR());
        }
        String[] showColumKeys = AllocfacItemUtil.getAllocContentColumByContent(nowItem.getValueObject().toString());
        AllocfacViewUtil.setBillCardPanelBodyTableColumsVisible(nowBillCardPanel, tableCode, showColumKeys, true);

        // �Զ�����һ�еĲ���������������ͣ��������ʾ�л�䣬������գ���Ӧ��Ӧ���Զ�����һ��
        int count = this.getEditor().getBillCardPanel().getBillModel().getRowCount();
        this.getEditor().getBillCardPanel().getBodyPanel().addLine();
        Object group = this.getEditor().getBillCardPanel().getHeadItem(AllocfacItemVO.PK_GROUP).getValueObject();
        Object org = this.getEditor().getBillCardPanel().getHeadItem(AllocfacItemVO.PK_ORG).getValueObject();
        Object orgv = this.getEditor().getBillCardPanel().getHeadItem(AllocfacItemVO.PK_ORG_V).getValueObject();

        this.getEditor().getBillCardPanel().setBodyValueAt(group, count, AllocfacItemVO.PK_GROUP);
        this.getEditor().getBillCardPanel().setBodyValueAt(org, count, AllocfacItemVO.PK_ORG);
        this.getEditor().getBillCardPanel().setBodyValueAt(orgv, count, AllocfacItemVO.PK_ORG_V);
        // this.getEditor().updateUI();
    }

    @Override
    public void initMap() {
    }
}
