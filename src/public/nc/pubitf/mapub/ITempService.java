/**
 * 
 */
package nc.pubitf.mapub;

import java.util.Map;

import nc.vo.bd.material.pfc.MaterialPfcVO;
import nc.vo.pub.BusinessException;

/**
 * @since v6.3
 * @version 2014-12-19 обнГ1:41:01
 * @author shuzhan
 */
public interface ITempService {
    Map<String, MaterialPfcVO> queryMaterialPfcVO(String[] cmaterialOidArr, String pk_org) throws BusinessException;
}
