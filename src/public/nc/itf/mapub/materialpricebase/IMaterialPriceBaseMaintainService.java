package nc.itf.mapub.materialpricebase;

import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPullPriceAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPullPriceResult;
import nc.vo.pub.BusinessException;

public interface IMaterialPriceBaseMaintainService {
    public void delete(MaterialPriceBaseAggVO[] vos) throws BusinessException;

    public MaterialPriceBaseAggVO[] insert(MaterialPriceBaseAggVO[] vos) throws BusinessException;

    public MaterialPriceBaseAggVO[] importInsert(MaterialPriceBaseAggVO[] vos) throws BusinessException;

    public MaterialPriceBaseAggVO[] update(MaterialPriceBaseAggVO[] vos) throws BusinessException;

    public MaterialPriceBaseAggVO[] lock(MaterialPriceBaseAggVO[] vos) throws BusinessException;

    public MaterialPriceBaseAggVO[] deblocking(MaterialPriceBaseAggVO[] vos) throws BusinessException;

    public MaterialPriceBaseAggVO[] takePrice(MaterialPriceBaseAggVO[] vos) throws BusinessException;

    /**
     * ȡ�۷���
     *
     * @author zhangchd
     * @param priceParamVO ȡ�۶Ի������vo
     * @param primaryKey
     * @return ����vo
     * @throws BusinessException
     */
    public MaterialPullPriceResult pullPrice(MaterialPullPriceAggVO priceParamVO, String[] primaryKey)
            throws BusinessException;
}
