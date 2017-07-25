package nc.pubimpl.mapub.costtype.cm.pub;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bd.framework.db.CMSqlBuilder;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.pubitf.mapub.costtype.pub.ICostTypePubQueryService;
import nc.ui.dbcache.DBCacheQueryFacade;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

@SuppressWarnings({
    "rawtypes", "unchecked"
})
public class CostTypePubQueryServiceImpl implements ICostTypePubQueryService {
    @Override
    public ISuperVO getCostTypeVoByPK(String costTypePK) {
        if (costTypePK == null) {
            return null;
        }
        String where = " where dr=0 and  " + CostTypeVO.CCOSTTYPEID + "='" + costTypePK + "'";
        try {
            ISuperVO[] results = this.queryCostTypeVOsByWhereSql(where);
            if (results != null && results.length > 0) {
                return results[0];
            }
        }
        catch (Exception e) {
            ExceptionUtils.wrappException(e);
        }
        return null;
    }

    @Override
    public Map<String, String> getCostTypeMapByPK(String[] costTypePKS) {
        if (null == costTypePKS || costTypePKS.length == 0) {
            return null;
        }
        Map<String, String> costTypeToCode = new HashMap<String, String>();
        CMSqlBuilder sql = new CMSqlBuilder();
        sql.append(" select ");
        sql.append(" a." + CostTypeVO.CCOSTTYPEID);
        sql.append(" , ");
        sql.append(" a." + CostTypeVO.VCOSTTYPECODE);
        sql.append(" from ");
        sql.append(CostTypeVO.getDefaultTableName() + " a");
        sql.append(" where ");
        sql.append("a." + CostTypeVO.CCOSTTYPEID, costTypePKS);
        sql.append(" and ");
        sql.append("a.dr", 0);

        // ≤È—Ø
        IRowSet rowSet = new DataAccessUtils().query(sql.toString());
        if (rowSet == null || rowSet.size() == 0) {
            return costTypeToCode;
        }
        while (rowSet.next()) {
            String costypeId = rowSet.getString(0);
            String costypeCode = rowSet.getString(1);
            costTypeToCode.put(costypeId, costypeCode);
        }
        return costTypeToCode;
    }

    @Override
    public ISuperVO getDefaultCostType(String pk_org, String accPeriod) {
        StringBuffer sql = new StringBuffer();
        sql.append(" where dr=0 ");
        sql.append(" and ");
        sql.append(CostTypeVO.PK_ORG + "='" + pk_org + "'");
        // sql.append(" and ");
        // sql.append(CostTypeVO.BDEFAULT + " = 'Y' ");
        sql.append(" and ");
        sql.append(CostTypeVO.CBEGINMONTH + "<='" + accPeriod + "'");// add by zhangchdV63SP
        sql.append(" and ");
        sql.append("(");
        sql.append(CostTypeVO.CENDMONTH + ">='" + accPeriod + "'");
        sql.append(" or ");
        CMSqlBuilder sqlbuBuilder = new CMSqlBuilder();
        sqlbuBuilder.appendIDIsNull(CostTypeVO.CENDMONTH);
        sql.append(sqlbuBuilder.toString() + ")");
        try {
            ISuperVO[] results = this.queryCostTypeVOsByWhereSql(sql.toString());
            if (results != null && results.length > 0) {
                return results[0];
            }
        }
        catch (Exception e) {
            ExceptionUtils.wrappException(e);
        }
        return null;
    }

    @Override
    public ISuperVO[] getAllCostType(String pk_org, String accPeriod) {
        StringBuffer sql = new StringBuffer();
        sql.append(" where dr=0 ");
        sql.append(" and ");
        sql.append(CostTypeVO.PK_ORG + "='" + pk_org + "'");
        sql.append(" and ");
        sql.append(CostTypeVO.BCOMPUTE + " = 'Y' ");
        sql.append(" and ");
        sql.append(CostTypeVO.CBEGINMONTH + "<='" + accPeriod + "'");// add by zhangchdV63SP
        sql.append(" and ");
        sql.append("(");
        sql.append(CostTypeVO.CENDMONTH + ">='" + accPeriod + "'");
        sql.append(" or ");
        CMSqlBuilder sqlbuBuilder = new CMSqlBuilder();
        sqlbuBuilder.appendIDIsNull(CostTypeVO.CENDMONTH);
        sql.append(sqlbuBuilder.toString() + ")");
        try {
            ISuperVO[] results = this.queryCostTypeVOsByWhereSql(sql.toString());
            return results;
        }
        catch (Exception e) {
            ExceptionUtils.wrappException(e);
        }
        return null;
    }

    @Override
    public ISuperVO getDefaultCostType_C(String pk_org, String accPeriod) {
        CMSqlBuilder sql = new CMSqlBuilder();
        sql.append(" select * from ");
        sql.append(CostTypeVO.getDefaultTableName());
        sql.append(" where ");
        sql.append(" dr=0 ");
        sql.append(" and ");
        sql.append(CostTypeVO.PK_ORG + "='" + pk_org + "'");
        // sql.append(" and ");
        // sql.append(CostTypeVO.BDEFAULT + " = 'Y' ");
        sql.append(" and ");
        sql.append("(");
        sql.append(CostTypeVO.CENDMONTH + ">='" + accPeriod + "'");
        sql.append(" or ");
        sql.appendIDIsNull(CostTypeVO.CENDMONTH);
        sql.append(")");
        try {

            List<ISuperVO> results =
                    (List<ISuperVO>) DBCacheQueryFacade.runQuery(sql.toString(), new SQLParameter(),
                            new BeanListProcessor(CostTypeVO.class));
            if (results != null && results.size() > 0) {
                return results.get(0);
            }
            return null;
        }
        catch (Exception e) {
            ExceptionUtils.wrappException(e);
            return null;
        }
    }

    private ISuperVO[] queryCostTypeVOsByWhereSql(String whereSql) throws Exception {
        VOQuery query = new VOQuery(CostTypeVO.class);
        ISuperVO[] vos = query.queryWithWhereKeyWord(whereSql, " order by " + CostTypeVO.VCOSTTYPECODE);
        return vos;
    }

    @Override
    public ISuperVO getDefaultCostTypeByPeriod(String pk_org, String cPeriod) {
        CMSqlBuilder sql = new CMSqlBuilder();
        sql.append(" select * from ");
        sql.append(CostTypeVO.getDefaultTableName());
        sql.append(" where ");
        sql.append(" dr=0 ");
        sql.append(" and ");
        sql.append(CostTypeVO.BDEFAULT + " = 'Y' ");
        sql.append(" and ");
        sql.append(CostTypeVO.PK_ORG + "='" + pk_org + "'");
        sql.append(" and ");
        sql.append(CostTypeVO.CBEGINMONTH + "<='" + cPeriod + "'");
        sql.append(" and ");
        sql.append(" ( ");
        sql.append(CostTypeVO.CENDMONTH + ">='" + cPeriod + "'");
        sql.append(" or ");
        sql.appendIDIsNull(CostTypeVO.CENDMONTH);
        sql.append(" ) ");
        try {
            List<ISuperVO> results =
                    (List<ISuperVO>) DBCacheQueryFacade.runQuery(sql.toString(), new SQLParameter(),
                            new BeanListProcessor(CostTypeVO.class));
            if (null != results && results.size() > 0) {
                return results.get(0);
            }
            return null;
        }
        catch (Exception e) {
            ExceptionUtils.wrappException(e);
            return null;
        }
    }

    @Override
    public Boolean isCostTypeLegal(String pk_org, String cPeriod, String costtype, String pk_group) {
        CMSqlBuilder sql = new CMSqlBuilder();
        sql.append(" select * from ");
        sql.append(CostTypeVO.getDefaultTableName());
        sql.append(" dr=0 ");
        sql.append(" and ( ");
        sql.append(CostTypeVO.PK_ORG + "='" + pk_org + "'");
        sql.append(" or ");
        sql.append(CostTypeVO.PK_GROUP + "='" + pk_group + "' )");
        sql.append(" and ");
        sql.append(CostTypeVO.CCOSTTYPEID + "='" + costtype + "'");
        sql.append(" and ");
        sql.append(CostTypeVO.CBEGINMONTH + "<='" + cPeriod + "'");
        sql.append(" and ");
        sql.append("(");
        sql.append(CostTypeVO.CENDMONTH + ">='" + cPeriod + "'");
        sql.append(" or ");
        sql.appendIDIsNull(CostTypeVO.CENDMONTH);
        sql.append(")");
        try {
            List<ISuperVO> results =
                    (List<ISuperVO>) DBCacheQueryFacade.runQuery(sql.toString(), new SQLParameter(),
                            new BeanListProcessor(CostTypeVO.class));

            if (null != results && results.size() > 0) {
                return true;
            }
            return false;
        }
        catch (Exception e) {
            ExceptionUtils.wrappException(e);
            return false;
        }
    }
}
