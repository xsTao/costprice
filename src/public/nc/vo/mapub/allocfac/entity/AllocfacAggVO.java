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
 * @version 2015-1-13 ����2:18:15
 * @author yuzheb
 */
@SuppressWarnings("serial")
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.mapub.allocfac.entity.AllocfacHeadVO")
public class AllocfacAggVO extends AbstractBill {
    // ���渨�����Ա�������Ƶ�map,������̨����ʾ����
    private Map<String, String> map;

    /**
     * ��� map ������ֵ
     */
    public Map<String, String> getMap() {
        return this.map;
    }

    /**
     * ���� map ������ֵ
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
     * ��õ��ݱ�ͷVO
     * 
     * @return ���ݱ�ͷVO
     */
    @Override
    public AllocfacHeadVO getParentVO() {
        return (AllocfacHeadVO) this.getParent();
    }

    /**
     * ��õ��ݱ���VO
     * 
     * @return ���ݱ���VO����
     */
    public AllocfacItemVO[] getItemVO() {
        return (AllocfacItemVO[]) this.getChildren(AllocfacItemVO.class);
    }

    /**
     * ����Ԫ���ݱ�ͷ����
     **/
    public class BillMeta extends AbstractBillMeta {
        /**
         * ���췽��
         **/
        public BillMeta() {
            this.setParent(AllocfacHeadVO.class);
            this.addChildren(AllocfacItemVO.class);
            IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("cm.cm_allocfac_b");
            this.addChildForeignKey(meta.getAttribute(AllocfacItemVO.CALLOCFACID));
        }
    }
}
