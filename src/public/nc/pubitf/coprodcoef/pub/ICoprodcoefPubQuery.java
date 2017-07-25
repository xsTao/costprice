package nc.pubitf.coprodcoef.pub;

import java.util.List;

import nc.vo.pub.BusinessException;

public interface ICoprodcoefPubQuery {
    /**
     * ���ݲ�Ʒ��������ϵ��
     *
     * @param pkOrg
     *            ����
     * @param queryMsg
     *            ��Ʒ+�ɱ�����+����Ҫ��
     * @return ָ������ϵ���ṹ��
     * @throws BusinessException
     *             �쳣
     * @author ligq
     */
    List<QueryCofficientInfoDTO> queryCoefficient(List<QueryDto> queryMsg) throws BusinessException;

    List<QueryCofficientInfoDTO> queryCoefficientForSca(List<QueryDto> queryMsg, String ccosttypeid)
            throws BusinessException;

}
