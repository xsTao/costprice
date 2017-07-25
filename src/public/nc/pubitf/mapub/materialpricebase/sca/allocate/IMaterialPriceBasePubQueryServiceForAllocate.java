package nc.pubitf.mapub.materialpricebase.sca.allocate;

import java.util.Map;

import nc.vo.cmpub.framework.assistant.CMMarAssInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * ���ϼ۸�⹫����ѯ�ӿ�
 *
 * @since 6.36
 * @version 2014-11-28 ����9:28:36
 * @author zhangchd
 */
public interface IMaterialPriceBasePubQueryServiceForAllocate {

    /**
     * �ṩ�Ľӿڣ����ݼ۸���ѯ[����]�ӱ�ļ۸�
     *
     * @param priceBaseIDs �۸������
     * @param materialoid ����oid
     * @return Map<���ϣ�Map<�۸�⣬�۸�>>
     * @throws BusinessException
     */
    Map<CMMarAssInfoVO, Map<String, UFDouble>> getStuffPrice(String[] priceBaseIDs, CMMarAssInfoVO[] materialoid)
            throws BusinessException;

}
