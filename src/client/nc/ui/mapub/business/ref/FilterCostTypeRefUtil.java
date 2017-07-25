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
 * �ɱ����Ͳ�������
 */

public class FilterCostTypeRefUtil {

    /**
     * ������֯����ʧЧ���ڹ��˳ɱ�����
     *
     * @param pane
     *            UIRefPane
     * @param pkGroup
     *            ����
     * @param pkOrg
     *            ��֯
     */
    public static void filterRefByOrg(UIRefPane pane, String pkOrg) {
        if (!(pane.getRefModel() instanceof CostTypeRefModel)) {
            return;
        }
        // ��õ�ǰ��¼ҵ������
        UFDate currentDate = AppUiContext.getInstance().getBusiDate();
        FilterCostTypeRefUtil.filterRefByOrgAndDate(pane, pkOrg, currentDate);
    }

    /**
     * ������֯���ź���ʧЧ���ڹ��˳ɱ�����
     *
     * @param pane
     * @param pkOrg
     * @param pkGroup
     */
    public static void filterRefByDate(UIRefPane pane, String pkOrg, String pkGroup) {
        if (!(pane.getRefModel() instanceof CostTypeRefModel)) {
            return;
        }
        // ��õ�ǰ��¼ҵ������
        UFDate currentDate = AppUiContext.getInstance().getBusiDate();
        FilterCostTypeRefUtil.filterRefByOrgGroupDate(pane, pkOrg, currentDate, pkGroup);
    }

    /**
     * ������֯���ź���ʧЧ���ڹ��˳ɱ�����
     *
     * @param pane
     * @param pkOrg
     * @param date
     * @param pkGroup
     */
    public static void filterRefByOrgGroupDate(UIRefPane pane, String pkOrg, UFDate date, String pkGroup) {
        // ʧЧ����֮ǰ
        CMSqlBuilder sql = new CMSqlBuilder();

        // ��Ч����֮��
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
        // ��Ч����֮��
        pane.setWhereString(sql.toString());
    }

    /**
     * ������֯����ʧЧ���ڹ��˳ɱ�����
     *
     * @param pane
     *            UIRefPane
     * @param pkGroup
     *            ����
     * @param pkOrg
     *            ��֯
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
        // ��Ч����֮��
        pane.setWhereString(FilterCostTypeRefUtil.filterRefByOrgAndCPeriod(pkOrg, cperiod));
    }

    /**
     * ������֯����ʧЧ���ڶ�Ӧ�Ļ���ڼ���˳ɱ�����
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

        // ʧЧ����֮ǰ
        CMSqlBuilder sql = new CMSqlBuilder();

        // ��Ч����֮��
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
     * ����ȱʡ�ɱ�����
     * 1.Ĭ��Ϊ��������Ĭ�ϵ���Ч�Ѽ���ĳɱ�����
     * 2.û���ҵ���Ĭ��Ϊ���������Ч�Ѽ���ɱ�����
     * 3.��û�ҵ���Ĭ��Ϊ��
     *
     * @param pane
     *            ����
     * @param pkOrg
     *            ��ǰ����
     * @param period
     *            ����ڼ�
     */
    public static void setDefaultCostType(UIRefPane pane, String pkOrg, String period) {
        // �ж��Ƿ�Ϊ�ɱ����Ͳ���
        if (!(pane.getRefModel() instanceof CostTypeRefModel)) {
            return;
        }

        // �ɱ�����
        String costtype = null;
        // �ɱ����ͷ���
        ICostTypePubQueryService costTypeService = NCLocator.getInstance().lookup(ICostTypePubQueryService.class);
        // ��ȡ������Ĭ����Ч�Ҽ�����ĳɱ�����
        CostTypeVO defaultVO = (CostTypeVO) costTypeService.getDefaultCostType(pkOrg, period);

        if (defaultVO == null) {
            // �ɱ����
            String codeNum = null;
            // �õ������µ���Ч���Ѽ�������гɱ�����
            CostTypeVO[] costTypeVOs = (CostTypeVO[]) costTypeService.getAllCostType(pkOrg, period);

            // ��ȡ�����ڱ��������Ч�Ҽ�����ĳɱ�����
            if (CMArrayUtil.isNotEmpty(costTypeVOs) && costTypeVOs.length > 0) {
                // �ɱ����
                codeNum = costTypeVOs[0].getVcosttypecode();
                // �ɱ�����
                costtype = costTypeVOs[0].getCcosttypeid();

                // �����ɱ����
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

        // ����ȱʡֵ
        pane.setPK(costtype);
        pane.setText(costtype);
    }
}
