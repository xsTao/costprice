package nc.pubitf.mapub.driver.cm.allocate;

import java.util.List;
import java.util.Map;

import nc.vo.cmpub.framework.assistant.CMMarAssInfoVO;
import nc.vo.pub.BusinessException;

/**
 * <b> ��ʽ�ɱ������ȡ�����ӿ� </b>
 * <p>
 * ��ϸ��������
 * </p>
 * ��������:2010-4-20
 *
 * @author:wangtf
 */
public interface IDriverFormulaAquireData {

    boolean isProductAllocate();

    boolean hasMaterialId();

    /**
     * Map<���ͣ�Object> Object:
     * 1.UFDouble������;
     * 2.Map<�ɱ����ģ�UFDouble������>;
     * 3.Map<�ɱ����ģ�Map<�ɱ�����UFDouble������>>;
     * 4.Map<AssInfoVO��UFDouble������>;
     */
    Map<Object, Object> getVariableMap();

    void setVariableMap(Map<Object, Object> variableMap);

    /**
     * ��ȡ�ϸ�Ʒ����
     *
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireQualifiedNumber(String type) throws BusinessException;

    /**
     * ��ȡ��Լ������
     *
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireOnProductRate(String type) throws BusinessException;

    /**
     * ��ȡ���ϸ�Ʒ��
     *
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireJointQualifiedNumber(String type) throws BusinessException;

    /**
     * ��ȡ����Ʒ����
     *
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireByProductNumber(String type) throws BusinessException;

    /**
     * ��ȡ��Ʒ����
     *
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireWasteProductNumber(String type) throws BusinessException;

    /**
     * ��ȡ��ƷԼ������
     *
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireWasteProductRate(String type) throws BusinessException;

    /**
     * ��ȡ����Ʒ����
     *
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireJointWasteProductNumber(String type) throws BusinessException;

    /**
     * ��ȡ����ƷԼ������
     *
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireJointWasteProductRate(String type) throws BusinessException;

    /**
     * �ڳ��ڲ�ƷԼ������
     *
     * @param type
     *            ��������
     * @throws BusinessException
     */
    void aquireInproApproNum(String type) throws BusinessException;

    /**
     * ��ȡBom�������Ķ���
     *
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireBomStuffConsumeQuota(String type) throws BusinessException;

    /**
     * ��ȡָ����������Bom���Ķ���
     *
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireAssinStuffBomQuota(String stuffId, String variable) throws BusinessException;

    /**
     * ����·�߻�ȡ�������Ķ���
     *
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireRTStuffConsumeQuota(String type) throws BusinessException;

    /**
     * ����·�ߵ�λ��׼��ҵ��
     *
     * @param activityID
     *            ��ҵID
     * @param type
     *            ��������
     * @throws BusinessException
     */
    void aquireRTActivityNumber(String activityID, String type) throws BusinessException;

    /**
     * ��ȡ���������������Ķ���
     *
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireMOStuffConsumeQuota(String type) throws BusinessException;

    /**
     * ��ȡ�������Ķ���
     *
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireMainStuffConsumeQuota(String type) throws BusinessException;

    /**
     * ��ȡ��������ʵ����������
     *
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireActualStuffNumber(String type) throws BusinessException;

    /**
     * ��ȡָ����������ʵ����������
     *
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireAssignStuffActualNumber(String materialvid, String type) throws BusinessException;

    /**
     * ��ȡָ����������ʵ�����Ľ��
     *
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireAssignStuffActualMoney(String materialvid, String type) throws BusinessException;

    /**
     * ��ȡ���ϼƻ���
     *
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquirePlanPrice(String type) throws BusinessException;

    /**
     * ��ȡ���ϲο��ɱ�
     *
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireReferenceCost(String type) throws BusinessException;

    /**
     * ��ȡ���ϲο��ۼ�
     *
     * @param saleOrgCodes
     *            ������֯����
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireReferenceSalePrice(String[] saleOrgIDs, String type) throws BusinessException;

    /**
     * ��ȡ���ϵı�׼�ɱ�
     *
     * @param costTypeCodes
     *            �ɱ����ͱ���
     * @param costCenterIDs
     *            �ɱ���������
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireStandardCost(String costTypeID, String type) throws BusinessException;

    /**
     * ��ȡ�����ڼ۸���ж���ļ۸�
     *
     * @param priceLibraryCodes
     *            �۸�����
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquirePriceLibrary(String[] priceLibraryIDs, String type) throws BusinessException;

    /**
     * ��ȡʵ����ҵ��
     *
     * @param activityCode
     *            ��ҵ����
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireActualActivityNumber(String activityID, String type) throws BusinessException;

    /**
     * ��ȡʵ����ҵ�깤��
     *
     * @param activityCode
     *            ��ҵ����
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireActualActivityFinishNumber(String activityID, String type) throws BusinessException;

    /**
     * ��ȡ�ɱ�BOM��׼��ҵ��
     *
     * @param activityID
     *            ��ҵID
     * @param costType
     *            �ɱ�����
     * @param type
     *            ��������
     * @throws BusinessException
     */
    void aquireStandardActivityNumber(String activityID, String costType, String type) throws BusinessException;

    /**
     * ��ȡ����BOM��׼��ҵ��
     *
     * @param activityID
     *            ��ҵID
     * @param type
     *            ��������
     * @throws BusinessException
     */
    void aquireBOMActivityNumber(String activityID, String type) throws BusinessException;

    /**
     * ��ȡ����ϵ��
     *
     * @param allocfacCode
     *            ����ϵ������
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireAllocfac(String allocfacID, String allocfacType, String type) throws BusinessException;

    /**
     * ��ȡ����Ҫ�صĽ��
     *
     * @param factorCode
     *            ����Ҫ�ر���
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireFactor(String factorID, String type) throws BusinessException;

    /**
     * ��ȡ�������ֵ�����
     *
     * @param code
     *            ����
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireOther(String code, String type) throws BusinessException;

    /**
     * ��ȡBOM������Ʒ��������
     * BOM_BYJOINT_OUTPUT_QUOTA
     *
     * @throws BusinessException
     *             ҵ���쳣
     */
    void aquireBomJointByOutputQuota(String type) throws BusinessException;

    /**
     * ��ȡȡ��ά��List
     *
     * @param variable ����
     * @param costcenterId �ɱ�����
     * @param costobjId �ɱ�����
     * @param celementId ����Ҫ��
     * @param itemMarAssInfoVO ���ϸ�������
     * @param formulaMap ���ݻ���Map
     * @return
     */
    List<Object> getVariableKeyList(String variable, String costcenterId, String costobjId, String celementId,
            CMMarAssInfoVO itemMarAssInfoVO, Map<String, Object> formulaMap);

}
