package nc.bs.mapub.acquirepricelog.bp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMCollectionUtil;
import nc.bd.framework.base.CMStringUtil;
import nc.cmpub.business.adapter.BDAdapter;
import nc.vo.bd.material.prod.MaterialProdVO;
import nc.vo.mapub.acquirepricelog.entity.AcquirePriceLogVO;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class ClearUnenableAssInfoBP {

    public void process(Object[] vos) {
        if (CMArrayUtil.isEmpty(vos)) {
            return;
        }
        String pk_org = (String) ((ISuperVO) vos[0]).getAttributeValue(AcquirePriceLogVO.PK_ORG);
        List<String> marIdList = new ArrayList<String>();
        for (Object vo : vos) {
            Object marId = ((ISuperVO) vo).getAttributeValue(AcquirePriceLogVO.CMATERIALID);
            if (null != marId && !"~".equals(marId)) {
                marIdList.add((String) marId);
            }
        }

        if (CMCollectionUtil.isEmpty(marIdList)) {
            return;
        }
        // 成本模块启用辅助属性校验,待修改为uap提供接口
        Map<String, String> marvIdMap =
                BDAdapter.convertMaterialid2Vid(marIdList.toArray(new String[marIdList.size()]));
        try {
            Map<String, MaterialProdVO> prodVOMap =
                    BDAdapter.queryMaterialProduceInfoByPks(marvIdMap.values().toArray(new String[marvIdMap.size()]),
                            pk_org, new String[] {
                        MaterialProdVO.COSTVALUTASST2, MaterialProdVO.COSTVALUTASST3,
                        MaterialProdVO.COSTVALUTASST4, MaterialProdVO.COSTVALUTASST5,
                        MaterialProdVO.COSTVALUTASST6, MaterialProdVO.COSTVALUTASST7,
                        MaterialProdVO.COSTVALUTASST8, MaterialProdVO.COSTVALUTASST9,
                        MaterialProdVO.COSTVALUTASST10, MaterialProdVO.COSTVALUTASST11,
                        MaterialProdVO.COSTVALUTASST12, MaterialProdVO.COSTVALUTASST13,
                        MaterialProdVO.COSTVALUTASST14, MaterialProdVO.COSTVALUTASST15
                    });

            for (Object vo : vos) {
                AcquirePriceLogVO covo = (AcquirePriceLogVO) vo;
                String vid = marvIdMap.get(covo.getCmaterialid());
                if (CMStringUtil.isEmpty(vid)) {
                    continue;
                }
                MaterialProdVO materialProdVO = prodVOMap.get(vid);

                // liyjf 多语处理,提示辅助属性名称
                if (null == materialProdVO) {
                    continue;
                }
                if (!this.checkUFBoolean(materialProdVO.getCostvalutasst2())) {
                    covo.setCprojectid(null);
                }
                if (!this.checkUFBoolean(materialProdVO.getCostvalutasst3())) {
                    covo.setCvendorid(null);
                }
                if (!this.checkUFBoolean(materialProdVO.getCostvalutasst4())) {
                    covo.setCproductorid(null);
                }
                if (!this.checkUFBoolean(materialProdVO.getCostvalutasst5())) {
                    covo.setCcustomerid(null);
                }
                if (!this.checkUFBoolean(materialProdVO.getCostvalutasst6())) {
                    covo.setVfree1(null);
                }
                if (!this.checkUFBoolean(materialProdVO.getCostvalutasst7())) {
                    covo.setVfree2(null);
                }
                if (!this.checkUFBoolean(materialProdVO.getCostvalutasst8())) {
                    covo.setVfree3(null);
                }
                if (!this.checkUFBoolean(materialProdVO.getCostvalutasst9())) {
                    covo.setVfree4(null);
                }
                if (!this.checkUFBoolean(materialProdVO.getCostvalutasst10())) {
                    covo.setVfree5(null);
                }
                if (!this.checkUFBoolean(materialProdVO.getCostvalutasst11())) {
                    covo.setVfree6(null);
                }
                if (!this.checkUFBoolean(materialProdVO.getCostvalutasst12())) {
                    covo.setVfree7(null);
                }
                if (!this.checkUFBoolean(materialProdVO.getCostvalutasst13())) {
                    covo.setVfree8(null);
                }
                if (!this.checkUFBoolean(materialProdVO.getCostvalutasst14())) {
                    covo.setVfree9(null);
                }
                if (!this.checkUFBoolean(materialProdVO.getCostvalutasst15())) {
                    covo.setVfree10(null);
                }
            }
        }
        catch (Exception e) {
            ExceptionUtils.wrappException(e);
        }
    }

    private boolean checkUFBoolean(UFBoolean ufbooleanValue) {
        if (ufbooleanValue == null || !ufbooleanValue.booleanValue()) {
            return false;
        }
        return true;
    }
}
