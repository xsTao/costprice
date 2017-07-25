package nc.itf.mapub.driver;

import nc.vo.mapub.driver.entity.DriverAggVO;
import nc.vo.pub.BusinessException;

public interface IDriverMaintainService {

    void deleteDriver(DriverAggVO[] vos) throws BusinessException;

    DriverAggVO[] insertDriver(DriverAggVO[] vos) throws BusinessException;

    DriverAggVO[] updateDriver(DriverAggVO[] vos) throws BusinessException;
}
