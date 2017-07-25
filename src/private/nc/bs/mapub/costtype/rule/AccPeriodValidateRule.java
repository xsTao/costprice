/**
 *
 */
package nc.bs.mapub.costtype.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bd.framework.base.CMArrayUtil;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2015年5月18日 上午9:22:43
 * @author zhangshyb
 */
public class AccPeriodValidateRule implements IRule<CostTypeVO> {

    @Override
    public void process(CostTypeVO[] vos) {
        // 校验：要素与物料对照表中要素所对应的要素表与单据中要素表保持一致
        try {
            if (CMArrayUtil.isNotEmpty(vos)) {
                // 首先进行数据校验，判断是否 失效期间 >= 生效期间 ————纪录2010-04-15修改
                this.accPeriodValidate(vos);
            }
        }
        catch (ValidationException e) {
            ExceptionUtils.wrappException(e);
        }
    }

    /**
     * 保存时进行数据校验，判断是否 失效期间 >= 生效期间
     *
     * @param costTypeVOs
     *            CostTypeVO[]
     * @throws ValidationException
     *             异常
     */
    public void accPeriodValidate(CostTypeVO[] costTypeVOs) throws ValidationException {

        List<ValidationFailure> failure = new ArrayList<ValidationFailure>();
        StringBuffer errStrNum = new StringBuffer();

        // 数据校验，只要发现有错误的，跳出并输出错误
        for (int i = 0; i < costTypeVOs.length; i++) {
            UFDate beginDate = costTypeVOs[i].getCbeginmonth();
            UFDate endDate = costTypeVOs[i].getCendmonth();
            // t1不需要判空处理，因为t1首先进行保存的非空校验，此处t1必非空
            int compResult = beginDate.compareTo(endDate);
            if (compResult > 0) {
                // 为输出错误的行号做准备,每个行号后面以","连接如1,2,3(行)
                errStrNum.append(costTypeVOs[i].getVcosttypecode() + "[" + costTypeVOs[i].getVcosttypename() + "]");
                errStrNum.append(",");
            }
        }

        if (errStrNum.length() > 0) {
            errStrNum.deleteCharAt(errStrNum.length() - 1); // 要去掉最后一个","

            // 返回完整的错误警告信息,创建校验警告
            failure.add(new ValidationFailure(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                    "03810006-0312")/*
                     * @res
                     * "错误：[生效期间]不能晚于[失效期间]："
                     */
                    + errStrNum.toString()));
            throw new ValidationException(failure); // 校验警告输出
        }

    }

}
