package nc.ui.mapub.coprodcoef.handler;

import nc.bd.framework.db.CMSqlBuilder;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;

/**
 * ���ϱ༭�¼�
 * 
 * @since 6.0
 * @version 2014-10-11 ����12:34:05
 * @author zhangshyb
 */
public class CMaterialHandler {

    /**
     * ���ϱ༭���¼�
     * ��1���༭���ѡ���в���
     * 
     * @param e
     */
    public int[] afterEdit(CardBodyAfterEditEvent e) {
        // ��ǰҳǩ���ֶ�����ҳǩ��һ��
        if (!e.getBillCardPanel().getBodyPanel().getTableCode().equals(e.getTableCode())) {
            return null;
        }
        BillCardPanel cardPanel = e.getBillCardPanel();
        RefMoreSelectedUtils utils = new RefMoreSelectedUtils(cardPanel);
        // Ϊtrue�������У�Ϊfalse������
        return utils.refMoreSelected(e.getRow(), e.getKey(), false);
    }

    /**
     * ���ϱ༭ǰ�¼�
     * ��1�����ϲ��ն�ѡ��
     * 
     * @param e
     */
    public void beforeEdit(CardBodyBeforeEditEvent e) {
        BillCardPanel panel = e.getBillCardPanel();
        // Card util = new CardPanelValueUtils(panel);
        // ��ñ༭���
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
