package nc.pubitf.mapub.driver.cm.allocate;

import nc.vo.mapub.driver.entity.DriverVO;

/**
 * �ɱ������ѯ
 * 
 * @author liyjf
 */
public interface IDriverQueryForAllocate {
    /**
     * ����������ѯ
     * 
     * @param PKs
     *            ��������
     * @return �ɱ�����vos
     */

    DriverVO[] queryDriverByPKs(String[] PKs);
}
