/**
 * 
 */
package nc.vo.mapub.allocfac.entity;

import java.util.Map;

import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * @since v6.3
 * @version 2015-1-13 下午2:18:15
 * @author yuzheb
 */
@SuppressWarnings("serial")
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.mapub.allocfac.entity.AllocfacHeadVO")
public class AllocfacAggVO extends AbstractBill {
    // 保存辅助属性编码和名称的map,传到后台来提示名称
    private Map<String, String> map;

    /**
     * 获得 map 的属性值
     */
    public Map<String, String> getMap() {
        return this.map;
    }

    /**
     * 设置 map 的属性值
     */
    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public IBillMeta getMetaData() {
        IBillMeta meta = BillMetaFactory.getInstance().getBillMeta("cm.cm_allocfac");
        return meta;
    }

    /**
     * 获得单据表头VO
     * 
     * @return 单据表头VO
     */
    @Override
    public AllocfacHeadVO getParentVO() {
        return (AllocfacHeadVO) this.getParent();
    }

    /**
     * 获得单据表体VO
     * 
     * @return 单据表体VO数组
     */
    public AllocfacItemVO[] getItemVO() {
        return (AllocfacItemVO[]) this.getChildren(AllocfacItemVO.class);
    }

    /**
     * 设置元数据表头表体
     **/
    public class BillMeta extends AbstractBillMeta {
        /**
         * 构造方法
         **/
        public BillMeta() {
            this.setParent(AllocfacHeadVO.class);
            this.addChildren(AllocfacItemVO.class);
            IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("cm.cm_allocfac_b");
            this.addChildForeignKey(meta.getAttribute(AllocfacItemVO.CALLOCFACID));
        }
    }
}
