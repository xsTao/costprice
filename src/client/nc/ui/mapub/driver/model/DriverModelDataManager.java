package nc.ui.mapub.driver.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.driver.IDriverQueryService;
import nc.ui.pubapp.uif2app.query2.model.ModelDataManager;
import nc.vo.mapub.driver.entity.DriverAggVO;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.ml.MultiLangContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class DriverModelDataManager extends ModelDataManager {

    @Override
    public void initModel() {
        this.queryData(this.getModel().getContext().getPk_org());
    }

    public void queryData(String pk_org) {
        IDriverQueryService service =
                (IDriverQueryService) NCLocator.getInstance().lookup(IDriverQueryService.class.getName());
        List<DriverAggVO> driverAggVOList = new ArrayList<DriverAggVO>();
        List<DriverAggVO> seqAggVOList = new ArrayList<DriverAggVO>();
        try {
            // 从数据库中查询出得VO
            DriverVO[] dbVos = service.queryDriverByOrg(pk_org, DriverVO.VCODE);
            if (null == dbVos || dbVos.length == 0) {
                this.getModel().initModel(null);
            }
            int currentLangSeq = MultiLangContext.getInstance().getCurrentLangSeq().intValue();
            List<String> initcode = Arrays.asList(DriverVO.INIT_CODES);
            for (DriverVO dbVo : dbVos) {
                DriverAggVO agg = new DriverAggVO();
                agg.setParent(dbVo);
                if (!initcode.contains(dbVo.getVcode())) {
                    seqAggVOList.add(agg);
                }
                else {
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
                    driverAggVOList.add(agg);
                }
            }
            driverAggVOList.addAll(seqAggVOList);
            this.getModel().initModel(driverAggVOList.toArray(new DriverAggVO[0]));

        }
        catch (Exception e) {
            ExceptionUtils.wrappException(e);
        }
    }

}
