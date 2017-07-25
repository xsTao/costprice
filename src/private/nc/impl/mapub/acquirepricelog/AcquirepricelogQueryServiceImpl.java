/**
 *
 */
package nc.impl.mapub.acquirepricelog;

import java.util.HashMap;
import java.util.Map;

import nc.bd.framework.db.CMSqlBuilder;
import nc.cmpub.business.query.CMQueryVOIDs;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.itf.mapub.acquirepricelog.IAcquirepricelogQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.mapub.acquirepricelog.entity.AcquirePriceLogVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2015年4月20日 上午9:20:24
 * @author lizhpf
 */
public class AcquirepricelogQueryServiceImpl implements IAcquirepricelogQueryService {

    @SuppressWarnings({
        "unchecked", "rawtypes"
    })
    @Override
    public AcquirePriceLogVO[] queryByQueryScheme(IQueryScheme queryScheme) throws BusinessException {
        // TODO Auto-generated method stub

        if (queryScheme == null) {

            return null;

        }
        try {
            // VOQuery<AcquirePriceLogVO> vo = new VOQuery<AcquirePriceLogVO>(AcquirePriceLogVO.class);
            // return vo.query("and 1=1", null);

            // 取得查询条件
            // QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
            // CMSqlBuilder sql = new CMSqlBuilder();
            // qsp.appendWhere(sql.toString());
            Map<String, Integer> param = new HashMap<String, Integer>();
            // param.put("cmaterialid.pk_marbasclass", CMQueryVOIDs.BASE);
            // param.put("cmarcostclassid", CMQueryVOIDs.COST);
            String[] ids = new CMQueryVOIDs().queryVOIDs(AcquirePriceLogVO.class, queryScheme, param);
            if (ids.length == 0) {
                return null;
            }
            CMSqlBuilder whereSql = new CMSqlBuilder();
            whereSql.append(" and ");
            whereSql.append(AcquirePriceLogVO.PK_LOG, ids);
            VOQuery<AcquirePriceLogVO> vo = new VOQuery<AcquirePriceLogVO>(AcquirePriceLogVO.class);
            return vo.query(whereSql.toString(), null);
        }
        catch (Exception e) {
            ExceptionUtils.marsh(e);
            return null;
        }

    }

}
