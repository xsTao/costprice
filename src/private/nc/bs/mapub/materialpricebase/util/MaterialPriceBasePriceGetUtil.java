package nc.bs.mapub.materialpricebase.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.vo.cmpub.business.aquireprice.CMPriceParamVO;
import nc.vo.cmpub.business.aquireprice.AquirePriceParamVO;
import nc.vo.cmpub.business.enumeration.PriceResTypeEnum;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * ȡ�۹�����
 * 
 * @since 6.0
 * @version 2014-10-15 ����8:24:41
 * @author baoxina
 */
public class MaterialPriceBasePriceGetUtil {

    /**
     * ȡ�۷���
     * 
     * @param aggVo
     * @return
     * @throws BusinessException
     */
    @SuppressWarnings("boxing")
    public Map<MaterialParamVO, UFDouble> getPrice(Map<MaterialParamVO, List<SourceParamVO>> matSourceMap)
            throws BusinessException {
        if (matSourceMap == null || matSourceMap.size() == 0) {
            return new HashMap<MaterialParamVO, UFDouble>();
        }
        // Ҫ���ص� ����-�۸� ����(��ȡ���۵�)
        Map<MaterialParamVO, UFDouble> matrPriceMap = new HashMap<MaterialParamVO, UFDouble>();
        // modify by zhangchdV636 �����⣬��ȥ��

        // // ����δȡ���۵����ϼ���(û�м۸������)
        // List<MaterialParamVO> haveNonePriceMatrList = new ArrayList<MaterialParamVO>();
        // // ��һ��ȡ��
        // int num = 1;
        // PriceFacade facade = new PriceFacade();
        // while (matSourceMap.size() > 0) {
        // // ��N��ѭ����Ҫȡ�����ݼ���
        // List<PriceParamVO> priceParamList = new ArrayList<PriceParamVO>();
        // // ��ɾ������
        // List<MaterialParamVO> deleteParam = new ArrayList<MaterialParamVO>();
        //
        // // ������װ��ȡ�۲���
        // for (Entry<MaterialParamVO, List<SourceParamVO>> entry : matSourceMap.entrySet()) {
        // MaterialParamVO materialParamVO = entry.getKey();
        // List<SourceParamVO> sourceParamList = entry.getValue();
        // if (num > sourceParamList.size()) {
        // haveNonePriceMatrList.add(materialParamVO);
        // deleteParam.add(materialParamVO);
        // continue;
        // }
        // // ��N��ȡ��ʱ,��ÿһ�����Ϻ����ĵ�N����Դת����ȡ�۲���PriceParamVO����ӵ���N����Ҫȡ�۵ļ��ϵ���
        // priceParamList.add(this.getPriceParams(materialParamVO, sourceParamList.get(num)));
        // }
        //
        // // ����һ����ǰ���չ�ϵ���������ϵ�ȡ�۽��map
        // Map<CMPriceParamVO, UFDouble> allMaterialPriceMap = new HashMap<CMPriceParamVO, UFDouble>();
        //
        // /**
        // * ��priceParamList(��N��ѭ����Ҫȡ�����ݼ���)�е����ݽ��з���,�������ȣ�10000(ÿ10000�����ϵ���һ��ȡ�۷���)
        // */
        // int bachUnit = this.getBatchUnitCycleTotal(priceParamList).get(1);
        // int cycleTotal = this.getBatchUnitCycleTotal(priceParamList).get(2);
        // // ÿ�η�����ȡ��������
        // List<PriceParamVO> batchPriceParamList = new ArrayList<PriceParamVO>();
        // for (int i = 0; i < cycleTotal; i++) {
        // for (int j = 0; j < bachUnit; j++) {
        // batchPriceParamList.add(priceParamList.get(j));
        // }
        // // ִ��ȡ�۷���ȡÿ�η���������,��ȡ�����۸�
        // Map<CMPriceParamVO, UFDouble> batchPriceMap =
        // facade.doPriceAcquire(batchPriceParamList.toArray(new PriceParamVO[batchPriceParamList.size()]));
        // // �������۸���뵽�������ϵ�ȡ�۽��map��
        // allMaterialPriceMap.putAll(batchPriceMap);
        // // ���������ϼ������Ƴ��Ѿ�����ȡ������
        // priceParamList.removeAll(batchPriceParamList);
        // // �Ƴ���ǰ�������ϼ����е�����
        // batchPriceParamList.clear();
        // }
        // // ͨ������ת�����õ�ת���������ȡ�۽��
        // Map<MaterialParamVO, UFDouble> transferedPriceMap = this.getTransferedPriceParamMap(allMaterialPriceMap);
        //
        // // ��ȡ���۵����ݼ��뵽Ҫ���ص� ����-�۸� ��, ���Ӷ��չ�ϵ��ɾ���Ѿ�ȡ���۸������
        // for (Entry<MaterialParamVO, UFDouble> entry : transferedPriceMap.entrySet()) {
        // if (entry.getValue() != null) {
        // matrPriceMap.put(entry.getKey(), entry.getValue());
        // matSourceMap.remove(entry.getKey());
        // }
        // }
        // // ɾ��ĳ�������м۸���Դ�Ѿ�ȡ����ûȡ���۸������
        // for (MaterialParamVO delete : deleteParam) {
        // matSourceMap.remove(delete);
        // }
        // num++;
        // }
        // // ������δȡ���۸��������ӵ�����Ҫ���ص�ȡ�۽���У�����δȡ���۵����ϵļ۸�Ϊnull
        // for (MaterialParamVO matrvo : haveNonePriceMatrList) {
        // matrPriceMap.put(matrvo, null);
        // }
        // ����ȫ�����ϵ�ȡ�۽��(����ȡ���۵����Ϻ�δȡ���۵�����)��ͨ�����ⲿ�жϼ۸��Ƿ�Ϊnull����δȡ���۸������ɸѡ�����ٽ�����ʾ
        return matrPriceMap;
    }

    /**
     * ��ȡת�����ȡ�۽��
     * 
     * @param allMaterialPriceMap
     * @return
     */
    public Map<MaterialParamVO, UFDouble> getTransferedPriceParamMap(Map<CMPriceParamVO, UFDouble> allMaterialPriceMap) {
        Map<MaterialParamVO, UFDouble> transferedPriceParamMap = new HashMap<MaterialParamVO, UFDouble>();
        for (Entry<CMPriceParamVO, UFDouble> tempEntry : allMaterialPriceMap.entrySet()) {
            CMPriceParamVO paramVo = tempEntry.getKey();
            UFDouble price = tempEntry.getValue();
            MaterialParamVO transferParamVo = new MaterialParamVO();
            transferParamVo.setFinstoragetype(paramVo.getFinstoragetype());
            transferParamVo.setCcostcenterid(paramVo.getCcostcenterid());
            transferParamVo.setCmaterialid(paramVo.getCmaterialid());
            transferParamVo.setCprojectid(paramVo.getCprojectid());
            transferParamVo.setCvendorid(paramVo.getCvendorid());
            transferParamVo.setCproductorid(paramVo.getCproductorid());
            transferParamVo.setCcustomerid(paramVo.getCcustomerid());
            transferParamVo.setVfree1(paramVo.getVfree1());
            transferParamVo.setVfree2(paramVo.getVfree2());
            transferParamVo.setVfree3(paramVo.getVfree3());
            transferParamVo.setVfree4(paramVo.getVfree4());
            transferParamVo.setVfree5(paramVo.getVfree5());
            transferParamVo.setVfree6(paramVo.getVfree6());
            transferParamVo.setVfree7(paramVo.getVfree7());
            transferParamVo.setVfree8(paramVo.getVfree8());
            transferParamVo.setVfree9(paramVo.getVfree9());
            transferParamVo.setVfree10(paramVo.getVfree10());
            transferedPriceParamMap.put(transferParamVo, price);
        }
        return transferedPriceParamMap;
    }

    /**
     * ����ת��,�����չ�ϵת��Ϊȡ�۲���
     * 
     * @param materialParamVO
     * @param sourceParamVO
     * @return
     */
    public AquirePriceParamVO getPriceParams(MaterialParamVO materialParamVO, SourceParamVO sourceParamVO) {
        AquirePriceParamVO paramVo = new AquirePriceParamVO();
        paramVo.setPkGroup(sourceParamVO.getPk_group());
        paramVo.setPkOrg(sourceParamVO.getPk_org());
        paramVo.setPriceType(sourceParamVO.getPriceType());
        paramVo.setPkCostCenter(materialParamVO.getCcostcenterid());
        paramVo.setRestype(PriceResTypeEnum.STUFF);
        paramVo.setItemid(materialParamVO.getCmaterialid());
        CMPriceParamVO vo = new CMPriceParamVO();
        vo.setCmaterialid(materialParamVO.getCmaterialid());
        vo.setFinstoragetype(materialParamVO.getFinstoragetype());
        vo.setCcostcenterid(materialParamVO.getCcostcenterid());
        vo.setCcustomerid(materialParamVO.getCcustomerid());
        vo.setCproductorid(materialParamVO.getCproductorid());
        vo.setCprojectid(materialParamVO.getCprojectid());
        vo.setCvendorid(materialParamVO.getCvendorid());
        vo.setVfree1(materialParamVO.getVfree1());
        vo.setVfree2(materialParamVO.getVfree2());
        vo.setVfree3(materialParamVO.getVfree3());
        vo.setVfree4(materialParamVO.getVfree4());
        vo.setVfree5(materialParamVO.getVfree5());
        vo.setVfree6(materialParamVO.getVfree6());
        vo.setVfree7(materialParamVO.getVfree7());
        vo.setVfree8(materialParamVO.getVfree8());
        vo.setVfree9(materialParamVO.getVfree9());
        vo.setVfree10(materialParamVO.getVfree10());
        paramVo.setMateiralParam(vo);
        return paramVo;
    }

    /**
     * ͨ���۸�������ϻ�ȡ���������Ⱥ��ܷ�����,���ؼ��ϵ��±�Ϊ1�Ƿ������ȣ��±�Ϊ2���ܷ�����
     * 
     * @param priceParamList
     * @return
     */
    public List<Integer> getBatchUnitCycleTotal(List<AquirePriceParamVO> priceParamList) {
        // ÿ10000�����ϵ���һ��ȡ�۷���
        int init = 10000;
        // ��������
        int total = priceParamList.size();
        // �ܷ�������
        int cycleTotal = total / init;
        if (total % init != 0) {
            cycleTotal += 1;
            if (total < init) {
                init = priceParamList.size();
            }
        }
        List<Integer> batchUnitCycleTotalList = new ArrayList<Integer>();
        batchUnitCycleTotalList.add(1, Integer.valueOf(init));
        batchUnitCycleTotalList.add(2, Integer.valueOf(cycleTotal));
        return batchUnitCycleTotalList;
    }

}
