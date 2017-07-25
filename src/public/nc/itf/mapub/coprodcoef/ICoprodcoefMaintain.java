package nc.itf.mapub.coprodcoef;

import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;
import nc.vo.pub.BusinessException;

/**
 * ��׼����ҵ������ӿ�,��ɾ�Ĳ�
 * 
 * @since 6.0
 * @version 2014-10-11 ����12:27:01
 * @author zhangshyb
 */
public interface ICoprodcoefMaintain {
    /**
     * ɾ������
     * 
     * @param vo
     * @throws Exception
     */
    void delete(CoprodcoefAggVO[] vo) throws BusinessException;

    /**
     * ��������
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    CoprodcoefAggVO[] insert(CoprodcoefAggVO[] vo) throws BusinessException;

    /**
     * ���µ���
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    CoprodcoefAggVO[] update(CoprodcoefAggVO[] fullBills) throws BusinessException;
}
