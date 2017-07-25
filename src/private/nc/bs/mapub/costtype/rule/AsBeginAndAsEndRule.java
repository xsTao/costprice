/**
 *
 */
package nc.bs.mapub.costtype.rule;

import java.util.TimeZone;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMValueCheck;
import nc.bs.uif2.validation.ValidationException;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.costtype.entity.CMMLangConstCM0502;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2015年5月25日 上午1:44:07
 * @author zhangshyb
 */
public class AsBeginAndAsEndRule implements IRule<CostTypeVO> {

    @Override
    public void process(CostTypeVO[] vos) {
        // 校验：要素与物料对照表中要素所对应的要素表与单据中要素表保持一致
        try {
            if (CMArrayUtil.isNotEmpty(vos)) {
                // 首先进行数据校验，判断是否 失效期间 >= 生效期间 ————纪录2010-04-15修改
                this.asBeginAndAsEnd(vos);
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
    public void asBeginAndAsEnd(CostTypeVO[] costTypeVOs) throws ValidationException {
        for (CostTypeVO costTypeVO : costTypeVOs) {
            UFDate beginDate = costTypeVO.getCbeginmonth().asBegin(TimeZone.getTimeZone("GMT+8:00"));
            costTypeVO.setCbeginmonth(beginDate);
            UFDate endDate = costTypeVO.getCendmonth();
            if (CMValueCheck.isEmpty(endDate)) {
                costTypeVO.setCendmonth(UFDate.getDate(CMMLangConstCM0502.MAX_DATE).asEnd(
                        TimeZone.getTimeZone("GMT+8:00")));
            }
            else {
                costTypeVO.setCendmonth(endDate.asEnd(TimeZone.getTimeZone("GMT+8:00")));
            }
        }
    }

}
