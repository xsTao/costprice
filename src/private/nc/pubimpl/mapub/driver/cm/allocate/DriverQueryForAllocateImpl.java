package nc.pubimpl.mapub.driver.cm.allocate;

import nc.bd.framework.base.CMArrayUtil;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.mapub.driver.cm.allocate.IDriverQueryForAllocate;
import nc.vo.mapub.driver.entity.DriverVO;

/**
 * 成本动因
 *
 * @since 6.0
 * @version 2012-9-25 下午03:25:46
 * @author liyjf
 */
public class DriverQueryForAllocateImpl implements IDriverQueryForAllocate {
    @Override
    public DriverVO[] queryDriverByPKs(String[] PKs) {
        if (CMArrayUtil.isEmpty(PKs)) {
            return null;
        }

        return new VOQuery<DriverVO>(DriverVO.class).query(PKs);
    }
}
