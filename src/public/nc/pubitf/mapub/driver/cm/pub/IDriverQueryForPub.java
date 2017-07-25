package nc.pubitf.mapub.driver.cm.pub;

import java.util.List;

import nc.vo.pub.BusinessException;

/**
 * �ɱ����򹫹��ӿ�
 * 
 * @since v6.3
 * @version 2013-7-9 ����02:41:12
 * @author liyjf
 */
public interface IDriverQueryForPub {

    /**
     * �����Ƿ񱻳ɱ���������
     * 
     * @param pk_org
     *            ����
     * @param driverType
     *            �������ͣ�����CMDriverParameterEnum
     * @param idList
     *            ����IDList
     * @return
     * @throws BusinessException
     */
    boolean isUsedInDriver(String pk_org, String driverType, List<String> idList) throws BusinessException;
}
