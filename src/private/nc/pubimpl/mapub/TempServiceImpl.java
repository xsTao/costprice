/**
 * 
 */
package nc.pubimpl.mapub;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bd.framework.db.CMSqlBuilder;
import nc.bs.dao.BaseDAO;
import nc.cmpub.business.adapter.pccm.BDAdapter;
import nc.pubitf.mapub.ITempService;
import nc.vo.bd.material.pfc.MaterialPfcSubVO;
import nc.vo.bd.material.pfc.MaterialPfcVO;
import nc.vo.pub.BusinessException;

/**
 * @since v6.3
 * @version 2014-12-22 ÉÏÎç8:31:39
 * @author shuzhan
 */
public class TempServiceImpl implements ITempService {

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, MaterialPfcVO> queryMaterialPfcVO(String[] cmaterialOidArr, String pk_org)
            throws BusinessException {
        Map<String, MaterialPfcVO> resultMap = new HashMap<String, MaterialPfcVO>();
        Map<String, String> oid2VidMap = BDAdapter.convertMaterialid2Vid(cmaterialOidArr);
        Map<String, String> vid2OidMap = BDAdapter.convertMaterialvid2Oid(oid2VidMap.values().toArray(new String[0]));
        String[] materialVIDs = oid2VidMap.values().toArray(new String[0]);
        CMSqlBuilder sqlBuilder = new CMSqlBuilder();
        sqlBuilder.append(MaterialPfcVO.PK_ORG, pk_org);
        sqlBuilder.and();
        sqlBuilder.append(MaterialPfcVO.PK_MATERIAL, materialVIDs);
        sqlBuilder.appendDr();
        Collection<MaterialPfcVO> materialPfcVOs =
                new BaseDAO().retrieveByClause(MaterialPfcVO.class, sqlBuilder.toString());
        Set<String> pk_materialpfcSet = new HashSet<String>();
        for (MaterialPfcVO materialPfcVO : materialPfcVOs) {
            pk_materialpfcSet.add(materialPfcVO.getPk_materialpfc());
        }
        sqlBuilder = new CMSqlBuilder();
        sqlBuilder.append(MaterialPfcSubVO.PK_MATERIALPFC, pk_materialpfcSet.toArray(new String[0]));
        sqlBuilder.appendDr();

        Collection<MaterialPfcSubVO> materialPfcSubVOs =
                new BaseDAO().retrieveByClause(MaterialPfcSubVO.class, sqlBuilder.toString());
        Map<String, MaterialPfcSubVO> pk_materialpfcSubVOMap = new HashMap<String, MaterialPfcSubVO>();
        for (MaterialPfcSubVO materialPfcSubVO : materialPfcSubVOs) {
            pk_materialpfcSubVOMap.put(materialPfcSubVO.getPk_materialpfc(), materialPfcSubVO);
        }
        for (MaterialPfcVO materialPfcVO : materialPfcVOs) {
            if (pk_materialpfcSubVOMap.containsKey(materialPfcVO.getPk_materialpfc())) {
                materialPfcVO.setMaterialpfcsub(new MaterialPfcSubVO[] {
                    pk_materialpfcSubVOMap.get(materialPfcVO.getPk_materialpfc())
                });
            }
            resultMap.put(vid2OidMap.get(materialPfcVO.getPk_material()), materialPfcVO);
        }
        return resultMap;
    }
}
