package nc.vo.mapub.driver.entity;

/**
 * �ɱ��������
 *
 * @author liyjf
 */
public enum CMDriverParameterEnum {

    /**
     * �ϸ�Ʒ��
     */
    QUALIFIED_NUMBER("QUALIFIED_NUMBER"),

    /**
     * �ڲ�ƷԼ������
     */
    ON_PRODUCT_RATE("ON_PRODUCT_RATE"),

    /**
     * �����ڲ�ƷԼ������
     */
    INPRO_APPRONUM("INPRO_APPRONUM"),

    /**
     * ���ϸ�Ʒ��
     */
    JOINT_QUALIFIED_NUMBER("JOINT_QUALIFIED_NUMBER"),

    /**
     * ����Ʒ����
     */
    BY_PRODUCT_NUMBER("BY_PRODUCT_NUMBER"),

    /**
     * ��Ʒ����
     */
    WASTEPRODUCT_NUMBER("WASTEPRODUCT_NUMBER"),

    /**
     * ��ƷԼ������
     */
    WASTEPRODUCT_RATE("WASTEPRODUCT_RATE"),

    /**
     * ����Ʒ����
     */
    JOINT_WASTEPRODUCT_NUMBER("JOINT_WASTEPRODUCT_NUMBER"),

    /**
     * ����ƷԼ������
     */
    JOINT_WASTEPRODUCT_RATE("JOINT_WASTEPRODUCT_RATE"),

    /**
     * BOM������Ʒ��������
     */
    BOM_JOINTBY_OUTPUT_QUOTA("BOM_JOINTBY_OUTPUT_QUOTA"),

    /**
     * ����BOM�������Ķ���
     */
    BOM_STUFF_CONSUME_QUOTA("BOM_STUFF_CONSUME_QUOTA"),

    /**
     * ����·�߲������Ķ���
     */
    RT_STUFF_CONSUME_QUOTA("RT_STUFF_CONSUME_QUOTA"),

    /**
     * ���������������Ķ���
     */
    MO_STUFF_CONSUME_QUOTA("MO_STUFF_CONSUME_QUOTA"),

    /**
     * ָ��������������BOM���Ķ���
     */
    ASSIN_STUFF_BOM_QUOTA("ASSIN_STUFF_BOM_QUOTA"),

    /**
     * ����BOM�������Ķ���
     */
    MAIN_STUFF_CONSUME_QUOTA("MAIN_STUFF_CONSUME_QUOTA"),

    /**
     * ��������ʵ����������
     */
    ACTUAL_STUFF_NUMBER("ACTUAL_STUFF_NUMBER"),

    /**
     * ָ����������ʵ����������
     */
    ASSIGN_STUFF_ACTUAL_NUMBER("ASSIGN_STUFF_ACTUAL_NUMBER"),

    /**
     * ָ����������ʵ�����Ľ��
     */
    ASSIGN_STUFF_ACTUAL_MONEY("ASSIGN_STUFF_ACTUAL_MONEY"),

    /**
     * �ƻ���
     */
    PLAN_PRICE("PLAN_PRICE"),

    /**
     * �ο��ɱ�
     */
    REFERENCE_COST("REFERENCE_COST"),

    /**
     * �ο��ۼ�
     */
    REFERENCE_SALE_PRICE("REFERENCE_SALE_PRICE"),

    /**
     * ��׼�ɱ�.�ɱ�����
     */
    STANDARD_COST("STANDARD_COST"),

    /**
     * ��׼�ɱ�.�۸��
     */
    PRICE_LIBRARY("PRICE_LIBRARY"),

    /**
     * ʵ����ҵ��
     */
    ACTUAL_ACTIVITY_NUMBER("ACTUAL_ACTIVITY_NUMBER"),

    /**
     * ʵ����ҵ�깤��
     */
    ACTUAL_ACTIVITY_FINISH_NUMBER("ACTUAL_ACTIVITY_FINISH_NUMBER"),

    /**
     * �ɱ�BOM��λ��׼��ҵ��
     */
    STANDARD_ACTIVITY_NUMBER("STANDARD_ACTIVITY_NUMBER"),

    /**
     * ����BOM��λ��׼��ҵ��
     */
    BOM_ACTIVITY_NUMBER("BOM_ACTIVITY_NUMBER"),

    /**
     * ����·�ߵ�λ��׼��ҵ��
     */
    RT_ACTIVITY_NUMBER("RT_ACTIVITY_NUMBER"),

    /**
     * ����ϵ��
     */
    ALLOC_FAC("ALLOC_FAC"),

    /**
     * ��Ʒ�ɱ�Ҫ��ʵ�ʽ��
     */
    FACTOR("FACTOR"),

    /**
     * �������ĳɱ�Ҫ��ʵ�ʽ��
     */
    PCCM_FACTOR("PCCM_FACTOR"),

    /**
     * ����-��λ���
     */
    UNITVOLUME("UNITVOLUME"),

    /**
     * ����-��λ����
     */
    UNITWEIGHT("UNITWEIGHT"),

    /**
     * ����-���ٱ�׼�洢��λ
     */
    STOREUNITNUM("STOREUNITNUM"),

    /**
     * ����-����ݲ�(%)
     */
    INTOLERANCE("INTOLERANCE"),

    /**
     * ����-�����ݲ�(%)
     */
    OUTTOLERANCE("OUTTOLERANCE"),

    /**
     * ����-����ر����ݲ�(%)
     */
    OUTCLOSELOWERLIMIT("OUTCLOSELOWERLIMIT");

    private String code;

    private CMDriverParameterEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
