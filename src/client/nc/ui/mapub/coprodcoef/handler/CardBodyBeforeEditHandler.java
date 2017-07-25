package nc.ui.mapub.coprodcoef.handler;

import nc.bd.framework.base.CMStringUtil;
import nc.bd.framework.db.CMSqlBuilder;
import nc.ui.mapub.costtype.model.CostTypeRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.resa.refmodel.FactorRefModel;
import nc.vo.bd.pub.IPubEnumConst;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefHeadVO;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefItemVO;

/**
 * �����ֶα༭ǰ�¼�������
 * 
 * @since 6.0
 * @version 2014-10-11 ����12:35:49
 * @author zhangshyb
 */
public class CardBodyBeforeEditHandler implements IAppEventHandler<CardBodyBeforeEditEvent> {

    @Override
    public void handleAppEvent(CardBodyBeforeEditEvent e) {
        e.setReturnValue(Boolean.TRUE);

        String key = e.getKey();
        // bom�汾
        // BillItem bomItem = e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CBOMID);
        // // ����·�߰汾
        // BillItem crtidItem = e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CRTID);
        // // ������
        // BillItem materialItem = e.getBillCardPanel().getBodyItem(CoprodcoefHeadVO.CMATERIALID);
        // BillItem materialvItem = e.getBillCardPanel().getBodyItem(CoprodcoefHeadVO.CMATERIALVID);
        String pkOrg = (String) e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.PK_ORG).getValueObject();
        // ����
        if (key.equals(CoprodcoefItemVO.CMATERIALID)) {
            CMaterialHandler handler = new CMaterialHandler();
            handler.beforeEdit(e);

            // if (bomItem.getValueObject() == null && crtidItem.getValueObject() == null) {
            //
            // return;
            // }
            // else if (bomItem.getValueObject() != null && crtidItem.getValueObject() == null) {
            //
            // // ȡ����bom��Ӧ������+��Ʒ����+���ϵ��
            // this.fliterByBom(e);
            //
            // }

            // else if (bomItem.getValueObject() == null && crtidItem.getValueObject() != null) {
            // // ȡ����·��-�������-���ϱ���+��Ʒ����+��������+���ϵ��
            // // this.getDataFormLine(e);
            // this.fliterByLine(e);
            //
            // }
            // else if (bomItem.getValueObject() != null && crtidItem.getValueObject() != null) {
            // try {
            // if (this.checkCostObjIsFZFWByType(materialItem, pkOrg)) {
            // // ȡ����·��-�������-���ϱ���+��Ʒ����+��������+���ϵ��
            // this.fliterByLine(e);
            // }
            // else {
            // // ȡ����bom��Ӧ������+��Ʒ����+���ϵ��
            // this.fliterByBom(e);
            // }
            // }
            // catch (BusinessException e1) {
            // ExceptionUtils.wrappException(e1);
            // }
            // }
        }
        else if (key.equals(CoprodcoefItemVO.CELEMENTID)) {
            BillItem billitem = e.getBillCardPanel().getBodyItem(e.getKey());
            UIRefPane refPane = (UIRefPane) billitem.getComponent();
            String factorchart =
                    String.valueOf(e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.PK_FACTORCHART).getValueObject());
            // ����Pk_factorchart������resa_factorasoa.pk_factorchartȡֵ����ȷ
            FactorRefModel refModel = (FactorRefModel) refPane.getRefModel();
            if (CMStringUtil.isNotEmpty(factorchart) && !factorchart.equalsIgnoreCase("null")) {
                refModel.setPk_factorchart(factorchart);
                refPane.setWhereString("1 = 1");
            }
            else {
                refPane.setWhereString("1 = 2");
                // refPane.setEnabled(false);
            }
            // // ���պ���Ҫ��
            // // FilterFactorCostAndElementRefUtil.factorRefFilterByORGAndEnable(e, billitem);
            // CMSqlBuilder sql = new CMSqlBuilder();
            // sql.append("resa_factorasoa.dr = 0");
            // sql.and();
            // sql.append("resa_factorasoa.pk_factorchart = '" + factorchart + "'");
            // // sql.append("resa_factor.dr = 0");
            // // // sql.and();
            // // // sql.append(FactorVO.getDefaultTableName() + "." + FactorVO.SEALFLAG + " = 'N'");
            // // sql.and();
            // // sql.append(FactorVO.getDefaultTableName() + "." + FactorVO.PK_FACTORCHART + " = '" + factorchart +
            // "'");
            // refPane.setWhereString(sql.toString());
            refPane.setNotLeafSelectedEnabled(false);
            refPane.setMultiSelectedEnabled(true);
            e.setReturnValue(Boolean.TRUE);
        }
        // �ɱ���������֧�ֲ��ն�ѡ
        else if (key.equals(CoprodcoefItemVO.CCOSTCENTERID)) {
            // BillItem item = e.getBillCardPanel().getBodyItem(CoprodcoefItemVO.CCOSTCENTERID);
            // �ɱ����Ĳ���
            // FilterFactorCostAndElementRefUtil.factorRefFilter(e, item);
            // ���ն�ѡ
            UIRefPane refPane = (UIRefPane) e.getBillCardPanel().getBodyItem(e.getKey()).getComponent();
            CMSqlBuilder sql = new CMSqlBuilder();
            sql.append("pk_group" + "='" + e.getContext().getPk_group() + "' ");
            sql.and();
            sql.append("pk_org" + "='" + e.getContext().getPk_org() + "' ");
            sql.and();
            sql.append(" enablestate= " + IPubEnumConst.ENABLESTATE_ENABLE);
            sql.and();
            sql.append("dr = 0 ");
            refPane.setWhereString(sql.toString());
            refPane.setMultiSelectedEnabled(true);
            e.setReturnValue(Boolean.TRUE);
        }
        else if (key.equals(CoprodcoefItemVO.CCOSTTYPEID)) {
            // ���ն�ѡ
            UIRefPane refPane = (UIRefPane) e.getBillCardPanel().getBodyItem(e.getKey()).getComponent();
            CostTypeRefModel refModel = (CostTypeRefModel) refPane.getRefModel();
            refModel.setPk_org(pkOrg);
        }

    }

    // private void fliterByLine(CardBodyBeforeEditEvent e) {
    // AggRtVO aggrtvo = null;
    // BillItem materialItem = e.getBillCardPanel().getBodyItem(CoprodcoefHeadVO.CMATERIALID);
    // try {
    // aggrtvo = this.queryLineVersion(e);
    // }
    // catch (BusinessException e1) {
    // ExceptionUtils.wrappException(e1);
    // }
    // // �ӱ�
    // RtItemVO[] itemVo = (RtItemVO[]) aggrtvo.getChildren(RtItemVO.class);
    // List<String> listMarerialId = new ArrayList<String>();
    // for (int i = 0; i < itemVo.length; i++) {
    // RtOpacVO[] opacVo = itemVo[i].getRtopacitems();
    // if (opacVo != null && opacVo.length > 0) {
    // for (RtOpacVO rtOpacVO : opacVo) {
    // listMarerialId.add(rtOpacVO.getCmaterialid());
    // }
    // }
    // }
    // UIRefPane ref = (UIRefPane) materialItem.getComponent();
    // String[] hesPermissionOrgs = e.getContext().getPkorgs();
    // SqlBuilder sb = new SqlBuilder();
    // sb.append("pk_source", listMarerialId.toArray(new String[listMarerialId.size()]));
    //
    // ref.getRefModel().setWherePart(sb.toString());
    // }

    // private void fliterByBom(CardBodyBeforeEditEvent e) {
    // List<BomOutputsResultVO> outVOLst = this.queryBomVersion(e);
    // BillItem materialItem = e.getBillCardPanel().getBodyItem(CoprodcoefHeadVO.CMATERIALID);
    //
    // if (CMCollectionUtil.isNotEmpty(outVOLst)) {
    // BomOutputsResultVO[] outvos = outVOLst.toArray(new BomOutputsResultVO[0]);
    //
    // String[] materialItemIds = new String[outvos.length];
    // for (int i = 0; i < outvos.length; i++) {
    // materialItemIds[i] = outvos[i].getCmaterialid();
    // }
    //
    // UIRefPane ref = (UIRefPane) materialItem.getComponent();
    // String[] hesPermissionOrgs = e.getContext().getPkorgs();
    // SqlBuilder sb = new SqlBuilder();
    // sb.append("pk_source", materialItemIds);
    //
    // ref.getRefModel().setWherePart(sb.toString());
    //
    // }
    // else {
    // UIRefPane ref = (UIRefPane) materialItem.getComponent();
    // SqlBuilder sb = new SqlBuilder();
    // sb.append(" 1=2 ");
    //
    // ref.getRefModel().setWherePart(sb.toString());
    // }
    // }

    // ��ȡ��������ҳǩ���㹤��ɱ�ֵ
    // private boolean checkCostObjIsFZFWByType(BillItem item, String pkOrg) throws BusinessException {
    // if (item == null) {
    // return false;
    // }

    // ��ȡ��������ҳǩ
    // Map<String, MaterialProdVO> materialMap = null;
    // Map<String, MaterialProdVO> materialMap =
    // NCLocator.getInstance().lookup(IMaterialPubService.class).queryMaterialProduceInfoByPks(new String[] {
    // (String) item.getValueObject()
    // }, pkOrg, new String[] {
    // MaterialProdVO.SFFZFW, MaterialProdVO.SFCBDXTYPE
    // });
    //
    // if (CMMapUtil.isEmpty(materialMap)) {
    // return false;
    // }
    // if (materialMap.containsKey(item.getValueObject())) {
    // MaterialProdVO marprod = materialMap.get(item.getValueObject());
    // if (Boolean.TRUE.equals(marprod.getIsuseroad().booleanValue())) {
    // return true;
    // }
    // }
    // return false;
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
    // private List<BomOutputsResultVO> queryBomVersion(CardBodyBeforeEditEvent e) {
    // List<BomOutputsResultVO> result = new ArrayList<BomOutputsResultVO>();
    //
    // String materialoid =
    // ValueUtils.getString(e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CMATERIALID).getValueObject());
    //
    // String mversion = ValueUtils.getString(e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CBOMID).getName());
    //
    // mversion = ((UIRefPane) e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CBOMID).getComponent()).getRefCode();
    // MapList<String, BomOutputsResultVO> maplst = null;
    // try {
    // maplst =
    // NCLocator.getInstance().lookup(IPubBomServiceForProductcoefficient.class)
    // .queryBomOutput(e.getContext().getPk_group(), e.getContext().getPk_org(), new String[] {
    // materialoid
    // }, mversion);
    // }
    // catch (BusinessException e1) {
    // ExceptionUtils.wrappException(e1);
    // }
    //
    // if (maplst == null || CMMapUtil.isEmpty(maplst.toMap())) {
    // return null;
    // }
    //
    // String uniqueKey = materialoid + mversion;// ����OID+����BOM�汾��
    // for (String key : maplst.keySet()) {
    // if (key.equals(uniqueKey)) {
    // result = maplst.toMap().get(uniqueKey);
    // break;
    // }
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
    // private AggRtVO queryLineVersion(CardBodyBeforeEditEvent e) throws BusinessException {
    // Map<String, AggRtVO> aggRtvoMap = null;
    //
    // String materialoid =
    // ValueUtils.getString(e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CMATERIALID).getValueObject());
    // String materialvid =
    // ValueUtils.getString(e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CMATERIALVID).getValueObject());
    //
    // String mversionid = ValueUtils.getString(e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CRTID).getName());
    // // 48: AbstractRefModel refModel = ((UIRefPane) headItem.getComponent()).getRefModel();
    // String mversion =
    // ((UIRefPane) e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CRTID).getComponent()).getRefCode();
    // // ͷRtHeadVO��RtItemVO��RtOpacVO
    // RtHeadVO headVO = new RtHeadVO();
    // RtItemVO bodyVo = new RtItemVO();
    // String[] headfileds = headVO.getAttributeNames();
    // String[] bodyfileds = bodyVo.getAttributeNames();
    // aggRtvoMap = FIUtil.queryRtByVersionNew(e.getContext().getPk_group(), e.getContext().getPk_org(), new String[] {
    // materialvid
    // }, new String[] {
    // mversion
    // }, headfileds, bodyfileds);
    //
    // if (null == aggRtvoMap || aggRtvoMap.isEmpty()) {
    // return null;
    // }
    // return aggRtvoMap.get(materialoid);
    //
    // }

}
