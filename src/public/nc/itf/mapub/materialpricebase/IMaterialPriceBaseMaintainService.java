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
     * 取价服务
     *
     * @author zhangchd
     * @param priceParamVO 取价对话框参数vo
     * @param primaryKey
     * @return 界面vo
     * @throws BusinessException
     */
    public MaterialPullPriceResult pullPrice(MaterialPullPriceAggVO priceParamVO, String[] primaryKey)
            throws BusinessException;
}
