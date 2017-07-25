package nc.pubitf.mapub.materialpricebase.pub;

import java.util.List;
import java.util.Map;

import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.vo.cmpub.business.aquireprice.AquirePriceParamVO;
import nc.vo.mapub.framework.entity.acquireprice.AcquirePriceParam;
import nc.vo.mapub.framework.entity.acquireprice.AcquirePriceReturn;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * �۸�⹫����ѯ�ӿ�
 * �۸���ѯ�ӿڣ��ṩ���ϼ۸���ҵ�۸񡢷��÷��ʵĲ�ѯ�ӿ�
 * ��������:2012-9-14
 * 
 * @author:xionghuic
 */
public interface IMaterialPriceBasePubQueryServiceForPrice {

    /**
     * �ṩ�Ľӿڣ����ݼ۸���ѯVO����ѯ�����ӱ�ļ۸�Map<key, �۸�>�������ֲ�ѯ����ʲô�۸�
     * ע�� 1����ѯVO��һ��Ҫ������Դ���ͣ�ResType������鲻����VO�ļ۸�
     * 2�����Map�е�key����PriceBaseQueryPriceUtil���е�getMapKey()������ȡ���۸����ûȡ������۸�Ϊnull
     * 
     * @param priceParamVOs
     *            ��ѯ����VO����
     * @return Map<String, UFDouble> Map<key, �۸�>
     * @throws BusinessException
     *             �쳣
     */
    Map<String, UFDouble> getAllPriceMap(AquirePriceParamVO[] priceParamVOs) throws BusinessException;

    /**
     * ���۸��ȡ��
     * 
     * @param priceParams
     * @return
     * @throws BusinessException
     */
    Map<AcquirePriceParam, AcquirePriceReturn> getPriceByMarAss(List<AcquirePriceParam> priceParams)
            throws BusinessException;

    /**
     * ��ѯ���ϼ۸���ͷVO
     * �ɱ����͡��ɱ�BOMʹ��
     * 
     * @param pkorg ҵ��Ԫ
     * @param group ����
     * @param date ����
     * @return VO����
     * @throws BusinessException
     */
    List<DefaultConstEnum> queryMaterialPriceBaseHeadVO(String[] pkorg, String group, UFDate date)
            throws BusinessException;
}
