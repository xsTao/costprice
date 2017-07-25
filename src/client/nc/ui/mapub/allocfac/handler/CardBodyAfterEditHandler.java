/**
 *
 */
package nc.ui.mapub.allocfac.handler;

import nc.ui.bd.pub.handler.CMBasedocEventHandler;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.mapub.allocfac.enumeration.AllocfacEnum;
import nc.vo.mapub.allocfac.util.AllocfacItemUtil;

/**
 * ��Ƭ����༭�¼�����
 */
public class CardBodyAfterEditHandler extends CMBasedocEventHandler implements IAppEventHandler<CardBodyAfterEditEvent> {
    public static final int VALUE = 1;

    @Override
    public void handleAppEvent(CardBodyAfterEditEvent e) {
        if (e.getKey().equals(AllocfacItemVO.CMATERIALID)) {
            this.setVBFreeNull(e);
        }
        if (e.getKey().equals(AllocfacItemVO.CCOSTCENTERID) || e.getKey().equals(AllocfacItemVO.CMATERIALID)
                || e.getKey().equals(AllocfacItemVO.CMARCOSTCLASSID) || e.getKey().equals(AllocfacItemVO.CACTIVITYID)
                || AllocfacItemVO.CSTUFFID.equals(e.getKey()) || AllocfacItemVO.CMARBASECLASSID.equals(e.getKey())) {
            this.multiSelectEdit(e);
        }
    }

    /**
     * ���ò�Ʒ��������Ϊ��
     */
    private void setVBFreeNull(CardBodyAfterEditEvent e) {
        Object type = e.getBillCardPanel().getHeadItem(AllocfacHeadVO.IALLOCTYPE).getValueObject();
        if (!type.equals(AllocfacEnum.productvbfree.toIntValue())) {
            return;
        }
        if (e.getOldValue() == null && e.getValue() == null) {
            return;
        }
        if (e.getOldValue() != null && e.getOldValue().equals(e.getValue())) {
            return;
        }
        for (String name : AllocfacItemUtil.VBFREE) {
            e.getBillCardPanel().setBodyValueAt(null, e.getRow(), name);
        }
    }

    /**
     * ���ն�ѡ
     */
    private void multiSelectEdit(CardBodyAfterEditEvent e) {
        RefMoreSelectedUtils utils = new RefMoreSelectedUtils(e.getBillCardPanel());
        utils.refMoreSelected(e.getRow(), e.getKey(), true);
    }

    @Override
    public void initMap() {
    }
}
