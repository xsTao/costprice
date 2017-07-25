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
 * 取价工具类
 * 
 * @since 6.0
 * @version 2014-10-15 下午8:24:41
 * @author baoxina
 */
public class MaterialPriceBasePriceGetUtil {

    /**
     * 取价方法
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
        // 要返回的 物料-价格 对象(已取到价的)
        Map<MaterialParamVO, UFDouble> matrPriceMap = new HashMap<MaterialParamVO, UFDouble>();
        // modify by zhangchdV636 有问题，先去掉

        // // 最终未取到价的物料集合(没有价格的物料)
        // List<MaterialParamVO> haveNonePriceMatrList = new ArrayList<MaterialParamVO>();
        // // 第一次取价
        // int num = 1;
        // PriceFacade facade = new PriceFacade();
        // while (matSourceMap.size() > 0) {
        // // 第N次循环需要取价数据集合
        // List<PriceParamVO> priceParamList = new ArrayList<PriceParamVO>();
        // // 待删除集合
        // List<MaterialParamVO> deleteParam = new ArrayList<MaterialParamVO>();
        //
        // // 参数组装成取价参数
        // for (Entry<MaterialParamVO, List<SourceParamVO>> entry : matSourceMap.entrySet()) {
        // MaterialParamVO materialParamVO = entry.getKey();
        // List<SourceParamVO> sourceParamList = entry.getValue();
        // if (num > sourceParamList.size()) {
        // haveNonePriceMatrList.add(materialParamVO);
        // deleteParam.add(materialParamVO);
        // continue;
        // }
        // // 第N次取价时,将每一个物料和它的第N个来源转换成取价参数PriceParamVO后添加到第N次需要取价的集合当中
        // priceParamList.add(this.getPriceParams(materialParamVO, sourceParamList.get(num)));
        // }
        //
        // // 定义一个当前对照关系中所有物料的取价结果map
        // Map<CMPriceParamVO, UFDouble> allMaterialPriceMap = new HashMap<CMPriceParamVO, UFDouble>();
        //
        // /**
        // * 将priceParamList(第N次循环需要取价数据集合)中的数据进行分批,分批粒度：10000(每10000个物料调用一次取价方法)
        // */
        // int bachUnit = this.getBatchUnitCycleTotal(priceParamList).get(1);
        // int cycleTotal = this.getBatchUnitCycleTotal(priceParamList).get(2);
        // // 每次分批获取到的物料
        // List<PriceParamVO> batchPriceParamList = new ArrayList<PriceParamVO>();
        // for (int i = 0; i < cycleTotal; i++) {
        // for (int j = 0; j < bachUnit; j++) {
        // batchPriceParamList.add(priceParamList.get(j));
        // }
        // // 执行取价方法取每次分批的物料,获取批量价格
        // Map<CMPriceParamVO, UFDouble> batchPriceMap =
        // facade.doPriceAcquire(batchPriceParamList.toArray(new PriceParamVO[batchPriceParamList.size()]));
        // // 将批量价格加入到所有物料的取价结果map中
        // allMaterialPriceMap.putAll(batchPriceMap);
        // // 从所有物料集合中移除已经批量取出数据
        // priceParamList.removeAll(batchPriceParamList);
        // // 移除当前批量物料集合中的数据
        // batchPriceParamList.clear();
        // }
        // // 通过参数转换，得到转换参数后的取价结果
        // Map<MaterialParamVO, UFDouble> transferedPriceMap = this.getTransferedPriceParamMap(allMaterialPriceMap);
        //
        // // 将取到价的数据加入到要返回的 物料-价格 中, 并从对照关系中删除已经取到价格的数据
        // for (Entry<MaterialParamVO, UFDouble> entry : transferedPriceMap.entrySet()) {
        // if (entry.getValue() != null) {
        // matrPriceMap.put(entry.getKey(), entry.getValue());
        // matSourceMap.remove(entry.getKey());
        // }
        // }
        // // 删除某物料所有价格来源已经取完仍没取到价格的数据
        // for (MaterialParamVO delete : deleteParam) {
        // matSourceMap.remove(delete);
        // }
        // num++;
        // }
        // // 将最终未取到价格的物料添加到最终要返回的取价结果中，设置未取到价的物料的价格为null
        // for (MaterialParamVO matrvo : haveNonePriceMatrList) {
        // matrPriceMap.put(matrvo, null);
        // }
        // 返回全部物料的取价结果(包括取到价的物料和未取到价的物料)，通过在外部判断价格是否为null，将未取到价格的物料筛选出来再进行提示
        return matrPriceMap;
    }

    /**
     * 获取转换后的取价结果
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
     * 参数转换,将对照关系转换为取价参数
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
     * 通过价格参数集合获取分批的粒度和总分批数,返回集合的下标为1是分批粒度，下标为2是总分批数
     * 
     * @param priceParamList
     * @return
     */
    public List<Integer> getBatchUnitCycleTotal(List<AquirePriceParamVO> priceParamList) {
        // 每10000个物料调用一次取价方法
        int init = 10000;
        // 总物料数
        int total = priceParamList.size();
        // 总分批次数
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
