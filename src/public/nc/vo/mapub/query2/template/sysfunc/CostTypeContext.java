package nc.vo.mapub.query2.template.sysfunc;

/**
 * @since v6.3
 * @version 2013-7-23 下午02:00:09
 * @author xionghuic
 */
public class CostTypeContext {
    /**
     * 成本类型元数据ID
     */
    public final static String CostType = "06c07a8f-8c18-4d59-ae6c-958abe743062";

    private static volatile CostTypeContext context;

    private static Object obj = new Object();

    private static int count = 0;

    private String pk_org;

    private CostTypeContext() {
        if (CostTypeContext.count != 0) {
            throw new RuntimeException("to much CostTypeContext");
        }
        CostTypeContext.count++;
        CostTypeContext.context = this;
    }

    public static CostTypeContext getContext() {
        if (CostTypeContext.context == null) {
            synchronized (CostTypeContext.obj) {
                if (CostTypeContext.context == null) {
                    new CostTypeContext();
                }
            }
        }
        return CostTypeContext.context;
    }

    public String getPk_org() {
        return this.pk_org;
    }

    public void setPk_org(String pk_org) {
        this.pk_org = pk_org;
    }
}
