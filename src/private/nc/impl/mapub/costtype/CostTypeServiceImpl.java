package nc.impl.mapub.costtype;

import java.util.HashSet;
import java.util.Set;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMStringUtil;
import nc.bd.framework.db.CMSqlBuilder;
import nc.bs.bd.baseservice.md.BatchBaseService;
import nc.bs.mapub.costtype.rule.AccPeriodValidateRule;
import nc.bs.mapub.costtype.rule.AsBeginAndAsEndRule;
import nc.bs.mapub.costtype.rule.CostTypeEditableValidateRule;
import nc.bs.mapub.costtype.rule.DocViewValidateRule;
import nc.bs.mapub.costtype.rule.FactorChartEqualValidateRule;
import nc.bs.mapub.costtype.rule.IsReferencedCheckRule;
import nc.bs.mapub.costtype.rule.MaterialPriceSourceNumNullCheck;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.mapub.costtype.ICostTypeService;
import nc.md.MDBaseQueryFacade;
import nc.md.model.MetaDataException;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.mapub.costtype.entity.CMMLangConstCM0502;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.uif2.LoginContext;
import nc.vo.util.VisibleUtil;

/**
 * �ɱ����ͷ���ʵ����
 */
@SuppressWarnings({
    "unchecked", "rawtypes"
})
public class CostTypeServiceImpl extends BatchBaseService<CostTypeVO> implements ICostTypeService {
    /**
     * Ԫ����ID
     */
    private static String mdId;

    static {
        try {
            CostTypeServiceImpl.mdId =
                    MDBaseQueryFacade.getInstance().getBeanByFullClassName(CostTypeVO.class.getName()).getID();
        }
        catch (MetaDataException e) {
        }
    }

    public CostTypeServiceImpl() {
        super(CostTypeServiceImpl.mdId);
    }

    @Override
    public BatchOperateVO batchSaveCostTypeVos(BatchOperateVO batchVO) throws Exception {
        // ΪBatchOperateVO�����е��������޸�VO����ʧЧ�ڼ�Ĳ�ȫ�����Ϊ�գ���ȫ��9999-12
        // this.setEndMonthMaxForAllVOs(batchVO);
        // CheckElementidRule checkElementidRule = new CheckElementidRule();
        // checkElementidRule.process((CostTypeVO[]) batchVO.getAddObjs());
        // checkElementidRule.process((CostTypeVO[]) batchVO.getUpdObjs());
        CostTypeVO[] updVOs = CMArrayUtil.toArray(batchVO.getUpdObjs());
        CostTypeVO[] addVOs = CMArrayUtil.toArray(batchVO.getAddObjs());
        CostTypeVO[] delVOs = CMArrayUtil.toArray(batchVO.getDelObjs());
        CostTypeVO[] combineVos = CMArrayUtil.combineArray(addVOs, updVOs);
        // ɾ������У��
        new IsReferencedCheckRule().process(delVOs);
        // �ɱ༭��У��<�޸�>
        new CostTypeEditableValidateRule().process(updVOs);
        // ���ϼ۸���Դ��Ϊ��У��<����+�޸�>
        new MaterialPriceSourceNumNullCheck().process(combineVos);
        // ��Ч����ʱ��: 00��00��00 ʧЧ���ڣ� 23��59��59У��<����+�޸�>
        new AsBeginAndAsEndRule().process(combineVos);
        // ��Ч���ڡ�=ʧЧ����У��<����+�޸�>
        new AccPeriodValidateRule().process(combineVos);
        // ������Ҫ�ض��ձ���Դ����У��<����+�޸�>
        new DocViewValidateRule().process(combineVos);
        // Ҫ�ر�һ����У��<����+�޸�>
        new FactorChartEqualValidateRule().process(combineVos);
        return super.batchSave(batchVO);
    }

    @Override
    public ISuperVO[] queryCostTypeByDataVisibilitySetting(LoginContext context) throws Exception {
        // String sql = " where pk_group = '" + context.getPk_group() + "' and pk_org = '" + context.getPk_org() + "'";
        String visibleCondition = VisibleUtil.getVisibleCondition(context, CostTypeVO.class);
        return this.queryCostTypeVOsByWhereSql(" where " + visibleCondition);
    }

    @Override
    public ISuperVO[] queryCostTypeVOsByWhereSql(String whereSql) throws Exception {
        VOQuery query = new VOQuery(CostTypeVO.class);
        ISuperVO[] vos =
                query.queryWithWhereKeyWord(whereSql, " order by " + CostTypeVO.PK_ORG + "," + CostTypeVO.VCOSTTYPECODE);
        return vos;
    }

    /**
     * ��������-��ʾʧЧ
     */
    @Override
    public ISuperVO[] queryCostTypeByDate(LoginContext context, UFDate currentDate, UFBoolean isLegal) {
        ISuperVO[] vos = null;
        try {
            vos = this.queryCostType(context, currentDate, isLegal);
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
        return vos;
    }

    @Override
    public ISuperVO[] queryCostType(LoginContext context, UFDate currentDate, UFBoolean isLegal)
            throws BusinessException {
        CMSqlBuilder sbWhere = new CMSqlBuilder();
        sbWhere.append(" where dr=0 ");
        sbWhere.append(" and ");
        sbWhere.append(VisibleUtil.getVisibleCondition(context, CostTypeVO.class));
        if (isLegal.booleanValue()) {
            sbWhere.append(" and ");
            sbWhere.append(" ( ");
            sbWhere.append(CostTypeVO.CENDMONTH + ">='" + currentDate + "'");
            sbWhere.append(")");
        }
        try {
            return this.queryCostTypeVOsByWhereSql(sbWhere.toString());
        }
        catch (Exception e) {
            ExceptionUtils.wrappException(e);
            return null;
        }
    }

    /**
     * ΪBatchOperateVO�����е��������޸�VO����ʧЧ�ڼ�Ĳ�ȫ�����Ϊ�գ���ȫ��9999-12
     */
    public void setEndMonthMaxForAllVOs(BatchOperateVO batchVO) {
        int count = 0;
        // �޸ĵ�VO
        Object[] updateVOs = batchVO.getUpdObjs();
        if (updateVOs != null && updateVOs.length > 0) {
            for (int i = 0; i < updateVOs.length; i++) {
                CostTypeVO temp = (CostTypeVO) updateVOs[i];
                this.setEndMonthMaxForAllVO(temp);
                if (temp.getBdefault().equals(UFBoolean.TRUE)) {
                    count++;
                }
            }
        }
        // ������VO
        Object[] addVOs = batchVO.getAddObjs();
        if (addVOs != null && addVOs.length > 0) {
            for (int i = 0; i < addVOs.length; i++) {
                CostTypeVO temp = (CostTypeVO) addVOs[i];
                this.setEndMonthMaxForAllVO(temp);
                if (temp.getBdefault().equals(UFBoolean.TRUE)) {
                    count++;
                }
            }
        }
        if (count > 1) {
            ExceptionUtils.wrappBusinessException(CMMLangConstCM0502.GET_ERRO_BDEFAULT());
        }
    }

    /**
     * Ϊvo�������ʧЧ�ڼ�Ĳ�ȫ�����Ϊ�գ���ȫ��9999-12
     *
     * @param vo
     *            �ɱ�����VO����
     */
    private void setEndMonthMaxForAllVO(CostTypeVO vo) {
        if (vo == null) {
            return;
        }
        // ���Ϊ�գ��Զ���ȫ
        if (vo.getCendmonth() == null) {
            UFDateTime maxDate = new UFDateTime(CMMLangConstCM0502.MAX_DATE);
            vo.setCendmonth(maxDate.getDate());
        }
    }

    // �жϼ۸���Ƿ����ã����÷���true��δ�����÷���false
    @Override
    public boolean isReferenced(String[] pks) {
        CMSqlBuilder sbSql = new CMSqlBuilder();
        sbSql.select();
        sbSql.append("1");
        sbSql.from(CostTypeVO.getDefaultTableName());
        sbSql.where();
        sbSql.append(" dr=0 and vmaterialpricesourcenum like");
        sbSql.append(" '%");
        sbSql.append(pks[0]);
        sbSql.append("%'");
        DataAccessUtils dao = new DataAccessUtils();
        IRowSet rowset = dao.query(sbSql.toString());
        return rowset.size() > 0 ? true : false;
    }

    // ��ȡ�����õļ۸��pk
    public void getUsedPKs(String priceSourceNum, Set<String> inUsedPKs) {
        if (priceSourceNum == null) {
            return;
        }
        String[] priceSourceNums = priceSourceNum.split(",");
        for (String str : priceSourceNums) {
            if (str.indexOf("[") > 0) {
                str = str.substring(str.indexOf("[") + 1, str.indexOf("]"));
                inUsedPKs.add(str);
            }
        }
    }

    /**
     * �жϸóɱ��������Ƿ����bom����
     */
    @Override
    public Set<String> haveDate() {
        CMSqlBuilder sbSql = new CMSqlBuilder();
        sbSql.select();
        sbSql.append("ccosttypeid ");
        sbSql.from("sca_bom ");
        sbSql.where();
        // sbSql.append("pk_group = '");
        // sbSql.append(AppContext.getInstance().getPkGroup());
        // sbSql.append("' ");
        // sbSql.and();
        // sbSql.append("pk_org = '");
        // sbSql.append(pk_org);
        // sbSql.append("' ");
        // sbSql.and();
        // sbSql.append("ccosttypeid = '");
        // sbSql.append(ccosttypeid);
        // sbSql.append("' ");
        // sbSql.and();
        sbSql.append("dr = 0");
        DataAccessUtils dao = new DataAccessUtils();
        IRowSet rowset = dao.query(sbSql.toString());
        String[] ids = rowset.toOneDimensionStringArray();
        Set<String> idSet = new HashSet<String>();
        if (CMArrayUtil.isNotEmpty(ids)) {
            for (String id : ids) {
                idSet.add(id);
            }
            return idSet;
        }
        return null;
    }

    /**
     * �жϸóɱ��������Ƿ����bom�������Ѽ��������
     */
    @Override
    public Set<String> haveCalcDate() {
        CMSqlBuilder sbSql = new CMSqlBuilder();
        sbSql.select();
        sbSql.append("ccosttypeid ");
        sbSql.from("sca_bom ");
        sbSql.where();
        // sbSql.append("pk_group = '");
        // sbSql.append(AppContext.getInstance().getPkGroup());
        // sbSql.append("' ");
        // sbSql.and();
        // sbSql.append("pk_org = '");
        // sbSql.append(pk_org);
        // sbSql.append("' ");
        // sbSql.and();
        // sbSql.append("ccosttypeid = '");
        // sbSql.append(ccosttypeid);
        // sbSql.append("' ");
        // sbSql.and();
        sbSql.append("icalcstatus = 2 and dr = 0");
        DataAccessUtils dao = new DataAccessUtils();
        IRowSet rowset = dao.query(sbSql.toString());
        String[] ids = rowset.toOneDimensionStringArray();
        Set<String> idSet = new HashSet<String>();
        if (CMArrayUtil.isNotEmpty(ids)) {
            for (String id : ids) {
                idSet.add(id);
            }
            return idSet;
        }
        return null;
    }

    /**
     * �жϲ��ϼ۸���Դ�Ƿ��޸�
     */
    @Override
    public boolean isMaterialSrcChanged(String ccosttypeid, String scrapfactor, String shrinkfactor,
            String pk_elementsystem, String pk_factorchart, String pk_materialdocview, String vmaterialpricesourcenum) {

        CMSqlBuilder sbSql = new CMSqlBuilder();
        sbSql.select();
        sbSql.append(CostTypeVO.BSCRAPFACTOR);
        sbSql.append(",");
        sbSql.append(CostTypeVO.BSHRINKFACTOR);
        sbSql.append(",");
        sbSql.append(CostTypeVO.PK_ELEMENTSYSTEM);
        sbSql.append(",");
        sbSql.append(CostTypeVO.PK_FACTORCHART);
        sbSql.append(",");
        sbSql.append(CostTypeVO.PK_MATERIALDOCVIEW);
        sbSql.append(",");
        sbSql.append(CostTypeVO.VMATERIALPRICESOURCENUM);
        sbSql.from(CostTypeVO.getDefaultTableName());
        sbSql.where();
        sbSql.append("ccosttypeid = '");
        sbSql.append(ccosttypeid);
        sbSql.append("' ");
        sbSql.and();
        sbSql.append("dr = 0");
        DataAccessUtils dao = new DataAccessUtils();
        IRowSet rowset = dao.query(sbSql.toString());
        String[][] data = new String[1][6];
        data = rowset.toTwoDimensionStringArray();
        String old_scrapfactor = data[0][0];
        String old_shrinkfactor = data[0][1];
        String old_pk_elementsystem = data[0][2];
        String old_pk_factorchart = data[0][3];
        String old_pk_materialdocview = data[0][4];
        String old_vmaterialpricesourcenum = data[0][5];
        if (CMStringUtil.isEqual(old_scrapfactor, scrapfactor) && CMStringUtil.isEqual(old_shrinkfactor, shrinkfactor)
                && CMStringUtil.isEqual(old_pk_elementsystem, pk_elementsystem)
                && CMStringUtil.isEqual(old_pk_factorchart, pk_factorchart)
                && CMStringUtil.isEqual(old_pk_materialdocview, pk_materialdocview)
                && CMStringUtil.isEqual(old_vmaterialpricesourcenum, vmaterialpricesourcenum)) {
            return false;
        }
        return true;
    }

    /**
     * ���Ƿ�Ʒϵ��scrapfactor���������ϵ��shrinkfactor������Ҫ����ϵpk_elementsystem������Ҫ�ر�pk_factorchart��Ҫ�������϶��ձ�pk_materialdocview�Ƿ��޸�
     */
    @Override
    public boolean isValueChanged(String ccosttypeid, String scrapfactor, String shrinkfactor, String pk_elementsystem,
            String pk_factorchart, String pk_materialdocview) {
        CMSqlBuilder sbSql = new CMSqlBuilder();
        sbSql.select();
        sbSql.append(CostTypeVO.BSCRAPFACTOR);
        sbSql.append(",");
        sbSql.append(CostTypeVO.BSHRINKFACTOR);
        sbSql.append(",");
        sbSql.append(CostTypeVO.PK_ELEMENTSYSTEM);
        sbSql.append(",");
        sbSql.append(CostTypeVO.PK_FACTORCHART);
        sbSql.append(",");
        sbSql.append(CostTypeVO.PK_MATERIALDOCVIEW);
        sbSql.from(CostTypeVO.getDefaultTableName());
        sbSql.where();
        sbSql.append("ccosttypeid = '");
        sbSql.append(ccosttypeid);
        sbSql.append("' ");
        sbSql.and();
        sbSql.append("dr = 0");
        DataAccessUtils dao = new DataAccessUtils();
        IRowSet rowset = dao.query(sbSql.toString());
        String[][] data = new String[1][5];
        data = rowset.toTwoDimensionStringArray();
        String old_scrapfactor = data[0][0];
        String old_shrinkfactor = data[0][1];
        String old_pk_elementsystem = data[0][2];
        String old_pk_factorchart = data[0][3];
        String old_pk_materialdocview = data[0][4];
        if (CMStringUtil.isEqual(old_scrapfactor, scrapfactor) && CMStringUtil.isEqual(old_shrinkfactor, shrinkfactor)
                && CMStringUtil.isEqual(old_pk_elementsystem, pk_elementsystem)
                && CMStringUtil.isEqual(old_pk_factorchart, pk_factorchart)
                && CMStringUtil.isEqual(old_pk_materialdocview, pk_materialdocview)) {
            return false;
        }
        return true;
    }
}
