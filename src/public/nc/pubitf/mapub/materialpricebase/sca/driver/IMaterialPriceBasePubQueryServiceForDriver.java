package nc.pubitf.mapub.materialpricebase.sca.driver;

import java.util.List;

import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

/**
 * <b> �۸���ѯ�ӿ�</b>
 * <p>
 * �۸���ѯ�ӿڣ��ṩ���ϼ۸���ҵ�۸񡢷��÷��ʵĲ�ѯ�ӿ�
 * </p>
 * ��������:2012-9-14
 *
 * @author:xionghuic
 */
public interface IMaterialPriceBasePubQueryServiceForDriver {

    /**
     * ��ѯ��֯�µļ۸��
     *
     * @param pkOrg ��֯
     * @param fields ���ѯ���ֶ�
     * @return �۸���ͷVO
     * @throws BusinessException ҵ���쳣
     * @author xionghuic(for �ɱ�����)
     */
    List<MaterialPriceBaseHeadVO> queryMaterialPriceBaseHeadVOByOrg(String pkOrg, UFDate date) throws BusinessException;
}
