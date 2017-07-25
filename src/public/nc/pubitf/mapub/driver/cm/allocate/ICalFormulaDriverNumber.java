package nc.pubitf.mapub.driver.cm.allocate;

import nc.vo.cmpub.framework.assistant.CMMarAssInfoVO;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * <b> ���㹫ʽ������ </b>
 * <p>
 * ��ϸ��������
 * </p>
 * ��������:2010-4-20
 *
 * @author:wangtf
 */
public interface ICalFormulaDriverNumber {
    /**
     * ����ɱ�����
     *
     * @param driver
     *            �ɱ�����VO
     * @param variable
     *            ��ʽ�ı���
     * @param costType
     *            �ɱ�����
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireDriverNum(DriverVO driver, IDriverFormulaAquireData aquireData, String costType)
            throws BusinessException;

    /**
     * ���ɱ������Ƿ��ǳɱ����ķ���ϵ����ʵ�����ġ�ʵ�ʽ�ʵ����ҵ��
     *
     * @param beCoIsNull
     *            ������ɱ�����Ϊ��
     * @param formula
     *            ��ʽ
     * @return true �ɱ����ķ���ϵ����ʵ�����ġ�ʵ�ʽ�ʵ����ҵ��;false ������������
     */
    UFBoolean checkOnlyCenter(String formula, UFBoolean beCoIsNull);

    /**
     * ��ȡ����ֵ
     *
     * @param formula ��ʽ
     * @param costcenterId �ɱ�����
     * @param costobjId �ɱ�����
     * @param celementId ����Ҫ��
     * @param itemMarAssInfoVO ���ϸ�������
     * @return UFDouble ����ֵ
     * @throws BusinessException
     */
    UFDouble calculate(String formula, IDriverFormulaAquireData aquireData, String costcenterId, String costobjId,
            String celementId, CMMarAssInfoVO itemMarAssInfoVO) throws BusinessException;
}
