package nc.pubitf.mapub.allocfac.cm.allocate;

import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.mapub.allocfac.enumeration.AllocfacEnum;
import nc.vo.pub.BusinessException;

/**
 * @since v6.3
 * @version 2013-9-18 上午10:10:38
 * @author xionghuic
 */
public interface IAllocfacQueryForAllocateService {
    /**
     * 根据查询条件查询分配系数内容
     * 
     * @param pk_group
     *            集团
     * @param pk_org
     *            组织
     * @param facids
     *            分配系数ID
     * @param allcfacEnum
     *            分配内容枚举
     * @throws BusinessException
     *             业务异常
     * @return 分配系数内容
     */
    AllocfacItemVO[] queryAllocaFacForActivity(String pk_group, String pk_org, String[] facids,
            AllocfacEnum allocfacType) throws BusinessException;

    /**
     * 根据查询条件查询分配系数内容
     * 
     * @param pk_group
     *            集团
     * @param pk_org
     *            组织
     * @param facid
     *            分配系数ID
     * @param allcfacEnum
     *            分配内容枚举
     * @param facItemids
     *            分配系数子项ID
     * @throws BusinessException
     *             业务异常
     * @return 分配系数内容
     */
    AllocfacItemVO[] queryAllocaFac(String pk_group, String pk_org, String facid, AllocfacEnum allocfacType,
            String[] facItemids) throws BusinessException;
}
