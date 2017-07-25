package nc.ui.mapub.driver.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.smart.IBillQueryService;
import nc.ui.ml.NCLangRes;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.ml.MultiLangContext;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

@SuppressWarnings("serial")
public class DriverCardRefreshAction extends nc.ui.pubapp.uif2app.actions.RefreshSingleAction {

    @Override
    public void doAction(ActionEvent e) throws Exception {
        Object obj = this.model.getSelectedData();
        if (obj != null) {
            AbstractBill oldVO = (AbstractBill) obj;
            String pk = oldVO.getParentVO().getPrimaryKey();
            IBillQueryService billQuery = NCLocator.getInstance().lookup(IBillQueryService.class);
            AggregatedValueObject newVO = billQuery.querySingleBillByPk(oldVO.getClass(), pk);
            // 单据被删除之后应该回到列表界面再刷新
            if (newVO == null) {
                // 数据已经被删除
                throw new BusinessException(NCLangRes.getInstance().getStrByID("uif2", "RefreshSingleAction-000000")/*
                                                                                                                     * 数据已经被删除
                                                                                                                     * ，
                                                                                                                     * 请返回列表界面
                                                                                                                     * ！
                                                                                                                     */);
            }

            int currentLangSeq = MultiLangContext.getInstance().getCurrentLangSeq().intValue();
            DriverVO dbVo = (DriverVO) newVO.getParentVO();
            String formula = null;
            switch (currentLangSeq) {
                case 2:
                    formula = dbVo.getVformulavalue2();
                    break;
                case 3:
                    formula = dbVo.getVformulavalue3();
                    break;
                case 4:
                    formula = dbVo.getVformulavalue4();
                    break;
                case 5:
                    formula = dbVo.getVformulavalue5();
                    break;
                case 6:
                    formula = dbVo.getVformulavalue6();
                    break;
            }
            if (null == formula || formula.trim().length() <= 0) {
                formula = dbVo.getVformulavalue();
            }
            dbVo.setVformulavalue(formula);

            this.model.directlyUpdate(newVO);
        }
        this.showQueryInfo();
    }
}
