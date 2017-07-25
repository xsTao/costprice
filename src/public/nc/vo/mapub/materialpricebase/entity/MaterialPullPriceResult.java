package nc.vo.mapub.materialpricebase.entity;

import java.io.Serializable;

import nc.vo.mapub.acquirepricelog.entity.AcquirePriceLogVO;

public class MaterialPullPriceResult implements Serializable {

    private static final long serialVersionUID = 2461804222280565119L;

    private MaterialPriceBaseAggVO[] materialPriceBaseAggVO;

    private String resultInfo; // 结果信息

    private AcquirePriceLogVO[] acquirePriceLogVO; // 错误日志

    public MaterialPriceBaseAggVO[] getMaterialPriceBaseAggVO() {
        return this.materialPriceBaseAggVO;
    }

    public void setMaterialPriceBaseAggVO(MaterialPriceBaseAggVO[] materialPriceBaseAggVO) {
        this.materialPriceBaseAggVO = materialPriceBaseAggVO;
    }

    public String getResultInfo() {
        return this.resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    public AcquirePriceLogVO[] getAcquirePriceLogVO() {
        return this.acquirePriceLogVO;
    }

    public void setAcquirePriceLogVO(AcquirePriceLogVO[] acquirePriceLogVO) {
        this.acquirePriceLogVO = acquirePriceLogVO;
    }

}
