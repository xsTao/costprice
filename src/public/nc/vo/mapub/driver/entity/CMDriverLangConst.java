package nc.vo.mapub.driver.entity;

/**
 * <b> 常量 </b>
 * <p>
 * 成本动因，常量
 * </p>
 * 创建日期:2010-1-28
 *
 * @author:wangtf
 */
public class CMDriverLangConst {
    /**
     * 全局标识
     */
    public static final String GLFLAG = "@@@@";

    /**
     * 产量类
     */
    public static final String getOUTPUT() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0123")/* @res "产量类" */;
    }

    /**
     * 合格品量
     */
    public static final String getQUALIFIED_NUMBER() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0124")/* @res "合格品量" */;
    }

    /**
     * 在产品约当产量
     */
    public static final String getON_PRODUCT_RATE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0125")/* @res "在产品约当产量" */;
    }

    public static final String getON_PRODUCT_RATE_SHOWINFO() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0126")/*
                                                                                                   * @res
                                                                                                   * "在产品产量*在产约当系数"
                                                                                                   */;
    }

    /**
     * 联合格品量
     */
    public static final String getJOINT_QUALIFIED_NUMBER() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0127")/* @res "联合格品量" */;
    }

    /**
     * 副产品产量
     */
    public static final String getBY_PRODUCT_NUMBER() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0128")/* @res "副产品产量" */;
    }

    /**
     * 废品产量
     */
    public static final String getWASTEPRODUCT_NUMBER() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0129")/* @res "废品产量" */;
    }

    /**
     * 废品约当产量
     */
    public static final String getWASTEPRODUCT_RATE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0130")/* @res "废品约当产量" */;
    }

    public static final String getWASTEPRODUCT_RATE_SHOWINFO() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0131")/*
                                                                                                   * @res
                                                                                                   * "废品产量*废品约当系数"
                                                                                                   */;
    }

    /**
     * 联废品产量
     */
    public static final String getJOINT_WASTEPRODUCT_NUMBER() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0132")/* @res "联废品产量" */;
    }

    /**
     * 联废品约当产量
     */
    public static final String getJOINT_WASTEPRODUCT_RATE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0133")/* @res "联废品约当产量" */;
    }

    public static final String getJOINT_WASTEPRODUCT_RATE_SHOWINFO() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0134")/*
                                                                                                   * @res
                                                                                                   * "联废品产量*废品约当系数"
                                                                                                   */;
    }

    /**
     * BOM联副产品产出定额
     */
    public static final String getBOM_JOINTBY_OUTPUT_QUOTA() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0135")/* @res "BOM联副产品产出定额" */;
    }

    /**
     * 上期在产品约当产量
     */
    public static final String getINPRO_APPRONUM() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0136")/* @res "上期在产品约当产量" */;
    }

    /**
     * 消耗类
     */
    public static final String getSTUFF_NUMBER() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0137")/* @res "消耗类" */;
    }

    /**
     * 生产BOM材料消耗定额
     */
    public static final String getBOM_STUFF_CONSUME_QUOTA() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0138")/* @res "生产BOM材料消耗定额" */;
    }

    /**
     * 工艺路线材料消耗定额
     */
    public static final String getRT_STUFF_CONSUME_QUOTA() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0139")/* @res "工艺路线材料消耗定额" */;
    }

    /**
     * 生产订单材料消耗定额
     */
    public static final String getMO_STUFF_CONSUME_QUOTA() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0234")/* @res "生产订单材料消耗定额" */;
    }

    /**
     * 生产BOM主材消耗定额
     */
    public static final String getMAIN_STUFF_CONSUME_QUOTA() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0140")/*
                                                                                                   * @res
                                                                                                   * "生产BOM主材消耗定额"
                                                                                                   */;
    }

    /**
     * 材料子项实际消耗数量
     */
    public static final String getACTUAL_STUFF_NUMBER() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0141")/* @res "材料子项实际消耗数量" */;
    }

    /**
     * 指定材料子项实际消耗数量
     */
    public static final String getASSIGN_STUFF_ACTUAL_NUMBER() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0142")/*
                                                                                                   * @res
                                                                                                   * "指定材料子项实际消耗数量"
                                                                                                   */;
    }

    /**
     * 指定材料子项实际消耗金额
     */
    public static final String getASSIGN_STUFF_ACTUAL_MONEY() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0143")/*
                                                                                                   * @res
                                                                                                   * "指定材料子项实际消耗金额"
                                                                                                   */;
    }

    /**
     * 指定材料子项生产BOM消耗定额
     */
    public static final String getASSIN_STUFF_BOM_QUOTA() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0144")/*
                                                                                                   * @res
                                                                                                   * "指定材料子项生产BOM消耗定额"
                                                                                                   */;
    }

    /**
     * 价值类
     */
    public static final String getPRICE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0145")/* @res "价值类" */;
    }

    /**
     * 计划价
     */
    public static final String getPLAN_PRICE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC000-0003465")/* @res "计划价" */;
    }

    /**
     * 参考成本
     */
    public static final String getREFERENCE_COST() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC000-0000958")/* @res "参考成本" */;
    }

    /**
     * 参考售价
     */
    public static final String getREFERENCE_SALE_PRICE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC000-0000957")/* @res "参考售价" */;
    }

    /**
     * 标准成本.成本类型
     */
    public static final String getSTANDARD_COST() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0146")/* @res "标准成本.成本类型" */;
    }

    /**
     * 标准成本.价格库
     */
    public static final String getPRICE_LIBRARY() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0147")/* @res "标准成本.价格库" */;
    }

    /**
     * 作业类
     */
    public static final String getACTIVITYTITLE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0148")/* @res "作业类" */;
    }

    /**
     * 实际作业量
     */
    public static final String getACTUAL_ACTIVITY_NUMBER() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0149")/* @res "实际作业量" */;
    }

    /**
     * 实际作业完工量
     */
    public static final String getACTUAL_ACTIVITY_FINISH_NUMBER() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0364")/* @res "实际作业完工量" */;
    }

    /**
     * 成本BOM单位标准作业量
     */
    public static final String getSTANDARD_ACTIVITY_NUMBER() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0150")/*
                                                                                                   * @res
                                                                                                   * ".成本BOM单位标准作业量"
                                                                                                   */;
    }

    /**
     * 生产BOM单位标准作业量
     */
    public static final String getBOM_ACTIVITY_NUMBER() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0151")/*
                                                                                                   * @res
                                                                                                   * ".生产BOM单位标准作业量"
                                                                                                   */;
    }

    /**
     * 工艺路线单位标准作业量
     */
    public static final String getRT_ACTIVITY_NUMBER() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0152")/*
                                                                                                   * @res
                                                                                                   * ".工艺路线单位标准作业量"
                                                                                                   */;
    }

    /**
     * 分配系数
     */
    public static final String getALLOCFAC() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0153")/* @res "分配系数" */;
    }

    public static final String getFACTORTITLE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0154")/* @res "要素金额类" */;
    }

    /**
     * 其他
     */
    public static final String getOTHER() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0155")/* @res "其他" */;
    }

    public static final String OTHER = "OTHER";

    public static final String getUNITVOLUME() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC000-0000751")/* @res "单位体积" */;
    }

    public static final String getUNITWEIGHT() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC000-0000788")/* @res "单位重量" */;
    }

    public static final String getSTOREUNITNUMBER() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC000-0001389")/* @res "多少标准存储单位" */;
    }

    public static final String getINTOLERANCE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0156")/* @res "入库容差(%)" */;
    }

    public static final String getOUTTOLERANCE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0157")/* @res "出库容差(%)" */;
    }

    public static final String getOUTCLOSELOWERLIMIT() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0158")/*
                                                                                                   * @res
                                                                                                   * "出库关闭下容差(%)"
                                                                                                   */;
    }

    public static final String getDRIVER() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0159")/* @res "成本动因编码" */;
    }

    public static final String getCODE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC000-0003279");/* @res "编码" */
    }

    public static final String getEXIST() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0160")/* @res "已经存在" */;
    }

    public static final String getNOTSELECT() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0161")/* @res "可选" */;
    }

    public static final String getSELECTED() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0162")/* @res "已选" */;
    }

    public static final String getCONFIRM() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC001-0000044")/* @res "确定" */;
    }

    public static final String getCANCEL() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC001-0000008")/* @res "取消" */;
    }

    public static final String getSALEORG() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC000-0004128")/* @res "销售组织" */;
    }

    public static final String getSTANDARDCOST() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0163")/* @res "价格库" */;
    }

    public static final String getSOURCE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC000-0002745")/* @res "来源" */;
    }

    public static final String getFORMULA_VALIDATE_FAIL_GRAMMER() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0164")/*
                                                                                                   * @res
                                                                                                   * "公式验证失败.语法错误."
                                                                                                   */;
    }

    public static final String getFORMULA_VALIDATE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0165")/* @res "公式验证" */;
    }

    public static final String getFORMULA_VALIDATE_FAIL_BASE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0166")/*
                                                                                                   * @res
                                                                                                   * "公式验证失败.基准对象不兼容"
                                                                                                   */;
    }

    public static final String getFORMULA_VALIDATE_FAIL_JOIN() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0167")/*
                                                                                                   * @res
                                                                                                   * "公式变量不能直接连接"
                                                                                                   */;
    }

    public static final String getFORMULA_VALIDATE_FAIL_JOINNUM() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0168")/*
                                                                                                   * @res
                                                                                                   * "公式变量不能直接和数字或其他变量连接"
                                                                                                   */;
    }

    public static final String getFORMULA_VALIDATE_FAIL_LEFT1() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0169")/*
                                                                                                   * @res
                                                                                                   * "'(' 不能与数字、变量或 ')' 直接相连"
                                                                                                   */;
    }

    public static final String getFORMULA_VALIDATE_FAIL_LEFT2() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0170")/*
                                                                                                   * @res
                                                                                                   * "'(' 不能作为公式结尾"
                                                                                                   */;
    }

    public static final String getFORMULA_VALIDATE_FAIL_LEFT3() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0171")/*
                                                                                                   * @res
                                                                                                   * "符号或 ')' 不能与 '('直接相连"
                                                                                                   */;
    }

    public static final String getERRO_FORMULAORJAVA() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0172")/*
                                                                                                   * @res
                                                                                                   * "公式与自定义JAVA类只能录入其一"
                                                                                                   */;
    }

    public static final String getFORMULA_VALIDATE_FAIL_RIGHT1() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0173")/*
                                                                                                   * @res
                                                                                                   * "')' 不能作为公式开始"
                                                                                                   */;
    }

    public static final String getFORMULA_VALIDATE_FAIL_RIGHT2() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0174")/*
                                                                                                   * @res
                                                                                                   * "符号或 '(' 不能与 ')' 直接相连"
                                                                                                   */;
    }

    public static final String getFORMULA_VALIDATE_FAIL_RIGHT3() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0175")/*
                                                                                                   * @res
                                                                                                   * "')' 不能与数字或变量直接相连"
                                                                                                   */;
    }

    public static final String getFORMULA_VALIDATE_VARABAL_NULL() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0176")/*
                                                                                                   * @res
                                                                                                   * "公式验证失败.公式中至少包含一个公式变量"
                                                                                                   */;
    }

    public static final String getFORMULA_VALIDATE_SUCCESS() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0177")/* @res "公式验证通过." */;
    }

    public static final String getFORMULA_VALIDATE_FAIL_ZERO() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0178")/*
                                                                                                   * @res
                                                                                                   * "公式验证失败.除数不能为零"
                                                                                                   */;
    }

    public static final String getFORMULA_VALIDATE_FAIL_NUM() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0179")/*
                                                                                                   * @res
                                                                                                   * "公式验证失败.数值格式错误"
                                                                                                   */;
    }

    public static final String getFORMULA_VALIDATE_FAIL_MATCH() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0180")/*
                                                                                                   * @res
                                                                                                   * "公式验证失败.括号必须完整配对"
                                                                                                   */;
    }

    public static final String getHINT() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0181")/* @res "提示" */;
    }

    public static final String getSYSTEMCODE_NOT_DELETE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0182")/*
                                                                                                   * @res
                                                                                                   * "系统预置成本动因，不允许删除"
                                                                                                   */;
    }

    public static final String getSYSTEMCODE_NOT_EDIT() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0183")/*
                                                                                                   * @res
                                                                                                   * "系统预置成本动因，不允许修改"
                                                                                                   */;
    }

    /**
     * 公式中使用，用来分隔变量信息
     */
    public static final String FORMULA_SPLIT_START = "[";

    public static final String FORMULA_SPLIT_END = "]";

    /**
     * 参照的显示——编码
     */
    public static final String getREFMODELCODE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC000-0003279")/* @res "编码" */;
    }

    /**
     * 参照的显示——名称
     */
    public static final String getREFMODELNAME() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC000-0001155")/* @res "名称" */;
    }

    /**
     * 参照的显示——标题
     */
    public static final String getREFMODELTITLE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0184")/* @res "成本动因" */;
    }

    /**
     * 参照的显示-按钮-清除
     */
    public static final String getREF_BTN_CLEAR() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0185")/* @res "清除" */;
    }

    /**
     * 参照的显示-按钮-清除
     */
    public static final String getREF_BTN_DELETE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC001-0000039")/* @res "删除" */;
    }

    /**
     * 成本动因编码已经存在
     */
    public static final String getERRO_DRIVERCODE_EXIT() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0186")/*
                                                                                                   * @res
                                                                                                   * "成本动因编码已经存在"
                                                                                                   */;
    }

    /**
     * 成本动因正在被其他人处理，请刷新界面
     */
    public static final String getERRO_DRIVERCODE_DEALING() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0187")/*
                                                                                                   * @res
                                                                                                   * "成本动因正在被其他人处理，请刷新界面"
                                                                                                   */;
    }

    /**
     * 提示-成本动因已被引用，不能删除
     */
    public static final String getHINT_DRIVER_REFERENCED() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0188")/*
                                                                                                   * @res
                                                                                                   * "第%s行成本动因已被引用，不能删除。"
                                                                                                   */;
    }

    /**
     * 提示-系统预置成本动因，不能修改或删除
     */
    public static final String getHINT_DRIVER_SYSTEMINIT() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0189")/*
                                                                                                   * @res
                                                                                                   * "系统预置成本动因，不能修改或删除。"
                                                                                                   */;
    }

    /**
     * 提示-请先选择主组织
     */
    public static final String getHINT_ORG_IS_NULL() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0190")/* @res "请先选择主组织" */;
    }

    /**
     * 错误-解析公式不能为空
     */
    public static final String getERR_NO_FOMULA() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0191")/* @res "解析公式不能为空" */;
    }

    /**
     * 错误-取数类不能为空
     */
    public static final String getERR_NO_FETCHDATA_CLASS() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0192")/* @res "取数类不能为空" */;
    }

    public static final String getERR_FORMULA() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0193")/* @res "公式编辑器出错！" */;
    }

    /**
     * 错误-动因公式中存在为空的变量值！
     */
    public static final String getERR_EXIST_NULL_VARIABLE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0194")/*
                                                                                                   * @res
                                                                                                   * "动因公式中存在为空的变量值！"
                                                                                                   */;
    }

    /**
     * 错误-动因公式中存在分母值为0！
     */
    public static final String getERR_EXIST_ZERO_DENOMINATOR() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0195")/*
                                                                                                   * @res
                                                                                                   * "动因公式中存在分母值为0！"
                                                                                                   */;
    }

    /**
     * 错误-成本动因涉及标准成本或标准作业量，成本动因不能为空！
     */
    public static final String getERR_NO_COSTTYPE_STDCOST() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0196")/*
                                                                                                   * @res
                                                                                                   * "成本动因涉及标准成本或标准作业量，成本类型不能为空！"
                                                                                                   */;
    }

    /**
     * 错误-工厂下存在相同编码，不能保存！
     */
    public static final String getERR_SAME_CODE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0197")/*
                                                                                                   * @res
                                                                                                   * "工厂下存在相同编码，不能保存！"
                                                                                                   */;
    }

    /**
     * 合格品量
     */
    public static final String getPro() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0198")/*
                                                                                                   * @res
                                                                                                   * "合格品量"
                                                                                                   */;
    }

    /**
     * 废品产量
     */
    public static final String getWastePro() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0199")/*
                                                                                                   * @res
                                                                                                   * "废品产量"
                                                                                                   */;
    }

    /**
     * 废品约当系数
     */
    public static final String getWasteEquiv() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0200")/*
                                                                                                   * @res
                                                                                                   * "废品约当系数"
                                                                                                   */;
    }

    /**
     * 完工产量
     */
    public static final String getFPro() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0201")/*
                                                                                                   * @res
                                                                                                   * "完工产量"
                                                                                                   */;
    }

    /**
     * 联合格品量
     */
    public static final String getPPro() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0202")/*
                                                                                                   * @res
                                                                                                   * "联合格品量"
                                                                                                   */;
    }

    /**
     * 联废品产量
     */
    public static final String getPWPro() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0203")/*
                                                                                                   * @res
                                                                                                   * "联废品产量"
                                                                                                   */;
    }

    /**
     * 主联完工产量
     */
    public static final String getPFPro() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0204")/*
                                                                                                   * @res
                                                                                                   * "主联完工产量"
                                                                                                   */;
    }

    /**
     * 在产品产量
     */
    public static final String getOnPro() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0205")/*
                                                                                                   * @res
                                                                                                   * "在产品产量"
                                                                                                   */;
    }

    /**
     * 在产约当系数
     */
    public static final String getOnEquiv() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0206")/*
                                                                                                   * @res
                                                                                                   * "在产约当系数"
                                                                                                   */;
    }

    /**
     * 副产品产量
     */
    public static final String getFFPro() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0207")/*
                                                                                                   * @res
                                                                                                   * "副产品产量"
                                                                                                   */;
    }

    /**
     * 总产量
     */
    public static final String getAllPro() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0208")/*
                                                                                                   * @res
                                                                                                   * "总产量"
                                                                                                   */;
    }

    /**
     * 约当在产量
     */
    public static final String getEquivPro() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0209")/*
                                                                                                   * @res
                                                                                                   * "约当在产量"
                                                                                                   */;
    }

    /**
     * BOM材料消耗定额
     */
    public static final String getBOMCost() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0230")/*
                                                                                                   * @res
                                                                                                   * "BOM材料消耗定额"
                                                                                                   */;
    }

    /**
     * 主联BOM材料消耗定额
     */
    public static final String getPFBOMCost() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0231")/*
                                                                                                   * @res
                                                                                                   * "主联BOM材料消耗定额"
                                                                                                   */;
    }

    /**
     * 材料消耗定额
     */
    public static final String getStuffCost() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0232")/*
                                                                                                   * @res
                                                                                                   * "材料消耗定额"
                                                                                                   */;
    }

    /**
     * 工艺路线材料消耗定额
     */
    public static final String getGStuffCost() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0233")/*
                                                                                                   * @res
                                                                                                   * "工艺路线材料消耗定额"
                                                                                                   */;
    }

    /**
     * 生产订单材料消耗定额
     */
    public static final String getDStuffCost() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0234")/*
                                                                                                   * @res
                                                                                                   * "生产订单材料消耗定额"
                                                                                                   */;
    }

    /**
     * 联产品产量
     */
    public static final String getLPro() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0235")/*
                                                                                                   * @res
                                                                                                   * "联产品产量"
                                                                                                   */;
    }

    /**
     * 生产BOM主材消耗定额
     */
    public static final String getBOMStuffCost() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0236")/*
                                                                                                   * @res
                                                                                                   * "生产BOM主材消耗定额"
                                                                                                   */;
    }

    /**
     * 材料子项实际消耗数量
     */
    public static final String getStuffRCost() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0237")/*
                                                                                                   * @res
                                                                                                   * "材料子项实际消耗数量"
                                                                                                   */;
    }

    /**
     * 主计划价
     */
    public static final String getMCost() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0238")/*
                                                                                                   * @res
                                                                                                   * "主计划价"
                                                                                                   */;
    }

    /**
     * 主联计划价
     */
    public static final String getMFCost() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0239")/*
                                                                                                   * @res
                                                                                                   * "主联计划价"
                                                                                                   */;
    }

    /**
     * 计划价
     */
    public static final String getCost() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0240")/*
                                                                                                   * @res
                                                                                                   * "计划价"
                                                                                                   */;
    }

    /**
     * 主参考成本
     */
    public static final String getMMCost() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0241")/*
                                                                                                   * @res
                                                                                                   * "主参考成本"
                                                                                                   */;
    }

    /**
     * 主联参考成本
     */
    public static final String getMLCost() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0242")/*
                                                                                                   * @res
                                                                                                   * "主联参考成本"
                                                                                                   */;
    }

    /**
     * 参考成本
     */
    public static final String getCCost() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0243")/*
                                                                                                   * @res
                                                                                                   * "参考成本"
                                                                                                   */;
    }

    /**
     * 废品约当量
     */
    public static final String getFEquiv() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0244")/*
                                                                                                   * @res
                                                                                                   * "废品约当量"
                                                                                                   */;
    }

    /**
     * 分配事务定义中已引用该成本动因，不能删除
     */
    public static final String getUSED_IN_ALLOCDEF() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0245")/*
                                                                                                   * @res
                                                                                                   * "分配事务定义中已引用该成本动因，不能删除"
                                                                                                   */;
    }

    /**
     * 材料子项
     */
    public static final String getMATERIAL_ITEM() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0246")/*
                                                                                                   * @res
                                                                                                   * " 材料子项"
                                                                                                   */;
    }

    /**
     * 期初在产品产量
     */
    public static final String getINPRONUM() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0247")/*
                                                                                                   * @res
                                                                                                   * " 期初在产品产量"
                                                                                                   */;
    }

    /**
     * 上期在产约当系数
     */
    public static final String getPRE_INPRO_EQUIVRATE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0248")/*
                                                                                                   * @res
                                                                                                   * " 上期在产约当系数"
                                                                                                   */;
    }

    /**
     * 产品成本要素实际金额
     */
    public static final String getCM_FACTOR() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0249")/* @res "产品成本要素实际金额" */;
    }

    /**
     * 利润中心成本要素实际金额
     */
    public static final String getPCCM_FACTOR() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0250")/*
                                                                                                   * @res
                                                                                                   * "利润中心成本要素实际金额"
                                                                                                   */;
    }

    /**
     * 财务核算账簿
     *
     * @return
     */
    public static final String getACCOUNTINGBOOK_NAME() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0251")/* @res "财务核算账簿" */;
    }

    /**
     * 责任核算账簿
     *
     * @return
     */
    public static final String getLIABILITYBOOK_NAME() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0252")/* @res "责任核算账簿" */;
    }

    /**
     * 核算要素
     *
     * @return
     */
    public static final String getFACTORNAME() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0253")/* @res "核算要素" */;
    }

    /**
     * 作业
     *
     * @return
     */
    public static final String getACTIVITY_ITEM() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0072")/* @res "作业" */;
    }

    /**
     * 成本中心
     *
     * @return
     */
    public static final String getCENTER_ALLOCFAC() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0354")/* @res "成本中心" */;
    }

    /**
     * 产品
     *
     * @return
     */
    public static final String getPRODUCT_ALLOCFAC() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0355")/* @res "产品" */;
    }

    /**
     * 产品+辅助属性
     *
     * @return
     */
    public static final String getPRODUCTFREE_ALLOCFAC() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0356")/* @res "产品+辅助属性" */;
    }

    /**
     * 产品成本分类
     *
     * @return
     */
    public static final String getCOSTCLASS_ALLOCFAC() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0357")/* @res "产品成本分类" */;
    }

    /**
     * 产品基本分类
     *
     * @return
     */
    public static final String getBASECLASS_ALLOCFAC() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0361")/* @res "产品基本分类" */;
    }

    /**
     * 材料
     *
     * @return
     */
    public static final String getSTUFF_ALLOCFAC() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0358")/* @res "材料" */;
    }

    /**
     * 未安装多语，表pub_multilang空，请先安装多语。
     */
    public static final String getERR_LANGUAGE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0350")/*
                                                                                                   * @res
                                                                                                   * "未安装多语，表pub_multilang空，请先安装多语。"
                                                                                                   */;
    }

    /**
     * 不支持此种动因：
     */
    public static final String getERR_NO_SUPORT_DRIVER() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0353")/*
                                                                                                   * @res
                                                                                                   * "不支持此种动因："
                                                                                                   */;
    }

    /**
     * 成本对象
     */
    public static final String getCOSTOBJECT_ALLOCFAC() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "0101436401-0325")/*
         * @res
         * "成本对象"
         */;
    }
}
