package nc.vo.mapub.costtype.entity;

/**
 * 成本类型节点下 常量
 *
 * @author 纪录 2010-04-15
 */
public class CMMLangConstCM0502 {

    /**
     * 最大失效日期
     */
    public static final String MAX_DATE = "9999-12-31 23:59:59";

    /**
     * 显示失效 按钮Tips。
     */
    public static final String GET_BTN_LEGAL() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0092")/* @res "显示失效" */;
    }

    /**
     * 显示失效 按钮Tips。
     */
    public static final String GET_ERR_LEGAL() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0093")/*
         * @res
         * "错误：[生效期间]不能晚于[失效期间]：第"
         */;
    }

    /**
     * 当前系统会计期间为空时的提示信息。
     */
    public static final String GET_ERR_ACCTIMENULL() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0094")/*
         * @res
         * "新增错误：系统会计期间异常！"
         */;
    }

    /**
     * 当前系统会计期间为空时的提示信息。
     */
    public static final String GET_ERRO_BDEFAULT() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0095")/*
         * @res
         * "保存失败！工厂内生效数据中有且仅有一个成本类型设置为默认！"
         */;
    }

    /**
     * 删除失败：当存在有效数据时，删除后需要存在一个有效且默认的成本类型
     */
    public static final String GET_ERRO_DEFAULT() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0096")/*
         * @res
         * "删除失败：当存在有效数据时，删除后需要存在一个有效且默认的成本类型！"
         */;
    }

    /**
     * 删除成功
     */
    public static final String GET_ERRO_DELETE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0297")/*
         * @res
         * "删除成功"
         */;
    }

    /**
     * 已经失效的成本类型中包含默认成本类型
     */
    public static final String GET_ERRO_INLEGALHASDEFAULT() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0098")/*
         * @res
         * "保存失败！所选择的默认成本类型已经失效，请重新选择！"
         */;
    }

    /**
     * 根据产生错误的行号，输出完整的错误信息。
     *
     * @param erroStr
     *            产生错误的行号
     * @return String 完整的错误信息
     */
    public static final String GET_ERRORLEGALSTR(String erroStr) {
        String erroStrFianl =
                CMMLangConstCM0502.GET_ERR_LEGAL() + erroStr
                + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0099")/*
                 * @res
                 * "(行)"
                 */;
        return erroStrFianl;
    }

    /**
     * 会计期间错误
     */
    public static final String GET_ACCOUNTCALENDAR_ERROR() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0100")/*
         * @res
         * "当前系统未创建会计期间，请先创建会计期间！"
         */;
    }

    /**
     * 管控范围
     */
    public static final String GET_BTN_CTRLSCOPE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0101")/* @res "管控范围" */;
    }

    /**
     * 生产BOM
     */
    public static final String GET_BTN_TOBOM() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0102")/* @res "成本BOM" */;
    }

    /**
     * 要素与物料对照表和核算要素表不匹配，请重新设置。
     */
    public static final String GET_ERROR_DIF_DOCVIEW_FACTORCHART(String error) {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0346", null, new String[] {
            error
        });/*
            * @res
            * "要素与物料对照表和核算要素表不匹配，请重新设置。"
            */

    }

    /**
     * 要素与物料对照表未找到要素表!
     */
    public static final String GET_NODOCVIEWFACTOR(String error) {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0349", null, new String[] {
            error
        });/*
            * @res
            * "要素与物料对照表未找到要素表!"
            */

    }
}
