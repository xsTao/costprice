package nc.vo.mapub.allocfac.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * 分配内容
 * <p>
 * 在此处添加此类的描述信息
 */
public class AllocfacEnum extends MDEnum {

    public AllocfacEnum(IEnumValue enumvalue) {
        super(enumvalue);
    }

    /**
     * 成本中心
     */
    public static final AllocfacEnum costcenter = MDEnum.valueOf(AllocfacEnum.class, Integer.valueOf(1));

    /**
     * 产品
     */
    public static final AllocfacEnum product = MDEnum.valueOf(AllocfacEnum.class, Integer.valueOf(2));

    /**
     * 成本分类
     */
    public static final AllocfacEnum costclass = MDEnum.valueOf(AllocfacEnum.class, Integer.valueOf(3));

    /**
     * 产品+辅助属性
     */
    public static final AllocfacEnum productvbfree = MDEnum.valueOf(AllocfacEnum.class, Integer.valueOf(4));

    /**
     * 作业
     */
    public static final AllocfacEnum activity = MDEnum.valueOf(AllocfacEnum.class, Integer.valueOf(5));

    /**
     * 材料
     */
    public static final AllocfacEnum stuff = MDEnum.valueOf(AllocfacEnum.class, Integer.valueOf(7));

    /**
     * 基本分类
     */
    public static final AllocfacEnum baseclass = MDEnum.valueOf(AllocfacEnum.class, Integer.valueOf(8));
}
