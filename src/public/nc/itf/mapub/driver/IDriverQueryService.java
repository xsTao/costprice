package nc.itf.mapub.driver;

import java.util.List;

import nc.vo.bd.bdactivity.entity.BDActivityVO;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.driver.entity.DriverQueryCondition;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.org.OrgVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.resa.factor.FactorAsoaVO;

public interface IDriverQueryService {

    /**
     * ��ȡ����ϵ��
     * 
     * @param queryCondition
     *            ��ѯ�Ĳ���
     * @return ����ϵ��
     */
    AllocfacHeadVO[] queryAllocfacHeadVO(DriverQueryCondition queryCondition);

    /**
     * ��ȡ��ҵ
     * 
     * @param queryCondition
     *            ��ѯ����
     * @return ��ҵ
     */
    BDActivityVO[] queryActivity(DriverQueryCondition queryCondition);

    /**
     * ��ȡ����Ҫ��
     * 
     * @param queryCondition
     *            ��ѯ����
     * @param businessDate
     *            ҵ������
     * @return ����Ҫ��
     */
    List<FactorAsoaVO> queryFactor(DriverQueryCondition queryCondition, UFDate businessDate);

    // /**
    // * ��ȡ�۸������
    // *
    // * @param queryCondition
    // * ��ѯ����
    // * @return �۸������
    // */
    // List<PriceBaseHeadVO> queryPriceLibrary(DriverQueryCondition queryCondition);

    /**
     * ��ѯ������֯
     * 
     * @param queryCondition
     *            ��ѯ����
     * @return ������֯
     */
    List<OrgVO> querySaleOrgs(DriverQueryCondition queryCondition);

    /**
     * ͨ����֯����
     * 
     * @param pk_org
     *            ��֯����
     * @return �ɱ�����vo����
     */
    DriverVO[] queryDriverByOrg(String pk_org, String sortby);

}
