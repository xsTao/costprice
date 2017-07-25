package nc.vo.mapub.allocfac.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * ��������
 * <p>
 * �ڴ˴���Ӵ����������Ϣ
 */
public class AllocfacEnum extends MDEnum {

    public AllocfacEnum(IEnumValue enumvalue) {
        super(enumvalue);
    }

    /**
     * �ɱ�����
     */
    public static final AllocfacEnum costcenter = MDEnum.valueOf(AllocfacEnum.class, Integer.valueOf(1));

    /**
     * ��Ʒ
     */
    public static final AllocfacEnum product = MDEnum.valueOf(AllocfacEnum.class, Integer.valueOf(2));

    /**
     * �ɱ�����
     */
    public static final AllocfacEnum costclass = MDEnum.valueOf(AllocfacEnum.class, Integer.valueOf(3));

    /**
     * ��Ʒ+��������
     */
    public static final AllocfacEnum productvbfree = MDEnum.valueOf(AllocfacEnum.class, Integer.valueOf(4));

    /**
     * ��ҵ
     */
    public static final AllocfacEnum activity = MDEnum.valueOf(AllocfacEnum.class, Integer.valueOf(5));

    /**
     * ����
     */
    public static final AllocfacEnum stuff = MDEnum.valueOf(AllocfacEnum.class, Integer.valueOf(7));

    /**
     * ��������
     */
    public static final AllocfacEnum baseclass = MDEnum.valueOf(AllocfacEnum.class, Integer.valueOf(8));
}
