package nc.vo.mapub.allocfac.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.cmpub.framework.assistant.CMAssInfoUtil;
import nc.vo.bd.material.prod.MaterialProdVO;
import nc.vo.cmpub.framework.assistant.CMAssInfoItemVO;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.mapub.allocfac.enumeration.AllocfacEnum;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class AllocfacItemUtil {
    /**
     * 辅助服务字段
     */
    public static final String[] VBFREE = {
        CMAssInfoItemVO.CCUSTOMERID, CMAssInfoItemVO.CVENDORID, CMAssInfoItemVO.CPRODUCTORID,
        CMAssInfoItemVO.CPROJECTID, CMAssInfoItemVO.VBFREE1, CMAssInfoItemVO.VBFREE2, CMAssInfoItemVO.VBFREE3,
        CMAssInfoItemVO.VBFREE4, CMAssInfoItemVO.VBFREE5, CMAssInfoItemVO.VBFREE6, CMAssInfoItemVO.VBFREE7,
        CMAssInfoItemVO.VBFREE8, CMAssInfoItemVO.VBFREE9, CMAssInfoItemVO.VBFREE10
    };

    /**
     * 根据分配内容中选择的项 返回其表体中对应的列名
     */
    public static String[] getAllocContentColumByContent(String value) {
        List<String> result = new ArrayList<String>();
        if (AllocfacEnum.costcenter.getEnumValue().getValue().equals(value)) {
            result.add(AllocfacItemVO.CCOSTCENTERID); // 表格中列的id
            result.add("ccostcenterid.ccname");
        }
        else if (AllocfacEnum.activity.getEnumValue().getValue().equals(value)) {
            result.add(AllocfacItemVO.CACTIVITYID); // 表格中列的id
            result.add("cactivityid.vactivityname");
        }
        else if (AllocfacEnum.costclass.getEnumValue().getValue().equals(value)) {
            result.add(AllocfacItemVO.CMARCOSTCLASSID); // 表格中列的id
            result.add("cmarcostclassid.name");
        }
        else if (AllocfacEnum.productvbfree.getEnumValue().getValue().equals(value)) {
            List<String> freeHideList = new CMAssInfoUtil().getHideAssInfoCode();
            List<String> showList = new ArrayList<String>();
            showList.add(AllocfacItemVO.CMATERIALID);
            showList.add("cmaterialid.name");
            for (String column : AllocfacItemUtil.VBFREE) {
                showList.add(column);
            }
            for (String column : freeHideList) {
                showList.remove(column);
            }
            return showList.toArray(new String[0]);
        }
        else if (AllocfacEnum.product.getEnumValue().getValue().equals(value)) {
            result.add(AllocfacItemVO.CMATERIALID); // 表格中列的id
            result.add("cmaterialid.name");
        }
        else if (AllocfacEnum.stuff.getEnumValue().getValue().equals(value)) {
            result.add(AllocfacItemVO.CSTUFFID); // 表格中列的id
            result.add("cstuffid.name");
        }
        else if (AllocfacEnum.baseclass.getEnumValue().getValue().equals(value)) {
            result.add(AllocfacItemVO.CMARBASECLASSID); // 表格中列的id
            result.add("cmarbaseclassid.name");
        }
        else {
            ExceptionUtils.wrappBusinessException(CMMLangConstAllocfac.getALLOCCONTENT_ERROR());
        }
        return result.toArray(new String[0]);
    }

    public static String getDefaultTableName() {
        return "cm_allocfac_b";
    }

    public final static String[] CMATERIAL_PLAN_FREE_COLUMN = new String[] {
        MaterialProdVO.COSTVALUTASST2, MaterialProdVO.COSTVALUTASST3, MaterialProdVO.COSTVALUTASST4,
        MaterialProdVO.COSTVALUTASST5, MaterialProdVO.COSTVALUTASST6, MaterialProdVO.COSTVALUTASST7,
        MaterialProdVO.COSTVALUTASST8, MaterialProdVO.COSTVALUTASST9, MaterialProdVO.COSTVALUTASST10,
        MaterialProdVO.COSTVALUTASST11, MaterialProdVO.COSTVALUTASST12, MaterialProdVO.COSTVALUTASST13,
        MaterialProdVO.COSTVALUTASST14, MaterialProdVO.COSTVALUTASST15
    };

    public final static String[] ALLOCFAC_FREE_COLUMN = new String[] {
        CMAssInfoItemVO.CPROJECTID, CMAssInfoItemVO.CVENDORID, CMAssInfoItemVO.CPRODUCTORID,
        CMAssInfoItemVO.CCUSTOMERID, CMAssInfoItemVO.VBFREE1, CMAssInfoItemVO.VBFREE2, CMAssInfoItemVO.VBFREE3,
        CMAssInfoItemVO.VBFREE4, CMAssInfoItemVO.VBFREE5, CMAssInfoItemVO.VBFREE6, CMAssInfoItemVO.VBFREE7,
        CMAssInfoItemVO.VBFREE8, CMAssInfoItemVO.VBFREE9, CMAssInfoItemVO.VBFREE10
    };

    public final static Map<String, String> COLUMNMAP = new HashMap<String, String>();

    static {
        for (int i = 0; i < AllocfacItemUtil.CMATERIAL_PLAN_FREE_COLUMN.length; i++) {
            AllocfacItemUtil.COLUMNMAP.put(AllocfacItemUtil.CMATERIAL_PLAN_FREE_COLUMN[i],
                    AllocfacItemUtil.ALLOCFAC_FREE_COLUMN[i]);
        }
    }

    public final static String[] MATERIALPRODFIELDS = new String[] {
        MaterialProdVO.COSTVALUTASST2, MaterialProdVO.COSTVALUTASST3, MaterialProdVO.COSTVALUTASST4,
        MaterialProdVO.COSTVALUTASST5, MaterialProdVO.COSTVALUTASST6, MaterialProdVO.COSTVALUTASST7,
        MaterialProdVO.COSTVALUTASST8, MaterialProdVO.COSTVALUTASST9, MaterialProdVO.COSTVALUTASST10,
        MaterialProdVO.COSTVALUTASST11, MaterialProdVO.COSTVALUTASST12, MaterialProdVO.COSTVALUTASST13,
        MaterialProdVO.COSTVALUTASST14, MaterialProdVO.COSTVALUTASST15, MaterialProdVO.SFCBDX,
        MaterialProdVO.SFCBDXTYPE
    };
}
