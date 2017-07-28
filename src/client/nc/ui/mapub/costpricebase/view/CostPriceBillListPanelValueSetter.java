/**
 *
 */
package nc.ui.mapub.costpricebase.view;

import java.util.HashSet;
import java.util.Set;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.uif2.editor.BillListView.IBillListPanelValueSetterExt;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;
import nc.vo.mapub.costpricebase.entity.CostPriceHeadVO;

/**
 * @since v6.3
 * @version 2017��7��19�� ����9:19:01
 * @author Administrator
 */
public class CostPriceBillListPanelValueSetter implements IBillListPanelValueSetterExt {
    /*
     * (non-Javadoc)
     * @see nc.ui.uif2.editor.BillListView.IBillListPanelValueSetter#setHeaderDatas(nc.ui.pub.bill.BillListPanel,
     * java.lang.Object[])
     */
    @Override
    public void setHeaderDatas(BillListPanel listPanel, Object[] allDatas) {
        // TODO Auto-generated method stub
        // ��ѯ��(����AggVO)

        Set<String> orgSet = new HashSet<String>();
        for (Object obj : allDatas) {
            // allDatas�ض���CostPriceAggVO���͵����飬��ֱ��ǿת
            CostPriceHeadVO headVO = ((CostPriceAggVO) obj).getParentVO();
            String ccostPriceid = headVO.getCcostpriceid();
            // ������ȡ���õ�ҵ��Ԫ��֮����ͳһ����pk��ѯ��code
            // if (CMValueCheck.isNotEmpty(vpriceSourceNum)) {
            // String[] priceSourcesNum = vpriceSourceNum.split("\\,");
            // for (String priceSource : priceSourcesNum) {
            // if (priceSource.endsWith("]")) {
            // String pk_org = priceSource.substring(priceSource.indexOf("[") + 1, priceSource.indexOf("]"));
            // orgSet.add(pk_org);
            // }
            // }
            // }
        }
        // try {
        // Map<String, String> oid_codeMap = BDAdapter.queryOrgCode(orgSet.toArray(new String[orgSet.size()]));
        // for (Object obj : allDatas) {
        // CostPriceHeadVO headVO = ((CostPriceAggVO) obj).getParentVO();
        // String vpriceSourceNum = headVO.getVpricesourcenum();
        // if (CMValueCheck.isNotEmpty(vpriceSourceNum)) {
        // // ����
        // String vpriceSourcesName = this.getDataConversion(vpriceSourceNum, oid_codeMap);
        // headVO.setVpricesource(vpriceSourcesName);
        // }
        // }
        // }
        // catch (BusinessException e) {
        // ExceptionUtils.wrappException(e);
        // }
        // ����
        listPanel.getHeadBillModel().setSortColumn(new String[] {
            CostPriceHeadVO.PK_ORG_V, CostPriceHeadVO.CREATETIME
        });
        listPanel.getBillListData().setHeaderValueObjectByMetaData(allDatas);
        listPanel.getBillListData().getHeadBillModel().execLoadFormula();

    }

    /*
     * (non-Javadoc)
     * @see nc.ui.uif2.editor.BillListView.IBillListPanelValueSetter#setHeaderRowData(nc.ui.pub.bill.BillListPanel,
     * java.lang.Object, int)
     */
    @Override
    public void setHeaderRowData(BillListPanel arg0, Object arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see nc.ui.uif2.editor.BillListView.IBillListPanelValueSetterExt#setHeaderRowsData(nc.ui.pub.bill.BillListPanel,
     * java.lang.Object[], int[])
     */
    @Override
    public void setHeaderRowsData(BillListPanel arg0, Object[] arg1, int[] arg2) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see nc.ui.uif2.editor.BillListView.IBillListPanelValueSetter#setBodyData(nc.ui.pub.bill.BillListPanel,
     * java.lang.Object)
     */
    @Override
    public void setBodyData(BillListPanel arg0, Object arg1) {
        // TODO Auto-generated method stub

    }

}
