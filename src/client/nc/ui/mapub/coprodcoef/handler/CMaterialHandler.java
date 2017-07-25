package nc.ui.mapub.coprodcoef.handler;

import nc.bd.framework.db.CMSqlBuilder;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;

/**
 * 物料编辑事件
 * 
 * @since 6.0
 * @version 2014-10-11 下午12:34:05
 * @author zhangshyb
 */
public class CMaterialHandler {

    /**
     * 物料编辑后事件
     * （1）编辑后多选增行操作
     * 
     * @param e
     */
    public int[] afterEdit(CardBodyAfterEditEvent e) {
        // 当前页签和字段所属页签不一致
        if (!e.getBillCardPanel().getBodyPanel().getTableCode().equals(e.getTableCode())) {
            return null;
        }
        BillCardPanel cardPanel = e.getBillCardPanel();
        RefMoreSelectedUtils utils = new RefMoreSelectedUtils(cardPanel);
        // 为true则新增行，为false则复制行
        return utils.refMoreSelected(e.getRow(), e.getKey(), false);
    }

    /**
     * 物料编辑前事件
     * （1）物料参照多选打开
     * 
     * @param e
     */
    public void beforeEdit(CardBodyBeforeEditEvent e) {
        BillCardPanel panel = e.getBillCardPanel();
        // Card util = new CardPanelValueUtils(panel);
        // 获得编辑组件
        UIRefPane refPane = (UIRefPane) panel.getBodyItem(e.getKey()).getComponent();
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
        refPane.setMultiSelectedEnabled(true);
        e.setReturnValue(Boolean.TRUE);
    }

}
