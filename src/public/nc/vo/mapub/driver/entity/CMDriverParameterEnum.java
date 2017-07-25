package nc.vo.mapub.driver.entity;

/**
 * 成本动因变量
 *
 * @author liyjf
 */
public enum CMDriverParameterEnum {

    /**
     * 合格品量
     */
    QUALIFIED_NUMBER("QUALIFIED_NUMBER"),

    /**
     * 在产品约当产量
     */
    ON_PRODUCT_RATE("ON_PRODUCT_RATE"),

    /**
     * 上期在产品约当产量
     */
    INPRO_APPRONUM("INPRO_APPRONUM"),

    /**
     * 联合格品量
     */
    JOINT_QUALIFIED_NUMBER("JOINT_QUALIFIED_NUMBER"),

    /**
     * 副产品产量
     */
    BY_PRODUCT_NUMBER("BY_PRODUCT_NUMBER"),

    /**
     * 废品产量
     */
    WASTEPRODUCT_NUMBER("WASTEPRODUCT_NUMBER"),

    /**
     * 废品约当产量
     */
    WASTEPRODUCT_RATE("WASTEPRODUCT_RATE"),

    /**
     * 联废品产量
     */
    JOINT_WASTEPRODUCT_NUMBER("JOINT_WASTEPRODUCT_NUMBER"),

    /**
     * 联废品约当产量
     */
    JOINT_WASTEPRODUCT_RATE("JOINT_WASTEPRODUCT_RATE"),

    /**
     * BOM联副产品产出定额
     */
    BOM_JOINTBY_OUTPUT_QUOTA("BOM_JOINTBY_OUTPUT_QUOTA"),

    /**
     * 生产BOM材料消耗定额
     */
    BOM_STUFF_CONSUME_QUOTA("BOM_STUFF_CONSUME_QUOTA"),

    /**
     * 工艺路线材料消耗定额
     */
    RT_STUFF_CONSUME_QUOTA("RT_STUFF_CONSUME_QUOTA"),

    /**
     * 生产订单材料消耗定额
     */
    MO_STUFF_CONSUME_QUOTA("MO_STUFF_CONSUME_QUOTA"),

    /**
     * 指定材料子项生产BOM消耗定额
     */
    ASSIN_STUFF_BOM_QUOTA("ASSIN_STUFF_BOM_QUOTA"),

    /**
     * 生产BOM主材消耗定额
     */
    MAIN_STUFF_CONSUME_QUOTA("MAIN_STUFF_CONSUME_QUOTA"),

    /**
     * 材料子项实际消耗数量
     */
    ACTUAL_STUFF_NUMBER("ACTUAL_STUFF_NUMBER"),

    /**
     * 指定材料子项实际消耗数量
     */
    ASSIGN_STUFF_ACTUAL_NUMBER("ASSIGN_STUFF_ACTUAL_NUMBER"),

    /**
     * 指定材料子项实际消耗金额
     */
    ASSIGN_STUFF_ACTUAL_MONEY("ASSIGN_STUFF_ACTUAL_MONEY"),

    /**
     * 计划价
     */
    PLAN_PRICE("PLAN_PRICE"),

    /**
     * 参考成本
     */
    REFERENCE_COST("REFERENCE_COST"),

    /**
     * 参考售价
     */
    REFERENCE_SALE_PRICE("REFERENCE_SALE_PRICE"),

    /**
     * 标准成本.成本类型
     */
    STANDARD_COST("STANDARD_COST"),

    /**
     * 标准成本.价格库
     */
    PRICE_LIBRARY("PRICE_LIBRARY"),

    /**
     * 实际作业量
     */
    ACTUAL_ACTIVITY_NUMBER("ACTUAL_ACTIVITY_NUMBER"),

    /**
     * 实际作业完工量
     */
    ACTUAL_ACTIVITY_FINISH_NUMBER("ACTUAL_ACTIVITY_FINISH_NUMBER"),

    /**
     * 成本BOM单位标准作业量
     */
    STANDARD_ACTIVITY_NUMBER("STANDARD_ACTIVITY_NUMBER"),

    /**
     * 生产BOM单位标准作业量
     */
    BOM_ACTIVITY_NUMBER("BOM_ACTIVITY_NUMBER"),

    /**
     * 工艺路线单位标准作业量
     */
    RT_ACTIVITY_NUMBER("RT_ACTIVITY_NUMBER"),

    /**
     * 分配系数
     */
    ALLOC_FAC("ALLOC_FAC"),

    /**
     * 产品成本要素实际金额
     */
    FACTOR("FACTOR"),

    /**
     * 利润中心成本要素实际金额
     */
    PCCM_FACTOR("PCCM_FACTOR"),

    /**
     * 其他-单位体积
     */
    UNITVOLUME("UNITVOLUME"),

    /**
     * 其他-单位重量
     */
    UNITWEIGHT("UNITWEIGHT"),

    /**
     * 其他-多少标准存储单位
     */
    STOREUNITNUM("STOREUNITNUM"),

    /**
     * 其他-入库容差(%)
     */
    INTOLERANCE("INTOLERANCE"),

    /**
     * 其他-出库容差(%)
     */
    OUTTOLERANCE("OUTTOLERANCE"),

    /**
     * 其他-出库关闭下容差(%)
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
