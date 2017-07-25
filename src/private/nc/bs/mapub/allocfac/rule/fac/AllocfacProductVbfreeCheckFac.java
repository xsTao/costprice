/**
 * 
 */
package nc.bs.mapub.allocfac.rule.fac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.mapub.allocfac.rule.fac.AllocfacMaterialCheckFac;
import nc.bs.framework.common.NCLocator;
import nc.pubitf.uapbd.IMaterialPubService_C;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.mapub.allocfac.util.AllocfacItemUtil;
import nc.vo.mapub.allocfac.util.CMMLangConstAllocfac;
import nc.vo.mapub.allocfac.util.FieldKeyUtil;
import nc.vo.bd.material.MaterialVO;
import nc.vo.cmpub.framework.assistant.CMAssInfoItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2013-8-27 上午10:00:43
 * @author xionghuic
 */
public class AllocfacProductVbfreeCheckFac extends AllocfacMaterialCheckFac {
    private final String RN = "\r\n";

    private Map<FieldKeyUtil, String> valueMap = new HashMap<FieldKeyUtil, String>();

    private Set<String> idSet = new HashSet<String>();

    @Override
    public Set<String> getIdSet(AllocfacItemVO[] itemVOS) {
        Set<String> repSet = new HashSet<String>();
        List<String> nameList;
        for (int i = 0; i < itemVOS.length; i++) {
            nameList = new ArrayList<String>();
            String str = this.getStrByName(itemVOS[i], nameList);
            if (repSet.contains(str)) {
                String cmaterialid = itemVOS[i].getCmaterialid();
                this.idSet.add(cmaterialid);
                FieldKeyUtil util = new FieldKeyUtil(cmaterialid, this.getKey(nameList));
                if (this.valueMap.get(util) == null) {
                    String fields = this.getFieldByValue(nameList);
                    this.valueMap.put(util, fields);
                }
            }
            else {
                repSet.add(str);
            }
        }
        return this.idSet;
    }

    private Map<String, MaterialVO> getCodeMap(Set<String> idSet) {
        Map<String, MaterialVO> idToVO = new HashMap<String, MaterialVO>();
        try {
            idToVO =
                    NCLocator.getInstance().lookup(IMaterialPubService_C.class)
                            .queryMaterialBaseInfoByPks(idSet.toArray(new String[0]), new String[] {
                                "code"
                            });
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
        return idToVO;
    }

    private String getFieldByValue(List<String> nameList) {
        Map<String, String> fieldsMap = this.getFieldsMap();
        StringBuilder fields = new StringBuilder();
        for (String value : nameList) {
            String field = fieldsMap.get(value);
            if (field != null) {
                fields.append("+" + field);
            }
        }
        return fields.toString();
    }

    private String getKey(List<String> nameList) {
        StringBuilder key = new StringBuilder();
        for (String value : nameList) {
            key.append(value);
        }
        return key.toString();
    }

    // 拼接辅助服务字段
    private String getStrByName(CMAssInfoItemVO itemVO, List<String> nameList) {
        StringBuilder result = new StringBuilder();
        result.append(itemVO.getAttributeValue(AllocfacItemVO.CMATERIALID));
        for (String name : AllocfacItemUtil.VBFREE) {
            String value = (String) itemVO.getAttributeValue(name);
            result.append(value);
            if (value != null) {
                nameList.add(name);
            }
        }
        return result.toString();
    }

    @Override
    public void showWrongInfo(String mes) {
        StringBuilder errorInfo = new StringBuilder();
        errorInfo.append(CMMLangConstAllocfac.SAME_MATERIAL_VBFREE_ERRO());
        if (this.valueMap.size() > 1) {
            errorInfo.append(this.RN);
        }
        Map<String, MaterialVO> idToVO = this.getCodeMap(this.idSet);
        for (Map.Entry<FieldKeyUtil, String> e : this.valueMap.entrySet()) {
            String code = idToVO.get(e.getKey().getId()).getCode();
            errorInfo.append(CMMLangConstAllocfac.getHitCurrentBodyRepeat(code, e.getValue()) + this.RN);
        }
        ExceptionUtils.wrappBusinessException(errorInfo.toString());
    }
}
