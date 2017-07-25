/**
 *
 */
package nc.itf.mapub.costpricebase;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;

/**
 * @since v6.3
 * @version 2017年7月19日 下午4:35:22
 * @author
 */
public interface ICostPriceBaseQueryService {

    public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws BusinessException;

    /*
     * public String[] queryPkorgsByOrgType(String orgtypeid, String funcode, String cuserid, String pk_group)
     * throws BusinessException;
     */

    /* public Map<String, String> queryOrgCodeOid(String[] pk_orgs) throws BusinessException; */
}
