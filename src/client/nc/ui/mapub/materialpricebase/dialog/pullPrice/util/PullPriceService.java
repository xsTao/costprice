package nc.ui.mapub.materialpricebase.dialog.pullPrice.util;

import java.util.HashSet;
import java.util.Set;

import nc.bd.framework.base.CMValueCheck;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.mapub.materialpricebase.enumeration.PriceSourceEnum;

/**
 * 取价服务
 * 
 * @since 6.36
 * @version 2014-12-4 下午4:12:21
 * @author zhangchd
 */
public class PullPriceService {

    public PullPriceService() {

    }

    /**
     * 执行取价
     * 
     * @param selectData 选择数据
     * @param pullType 取价类型
     * @return String
     */
    public String getPullPriceInfo(Object selectData, int[] pullType) {

        return null;
    }

    /**
     * 取价前校验
     * 
     * @param selectData
     * @return
     */
    public String getPullPriceBeforeErroInfo(Object selectData) {
        StringBuffer errInfo = new StringBuffer();
        // 1. 判断按照表头或表体取价的来源是否只有：手工录入，如果是则报错，不取价
        this.getIsManuePriceErroInfo(selectData, errInfo);

        return errInfo.toString();
    }

    /**
     * 1. 判断按照表头或表体取价的来源是否只有：手工录入，如果是则报错，不取价
     * 
     * @param selectData 选择的数据
     * @param errInfo 错误信息
     */
    @SuppressWarnings("boxing")
    private void getIsManuePriceErroInfo(Object selectData, StringBuffer errInfo) {
        Set<String> result = new HashSet<String>();

        if (selectData instanceof MaterialPriceBaseAggVO) {
            Boolean isMerge = false;
            Set<String> bodyResult = new HashSet<String>();

            MaterialPriceBaseAggVO materialPriceBaseAggVO = (MaterialPriceBaseAggVO) selectData;
            MaterialPriceBaseHeadVO materialPriceBaseHeadVO = materialPriceBaseAggVO.getParentVO();
            MaterialPriceBaseBodyVO[] materialPriceSourcesBodyVO =
                    (MaterialPriceBaseBodyVO[]) materialPriceBaseAggVO.getAllChildrenVO();

            if (CMValueCheck.isNotEmpty(materialPriceSourcesBodyVO)) {
                for (MaterialPriceBaseBodyVO item : materialPriceSourcesBodyVO) {
                    String vpricesourcenum = item.getVpricesourcenum();
                    if (CMValueCheck.isEmpty(vpricesourcenum)) {
                        isMerge = true;
                    }
                    else {
                        String[] priceSources = vpricesourcenum.split("\\,");
                        for (String priceSource : priceSources) {
                            if (priceSource.endsWith("]")) {
                                String[] splitStrings = priceSource.split("\\[");

                                String priceSourceVO = splitStrings[0];
                                bodyResult.add(priceSourceVO);
                            }
                            else {
                                bodyResult.add(priceSource);
                            }
                        }
                    }
                }
            }

            // 说明取表头、表体并集
            if (isMerge) {
                String vpricesourcenum = materialPriceBaseHeadVO.getVpricesourcenum();
                if (CMValueCheck.isNotEmpty(vpricesourcenum)) {
                    String[] priceSources = vpricesourcenum.split("\\,");
                    for (String priceSource : priceSources) {
                        if (priceSource.endsWith("]")) {
                            String[] splitStrings = priceSource.split("\\[");

                            String priceSourceVO = splitStrings[0];

                            bodyResult.add(priceSourceVO);

                        }
                        else {
                            bodyResult.add(priceSource);
                        }
                    }
                }
                result.addAll(bodyResult);
            }
            else {// 只取表体
                result.addAll(bodyResult);
            }

            if (result.size() == 1 && result.contains(String.valueOf(PriceSourceEnum.MANUAL.toIntValue()))) {
                errInfo.append(CMMLangConstMaterialPriceBase.getMSG29());
                errInfo.append("\r\n");
            }
        }
    }
}
