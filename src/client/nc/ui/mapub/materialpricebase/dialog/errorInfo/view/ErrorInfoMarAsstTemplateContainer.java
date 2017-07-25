/**
 *
 */
package nc.ui.mapub.materialpricebase.dialog.errorInfo.view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bd.framework.base.CMValueCheck;
import nc.cmpub.framework.assistant.CMAssInfoUtil;
import nc.ui.uif2.editor.TemplateContainer;
import nc.vo.cmpub.framework.assistant.CMAssInfoItemVO;
import nc.vo.mapub.acquirepricelog.entity.AcquirePriceLogVO;
import nc.vo.pub.bill.BillTempletBodyVO;
import nc.vo.pub.bill.BillTempletVO;

/**
 * 辅助属性启用显示容器类
 *
 * @since v6.36
 * @version 2014-12-24 上午11:20:17
 * @author zhangchd
 */
public class ErrorInfoMarAsstTemplateContainer extends TemplateContainer {

    // “错误信息对话框”中对应辅助属性字段
    public final static String[] ERRORINFO_FREE_COLUMN = new String[] {
        AcquirePriceLogVO.CBPROJECTID, AcquirePriceLogVO.CBVENDORID, AcquirePriceLogVO.CBPRODUCTORID,
        AcquirePriceLogVO.CBCUSTOMERID, AcquirePriceLogVO.VBFREE1, AcquirePriceLogVO.VBFREE2,
        AcquirePriceLogVO.VBFREE3, AcquirePriceLogVO.VBFREE4, AcquirePriceLogVO.VBFREE5, AcquirePriceLogVO.VBFREE6,
        AcquirePriceLogVO.VBFREE7, AcquirePriceLogVO.VBFREE8, AcquirePriceLogVO.VBFREE9, AcquirePriceLogVO.VBFREE10
    };

    /**
     * 辅助服务字段
     */
    public static final String[] VBFREE = {
        CMAssInfoItemVO.CPROJECTID, CMAssInfoItemVO.CVENDORID, CMAssInfoItemVO.CPRODUCTORID,
        CMAssInfoItemVO.CCUSTOMERID, CMAssInfoItemVO.VBFREE1, CMAssInfoItemVO.VBFREE2, CMAssInfoItemVO.VBFREE3,
        CMAssInfoItemVO.VBFREE4, CMAssInfoItemVO.VBFREE5, CMAssInfoItemVO.VBFREE6, CMAssInfoItemVO.VBFREE7,
        CMAssInfoItemVO.VBFREE8, CMAssInfoItemVO.VBFREE9, CMAssInfoItemVO.VBFREE10
    };

    public final static Map<String, String> VBCOLUMNMAP = new HashMap<String, String>();

    static {
        for (int i = 0; i < ErrorInfoMarAsstTemplateContainer.ERRORINFO_FREE_COLUMN.length; i++) {
            ErrorInfoMarAsstTemplateContainer.VBCOLUMNMAP.put(ErrorInfoMarAsstTemplateContainer.VBFREE[i],
                    ErrorInfoMarAsstTemplateContainer.ERRORINFO_FREE_COLUMN[i]);
        }
    }

    // 缓存对隐藏列的查询结果，在列表和卡片界面的加载过程中可以减少1次远程调用。当辅助属性结构变化时，需要重新打开节点。
    private Set<String> hideColumns = new HashSet<String>();

    @SuppressWarnings("boxing")
    @Override
    public BillTempletVO getTemplate(String nodeKey, String pos, List<String> tab) {
        BillTempletVO TempletVO = super.getTemplate(nodeKey, pos, tab);
        if (TempletVO != null) {
            // 表头要显示的列
            Set<String> hideSet = this.getHideColumns();
            Set<String> vbSet = this.getVBColumns();
            BillTempletBodyVO[] vos = TempletVO.getBodyVO();
            for (BillTempletBodyVO vo : vos) {
                String key = vo.getItemkey();
                if (vbSet.contains(key)) {
                    if (hideSet.contains(key)) {
                        vo.setListshowflag(false);
                        vo.setShowflag(false);
                    }
                    else {
                        vo.setListshowflag(true);
                        vo.setShowflag(true);
                    }
                }
            }

        }
        return TempletVO;
    }

    private Set<String> getHideColumns() {
        if (CMValueCheck.isEmpty(this.hideColumns)) {
            List<String> freeHideList = new CMAssInfoUtil().getHideAssInfoCode();
            for (String column : freeHideList) {
                this.hideColumns.add(ErrorInfoMarAsstTemplateContainer.VBCOLUMNMAP.get(column));
            }
        }
        return this.hideColumns;
    }

    private Set<String> getVBColumns() {
        Set<String> vbSet = new HashSet<String>();
        for (String column : ErrorInfoMarAsstTemplateContainer.ERRORINFO_FREE_COLUMN) {
            vbSet.add(column);
        }
        return vbSet;
    }

}
