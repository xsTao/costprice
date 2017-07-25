package nc.impl.mapub.allocfac;

import nc.bd.framework.db.CMSqlBuilder;
import nc.cmpub.framework.batchlimit.MABatchLimitUtil;
import nc.impl.pubapp.pattern.data.bill.EfficientBillQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.mapub.allocfac.IAllocfacQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

/**
 * ����ϵ����ѯserviceimpl
 */
public class AllocfacQueryServiceImpl implements IAllocfacQueryService {
    /**
     * ����ϵ����ѯ
     *
     * @param whereSql
     *            ��ѯ����
     * @param orderPaths
     *            �����ֶ�
     * @return ��ѯ���
     * @throws BusinessException
     *             �쳣
     */
    private Object[] queryByWhereSql(String whereSql, String[] orderPaths) throws BusinessException {
        // 3 ���в�ѯ
        EfficientBillQuery<AllocfacAggVO> query = new EfficientBillQuery<AllocfacAggVO>(AllocfacAggVO.class);
        AllocfacAggVO[] allocfacAggVOS = query.query(whereSql.toString());
        return allocfacAggVOS;
    }

    @Override
    public Object[] queryAllocByHeadVO(AllocfacHeadVO headVO, String[] orderPath) throws BusinessException {
        if (headVO == null) {
            return this.queryByWhereSql(" from " + AllocfacHeadVO.getDefaultTableName() + " where dr=0 ", orderPath);
        }
        String[] fieldNames = headVO.getAttributeNames();
        if (fieldNames == null) {
            return null;
        }

        CMSqlBuilder CMSqlBuilder = new CMSqlBuilder();
        CMSqlBuilder.append(" from ");
        CMSqlBuilder.append(AllocfacHeadVO.getDefaultTableName());
        CMSqlBuilder.append(" where dr=0 ");

        for (int i = 0; i < fieldNames.length; i++) {
            Object odata = headVO.getAttributeValue(fieldNames[i]);
            // ���� ����Ϊ��ʱ����ʾ����
            if (odata == null && fieldNames[i].equalsIgnoreCase(AllocfacHeadVO.PK_ORG)) {
                CMSqlBuilder.append(" and ");
                CMSqlBuilder.appendIDIsNull(AllocfacHeadVO.PK_ORG);
            }
            if (odata != null) {
                CMSqlBuilder.append(" and ");
                if (AllocfacHeadVO.IALLOCTYPE.equals(fieldNames[i])) {
                    // �ַ��� ���⴦��һ��
                    CMSqlBuilder.append(fieldNames[i], (Integer) odata);
                }
                else {
                    CMSqlBuilder.append(fieldNames[i], odata.toString());
                }
            }
        }
        return this.queryByWhereSql(CMSqlBuilder.toString(), orderPath);
    }

    @Override
    public Object[] queryByQueryScheme(IQueryScheme queryScheme) {
        AllocfacAggVO[] vos = null;
        String[] ids =
                this.queryBillIDs(queryScheme, new AllocfacHeadVO().getMetaData().getPrimaryAttribute().getName());
        if (ids == null || ids.length == 0) {
            return null;
        }
        VOQuery<AllocfacHeadVO> query = new VOQuery<AllocfacHeadVO>(AllocfacHeadVO.class);
        AllocfacHeadVO[] heads = query.query(ids);
        vos = new AllocfacAggVO[heads.length];
        for (int i = 0; i < heads.length; i++) {
            vos[i] = new AllocfacAggVO();
            vos[i].setParent(heads[i]);
            if (i == 0) {
                AllocfacItemVO[] items1 = this.queryAllocfacItemVOByParentID(heads[i].getCallocfacid());
                vos[i].setChildren(vos[i].getMetaData().getChildren()[0], items1);
            }
        }
        return vos;
    }

    /**
     * �������������õ��ӱ�VO
     *
     * @param parentID
     * @return
     */
    protected AllocfacItemVO[] queryAllocfacItemVOByParentID(String parentID) {
        String sql =
                " and " + new AllocfacHeadVO().getMetaData().getPrimaryAttribute().getName() + " ='" + parentID + "'";
        VOQuery<AllocfacItemVO> query = new VOQuery<AllocfacItemVO>(AllocfacItemVO.class);
        return query.query(sql, null);
    }

    /**
     * ����sql��ѯ��Ӧ������ID
     *
     * @param queryScheme
     *            ��ѯ����
     */
    protected String[] queryBillIDs(IQueryScheme queryScheme, String idName) {
        String sql = this.buildQuerySql(queryScheme, idName);
        DataAccessUtils utils = new DataAccessUtils();
        IRowSet rowset = utils.query(sql);
        if (rowset == null || rowset.size() == 0) {
            return null;
        }
        return rowset.toOneDimensionStringArray();
    }

    /**
     * ����ǰ̨�������Ĳ�ѯ�����γ������Ĳ�ѯID��sql���
     *
     * @param queryScheme
     *            ��ѯ����
     * @return
     */
    private String buildQuerySql(IQueryScheme queryScheme, String idName) {
        QuerySchemeProcessor qrySchemeProcessor = new QuerySchemeProcessor(queryScheme);
        String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
        // ��Ӽ����޶�
        qrySchemeProcessor.appendCurrentGroup();
        // ��ѯ��Ȩ�޵���֯
        qrySchemeProcessor.appendFuncPermissionOrgSql();
        StringBuffer sql = new StringBuffer();
        sql.append("select distinct ");
        sql.append(mainTableAlias + "." + idName);
        sql.append(" ");
        sql.append(qrySchemeProcessor.getFinalFromWhere());
        return sql.toString();
    }

    @Override
    public void cleanBatchLimit() {
        MABatchLimitUtil.getInstance().doClean();
    }
}
