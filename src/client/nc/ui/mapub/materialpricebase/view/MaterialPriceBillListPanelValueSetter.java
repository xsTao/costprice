package nc.ui.mapub.materialpricebase.view;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bd.framework.base.CMValueCheck;
import nc.cmpub.business.adapter.BDAdapter;
import nc.ui.mapub.materialpricebase.dialog.priceSources.util.PriceSourcesEnumMap;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.uif2.editor.BillListView.IBillListPanelValueSetterExt;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 界面参照字段值转换
 *
 * @since 6.36
 * @version 2014-11-10 下午5:44:44
 * @author zhangchd
 */
public class MaterialPriceBillListPanelValueSetter implements IBillListPanelValueSetterExt {

    @Override
    public void setHeaderDatas(BillListPanel listPanel, Object[] allDatas) {
        // 查询走(所有AggVO)
        Set<String> orgSet = new HashSet<String>();

        for (Object obj : allDatas) {
            // allDatas必定是MaterialPriceBaseAggVO类型的数组，故直接强转
            MaterialPriceBaseHeadVO headVO = ((MaterialPriceBaseAggVO) obj).getParentVO();
            String vpriceSourceNum = headVO.getVpricesourcenum();
            // 遍历获取所用的业务单元，之后在统一根据pk查询其code
            if (CMValueCheck.isNotEmpty(vpriceSourceNum)) {
                String[] priceSourcesNum = vpriceSourceNum.split("\\,");
                for (String priceSource : priceSourcesNum) {
                    if (priceSource.endsWith("]")) {
                        String pk_org = priceSource.substring(priceSource.indexOf("[") + 1, priceSource.indexOf("]"));
                        orgSet.add(pk_org);
                    }
                }
            }
        }

        try {
            Map<String, String> oid_codeMap = BDAdapter.queryOrgCode(orgSet.toArray(new String[orgSet.size()]));

            for (Object obj : allDatas) {
                MaterialPriceBaseHeadVO headVO = ((MaterialPriceBaseAggVO) obj).getParentVO();
                String vpriceSourceNum = headVO.getVpricesourcenum();
                if (CMValueCheck.isNotEmpty(vpriceSourceNum)) {
                    // 翻译
                    String vpriceSourcesName = this.getDataConversion(vpriceSourceNum, oid_codeMap);
                    headVO.setVpricesource(vpriceSourcesName);
                }
            }
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
        // 排序
        listPanel.getHeadBillModel().setSortColumn(new String[] {
                MaterialPriceBaseHeadVO.PK_ORG_V, MaterialPriceBaseHeadVO.VPRICECODE
        });
        listPanel.getBillListData().setHeaderValueObjectByMetaData(allDatas);
        listPanel.getBillListData().getHeadBillModel().execLoadFormula();
    }

    @Override
    public void setHeaderRowData(BillListPanel listPanel, Object rowData, int row) {
        // 刷新走（rowData，1个AggVO,row=0）
        // 获取表头【价格来源数】
        MaterialPriceBaseHeadVO headVO = ((MaterialPriceBaseAggVO) rowData).getParentVO();
        String vpriceSourceNum = headVO.getVpricesourcenum();
        if (CMValueCheck.isNotEmpty(vpriceSourceNum)) {
            Set<String> orgSet = new HashSet<String>();

            String[] priceSourcesNum = vpriceSourceNum.split("\\,");
            for (String priceSource : priceSourcesNum) {
                if (priceSource.endsWith("]")) {
                    String pk_org = priceSource.substring(priceSource.indexOf("[") + 1, priceSource.indexOf("]"));
                    orgSet.add(pk_org);
                }

            }

            try {
                Map<String, String> oid_codeMap = BDAdapter.queryOrgCode(orgSet.toArray(new String[orgSet.size()]));

                String vpriceSourcesName = this.getDataConversion(vpriceSourceNum, oid_codeMap);

                headVO.setVpricesource(vpriceSourcesName);

            }
            catch (BusinessException e) {
                ExceptionUtils.wrappException(e);
            }

        }

        listPanel.getBillListData().setHeaderValueRowObjectByMetaData(rowData, row);
        listPanel.getBillListData().getHeadBillModel().execLoadFormulaByRow(row);
    }

    @Override
    public void setBodyData(BillListPanel listPanel, Object selectedData) {
        // 查询走（selectedData为空，只有1个AggVO）,刷新走
        if (selectedData != null) {
            MaterialPriceBaseBodyVO[] bodyVOS =
                    (MaterialPriceBaseBodyVO[]) ((MaterialPriceBaseAggVO) selectedData).getAllChildrenVO();
            if (CMValueCheck.isNotEmpty(bodyVOS)) {

                Set<String> orgSet = new HashSet<String>();

                for (MaterialPriceBaseBodyVO bodyVO : bodyVOS) {
                    // 获取表体【价格来源数】
                    String vpriceBodySourceNum = bodyVO.getVpricesourcenum();
                    // 获取表体【实际价格来源数】
                    String vpricesourcerealNum = bodyVO.getVpricesourcerealnum();

                    if (CMValueCheck.isNotEmpty(vpriceBodySourceNum)) {

                        String[] priceBodySourcesNum = vpriceBodySourceNum.split("\\,");
                        for (String priceSource : priceBodySourcesNum) {
                            if (priceSource.endsWith("]")) {
                                String pk_org =
                                        priceSource.substring(priceSource.indexOf("[") + 1, priceSource.indexOf("]"));
                                orgSet.add(pk_org);
                            }

                        }
                    }
                    if (CMValueCheck.isNotEmpty(vpricesourcerealNum)) {

                        String[] priceBodySourcesNum = vpricesourcerealNum.split("\\,");
                        for (String priceSource : priceBodySourcesNum) {
                            if (priceSource.endsWith("]")) {
                                String pk_org =
                                        priceSource.substring(priceSource.indexOf("[") + 1, priceSource.indexOf("]"));
                                orgSet.add(pk_org);
                            }

                        }
                    }
                }

                try {
                    Map<String, String> oid_codeMap = BDAdapter.queryOrgCode(orgSet.toArray(new String[orgSet.size()]));

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
        // listPanel.getBodyBillModel().setSortColumn(
        // new String[] {
        // MaterialPriceBaseBodyVO.CMATERIALID, MaterialPriceBaseBodyVO.CPROJECTID,
        // MaterialPriceBaseBodyVO.CVENDORID, MaterialPriceBaseBodyVO.CPRODUCTORID,
        // MaterialPriceBaseBodyVO.CCUSTOMERID, MaterialPriceBaseBodyVO.CMATERIALID,
        // MaterialPriceBaseBodyVO.VBFREE1, MaterialPriceBaseBodyVO.VBFREE2, MaterialPriceBaseBodyVO.VBFREE3,
        // MaterialPriceBaseBodyVO.VBFREE4, MaterialPriceBaseBodyVO.VBFREE5, MaterialPriceBaseBodyVO.VBFREE6,
        // MaterialPriceBaseBodyVO.VBFREE7, MaterialPriceBaseBodyVO.VBFREE8, MaterialPriceBaseBodyVO.VBFREE9,
        // MaterialPriceBaseBodyVO.VBFREE10,
        //
        // });
        listPanel.getBillListData().setBodyValueObjectByMetaData(selectedData);
        listPanel.getBillListData().getBodyBillModel().execLoadFormula();

    }

    @Override
    public void setHeaderRowsData(BillListPanel listPanel, Object[] datas, int[] indexs) {
        if (ArrayUtils.isEmpty(indexs) || ArrayUtils.isEmpty(datas) || datas.length != indexs.length) {
            return;
        }

        listPanel.getBillListData().setHeaderValueRowsObjectByMetaData(datas, indexs);
        listPanel.getBillListData().getHeadBillModel().execLoadFormula();
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
                String org_Code = oid_codeMap.get(splitStrings[1].substring(0, splitStrings[1].lastIndexOf("]")));
                // 翻译价格来源枚举
                vpriceSourcesBodyName
                .append(PriceSourcesEnumMap.getPriceSourcesEnum().get(Integer.valueOf(price_Sour)));
                vpriceSourcesBodyName.append("[");
                // 翻译业务单元
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
