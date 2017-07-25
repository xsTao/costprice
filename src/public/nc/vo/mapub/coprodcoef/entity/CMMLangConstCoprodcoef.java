package nc.vo.mapub.coprodcoef.entity;

public class CMMLangConstCoprodcoef {

    /** 管控范围 */
    public static final String CONTROLAREA = "controlarea";

    /** 业务单元 */
    public static final String PK_ORG = "pk_org";

    /** 联合体 */
    public static final String CMATERIALID = "cmaterialid";

    /** 核算要素体系 */
    public static final String PK_ELEMENTSYSTEM = "pk_elementsystem";

    /** 核算要素表 */
    public static final String PK_FACTORCHART = "pk_factorchart";

    /** 产品 */
    public static final String CM_PRODUCT_CMATERIALID = "itembpks.cmaterialid";

    /** 成本中心 */
    public static final String CCOSTCENTERID = "itembpks.ccostcenterid";

    /** 核算要素 */
    public static final String CELEMENTID = "itembpks.celementid";

    /**
     * 提示对话框title
     */
    public static final String getMSG_TITLE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "0101436401-0330")/*
         * @res
         * "是否要重新获得产品信息？重新获得后当前产品信息将被清空！"
         */;
    }

    /**
     * 校验重复
     *
     * @return
     */
    public static final String getMSG_Repeat() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "0101436401-0331")/*
         * @res
         * "产品+成本中心+核算要素 只能定义一条联副方法！"
         */;
    }

    /**
     * 校验重复
     *
     * @return
     */
    public static final String getMSG_RepeatCMATERIALVID() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "0101436401-0332")/*
         * @res
         * "该工厂对应联合体的联副系数已经存在，不允许重复维护！"
         */;
    }

    /**
     * 主产品校验
     *
     * @return
     */
    public static final String getMSG_MAINMATERIAL() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "0101436401-0333")/*
         * @res
         * "对应产品类型为主产品的产品编码必须与联合体编码保持一致!"
         */;
    }

    /**
     * 主产品校验
     *
     * @return
     */
    public static final String getMSG_MAINMATERIALONE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "0101436401-0334")/*
         * @res
         * "与联合体编码一致的产品必须为主产品!"
         */;
    }

    /**
     * 主产品校验
     *
     * @return
     */
    public static final String getMSG_MATERIALTYPE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "0101436401-0335")/*
         * @res
         * "同一产品只能对应一种产品类型!"
         */;
    }

    /**
     * 子行空校验
     */
    public static final String getMSG_HAVELINES() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "0101436401-0336")/* @res "表体行不允许为空!" */;
    }

    /**
     * 相对系数不能为负数
     */
    public static final String getMSG_OPPOSITESUM() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "0101436401-0363")/* @res "相对系数不能为负数!" */;
    }

    /**
     * 联合体编码没有对应的BOM版本
     */
    public static final String getMSG_BOMSBYVERSION() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "0101436401-0364")/*
         * @res
         * "联合体编码没有对应的BOM版本!"
         */;
    }

    /**
     * Service不能为空
     */
    public static String getErrNullService() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "0101436401-0362")/*
         * @res
         * "Service不能为空！"
         */;
    }

}
