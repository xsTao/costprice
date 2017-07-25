package nc.bs.mapub.driver.listener;

import nc.bd.framework.db.CMSqlBuilder;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.pub.BusinessException;

public class DriverDeleteDefaultListener implements IBusinessListener {

    @Override
    public void doAction(IBusinessEvent event) throws BusinessException {

        CMSqlBuilder sql = new CMSqlBuilder();
        sql.delete(DriverVO.getDefaultTableName());
        sql.where();
        sql.append(DriverVO.PK_GROUP, CMDriverLangConst.GLFLAG);
        sql.and();
        sql.append(DriverVO.VCODE, DriverVO.INIT_CODES);

        new DataAccessUtils().update(sql.toString());

    }

    // 通知缓存(不能回滚，暂时不刷新缓存)
    // CacheProxy.fireDataInserted("cm_driver");

}
