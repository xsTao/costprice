package nc.itf.mapub.costtype;

import java.util.Set;

import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.uif2.LoginContext;

/**
 * �ɱ����ͷ���ӿ�
 * <p>
 * �ɱ����ͷ���ӿ�
 */
public interface ICostTypeService {

    /**
     * �����Ƿ���Ч��ȡ�ɱ���������
     *
     * @param pk_group
     *            ����
     * @param pk_org
     *            ��֯
     * @param currentDate
     *            ��ǰ����
     * @param isLegal
     *            �Ƿ���Ч true����Ч false����Ч
     * @return �ɱ���������
     */
    ISuperVO[] queryCostType(LoginContext context, UFDate currentDate, UFBoolean isLegal) throws BusinessException;

    /**
     * �����Ƿ���Ч��ȡ�ɱ���������
     *
     * @param pk_group
     *            ����
     * @param pk_org
     *            ��֯
     * @param date
     *            ǰ̨�ͻ�������
     * @param isLegal
     *            �Ƿ���Ч true����Ч false����Ч
     * @return �ɱ���������
     */
    ISuperVO[] queryCostTypeByDate(LoginContext context, UFDate date, UFBoolean isLegal);

    BatchOperateVO batchSaveCostTypeVos(BatchOperateVO batchVO) throws Exception;

    ISuperVO[] queryCostTypeByDataVisibilitySetting(LoginContext context) throws Exception;

    /**
     * selectByWhereSql
     *
     * @param whereSql
     *            sql
     * @param clz
     *            clz
     * @return data
     * @throws Exception
     *             Exception
     */
    ISuperVO[] queryCostTypeVOsByWhereSql(String whereSql) throws Exception;

    // �жϼ۸���Ƿ����ã����÷���true��δ�����÷���false
    public boolean isReferenced(String[] pks);

    /**
     * �жϸóɱ��������Ƿ����bom����
     */
    public Set<String> haveDate();

    /**
     * �жϸóɱ��������Ƿ����bom�������Ѽ��������
     */
    public Set<String> haveCalcDate();

    /**
     * �жϲ��ϼ۸���Դ�Ƿ��޸�
     */
    public boolean isMaterialSrcChanged(String ccosttypeid, String scrapfactor, String shrinkfactor,
            String pk_elementsystem, String pk_factorchart, String pk_materialdocview, String vmaterialpricesourcenum);

    /**
     * ���Ƿ�Ʒϵ��scrapfactor���������ϵ��shrinkfactor������Ҫ����ϵpk_elementsystem������Ҫ�ر�pk_factorchart��Ҫ�������϶��ձ�pk_materialdocview�Ƿ��޸�
     */
    public boolean isValueChanged(String ccosttypeid, String scrapfactor, String shrinkfactor, String pk_elementsystem,
            String pk_factorchart, String pk_materialdocview);
}
