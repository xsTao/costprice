package nc.ui.mapub.materialpricebase.dialog.priceSources.action.head;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import nc.bd.framework.base.CMValueCheck;
import nc.ui.mapub.materialpricebase.dialog.priceSources.util.PriceSourcesConfirmService;
import nc.ui.mapub.materialpricebase.dialog.priceSources.util.PriceSourcesEnumMap;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.FuncletDialog;
import nc.ui.uif2.NCAction;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceSourcesBodyVO;

/**
 * ȡ���ײ���ȷ����ť
 * 
 * @since v6.5
 * @version 2014-5-16 ����3:41:28
 * @author zhangchd
 */

public class PriceSourcesConfirmAction extends NCAction {

    private static final long serialVersionUID = 8927615540322461112L;

    private FuncletDialog dialog;

    private BillForm dialogBillForm;

    private BillForm billform;

    private PriceSourcesConfirmService service;

    @Override
    protected boolean isActionEnable() {
        return true;
    }

    /**
     * Ĭ�Ϲ��캯��
     */
    public PriceSourcesConfirmAction() {
        // @res "ȷ��(Y)
        // this.setBtnName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("50010006_0", "050010006-0198"));
        this.setBtnName(CMMLangConstMaterialPriceBase.getMSG3());
        this.setCode("CONFIRM_CHILD_WINDOWS");

        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, Event.ALT_MASK));
        this.putValue(Action.SHORT_DESCRIPTION, CMMLangConstMaterialPriceBase.getMSG3());
        // "ȷ��(ALT+Y)"
    }

    @Override
    public void doAction(ActionEvent e) throws Exception {
        this.getDialogBillForm().getBillCardPanel().stopEditing();
        // ������Ϣ
        String erroInfo = this.getService().getConfirmBeforeErroInfo();
        if (CMValueCheck.isEmpty(erroInfo)) {
            // ��ȡ�Ի�����������
            MaterialPriceSourcesBodyVO[] materialDatas =
                    (MaterialPriceSourcesBodyVO[]) this.getDialogBillForm().getBillCardPanel().getBillData()
                            .getBodyValueVOs("itempks", MaterialPriceSourcesBodyVO.class.getName());

            StringBuffer vpriceSourcesCode = new StringBuffer();
            StringBuffer vpriceSourcesName = new StringBuffer();

            if (materialDatas.length > 0) {

                for (MaterialPriceSourcesBodyVO iterm : materialDatas) {
                    if (CMValueCheck.isNotEmpty(iterm.getVpricesource())) {
                        vpriceSourcesCode.append(iterm.getVpricesource());
                        if (CMValueCheck.isNotEmpty(iterm.getPk_org())) {
                            vpriceSourcesCode.append("[");
                            vpriceSourcesCode.append(iterm.getPk_org());
                            vpriceSourcesCode.append("]");
                        }
                        vpriceSourcesCode.append(",");

                        vpriceSourcesName
                                .append(PriceSourcesEnumMap.getPriceSourcesEnum().get(iterm.getVpricesource()));
                        if (CMValueCheck.isNotEmpty(iterm.getCorgcode())) {
                            vpriceSourcesName.append("[");
                            vpriceSourcesName.append(iterm.getCorgcode());
                            vpriceSourcesName.append("]");
                        }
                        vpriceSourcesName.append(",");
                    }
                }

                if (CMValueCheck.isNotEmpty(vpriceSourcesCode.toString())
                        && CMValueCheck.isNotEmpty(vpriceSourcesName.toString())) {
                    vpriceSourcesCode.deleteCharAt(vpriceSourcesCode.lastIndexOf(","));
                    vpriceSourcesName.deleteCharAt(vpriceSourcesName.lastIndexOf(","));
                }
            }
            // this.getBillform().getBillCardPanel()
            // .setHeadItem(MaterialPriceBaseBodyVO.VPRICESOURCE, vpriceSourcesName.toString());
            this.getBillform().getBillCardPanel()
                    .setHeadItem(MaterialPriceBaseBodyVO.VPRICESOURCENUM, vpriceSourcesCode.toString());

            // ��ͷ�۸���Դ�༭���¼�
            BillItem materialHeadrefItem =
                    this.getBillform().getBillCardPanel().getHeadItem(MaterialPriceBaseHeadVO.VPRICESOURCE);
            UIRefPane refPane = (UIRefPane) materialHeadrefItem.getComponent();
            refPane.setValueObjFireValueChangeEvent(vpriceSourcesName.toString());

            this.getBillform().getBillCardPanel().stopEditing();

            // �رնԻ���
            this.getDialog().closeOK();
        }
        else {
            // this.getDialog().setContentText(erroInfo);
            MessageDialog.showWarningDlg(this.getDialog(), CMMLangConstMaterialPriceBase.getMSG4(), erroInfo);
        }

    }

    public FuncletDialog getDialog() {
        return this.dialog;
    }

    public void setDialog(FuncletDialog dialog) {
        this.dialog = dialog;
    }

    public BillForm getBillform() {
        return this.billform;
    }

    public void setBillform(BillForm billform) {
        this.billform = billform;
    }

    public BillForm getDialogBillForm() {
        return this.dialogBillForm;
    }

    public void setDialogBillForm(BillForm dialogBillForm) {
        this.dialogBillForm = dialogBillForm;
    }

    public PriceSourcesConfirmService getService() {
        return this.service;
    }

    public void setService(PriceSourcesConfirmService service) {
        this.service = service;
    }

}