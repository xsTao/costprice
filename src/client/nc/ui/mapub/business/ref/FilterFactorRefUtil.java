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
 * ����Ҫ�ز��յĹ��˹��߼�
 *
 * @since 6.36
 * @version 2011-7-5 ����06:39:04
 * @author xuyang
 */
public class FilterFactorRefUtil extends CMFilterDefaultRefUtils {
    /**
     * Ĭ�Ϲ��캯��
     *
     * @param pane
     *            ����ʵ��
     */
    public FilterFactorRefUtil(UIRefPane pane) {
        super(pane);
    }

    /**
     * ����BillItem����
     *
     * @param item
     *            BillItem��������Ϊ��
     */
    public FilterFactorRefUtil(BillItem item) {
        super((UIRefPane) item.getComponent());
    }

    /**
     * ���ݹ������˲�����֯��Ӧ����Ҫ��
     * ���˺���Ҫ�أ��õ��μ���������ҵ��δͣ�õĺ���Ҫ��
     *
     * @param item
     *            BillItem,����item
     * @param pk_org
     *            ����pk
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
        // �μ�Ҫ��
        where.append(FactorVO.getDefaultTableName() + "." + FactorVO.FACTORCLASS + " = 1");
        // ����ҵҪ��
        where.and();
        where.append(FactorVO.getDefaultTableName() + "." + FactorVO.ISWORKFACTOR + " = 'N'");
        // ĩ����־
        where.and();
        where.append(FactorVO.ENDFLAG + " = 'Y'");
        ref.setWhereString(where.toString());
        ref.getRefModel().setPk_org(pk_org);
        ((FactorRefModel) ref.getRefModel()).setPk_factorchart(pk_factorchart);
        // ���÷�ĩ��Ҫ�ز���ѡ
        // ref.setNotLeafSelectedEnabled(false);
    }

    /** modify by hupeng */
    /**
     * ���˺���Ҫ�أ�������ҵ��δͣ�õĺ���Ҫ��
     *
     * @param item
     *            BillItem,����item
     * @param pk_org
     *            ����pk
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
        // ����ҵҪ��
        where.append(FactorVO.getDefaultTableName() + "." + FactorVO.ISWORKFACTOR + " = 'N'");
        // ĩ����־
        where.and();
        where.append(FactorVO.ENDFLAG + " = 'Y'");
        ref.setWhereString(where.toString());
        // ���÷�ĩ��Ҫ�ز���ѡ
        // ref.setNotLeafSelectedEnabled(false);
    }

    /**
     * <p>
     * ������������ĺ���Ҫ��ֻ���Ƿǲ����ҷǺϲ���ϸ�ġ�����ҵ���Ҫ�� - liyjf ��ĩ��Ҫ�ؿɼ������ǲ���ѡ
     *
     * @param financeID
     *            ��������������֯
     * @param item
     *            BillItem,����item
     * @since v6.5
     * @author shangzhm1
     */
    public static void factorRefFilterForFee(String financeID, BillItem item) {
        if (item == null) {
            return;
        }

        UIRefPane ref = (UIRefPane) item.getComponent();

        CMSqlBuilder where = new CMSqlBuilder();
        // ����ҵҪ��
        where.append(FactorVO.getDefaultTableName() + "." + FactorVO.ISWORKFACTOR, UFBoolean.FALSE);
        // �Ƿ�ϲ���ϸ
        // ��������Ϊ�ǲ��Ϸѻ��ߺϲ���ϸ��
        where.and();
        where.l();
        where.append(FactorVO.FEETYPE + " <> 1");
        where.or();
        where.append(FactorVO.ISEXPLAND, UFBoolean.TRUE);
        where.r();
        ref.setWhereString(where.toString());
        // ���ò�����֯
        ref.setPk_org(financeID);
        // ���ն�ѡ
        ref.setMultiSelectedEnabled(true);
        // ���÷�ĩ��Ҫ�ز���ѡ
        ref.setNotLeafSelectedEnabled(false);

    }

    /**
     * ���˺���Ҫ��
     * ��ҵҪ��=N����������!=1
     * ������������ĺ���Ҫ��ֻ���Ƿǲ��������ҵ��Ҫ��
     */
    public static void specFactorRefFilterForFee(BillItem item) {
        if (item == null) {
            return;
        }

        UIRefPane ref = (UIRefPane) item.getComponent();
        CMSqlBuilder where = new CMSqlBuilder();
        // ����ҵҪ��
        where.append(FactorVO.getDefaultTableName() + "." + FactorVO.ISWORKFACTOR + " = 'N'");
        // �Ƿ�ϲ���ϸ�Ĳ���Ҫ��
        where.append(" and ((" + FactorVO.ISEXPLAND + "='Y' and " + FactorVO.FEETYPE + "=1" + ")");
        // �ǲ���Ҫ��
        where.append(" or " + FactorVO.FEETYPE + "<>1 or " + FactorVO.FEETYPE + " is null)");
        ref.setWhereString(where.toString());
        // ���÷�ĩ��Ҫ�ز���ѡ
        ref.setNotLeafSelectedEnabled(false);

    }

    /**
     * ���˺���Ҫ��
     * ��ҵҪ��=N����������!=1
     * ������������ĺ���Ҫ��ֻ���Ƿǲ��������ҵ��Ҫ��
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
        // ����ҵҪ��
        where.append(FactorVO.ISWORKFACTOR + " = 'N'");
        // �Ƿ�ϲ���ϸ�Ĳ���Ҫ��
        where.append(" and ((" + FactorVO.ISEXPLAND + "='Y' and " + FactorVO.FEETYPE + "=1" + ")");
        // �ǲ���Ҫ��
        where.append(" or " + FactorVO.FEETYPE + "<>1 or " + FactorVO.FEETYPE + " is null)");
        ref.setWhereString(where.toString());
        // ���÷�ĩ��Ҫ�ز���ѡ
        ref.setNotLeafSelectedEnabled(false);

    }

    /**
     * ���˺���Ҫ�أ��ܿط�Χ�ڵĺ���Ҫ�أ�ĩ������Ч
     * add by chengjieb 2011-11-25 �ɱ���ת����
     *
     * @param item
     *            BillItem,����item
     * @param org
     *            ����org
     */
    public static void factorRefFilterByGk(BillItem item, String org) {
        String factorchart = null;
        // ҵ�����ڣ���ֵ��19λ������ʱ��
        String businessdatetime = AppUiContext.getInstance().getBusiDate().toString();
        // ������ʱ���ҵ������
        String businessdate = businessdatetime.substring(0, 10);

        if (item == null) {
            return;
        }

        UIRefPane ref = (UIRefPane) item.getComponent();
        FactorRefModel model = (FactorRefModel) ref.getRefModel();
        if (org != null && org.length() > 0) {
            try {
                // �õ��ܿط�Χ�µĺ���Ҫ�ر�����
                factorchart = FIUtil.getFactorChartByPkOrg(org, businessdate);
                // ���ú���Ҫ�ز����еĺ���Ҫ�ر�����������Ҫ�ر���������ɺ���Ҫ�ر��������
                model.setPk_factorchart(factorchart);

                model.setWherePart(FactorVO.SEALFLAG + " = 'N'" + " and " + FactorVO.ENDFLAG + " = 'Y'");

                // ���÷�ĩ��Ҫ�ز���ѡ
                // ref.setNotLeafSelectedEnabled(false);

            }
            catch (Exception e) {
                ExceptionUtils.wrappException(e);
            }

        }
    }

    /**
     * ���˺���Ҫ�أ�ĩ��δͣ�õĺ���Ҫ��
     * add by sunyqb at 2011.11.04 Լ��ϵ����ֻ��¼��ĩ��δͣ�õĺ���Ҫ��
     *
     * @param item
     *            BillItem,����item
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
        // ͣ�ñ�־
        where.append(FactorVO.SEALFLAG + " = 'N'");
        where.append(" and ");
        // ĩ����־
        where.append(FactorVO.ENDFLAG + " = 'Y'");
        ref.setWhereString(where.toString());
        // ���÷�ĩ��Ҫ�ز���ѡ
        // ref.setNotLeafSelectedEnabled(false);
    }

    /**
     * ���˺���Ҫ��
     * ��ҵҪ��=N����������!=1
     * ������������ĺ���Ҫ��ֻ���Ƿǲ��������ҵ��Ҫ��
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
        // ����ҵҪ��
        where.append(FactorVO.ISWORKFACTOR + " = 'N'");
        // �ϲ���ϸ
        where.and();
        where.append(FactorVO.ISEXPLAND + " = 'Y'");
        // ͣ�ñ�־
        where.and();
        where.append(FactorVO.SEALFLAG + " = 'N'");
        ref.setWhereString(where.toString());
        ref.setMultiSelectedEnabled(true);
        // ���÷�ĩ��Ҫ�ز���ѡ
        ref.setNotLeafSelectedEnabled(false);

    }
}
