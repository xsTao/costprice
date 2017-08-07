/**
 *
 */
package nc.ui.mapub.costpricebase.view;

import nc.ui.mapub.costpricebase.scale.CostPriceBaseScaleUtil;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.components.pagination.PaginationBar;
import nc.vo.mapub.costpricebase.entity.CostPriceHeadVO;

/**
 * @since v6.3
 * @version 2017年7月19日 下午3:53:58
 * @author Administrator
 */
public class CostPriceListView extends ShowUpableBillListView {

    private static final long serialVersionUID = -8906686830841878902L;

    private PaginationBar paginationBar;

    @Override
    public void initUI() {
        super.initUI();
        new CostPriceBaseScaleUtil().setListScale(this.getModel().getContext().getPk_group(), this.getBillListPanel());
        this.getBillListPanel().getHeadBillModel().setSortColumn(new String[] {
                CostPriceHeadVO.PK_ORG, CostPriceHeadVO.CCOSTPRICEID
        });
        this.billListPanel.addMouseListener(this);
        if (null != this.paginationBar) {
            this.billListPanel.addHeadNavigatePanel(this.paginationBar);
        }
    }

    /**
     * 获得 paginationBar 的属性值
     *
     * @return the paginationBar
     * @since 2017年8月2日
     * @author Administrator
     */
    @Override
    public PaginationBar getPaginationBar() {
        return this.paginationBar;
    }

    /**
     * 设置 paginationBar 的属性值
     *
     * @param paginationBar the paginationBar to set
     * @since 2017年8月2日
     * @author Administrator
     */
    @Override
    public void setPaginationBar(PaginationBar paginationBar) {
        this.paginationBar = paginationBar;
    }

}
