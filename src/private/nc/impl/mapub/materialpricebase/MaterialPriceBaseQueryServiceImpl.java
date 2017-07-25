package nc.impl.mapub.materialpricebase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bd.framework.base.CMValueCheck;
import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.cmpub.business.query.CMBillQuery;
import nc.cmpub.business.query.QueryTempletParam;
import nc.itf.mapub.materialpricebase.IMaterialPriceBaseQueryService;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.pubitf.org.cache.IOrgUnitPubService_C;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.mapub.materialpricebase.entity.CMMCommonConstMaterialPriceBase;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.org.OrgVO;
import nc.vo.org.orgmodel.OrgTypeVO;
import nc.vo.org.util.OrgTypeManager;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.uap.rbac.util.RbacSqlUtils;
import nc.vo.util.SqlWrapper;

/**
 * 材料价格库
 *
 * @since 6.36
 * @version 2014-11-7 下午4:32:30
 * @author zhangchd
 */
public class MaterialPriceBaseQueryServiceImpl implements IMaterialPriceBaseQueryService {

    @Override
    public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws BusinessException {
        try {
            QueryTempletParam param = new QueryTempletParam();
            param.setCmarcostclassid(CMMCommonConstMaterialPriceBase.PK_MARCOSTCLASS);
            param.setCmarMarbasclass(CMMCommonConstMaterialPriceBase.PK_MARBASCLASS);
            param.setCmaterialid(CMMCommonConstMaterialPriceBase.CMATERIALID);
            CMBillQuery<MaterialPriceBaseAggVO> qry =
                    new CMBillQuery<MaterialPriceBaseAggVO>(MaterialPriceBaseAggVO.class, param);

            return qry.queryByQueryScheme(queryScheme);
        }
        catch (Exception e) {
            ExceptionUtils.marsh(e);
        }
        return null;
    }

    /**
     * 查询有权限的组织
     */
    @Override
    public String[] queryPkorgsByOrgType(String orgtypeid, String funcode, String cuserid, String pk_group)
            throws BusinessException {
        OrgTypeVO orgType = OrgTypeManager.getInstance().getOrgTypeByID(orgtypeid);
        String fieldName = orgType.getFieldname();
        String sqlSuf = "";
        if (CMValueCheck.isNotEmpty(fieldName)) {
            sqlSuf = " and org." + fieldName + "='Y' ";
        }
        String sql = RbacSqlUtils.QUERY_PKORGSWITHFUNC_USERPERM_SQL + sqlSuf;
        SqlWrapper wrapper = new SqlWrapper(sql);
        wrapper.bind("funcode", funcode);
        wrapper.bind("cuserid", cuserid);
        wrapper.bind("nowtime", new UFDateTime());
        wrapper.bind("pk_group", pk_group);

        String[] result =
                (String[]) new BaseDAO().executeQuery(wrapper.getSql(), wrapper.getSqlParameter(),
                        new ColumnListProcessor() {
                            private static final long serialVersionUID = 3240526984160436286L;

                            @Override
                            public Object handleResultSet(ResultSet rs) throws SQLException {

                                List<String> result = new ArrayList<String>();
                                while (rs.next()) {
                                    String pk_org = rs.getString("pk_org");
                                    result.add(pk_org);
                                }
                                return result.toArray(new String[result.size()]);
                            }
                        });

        return result;
    }

    /**
     * 根据业务单元OID查找code
     * add by zhangshyb
     *
     * @param pk_orgs 业务单元
     * @return key:code value:oid
     * @throws BusinessException
     */
    @Override
    public Map<String, String> queryOrgCodeOid(String[] pk_orgs) throws BusinessException {
        // key:oid value:code
        Map<String, String> code_oidMap = new HashMap<String, String>();

        OrgVO[] orgVOs = NCLocator.getInstance().lookup(IOrgUnitPubService_C.class).getOrgs(pk_orgs, new String[] {
            OrgVO.PK_ORG, OrgVO.CODE
        });

        // BDAdapter.getIOrgUnitQryService().getOrgs(pk_orgs);
        if (CMValueCheck.isEmpty(orgVOs)) {
            return null;
        }
        for (OrgVO orgVO : orgVOs) {
            if (orgVO == null) {
                continue;
            }
            code_oidMap.put(orgVO.getCode(), orgVO.getPk_org());
        }

        return code_oidMap;

    }
}
