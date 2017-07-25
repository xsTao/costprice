package nc.bs.mapub.materialpricebase.rule;

import java.util.HashSet;
import java.util.Set;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMValueCheck;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.mapub.materialpricebase.enumeration.PriceSourceEnum;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 材料价格库
 * 空值校验
 *
 * @since 6.36
 * @version 2014-11-11 下午4:12:00
 * @author zhangchd
 */
public class MaterialPriceValidateNumRule implements IRule<MaterialPriceBaseAggVO> {

    @Override
    public void process(MaterialPriceBaseAggVO[] vos) {
        if (CMArrayUtil.isEmpty(vos)) {
            return;
        }
        // 1、验证表头价格库编码、价格库名称、币种、生效日期
        // 2、验证表体材料编码、单价(价格来源为：手工录入)

        this.checkNumAndCostRule(vos);
    }

    /**
     * 1、验证表头价格库编码、价格库名称、币种、生效日期
     * 2、验证表体材料编码、单价(价格来源为：手工录入)
     *
     * @param vos
     */
    private void checkNumAndCostRule(MaterialPriceBaseAggVO[] vos) {
        Set<String> errorSet = new HashSet<String>();

        ValidationException exception = new ValidationException();

        for (MaterialPriceBaseAggVO vo : vos) {
            // 表头
            MaterialPriceBaseHeadVO headVO = (MaterialPriceBaseHeadVO) vo.getParent();
            // 价格库编码
            String vpricecode = headVO.getVpricecode();
            // 价格库名称
            String vpricename = headVO.getVpricename();
            // // 币种
            // String ccrencyid = headVO.getCcrrencyid();
            // 生效日期
            UFDate dbegindate = headVO.getDbegindate();

            if (CMValueCheck.isEmpty(vpricecode)) {
                String errMsg =
                        CMMLangConstMaterialPriceBase.getNULL_VALIDATE_HEAD_ITEM(CMMLangConstMaterialPriceBase
                                .getVPRICEBASE());
                if (!errorSet.contains(errMsg)) {
                    errorSet.add(errMsg);
                }
            }
            if (CMValueCheck.isEmpty(vpricename)) {
                String errMsg =
                        CMMLangConstMaterialPriceBase.getNULL_VALIDATE_HEAD_ITEM(CMMLangConstMaterialPriceBase
                                .getVPRICENAME());
                if (!errorSet.contains(errMsg)) {
                    errorSet.add(errMsg);
                }
            }
            // if (CMValueCheck.isEmpty(ccrencyid)) {
            // String errMsg =
            // CMMLangConstMaterialPriceBase.getNULL_VALIDATE_HEAD_ITEM(CMMLangConstMaterialPriceBase
            // .getCRENCYID());
            // if (!errorSet.contains(errMsg)) {
            // errorSet.add(errMsg);
            // }
            // }
            if (CMValueCheck.isEmpty(dbegindate)) {
                String errMsg =
                        CMMLangConstMaterialPriceBase.getNULL_VALIDATE_HEAD_ITEM(CMMLangConstMaterialPriceBase
                                .getDBEGINDATE());
                if (!errorSet.contains(errMsg)) {
                    errorSet.add(errMsg);
                }
            }
            // 表体
            MaterialPriceBaseBodyVO[] bodyVOs =
                    (MaterialPriceBaseBodyVO[]) vo.getChildren(MaterialPriceBaseBodyVO.class);

            if (CMArrayUtil.isNotEmpty(bodyVOs)) {
                for (MaterialPriceBaseBodyVO itemvo : bodyVOs) {
                    if (itemvo.getStatus() == VOStatus.DELETED) {
                        continue;
                    }
                    if (itemvo != null) {
                        // 材料编码
                        if (CMValueCheck.isEmpty(itemvo.getCmaterialid())) {
                            String errMsg =
                                    CMMLangConstMaterialPriceBase
                                    .getNULL_VALIDATE_BODY_ITEM1(CMMLangConstMaterialPriceBase.getCMATERIALID());
                            if (!errorSet.contains(errMsg)) {
                                errorSet.add(errMsg);
                            }
                        }
                        // 表体价格来源为空时，表头价格来源为【手工录入】时，单价必输
                        if (CMValueCheck.isEmpty(itemvo.getVpricesourcenum())
                                && String.valueOf(PriceSourceEnum.MANUAL.toIntValue()).equals(
                                        headVO.getVpricesourcenum())) {
                            if (CMValueCheck.isEmpty(itemvo.getNprice())) {
                                String errMsg = CMMLangConstMaterialPriceBase.getHeadandBodyPriceIsNotEmpty();
                                if (!errorSet.contains(errMsg)) {
                                    errorSet.add(errMsg);
                                }
                            }
                        }
                        // 当表体价格来源为【手工录入】，单价必输
                        else if (String.valueOf(PriceSourceEnum.MANUAL.toIntValue())
                                .equals(itemvo.getVpricesourcenum())) {
                            if (CMValueCheck.isEmpty(itemvo.getNprice())) {
                                String errMsg = CMMLangConstMaterialPriceBase.getBodyPriceIsNotEmpty();
                                if (!errorSet.contains(errMsg)) {
                                    errorSet.add(errMsg);
                                }
                            }
                        }

                    }
                }
            }

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
