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
 * ȡ�۷���
 * 
 * @since 6.36
 * @version 2014-12-4 ����4:12:21
 * @author zhangchd
 */
public class PullPriceService {

    public PullPriceService() {

    }

    /**
     * ִ��ȡ��
     * 
     * @param selectData ѡ������
     * @param pullType ȡ������
     * @return String
     */
    public String getPullPriceInfo(Object selectData, int[] pullType) {

        return null;
    }

    /**
     * ȡ��ǰУ��
     * 
     * @param selectData
     * @return
     */
    public String getPullPriceBeforeErroInfo(Object selectData) {
        StringBuffer errInfo = new StringBuffer();
        // 1. �жϰ��ձ�ͷ�����ȡ�۵���Դ�Ƿ�ֻ�У��ֹ�¼�룬������򱨴���ȡ��
        this.getIsManuePriceErroInfo(selectData, errInfo);

        return errInfo.toString();
    }

    /**
     * 1. �жϰ��ձ�ͷ�����ȡ�۵���Դ�Ƿ�ֻ�У��ֹ�¼�룬������򱨴���ȡ��
     * 
     * @param selectData ѡ�������
     * @param errInfo ������Ϣ
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

            // ˵��ȡ��ͷ�����岢��
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
            else {// ֻȡ����
                result.addAll(bodyResult);
            }

            if (result.size() == 1 && result.contains(String.valueOf(PriceSourceEnum.MANUAL.toIntValue()))) {
                errInfo.append(CMMLangConstMaterialPriceBase.getMSG29());
                errInfo.append("\r\n");
            }
        }
    }
}
