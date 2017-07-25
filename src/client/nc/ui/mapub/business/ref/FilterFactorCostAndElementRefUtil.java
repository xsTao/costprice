package nc.ui.mapub.business.ref;

import java.util.ArrayList;
import java.util.List;

import nc.bd.business.util.FIUtil;
import nc.bd.framework.db.CMSqlBuilder;
import nc.bs.framework.common.NCLocator;
import nc.pubitf.mapub.costtype.pub.ICostTypePubQueryService;
import nc.ui.bd.business.ref.CMFilterDefaultRefUtils;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.ui.resa.refmodel.FactorRefModel;
import nc.vo.bd.pub.IPubEnumConst;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.resa.factor.FactorVO;

/**
 * �ɱ���ת������������붨��ɱ����ĺͺ���Ҫ�ز���
 *
 * @since 6.0
 * @version 2012-3-9 ����09:46:40
 * @author chengjieb
 */
public class FilterFactorCostAndElementRefUtil extends CMFilterDefaultRefUtils {

    public FilterFactorCostAndElementRefUtil(UIRefPane pane) {
        super(pane);
        // TODO Auto-generated constructor stub
    }

    /**
     * ���˺���Ҫ�أ��õ��μ���������ҵ��δͣ�õ�ĩ������Ҫ��
     *
     * @param item
     *            BillItem,����item
     * @param pk_org
     *            ����pk
     */
    public static void factorRefFilterByORGAndEnable(CardBodyBeforeEditEvent e, BillItem billitem) {
        CardPanelValueUtils util = new CardPanelValueUtils(e.getBillCardPanel());

        String factorchart = null;
        // ҵ�����ڣ���ֵ��19λ������ʱ��
        String businessdatetime = AppUiContext.getInstance().getBusiDate().toString();
        // ������ʱ���ҵ������
        String businessdate = businessdatetime.substring(0, 10);
        String org = e.getContext().getPk_org();

        if (billitem == null) {
            return;
        }

        UIRefPane ref = (UIRefPane) billitem.getComponent();
        FactorRefModel model = (FactorRefModel) ref.getRefModel();
        if (org != null && org.length() > 0) {
            try {
                // �õ���������Ӧ���˲���Ҫ�ر�
                factorchart = FIUtil.getFactorChartByPkStockOrg(org, businessdate);
                // ���ú���Ҫ�ز����еĺ���Ҫ�ر�����������Ҫ�ر���������ɺ���Ҫ�ر��������

            }
            catch (Exception e1) {
                ExceptionUtils.wrappException(e1);
            }

        }
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < util.getRowCount(); i++) {
            if (i != e.getRow() && !StringUtil.isEmptyWithTrim(util.getBodyStringValue(i, e.getKey()))) {
                list.add(util.getBodyStringValue(i, e.getKey()));
            }
        }
        CMSqlBuilder where = new CMSqlBuilder();
        // �����ظ�
        // �����ظ�

        where.append(FactorVO.SEALFLAG + " = 'N'" /* + " and " + FactorVO.ENDFLAG + " = 'Y'" */);

        model.setWherePart(where.toString());

        model.setPk_factorchart(factorchart);
        // ���÷�ĩ��Ҫ�ز���ѡ
        ref.setNotLeafSelectedEnabled(false);
        // ����֧�ֶ�ѡ
        ref.setMultiSelectedEnabled(true);

        // ���˺���Ҫ�أ��ܿط�Χ�ڵĺ���Ҫ��,ĩ����Ϊͣ�õ�Ҫ��
        // FilterFactorRefUtil.factorRefFilterByGk(billitem, e.getContext().getPk_org());

    }

    /**
     * ���˳ɱ�����
     *
     * @param item
     *            BillItem,����item
     * @param pk_org
     *            ����pk
     */
    public static void factorRefFilter(CardBodyBeforeEditEvent e, BillItem item) {
        UIRefPane ref = (UIRefPane) item.getComponent();
        // ����֧�ֶ�ѡ
        ref.setMultiSelectedEnabled(true);
        ref.setPk_org(e.getContext().getPk_org());

        CardPanelValueUtils util = new CardPanelValueUtils(e.getBillCardPanel());
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < util.getRowCount(); i++) {
            if (i != e.getRow() && !StringUtil.isEmptyWithTrim(util.getBodyStringValue(i, e.getKey()))) {
                list.add(util.getBodyStringValue(i, e.getKey()));
            }
        }
        CMSqlBuilder where = new CMSqlBuilder();
        where.append(" enablestate= " + IPubEnumConst.ENABLESTATE_ENABLE);
        ref.setNotLeafSelectedEnabled(true);

    }

    /**
     * ���˺���Ҫ�أ��õ��μ���������ҵ��δͣ�õ�ĩ������Ҫ��
     *
     * @param item
     *            BillItem,����item
     * @param pk_org
     *            ����pk
     */
    public static void factorRefFilterByCostTypeAndEnable(CardBodyBeforeEditEvent e, BillItem billitem) {
        String costtype = String.valueOf(e.getBillCardPanel().getBodyValueAt(e.getRow(), CostTypeVO.CCOSTTYPEID));
        CostTypeVO vo = null;
        String factorchart = null;
        CMSqlBuilder where = new CMSqlBuilder();
        UIRefPane ref = (UIRefPane) billitem.getComponent();
        FactorRefModel model = (FactorRefModel) ref.getRefModel();
        if (billitem == null || costtype == "null") {
            where.append(" 1= 2 and ");
        }
        else {
            try {
                vo =
                        (CostTypeVO) NCLocator.getInstance().lookup(ICostTypePubQueryService.class)
                        .getCostTypeVoByPK(costtype);
                factorchart = vo.getPk_factorchart();
                model.setPk_factorchart(factorchart);
            }
            catch (Exception e1) {
                ExceptionUtils.wrappException(e1);
            }
        }

        where.append(FactorVO.SEALFLAG + " = 'N'");
        model.setWherePart(where.toString());
        // ���÷�ĩ��Ҫ�ز���ѡ
        ref.setNotLeafSelectedEnabled(false);
        // ����֧�ֶ�ѡ
        ref.setMultiSelectedEnabled(true);
    }

}
