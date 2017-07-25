/**
 * 
 */
package nc.bs.mapub.allocfac.rule.fac;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bd.framework.base.CMCollectionUtil;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.pub.VOStatus;

/**
 * @since v6.3
 * @version 2013-3-14 上午08:58:48
 * @author xionghuic
 */
public abstract class AllocfacCheckFac {
    private Map<String, String> fieldsMap;

    public void validate(AllocfacAggVO[] aggVO) {
        this.setFieldsMap(aggVO[0].getMap());
        for (AllocfacAggVO aggvo : aggVO) {
            AllocfacItemVO[] itemVOS = aggvo.getItemVO();
            List<AllocfacItemVO> voList = new ArrayList<AllocfacItemVO>();
            for (AllocfacItemVO itemVO : itemVOS) {
                if (itemVO.getStatus() == VOStatus.DELETED) {
                    continue;
                }
                voList.add(itemVO);
            }
            Set<String> idSet = this.getIdSet(voList.toArray(new AllocfacItemVO[0]));
            StringBuilder error = new StringBuilder();
            Set<String> codeSet = new HashSet<String>();
            if (CMCollectionUtil.isNotEmpty(idSet)) {
                codeSet = this.getCode(idSet);
            }
            for (String code : codeSet) {
                error.append(code + ";");
            }
            if (error.length() == 0) {
                return;
            }
            String mes = error.substring(0, error.length() - 1);
            this.showWrongInfo(mes);
        }
    }

    public abstract Set<String> getIdSet(AllocfacItemVO[] itemVOS);

    public abstract Set<String> getCode(Set<String> idSet);

    public abstract void showWrongInfo(String mes);

    /**
     * 获得 fieldsMap 的属性值
     */
    public Map<String, String> getFieldsMap() {
        return this.fieldsMap;
    }

    /**
     * 设置 fieldsMap 的属性值
     */
    public void setFieldsMap(Map<String, String> fieldsMap) {
        this.fieldsMap = fieldsMap;
    }
}
