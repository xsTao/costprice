/**
 *
 */
package nc.impl.mapub.costpricebase;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.mapub.costpricebase.ICostPriceBasePaginationService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;
import nc.vo.mapub.costpricebase.entity.CostPriceHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.bill.pagination.util.PaginationUtils;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

/**
 * @since v6.3
 * @version 2017��8��2�� ����7:11:46
 * @author Administrator
 */
public class CostPricePaginationServiceImpl implements ICostPriceBasePaginationService {

    /*
     * (non-Javadoc)
     * @see
     * nc.itf.mapub.costpricebase.ICostPriceBasePaginationService#queryPKs(nc.ui.querytemplate.querytree.IQueryScheme)
     */
    @Override
    public String[] queryPKs(IQueryScheme queryScheme) throws BusinessException {
        // try {
        // QueryTempletParam param = new QueryTempletParam();
        // // param.setPk_org(CMMCommonConstCostPriceBase.PK_ORG);
        // // param.setCperiod(CMMCommonConstCostPriceBase.VPERIOD);
        // // param.setCproductid(CMMCommonConstCostPriceBase.VPRODUCTCODE);
        // param.setPk_group(CMMCommonConstCostPriceBase.PK_ORG);
        // CMBillQuery<CostPriceAggVO> qry = new CMBillQuery<CostPriceAggVO>(CostPriceAggVO.class, param);
        // Object[] rets = qry.queryByQueryScheme(queryScheme);
        // return (String[]) rets;
        // }
        // catch (Exception e) {
        // ExceptionUtils.marsh(e);
        // }
        // return null;
        StringBuffer sql = new StringBuffer();
        QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
        // Ĭ����֯Ȩ��
        processor.appendFuncPermissionOrgSql();
        // �����������
        String mainAlias = processor.getMainTableAlias();

        sql.append("select ");
        sql.append(mainAlias);
        sql.append(".");
        sql.append(CostPriceHeadVO.CCOSTPRICEID);
        sql.append(processor.getFinalFromWhere()); // ��üӹ�֮�����յ�sql
        sql.append(" order by ").append(CostPriceHeadVO.CREATETIME);

        DataAccessUtils dao = new DataAccessUtils();
        IRowSet rowSet = dao.query(sql.toString());
        String[] keys = rowSet.toOneDimensionStringArray(); // ת��Ϊһά�ַ�������

        return keys;
    }

    /*
     * (non-Javadoc)
     * @see nc.itf.mapub.costpricebase.ICostPriceBasePaginationService#queryBillByPK(java.lang.String[])
     */
    @Override
    public CostPriceAggVO[] queryBillByPK(String[] pks) throws BusinessException {
        // TODO Auto-generated method stub
        CostPriceAggVO[] bills = null;
        BillQuery<CostPriceAggVO> billQuery = new BillQuery<CostPriceAggVO>(CostPriceAggVO.class);
        bills = billQuery.query(pks);
        return PaginationUtils.filterNotExistBills(bills, pks);
    }

}