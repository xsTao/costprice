package nc.pubitf.mapub.allocfac.cm.allocate;

import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.mapub.allocfac.enumeration.AllocfacEnum;
import nc.vo.pub.BusinessException;

/**
 * @since v6.3
 * @version 2013-9-18 ����10:10:38
 * @author xionghuic
 */
public interface IAllocfacQueryForAllocateService {
    /**
     * ���ݲ�ѯ������ѯ����ϵ������
     * 
     * @param pk_group
     *            ����
     * @param pk_org
     *            ��֯
     * @param facids
     *            ����ϵ��ID
     * @param allcfacEnum
     *            ��������ö��
     * @throws BusinessException
     *             ҵ���쳣
     * @return ����ϵ������
     */
    AllocfacItemVO[] queryAllocaFacForActivity(String pk_group, String pk_org, String[] facids,
            AllocfacEnum allocfacType) throws BusinessException;

    /**
     * ���ݲ�ѯ������ѯ����ϵ������
     * 
     * @param pk_group
     *            ����
     * @param pk_org
     *            ��֯
     * @param facid
     *            ����ϵ��ID
     * @param allcfacEnum
     *            ��������ö��
     * @param facItemids
     *            ����ϵ������ID
     * @throws BusinessException
     *             ҵ���쳣
     * @return ����ϵ������
     */
    AllocfacItemVO[] queryAllocaFac(String pk_group, String pk_org, String facid, AllocfacEnum allocfacType,
            String[] facItemids) throws BusinessException;
}
