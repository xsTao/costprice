package nc.ui.mapub.materialpricebase.ref;

import nc.bd.framework.db.CMSqlBuilder;
import nc.ui.bd.business.ref.CMFilterDefaultRefUtils;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.AppUiContext;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.pub.lang.UFDate;

/**
 * �۸����˵�ʧЧ�ļ۸�
 *
 * @since 6.0
 * @version 2012-4-20 ����11:20:59
 * @author chengjieb
 */
public class FilterBasePriceRefUtil extends CMFilterDefaultRefUtils {

    public FilterBasePriceRefUtil(UIRefPane pane) {
        super(pane);
    }

    /**
     * ����BillItem����
     *
     * @param item
     *            BillItem��������Ϊ��
     */
    public FilterBasePriceRefUtil(BillItem item) {

        super((UIRefPane) item.getComponent());

    }

    /**
     * ���˼۸��Ϊ��Ч�ڼ�۸�
     *
     * @param item
     *            BillItem,����item
     * @param pk_org
     *            ����pk
     */
    public static void refFilterEnableAndLast(BillItem itembuness, BillItem itemprice) {
        if (itembuness.getValueObject() != null) {
            CMSqlBuilder sqlwhere = new CMSqlBuilder();
            // �������������� nc.vo.cm.st.st0206.PriceBaseHeadVO
            sqlwhere.append("and '" + itembuness.getValueObject().toString() + "'<=" + MaterialPriceBaseHeadVO.DENDDATE);
            sqlwhere.append(" and '" + itembuness.getValueObject().toString() + "'>="
                    + MaterialPriceBaseHeadVO.DBEGINDATE);
            sqlwhere.append(" and dr=0");

            UIRefPane ref = (UIRefPane) itemprice.getComponent();
            AbstractRefModel refModel = ref.getRefModel();
            refModel.addWherePart(sqlwhere.toString());
        }

    }

    /**
     * ������֯����ʧЧ���ڹ��˼۸��
     *
     * @param pane
     *            UIRefPane
     * @param pkGroup
     *            ����
     * @param pkOrg
     *            ��֯
     */
    public static void filterPriceRefByOrgAndDate(UIRefPane pane, String pkGroup, String pkOrg) {
        if (!(pane.getRefModel() instanceof PriceBaseRefModel)) {
            return;
        }
        // ��õ�ǰ��¼ҵ������
        UFDate currentDate = AppUiContext.getInstance().getBusiDate();
        String strCurrentDate = currentDate.toStdString();

        pane.setWhereString(FilterBasePriceRefUtil.getFilterPriceByOrgAndDate(pkGroup, pkOrg, strCurrentDate));
    }

    public static String getFilterPriceByOrgAndDate(String pkGroup, String pkOrg, String strCurrentDate) {
        // ʧЧ����֮ǰ
        CMSqlBuilder sql = new CMSqlBuilder();
        sql.append(" '" + strCurrentDate + " 00:00:00'<=" + MaterialPriceBaseHeadVO.DENDDATE);
        sql.append(" and '" + strCurrentDate + " 24:00:00'>=" + MaterialPriceBaseHeadVO.DBEGINDATE);
        sql.append(" and dr=0 and pk_org='" + pkOrg + "' and pk_group='" + pkGroup + "'");
        return sql.toString();
    }
}
