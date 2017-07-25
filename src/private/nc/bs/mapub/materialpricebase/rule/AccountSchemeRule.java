package nc.bs.mapub.materialpricebase.rule;

import java.util.HashMap;
import java.util.Map;

import nc.bd.framework.base.CMArrayUtil;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.org.IAccountingBookPubService;
import nc.pubitf.org.IStockOrgPubService;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 工厂对应的财务组织主账簿不为空
 *
 * @since 6.36
 * @version 2014-11-10 下午2:48:35
 * @author zhangchd
 */
public class AccountSchemeRule implements IRule<MaterialPriceBaseAggVO> {

    @Override
    public void process(MaterialPriceBaseAggVO[] vos) {
        if (CMArrayUtil.isEmpty(vos)) {
            ExceptionUtils.wrappBusinessException(CMMLangConstMaterialPriceBase.CHECK_NULL_VO());
        }

        MaterialPriceBaseHeadVO headVO = vos[0].getParentVO();
        String pkorg = headVO.getPk_org();

        // 1、查找该库存组织的所属财务组织UAP
        Map<String, String> fiOrgofStockOrgMap = new HashMap<String, String>();
        try {
            fiOrgofStockOrgMap =
                    NCLocator.getInstance().lookup(IStockOrgPubService.class)
                    .queryFinanceOrgIDsByStockOrgIDs(new String[] {
                            pkorg
                    });

            if (fiOrgofStockOrgMap == null || fiOrgofStockOrgMap.size() == 0 || fiOrgofStockOrgMap.get(pkorg) == null) {
                ExceptionUtils.wrappBusinessException(CMMLangConstMaterialPriceBase.GET_HIT_NOFINANCE());
            }
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappBusinessException(CMMLangConstMaterialPriceBase.GET_HIT_NOFINANCE());
        }

        // 2、再查找财务组织所对应的账簿分类为主账簿的财务核算账簿
        String pkFIOrg = fiOrgofStockOrgMap.get(pkorg);

        // 批量根据财务组织主键找主财务核算账簿主键UAP
        Map<String, String> pkMainAccountBookMap = new HashMap<String, String>();
        try {
            pkMainAccountBookMap =
                    NCLocator.getInstance().lookup(IAccountingBookPubService.class)
                    .queryAccountingBookIDByFinanceOrgIDWithMainAccountBook(new String[] {
                            pkFIOrg
                    });

            if (pkMainAccountBookMap == null || pkMainAccountBookMap.size() == 0
                    || pkMainAccountBookMap.get(pkFIOrg) == null) {
                ExceptionUtils.wrappBusinessException(CMMLangConstMaterialPriceBase.GET_HIT_NOSCHEME());
            }
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappBusinessException(CMMLangConstMaterialPriceBase.GET_HIT_NOSCHEME());
        }

    }
}
