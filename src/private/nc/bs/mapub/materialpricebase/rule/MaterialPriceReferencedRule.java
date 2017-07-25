package nc.bs.mapub.materialpricebase.rule;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import nc.bd.framework.base.CMArrayUtil;
import nc.bs.framework.common.NCLocator;
import nc.cmpub.business.adapter.BDAdapter;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.mapub.costtype.ICostTypeService;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/*
 * @since 6.36
 * @version 2014-11-25 上午9:14:18
 * @author zhangchd
 */
public class MaterialPriceReferencedRule implements IRule<MaterialPriceBaseAggVO> {

    private ICostTypeService materialPubService;

    @Override
    public void process(MaterialPriceBaseAggVO[] vos) {
        if (CMArrayUtil.isEmpty(vos)) {
            return;
        }
        // 1、验证被成本类型引用
        this.checkReferencedRule(vos);
    }

    /**
     * 1、验证被成本类型引用
     *
     * @param vos
     */
    @SuppressWarnings("boxing")
    private void checkReferencedRule(MaterialPriceBaseAggVO[] vos) {
        Boolean costTypeBoolean;
        Boolean costBomBoolean;
        Set<String> materialPriceBasepksSet = new HashSet<String>();
        for (MaterialPriceBaseAggVO aggVO : vos) {
            // 表头
            MaterialPriceBaseHeadVO headVO = (MaterialPriceBaseHeadVO) aggVO.getParent();
            materialPriceBasepksSet.add(headVO.getCmaterialpriceid());
        }

        costTypeBoolean =
                this.queryCostTypeIsReferced(materialPriceBasepksSet.toArray(new String[materialPriceBasepksSet.size()]));
        if (costTypeBoolean) {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                    "03810006-0326")/* 物料价格库已被引用，不能删除！ */);
        }
        // 判断sca(3860)模块是否启用
        if (BDAdapter.isModuleEnabled(AppContext.getInstance().getPkGroup(), "3860")) {
            costBomBoolean =
                    this.queryCostBomIsReferced(materialPriceBasepksSet.toArray(new String[materialPriceBasepksSet
                                                                                           .size()]));
            if (costBomBoolean) {
                ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                        "3810006_0", "03810006-0326")/* 物料价格库已被引用，不能删除！ */);
            }
        }
    }

    public boolean queryCostTypeIsReferced(String[] pks) {
        if (this.materialPubService == null) {
            this.materialPubService = NCLocator.getInstance().lookup(ICostTypeService.class);
        }
        return this.materialPubService.isReferenced(pks);
    }

    public boolean queryCostBomIsReferced(String[] pks) {
        boolean isReferced = false;
        try {
            Class<?> clazz = Class.forName("nc.pubitf.sca.costbom.materialPriceSource.ICostBomServiceForPriceSource");
            Object service1 = NCLocator.getInstance().lookup(clazz);
            Method m = clazz.getMethod("isReferenced", pks.getClass());
            Object result = m.invoke(service1, new Object[] {
                    pks
            });
            if (result instanceof Boolean) {
                isReferced = ((Boolean) result).booleanValue();
            }
        }

        catch (Exception e) {
        }
        return isReferced;
        // if (this.materialPubBomService == null) {
        // this.materialPubBomService = NCLocator.getInstance().lookup(ICostBomServiceForPriceSource.class);
        // }
        // return this.materialPubBomService.isReferenced(pks);
    }

}
