package nc.ui.mapub.allocfac.util;

import nc.vo.cmpub.framework.assistant.CMAssInfoItemVO;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;

/**
 * 分配系数界面常数类
 * <p>
 * 界面需要隐藏列的列名
 */
public class AllocfacBillConst {

    /**
     * 隐藏列的列名
     */
    public static final String[] HIDEITEMNAMES = {
        // 成本中心
        AllocfacItemVO.CCOSTCENTERID,
        // 产品
        AllocfacItemVO.CMATERIALID,
        // 成本分类
        AllocfacItemVO.CMARCOSTCLASSID,
        // 作业
        AllocfacItemVO.CACTIVITYID,
        AllocfacItemVO.CSTUFFID,
        AllocfacItemVO.CMARBASECLASSID,
        // 所有的名称
        "ccostcenterid.ccname", "cmaterialid.name", "cmarcostclassid.name", "cactivityid.vactivityname",
        "cstuffid.name", "cmarbaseclassid.name", CMAssInfoItemVO.CCUSTOMERID, CMAssInfoItemVO.CVENDORID,
        CMAssInfoItemVO.CPRODUCTORID, CMAssInfoItemVO.CPROJECTID, CMAssInfoItemVO.VBFREE1, CMAssInfoItemVO.VBFREE2,
        CMAssInfoItemVO.VBFREE3, CMAssInfoItemVO.VBFREE4, CMAssInfoItemVO.VBFREE5, CMAssInfoItemVO.VBFREE6,
        CMAssInfoItemVO.VBFREE7, CMAssInfoItemVO.VBFREE8, CMAssInfoItemVO.VBFREE9, CMAssInfoItemVO.VBFREE10
    };

    /**
     * 初始化要显示的列的列名
     */
    public static final String[] SHOWITEMNAMES_INIT = {
        AllocfacItemVO.CCOSTCENTERID, // 成本中心
        "ccostcenterid.ccname"
    };

    /**
     * 分配系数的子表tablecode
     */
    public static final String ALLOCFAC_TABLECODE = "itempks";
}
