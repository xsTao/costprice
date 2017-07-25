/**
 * 
 */
package nc.ui.mapub.allocfac.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.uapbd.IMaterialPubService_C;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator;
import nc.vo.bd.material.prod.MaterialProdVO;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.mapub.allocfac.util.AllocfacItemUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2013-10-11 下午03:13:34
 * @author xionghuic
 */

public class AllocfacMarAsstPreparator extends MarAsstPreparator {
    private IAppModelEx model;

    @Override
    public void setModel(IAppModelEx model) {
        this.model = model;
        super.setModel(model);
    }

    @Override
    protected void beforEdit(CardBodyBeforeEditEvent e) {
        if (this.model.getAppUiState() == AppUiState.NOT_EDIT) {
            e.setReturnValue(false);
            return;
        }
        super.beforEdit(e);
        String column = e.getKey();
        if (this.isAsstField(column)) {
            boolean editable = false;
            MaterialProdVO vo = this.getVOMap(e);
            List<String> vbFreeColumn = this.getEditList(vo);
            if (vbFreeColumn.contains(column)) {
                editable = true;
            }
            e.setReturnValue(editable);
        }
    }

    /**
     * 获得产品计划页签辅助属性状态
     */
    private MaterialProdVO getVOMap(CardBodyBeforeEditEvent e) {
        String pkMaterial = (String) e.getBillCardPanel().getBodyValueAt(e.getRow(), AllocfacItemVO.CMATERIALID);
        MaterialProdVO vo = null;
        if (pkMaterial != null) {
            try {
                Map<String, MaterialProdVO> prodVOMap =
                        NCLocator.getInstance().lookup(IMaterialPubService_C.class)
                                .queryMaterialProduceInfoByPks(new String[] {
                                    pkMaterial
                                }, e.getContext().getPk_org(), AllocfacItemUtil.CMATERIAL_PLAN_FREE_COLUMN);
                vo = prodVOMap.get(pkMaterial);
            }
            catch (BusinessException e1) {
                ExceptionUtils.wrappException(e1);
            }
        }
        return vo;
    }

    /**
     * 获得产品在分配系数子表中可编辑的对应的字段
     */
    private List<String> getEditList(MaterialProdVO vo) {
        List<String> vbFreeColumn = new ArrayList<String>();
        if (vo != null) {
            for (String column : AllocfacItemUtil.CMATERIAL_PLAN_FREE_COLUMN) {
                if (UFBoolean.TRUE.equals(vo.getAttributeValue(column))) {
                    vbFreeColumn.add(AllocfacItemUtil.COLUMNMAP.get(column));
                }
            }
        }
        return vbFreeColumn;
    }

    /**
     * 判断给定字段是否是物料辅助属性字段(包括自由辅助属性但不包括固定辅助属性)
     * 
     * @param field
     *            需要判断的字段名称
     * @return 如果是辅料辅助属性字段返回true，否则返回false
     */
    private boolean isAsstField(String field) {
        for (String column : AllocfacItemUtil.ALLOCFAC_FREE_COLUMN) {
            if (column.equalsIgnoreCase(field)) {
                return true;
            }
        }
        return false;
    }
}
