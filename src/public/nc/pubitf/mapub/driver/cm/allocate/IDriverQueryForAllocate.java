package nc.pubitf.mapub.driver.cm.allocate;

import nc.vo.mapub.driver.entity.DriverVO;

/**
 * 成本动因查询
 * 
 * @author liyjf
 */
public interface IDriverQueryForAllocate {
    /**
     * 根据主键查询
     * 
     * @param PKs
     *            主键数组
     * @return 成本动因vos
     */

    DriverVO[] queryDriverByPKs(String[] PKs);
}
