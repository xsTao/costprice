package nc.bs.mapub.materialpricebase.rule;

import java.util.HashSet;
import java.util.Set;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMValueCheck;
import nc.bd.framework.db.CMSqlBuilder;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 验证表头价格库编码唯一性
 *
 * @since 6.36
 * @version 2014-11-12 上午10:20:20
 * @author zhangshyb
 */
public class MaterialPriceHeadRepeatRule implements IRule<MaterialPriceBaseAggVO> {

    @Override
    public void process(MaterialPriceBaseAggVO[] vos) {
        if (CMArrayUtil.isEmpty(vos)) {
            return;
        }
        // 1、更新表头价格库编码
        this.updatePriceCodeRule(vos);
    }

    /**
     * 更新表头价格库编码
     *
     * @param vos
     */
    private void updatePriceCodeRule(MaterialPriceBaseAggVO[] vos) {
        Set<String> errorSet = new HashSet<String>();
        ValidationException exception = new ValidationException();
        // 表头
        MaterialPriceBaseHeadVO headVO = (MaterialPriceBaseHeadVO) vos[0].getParent();
        // 价格库编码
        String vpricecode = headVO.getVpricecode();
        // 业务单元
        String pk_org = headVO.getPk_org();
        // 集团
        String pk_group = headVO.getPk_group();
        DataAccessUtils tool = new DataAccessUtils();
        CMSqlBuilder sqlBuilder = new CMSqlBuilder();
        sqlBuilder.append("select count(1) from mapub_materialpricebase where ");
        sqlBuilder.append(MaterialPriceBaseHeadVO.VPRICECODE, vpricecode);
        sqlBuilder.append("and ");
        sqlBuilder.append(MaterialPriceBaseHeadVO.PK_ORG, pk_org);
        sqlBuilder.append("and ");
        sqlBuilder.append(MaterialPriceBaseHeadVO.PK_GROUP, pk_group);
        sqlBuilder.append("and dr=0 ");
        IRowSet set = tool.query(sqlBuilder.toString());
        if (set.next() && set.getInt(0) > 1) {
            // 说明已存在
            errorSet.add(CMMLangConstMaterialPriceBase.getMSGCMATERIALPRICE());
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
