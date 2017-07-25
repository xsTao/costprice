package nc.ui.mapub.materialpricebase.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bd.framework.base.CMValueCheck;
import nc.cmpub.business.adapter.BDAdapter;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceSourcesAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceSourcesBodyVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceSourcesHeadVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPullPriceAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPullPriceBodyVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPullPriceHeadVO;
import nc.vo.mapub.materialpricebase.enumeration.PriceSourceEnum;

/**
 * ��ͷ���������۸���Դֵ���Լ��ֶεĽ���
 *
 * @since 6.36
 * @version 2014-11-19 ����1:37:20
 * @author zhangchd
 */
public class PricSourcesQueryService {

    private BillForm billform;

    public PricSourcesQueryService() {

    }

    /**
     * ͨ���������ݣ�����ȡ�۶Ի����ʼ����
     *
     * @param selectData ����ѡ�������
     * @return MaterialPullPriceAggVO[]
     */
    public MaterialPullPriceAggVO[] getMaterialPullPriceAggVOByBillFormDatas(Object selectData) {
        MaterialPullPriceAggVO materialPullPriceAggVO = new MaterialPullPriceAggVO();

        if (CMValueCheck.isNotEmpty(selectData)) {
            if (selectData instanceof MaterialPriceBaseAggVO) {
                MaterialPriceBaseAggVO materialPriceBaseAggVO = (MaterialPriceBaseAggVO) selectData;
                MaterialPriceBaseHeadVO materialPriceBaseHeadVO = materialPriceBaseAggVO.getParentVO();
                // ҵ��Ԫ
                String pk_org = materialPriceBaseHeadVO.getPk_org();
                // ����
                String pk_group = materialPriceBaseHeadVO.getPk_group();
                // ����ڼ䷽��
                String cperiodScheme = null;
                if (CMValueCheck.isNotEmpty(pk_org)) {
                    cperiodScheme = BDAdapter.getPKAccountSchemeByOrg(pk_org);
                }

                MaterialPullPriceHeadVO materialPullPriceHeadVO = new MaterialPullPriceHeadVO();
                materialPullPriceHeadVO.setCperiodscheme(cperiodScheme);
                materialPullPriceHeadVO.setPk_org(pk_org);
                materialPullPriceHeadVO.setPk_group(pk_group);

                materialPullPriceAggVO.setParentVO(materialPullPriceHeadVO);
                materialPullPriceAggVO.setChildrenVO(new MaterialPullPriceBodyVO[0]);
            }
        }
        return new MaterialPullPriceAggVO[] {
            materialPullPriceAggVO
        };

    }

    /**
     * ��ȡ����۸���Դ���ϣ�����󼯺�Ϊ����׼�ɱ���ƽ���ɹ���ⵥ�ۣ����½���
     *
     * @param selectData
     * @return Set
     */
    @SuppressWarnings("boxing")
    public Set<String> getVBPullPriceByBillFormDatas(Object selectData) {
        Set<String> result = new HashSet<String>();
        if (CMValueCheck.isEmpty(selectData)) {
            return result;
        }
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
                    if (CMValueCheck.isNotEmpty(vpricesourcenum)) {
                        String[] priceSources = vpricesourcenum.split("\\,");
                        for (String priceSource : priceSources) {
                            if (priceSource.endsWith("]")) {
                                String[] splitStrings = priceSource.split("\\[");

                                String priceSourceVO = splitStrings[0];
                                // ƽ���ɹ���ⵥ�ۡ����½���
                                if (String.valueOf(PriceSourceEnum.PINGJUNCAIGOURUKU.toIntValue())
                                        .equals(priceSourceVO)
                                        || String.valueOf(PriceSourceEnum.FORWARD.toIntValue()).equals(priceSourceVO)) {
                                    bodyResult.add(priceSourceVO);
                                }
                            }
                            // ��׼�ɱ�
                            else if (String.valueOf(PriceSourceEnum.STDCOST.toIntValue()).equals(priceSource)) {
                                bodyResult.add(priceSource);
                            }
                        }
                    }
                    else {
                        isMerge = true;
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
                                // ƽ���ɹ���ⵥ�ۡ����½���
                                if (String.valueOf(PriceSourceEnum.PINGJUNCAIGOURUKU.toIntValue())
                                        .equals(priceSourceVO)
                                        || String.valueOf(PriceSourceEnum.FORWARD.toIntValue()).equals(priceSourceVO)) {
                                    bodyResult.add(priceSourceVO);
                                }
                            }
                            else if (String.valueOf(PriceSourceEnum.STDCOST.toIntValue()).equals(priceSource)) {// ��׼�ɱ�
                                bodyResult.add(priceSource);
                            }
                        }
                    }
                    result.addAll(bodyResult);
                }
                else {// ֻȡ����
                    result.addAll(bodyResult);
                }
            }
        }
        return result;
    }

    /**
     * @return �������۸���Դֵ
     */
    public MaterialPriceSourcesAggVO[] getPriceSourcesByBodyBillForm() {
        // ��ǰѡ����
        BillScrollPane bsp = this.getBillform().getBillCardPanel().getBodyPanel();
        UITable table = bsp.getTable();
        int row = table.getSelectedRow();

        Object result =
                this.getBillform().getBillCardPanel().getBodyValueAt(row, MaterialPriceBaseBodyVO.VPRICESOURCENUM);
        List<MaterialPriceSourcesBodyVO> materialPriceSourcesBodyList = new ArrayList<MaterialPriceSourcesBodyVO>();
        MaterialPriceSourcesAggVO materialPriceSourcesAggVO = new MaterialPriceSourcesAggVO();
        MaterialPriceSourcesHeadVO materialPriceSourcesHeadVO = new MaterialPriceSourcesHeadVO();

        // �����۸���Դ��ֵ
        if (CMValueCheck.isNotEmpty(result)) {
            String[] priceSources = result.toString().split("\\,");
            for (String priceSource : priceSources) {

                MaterialPriceSourcesBodyVO materialPriceSourcesBodyVO = new MaterialPriceSourcesBodyVO();

                if (priceSource.endsWith("]")) {
                    String[] splitStrings = priceSource.split("\\[");

                    String priceSourceVO = splitStrings[0];
                    String pk_orgVO = splitStrings[1].substring(0, splitStrings[1].lastIndexOf("]"));

                    materialPriceSourcesBodyVO.setVpricesource(Integer.valueOf(priceSourceVO));
                    materialPriceSourcesBodyVO.setPk_org(pk_orgVO);
                }
                else {
                    materialPriceSourcesBodyVO.setVpricesource(Integer.valueOf(priceSource));
                }

                materialPriceSourcesBodyList.add(materialPriceSourcesBodyVO);
            }

        }
        else {
            MaterialPriceSourcesBodyVO materialPriceSourcesBodyVO = new MaterialPriceSourcesBodyVO();
            // TODO:��������Ը�Ĭ��ֵ
            materialPriceSourcesBodyList.add(materialPriceSourcesBodyVO);
            // return this.getPriceSourcesByHeadBillForm();
        }
        materialPriceSourcesAggVO.setParentVO(materialPriceSourcesHeadVO);
        materialPriceSourcesAggVO.setChildrenVO(materialPriceSourcesBodyList
                .toArray(new MaterialPriceSourcesBodyVO[materialPriceSourcesBodyList.size()]));

        return new MaterialPriceSourcesAggVO[] {
            materialPriceSourcesAggVO
        };
    }

    /**
     * @return ��ͷ����۸���Դֵ
     */
    public MaterialPriceSourcesAggVO[] getPriceSourcesByHeadBillForm() {
        Object result =
                this.getBillform().getBillCardPanel().getHeadItem(MaterialPriceBaseHeadVO.VPRICESOURCENUM)
                        .getValueObject();
        List<MaterialPriceSourcesBodyVO> materialPriceSourcesBodyList = new ArrayList<MaterialPriceSourcesBodyVO>();
        MaterialPriceSourcesAggVO materialPriceSourcesAggVO = new MaterialPriceSourcesAggVO();
        MaterialPriceSourcesHeadVO materialPriceSourcesHeadVO = new MaterialPriceSourcesHeadVO();

        // �����۸���Դ��ֵ
        if (CMValueCheck.isNotEmpty(result)) {
            String[] priceSources = result.toString().split("\\,");
            for (String priceSource : priceSources) {

                MaterialPriceSourcesBodyVO materialPriceSourcesBodyVO = new MaterialPriceSourcesBodyVO();

                if (priceSource.endsWith("]")) {
                    String[] splitStrings = priceSource.split("\\[");

                    String priceSourceVO = splitStrings[0];
                    String pk_orgVO = splitStrings[1].substring(0, splitStrings[1].lastIndexOf("]"));

                    materialPriceSourcesBodyVO.setVpricesource(Integer.valueOf(priceSourceVO));
                    materialPriceSourcesBodyVO.setPk_org(pk_orgVO);
                }
                else {
                    materialPriceSourcesBodyVO.setVpricesource(Integer.valueOf(priceSource));
                }

                materialPriceSourcesBodyList.add(materialPriceSourcesBodyVO);
            }

        }
        else {
            MaterialPriceSourcesBodyVO materialPriceSourcesBodyVO = new MaterialPriceSourcesBodyVO();
            // TODO:��������Ը�Ĭ��ֵ
            materialPriceSourcesBodyList.add(materialPriceSourcesBodyVO);
        }
        materialPriceSourcesAggVO.setParentVO(materialPriceSourcesHeadVO);
        materialPriceSourcesAggVO.setChildrenVO(materialPriceSourcesBodyList
                .toArray(new MaterialPriceSourcesBodyVO[materialPriceSourcesBodyList.size()]));

        return new MaterialPriceSourcesAggVO[] {
            materialPriceSourcesAggVO
        };
    }

    public BillForm getBillform() {
        return this.billform;
    }

    public void setBillform(BillForm billform) {
        this.billform = billform;
    }
}
