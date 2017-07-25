package nc.ui.mapub.allocfac.util;

import nc.vo.cmpub.framework.assistant.CMAssInfoItemVO;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;

/**
 * ����ϵ�����泣����
 * <p>
 * ������Ҫ�����е�����
 */
public class AllocfacBillConst {

    /**
     * �����е�����
     */
    public static final String[] HIDEITEMNAMES = {
        // �ɱ�����
        AllocfacItemVO.CCOSTCENTERID,
        // ��Ʒ
        AllocfacItemVO.CMATERIALID,
        // �ɱ�����
        AllocfacItemVO.CMARCOSTCLASSID,
        // ��ҵ
        AllocfacItemVO.CACTIVITYID,
        AllocfacItemVO.CSTUFFID,
        AllocfacItemVO.CMARBASECLASSID,
        // ���е�����
        "ccostcenterid.ccname", "cmaterialid.name", "cmarcostclassid.name", "cactivityid.vactivityname",
        "cstuffid.name", "cmarbaseclassid.name", CMAssInfoItemVO.CCUSTOMERID, CMAssInfoItemVO.CVENDORID,
        CMAssInfoItemVO.CPRODUCTORID, CMAssInfoItemVO.CPROJECTID, CMAssInfoItemVO.VBFREE1, CMAssInfoItemVO.VBFREE2,
        CMAssInfoItemVO.VBFREE3, CMAssInfoItemVO.VBFREE4, CMAssInfoItemVO.VBFREE5, CMAssInfoItemVO.VBFREE6,
        CMAssInfoItemVO.VBFREE7, CMAssInfoItemVO.VBFREE8, CMAssInfoItemVO.VBFREE9, CMAssInfoItemVO.VBFREE10
    };

    /**
     * ��ʼ��Ҫ��ʾ���е�����
     */
    public static final String[] SHOWITEMNAMES_INIT = {
        AllocfacItemVO.CCOSTCENTERID, // �ɱ�����
        "ccostcenterid.ccname"
    };

    /**
     * ����ϵ�����ӱ�tablecode
     */
    public static final String ALLOCFAC_TABLECODE = "itempks";
}
