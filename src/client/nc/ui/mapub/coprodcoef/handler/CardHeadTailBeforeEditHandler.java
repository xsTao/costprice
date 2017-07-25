package nc.ui.mapub.coprodcoef.handler;

import nc.bd.framework.base.CMStringUtil;
import nc.bd.framework.db.CMSqlBuilder;
import nc.ui.bd.ref.model.BomVerRefModel;
import nc.ui.bd.ref.model.RoutVerRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.resa.refmodel.FactorChartRefModel;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefHeadVO;

/**
 * 单据表头表尾字段编辑前事件处理类
 *
 * @since 6.0
 * @version 2014-10-11 下午12:42:07
 * @author zhangshyb
 */
public class CardHeadTailBeforeEditHandler implements IAppEventHandler<CardHeadTailBeforeEditEvent> {

    @Override
    public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
        e.setReturnValue(Boolean.TRUE);

        String key = e.getKey();

        BillItem cmaterialVIDHeadItem = e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CMATERIALVID);
        BillItem cmaterialOIDHeadItem = e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CMATERIALID);
        String materialvid = (String) cmaterialVIDHeadItem.getValueObject();
        String materialid = (String) cmaterialOIDHeadItem.getValueObject();

        if (CMStringUtil.isEqual(key, CoprodcoefHeadVO.CMATERIALID)) {
            UIRefPane refPane =
                    (UIRefPane) e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CMATERIALID).getComponent();
            CMSqlBuilder sql = new CMSqlBuilder();
            sql.append("dr = 0");
            sql.and();
            sql.append("PK_MATERIAL in (");
            sql.select();
            sql.append("PK_MATERIAL");
            sql.from("bd_materialprod bd_materialprod");
            sql.where();
            sql.append("bd_materialprod.SFCBDX = 'Y'");
            sql.and();
            sql.append("bd_materialprod.SFFZFW = 'N'");
            sql.and();
            sql.append("bd_materialprod.dr = 0");
            sql.and();
            sql.append("(");
            sql.append("bd_materialprod.pk_org = '");
            sql.append(e.getContext().getPk_group());
            sql.append("'");
            sql.or();
            sql.append("bd_materialprod.pk_org = '");
            sql.append(e.getContext().getPk_org());
            sql.append("'");
            sql.append(")");
            sql.append(")");
            refPane.setWhereString(sql.toString());
        }
        if (CMStringUtil.isEqual(key, CoprodcoefHeadVO.CBOMID)) {
            // 根据联合体（物料）过滤bom版本
            // if (CMStringUtil.isNotEmpty(materialvid)) {
            UIRefPane ref = (UIRefPane) e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CBOMID).getComponent();

            BomVerRefModel bomRefModel = (BomVerRefModel) ref.getRefModel();
            bomRefModel.setJoinType(BomVerRefModel.OIDANDVID);
            bomRefModel.setWherePart("hcmaterialid='" + materialid + "' and fbomtype='1' and fbillstatus = '1'");
            // }

        }
        else if (CMStringUtil.isEqual(key, CoprodcoefHeadVO.CRTID)) {
            // 根据联合体（物料）过滤工艺路线版本
            if (cmaterialVIDHeadItem != null) {
                UIRefPane ref = (UIRefPane) e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.CRTID).getComponent();

                RoutVerRefModel verModel = (RoutVerRefModel) ref.getRefModel();
                verModel.setCmaterialid(materialid);
                verModel.setCmaterialvid(materialvid);

            }

        }
        else if (key.equals(CoprodcoefHeadVO.PK_FACTORCHART)) {
            BillCardPanel panel = e.getBillCardPanel();
            String elementSystem =
                    String.valueOf(e.getBillCardPanel().getHeadItem(CoprodcoefHeadVO.PK_ELEMENTSYSTEM).getValueObject());
            UIRefPane refPane = (UIRefPane) panel.getHeadItem(e.getKey()).getComponent();
            FactorChartRefModel refModel = (FactorChartRefModel) refPane.getRefModel();
            refModel.setPk_org(e.getContext().getPk_org(), elementSystem);
        }

        e.setReturnValue(Boolean.TRUE);
    }

}
