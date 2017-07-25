package nc.ui.mapub.business.ref;

import java.util.Map;

import nc.bd.business.util.FIUtil;
import nc.bd.framework.db.CMSqlBuilder;
import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.pubitf.mapub.costtype.pub.ICostTypePubQueryService;
import nc.pubitf.resa.factor.chart.IFactorVersionQueryService;
import nc.ui.bd.business.ref.CMFilterDefaultRefUtils;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.AppUiContext;
import nc.ui.resa.refmodel.FactorRefModel;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.resa.factor.FactorVO;

/**
 * 核算要素参照的过滤工具集
 *
 * @since 6.36
 * @version 2011-7-5 下午06:39:04
 * @author xuyang
 */
public class FilterFactorRefUtil extends CMFilterDefaultRefUtils {
    /**
     * 默认构造函数
     *
     * @param pane
     *            参照实例
     */
    public FilterFactorRefUtil(UIRefPane pane) {
        super(pane);
    }

    /**
     * 根据BillItem构造
     *
     * @param item
     *            BillItem，不可以为空
     */
    public FilterFactorRefUtil(BillItem item) {
        super((UIRefPane) item.getComponent());
    }

    /**
     * 根据工厂过滤财务组织对应核算要素
     * 过滤核算要素，得到次级，不是作业，未停用的核算要素
     *
     * @param item
     *            BillItem,参照item
     * @param pk_org
     *            工厂pk
     * @throws Exception
     */
    public static void factorRefFilterByStockORGAndEnable(BillItem item, String pk_org) throws Exception {
        if (item == null) {
            return;
        }
        String businessdatetime = WorkbenchEnvironment.getInstance().getBusiDate().toStdString();
        String businessdate = businessdatetime.substring(0, 10);
        String pk_factorchart = FIUtil.getFactorChartByPkStockOrg(pk_org, businessdate);

        UIRefPane ref = (UIRefPane) item.getComponent();
        ref.setPk_org(pk_org);

        CMSqlBuilder where = new CMSqlBuilder();
        // 次级要素
        where.append(FactorVO.getDefaultTableName() + "." + FactorVO.FACTORCLASS + " = 1");
        // 非作业要素
        where.and();
        where.append(FactorVO.getDefaultTableName() + "." + FactorVO.ISWORKFACTOR + " = 'N'");
        // 末级标志
        where.and();
        where.append(FactorVO.ENDFLAG + " = 'Y'");
        ref.setWhereString(where.toString());
        ref.getRefModel().setPk_org(pk_org);
        ((FactorRefModel) ref.getRefModel()).setPk_factorchart(pk_factorchart);
        // 设置非末级要素不可选
        // ref.setNotLeafSelectedEnabled(false);
    }

    /** modify by hupeng */
    /**
     * 过滤核算要素，不是作业，未停用的核算要素
     *
     * @param item
     *            BillItem,参照item
     * @param pk_org
     *            工厂pk
     */
    public static void factorRefFilterByORG(BillItem billitem, String ccosttypeid) {
        CostTypeVO vo = null;
        String factorchart = null;
        CMSqlBuilder where = new CMSqlBuilder();
        UIRefPane ref = (UIRefPane) billitem.getComponent();
        FactorRefModel model = (FactorRefModel) ref.getRefModel();
        if (billitem == null || ccosttypeid == null) {
            where.append(" 1= 2 and ");
        }
        else {
            try {
                vo =
                        (CostTypeVO) NCLocator.getInstance().lookup(ICostTypePubQueryService.class)
                                .getCostTypeVoByPK(ccosttypeid);
                factorchart = vo.getPk_factorchart();
                model.setPk_factorchart(factorchart);
            }
            catch (Exception e1) {
                ExceptionUtils.wrappException(e1);
            }
        }
        // 非作业要素
        where.append(FactorVO.getDefaultTableName() + "." + FactorVO.ISWORKFACTOR + " = 'N'");
        // 末级标志
        where.and();
        where.append(FactorVO.ENDFLAG + " = 'Y'");
        ref.setWhereString(where.toString());
        // 设置非末级要素不可选
        // ref.setNotLeafSelectedEnabled(false);
    }

    /**
     * <p>
     * 不含材料子项的核算要素只能是非材料且非合并明细的、非作业类的要素 - liyjf 非末级要素可见，但是不可选
     *
     * @param financeID
     *            工厂所属财务组织
     * @param item
     *            BillItem,参照item
     * @since v6.5
     * @author shangzhm1
     */
    public static void factorRefFilterForFee(String financeID, BillItem item) {
        if (item == null) {
            return;
        }

        UIRefPane ref = (UIRefPane) item.getComponent();

        CMSqlBuilder where = new CMSqlBuilder();
        // 非作业要素
        where.append(FactorVO.getDefaultTableName() + "." + FactorVO.ISWORKFACTOR, UFBoolean.FALSE);
        // 是否合并明细
        // 费用类型为非材料费或者合并明细的
        where.and();
        where.l();
        where.append(FactorVO.FEETYPE + " <> 1");
        where.or();
        where.append(FactorVO.ISEXPLAND, UFBoolean.TRUE);
        where.r();
        ref.setWhereString(where.toString());
        // 设置财务组织
        ref.setPk_org(financeID);
        // 参照多选
        ref.setMultiSelectedEnabled(true);
        // 设置非末级要素不可选
        ref.setNotLeafSelectedEnabled(false);

    }

    /**
     * 过滤核算要素
     * 作业要素=N，费用类型!=1
     * 不含材料子项的核算要素只能是非材料类非作业的要素
     */
    public static void specFactorRefFilterForFee(BillItem item) {
        if (item == null) {
            return;
        }

        UIRefPane ref = (UIRefPane) item.getComponent();
        CMSqlBuilder where = new CMSqlBuilder();
        // 非作业要素
        where.append(FactorVO.getDefaultTableName() + "." + FactorVO.ISWORKFACTOR + " = 'N'");
        // 是否合并明细的材料要素
        where.append(" and ((" + FactorVO.ISEXPLAND + "='Y' and " + FactorVO.FEETYPE + "=1" + ")");
        // 非材料要素
        where.append(" or " + FactorVO.FEETYPE + "<>1 or " + FactorVO.FEETYPE + " is null)");
        ref.setWhereString(where.toString());
        // 设置非末级要素不可选
        ref.setNotLeafSelectedEnabled(false);

    }

    /**
     * 过滤核算要素
     * 作业要素=N，费用类型!=1
     * 不含材料子项的核算要素只能是非材料类非作业的要素
     */
    public static void specFactorRefFilterForFee(BillItem billitem, String ccosttypeid) {
        CostTypeVO vo = null;
        String factorchart = null;
        CMSqlBuilder where = new CMSqlBuilder();
        UIRefPane ref = (UIRefPane) billitem.getComponent();
        FactorRefModel model = (FactorRefModel) ref.getRefModel();
        if (billitem == null || ccosttypeid == null) {
            where.append(" 1= 2 and ");
        }
        else {
            try {
                vo =
                        (CostTypeVO) NCLocator.getInstance().lookup(ICostTypePubQueryService.class)
                                .getCostTypeVoByPK(ccosttypeid);
                factorchart = vo.getPk_factorchart();
                model.setPk_factorchart(factorchart);
            }
            catch (Exception e1) {
                ExceptionUtils.wrappException(e1);
            }
        }
        // 非作业要素
        where.append(FactorVO.ISWORKFACTOR + " = 'N'");
        // 是否合并明细的材料要素
        where.append(" and ((" + FactorVO.ISEXPLAND + "='Y' and " + FactorVO.FEETYPE + "=1" + ")");
        // 非材料要素
        where.append(" or " + FactorVO.FEETYPE + "<>1 or " + FactorVO.FEETYPE + " is null)");
        ref.setWhereString(where.toString());
        // 设置非末级要素不可选
        ref.setNotLeafSelectedEnabled(false);

    }

    /**
     * 过滤核算要素，管控范围内的核算要素，末级，有效
     * add by chengjieb 2011-11-25 成本结转定义
     *
     * @param item
     *            BillItem,参照item
     * @param org
     *            工厂org
     */
    public static void factorRefFilterByGk(BillItem item, String org) {
        String factorchart = null;
        // 业务日期，此值有19位，包括时间
        String businessdatetime = AppUiContext.getInstance().getBusiDate().toString();
        // 不包括时间的业务日期
        String businessdate = businessdatetime.substring(0, 10);

        if (item == null) {
            return;
        }

        UIRefPane ref = (UIRefPane) item.getComponent();
        FactorRefModel model = (FactorRefModel) ref.getRefModel();
        if (org != null && org.length() > 0) {
            try {
                // 得到管控范围下的核算要素表主键
                factorchart = FIUtil.getFactorChartByPkOrg(org, businessdate);
                // 设置核算要素参照中的核算要素表主键，核算要素编码和名称由核算要素表主键获得
                model.setPk_factorchart(factorchart);

                model.setWherePart(FactorVO.SEALFLAG + " = 'N'" + " and " + FactorVO.ENDFLAG + " = 'Y'");

                // 设置非末级要素不可选
                // ref.setNotLeafSelectedEnabled(false);

            }
            catch (Exception e) {
                ExceptionUtils.wrappException(e);
            }

        }
    }

    /**
     * 过滤核算要素，末级未停用的核算要素
     * add by sunyqb at 2011.11.04 约当系数，只能录入末级未停用的核算要素
     *
     * @param item
     *            BillItem,参照item
     */
    public static void factorRefFilterForLastAndEnabled(BillItem item, String pk_org) {
        if (item == null) {
            return;
        }
        String date = AppUiContext.getInstance().getBusiDate().toString();
        String factorchart = null;
        try {
            factorchart = FIUtil.getFactorChartByPkStockOrg(pk_org, date);
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
        UIRefPane ref = (UIRefPane) item.getComponent();

        FactorRefModel model = (FactorRefModel) ref.getRefModel();
        model.setPk_factorchart(factorchart);
        StringBuffer where = new StringBuffer();
        // 停用标志
        where.append(FactorVO.SEALFLAG + " = 'N'");
        where.append(" and ");
        // 末级标志
        where.append(FactorVO.ENDFLAG + " = 'Y'");
        ref.setWhereString(where.toString());
        // 设置非末级要素不可选
        // ref.setNotLeafSelectedEnabled(false);
    }

    /**
     * 过滤核算要素
     * 作业要素=N，费用类型!=1
     * 不含材料子项的核算要素只能是非材料类非作业的要素
     */
    public static void specFactorRefFilterForCostBomFee(BillItem billitem, String ccosttypeid) {
        CostTypeVO vo = null;
        String factorchart = null;
        CMSqlBuilder where = new CMSqlBuilder();
        UIRefPane ref = (UIRefPane) billitem.getComponent();
        FactorRefModel model = (FactorRefModel) ref.getRefModel();
        if (billitem == null || ccosttypeid == null) {
            return;
        }
        else {
            try {
                vo =
                        (CostTypeVO) NCLocator.getInstance().lookup(ICostTypePubQueryService.class)
                                .getCostTypeVoByPK(ccosttypeid);
                factorchart = vo.getPk_factorchart();
                Map<String, String> factorchartmap =
                        NCLocator.getInstance().lookup(IFactorVersionQueryService.class)
                        .getFactorchartByDate(new String[] {
                                factorchart
                        }, null);
                model.setPk_factorchart(factorchartmap.get(factorchart));
            }
            catch (Exception e1) {
                ExceptionUtils.wrappException(e1);
            }
        }
        // 非作业要素
        where.append(FactorVO.ISWORKFACTOR + " = 'N'");
        // 合并明细
        where.and();
        where.append(FactorVO.ISEXPLAND + " = 'Y'");
        // 停用标志
        where.and();
        where.append(FactorVO.SEALFLAG + " = 'N'");
        ref.setWhereString(where.toString());
        ref.setMultiSelectedEnabled(true);
        // 设置非末级要素不可选
        ref.setNotLeafSelectedEnabled(false);

    }
}
