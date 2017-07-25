package nc.bs.mapub.driver.listener;

import nc.bs.bd.cache.CacheProxy;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.businessevent.bd.BDCommonEvent;
import nc.bs.framework.common.NCLocator;
import nc.bs.mapub.driver.util.DriverInitDMO;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.itf.mapub.driver.IDriverQueryService;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class DriverAddDefaultListener implements IBusinessListener {

    @Override
    public void doAction(IBusinessEvent event) throws BusinessException {
        BDCommonEvent be = (BDCommonEvent) event;
        OrgVO orgvo = (OrgVO) be.getNewObjs()[0];
        // if (orgvo == null) {
        // return;
        // }
        // // 如果不是业务单元就不
        // if (orgvo.getIsbusinessunit() == null || !orgvo.getIsbusinessunit().booleanValue()) {
        // return;
        // }
        String pk_org = null;
        if (orgvo != null) {
            pk_org = orgvo.getPk_org();
        }

        IDriverQueryService service =
                (IDriverQueryService) NCLocator.getInstance().lookup(IDriverQueryService.class.getName());
        // 从数据库中查询出得VO
        DriverVO[] dbVos = service.queryDriverByOrg(pk_org, null);
        boolean isInit = false;
        if (dbVos != null) {
            for (int i = 0; i < dbVos.length; i++) {
                if (CMDriverLangConst.GLFLAG.equals(dbVos[i].getPk_group())) {
                    isInit = true;
                    break;
                }
            }
        }
        if (!isInit) {
            this.initDriverByOrg();
        }
    }

    private void initDriverByOrg() throws BusinessException {
        DriverInitDMO initdmo = new DriverInitDMO();
        // 初始化预置成本动因VO
        initdmo.initDatas();
        VOInsert<DriverVO> tool = new VOInsert<DriverVO>();
        try {
            tool.insert(initdmo.getDriverVOList().toArray(new DriverVO[0]));
            // 通知缓存
            CacheProxy.fireDataInserted("cm_driver");
        }
        catch (Exception e) {
            ExceptionUtils.marsh(e);
        }
    }

}
