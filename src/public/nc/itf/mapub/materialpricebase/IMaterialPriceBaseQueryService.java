package nc.itf.mapub.materialpricebase;

import java.util.Map;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;

public interface IMaterialPriceBaseQueryService {
    public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws BusinessException;

    public String[] queryPkorgsByOrgType(String orgtypeid, String funcode, String cuserid, String pk_group)
            throws BusinessException;

    public Map<String, String> queryOrgCodeOid(String[] pk_orgs) throws BusinessException;
}
