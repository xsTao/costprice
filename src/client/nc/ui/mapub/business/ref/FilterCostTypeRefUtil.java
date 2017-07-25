package nc.ui.mapub.business.ref;

import nc.bd.business.util.FIUtil;
import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.db.CMSqlBuilder;
import nc.bs.framework.common.NCLocator;
import nc.cmpub.business.adapter.BDAdapter;
import nc.pub.templet.converter.util.helper.ExceptionUtils;
import nc.pubitf.mapub.costtype.pub.ICostTypePubQueryService;
import nc.ui.mapub.costtype.model.CostTypeRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.AppUiContext;
import nc.vo.bd.period2.AccperiodmonthVO;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;

/**
 * 成本类型参照条件
 */

public class FilterCostTypeRefUtil {

    /**
     * 根据组织和生失效日期过滤成本类型
     *
     * @param pane
     *            UIRefPane
     * @param pkGroup
     *            集团
     * @param pkOrg
     *            组织
     */
    public static void filterRefByOrg(UIRefPane pane, String pkOrg) {
        if (!(pane.getRefModel() instanceof CostTypeRefModel)) {
            return;
        }
        // 获得当前登录业务日期
        UFDate currentDate = AppUiContext.getInstance().getBusiDate();
        FilterCostTypeRefUtil.filterRefByOrgAndDate(pane, pkOrg, currentDate);
    }

    /**
     * 根据组织集团和生失效日期过滤成本类型
     *
     * @param pane
     * @param pkOrg
     * @param pkGroup
     */
    public static void filterRefByDate(UIRefPane pane, String pkOrg, String pkGroup) {
        if (!(pane.getRefModel() instanceof CostTypeRefModel)) {
            return;
        }
        // 获得当前登录业务日期
        UFDate currentDate = AppUiContext.getInstance().getBusiDate();
        FilterCostTypeRefUtil.filterRefByOrgGroupDate(pane, pkOrg, currentDate, pkGroup);
    }

    /**
     * 根据组织集团和生失效日期过滤成本类型
     *
     * @param pane
     * @param pkOrg
     * @param date
     * @param pkGroup
     */
    public static void filterRefByOrgGroupDate(UIRefPane pane, String pkOrg, UFDate date, String pkGroup) {
        // 失效日期之前
        CMSqlBuilder sql = new CMSqlBuilder();

        // 生效日期之后
        sql.append(" dr=0 and (");
        sql.append(CostTypeVO.PK_ORG, pkOrg);
        sql.or();
        sql.append(CostTypeVO.PK_GROUP, pkGroup);
        sql.r();
        sql.and();
        sql.append(CostTypeVO.CBEGINMONTH);
        sql.append("<= '");
        sql.append(date);
        sql.append("' and '");
        sql.append(date);
        sql.append("' <= ");
        sql.append(CostTypeVO.CENDMONTH);
        // 生效日期之后
        pane.setWhereString(sql.toString());
    }

    /**
     * 根据组织和生失效日期过滤成本类型
     *
     * @param pane
     *            UIRefPane
     * @param pkGroup
     *            集团
     * @param pkOrg
     *            组织
     */
    public static void filterRefByOrgAndDate(UIRefPane pane, String pkOrg, UFDate date) {
        if (!(pane.getRefModel() instanceof CostTypeRefModel)) {
            return;
        }
        String cperiod = null;
        try {
            AccperiodmonthVO monthvo = FIUtil.getAccPeriodMonthVO(pkOrg, date);
            if (monthvo != null) {
                cperiod = monthvo.getYearmth();
            }
            else {
                cperiod = "9999-99";
            }
        }
        catch (BusinessException e) {
            ExceptionUtils.wrapException(e);
        }
        // 生效日期之后
        pane.setWhereString(FilterCostTypeRefUtil.filterRefByOrgAndCPeriod(pkOrg, cperiod));
    }

    /**
     * 根据组织和生失效日期对应的会计期间过滤成本类型
     *
     * @param pkOrg
     * @param cperiod
     * @return
     */
    public static String filterRefByOrgAndCPeriod(String pkOrg, String cperiod) {
        UFDate[] dates = new UFDate[2];
        try {
            dates = BDAdapter.getBeginAndEndDateByPeriod(pkOrg, cperiod);
        }
        catch (BusinessException e) {
            ExceptionUtils.wrapException(e);
        }

        // 失效日期之前
        CMSqlBuilder sql = new CMSqlBuilder();

        // 生效日期之后
        sql.append(" dr=0 and ");
        sql.append(" ( ");
        sql.append(CostTypeVO.PK_ORG, pkOrg);
        sql.append(" or ");
        sql.append(CostTypeVO.PK_ORG, AppContext.getInstance().getPkGroup());
        sql.append(" ) ");
        sql.append(" and ");
        sql.append(CostTypeVO.CBEGINMONTH);
        sql.append("<= '");
        sql.append(dates[1]);
        sql.append("' and ");
        sql.append(CostTypeVO.CENDMONTH);
        sql.append(">='");
        sql.append(dates[0]);
        sql.append(" '");

        return sql.toString();
    }

    /**
     * 设置缺省成本类型
     * 1.默认为本工厂内默认的有效已计算的成本类型
     * 2.没有找到，默认为编码最大有效已计算成本类型
     * 3.还没找到，默认为空
     *
     * @param pane
     *            参照
     * @param pkOrg
     *            当前工厂
     * @param period
     *            会计期间
     */
    public static void setDefaultCostType(UIRefPane pane, String pkOrg, String period) {
        // 判断是否为成本类型参照
        if (!(pane.getRefModel() instanceof CostTypeRefModel)) {
            return;
        }

        // 成本类型
        String costtype = null;
        // 成本类型服务
        ICostTypePubQueryService costTypeService = NCLocator.getInstance().lookup(ICostTypePubQueryService.class);
        // 获取工厂内默认有效且计算过的成本类型
        CostTypeVO defaultVO = (CostTypeVO) costTypeService.getDefaultCostType(pkOrg, period);

        if (defaultVO == null) {
            // 成本编号
            String codeNum = null;
            // 得到工厂下的有效且已计算的所有成本类型
            CostTypeVO[] costTypeVOs = (CostTypeVO[]) costTypeService.getAllCostType(pkOrg, period);

            // 获取工厂内编号最大的有效且计算过的成本类型
            if (CMArrayUtil.isNotEmpty(costTypeVOs) && costTypeVOs.length > 0) {
                // 成本编号
                codeNum = costTypeVOs[0].getVcosttypecode();
                // 成本类型
                costtype = costTypeVOs[0].getCcosttypeid();

                // 找最大成本编号
                for (int i = 1; i < costTypeVOs.length; i++) {
                    if (codeNum.compareTo(costTypeVOs[i].getVcosttypecode()) < 0) {
                        costtype = costTypeVOs[i].getCcosttypeid();
                    }
                }
            }
        }
        else {
            costtype = defaultVO.getCcosttypeid();
        }

        // 设置缺省值
        pane.setPK(costtype);
        pane.setText(costtype);
    }
}
