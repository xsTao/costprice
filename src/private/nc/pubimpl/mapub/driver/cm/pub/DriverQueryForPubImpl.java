package nc.pubimpl.mapub.driver.cm.pub;

import java.util.List;

import nc.bd.framework.db.CMSqlBuilder;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.mapub.driver.cm.pub.IDriverQueryForPub;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;

/**
 * @since v6.3
 * @version 2013-7-9 ÏÂÎç02:31:56
 * @author liyjf
 */
public class DriverQueryForPubImpl implements IDriverQueryForPub {
    @Override
    public boolean isUsedInDriver(String pk_org, String driverType, List<String> idList) throws BusinessException {
        CMSqlBuilder sql = new CMSqlBuilder();
        sql.append("select distinct vformulacode from cm_driver where (pk_org ='" + pk_org + "' or pk_group='@@@@')"
                + " and vformulacode like '%" + driverType + "%' and dr=0");

        IRowSet rows = new DataAccessUtils().query(sql.toString());
        while (rows.next()) {
            for (String id : idList) {
                if (rows.getString(0).indexOf(id) != -1) {
                    return false;
                }
            }
        }
        return true;
    }
}
