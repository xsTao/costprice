/**
 * 
 */
package nc.ui.mapub.allocfac.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.ui.mapub.allocfac.view.AllocfacBillForm;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.actions.DifferentVOSaveAction;
import nc.ui.uif2.UIState;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.mapub.allocfac.util.AllocfacItemUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.bill.IMetaDataProperty;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;

/**
 * @since v6.3
 * @version 2013-9-11 下午03:27:15
 * @author xionghuic
 */

public class AllocfacSaveAction extends DifferentVOSaveAction {
    private static final long serialVersionUID = 215159769588834322L;

    @Override
    protected void doAddSave(Object value) throws Exception {
        // 取界面vo数据
        IBill[] clientVOs = new IBill[] {
            (IBill) value
        };

        ClientBillToServer<IBill> tool = new ClientBillToServer<IBill>();

        // 取得差异VO，并把差异vo传到后台
        IBill[] lightVOs = tool.constructInsert(clientVOs);
        IBill[] afterUpdateVOs = null;

        if (this.getService() == null) {
            throw new BusinessException("servce can't be null!");
        }
        this.setMap(lightVOs);
        afterUpdateVOs = this.getService().insert(lightVOs);

        // clientVOs为界面上的数据，afterUpdateVOs为后台返回的差异数据，取全vo数据
        new ClientBillCombinServer<IBill>().combine(clientVOs, afterUpdateVOs);

        this.getModel().directlyAdd(clientVOs[0]);
        this.getModel().setUiState(UIState.NOT_EDIT);
    }

    @Override
    protected void doEditSave(Object value) throws Exception {
        // 取界面vo数据
        IBill[] clientVOs = new IBill[] {
            (IBill) value
        };

        ClientBillToServer<IBill> tool = new ClientBillToServer<IBill>();

        IBill[] oldVO = new IBill[] {
            (IBill) this.getModel().getSelectedData()
        };
        // 取得轻量级VO
        IBill[] lightVOs = tool.construct(oldVO, clientVOs);
        IBill[] afterUpdateVOs = null;

        if (this.getService() == null) {
            throw new BusinessException("service can't be null");
        }
        this.setMap(lightVOs);
        afterUpdateVOs = this.getService().update(lightVOs);

        // clientVOs为界面上的数据，afterUpdateVOs为后台返回的差异数据
        new ClientBillCombinServer<IBill>().combine(clientVOs, afterUpdateVOs);

        this.getModel().directlyUpdate(clientVOs[0]);
        this.getModel().setUiState(UIState.NOT_EDIT);
    }

    private void setMap(IBill[] VOs) {
        Map<String, String> fieldMap = this.handlerMap();
        AllocfacAggVO aggVO = (AllocfacAggVO) VOs[0];
        if (aggVO.getMap() == null) {
            aggVO.setMap(fieldMap);
        }
    }

    private Map<String, String> handlerMap() {
        BillItem[] items = ((AllocfacBillForm) this.getEditor()).getBodyItems();
        Map<String, String> fieldMap = new HashMap<String, String>();
        Set<String> fieldSet = new HashSet<String>();
        for (String s : AllocfacItemUtil.VBFREE) {
            fieldSet.add(s);
        }
        for (BillItem item : items) {
            String key = item.getKey();
            if (fieldSet.contains(key)) {
                String name = item.getName();
                if (name == null) {
                    IMetaDataProperty meta = item.getMetaDataProperty();
                    name = meta.getAttribute().getDisplayName();

                }
                fieldMap.put(key, name);
            }
        }
        return fieldMap;
    }
}
