/**
 *
 */
package nc.bs.mapub.materialpricebase.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMCollectionUtil;
import nc.bd.framework.base.CMMapUtil;
import nc.bd.framework.base.CMStringUtil;
import nc.bd.framework.base.CMValueCheck;
import nc.bs.framework.common.NCLocator;
import nc.cmpub.business.adapter.BDAdapter;
import nc.cmpub.business.cons.CMConstant;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.bd.userdefitem.IUserdefitemQryService;
import nc.pubitf.uapbd.assistant.IMarAssistantPubService;
import nc.vo.bd.material.MaterialVersionVO;
import nc.vo.bd.userdefrule.UserdefitemVO;
import nc.vo.cmpub.business.lang.CMLangConstPub;
import nc.vo.cmpub.framework.assistant.CMAssInfoItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * @since v6.3
 * @version 2015年5月11日 下午3:13:10
 * @author zhangshyb
 */
public class MaterialPriceBaseMarAssistantRule<E extends AbstractBill> implements IRule<E> {

    /** 物料辅助属性自定义项规则编码 */
    private static final String MARASSISTANTRULECODE = "materialassistant";

    // 物料字段
    private String materialField = "cmaterialid";

    // key=单据表体辅助属性字段编码,value=辅助属性序号
    private Map<String, Integer> assistantMap = new HashMap<String, Integer>();

    // key=单据表体辅助属性字段编码,value=辅助属性序号
    private Map<String, Integer> fixedAssistantMap = new HashMap<String, Integer>();

    public MaterialPriceBaseMarAssistantRule(String materialField) {
        if (CMStringUtil.isNotEmpty(materialField)) {
            this.materialField = materialField;
        }
    }

    public MaterialPriceBaseMarAssistantRule() {

    }

    @Override
    public void process(E[] vos) {
        this.setAssistantMap();
        this.setFixedAssistantMap();
        // key=物料OID,value=Map<辅助属性字段,辅助属性值>
        // Map<String, Map<String, Object>> materAssMap = this.getMaterialid(vos);
        // key=物料OID,value=MaterialVersionVO
        Map<String, MaterialVersionVO> materialVersionMap = this.getMaterialMap(vos);
        // key=物料辅助属性结构ID,value=辅助属性序号列表
        Map<String, List<Integer>> marFrameMap = this.getMarFrameMap(materialVersionMap);
        // key=物料OID,value=未启用辅助属性序号列表
        List<Map<String, Set<Integer>>> errMapList = this.getErrorInfo(vos, materialVersionMap, marFrameMap);
        if (CMValueCheck.isEmpty(errMapList)) {
            return;
        }
        UserdefitemVO[] userdefitemVOs = this.queryUserDefitem(vos);
        this.setErrorMsg(errMapList.get(0), materialVersionMap, userdefitemVOs);

    }

    /**
     * 根据材料OID得到其最新版本信息
     *
     * @param materAssMap
     *            key=物料OID,value=Map<辅助属性字段,辅助属性值>
     * @return
     *         key=物料OID,value=MaterialVersionVO
     */
    private Map<String, MaterialVersionVO> getMaterialMap(E[] vos) {
        if (vos == null || vos.length == 0) {
            return null;
        }
        // key=物料OID,value=Map<辅助属性字段,辅助属性值>
        Set<String> materialSet = new HashSet<String>();

        for (int i = 0; i < vos.length; i++) {
            if (vos[i] == null) {
                continue;
            }
            CircularlyAccessibleValueObject[] itemVOs = vos[i].getChildrenVO();
            if (itemVOs == null || itemVOs.length == 0) {
                continue;
            }
            for (CircularlyAccessibleValueObject itemVO : itemVOs) {
                Object materialid = itemVO.getAttributeValue(this.materialField);
                if (materialid != null && !materialid.equals("~")) {
                    materialSet.add(materialid.toString());
                }

            }

        }

        if (CMCollectionUtil.isEmpty(materialSet)) {
            return null;
        }
        String[] materialids = materialSet.toArray(new String[0]);

        Map<String, MaterialVersionVO> materialVersionMap =
                BDAdapter.queryMaterialLatestBaseInfoByOids(materialids, new String[] {
                    MaterialVersionVO.CODE, MaterialVersionVO.NAME, MaterialVersionVO.PK_MARASSTFRAME
                });
        return materialVersionMap;

    }

    /**
     * 根据辅助属性结构ID数组，查询辅助属性结构包含的辅助属性序号
     *
     * @param materialVersionMap
     *            key=物料OID,value=MaterialVersionVO
     * @return
     *         key=辅助属性结构ID,value=辅助属性序号列表
     */
    private Map<String, List<Integer>> getMarFrameMap(Map<String, MaterialVersionVO> materialVersionMap) {
        if (materialVersionMap == null || materialVersionMap.size() == 0 || materialVersionMap.isEmpty()) {
            return null;
        }
        Map<String, List<Integer>> marFrameMap = null;
        try {
            Set<String> marFramePkSet = new HashSet<String>();
            for (Entry<String, MaterialVersionVO> entry : materialVersionMap.entrySet()) {
                String pk_marasstframe = entry.getValue().getPk_marasstframe();
                if (pk_marasstframe != null) {
                    marFramePkSet.add(pk_marasstframe);
                }
            }
            marFrameMap =
                    NCLocator.getInstance().lookup(IMarAssistantPubService.class)
                            .queryMarAsstFrameIncludeDefPropIndex(marFramePkSet.toArray(new String[0]));
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
        return marFrameMap;
    }

    /**
     * 设置单据表体辅助属性字段编码和辅助属性序号之间的对照关系
     *
     * @return
     *         key=单据表体辅助属性字段编码,value=辅助属性序号
     */
    private void setAssistantMap() {
        this.assistantMap.put(CMAssInfoItemVO.CPROJECTID, Integer.valueOf(2));
        this.assistantMap.put(CMAssInfoItemVO.CVENDORID, Integer.valueOf(3));
        this.assistantMap.put(CMAssInfoItemVO.CPRODUCTORID, Integer.valueOf(4));
        this.assistantMap.put(CMAssInfoItemVO.CCUSTOMERID, Integer.valueOf(5));
        this.assistantMap.put(CMAssInfoItemVO.VBFREE1, Integer.valueOf(6));
        this.assistantMap.put(CMAssInfoItemVO.VBFREE2, Integer.valueOf(7));
        this.assistantMap.put(CMAssInfoItemVO.VBFREE3, Integer.valueOf(8));
        this.assistantMap.put(CMAssInfoItemVO.VBFREE4, Integer.valueOf(9));
        this.assistantMap.put(CMAssInfoItemVO.VBFREE5, Integer.valueOf(10));
        this.assistantMap.put(CMAssInfoItemVO.VBFREE6, Integer.valueOf(11));
        this.assistantMap.put(CMAssInfoItemVO.VBFREE7, Integer.valueOf(12));
        this.assistantMap.put(CMAssInfoItemVO.VBFREE8, Integer.valueOf(13));
        this.assistantMap.put(CMAssInfoItemVO.VBFREE9, Integer.valueOf(14));
        this.assistantMap.put(CMAssInfoItemVO.VBFREE10, Integer.valueOf(15));

    }

    /**
     * 设置单据表体4个固定辅助属性字段编码和辅助属性序号之间的对照关系
     *
     * @return
     *         key=单据表体辅助属性字段编码,value=辅助属性序号
     */
    private void setFixedAssistantMap() {
        this.fixedAssistantMap.put(CMAssInfoItemVO.CPROJECTID, Integer.valueOf(2));
        this.fixedAssistantMap.put(CMAssInfoItemVO.CVENDORID, Integer.valueOf(3));
        this.fixedAssistantMap.put(CMAssInfoItemVO.CPRODUCTORID, Integer.valueOf(4));
        this.fixedAssistantMap.put(CMAssInfoItemVO.CCUSTOMERID, Integer.valueOf(5));

    }

    /**
     * 得到错误信息，包括未启用辅助属性却有值的和启用辅助属性却没值的
     *
     * @param materMap
     *            key=物料OID,value=Map<辅助属性字段,辅助属性值>
     * @param materialVersionMap
     *            key=物料OID,value=MaterialVersionVO
     * @param marFrameMap
     *            key=辅助属性结构ID,value=辅助属性序号列表
     * @return
     *         List Map key=物料OID,value=未启用辅助属性序号列表
     */
    private List<Map<String, Set<Integer>>> getErrorInfo(E[] vos, Map<String, MaterialVersionVO> materialVersionMap,
            Map<String, List<Integer>> marFrameMap) {
        List<Map<String, Set<Integer>>> resultList = new ArrayList<Map<String, Set<Integer>>>();

        if (CMArrayUtil.isEmpty(vos) || CMMapUtil.isEmpty(materialVersionMap) || CMMapUtil.isEmpty(marFrameMap)) {
            return null;
        }
        Map<String, Set<Integer>> nullErrMap = new HashMap<String, Set<Integer>>();// 应当为空的辅助属性
        for (E vo : vos) {
            CircularlyAccessibleValueObject[] itemVOs = vo.getChildrenVO();
            if (itemVOs == null || itemVOs.length == 0) {
                continue;
            }
            for (CircularlyAccessibleValueObject itemVO : itemVOs) {
                Object materialid = itemVO.getAttributeValue(this.materialField);
                if (materialid != null && !materialid.equals("~")) {
                    MaterialVersionVO materialVersionVO = materialVersionMap.get(materialid);
                    String pk_marasstframe = materialVersionVO.getPk_marasstframe();
                    List<Integer> tempInt = marFrameMap.get(pk_marasstframe); // 启用的辅助属性序号

                    for (String ass : this.assistantMap.keySet()) {
                        Object assValue = itemVO.getAttributeValue(ass);
                        if (this.fixedAssistantMap.get(ass) == null
                                && (CMValueCheck.isNotEmpty(tempInt) && !tempInt.contains(this.assistantMap.get(ass)) || CMValueCheck
                                        .isEmpty(tempInt)) && assValue != null) {
                            // 如果辅助属性类型为布尔型，即使没有启用也会有默认值"N"，是平台从前台界面取数时通过UFBooleanConverter设置的，影响校验，这里统一把没有启用但是值为"N"的设置为NULL
                            if ("N".equals(assValue)) {
                                // itemVO.setAttributeValue(ass, null);
                            }
                            if (nullErrMap.containsKey(materialid)) {
                                nullErrMap.get(materialid).add(this.assistantMap.get(ass));
                            }
                            else {
                                Set<Integer> tempValue = new HashSet<Integer>();
                                tempValue.add(this.assistantMap.get(ass)); // 辅助属性序号
                                nullErrMap.put(materialid.toString(), tempValue);
                            }
                        }

                    }
                }

            }

        }
        resultList.add(nullErrMap);
        return resultList;
    }

    /**
     * 得到工厂下的用户自定义项
     *
     * @param vos
     *            待校验聚合vo数组
     * @return
     *         用户自定义项vo数组
     */
    private UserdefitemVO[] queryUserDefitem(E[] vos) {
        String pk_org = null;
        for (int i = 0; i < vos.length; i++) {
            if (pk_org != null) {
                break;
            }
            if (vos[i] == null || vos[i].getParentVO() == null) {
                continue;
            }
            pk_org = vos[i].getParentVO().getAttributeValue("pk_org").toString();
        }

        UserdefitemVO[] defs = null;
        try {
            defs =
                    NCLocator
                            .getInstance()
                            .lookup(IUserdefitemQryService.class)
                            .queryUserdefitemVOsByUserdefruleCode(
                                    MaterialPriceBaseMarAssistantRule.MARASSISTANTRULECODE, pk_org);

        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
        return defs;
    }

    /**
     * 设置错误提示信息
     *
     * @param mustErrMap
     *            key=物料OID,value=必须填写的辅助属性序号列表
     * @param materialVersionMap
     *            key=物料OID,value=MaterialVersionVO
     * @param userdefitemVOs
     *            用户自定义项vo数组
     */
    private void setErrorMsg(Map<String, Set<Integer>> mustErrMap, Map<String, MaterialVersionVO> materialVersionMap,
            UserdefitemVO[] userdefitemVOs) {
        HashMap<Integer, String> itemIndexShowNameMap = new HashMap<Integer, String>();
        for (UserdefitemVO userdefitemVO : userdefitemVOs) {
            itemIndexShowNameMap.put(userdefitemVO.getPropindex(), userdefitemVO.getShowname());
        }
        StringBuilder str = new StringBuilder();

        for (Entry<String, Set<Integer>> entry : mustErrMap.entrySet()) {
            String materialid = entry.getKey();
            Set<Integer> assistant = entry.getValue();
            MaterialVersionVO materialVersionVO = materialVersionMap.get(materialid);
            StringBuffer showname = new StringBuffer();
            for (Integer code : assistant) {
                if (CMStringUtil.isEmpty(itemIndexShowNameMap.get(code))) {
                    showname.append(" ["
                            + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0347")/*
                             * @res
                             * "自由辅助属性"
                             */
                            + Integer.toString(code.intValue() - 5) + "] ");
                }
                else {
                    showname.append(" [" + itemIndexShowNameMap.get(code) + "] ");
                }
            }
            str.append(String.format(CMLangConstPub.getNullMaterAssMsg(), materialVersionVO.getName(), showname));
            str.append(CMConstant.CRLF);
        }
        if (CMStringUtil.isNotEmpty(str.toString())) {
            ExceptionUtils.wrappBusinessException(str.toString());
        }

    }
}
