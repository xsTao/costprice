package nc.itf.mapub.materialpricebase;

/**
 * <b> �ڼ۸���в�ѯ�ӱ�۸�ʱ������׼���ӿ� </b>
 * <p>
 * ��Ҫ���ر�����SQL�������Ҫ��ѯ��Fields
 * </p>
 * 
 * @since 6.0
 * @version 2014-9-23 ����2:30:07
 * @author baoxina
 */
public interface IMaterialPriceBasePrepareParam {

    /**
     * ���ر���
     * 
     * @return ����
     */
    String getTableName();

    /**
     * ���ز�ѯ����ע��String[]���һ������Ǽ۸�/����
     * 
     * @return ������
     */
    String[] getFieldsForSQL();
}
