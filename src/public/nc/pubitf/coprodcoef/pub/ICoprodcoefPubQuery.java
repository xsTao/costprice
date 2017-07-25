package nc.pubitf.coprodcoef.pub;

import java.util.List;

import nc.vo.pub.BusinessException;

public interface ICoprodcoefPubQuery {
    /**
     * 根据产品查找联副系数
     *
     * @param pkOrg
     *            工厂
     * @param queryMsg
     *            产品+成本中心+核算要素
     * @return 指定联副系数结构集
     * @throws BusinessException
     *             异常
     * @author ligq
     */
    List<QueryCofficientInfoDTO> queryCoefficient(List<QueryDto> queryMsg) throws BusinessException;

    List<QueryCofficientInfoDTO> queryCoefficientForSca(List<QueryDto> queryMsg, String ccosttypeid)
            throws BusinessException;

}
