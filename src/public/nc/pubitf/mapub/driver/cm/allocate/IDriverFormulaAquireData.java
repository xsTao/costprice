package nc.pubitf.mapub.driver.cm.allocate;

import java.util.List;
import java.util.Map;

import nc.vo.cmpub.framework.assistant.CMMarAssInfoVO;
import nc.vo.pub.BusinessException;

/**
 * <b> 公式成本动因获取变量接口 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-4-20
 *
 * @author:wangtf
 */
public interface IDriverFormulaAquireData {

    boolean isProductAllocate();

    boolean hasMaterialId();

    /**
     * Map<类型，Object> Object:
     * 1.UFDouble动因量;
     * 2.Map<成本中心，UFDouble动因量>;
     * 3.Map<成本中心，Map<成本对象，UFDouble动因量>>;
     * 4.Map<AssInfoVO，UFDouble动因量>;
     */
    Map<Object, Object> getVariableMap();

    void setVariableMap(Map<Object, Object> variableMap);

    /**
     * 获取合格品产量
     *
     * @throws BusinessException
     *             业务异常
     */
    void aquireQualifiedNumber(String type) throws BusinessException;

    /**
     * 获取在约当产量
     *
     * @throws BusinessException
     *             业务异常
     */
    void aquireOnProductRate(String type) throws BusinessException;

    /**
     * 获取联合格品量
     *
     * @throws BusinessException
     *             业务异常
     */
    void aquireJointQualifiedNumber(String type) throws BusinessException;

    /**
     * 获取副产品产量
     *
     * @throws BusinessException
     *             业务异常
     */
    void aquireByProductNumber(String type) throws BusinessException;

    /**
     * 获取废品产量
     *
     * @throws BusinessException
     *             业务异常
     */
    void aquireWasteProductNumber(String type) throws BusinessException;

    /**
     * 获取废品约当产量
     *
     * @throws BusinessException
     *             业务异常
     */
    void aquireWasteProductRate(String type) throws BusinessException;

    /**
     * 获取联废品产量
     *
     * @throws BusinessException
     *             业务异常
     */
    void aquireJointWasteProductNumber(String type) throws BusinessException;

    /**
     * 获取联废品约当产量
     *
     * @throws BusinessException
     *             业务异常
     */
    void aquireJointWasteProductRate(String type) throws BusinessException;

    /**
     * 期初在产品约当产量
     *
     * @param type
     *            动因类型
     * @throws BusinessException
     */
    void aquireInproApproNum(String type) throws BusinessException;

    /**
     * 获取Bom材料消耗定额
     *
     * @throws BusinessException
     *             业务异常
     */
    void aquireBomStuffConsumeQuota(String type) throws BusinessException;

    /**
     * 获取指定材料子项Bom消耗定额
     *
     * @throws BusinessException
     *             业务异常
     */
    void aquireAssinStuffBomQuota(String stuffId, String variable) throws BusinessException;

    /**
     * 工艺路线获取材料消耗定额
     *
     * @throws BusinessException
     *             业务异常
     */
    void aquireRTStuffConsumeQuota(String type) throws BusinessException;

    /**
     * 工艺路线单位标准作业量
     *
     * @param activityID
     *            作业ID
     * @param type
     *            动因类型
     * @throws BusinessException
     */
    void aquireRTActivityNumber(String activityID, String type) throws BusinessException;

    /**
     * 获取生产订单材料消耗定额
     *
     * @throws BusinessException
     *             业务异常
     */
    void aquireMOStuffConsumeQuota(String type) throws BusinessException;

    /**
     * 获取主料消耗定额
     *
     * @throws BusinessException
     *             业务异常
     */
    void aquireMainStuffConsumeQuota(String type) throws BusinessException;

    /**
     * 获取材料子项实际消耗数量
     *
     * @throws BusinessException
     *             业务异常
     */
    void aquireActualStuffNumber(String type) throws BusinessException;

    /**
     * 获取指定材料子项实际消耗数量
     *
     * @throws BusinessException
     *             业务异常
     */
    void aquireAssignStuffActualNumber(String materialvid, String type) throws BusinessException;

    /**
     * 获取指定材料子项实际消耗金额
     *
     * @throws BusinessException
     *             业务异常
     */
    void aquireAssignStuffActualMoney(String materialvid, String type) throws BusinessException;

    /**
     * 获取物料计划价
     *
     * @throws BusinessException
     *             业务异常
     */
    void aquirePlanPrice(String type) throws BusinessException;

    /**
     * 获取物料参考成本
     *
     * @throws BusinessException
     *             业务异常
     */
    void aquireReferenceCost(String type) throws BusinessException;

    /**
     * 获取物料参考售价
     *
     * @param saleOrgCodes
     *            销售组织编码
     * @throws BusinessException
     *             业务异常
     */
    void aquireReferenceSalePrice(String[] saleOrgIDs, String type) throws BusinessException;

    /**
     * 获取物料的标准成本
     *
     * @param costTypeCodes
     *            成本类型编码
     * @param costCenterIDs
     *            成本中心数组
     * @throws BusinessException
     *             业务异常
     */
    void aquireStandardCost(String costTypeID, String type) throws BusinessException;

    /**
     * 获取物料在价格库中定义的价格
     *
     * @param priceLibraryCodes
     *            价格库编码
     * @throws BusinessException
     *             业务异常
     */
    void aquirePriceLibrary(String[] priceLibraryIDs, String type) throws BusinessException;

    /**
     * 获取实际作业量
     *
     * @param activityCode
     *            作业编码
     * @throws BusinessException
     *             业务异常
     */
    void aquireActualActivityNumber(String activityID, String type) throws BusinessException;

    /**
     * 获取实际作业完工量
     *
     * @param activityCode
     *            作业编码
     * @throws BusinessException
     *             业务异常
     */
    void aquireActualActivityFinishNumber(String activityID, String type) throws BusinessException;

    /**
     * 获取成本BOM标准作业量
     *
     * @param activityID
     *            作业ID
     * @param costType
     *            成本类型
     * @param type
     *            动因类型
     * @throws BusinessException
     */
    void aquireStandardActivityNumber(String activityID, String costType, String type) throws BusinessException;

    /**
     * 获取生产BOM标准作业量
     *
     * @param activityID
     *            作业ID
     * @param type
     *            动因类型
     * @throws BusinessException
     */
    void aquireBOMActivityNumber(String activityID, String type) throws BusinessException;

    /**
     * 获取分配系数
     *
     * @param allocfacCode
     *            分配系数编码
     * @throws BusinessException
     *             业务异常
     */
    void aquireAllocfac(String allocfacID, String allocfacType, String type) throws BusinessException;

    /**
     * 获取核算要素的金额
     *
     * @param factorCode
     *            核算要素编码
     * @throws BusinessException
     *             业务异常
     */
    void aquireFactor(String factorID, String type) throws BusinessException;

    /**
     * 获取其他部分的数据
     *
     * @param code
     *            参数
     * @throws BusinessException
     *             业务异常
     */
    void aquireOther(String code, String type) throws BusinessException;

    /**
     * 获取BOM联副产品产出定额
     * BOM_BYJOINT_OUTPUT_QUOTA
     *
     * @throws BusinessException
     *             业务异常
     */
    void aquireBomJointByOutputQuota(String type) throws BusinessException;

    /**
     * 获取取数维度List
     *
     * @param variable 变量
     * @param costcenterId 成本中心
     * @param costobjId 成本对象
     * @param celementId 核算要素
     * @param itemMarAssInfoVO 材料辅助属性
     * @param formulaMap 数据缓存Map
     * @return
     */
    List<Object> getVariableKeyList(String variable, String costcenterId, String costobjId, String celementId,
            CMMarAssInfoVO itemMarAssInfoVO, Map<String, Object> formulaMap);

}
