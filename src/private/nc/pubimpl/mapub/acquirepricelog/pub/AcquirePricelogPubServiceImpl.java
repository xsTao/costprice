/**
 *
 */
package nc.pubimpl.mapub.acquirepricelog.pub;

import nc.bd.framework.db.CMSqlBuilder;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.mapub.acquirepricelog.pub.IAcquirePricelogPubService;
import nc.vo.mapub.acquirepricelog.entity.AcquirePriceLogVO;
import nc.vo.mapub.framework.entity.acquireprice.AcquirePriceParam;
import nc.vo.mapub.framework.entity.acquireprice.AcquireTypeEnum;
import nc.vo.pub.BusinessException;

/**
 * @since v6.3
 * @version 2014-12-22 ÉÏÎç8:31:39
 * @author shuzhan
 */
public class AcquirePricelogPubServiceImpl implements IAcquirePricelogPubService {

    @Override
    public void deletePriceLog(AcquirePriceParam priceParam) throws BusinessException {
        CMSqlBuilder deleteSql = new CMSqlBuilder();
        deleteSql.delete();
        deleteSql.append(AcquirePriceLogVO.getDefaultTableName());
        deleteSql.where();
        deleteSql.append(AcquirePriceLogVO.VACQUIRETYPE, priceParam.getAcquireType().toString());
        deleteSql.and();
        deleteSql.append(AcquirePriceLogVO.PK_GROUP, priceParam.getPk_group());
        deleteSql.and();
        deleteSql.append(AcquirePriceLogVO.PK_ORG, priceParam.getPk_org());
        if (AcquireTypeEnum.STDCOSTPROCESS_MAT.equals(priceParam.getAcquireType())) {
            deleteSql.and();
            deleteSql.append(AcquirePriceLogVO.CCOSTTYPEID, priceParam.getCcosttypeid());
        }
        else if (AcquireTypeEnum.STDCOSTPROCESS_COST.equals(priceParam.getAcquireType())) {
            deleteSql.and();
            deleteSql.append(AcquirePriceLogVO.CCOSTTYPEID, priceParam.getCcosttypeid());
        }
        else if (AcquireTypeEnum.MATPRICEBASE.equals(priceParam.getAcquireType())) {
            deleteSql.and();
            deleteSql.append(AcquirePriceLogVO.CMATERIALPRICEID, priceParam.getCmaterialpriceid());
        }
        else if (AcquireTypeEnum.COSTPRICEBASE.equals(priceParam.getAcquireType())) {
            deleteSql.and();
            deleteSql.append(AcquirePriceLogVO.CMATERIALPRICEID, priceParam.getCmaterialpriceid());
        }
        DataAccessUtils util = new DataAccessUtils();
        util.update(deleteSql.toString());
    }

    @Override
    public AcquirePriceLogVO[] queryPriceLogVOSByCondition(AcquirePriceParam priceParam) throws BusinessException {
        VOQuery<AcquirePriceLogVO> voQuery = new VOQuery<AcquirePriceLogVO>(AcquirePriceLogVO.class);
        CMSqlBuilder whereSql = new CMSqlBuilder();
        whereSql.and();
        whereSql.append(AcquirePriceLogVO.VACQUIRETYPE, priceParam.getAcquireType().toString());
        whereSql.and();
        whereSql.append(AcquirePriceLogVO.PK_GROUP, priceParam.getPk_group());
        whereSql.and();
        whereSql.append(AcquirePriceLogVO.PK_ORG, priceParam.getPk_org());
        if (AcquireTypeEnum.STDCOSTPROCESS_MAT.equals(priceParam.getAcquireType())) {
            whereSql.and();
            whereSql.append(AcquirePriceLogVO.CCOSTTYPEID, priceParam.getCcosttypeid());
        }
        else if (AcquireTypeEnum.STDCOSTPROCESS_COST.equals(priceParam.getAcquireType())) {
            whereSql.and();
            whereSql.append(AcquirePriceLogVO.CCOSTTYPEID, priceParam.getCcosttypeid());
        }
        else if (AcquireTypeEnum.MATPRICEBASE.equals(priceParam.getAcquireType())) {
            whereSql.and();
            whereSql.append(AcquirePriceLogVO.CMATERIALPRICEID, priceParam.getCmaterialpriceid());
        }
        else if (AcquireTypeEnum.COSTPRICEBASE.equals(priceParam.getAcquireType())) {
            whereSql.and();
            whereSql.append(AcquirePriceLogVO.CMATERIALPRICEID, priceParam.getCmaterialpriceid());
        }
        AcquirePriceLogVO[] logvos = voQuery.query(whereSql.toString(), this.getOrderSql());
        return logvos;
    }

    private String getOrderSql() {
        CMSqlBuilder orderSql = new CMSqlBuilder();
        orderSql.append(" order by ");
        orderSql.append(AcquirePriceLogVO.FINSTORAGETYPE + ",");
        orderSql.append(AcquirePriceLogVO.CCOSTCENTERID + ",");
        orderSql.append(AcquirePriceLogVO.CMATERIALID + ",");
        orderSql.append(AcquirePriceLogVO.CPROJECTID + ",");
        orderSql.append(AcquirePriceLogVO.CVENDORID + ",");
        orderSql.append(AcquirePriceLogVO.CPRODUCTORID + ",");
        orderSql.append(AcquirePriceLogVO.VFREE1 + ",");
        orderSql.append(AcquirePriceLogVO.VFREE2 + ",");
        orderSql.append(AcquirePriceLogVO.VFREE3 + ",");
        orderSql.append(AcquirePriceLogVO.VFREE4 + ",");
        orderSql.append(AcquirePriceLogVO.VFREE5 + ",");
        orderSql.append(AcquirePriceLogVO.VFREE6 + ",");
        orderSql.append(AcquirePriceLogVO.VFREE7 + ",");
        orderSql.append(AcquirePriceLogVO.VFREE8 + ",");
        orderSql.append(AcquirePriceLogVO.VFREE9 + ",");
        orderSql.append(AcquirePriceLogVO.VFREE10 + ",");
        orderSql.append(AcquirePriceLogVO.CBCOSTCENTERID + ",");
        orderSql.append(AcquirePriceLogVO.CBMATERIALID + ",");
        orderSql.append(AcquirePriceLogVO.CBPROJECTID + ",");
        orderSql.append(AcquirePriceLogVO.CBVENDORID + ",");
        orderSql.append(AcquirePriceLogVO.CBPRODUCTORID + ",");
        orderSql.append(AcquirePriceLogVO.VBFREE1 + ",");
        orderSql.append(AcquirePriceLogVO.VBFREE2 + ",");
        orderSql.append(AcquirePriceLogVO.VBFREE3 + ",");
        orderSql.append(AcquirePriceLogVO.VBFREE4 + ",");
        orderSql.append(AcquirePriceLogVO.VBFREE5 + ",");
        orderSql.append(AcquirePriceLogVO.VBFREE6 + ",");
        orderSql.append(AcquirePriceLogVO.VBFREE7 + ",");
        orderSql.append(AcquirePriceLogVO.VBFREE8 + ",");
        orderSql.append(AcquirePriceLogVO.VBFREE9 + ",");
        orderSql.append(AcquirePriceLogVO.VBFREE10 + ",");
        orderSql.append(AcquirePriceLogVO.CELEMENTID + ",");
        orderSql.append(AcquirePriceLogVO.CACTIVITYID + ",");
        orderSql.append(AcquirePriceLogVO.ISOURCETYPE + ",");
        orderSql.append(AcquirePriceLogVO.PRIORITYLEVEL);
        return orderSql.toString();
    }

    @Override
    public void deletePriceLog() throws BusinessException {
        CMSqlBuilder deleteSql = new CMSqlBuilder();
        deleteSql.delete();
        deleteSql.append(AcquirePriceLogVO.getDefaultTableName());
        DataAccessUtils util = new DataAccessUtils();
        util.update(deleteSql.toString());
    }

}
