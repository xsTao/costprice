package nc.pubitf.mapub.driver.cm.allocate;

import nc.vo.cmpub.framework.assistant.CMMarAssInfoVO;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * <b> 计算公式动因量 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-4-20
 *
 * @author:wangtf
 */
public interface ICalFormulaDriverNumber {
    /**
     * 计算成本动因
     *
     * @param driver
     *            成本动因VO
     * @param variable
     *            公式的变量
     * @param costType
     *            成本类型
     * @throws BusinessException
     *             业务异常
     */
    void aquireDriverNum(DriverVO driver, IDriverFormulaAquireData aquireData, String costType)
            throws BusinessException;

    /**
     * 检查成本动因是否是成本中心分配系数、实际消耗、实际金额、实际作业量
     *
     * @param beCoIsNull
     *            待分配成本对象为空
     * @param formula
     *            公式
     * @return true 成本中心分配系数、实际消耗、实际金额、实际作业量;false 存在其他动因
     */
    UFBoolean checkOnlyCenter(String formula, UFBoolean beCoIsNull);

    /**
     * 获取动因值
     *
     * @param formula 公式
     * @param costcenterId 成本中心
     * @param costobjId 成本对象
     * @param celementId 核算要素
     * @param itemMarAssInfoVO 材料辅助属性
     * @return UFDouble 动因值
     * @throws BusinessException
     */
    UFDouble calculate(String formula, IDriverFormulaAquireData aquireData, String costcenterId, String costobjId,
            String celementId, CMMarAssInfoVO itemMarAssInfoVO) throws BusinessException;
}
