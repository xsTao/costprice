/**
 *
 */
package nc.bs.mapub.costtype.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMStringUtil;
import nc.bs.uif2.validation.ValidationException;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.ui.mapub.costtype.view.CostTypeMaterialPriceSourceBaseData;
import nc.ui.pub.beans.constenum.IConstEnum;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2015年6月5日 下午2:52:48
 * @author zhangshyb
 */
public class MaterialPriceSourceNumNullCheck implements IRule<CostTypeVO> {

    @Override
    public void process(CostTypeVO[] vos) {
        // 校验：材料价格来源是否为空，不为空则更新材料价格来源数
        try {
            if (CMArrayUtil.isNotEmpty(vos)) {
                this.materialPriceSourceNumNullCheck(vos);
            }
        }
        catch (ValidationException e) {
            ExceptionUtils.wrappException(e);
        }
    }

    /**
     * 校验：材料价格来源是否为空，不为空则更新材料价格来源数
     *
     * @param costTypeVOs
     *            CostTypeVO[]
     * @throws ValidationException
     *             异常
     */
    public void materialPriceSourceNumNullCheck(CostTypeVO[] costTypeVOs) throws ValidationException {
        CostTypeMaterialPriceSourceBaseData materialbaseData = new CostTypeMaterialPriceSourceBaseData();
        List<IConstEnum> materiales = new ArrayList<IConstEnum>();
        materiales = materialbaseData.getInitValues(costTypeVOs[0].getPk_org());
        for (CostTypeVO costTypeVO : costTypeVOs) {
            if (CMStringUtil.isNotEmpty(costTypeVO.getVmaterialpricesource())) {
                costTypeVO.setVmaterialpricesourcenum(this.stringConversion(costTypeVO.getVmaterialpricesource(),
                        materiales));
            }
            else {
                costTypeVO.setVmaterialpricesourcenum(null);
            }
        }
    }

    private String stringConversion(String toconver, List<IConstEnum> list) {

        String[] sss = toconver.split("\\,");
        StringBuffer string = new StringBuffer();
        String value = null;
        for (String ss : sss) {
            for (IConstEnum enums : list.toArray(new IConstEnum[list.size()])) {
                if (ss.equals(enums.getName().toString())) {
                    string.append(enums.getValue());
                    string.append(",");
                }
            }
        }
        value = string.substring(0, string.length() - 1);
        return value;

    }

}
