package nc.vo.mapub.query2.template.sysfunc;

import nc.bd.business.util.FIUtil;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.pubitf.mapub.costtype.pub.ICostTypePubQueryService;
import nc.pubitf.setting.defaultdata.OrgSettingAccessor;
import nc.vo.bd.period2.AccperiodmonthVO;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.org.util.OrgPubUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.query.RefResultVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.sysfunc.ISysFunction;
import nc.vo.querytemplate.sysfunc.SFType;

public class MainCosttypeFunction implements ISysFunction {
    private final static String CODE = ISysFunction.TOKEN + "maincosttype" + ISysFunction.TOKEN;

    @Override
    public String getCode() {
        return MainCosttypeFunction.CODE;
    }

    @Override
    public String getName() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3830006_0", "03830006-0174")/* @res "默认成本类型" */;
    }

    @Override
    public SFType getType() {
        SFType sfType = new SFType(CostTypeContext.CostType);
        return sfType;
    }

    @Override
    public RefResultVO value() {
        RefResultVO result = new RefResultVO();
        String pk_org = CostTypeContext.getContext().getPk_org();
        String accPeriod = null;
        CostTypeVO vo = null;
        try {
            if (pk_org == null) {
                // 默认主组织
                pk_org = OrgSettingAccessor.getDefaultOrgUnit();
            }
            try {
                AccperiodmonthVO monthVO =
                        FIUtil.getAccPeriodMonthVO(pk_org, new UFDate(InvocationInfoProxy.getInstance()
                                .getBizDateTime()));
                if (monthVO != null) {
                    accPeriod = monthVO.getYearmth();
                }
            }
            catch (BusinessException e) {
                ExceptionUtils.wrappException(e);
            }

            if (pk_org != null && pk_org.length() != 0) {
                vo =
                        (CostTypeVO) NCLocator.getInstance().lookup(ICostTypePubQueryService.class)
                                .getDefaultCostType_C(pk_org, accPeriod);
            }
        }
        catch (Exception e) {
            // 不抛异常，正常显示
        }
        if (accPeriod == null || vo == null) {
            return result;
        }
        result.setRefPK(vo.getPrimaryKey());
        result.setRefCode(vo.getVcosttypecode());
        result.setRefName(OrgPubUtil.getNameByMultiLang(vo, CostTypeVO.VCOSTTYPENAME));
        return result;
    }

    @Override
    public String getShowName() {
        return null;
    }
}
