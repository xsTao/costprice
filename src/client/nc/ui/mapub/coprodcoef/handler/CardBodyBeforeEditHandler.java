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
 * 表体字段编辑前事件处理类
 * 
 * @since 6.0
 * @version 2014-10-11 下午12:35:49
 * @author zhangshyb
 */
public class CardBodyBeforeEditHandler implements IAppEventHandler<CardBodyBeforeEditEvent> {

    @Override
    public void handleAppEvent(CardBodyBeforeEditEvent e) {
        e.setReturnValue(Boolean.TRUE);

        String key = e.getKey();
        // bom版本
        // BillItem bomItem = e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CBOMID);
        // // 工艺路线版本
        // BillItem crtidItem = e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CRTID);
        // // 联合体
        // BillItem materialItem = e.getBillCardPanel().getBodyItem(CoprodcoefHeadVO.CMATERIALID);
        // BillItem materialvItem = e.getBillCardPanel().getBodyItem(CoprodcoefHeadVO.CMATERIALVID);
        String pkOrg = (String) e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.PK_ORG).getValueObject();
        // 物料
        if (key.equals(CoprodcoefItemVO.CMATERIALID)) {
            CMaterialHandler handler = new CMaterialHandler();
            handler.beforeEdit(e);

            // if (bomItem.getValueObject() == null && crtidItem.getValueObject() == null) {
            //
            // return;
            // }
            // else if (bomItem.getValueObject() != null && crtidItem.getValueObject() == null) {
            //
            // // 取生产bom对应子项编号+产品类型+相对系数
            // this.fliterByBom(e);
            //
            // }

            // else if (bomItem.getValueObject() == null && crtidItem.getValueObject() != null) {
            // // 取工艺路线-工序耗料-物料编码+产品类型+工作中心+相对系数
            // // this.getDataFormLine(e);
            // this.fliterByLine(e);
            //
            // }
            // else if (bomItem.getValueObject() != null && crtidItem.getValueObject() != null) {
            // try {
            // if (this.checkCostObjIsFZFWByType(materialItem, pkOrg)) {
            // // 取工艺路线-工序耗料-物料编码+产品类型+工作中心+相对系数
            // this.fliterByLine(e);
            // }
            // else {
            // // 取生产bom对应子项编号+产品类型+相对系数
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
            // 设置Pk_factorchart，否则resa_factorasoa.pk_factorchart取值不正确
            FactorRefModel refModel = (FactorRefModel) refPane.getRefModel();
            if (CMStringUtil.isNotEmpty(factorchart) && !factorchart.equalsIgnoreCase("null")) {
                refModel.setPk_factorchart(factorchart);
                refPane.setWhereString("1 = 1");
            }
            else {
                refPane.setWhereString("1 = 2");
                // refPane.setEnabled(false);
            }
            // // 参照核素要素
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
        // 成本中心设置支持参照多选
        else if (key.equals(CoprodcoefItemVO.CCOSTCENTERID)) {
            // BillItem item = e.getBillCardPanel().getBodyItem(CoprodcoefItemVO.CCOSTCENTERID);
            // 成本中心参照
            // FilterFactorCostAndElementRefUtil.factorRefFilter(e, item);
            // 参照多选
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
            // 参照多选
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
    // // 子表
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

    // 获取物料生产页签核算工序成本值
    // private boolean checkCostObjIsFZFWByType(BillItem item, String pkOrg) throws BusinessException {
    // if (item == null) {
    // return false;
    // }

    // 获取物料生产页签
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
     * 查询生产BOM
     * 
     * @param cardPanel
     *            BillCardPanel
     * @param cbomid
     *            bom主键
     * @return 版本号
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
    // String uniqueKey = materialoid + mversion;// 物料OID+生产BOM版本号
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
     * 查询 工艺路线
     * 
     * @param CardHeadTailAfterEditEvent
     * @return 版本aggvo
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
    // // 头RtHeadVO子RtItemVO孙RtOpacVO
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
