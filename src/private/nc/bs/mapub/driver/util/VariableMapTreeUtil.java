package nc.bs.mapub.driver.util;

import java.util.List;
import java.util.Map;

import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.CMDriverParameterEnum;
import nc.vo.pub.lang.UFDouble;

public class VariableMapTreeUtil {
    private Map<Object, Object> variableMap;

    private List<Object> keyList;

    public VariableMapTreeUtil(Map<Object, Object> variableMap, List<Object> variableKeyList) {
        this.variableMap = variableMap;
        this.keyList = variableKeyList;
    }

    public UFDouble getValue(boolean hasMaterial, boolean isCTCFinishAllocate) {
        UFDouble value = this.getValueByVariableMap(this.variableMap, 0);
        if (value == null) {
            String variable = this.keyList.get(0).toString();
            if (CMDriverParameterEnum.MAIN_STUFF_CONSUME_QUOTA.getCode().equals(variable)
                    || CMDriverParameterEnum.ACTUAL_STUFF_NUMBER.getCode().equals(variable)) {
                return null;
            }
            else if (isCTCFinishAllocate) {
                if (CMDriverParameterEnum.ACTUAL_ACTIVITY_NUMBER.getCode().equals(variable)
                        || CMDriverParameterEnum.STANDARD_ACTIVITY_NUMBER.getCode().equals(variable)
                        || CMDriverParameterEnum.BOM_ACTIVITY_NUMBER.getCode().equals(variable)
                        || CMDriverParameterEnum.RT_ACTIVITY_NUMBER.getCode().equals(variable)
                        || variable.indexOf(CMDriverLangConst.OTHER) > -1) {
                    return null;
                }
            }
            if (CMDriverParameterEnum.BOM_STUFF_CONSUME_QUOTA.getCode().equals(variable)
                    || CMDriverParameterEnum.RT_STUFF_CONSUME_QUOTA.getCode().equals(variable)
                    || CMDriverParameterEnum.MO_STUFF_CONSUME_QUOTA.getCode().equals(variable)
                    || CMDriverParameterEnum.BOM_JOINTBY_OUTPUT_QUOTA.getCode().equals(variable)) {
                if (!hasMaterial) {
                    return null;
                }
            }
            value = UFDouble.ZERO_DBL;
        }
        return value;
    }

    @SuppressWarnings("unchecked")
    private UFDouble getValueByVariableMap(Map<Object, Object> map, int layer) {
        Object obj = map.get(this.keyList.get(layer));
        if (obj == null) {
            return null;
        }
        if (obj instanceof UFDouble) {
            return (UFDouble) obj;
        }
        if (obj instanceof Map || layer < this.keyList.size() - 1) {
            return this.getValueByVariableMap((Map<Object, Object>) obj, ++layer);
        }
        return null;
    }
}
