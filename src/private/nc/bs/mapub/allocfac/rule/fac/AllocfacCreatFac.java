/**
 *
 */
package nc.bs.mapub.allocfac.rule.fac;

/**
 * @since v6.3
 * @version 2013-3-14 ÉÏÎç09:04:53
 * @author xionghuic
 */
public class AllocfacCreatFac {
    public static AllocfacCheckFac creatCenterfac() {
        return new AllocfacCenterCheckFac();
    }

    public static AllocfacCheckFac creatProductfac() {
        return new AllocfacMaterialCheckFac();
    }

    public static AllocfacCheckFac creatStufffac() {
        return new AllocfacStuffCheckFac();
    }

    public static AllocfacCheckFac creatActivity() {
        return new AllocfacActivityCheckFac();
    }

    public static AllocfacCheckFac creatCostClassfac() {
        return new AllocfacCostClassCheckFac();
    }

    public static AllocfacCheckFac creatBaseClassfac() {
        return new AllocfacBaseClassCheckFac();
    }

    public static AllocfacCheckFac creatProductVbfreefac() {
        return new AllocfacProductVbfreeCheckFac();
    }
}
