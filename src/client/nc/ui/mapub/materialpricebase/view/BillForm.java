package nc.ui.mapub.materialpricebase.view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bd.framework.base.CMValueCheck;
import nc.cmpub.business.adapter.BDAdapter;
import nc.ui.mapub.materialpricebase.dialog.priceSources.util.PriceSourcesEnumMap;
import nc.ui.mapub.materialpricebase.dialog.priceSources.view.head.PriceSourcesRefPanel;
import nc.ui.mapub.materialpricebase.scale.MaterialPriceBaseScaleUtil;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 材料价格库
 *
 * @since 6.36
 * @version 2014-11-7 下午4:14:10
 * @author zhangchd
 */
public class BillForm extends ShowUpableBillForm {
    private static final long serialVersionUID = -7288841559873749687L;

    private PriceSourcesRefPanel priceRefPanel;

    @Override
    protected void onEdit() {
        super.onEdit();
        this.getPriceRefPanel().setRefEditable(true);
    }

    @Override
    protected void onNotEdit() {
        super.onNotEdit();
        this.getPriceRefPanel().setRefEditable(false);
    }

    @Override
    public void initUI() {
        super.initUI();
        new MaterialPriceBaseScaleUtil().setCardScale(this.getModel().getContext().getPk_group(),
                this.getBillCardPanel());
        MaterialPriceBillTableBatchCopy billTableBatchCopy = new MaterialPriceBillTableBatchCopy();
        billTableBatchCopy.setBillCardPanel(this.getBillCardPanel());
        this.getBillCardPanel().getBodyPanel().addBatchCopyListener(billTableBatchCopy);
    }

    @Override
    public void setValue(Object object) {
        if (object instanceof MaterialPriceBaseAggVO) {
            Set<String> orgSet = new HashSet<String>();
            Map<String, String> oid_codeMap = new HashMap<String, String>();
            // 获取表头【价格来源数】
            MaterialPriceBaseHeadVO headVO = ((MaterialPriceBaseAggVO) object).getParentVO();
            String vpriceSourceNum = headVO.getVpricesourcenum();
            if (CMValueCheck.isNotEmpty(vpriceSourceNum)) {
                String[] priceSourcesNum = vpriceSourceNum.split("\\,");
                for (String priceSource : priceSourcesNum) {
                    if (priceSource.endsWith("]")) {
                        String[] splitStrings = priceSource.split("\\[");
                        String pk_org = splitStrings[1].substring(0, splitStrings[1].lastIndexOf("]"));
                        orgSet.add(pk_org);
                    }
                }
            }
            // 表体
            MaterialPriceBaseBodyVO[] bodyVOS =
                    (MaterialPriceBaseBodyVO[]) ((MaterialPriceBaseAggVO) object).getAllChildrenVO();
            if (CMValueCheck.isNotEmpty(bodyVOS)) {
                for (MaterialPriceBaseBodyVO bodyVO : bodyVOS) {
                    // 获取表体【价格来源数】
                    String vpriceBodySourceNum = bodyVO.getVpricesourcenum();
                    // 获取表体【实际价格来源数】
                    String vpricesourcerealNum = bodyVO.getVpricesourcerealnum();
                    if (CMValueCheck.isNotEmpty(vpriceBodySourceNum)) {
                        String[] priceBodySourcesNum = vpriceBodySourceNum.split("\\,");
                        for (String priceSource : priceBodySourcesNum) {
                            if (priceSource.endsWith("]")) {
                                String[] splitStrings = priceSource.split("\\[");
                                String pk_org = splitStrings[1].substring(0, splitStrings[1].lastIndexOf("]"));
                                orgSet.add(pk_org);
                            }
                        }
                    }
                    if (CMValueCheck.isNotEmpty(vpricesourcerealNum)) {
                        String[] priceBodySourcesNum = vpricesourcerealNum.split("\\,");
                        for (String priceSource : priceBodySourcesNum) {
                            if (priceSource.endsWith("]")) {
                                String[] splitStrings = priceSource.split("\\[");
                                String pk_org = splitStrings[1].substring(0, splitStrings[1].lastIndexOf("]"));
                                orgSet.add(pk_org);
                            }
                        }
                    }
                }
                try {
                    oid_codeMap = BDAdapter.queryOrgCode(orgSet.toArray(new String[orgSet.size()]));
                    // 表头
                    String vpriceSourcesName = this.getDataConversion(vpriceSourceNum, oid_codeMap);
                    headVO.setVpricesource(vpriceSourcesName);
                    // 表体
                    for (MaterialPriceBaseBodyVO bodyVO : bodyVOS) {
                        // 获取表体【价格来源数】
                        String vpriceBodySourceNum = bodyVO.getVpricesourcenum();
                        // 获取表体【实际价格来源数】
                        String vpricesourcerealNum = bodyVO.getVpricesourcerealnum();
                        if (CMValueCheck.isNotEmpty(vpriceBodySourceNum)) {
                            String vpriceSourcesBodyName = this.getDataConversion(vpriceBodySourceNum, oid_codeMap);
                            // 表体赋值
                            bodyVO.setVpricesource(vpriceSourcesBodyName);
                        }
                        if (CMValueCheck.isNotEmpty(vpricesourcerealNum)) {
                            String vpriceSourcesBodyName = this.getDataConversion(vpricesourcerealNum, oid_codeMap);
                            // 表体赋值
                            bodyVO.setVpricesourcereal(vpriceSourcesBodyName);
                        }
                    }
                }
                catch (BusinessException e) {
                    ExceptionUtils.wrappException(e);
                }
            }

        }
        super.setValue(object);
    }

    public PriceSourcesRefPanel getPriceRefPanel() {
        return this.priceRefPanel;
    }

    public void setPriceRefPanel(PriceSourcesRefPanel priceRefPanel) {
        this.priceRefPanel = priceRefPanel;
    }

    /**
     * 数据转换
     *
     * @param vpriceBodySourceNum
     * @param oid_codeMap
     * @return data
     */
    private String getDataConversion(String vpriceBodySourceNum, Map<String, String> oid_codeMap) {
        StringBuffer vpriceSourcesBodyName = new StringBuffer();

        String[] priceBodySourcesNum = vpriceBodySourceNum.split("\\,");
        for (String priceSource : priceBodySourcesNum) {
            if (priceSource.endsWith("]")) {
                String[] splitStrings = priceSource.split("\\[");

                String price_Sour = splitStrings[0];
                String org_Code;
                if ("null".equals(splitStrings[1].substring(0, splitStrings[1].lastIndexOf("]")))) {
                    org_Code = null;
                }
                else {
                    org_Code = oid_codeMap.get(splitStrings[1].substring(0, splitStrings[1].lastIndexOf("]")));
                }

                vpriceSourcesBodyName
                .append(PriceSourcesEnumMap.getPriceSourcesEnum().get(Integer.valueOf(price_Sour)));
                vpriceSourcesBodyName.append("[");
                vpriceSourcesBodyName.append(org_Code);
                vpriceSourcesBodyName.append("]");
                vpriceSourcesBodyName.append(",");

            }
            else {
                vpriceSourcesBodyName.append(PriceSourcesEnumMap.getPriceSourcesEnum()
                        .get(Integer.valueOf(priceSource)));
                vpriceSourcesBodyName.append(",");
            }

        }

        vpriceSourcesBodyName.deleteCharAt(vpriceSourcesBodyName.lastIndexOf(","));

        return vpriceSourcesBodyName.toString();

    }

}
