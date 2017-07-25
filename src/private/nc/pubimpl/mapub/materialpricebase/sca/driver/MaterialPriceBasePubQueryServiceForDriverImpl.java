package nc.pubimpl.mapub.materialpricebase.sca.driver;

import java.util.ArrayList;
import java.util.List;

import nc.bd.framework.base.CMStringUtil;
import nc.bd.framework.base.CMValueCheck;
import nc.bd.framework.db.CMSqlBuilder;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.mapub.materialpricebase.sca.driver.IMaterialPriceBasePubQueryServiceForDriver;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

public class MaterialPriceBasePubQueryServiceForDriverImpl implements IMaterialPriceBasePubQueryServiceForDriver {

    @Override
    public List<MaterialPriceBaseHeadVO> queryMaterialPriceBaseHeadVOByOrg(String pkOrg, UFDate date)
            throws BusinessException {
        CMSqlBuilder wheresql = new CMSqlBuilder();
        if (CMStringUtil.isNotEmpty(pkOrg)) {
            wheresql.and();
            wheresql.append(MaterialPriceBaseHeadVO.PK_ORG, pkOrg);
        }
        else {
            wheresql.and();
            wheresql.appendIDIsNull(MaterialPriceBaseHeadVO.PK_ORG);
        }
        if (CMValueCheck.isNotEmpty(date)) {
            wheresql.append(" and '");
            wheresql.append(date);
            wheresql.append("' >= ");
            wheresql.append(MaterialPriceBaseHeadVO.DBEGINDATE);
            wheresql.append(" and '");
            wheresql.append(date);
            wheresql.append("' <= ");
            wheresql.append(MaterialPriceBaseHeadVO.DENDDATE);
        }
        wheresql.appendDr();
        MaterialPriceBaseHeadVO[] vos =
                new VOQuery<MaterialPriceBaseHeadVO>(MaterialPriceBaseHeadVO.class).query(wheresql.toString(), null);
        List<MaterialPriceBaseHeadVO> results = new ArrayList<MaterialPriceBaseHeadVO>();
        if (vos != null) {
            for (MaterialPriceBaseHeadVO vo : vos) {
                results.add(vo);
            }
        }
        return results;
    }
}
