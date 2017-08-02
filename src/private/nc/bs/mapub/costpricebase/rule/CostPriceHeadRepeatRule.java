/**
 *
 */
package nc.bs.mapub.costpricebase.rule;

import java.util.HashSet;
import java.util.Set;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMValueCheck;
import nc.bd.framework.db.CMSqlBuilder;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;
import nc.vo.mapub.costpricebase.entity.CostPriceHeadVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2017��7��28�� ����9:06:59
 * @author Administrator
 */
public class CostPriceHeadRepeatRule implements IRule<CostPriceAggVO> {

    /*
     * (non-Javadoc)
     * @see nc.impl.pubapp.pattern.rule.IRule#process(java.lang.Object[])
     */
    @Override
    public void process(CostPriceAggVO[] vos) {
        // TODO Auto-generated method stub
        if (CMArrayUtil.isEmpty(vos)) {
            return;
        }
        this.updatePriceCodeRule(vos);
    }

    /**
     * ���¼۸�����
     *
     * @param vos
     */
    private void updatePriceCodeRule(CostPriceAggVO[] vos) {
        // TODO Auto-generated method stub
        Set<String> errorSet = new HashSet<String>();
        ValidationException exception = new ValidationException();
        // ��ͷ
        CostPriceHeadVO headVO = (CostPriceHeadVO) vos[0].getParent();
        // �۸�����
        // String vpricecode = headVO.getCcostpriceid();
        // ����ڼ�
        String vperiod = headVO.getVperiod();
        // ���
        String annual = headVO.getAnnual();
        // ҵ��Ԫ
        String pk_org = headVO.getPkOrg();
        // ����
        String pk_group = headVO.getPkGroup();
        // ���������Ϣ
        String msg = "���� " + pk_org;
        DataAccessUtils tool = new DataAccessUtils();
        CMSqlBuilder sqlBuilder = new CMSqlBuilder();
        sqlBuilder.append("select count(1) from mapub_costprice where ");
        // sqlBuilder.append(CostPriceHeadVO.CCOSTPRICEID, vpricecode);
        // sqlBuilder.append("and ");
        if (CMValueCheck.isNotEmpty(vperiod)) {
            sqlBuilder.append(CostPriceHeadVO.VPERIOD, vperiod);
            msg += " +����ڼ�" + vperiod + "�Ѿ����ڣ�" + "Ҫ�󡾹���+����ڼ�Ψһ�����ߡ�����+���Ψһ��";
        }
        else if (CMValueCheck.isNotEmpty(annual)) {
            sqlBuilder.append(CostPriceHeadVO.ANNUAL, annual);
            msg += " +��� " + annual + "�Ѿ����ڣ�" + "Ҫ�󡾹���+����ڼ�Ψһ�����ߡ�����+���Ψһ��";
        }
        sqlBuilder.append("and ");
        sqlBuilder.append(CostPriceHeadVO.PK_ORG, pk_org);
        sqlBuilder.append("and ");
        sqlBuilder.append(CostPriceHeadVO.PK_GROUP, pk_group);
        sqlBuilder.append("and dr=0 ");
        IRowSet set = tool.query(sqlBuilder.toString());
        if (set.next() && set.getInt(0) > 1) {
            // ˵���Ѵ���
            errorSet.add(msg);
        }
        ValidationFailure failure =
                new ValidationFailure(nc.vo.jcom.lang.StringUtil.getUnionStr(errorSet.toArray(new String[0]), "\n", ""));
        if (CMValueCheck.isNotEmpty(failure.getMessage())) {
            exception.addValidationFailure(failure);
        }
        if (exception.getFailures() != null && exception.getFailures().size() > 0) {
            ExceptionUtils.wrappException(exception);
        }
    }
}
