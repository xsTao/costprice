package nc.bs.mapub.coprodcoef.rule;

import java.util.Map;

import nc.bd.framework.base.CMMapUtil;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.bd.bom.IPubBomService;
import nc.vo.bd.bom.bom0202.entity.AggBomVO;
import nc.vo.bd.bom.bom0202.entity.BomVO;
import nc.vo.mapub.coprodcoef.entity.CMMLangConstCoprodcoef;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 联合体编码、bom版本对应关系
 *
 * @since 6.0
 * @version 2012-7-6 上午11:10:11
 * @author
 */
public class CoprodcoefBomVersionCheckRule implements IRule<CoprodcoefAggVO> {

    @Override
    public void process(CoprodcoefAggVO[] bills) {
        // 联合体编码、bom版本对应关系
        this.bomVersionCheck(bills);
    }

    private void bomVersionCheck(CoprodcoefAggVO[] bills) {
        for (CoprodcoefAggVO bill : bills) {
            CoprodcoefHeadVO head = bill.getParentVO();
            try {
                // String str = (String) head.getAttributeValue("cbomid");
                // 联合体编码、bom版本对应的bomVO
                Map<String, AggBomVO> bomMap =
                        NCLocator.getInstance().lookup(IPubBomService.class).queryBomFieldsByPk(new String[] {
                                head.getCbomid()
                        }, new String[] {
                                BomVO.HCMATERIALID
                        }, false);

                if (CMMapUtil.isNotEmpty(bomMap)) {
                    for (AggBomVO vo : bomMap.values()) {
                        if (!vo.getParentVO().getAttributeValue(BomVO.HCMATERIALID).equals(head.getCmaterialid())) {
                            ExceptionUtils.wrappBusinessException(CMMLangConstCoprodcoef.getMSG_BOMSBYVERSION());
                        }
                    }
                }
            }
            catch (BusinessException e) {
                ExceptionUtils.wrappException(e);
            }
        }
    }
}
