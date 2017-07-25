/**
 * 
 */
package nc.itf.mapub.allocfac;

import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.pub.BusinessException;

/**
 * ����ϵ����ɾ�Ĳ�����ӿ�
 */
public interface IAllocfacMaintainService {
    /**
     * ����ϵ���Ĳ������
     * 
     * @param vo
     *            ���ĵ��ۺ�vo
     * @return
     *         ���ĵ�
     * @throws BusinessException
     */
    AllocfacAggVO[] insert(AllocfacAggVO[] vos) throws BusinessException;

    /**
     * ����ϵ���޸Ĳ���
     * 
     * @return StuffAggVO[]
     */
    AllocfacAggVO[] update(AllocfacAggVO[] vos) throws BusinessException;

    /**
     * ɾ������ϵ��
     * 
     * @param vos
     * @throws BusinessException
     */
    void allocDelete(AllocfacAggVO[] vos) throws BusinessException;

    public String getDatabaseCFG(boolean needDetail) throws BusinessException;
}
