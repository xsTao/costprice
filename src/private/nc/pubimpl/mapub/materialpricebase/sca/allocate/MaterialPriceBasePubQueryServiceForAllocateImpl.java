package nc.pubimpl.mapub.materialpricebase.sca.allocate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bd.framework.base.CMValueCheck;
import nc.bd.framework.db.CMSqlBuilder;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.mapub.materialpricebase.sca.allocate.IMaterialPriceBasePubQueryServiceForAllocate;
import nc.vo.cmpub.framework.assistant.CMMarAssInfoVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;

/**
 * 物料价格库公共查询实现类
 *
 * @since 6.36
 * @version 2014-11-28 上午9:32:09
 * @author zhangchd
 */
public class MaterialPriceBasePubQueryServiceForAllocateImpl implements IMaterialPriceBasePubQueryServiceForAllocate {
    /**
     * 提供的接口：根据价格库查询[材料]子表的价格
     *
     * @param priceBaseIDs 价格库主键
     * @param materialids 材料vid
     * @return Map<物料，Map<价格库，价格>>
     */
    @Override
    public Map<CMMarAssInfoVO, Map<String, UFDouble>> getStuffPrice(String[] priceBaseIDs, CMMarAssInfoVO[] materialids)
            throws BusinessException {
        if (priceBaseIDs == null || materialids == null) {
            return null;
        }
        Set<String> unionMatMarAss = new HashSet<String>();
        for (CMMarAssInfoVO marAssInfo : materialids) {
            String matMarAss = "";
            matMarAss =
                    matMarAss + marAssInfo.getCmaterialid() + marAssInfo.getCcustomerid() + marAssInfo.getCvendorid()
                    + marAssInfo.getCproductorid() + marAssInfo.getCprojectid() + marAssInfo.getVfree1()
                    + marAssInfo.getVfree2() + marAssInfo.getVfree3() + marAssInfo.getVfree4()
                    + marAssInfo.getVfree5() + marAssInfo.getVfree6() + marAssInfo.getVfree7()
                    + marAssInfo.getVfree8() + marAssInfo.getVfree9() + marAssInfo.getVfree10();
            unionMatMarAss.add(matMarAss.replace("null", ""));
        }
        CMSqlBuilder sql = new CMSqlBuilder();
        sql.append("select ");
        sql.append(MaterialPriceBaseBodyVO.CMATERIALPRICEID + ",");
        sql.append(MaterialPriceBaseBodyVO.CMATERIALID + ",");
        sql.append(MaterialPriceBaseBodyVO.CCUSTOMERID + ",");
        sql.append(MaterialPriceBaseBodyVO.CVENDORID + ",");
        sql.append(MaterialPriceBaseBodyVO.CPRODUCTORID + ",");
        sql.append(MaterialPriceBaseBodyVO.CPROJECTID + ",");
        sql.append(MaterialPriceBaseBodyVO.VBFREE1 + ",");
        sql.append(MaterialPriceBaseBodyVO.VBFREE2 + ",");
        sql.append(MaterialPriceBaseBodyVO.VBFREE3 + ",");
        sql.append(MaterialPriceBaseBodyVO.VBFREE4 + ",");
        sql.append(MaterialPriceBaseBodyVO.VBFREE5 + ",");
        sql.append(MaterialPriceBaseBodyVO.VBFREE6 + ",");
        sql.append(MaterialPriceBaseBodyVO.VBFREE7 + ",");
        sql.append(MaterialPriceBaseBodyVO.VBFREE8 + ",");
        sql.append(MaterialPriceBaseBodyVO.VBFREE9 + ",");
        sql.append(MaterialPriceBaseBodyVO.VBFREE10 + ",");
        sql.append(MaterialPriceBaseBodyVO.NPRICE);
        sql.append(" from " + " mapub_materialpricebase_b");
        sql.append(" where ");
        sql.append(MaterialPriceBaseBodyVO.CMATERIALPRICEID, priceBaseIDs);
        sql.and();
        sql.append("replace(" + this.getUnionMatMarAssSql() + ",'~','')",
                unionMatMarAss.toArray(new String[unionMatMarAss.size()]));
        sql.and();
        sql.appendDr("mapub_materialpricebase_b");

        DataAccessUtils util = new DataAccessUtils();
        IRowSet rowSet = util.query(sql.toString());
        Map<CMMarAssInfoVO, Map<String, UFDouble>> marAssPriceMap =
                new HashMap<CMMarAssInfoVO, Map<String, UFDouble>>();
        while (rowSet.next()) {
            CMMarAssInfoVO marAssInfoVO = new CMMarAssInfoVO();
            int i = 1;
            for (String col : CMMarAssInfoVO.CM_AssInfo_Item) {
                marAssInfoVO.setAttributeValue(col, rowSet.getString(i++));
            }
            Map<String, UFDouble> priceMap = null;
            if (marAssPriceMap.containsKey(marAssInfoVO)) {
                priceMap = marAssPriceMap.get(marAssInfoVO);
            }
            else {
                priceMap = new HashMap<String, UFDouble>();
            }
            if (CMValueCheck.isNotEmpty(rowSet.getUFDouble(16))) {
                priceMap.put(rowSet.getString(0), rowSet.getUFDouble(16));
                marAssPriceMap.put(marAssInfoVO, priceMap);
            }
        }
        return marAssPriceMap;
    }

    private String getUnionMatMarAssSql() {
        String unionMatMarAssSql =
                MaterialPriceBaseBodyVO.CMATERIALID + "||" + MaterialPriceBaseBodyVO.CCUSTOMERID + "||"
                        + MaterialPriceBaseBodyVO.CVENDORID + "||" + MaterialPriceBaseBodyVO.CPRODUCTORID + "||"
                        + MaterialPriceBaseBodyVO.CPROJECTID + "||" + MaterialPriceBaseBodyVO.VBFREE1 + "||"
                        + MaterialPriceBaseBodyVO.VBFREE2 + "||" + MaterialPriceBaseBodyVO.VBFREE3 + "||"
                        + MaterialPriceBaseBodyVO.VBFREE4 + "||" + MaterialPriceBaseBodyVO.VBFREE5 + "||"
                        + MaterialPriceBaseBodyVO.VBFREE6 + "||" + MaterialPriceBaseBodyVO.VBFREE7 + "||"
                        + MaterialPriceBaseBodyVO.VBFREE8 + "||" + MaterialPriceBaseBodyVO.VBFREE9 + "||"
                        + MaterialPriceBaseBodyVO.VBFREE10;
        return unionMatMarAssSql;
    }

}
