package nc.ui.mapub.coprodcoef.listener;

import java.util.Map;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMMapUtil;
import nc.bs.framework.common.NCLocator;
import nc.pubitf.bd.bom.IPubBomService;
import nc.ui.bd.ref.RefValueObject;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.EventCurrentThread;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.vo.bd.bom.bom0202.entity.AggBomVO;
import nc.vo.bd.bom.bom0202.entity.BomOutputsVO;
import nc.vo.bd.bom.bom0202.paramvo.cm.BomOutputsResultVO;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefHeadVO;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * Bom�汾ֵ�ı��¼�
 *
 * @since 6.36
 * @version 2014-12-11 ����8:18:16
 * @author zhangshyb
 */
public class BomIDValueChangedListener implements nc.ui.pub.beans.ValueChangedListener {

    private UIRefPane refPane;

    private BillForm billForm;

    // private AbstractUIAppModel model;

    public BomIDValueChangedListener(UIRefPane refPane, BillForm billForm, AbstractUIAppModel model) {
        this.refPane = refPane;
        this.billForm = billForm;
        // this.model = model;
    }

    @Override
    public void valueChanged(ValueChangedEvent event) {
        // �¼���ʼ
        EventCurrentThread.start();

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
            // this.fireBomIDChangedEvent(oldPk, newPk);
            // }
            // else {
            if (StringUtils.isEmpty(oldPk)) {
                this.fireBomIDChangedEvent(oldPk, newPk);
            }
            else {
                int dialogReturn = 0;
                BillCardPanel cardPanel = (BillCardPanel) this.billForm.getBillCardPanel();
                // �ж��Ƿ���Ҫ�����¼��ɷ������������ǡ��Ž����¼��ɷ�
                if (cardPanel.getBillData().getBillTableVos() != null) {
                    dialogReturn =
                            MessageDialog
                            .showYesNoDlg(
                                    this.billForm.getBillCardPanel(),
                                    nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                                            "03810006-0297")/* @res "ȷ���޸�" */,
                                            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                                                    "03810006-0298")/* @res "�Ƿ��޸�����BOM�汾�ţ�����BOM�汾�޸ĺ󽫻���±�����Ϣ?" */,
                                                    UIDialog.ID_YES);
                    if (UIDialog.ID_YES == dialogReturn) {
                        this.fireBomIDChangedEvent(oldPk, newPk);
                    }
                    else if (UIDialog.ID_NO == dialogReturn) {
                        this.refPane.setPK(oldPk);
                    }
                }
                else {
                    this.fireBomIDChangedEvent(oldPk, newPk);
                }
            }
            // }
        }

        // �¼�����
        EventCurrentThread.end();
    }

    /**
     * �ɷ��ɱ�BOMID�ɱ����͸ı��¼�
     *
     * @author zhangshyb
     */
    void fireBomIDChangedEvent(String oldPk, String newPk) {
        // ��ձ�������
        BillCardPanel cardPanel = (BillCardPanel) this.billForm.getBillCardPanel();
        cardPanel.getBillModel().setBodyDataVO(null);
        try {
            this.getDataFormBom(cardPanel);
            // ����
            // this.billForm.getBillCardPanel().addLine();
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
    }

    // ����BOM�汾ȡ��
    private void getDataFormBom(BillCardPanel cardPanel) throws BusinessException {
        UIRefPane bomPane = (UIRefPane) cardPanel.getHeadItem(CoprodcoefHeadVO.CBOMID).getComponent();
        String bomPK = bomPane.getRefModel().getPkValue();
        BomOutputsResultVO[] vos = this.queryBomVersion(bomPK);
        if (CMArrayUtil.isEmpty(vos)) {
            return;
        }
        if (vos != null && vos.length > 0) {
            BillModel model = cardPanel.getBillModel();
            model.addLine();
            int row = 0;
            for (BomOutputsResultVO bomOutputsVO : vos) {
                row = model.getRowCount() - 1;
                CoprodcoefItemVO vo = new CoprodcoefItemVO();
                vo.setCmaterialid(bomOutputsVO.getCmaterialid());
                vo.setCmaterialvid(bomOutputsVO.getCmaterialvid());
                vo.setIproducttype(bomOutputsVO.getFoutputtype());
                // ��1��2��3
                if (bomOutputsVO.getFoutputtype().equals(Integer.valueOf(2))) {
                    vo.setNrelcoefficient(bomOutputsVO.getNbyprodsptnum());
                }
                else {
                    continue;
                }
                model.setBodyRowVO(vo, row);
                model.addLine();
                model.loadLoadRelationItemValue();
            }
        }
        else {
            return;
        }

    }

    /**
     * ��ѯ����BOM
     *
     * @param cardPanel
     *            BillCardPanel
     * @param cbomid
     *            bom����
     * @return �汾��
     * @throws BusinessException
     */
    private BomOutputsResultVO[] queryBomVersion(String bomPK) throws BusinessException {
        Map<String, AggBomVO> pk2VOMap =
                NCLocator.getInstance().lookup(IPubBomService.class).queryBomFieldsByPk(new String[] {
                        bomPK
                }, null, false);
        if (CMMapUtil.isEmpty(pk2VOMap)) {
            return null;
        }
        BomOutputsVO[] itemVOs = (BomOutputsVO[]) pk2VOMap.get(bomPK).getChildren(BomOutputsVO.class);
        if (CMArrayUtil.isEmpty(itemVOs)) {
            return null;
        }
        BomOutputsResultVO[] vos = new BomOutputsResultVO[itemVOs.length];
        int i = 0;
        for (BomOutputsVO itemVO : itemVOs) {
            BomOutputsResultVO vo = new BomOutputsResultVO();
            vo.setCmaterialid(itemVO.getCmaterialid());
            vo.setCmaterialvid(itemVO.getCmaterialvid());
            vo.setFoutputtype(itemVO.getFoutputtype());
            vo.setNbyprodsptnum(itemVO.getNbyprodsptnum());
            vos[i] = vo;
            i++;
        }
        return vos;
    }

}
