package nc.bs.mapub.allocfac.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bd.framework.base.CMArrayUtil;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.uapbd.IMaterialPubService_C;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.prod.MaterialProdVO;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.mapub.allocfac.enumeration.AllocfacEnum;
import nc.vo.mapub.allocfac.util.AllocfacItemUtil;
import nc.vo.mapub.allocfac.util.CMMLangConstAllocfac;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2013-3-14 上午08:49:34
 * @author xionghuic
 */
public class AllocfacCheckVBRule implements IRule<AllocfacAggVO> {
    private final String RN = "\r\n";

    @Override
    public void process(AllocfacAggVO[] vos) {
        if (CMArrayUtil.isEmpty(vos)) {
            return;
        }
        for (AllocfacAggVO vo : vos) {
            AllocfacHeadVO hvo = (AllocfacHeadVO) vo.getParent();
            String pk_org = hvo.getPk_org();
            Integer ialloctype = hvo.getIalloctype();
            Map<String, String> nameMap = vo.getMap();
            AllocfacItemVO[] itemvos = (AllocfacItemVO[]) vo.getChildren(AllocfacItemVO.class);
            if (itemvos == null || itemvos.length == 0) {
                continue;
            }
            // 校验辅助属性和产品信息
            if (ialloctype.equals(AllocfacEnum.productvbfree.toIntValue())
                    || ialloctype.equals(AllocfacEnum.product.toIntValue())) {
                this.checkAssInfo(pk_org, itemvos, nameMap);

            }
            // 校验产品信息
            if (ialloctype.equals(AllocfacEnum.product.toIntValue())) {
                Set<String> matIdSet = new HashSet<String>();
                for (AllocfacItemVO itemvo : itemvos) {
                    matIdSet.add(itemvo.getCmaterialid());
                }
                Map<String, MaterialProdVO> prodVOMap = this.getProdVOMap(pk_org, matIdSet.toArray(new String[0]));
                Set<String> errorProductIdSet = new HashSet<String>();
                for (AllocfacItemVO itemvo : itemvos) {
                    String matId = itemvo.getCmaterialid();
                    MaterialProdVO prodVO = prodVOMap.get(matId);
                    if (prodVO != null && !prodVO.getSfcbdx().booleanValue()) {
                        errorProductIdSet.add(matId);
                    }
                }
                this.checkProduct(errorProductIdSet);
            }

        }
    }

    /**
     * 辅助属性信息校验
     *
     * @param pk_org
     * @param itemvos
     * @param nameMap
     */
    private void checkAssInfo(String pk_org, AllocfacItemVO[] itemvos, Map<String, String> nameMap) {

        Set<String> matIdSet = new HashSet<String>();
        for (AllocfacItemVO itemvo : itemvos) {
            matIdSet.add(itemvo.getCmaterialid());
        }
        Map<String, MaterialProdVO> prodVOMap = this.getProdVOMap(pk_org, matIdSet.toArray(new String[0]));
        Set<String> wrongMatIdSet = new HashSet<String>();
        Set<String> errorProductIdSet = new HashSet<String>();
        for (AllocfacItemVO itemvo : itemvos) {
            String matId = itemvo.getCmaterialid();
            MaterialProdVO prodVO = prodVOMap.get(matId);
            boolean sign = false;
            for (String name : AllocfacItemUtil.ALLOCFAC_FREE_COLUMN) {
                if (itemvo.getAttributeValue(name) != null) {
                    sign = true;
                    break;
                }
            }
            if (sign) {
                for (String name : AllocfacItemUtil.CMATERIAL_PLAN_FREE_COLUMN) {
                    if (null != prodVO && UFBoolean.TRUE.equals(prodVO.getAttributeValue(name))) {
                        if (itemvo.getAttributeValue(AllocfacItemUtil.COLUMNMAP.get(name)) == null) {
                            wrongMatIdSet.add(matId);
                        }
                    }
                    else {
                        itemvo.setAttributeValue(AllocfacItemUtil.COLUMNMAP.get(name), null);
                    }
                }
            }
            if (null != prodVO && !prodVO.getSfcbdx().booleanValue()) {
                errorProductIdSet.add(matId);
            }

        }
        if (wrongMatIdSet.size() == 0 && errorProductIdSet.size() == 0) {
            return;
        }
        Map<String, String> vbNameMap = new HashMap<String, String>();
        if (wrongMatIdSet.size() > 0) {
            for (String id : wrongMatIdSet) {
                MaterialProdVO prodVO = prodVOMap.get(id);
                StringBuilder strBuilder = new StringBuilder();
                for (String name : AllocfacItemUtil.CMATERIAL_PLAN_FREE_COLUMN) {
                    if (UFBoolean.TRUE.equals(prodVO.getAttributeValue(name))) {
                        strBuilder.append(nameMap.get(AllocfacItemUtil.COLUMNMAP.get(name)));
                        strBuilder.append(",");
                    }
                }
                vbNameMap.put(id, strBuilder.substring(0, strBuilder.length() - 1));
            }

            Map<String, String> codeMap = this.getMatCodes(wrongMatIdSet.toArray(new String[0]));
            StringBuilder info = new StringBuilder();
            for (String id : wrongMatIdSet) {
                info.append(CMMLangConstAllocfac.getWrongMatVBs(codeMap.get(id), vbNameMap.get(id)));
                info.append(this.RN);
            }
            ExceptionUtils.wrappBusinessException(info.toString());
        }
        this.checkProduct(errorProductIdSet);

    }

    /**
     * 校验产品信息
     *
     * @param errorProductIdSet
     */
    private void checkProduct(Set<String> errorProductIdSet) {

        if (errorProductIdSet.size() > 0) {
            Map<String, String> codeMap = this.getMatCodes(errorProductIdSet.toArray(new String[0]));
            StringBuilder errInfo = new StringBuilder();
            for (String id : errorProductIdSet) {
                errInfo.append(CMMLangConstAllocfac.getERR_Product(codeMap.get(id)));
                errInfo.append(this.RN);
            }
            ExceptionUtils.wrappBusinessException(errInfo.toString());
        }
    }

    private Map<String, MaterialProdVO> getProdVOMap(String pk_org, String[] mats) {
        // 查询物料生产信息页签
        Map<String, MaterialProdVO> prodVOMap = new HashMap<String, MaterialProdVO>();
        try {
            prodVOMap =
                    NCLocator.getInstance().lookup(IMaterialPubService_C.class)
                            .queryMaterialProduceInfoByPks(mats, pk_org, AllocfacItemUtil.MATERIALPRODFIELDS);
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
        return prodVOMap;
    }

    private Map<String, String> getMatCodes(String[] matIds) {
        Map<String, String> codeMap = new HashMap<String, String>();
        Map<String, MaterialVO> idToVO = new HashMap<String, MaterialVO>();
        try {
            idToVO =
                    NCLocator.getInstance().lookup(IMaterialPubService_C.class)
                            .queryMaterialBaseInfoByPks(matIds, new String[] {
                                "code"
                            });
        }
        catch (BusinessException e1) {
            ExceptionUtils.wrappException(e1);
        }
        for (Map.Entry<String, MaterialVO> e : idToVO.entrySet()) {
            codeMap.put(e.getKey(), e.getValue().getCode());
        }
        return codeMap;
    }

}
