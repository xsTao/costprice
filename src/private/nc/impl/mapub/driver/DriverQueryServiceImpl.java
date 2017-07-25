package nc.impl.mapub.driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nc.bd.business.util.FIUtil;
import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMStringUtil;
import nc.bd.framework.db.CMSqlBuilder;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.itf.mapub.driver.IDriverQueryService;
import nc.itf.org.IOrgConst;
import nc.pubitf.bd.bdactivity.pub.IBDActivityPubQuery;
import nc.pubitf.org.IOrgUnitPubService;
import nc.vo.bd.bdactivity.entity.BDActivityVO;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.allocfac.enumeration.AllocfacEnum;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.DriverQueryCondition;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.resa.factor.FactorAsoaVO;

public class DriverQueryServiceImpl implements IDriverQueryService {

    @Override
    public BDActivityVO[] queryActivity(DriverQueryCondition queryCondition) {
        if (null == queryCondition) {
            return new BDActivityVO[0];
        }
        BDActivityVO[] activityVOs = new BDActivityVO[] {};
        try {
            activityVOs =
                    NCLocator.getInstance().lookup(IBDActivityPubQuery.class)
                            .queryBDActivityVOByOrg(queryCondition.getPk_group(), queryCondition.getPk_org());
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
        return activityVOs;

    }

    @Override
    public AllocfacHeadVO[] queryAllocfacHeadVO(DriverQueryCondition queryCondition) {
        // ��֯����
        String sql = " and " + this.getCommonSql(AllocfacHeadVO.PK_ORG, queryCondition);
        sql = sql + " and " + AllocfacHeadVO.IALLOCTYPE + " <> " + AllocfacEnum.activity.toIntValue();

        return new VOQuery<AllocfacHeadVO>(AllocfacHeadVO.class).query(sql, null);
    }

    @Override
    public List<FactorAsoaVO> queryFactor(DriverQueryCondition queryCondition, UFDate businessDate) {
        if (null == queryCondition || CMStringUtil.isEmpty(queryCondition.getPk_org())) {
            return new ArrayList<FactorAsoaVO>();
        }
        List<FactorAsoaVO> resultList = new ArrayList<FactorAsoaVO>();
        try {
            List<FactorAsoaVO> FactorasoaVOList = FIUtil.queryFactorvos(queryCondition.getPk_org(), new String[] {
                FactorAsoaVO.FACTORCODE, FactorAsoaVO.FACTORNAME, FactorAsoaVO.PK_FACTOR, FactorAsoaVO.ENDFLAG
            }, businessDate);
            for (FactorAsoaVO factorasoaVO : FactorasoaVOList) {
                if (factorasoaVO.getEndflag().booleanValue()) {
                    resultList.add(factorasoaVO);
                }
            }
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }

        return resultList;
    }

    // @Override
    // public List<PriceBaseHeadVO> queryPriceLibrary(DriverQueryCondition queryCondition) {
    // List<PriceBaseHeadVO> objs = new ArrayList<PriceBaseHeadVO>();
    // try {
    // objs =
    // NCLocator.getInstance().lookup(IPriceBasePubQueryServiceForDriver.class)
    // .queryPriceBaseHeadVOByOrg(queryCondition.getPk_org());
    // }
    // catch (BusinessException e) {
    // ExceptionUtils.wrappException(e);
    // }
    // return objs;
    // }

    @Override
    public List<OrgVO> querySaleOrgs(DriverQueryCondition queryCondition) {
        OrgVO[] orgVos = null;
        try {
            orgVos =
                    NCLocator.getInstance().lookup(IOrgUnitPubService.class)
                            .getAllOrgVOSByGroupIDAndOrgTypes(queryCondition.getPk_group(), new String[] {
                                IOrgConst.SALEORGTYPE
                            });
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
        if (CMArrayUtil.isEmpty(orgVos)) {
            return new ArrayList<OrgVO>();
        }
        return Arrays.asList(orgVos);
    }

    /**
     * ������֯���ֶ����ƺ���֯�ֶε�ֵ�õ���֯��sql
     * 
     * @param orgFieldName
     *            ��֯�ֶε�����
     * @param pk_org
     *            ��֯����
     * @return �����֯�ֶε�ֵΪ�շ��� ���ַ��� �����򷵻ع���õ�sql��
     */
    private String getOrgSql(String orgFieldName, String pk_org) {
        String retString = " " + orgFieldName + "='" + pk_org + "'";
        return retString;
    }

    /**
     * ������֯�ֶε����ƺͲ�ѯ�����õ���֯��sql
     * 
     * @param orgFieldName
     *            ��֯�ֶε�����
     * @param queryCondition
     *            ��ѯ����
     * @return �����ѯ����Ϊ�ջ��߲�ѯ�����е���֯�ֶε�ֵΪ�շ��� ���ַ��� �����򷵻ع���õ�sql��
     */
    private String getOrgSql(String orgFieldName, DriverQueryCondition queryCondition) {

        if (queryCondition != null && queryCondition.getPk_org() != null
                && queryCondition.getPk_org().trim().length() > 0) {

            return this.getOrgSql(orgFieldName, queryCondition.getPk_org());

        }

        return "";
    }

    /**
     * ��ȡͨ�õ�sql���֣����� dr=0 ����֯��������
     * 
     * @param orgFieldName
     *            ��֯�ֶε�����
     * @param queryCondition
     *            ��ѯ����
     * @return sql
     */
    private String getCommonSql(String orgFieldName, DriverQueryCondition queryCondition) {
        // ��֯����
        CMSqlBuilder sql = new CMSqlBuilder();
        sql.append(" dr=0 ");
        String orgSql = this.getOrgSql(orgFieldName, queryCondition);
        if (orgSql != null && orgSql.trim().length() > 0) {
            sql.append(" and ");
            sql.append(orgSql);
        }

        return sql.toString();
    }

    @Override
    public DriverVO[] queryDriverByOrg(String pk_org, String sortby) {
        String sql =
                " and (" + DriverVO.PK_ORG + "= '" + pk_org + "' or " + DriverVO.PK_GROUP + "= '"
                        + CMDriverLangConst.GLFLAG + "')";

        String orderby = null;
        if (CMStringUtil.isNotEmpty(sortby)) {
            orderby = " order by " + sortby;
        }
        return new VOQuery<DriverVO>(DriverVO.class).query(sql, orderby);
    }
}
