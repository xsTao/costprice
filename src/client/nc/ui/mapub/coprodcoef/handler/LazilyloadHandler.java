package nc.ui.mapub.coprodcoef.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

/**
 * @since 6.0
 * @version 2014-10-11 下午12:43:23
 * @author zhangshyb
 */
public class LazilyloadHandler extends LazilyLoadManager {
    // private List<ICmBillLazilyLoadSupport> lazilyLoadSupporter1;

    // private IBillLazilyLoader loader2;

    private BillManageModel model;

    // LazilyloadHandler(IBillLazilyLoader loader, BillManageModel model,
    // List<ICmBillLazilyLoadSupport> lazilyLoadSupporter) {
    // // lazilyLoadSupporter1=super.getLazilyLoadSupporter();
    // }

    public BillManageModel getModel() {
        return this.model;
    }

    @Override
    public void setModel(BillManageModel model) {
        this.model = model;
    }

    @Override
    public void changeChildren(List<Class<? extends ISuperVO>> childrenClz, IBill[] bills) {

        Map<IBill, List<Class<? extends ISuperVO>>> needLoadChildrenMap =
                new HashMap<IBill, List<Class<? extends ISuperVO>>>();

        if (bills == null || bills.length == 0) {
            return;
        }
        for (IBill bill : bills) {
            if (bill == null) {
                continue;
            }
            List<Class<? extends ISuperVO>> needLoadChildren = null;
            if (needLoadChildrenMap.get(bill) == null) {
                needLoadChildren = new ArrayList<Class<? extends ISuperVO>>();
            }
            for (Class<? extends ISuperVO> childCls : childrenClz) {
                if (null == bill.getChildren(childCls)) {
                    needLoadChildren.add(childCls);
                }
            }
            if (needLoadChildren.size() > 0) {
                needLoadChildrenMap.put(bill, needLoadChildren);
            }
        }

        try {
            if (!needLoadChildrenMap.isEmpty()) {
                this.getLoader().loadChildrenByClass(needLoadChildrenMap);
                IBill[] loadBills = needLoadChildrenMap.keySet().toArray(new IBill[0]);
                this.model.directlyUpdate(loadBills);

            }
        }
        catch (Exception e) {
            ExceptionUtils.wrappException(e);
        }
    }

    List<Class<? extends ISuperVO>> getAllChildrenCls() {
        List<Class<? extends ISuperVO>> classList = new ArrayList<Class<? extends ISuperVO>>();
        IBill aggVo = (IBill) this.model.getSelectedData();
        if (aggVo != null) {
            IVOMeta[] voMeta = aggVo.getMetaData().getChildren();
            for (IVOMeta childMeta : voMeta) {
                Class<? extends ISuperVO> childClass = aggVo.getMetaData().getVOClass(childMeta);
                classList.add(childClass);
            }
        }
        return classList;
    }

    /**
     * 分解数据至2个页签
     */
    public IBill[] divideNewAgg(IBill[] vos) throws BusinessException {
        // 分解返回数据至两个页签
        ISuperVO head = null;
        // CoprodcoefItemVO productcoefficientItemVO = new CoprodcoefItemVO();
        // Productcoefficient_bbItemVO productcoefficient_bbItemVO = new Productcoefficient_bbItemVO();

        // String[] fileds = productcoefficient_bbItemVO.getAttributeNames();
        ArrayList<CoprodcoefAggVO> newAggvos = new ArrayList<CoprodcoefAggVO>();
        for (int i = 0; i < vos.length; i++) {
            CoprodcoefAggVO getAgg = new CoprodcoefAggVO();
            getAgg = (CoprodcoefAggVO) vos[i];
            ArrayList<CoprodcoefItemVO> backItemList = new ArrayList<CoprodcoefItemVO>();
            // ArrayList<Productcoefficient_bbItemVO> backItem_bList = new ArrayList<Productcoefficient_bbItemVO>();
            CoprodcoefAggVO backAggvo = new CoprodcoefAggVO();
            ISuperVO[] allitems = getAgg.getChildren(CoprodcoefItemVO.class);
            if (allitems != null && allitems.length > 0) {
                for (ISuperVO itemVo : allitems) {
                    if (itemVo.getAttributeValue(CoprodcoefItemVO.CELEMENTID) != null) {

                        // Productcoefficient_bbItemVO copyData = new Productcoefficient_bbItemVO();

                        // for (String filed : fileds) {
                        //
                        // copyData.setAttributeValue(filed, itemVo.getAttributeValue(filed));
                        // }
                        // copyData.setStatus(itemVo.getStatus());
                        //
                        // backItem_bList.add(copyData);
                    }
                    else {

                        backItemList.add((CoprodcoefItemVO) itemVo);
                    }
                }
            }
            head = vos[i].getParent();

            backAggvo.setParent(head);

            // Productcoefficient_bbItemVO[] children_bb =
            // backItem_bList.toArray(new Productcoefficient_bbItemVO[backItem_bList.size()]);
            CoprodcoefItemVO[] children = backItemList.toArray(new CoprodcoefItemVO[backItemList.size()]);
            // if (children_bb.length < 1) {
            // backAggvo.setChildren(Productcoefficient_bbItemVO.class, null);
            // }
            // else {
            // backAggvo.setChildren(Productcoefficient_bbItemVO.class, children_bb);
            // }
            if (children.length < 1) {
                backAggvo.setChildren(CoprodcoefItemVO.class, null);
            }
            else {
                backAggvo.setChildren(CoprodcoefItemVO.class, children);
            }
            /*
             * backAggvo.setChildren(Productcoefficient_bbItemVO.class, children_bb);
             * backAggvo.setChildren(CoprodcoefItemVO.class, children);
             */

            /*
             * backAggvo.setChildren(productcoefficient_bbItemVO.getMetaData(),
             * backItem_bList.toArray(new Productcoefficient_bbItemVO[backItem_bList.size()]));
             * backAggvo.setChildren(productcoefficientItemVO.getMetaData(),
             * backItemList.toArray(new CoprodcoefItemVO[backItemList.size()]));
             */
            newAggvos.add(backAggvo);
        }
        return newAggvos.toArray(new CoprodcoefAggVO[newAggvos.size()]);
    }
}
