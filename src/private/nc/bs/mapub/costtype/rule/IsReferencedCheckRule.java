/**
 *
 */
package nc.bs.mapub.costtype.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.db.CMSqlBuilder;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2015年6月18日 下午3:52:28
 * @author zhangshyb
 */
public class IsReferencedCheckRule implements IRule<CostTypeVO> {

    @Override
    public void process(CostTypeVO[] vos) {
        // 校验：要素与物料对照表中要素所对应的要素表与单据中要素表保持一致
        try {
            if (CMArrayUtil.isNotEmpty(vos)) {
                // 首先进行数据校验，判断是否 失效期间 >= 生效期间 ————纪录2010-04-15修改
                this.isReferencedCheck(vos);
            }
        }
        catch (ValidationException e) {
            ExceptionUtils.wrappException(e);
        }
    }

    /**
     * 删除保存时进行数据校验，判断是否被业务参数设置引用
     *
     * @param costTypeVOs
     *            CostTypeVO[]
     * @throws ValidationException
     *             异常
     */
    public void isReferencedCheck(CostTypeVO[] vos) throws ValidationException {

        List<ValidationFailure> failure = new ArrayList<ValidationFailure>();
        List<String> ids = new ArrayList<String>();
        // 数据校验，只要发现有错误的，跳出并输出错误
        for (CostTypeVO vo : vos) {
            ids.add(vo.getCcosttypeid());
        }
        if (this.isReferenced(ids.toArray(new String[ids.size()]))) {
            // 返回完整的错误警告信息,创建校验警告
            failure.add(new ValidationFailure(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("1014364_0",
                    "0101436401-0551")/*
                     * @res
                     * "数据已被引用，不能删除！"
                     */
                    ));
            throw new ValidationException(failure); // 校验警告输出

        }
    }

    private boolean isReferenced(String[] ids) {
        CMSqlBuilder sqlBuilder = new CMSqlBuilder();
        sqlBuilder.append("SELECT count(1)");
        sqlBuilder.append(" FROM PUB_SYSINIT ");
        sqlBuilder.where();
        sqlBuilder.append("value", ids); // 成本域
        sqlBuilder.appendDr();

        DataAccessUtils bo = new DataAccessUtils();
        IRowSet rowset = bo.query(sqlBuilder.toString());
        String[] values = rowset.toOneDimensionStringArray();
        if (Integer.valueOf(values[0]) > 0) {
            return true;
        }
        return false;

    }

}
