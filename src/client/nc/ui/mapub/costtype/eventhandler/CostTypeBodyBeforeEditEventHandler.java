package nc.ui.mapub.costtype.eventhandler;

import nc.bd.framework.base.CMStringUtil;
import nc.bd.framework.db.CMSqlBuilder;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.resa.refmodel.FactorChartRefModel;
import nc.vo.mapub.costtype.entity.CostTypeVO;

/**
 * <b> 成本类型编辑前事件，主要处理生效期间和失效期间的参照问题 </b>
 * <p>
 * 详细描述功能
 */
public class CostTypeBodyBeforeEditEventHandler implements IAppEventHandler<CardBodyBeforeEditEvent> {

    @SuppressWarnings("boxing")
    @Override
    public void handleAppEvent(CardBodyBeforeEditEvent e) {
        if (CMStringUtil.isEmpty(e.getContext().getPk_org())
                || e.getContext().getNodeType().toString().equals("ORG_NODE")
                && e.getContext().getPk_org().equals(e.getContext().getPk_group())) {
            MessageDialog.showWarningDlg(e.getContext().getEntranceUI(), nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                    .getStrByID("3810006_0", "03810006-0037")/* 警告 */, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                    .getStrByID("3810006_0", "03810006-0300")/* 请先选择业务单元！ */);
            return;
        }
        if (CostTypeVO.PK_FACTORCHART.equals(e.getKey())) {
            String elementSystem =
                    String.valueOf(e.getBillCardPanel().getBodyValueAt(e.getRow(), CostTypeVO.PK_ELEMENTSYSTEM));
            BillCardPanel panel = e.getBillCardPanel();
            UIRefPane refPane = (UIRefPane) panel.getBodyItem(e.getKey()).getComponent();
            FactorChartRefModel refModel = (FactorChartRefModel) refPane.getRefModel();
            if (e.getContext().getPk_group().equals(e.getContext().getPk_org())) {
                refModel.setPk_org(e.getContext().getPk_org(), elementSystem);
            }
            else {
                refModel.setPk_factorSystem(elementSystem);
            }
        }
        if (CostTypeVO.PK_MATERIALDOCVIEW.equals(e.getKey()) /* || CostTypeVO.PK_ACTIVITYDOCVIEW.equals(e.getKey()) */) {
            BillCardPanel panel = e.getBillCardPanel();
            UIRefPane refPane = (UIRefPane) panel.getBodyItem(e.getKey()).getComponent();
            refPane.setWhereString(this.getDocViewWhereSql(e));
        }
        // beforeEdit需要返回可编辑true
        e.setReturnValue(true);
    }

    public String getDocViewWhereSql(CardBodyBeforeEditEvent e) {
        CMSqlBuilder sql = new CMSqlBuilder();
        sql.append("pk_group" + "='" + e.getContext().getPk_group() + "' ");
        sql.and();
        if (e.getContext().getPk_group().equals(e.getContext().getPk_org())) {
            sql.append("pk_org" + "='" + e.getContext().getPk_group() + "' ");
            sql.and();
        }
        else {
            sql.append("(");
            sql.append("pk_org" + "='" + e.getContext().getPk_group() + "' ");
            sql.or();
            sql.append("(");
            sql.append("pk_org" + "='" + e.getContext().getPk_org() + "' ");
            sql.and();
            sql.append("ORGTYPE", new String[] {
                    "5144b675-b681-4509-809e-6eb311db39b4", "13a0d3b2-4d5b-4314-9e75-481193f993f2"
            });
            sql.append("))");
            sql.and();
        }
        sql.append("dr = 0 ");
        sql.and();
        sql.append(" desdocid='37e3adca-e0a0-4dff-82ea-63b335cf5515' ");
        sql.and();
        sql.append("pk_setorg1 in (");
        sql.append("select pk_setofbook from ORG_SETOFBOOK where pk_checkelemsystem = '"
                + e.getBillCardPanel().getBodyValueAt(e.getRow(), CostTypeVO.PK_ELEMENTSYSTEM) + "'");
        sql.append(")");
        return sql.toString();
    }

}
