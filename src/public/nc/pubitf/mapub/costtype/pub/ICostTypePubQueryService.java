package nc.pubitf.mapub.costtype.pub;

import java.util.Map;

import nc.vo.pub.ISuperVO;

public interface ICostTypePubQueryService {

    /**
     * add by xuyanga on 2011-06-16
     * �õ������µ�Ĭ����Ч�ĳɱ����ͣ�
     *
     * @param pk_org
     * @param accPeriod
     * @return
     */
    ISuperVO getDefaultCostType(String pk_org, String accPeriod);

    /**
     * add by zhangweix on 2012-04-23
     * �õ������µ�Ĭ����Ч�ĳɱ�����: �߻�����Ʋ�ѯ
     *
     * @param pk_org
     * @param accPeriod
     * @return
     */
    ISuperVO getDefaultCostType_C(String pk_org, String accPeriod);

    /**
     * add by xuyanga on 2011-06-16
     * �õ������µ���Ч���Ѽ�������гɱ����ͣ�
     *
     * @param pk_org
     * @param accPeriod
     * @return
     */
    ISuperVO[] getAllCostType(String pk_org, String accPeriod);

    /**
     * ����PK��ѯ�ɱ�����VO
     *
     * @param costTypePK
     *            String
     * @return ISuperVO
     */
    ISuperVO getCostTypeVoByPK(String costTypePK);

    /**
     * ����PK��ѯ�ɱ�����id��code��map
     *
     * @param costTypePK
     *            String
     * @return ISuperVO
     */
    Map<String, String> getCostTypeMapByPK(String[] costTypePKS);

    /**
     * ������Ч��ʧЧ������ָ�������µ�Ĭ�ϳɱ�����
     *
     * @param pk_org ����
     * @param cPeriod ��ǰ�ڼ�
     * @return �ɱ�����VO����δ�鵽��Ϊnull
     */
    ISuperVO getDefaultCostTypeByPeriod(String pk_org, String cPeriod);

    /**
     * ������Ч��ʧЧ������ָ�������µ�Ĭ�ϳɱ�����
     *
     * @param pk_org ����
     * @param cPeriod ��ǰ�ڼ�
     * @return �ɱ�����VO����δ�鵽��Ϊnull
     */
    Boolean isCostTypeLegal(String pk_org, String cPeriod, String costtype, String pk_group);
}
