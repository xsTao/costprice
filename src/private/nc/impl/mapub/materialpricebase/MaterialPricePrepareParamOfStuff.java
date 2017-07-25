package nc.impl.mapub.materialpricebase;

import nc.itf.mapub.materialpricebase.IMaterialPriceBasePrepareParam;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;

/**
 * <b> �ڼ۸���в�ѯ[����]�ӱ�ļ۸�ʱ������׼�� </b>
 * <p>
 * ��ϸ��������
 * </p>
 * ��������:2010-9-11
 * 
 * @author:jilu
 */
public class MaterialPricePrepareParamOfStuff implements IMaterialPriceBasePrepareParam {

    @Override
    public String[] getFieldsForSQL() {
        String[] fields =
                {
                    MaterialPriceBaseBodyVO.PK_ORG, MaterialPriceBaseBodyVO.CMATERIALPRICEID,
                    MaterialPriceBaseBodyVO.CMATERIALID, MaterialPriceBaseBodyVO.NPRICE
                };

        return fields;
    }

    @Override
    public String getTableName() {
        return "bd_materialpricebase_b";
    }

}
