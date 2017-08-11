/**
 *
 */
package nc.vo.mapub.costpricebase.info;

import java.util.ArrayList;
import java.util.List;

import nc.cmpub.business.entity.info.CMEntityNumInfoVO;
import nc.cmpub.business.entity.info.CMEntityVOInfo;
import nc.vo.mapub.costpricebase.entity.CostPriceBodyVO;
import nc.vo.pub.SuperVO;

/**
 * 实体描述文件
 * 
 * @since v6.3
 * @version 2017年8月9日 上午11:25:43
 * @author Administrator
 */
public class CostPriceBodyVOInfo extends CMEntityVOInfo {

    /**
     * 金额集合对信息
     */
    private final List<String> moneyList = new ArrayList<String>();

    public CostPriceBodyVOInfo() {
        this.moneyList.add(CostPriceBodyVO.DPRICE);
    }

    /**
     * 获得 moneyList 的属性值
     *
     * @return the moneyList
     * @since 2017年8月9日
     * @author Administrator
     */
    public List<String> getMoneyList() {
        return this.moneyList;
    }

    /*
     * (non-Javadoc)
     * @see nc.cmpub.business.entity.info.CMEntityVOInfo#getVO()
     */
    @Override
    public SuperVO getVO() {
        // TODO Auto-generated method stub
        return new CostPriceBodyVO();
    }

    /*
     * 实体在父实体的属性名
     * @see nc.cmpub.business.entity.info.CMEntityVOInfo#getTabCode()
     */
    @Override
    public String getTabCode() {
        // TODO Auto-generated method stub
        return "itempks";
    }

    /*
     * (non-Javadoc)
     * @see nc.cmpub.business.entity.info.CMEntityVOInfo#getMatField()
     */
    @Override
    public String getMatField() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see nc.cmpub.business.entity.info.CMEntityVOInfo#getUnitField()
     */
    @Override
    public String getUnitField() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see nc.cmpub.business.entity.info.CMEntityVOInfo#getAstunitField()
     */
    @Override
    public String getAstunitField() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see nc.cmpub.business.entity.info.CMEntityVOInfo#getChangeRateField()
     */
    @Override
    public String getChangeRateField() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see nc.cmpub.business.entity.info.CMEntityVOInfo#getFreePrefix()
     */
    @Override
    public String getFreePrefix() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see nc.cmpub.business.entity.info.CMEntityVOInfo#getDefPrefix()
     */
    @Override
    public String getDefPrefix() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see nc.cmpub.business.entity.info.CMEntityVOInfo#getProductField()
     */
    @Override
    public String getProductField() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see nc.cmpub.business.entity.info.CMEntityVOInfo#getVendorField()
     */
    @Override
    public String getVendorField() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see nc.cmpub.business.entity.info.CMEntityVOInfo#getCustomerField()
     */
    @Override
    public String getCustomerField() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see nc.cmpub.business.entity.info.CMEntityVOInfo#getProjectField()
     */
    @Override
    public String getProjectField() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see nc.cmpub.business.entity.info.CMEntityVOInfo#getEntityNumList()
     */
    @Override
    public List<CMEntityNumInfoVO> getEntityNumList() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see nc.cmpub.business.entity.info.CMEntityVOInfo#getSubTabCode()
     */
    @Override
    public String[] getSubTabCode() {
        // TODO Auto-generated method stub
        return null;
    }

}
