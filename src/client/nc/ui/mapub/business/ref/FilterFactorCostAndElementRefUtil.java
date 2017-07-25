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
 * 成本结转定义和联副分离定义成本中心和核算要素参照
 *
 * @since 6.0
 * @version 2012-3-9 上午09:46:40
 * @author chengjieb
 */
public class FilterFactorCostAndElementRefUtil extends CMFilterDefaultRefUtils {

    public FilterFactorCostAndElementRefUtil(UIRefPane pane) {
        super(pane);
        // TODO Auto-generated constructor stub
    }

    /**
     * 过滤核算要素，得到次级，不是作业，未停用的末级核算要素
     *
     * @param item
     *            BillItem,参照item
     * @param pk_org
     *            工厂pk
     */
    public static void factorRefFilterByORGAndEnable(CardBodyBeforeEditEvent e, BillItem billitem) {
        CardPanelValueUtils util = new CardPanelValueUtils(e.getBillCardPanel());

        String factorchart = null;
        // 业务日期，此值有19位，包括时间
        String businessdatetime = AppUiContext.getInstance().getBusiDate().toString();
        // 不包括时间的业务日期
        String businessdate = businessdatetime.substring(0, 10);
        String org = e.getContext().getPk_org();

        if (billitem == null) {
            return;
        }

        UIRefPane ref = (UIRefPane) billitem.getComponent();
        FactorRefModel model = (FactorRefModel) ref.getRefModel();
        if (org != null && org.length() > 0) {
            try {
                // 得到工厂所对应主账簿的要素表
                factorchart = FIUtil.getFactorChartByPkStockOrg(org, businessdate);
                // 设置核算要素参照中的核算要素表主键，核算要素编码和名称由核算要素表主键获得

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
        // 过滤重复
        // 过滤重复

        where.append(FactorVO.SEALFLAG + " = 'N'" /* + " and " + FactorVO.ENDFLAG + " = 'Y'" */);

        model.setWherePart(where.toString());

        model.setPk_factorchart(factorchart);
        // 设置非末级要素不可选
        ref.setNotLeafSelectedEnabled(false);
        // 参照支持多选
        ref.setMultiSelectedEnabled(true);

        // 过滤核算要素，管控范围内的核算要素,末级，为停用的要素
        // FilterFactorRefUtil.factorRefFilterByGk(billitem, e.getContext().getPk_org());

    }

    /**
     * 过滤成本中心
     *
     * @param item
     *            BillItem,参照item
     * @param pk_org
     *            工厂pk
     */
    public static void factorRefFilter(CardBodyBeforeEditEvent e, BillItem item) {
        UIRefPane ref = (UIRefPane) item.getComponent();
        // 参照支持多选
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
     * 过滤核算要素，得到次级，不是作业，未停用的末级核算要素
     *
     * @param item
     *            BillItem,参照item
     * @param pk_org
     *            工厂pk
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
        // 设置非末级要素不可选
        ref.setNotLeafSelectedEnabled(false);
        // 参照支持多选
        ref.setMultiSelectedEnabled(true);
    }

}
