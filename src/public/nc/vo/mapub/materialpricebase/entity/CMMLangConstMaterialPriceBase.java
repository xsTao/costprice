package nc.vo.mapub.materialpricebase.entity;

public class CMMLangConstMaterialPriceBase {
    // 回车换行
    public final static String CRLF = "\r\n";

    /**
     * 物料[%s]对应的辅助属性%s已经启用，必须录入值，请修改。
     */
    public static final String getMustMaterAssMsg() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0000")/*
         * @res
         * "物料[%s]对应的辅助属性%s已经启用，必须录入值，请修改。"
         */;// 01014364-0133=物料[%s]对应的辅助属性%s已经启用，必须录入值，请修改。
    }

    /**
     * 查询条件中设置了多个名为{0}的查询条件!
     *
     * @param name
     * @return
     */
    public static final String getERR_QUERY_CONDITION_EXISTS(String name) {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0001", null, new String[] {
                name
        }); // 01014364-0106=查询条件中设置了多个名为{0}的查询条件!
    }

    /**
     * 业务单元
     */
    public static final String getFAC() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0002");
    }

    /**
     * 表体字段[单价]应大于零！ 抛出异常信息。
     **/
    public static final String GET_ERRO_BODYITEMNPRICEZERO() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0003");/*
         * @res
         * "表体字段[单价]应大于零！"
         */
    }

    /**
     * 表体费率不能为零 抛出异常信息。
     **/
    public static final String GET_ERRO_BODYITEMNRATEZERO() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0004");/*
         * @res
         * "表体字段费率不能为零！"
         */
    }

    /**
     * 表头字段%s不能为空
     */
    public static final String getNULL_VALIDATE_HEAD_ITEM(String message) {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0005", null, new String[] {
                message
        });
        // return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("1014364_0", "01014364-0110")/* @res
        // "表头字段%s不能为空" */;
    }

    /**
     * 表体字段%s不能为空
     */
    public static final String getNULL_VALIDATE_BODY_ITEM1(String message) {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0006", null, new String[] {
                message
        });
        // return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("1014364_0", "01014364-0115")/* @res
        // "表体字段%s不能为空" */;
    }

    /**
     * 表体材料[价格来源]为手工录入时单价不能为空，请修改！
     */
    public static final String getBodyPriceIsNotEmpty() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0007")/*
         * @res
         * "表体材料[价格来源]为手工录入时单价不能为空，请修改！"
         */;
    }

    /**
     * 表体材料[价格来源]为空，表头材料[价格来源]为手工录入时，单价不能为空，请修改！
     */
    public static final String getHeadandBodyPriceIsNotEmpty() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0008")/*
         * @res
         * "表体材料[价格来源]为空，表头材料[价格来源]为手工录入时，单价不能为空，请修改！"
         */;
    }

    /**
     * 价格库编码
     */
    public static final String getVPRICEBASE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0009")/* @res "价格库编码" */;
    }

    /**
     * 价格库名称
     */
    public static final String getVPRICENAME() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0010")/* @res "价格库名称" */;
    }

    /**
     * 币种
     */
    public static final String getCRENCYID() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0011")/* @res "币种" */;
    }

    /**
     * 生效时间
     */
    public static final String getDBEGINDATE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0012")/* @res "生效时间" */;
    }

    /**
     * 失效时间
     */
    public static final String getDENDDATE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0013")/* @res "失效时间" */;
    }

    /**
     * 材料编码
     */
    public static final String getCMATERIALID() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0014")/* @res "材料编码" */;
    }

    /**
     * 单价
     */
    public static final String getNPRICE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0015")/* @res "单价" */;
    }

    /**
     * 成本中心编码
     */
    public static final String getCCOSTCENTERID() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0016")/* @res "成本中心编码" */;
    }

    /**
     * 核算要素编码
     */
    public static final String getCELEMENTID() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0017")/* @res "核算要素编码" */;
    }

    /**
     * 费率
     */
    public static final String getNRATE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0018")/* @res "费率" */;
    }

    /**
     * 以下内容不能为空，请检查：
     */
    public static final String getNULL_VALIDATE_H() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0019")/*
         * @res
         * "以下内容不能为空，请检查："
         */;
    }

    /**
     * 失效日期不能早于失效日期
     **/
    public static final String GET_ERRO_BEGIN_ENDDATE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0020")/*
         * @res
         * "失效日期不能早于生效日期！"
         */;
    }

    /**
     * 工厂所属财务组织不能为空
     */
    public static final String GET_HIT_NOFINANCE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0021")/* @res "工厂所属财务组织不能为空" */;
    }

    /**
     * 工厂对应的财务组织主账簿不能为空
     */
    public static final String GET_HIT_NOSCHEME() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0022")/*
         * @res
         * "工厂对应的财务组织主账簿不能为空"
         */;
    }

    /**
     * 错误-没有找到工厂组织对应的财务组织的主账簿
     */
    public static final String ERR_NO_ACCOUNTBOOK = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
            "03810006-0023")/* @res "没有找到工厂组织对应的财务组织的主账簿" */;

    /**
     * 错误-没有找到工厂组织对应的财务组织的主账簿的本位币，不能获取币别精度
     */
    public static final String ERR_NO_ACCOUNTBOOK_SCALE = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "3810006_0", "03810006-0024")/* @res "没有找到工厂组织对应的财务组织的主账簿的本位币，不能获取币别精度" */;

    /**
     * 生效日期，参照显示字段。
     */
    public static final String GET_REFFIELDNAME_BEGINDATE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC000-0002942")/* @res "生效日期" */;
    }

    /**
     * 失效日期，参照显示字段。
     */
    public static final String GET_REFFIELDNAME_ENDDATE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC000-0001402")/* @res "失效日期" */;
    }

    /**
     * 价格库，参照显示标题。
     */
    public static final String GET_REFTITLE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0025")/* @res "材料价格库" */;
    }

    /**
     * 需要检查的VO不能为空
     */
    public static final String CHECK_NULL_VO() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0026")/* @res "需要检查的VO不能为空" */;
    }

    /**
     * 价格库已被成本动因引用，不能进行该操作
     */
    public static final String getUSED_IN_DRIVER() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0027")/*
         * @res
         * "价格库已被成本动因引用，不能进行该操作"
         */;
    }

    /**
     * 价格库编码为{0}的价格库已经被锁定！请检查后再锁定！
     */
    public static final String LOCKED_PRICEBASE(String message) {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0028", null, new String[] {
                message
        })/* @res "价格库" */;
    }

    /**
     * 价格库编码为{0}的价格库未锁定！请检查后解冻！
     */
    public static final String UNLOCKED_PRICEBASE(String message) {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0029", null, new String[] {
                message
        })/* @res "价格库" */;
    }

    /**
     * 无法取到物料编码为{0}的价格。
     */
    public static final String HVAENONEPRICEMATRID(String message) {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0030", null, new String[] {
                message
        })/* @res "无法取到物料编码为{0}的价格。" */;
    }

    /**
     * @return 第%s行：
     */
    public static String getMsgRowNum() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0031")/* @res "第%s行：" */;
    }

    /**
     * 出现重复，请修改！
     */
    public static final String ITEMS_REPEAT_INFO() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0032")/* @res "出现重复，请修改！" */;
        // return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("1014364_0", "01014364-0129", null, new String[]
        // {
        // message
        // })/* @res {0}出现重复，请修改！ */;
    }

    /**
     * @return 价格库编码已存在，请修改！
     */
    public static String getMSGCMATERIALPRICE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0033")/*
         * @res
         * "价格库编码已存在，请修改！"
         */;
    }

    /**
     * @return 关闭
     */
    public static String getMSG1() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0034")/*
         * @res
         * "关闭"
         */;
    }

    /**
     * @return 取价错误信息
     */
    public static String getMSG2() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0035")/*
         * @res
         * "取价错误信息"
         */;
    }

    /**
     * @return 确定
     */
    public static String getMSG3() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0036")/*
         * @res
         * " 确定"
         */;
    }

    /**
     * @return 警告
     */
    public static String getMSG4() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0037")/*
         * @res
         * " 警告"
         */;
    }

    /**
     * @return 增行
     */
    public static String getMSG5() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0038")/*
         * @res
         * " 增行"
         */;
    }

    /**
     * @return 置底
     */
    public static String getMSG6() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0039")/*
         * @res
         * " 置底"
         */;
    }

    /**
     * @return 取消
     */
    public static String getMSG7() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0040")/*
         * @res
         * " 取消"
         */;
    }

    /**
     * @return 删行
     */
    public static String getMSG8() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0041")/*
         * @res
         * " 删行"
         */;
    }

    /**
     * @return 下移
     */
    public static String getMSG9() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0042")/*
         * @res
         * " 下移"
         */;
    }

    /**
     * @return 插入行
     */
    public static String getMSG10() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0043")/*
         * @res
         * " 插入行"
         */;
    }

    /**
     * @return 置顶
     */
    public static String getMSG11() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0044")/*
         * @res
         * " 置顶"
         */;
    }

    /**
     * @return 上移
     */
    public static String getMSG12() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0045")/*
         * @res
         * " 上移"
         */;
    }

    /**
     * @return 组织
     */
    public static String getMSG13() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0046")/*
         * @res
         * " 组织"
         */;
    }

    /**
     * @return 成本域
     */
    public static String getMSG14() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0047")/*
         * @res
         * " 成本域"
         */;
    }

    /**
     * "[ 价格来源 ]为手工录入的，其他行不能录入[ 价格来源 ]为非手工录入的，请修改！"
     */
    public static final String getMSG15() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0048");
        // * @res
        // [ 价格来源 ]为手工录入的，其他行不能录入[ 价格来源 ]为非手工录入的，请修改！ */;
    }

    /**
     * "第"
     */
    public static final String getMSG16() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0049");
        // * @res
        // 第 */;
    }

    /**
     * "行："
     */
    public static final String getMSG17() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0050");
        // * @res
        // 行： */;
    }

    /**
     * "[ 价格来源 ]为非手工录入或者非标准成本，[ 业务单元 ]字段必须录入值 ，请修改！"
     */
    public static final String getMSG18() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0051");
        // * @res
        // [ 价格来源 ]为非手工录入或者非标准成本，[ 业务单元 ]字段必须录入值 ，请修改！ */;
    }

    /**
     * " [ 价格来源 ]出现重复，请修改！"
     */
    public static final String getMSG19() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0052");
        // * @res
        // [ 价格来源 ]出现重复，请修改！ */;
    }

    /**
     * "  价格来源"
     */
    public static final String getMSG20() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0053");
        // * @res
        // 价格来源 */;
    }

    /**
     * " 取价"
     */
    public static final String getMSG21() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0054");
        // * @res
        // 取价 */;
    }

    /**
     * " 请选择一个价格库进行取价！"
     */
    public static final String getMSG22() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0055");
        // * @res
        // 请选择一个价格库进行取价！ */;
    }

    /**
     * " 警告！取价太频繁"
     */
    public static final String getMSG23() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0056");
        // * @res
        // 警告！取价太频繁 */;
    }

    /**
     * " 取价中，请稍后！"
     */
    public static final String getMSG24() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0057");
        // * @res
        // 取价中，请稍后！ */;
    }

    /**
     * " 取价失败！"
     */
    public static final String getMSG25() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0058");
        // * @res
        // 取价失败！ */;
    }

    /**
     * "你操作的数据已经被他人修改，请刷新界面"
     */
    public static final String getMSG26() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0059");
        // * @res
        // 你操作的数据已经被他人修改，请刷新界面 */;
    }

    /**
     * "开始期间应小于截止期间，请修改！"
     */
    public static final String getMSG27() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0060");
        // * @res
        // 开始期间应小于截止期间，请修改！ */;
    }

    /**
     * "下列字段值不能为空："
     */
    public static final String getMSG28() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0061");
        // * @res
        // 下列字段值不能为空： */;
    }

    /**
     * "价格来源为手工录入不需要取价!"
     */
    public static final String getMSG29() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0062");
        // * @res
        // 价格来源为手工录入不需要取价! */;
    }

    /**
     * "取价参数"
     */
    public static final String getMSG30() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0063");
        // * @res
        // 取价参数*/;
    }

    /**
     * "财务组织没有设置主账簿!"
     */
    public static final String getMSG31() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0064");
        // * @res
        // 财务组织没有设置主账簿!*/;
    }

    /**
     * "利润中心对应的财务组织没有设置主账簿! "
     */
    public static final String getMSG32() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0065");
        // * @res
        // 利润中心对应的财务组织没有设置主账簿! */;
    }

    /**
     * "获取本位币失败。业务单元必须具有财务、工厂、利润中心职能，请修改！ "
     */
    public static final String getMSG33() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0066");
        // * @res
        // 获取本位币失败。业务单元必须具有财务、工厂、利润中心职能，请修改！ */;
    }

    /**
     * "[ 价格来源 ]为非手工录入或者非标准成本，[ 业务单元 ]字段必须录入值 ，请修改！"
     */
    public static final String getMSG34() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0348");
        // * @res
        // [ 价格来源 ]为手工录入或者标准成本，[ 业务单元 ]字段不能录入值 ，请修改！ */;
    }

}
