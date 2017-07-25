package nc.ui.mapub.coprodcoef.handler;

import java.util.ArrayList;
import java.util.List;

import nc.bd.framework.base.CMStringUtil;
import nc.bd.framework.base.CMValueCheck;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefHeadVO;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefItemVO;

/**
 * ���ݱ�ͷ��β�ֶα༭���¼�������
 *
 * @since 6.1
 * @version 2011-10-7 ����02:52:22
 * @author ligq
 */
public class CardHeadTailAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent> {
    @Override
    public void handleAppEvent(CardHeadTailAfterEditEvent e) {
        String key = e.getKey();
        // if (CoprodcoefHeadVO.CBOMID.equals(e.getKey())) {
        // try {
        // // bom�汾
        // BillItem bomItem = e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CBOMID);
        // if (bomItem.getValueObject() != null) {
        // this.getDataFormBom(e);
        // }
        // }
        // catch (BusinessException ex) {
        // ExceptionUtils.wrappException(ex);
        // }
        // }
        /**
         * Ҫ����ϵ�ı�ʱ��Ҫ�ر�Ͷ��ձ�����Ϊ��
         */
        if (CoprodcoefHeadVO.PK_ELEMENTSYSTEM.equals(e.getKey())) {
            e.getBillCardPanel().setHeadItem(CoprodcoefHeadVO.PK_FACTORCHART, null);
            int rowCount = e.getBillCardPanel().getRowCount();
            List<Integer> delLine = new ArrayList<Integer>();
            for (int i = 0; i < rowCount; i++) {
                String celementid =
                        String.valueOf(e.getBillCardPanel().getBillModel()
                                .getValueObjectAt(i, CoprodcoefItemVO.CELEMENTID));
                // �пմ���
                if (CMStringUtil.isNotEmpty(celementid) && !celementid.equalsIgnoreCase("null")
                        && !celementid.isEmpty()) {
                    // ��������к���Ҫ�ز�Ϊ�յ�������
                    // e.getBillCardPanel().getBillModel().clearRowData(i, null);
                    delLine.add(Integer.valueOf(i));
                }
            }
            if (!delLine.isEmpty()) {
                int[] toDel = new int[delLine.size()];
                for (Integer integer : delLine) {
                    int num = 0;
                    toDel[num] = integer.intValue();
                }
                e.getBillCardPanel().getBillModel().delLine(toDel);
            }
            if (CMValueCheck.isEmpty(e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.PK_ELEMENTSYSTEM)
                    .getValueObject())) {
                e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.PK_FACTORCHART).setEdit(false);
                e.getBillCardPanel().getBodyItem(CoprodcoefItemVO.CELEMENTID).setEdit(false);
            }
            else {
                e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.PK_FACTORCHART).setEdit(true);
                // e.getBillCardPanel().getBodyItem(CoprodcoefItemVO.CELEMENTID).setEdit(true);
            }
        }
        /**
         * Ҫ�ر�ı�ʱ�����ձ�����Ϊ��
         */
        else if (CoprodcoefHeadVO.PK_FACTORCHART.equals(e.getKey())) {
            int rowCount = e.getBillCardPanel().getRowCount();
            List<Integer> delLine = new ArrayList<Integer>();
            for (int i = 0; i < rowCount; i++) {
                String celementid =
                        String.valueOf(e.getBillCardPanel().getBillModel()
                                .getValueObjectAt(i, CoprodcoefItemVO.CELEMENTID));
                // �пմ���
                if (CMStringUtil.isNotEmpty(celementid) && !celementid.equalsIgnoreCase("null")
                        && !celementid.isEmpty()) {
                    // ��������к���Ҫ�ز�Ϊ�յ�������
                    // e.getBillCardPanel().getBillModel().clearRowData(i, null);
                    delLine.add(Integer.valueOf(i));
                }
            }
            if (!delLine.isEmpty()) {
                int[] toDel = new int[delLine.size()];
                for (Integer integer : delLine) {
                    int num = 0;
                    toDel[num] = integer.intValue();
                }
                e.getBillCardPanel().getBillModel().delLine(toDel);
            }
            if (CMValueCheck
                    .isEmpty(e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.PK_FACTORCHART).getValueObject())) {
                e.getBillCardPanel().getBodyItem(CoprodcoefItemVO.CELEMENTID).setEdit(false);
            }
            else {
                e.getBillCardPanel().getBodyItem(CoprodcoefItemVO.CELEMENTID).setEdit(true);
            }
        }
        else if (CMStringUtil.isEqual(key, CoprodcoefHeadVO.CMATERIALVID)) {
            // ����������л�����ձ�ͷ���������ֶ�
            e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CBOMID).setValue(null);
            e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CRTID).setValue(null);
            e.getBillCardPanel().getBillModel().setBodyDataVO(null);
            // e.getBillCardPanel().getBillModel().setBodyDataVO(new CoprodcoefItemVO[] {
            // new CoprodcoefItemVO()
            // });
        }

    }

    // ����BOM�汾ȡ��
    // private void getDataFormBom(CardHeadTailAfterEditEvent e) throws BusinessException {
    // List<BomOutputsResultVO> outVOLst = this.queryBomVersion(e);
    // if (CMCollectionUtil.isEmpty(outVOLst)) {
    // return;
    // }
    // BomOutputsResultVO[] outvos = outVOLst.toArray(new BomOutputsResultVO[0]);
    // if (outvos != null && outvos.length > 0) {
    // for (BomOutputsResultVO bomOutputsVO : outvos) {
    // BillModel model = e.getBillCardPanel().getBillModel();
    // model.addLine();
    // int row = model.getRowCount() - 1;
    // CoprodcoefItemVO vo = new CoprodcoefItemVO();
    // vo.setCmaterialid(bomOutputsVO.getCmaterialid());
    // vo.setCmaterialvid(bomOutputsVO.getCmaterialvid());
    // vo.setIproducttype(bomOutputsVO.getFoutputtype());
    // if (bomOutputsVO.getFoutputtype().equals(Integer.valueOf(1))) {
    // vo.setNrelcoefficient(UFDouble.ONE_DBL);// ����ƷĬ��ϵ��Ϊ1��1��2��3��
    // }
    // else if (bomOutputsVO.getFoutputtype().equals(Integer.valueOf(2))) {
    // vo.setNrelcoefficient(bomOutputsVO.getNbyprodsptnum());
    // }
    // else {
    // return;
    // }
    //
    // model.setBodyRowVO(vo, row);
    // model.loadLoadRelationItemValue();
    // }
    // }
    // else {
    // return;
    // }
    //
    // }

    // // ���չ��հ汾ȡ��
    // private void getDataFormLine(CardHeadTailAfterEditEvent e) throws BusinessException {
    // // ��ձ�������
    // e.getBillCardPanel().getBillModel().setBodyDataVO(null);
    //
    // AggRtVO aggrtvo = this.queryLineVersion(e);
    // if (aggrtvo == null) {
    // return;
    // }
    // // �ӱ�
    // RtItemVO[] itemVo = (RtItemVO[]) aggrtvo.getChildren(RtItemVO.class);
    // // �ӱ�������
    // String[] cwkid = new String[itemVo.length];
    // // ��������
    // List<RtOpacVO> listRtOpacVO = new ArrayList<RtOpacVO>();
    // // �����-����������
    // Map<String, String> itemVono = new HashMap<String, String>();
    // for (int i = 0; i < itemVo.length; i++) {
    // itemVono.put(itemVo[i].getVprocessno(), itemVo[i].getCwkid());
    // cwkid[i] = itemVo[i].getCwkid();
    // RtOpacVO[] opacVo = itemVo[i].getRtopacitems();
    // if (opacVo != null && opacVo.length > 0) {
    // for (RtOpacVO rtOpacVO : opacVo) {
    // listRtOpacVO.add(rtOpacVO);
    // }
    // }
    // }
    // // ����������ɱ����Ķ�Ӧ��ϵ
    // Map<String, String> workToCoscenter = FIUtil.getAllCostCenterByOrgAndWkid(e.getContext().getPk_org(), cwkid);
    //
    // BillModel model = e.getBillCardPanel().getBillModel();
    // model.addLine();
    // int rowMain = model.getRowCount() - 1;
    // CoprodcoefItemVO voMain = new CoprodcoefItemVO();
    // voMain.setCmaterialid(e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CMATERIALID).getValueObject()
    // .toString());
    // voMain.setCmaterialvid(e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CMATERIALVID).getValueObject()
    // .toString());
    // voMain.setIproducttype(Integer.valueOf(1));
    // voMain.setNrelcoefficient(UFDouble.ONE_DBL);
    // // ������ȷ��ȡֵ
    // // voMain.setCcostcenterid(workToCoscenter.get(itemVono.get(onertOpacVO.getVprocessno())));// vprocessno�����
    // model.setBodyRowVO(voMain, rowMain);
    // model.loadLoadRelationItemValue();
    //
    // if (listRtOpacVO.size() > 0) {
    // for (RtOpacVO onertOpacVO : listRtOpacVO) {
    // if (onertOpacVO.getFitemtype().equals(Integer.valueOf(1))) {
    // continue;// ���˵�����Ʒ
    // }
    // model.addLine();
    // int row = model.getRowCount() - 1;
    // CoprodcoefItemVO vo = new CoprodcoefItemVO();
    // vo.setCmaterialid(onertOpacVO.getCmaterialid());
    // vo.setCmaterialvid(onertOpacVO.getCmaterialvid());
    // vo.setIproducttype(onertOpacVO.getFitemtype());
    //
    // if (onertOpacVO.getFitemtype().equals(Integer.valueOf(1))) {
    // // ����ƷĬ��ϵ��Ϊ1��1��2��3��
    // vo.setNrelcoefficient(UFDouble.ONE_DBL);
    // }
    // else {
    // vo.setNrelcoefficient(onertOpacVO.getNbyprodsptnum());
    // }
    // vo.setCcostcenterid(workToCoscenter.get(itemVono.get(onertOpacVO.getVprocessno())));// vprocessno�����
    // model.setBodyRowVO(vo, row);
    // model.loadLoadRelationItemValue();
    // }
    // }
    // else {
    // return;
    // }
    //
    // }

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
    // private List<BomOutputsResultVO> queryBomVersion(CardHeadTailAfterEditEvent e) throws BusinessException {
    // List<BomOutputsResultVO> result = new ArrayList<BomOutputsResultVO>();
    //
    // String materialid =
    // ValueUtils.getString(e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CMATERIALID).getValueObject());
    // String materialvid =
    // ValueUtils.getString(e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CMATERIALVID).getValueObject());
    // String mversion = ValueUtils.getString(e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CBOMID).getName());
    // mversion = ((UIRefPane) e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CBOMID).getComponent()).getRefCode();
    // MapList<String, BomOutputsResultVO> maplst =
    // NCLocator.getInstance().lookup(IPubBomServiceForProductcoefficient.class)
    // .queryBomOutput(e.getContext().getPk_group(), e.getContext().getPk_org(), new String[] {
    // materialid
    // }, mversion);
    // if (maplst == null || CMMapUtil.isEmpty(maplst.toMap())) {
    // return null;
    // }
    // UIRefPane ref = (UIRefPane) e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CBOMID).getComponent();
    // BomVerRefModel bomRefModel = (BomVerRefModel) ref.getRefModel();
    // Object materialVersion = bomRefModel.getValue(bomRefModel.getFieldCode()[2]);
    // String uniqueKey = "";// ����OID+����VID+����BOM�汾��
    // if (materialVersion == null || materialVersion.toString().trim() == "") {
    // uniqueKey = materialid + "null" + mversion;
    // }
    // else {
    // uniqueKey = materialid + materialvid + mversion;
    // }
    //
    // for (String key : maplst.keySet()) {
    // if (key.equals(uniqueKey)) {
    // result.addAll(maplst.toMap().get(uniqueKey));
    // }
    //
    // }
    // return result;
    //
    // }

    /**
     * ��ѯ ����·��
     *
     * @param CardHeadTailAfterEditEvent
     * @return �汾aggvo
     * @throws BusinessException
     */
    // private AggRtVO queryLineVersion(CardHeadTailAfterEditEvent e) throws BusinessException {
    // Map<String, AggRtVO> aggRtvoMap = null;
    // String materialoid =
    // ValueUtils.getString(e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CMATERIALID).getValueObject());
    // String materialvid =
    // ValueUtils.getString(e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CMATERIALVID).getValueObject());
    // String mversionid = ValueUtils.getString(e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CRTID).getName());
    // String mversion =
    // ((UIRefPane) e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CRTID).getComponent()).getRefCode();
    // // ͷRtHeadVO��RtItemVO��RtOpacVO
    // RtHeadVO headVO = new RtHeadVO();
    // RtItemVO bodyVo = new RtItemVO();
    // String[] headfileds = headVO.getAttributeNames();
    // List<String> list = new ArrayList<String>();
    // if (headfileds != null && headfileds.length > 0) {
    // for (String str : headfileds) {
    // if (!str.equals("pseudocolumn") && !str.equals("newbomversion") && !str.equals("cmaterialname")) {
    // list.add(str);
    // }
    // }
    // }
    // String[] newheadfileds = list.toArray(new String[list.size()]);
    // String[] bodyfileds = bodyVo.getAttributeNames();
    // List<String> listBody = new ArrayList<String>();
    // if (bodyfileds != null && bodyfileds.length > 0) {
    // for (String str : bodyfileds) {
    // if (!str.equals("pseudocolumn") && !str.equals("newbomversion")) {
    // listBody.add(str);
    // }
    // }
    // }
    // String[] newbodyfileds = listBody.toArray(new String[listBody.size()]);
    // aggRtvoMap = FIUtil.queryRtByVersionNew(e.getContext().getPk_group(), e.getContext().getPk_org(), new String[] {
    // materialvid
    // }, new String[] {
    // mversion
    // }, newheadfileds, newbodyfileds);
    //
    // if (null == aggRtvoMap || aggRtvoMap.isEmpty()) {
    // return null;
    // }
    // return aggRtvoMap.get(materialoid + e.getContext().getPk_org());
    //
    // }

}
