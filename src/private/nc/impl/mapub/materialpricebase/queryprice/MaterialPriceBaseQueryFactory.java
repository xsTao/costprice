package nc.impl.mapub.materialpricebase.queryprice;

import nc.impl.mapub.materialpricebase.MaterialPricePrepareParamOfStuff;
import nc.itf.mapub.materialpricebase.IMaterialPriceBasePrepareParam;
import nc.vo.cmpub.business.enumeration.PriceResTypeEnum;

/**
 * <b> �۸���ѯ�۸�Ĺ����࣬����3�ֲ�ѯ������ֱ��Ӧ���ϡ���ҵ������3���ӱ�Ĳ�ѯ </b>
 * <p>
 * ��ϸ��������
 * </p>
 * ��������:2010-9-11
 * 
 * @author:jilu
 */
public class MaterialPriceBaseQueryFactory {

    /**
     * ���ݲ�ѯ�����ͣ�������Ӧ�Ľӿ�ʵ����
     * 
     * @param priceQueryType
     *            ��ѯ���ͣ����ù����е����Ͳ���
     * @return �ӿ�ʵ��
     */
    public static IMaterialPriceBasePrepareParam getPricePrepareParam(PriceResTypeEnum priceQueryType) {

        if (priceQueryType.equals(PriceResTypeEnum.STUFF)) {
            // ���ز�ѯ[����]�۸�ר��ʵ����
            return new MaterialPricePrepareParamOfStuff();

        }
        // else if (priceQueryType.equals(PriceResTypeEnum.ACTV)) {
        // // ���ز�ѯ[��ҵ]�۸�ר��ʵ����
        // return new PriceBasePrepareParamOfActivity();
        //
        // }
        // else if (priceQueryType.equals(PriceResTypeEnum.FEE)) {
        // // ���ز�ѯ[����]�۸�ר��ʵ����
        // return new PriceBasePrepareParamOfFactor();
        //
        // }

        // û��Ĭ��ֵ���򷵻ؿ�
        return null;

    }

}
