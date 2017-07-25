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
 * 价格库过滤掉失效的价格
 *
 * @since 6.0
 * @version 2012-4-20 上午11:20:59
 * @author chengjieb
 */
public class FilterBasePriceRefUtil extends CMFilterDefaultRefUtils {

    public FilterBasePriceRefUtil(UIRefPane pane) {
        super(pane);
    }

    /**
     * 根据BillItem构造
     *
     * @param item
     *            BillItem，不可以为空
     */
    public FilterBasePriceRefUtil(BillItem item) {

        super((UIRefPane) item.getComponent());

    }

    /**
     * 过滤价格库为有效期间价格
     *
     * @param item
     *            BillItem,参照item
     * @param pk_org
     *            工厂pk
     */
    public static void refFilterEnableAndLast(BillItem itembuness, BillItem itemprice) {
        if (itembuness.getValueObject() != null) {
            CMSqlBuilder sqlwhere = new CMSqlBuilder();
            // 所属工厂类名称 nc.vo.cm.st.st0206.PriceBaseHeadVO
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
     * 根据组织和生失效日期过滤价格库
     *
     * @param pane
     *            UIRefPane
     * @param pkGroup
     *            集团
     * @param pkOrg
     *            组织
     */
    public static void filterPriceRefByOrgAndDate(UIRefPane pane, String pkGroup, String pkOrg) {
        if (!(pane.getRefModel() instanceof PriceBaseRefModel)) {
            return;
        }
        // 获得当前登录业务日期
        UFDate currentDate = AppUiContext.getInstance().getBusiDate();
        String strCurrentDate = currentDate.toStdString();

        pane.setWhereString(FilterBasePriceRefUtil.getFilterPriceByOrgAndDate(pkGroup, pkOrg, strCurrentDate));
    }

    public static String getFilterPriceByOrgAndDate(String pkGroup, String pkOrg, String strCurrentDate) {
        // 失效日期之前
        CMSqlBuilder sql = new CMSqlBuilder();
        sql.append(" '" + strCurrentDate + " 00:00:00'<=" + MaterialPriceBaseHeadVO.DENDDATE);
        sql.append(" and '" + strCurrentDate + " 24:00:00'>=" + MaterialPriceBaseHeadVO.DBEGINDATE);
        sql.append(" and dr=0 and pk_org='" + pkOrg + "' and pk_group='" + pkGroup + "'");
        return sql.toString();
    }
}
