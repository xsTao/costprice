package nc.vo.mapub.materialpricebase.entity;

public class CMMLangConstMaterialPriceBase {
    // �س�����
    public final static String CRLF = "\r\n";

    /**
     * ����[%s]��Ӧ�ĸ�������%s�Ѿ����ã�����¼��ֵ�����޸ġ�
     */
    public static final String getMustMaterAssMsg() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0000")/*
         * @res
         * "����[%s]��Ӧ�ĸ�������%s�Ѿ����ã�����¼��ֵ�����޸ġ�"
         */;// 01014364-0133=����[%s]��Ӧ�ĸ�������%s�Ѿ����ã�����¼��ֵ�����޸ġ�
    }

    /**
     * ��ѯ�����������˶����Ϊ{0}�Ĳ�ѯ����!
     *
     * @param name
     * @return
     */
    public static final String getERR_QUERY_CONDITION_EXISTS(String name) {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0001", null, new String[] {
                name
        }); // 01014364-0106=��ѯ�����������˶����Ϊ{0}�Ĳ�ѯ����!
    }

    /**
     * ҵ��Ԫ
     */
    public static final String getFAC() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0002");
    }

    /**
     * �����ֶ�[����]Ӧ�����㣡 �׳��쳣��Ϣ��
     **/
    public static final String GET_ERRO_BODYITEMNPRICEZERO() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0003");/*
         * @res
         * "�����ֶ�[����]Ӧ�����㣡"
         */
    }

    /**
     * ������ʲ���Ϊ�� �׳��쳣��Ϣ��
     **/
    public static final String GET_ERRO_BODYITEMNRATEZERO() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0004");/*
         * @res
         * "�����ֶη��ʲ���Ϊ�㣡"
         */
    }

    /**
     * ��ͷ�ֶ�%s����Ϊ��
     */
    public static final String getNULL_VALIDATE_HEAD_ITEM(String message) {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0005", null, new String[] {
                message
        });
        // return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("1014364_0", "01014364-0110")/* @res
        // "��ͷ�ֶ�%s����Ϊ��" */;
    }

    /**
     * �����ֶ�%s����Ϊ��
     */
    public static final String getNULL_VALIDATE_BODY_ITEM1(String message) {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0006", null, new String[] {
                message
        });
        // return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("1014364_0", "01014364-0115")/* @res
        // "�����ֶ�%s����Ϊ��" */;
    }

    /**
     * �������[�۸���Դ]Ϊ�ֹ�¼��ʱ���۲���Ϊ�գ����޸ģ�
     */
    public static final String getBodyPriceIsNotEmpty() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0007")/*
         * @res
         * "�������[�۸���Դ]Ϊ�ֹ�¼��ʱ���۲���Ϊ�գ����޸ģ�"
         */;
    }

    /**
     * �������[�۸���Դ]Ϊ�գ���ͷ����[�۸���Դ]Ϊ�ֹ�¼��ʱ�����۲���Ϊ�գ����޸ģ�
     */
    public static final String getHeadandBodyPriceIsNotEmpty() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0008")/*
         * @res
         * "�������[�۸���Դ]Ϊ�գ���ͷ����[�۸���Դ]Ϊ�ֹ�¼��ʱ�����۲���Ϊ�գ����޸ģ�"
         */;
    }

    /**
     * �۸�����
     */
    public static final String getVPRICEBASE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0009")/* @res "�۸�����" */;
    }

    /**
     * �۸������
     */
    public static final String getVPRICENAME() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0010")/* @res "�۸������" */;
    }

    /**
     * ����
     */
    public static final String getCRENCYID() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0011")/* @res "����" */;
    }

    /**
     * ��Чʱ��
     */
    public static final String getDBEGINDATE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0012")/* @res "��Чʱ��" */;
    }

    /**
     * ʧЧʱ��
     */
    public static final String getDENDDATE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0013")/* @res "ʧЧʱ��" */;
    }

    /**
     * ���ϱ���
     */
    public static final String getCMATERIALID() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0014")/* @res "���ϱ���" */;
    }

    /**
     * ����
     */
    public static final String getNPRICE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0015")/* @res "����" */;
    }

    /**
     * �ɱ����ı���
     */
    public static final String getCCOSTCENTERID() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0016")/* @res "�ɱ����ı���" */;
    }

    /**
     * ����Ҫ�ر���
     */
    public static final String getCELEMENTID() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0017")/* @res "����Ҫ�ر���" */;
    }

    /**
     * ����
     */
    public static final String getNRATE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0018")/* @res "����" */;
    }

    /**
     * �������ݲ���Ϊ�գ����飺
     */
    public static final String getNULL_VALIDATE_H() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0019")/*
         * @res
         * "�������ݲ���Ϊ�գ����飺"
         */;
    }

    /**
     * ʧЧ���ڲ�������ʧЧ����
     **/
    public static final String GET_ERRO_BEGIN_ENDDATE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0020")/*
         * @res
         * "ʧЧ���ڲ���������Ч���ڣ�"
         */;
    }

    /**
     * ��������������֯����Ϊ��
     */
    public static final String GET_HIT_NOFINANCE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0021")/* @res "��������������֯����Ϊ��" */;
    }

    /**
     * ������Ӧ�Ĳ�����֯���˲�����Ϊ��
     */
    public static final String GET_HIT_NOSCHEME() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0022")/*
         * @res
         * "������Ӧ�Ĳ�����֯���˲�����Ϊ��"
         */;
    }

    /**
     * ����-û���ҵ�������֯��Ӧ�Ĳ�����֯�����˲�
     */
    public static final String ERR_NO_ACCOUNTBOOK = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
            "03810006-0023")/* @res "û���ҵ�������֯��Ӧ�Ĳ�����֯�����˲�" */;

    /**
     * ����-û���ҵ�������֯��Ӧ�Ĳ�����֯�����˲��ı�λ�ң����ܻ�ȡ�ұ𾫶�
     */
    public static final String ERR_NO_ACCOUNTBOOK_SCALE = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "3810006_0", "03810006-0024")/* @res "û���ҵ�������֯��Ӧ�Ĳ�����֯�����˲��ı�λ�ң����ܻ�ȡ�ұ𾫶�" */;

    /**
     * ��Ч���ڣ�������ʾ�ֶΡ�
     */
    public static final String GET_REFFIELDNAME_BEGINDATE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC000-0002942")/* @res "��Ч����" */;
    }

    /**
     * ʧЧ���ڣ�������ʾ�ֶΡ�
     */
    public static final String GET_REFFIELDNAME_ENDDATE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common", "UC000-0001402")/* @res "ʧЧ����" */;
    }

    /**
     * �۸�⣬������ʾ���⡣
     */
    public static final String GET_REFTITLE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0025")/* @res "���ϼ۸��" */;
    }

    /**
     * ��Ҫ����VO����Ϊ��
     */
    public static final String CHECK_NULL_VO() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0026")/* @res "��Ҫ����VO����Ϊ��" */;
    }

    /**
     * �۸���ѱ��ɱ��������ã����ܽ��иò���
     */
    public static final String getUSED_IN_DRIVER() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0027")/*
         * @res
         * "�۸���ѱ��ɱ��������ã����ܽ��иò���"
         */;
    }

    /**
     * �۸�����Ϊ{0}�ļ۸���Ѿ����������������������
     */
    public static final String LOCKED_PRICEBASE(String message) {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0028", null, new String[] {
                message
        })/* @res "�۸��" */;
    }

    /**
     * �۸�����Ϊ{0}�ļ۸��δ�����������ⶳ��
     */
    public static final String UNLOCKED_PRICEBASE(String message) {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0029", null, new String[] {
                message
        })/* @res "�۸��" */;
    }

    /**
     * �޷�ȡ�����ϱ���Ϊ{0}�ļ۸�
     */
    public static final String HVAENONEPRICEMATRID(String message) {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0030", null, new String[] {
                message
        })/* @res "�޷�ȡ�����ϱ���Ϊ{0}�ļ۸�" */;
    }

    /**
     * @return ��%s�У�
     */
    public static String getMsgRowNum() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0031")/* @res "��%s�У�" */;
    }

    /**
     * �����ظ������޸ģ�
     */
    public static final String ITEMS_REPEAT_INFO() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0032")/* @res "�����ظ������޸ģ�" */;
        // return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("1014364_0", "01014364-0129", null, new String[]
        // {
        // message
        // })/* @res {0}�����ظ������޸ģ� */;
    }

    /**
     * @return �۸������Ѵ��ڣ����޸ģ�
     */
    public static String getMSGCMATERIALPRICE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0033")/*
         * @res
         * "�۸������Ѵ��ڣ����޸ģ�"
         */;
    }

    /**
     * @return �ر�
     */
    public static String getMSG1() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0034")/*
         * @res
         * "�ر�"
         */;
    }

    /**
     * @return ȡ�۴�����Ϣ
     */
    public static String getMSG2() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0035")/*
         * @res
         * "ȡ�۴�����Ϣ"
         */;
    }

    /**
     * @return ȷ��
     */
    public static String getMSG3() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0036")/*
         * @res
         * " ȷ��"
         */;
    }

    /**
     * @return ����
     */
    public static String getMSG4() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0037")/*
         * @res
         * " ����"
         */;
    }

    /**
     * @return ����
     */
    public static String getMSG5() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0038")/*
         * @res
         * " ����"
         */;
    }

    /**
     * @return �õ�
     */
    public static String getMSG6() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0039")/*
         * @res
         * " �õ�"
         */;
    }

    /**
     * @return ȡ��
     */
    public static String getMSG7() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0040")/*
         * @res
         * " ȡ��"
         */;
    }

    /**
     * @return ɾ��
     */
    public static String getMSG8() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0041")/*
         * @res
         * " ɾ��"
         */;
    }

    /**
     * @return ����
     */
    public static String getMSG9() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0042")/*
         * @res
         * " ����"
         */;
    }

    /**
     * @return ������
     */
    public static String getMSG10() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0043")/*
         * @res
         * " ������"
         */;
    }

    /**
     * @return �ö�
     */
    public static String getMSG11() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0044")/*
         * @res
         * " �ö�"
         */;
    }

    /**
     * @return ����
     */
    public static String getMSG12() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0045")/*
         * @res
         * " ����"
         */;
    }

    /**
     * @return ��֯
     */
    public static String getMSG13() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0046")/*
         * @res
         * " ��֯"
         */;
    }

    /**
     * @return �ɱ���
     */
    public static String getMSG14() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0047")/*
         * @res
         * " �ɱ���"
         */;
    }

    /**
     * "[ �۸���Դ ]Ϊ�ֹ�¼��ģ������в���¼��[ �۸���Դ ]Ϊ���ֹ�¼��ģ����޸ģ�"
     */
    public static final String getMSG15() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0048");
        // * @res
        // [ �۸���Դ ]Ϊ�ֹ�¼��ģ������в���¼��[ �۸���Դ ]Ϊ���ֹ�¼��ģ����޸ģ� */;
    }

    /**
     * "��"
     */
    public static final String getMSG16() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0049");
        // * @res
        // �� */;
    }

    /**
     * "�У�"
     */
    public static final String getMSG17() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0050");
        // * @res
        // �У� */;
    }

    /**
     * "[ �۸���Դ ]Ϊ���ֹ�¼����߷Ǳ�׼�ɱ���[ ҵ��Ԫ ]�ֶα���¼��ֵ �����޸ģ�"
     */
    public static final String getMSG18() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0051");
        // * @res
        // [ �۸���Դ ]Ϊ���ֹ�¼����߷Ǳ�׼�ɱ���[ ҵ��Ԫ ]�ֶα���¼��ֵ �����޸ģ� */;
    }

    /**
     * " [ �۸���Դ ]�����ظ������޸ģ�"
     */
    public static final String getMSG19() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0052");
        // * @res
        // [ �۸���Դ ]�����ظ������޸ģ� */;
    }

    /**
     * "  �۸���Դ"
     */
    public static final String getMSG20() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0053");
        // * @res
        // �۸���Դ */;
    }

    /**
     * " ȡ��"
     */
    public static final String getMSG21() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0054");
        // * @res
        // ȡ�� */;
    }

    /**
     * " ��ѡ��һ���۸�����ȡ�ۣ�"
     */
    public static final String getMSG22() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0055");
        // * @res
        // ��ѡ��һ���۸�����ȡ�ۣ� */;
    }

    /**
     * " ���棡ȡ��̫Ƶ��"
     */
    public static final String getMSG23() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0056");
        // * @res
        // ���棡ȡ��̫Ƶ�� */;
    }

    /**
     * " ȡ���У����Ժ�"
     */
    public static final String getMSG24() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0057");
        // * @res
        // ȡ���У����Ժ� */;
    }

    /**
     * " ȡ��ʧ�ܣ�"
     */
    public static final String getMSG25() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0058");
        // * @res
        // ȡ��ʧ�ܣ� */;
    }

    /**
     * "������������Ѿ��������޸ģ���ˢ�½���"
     */
    public static final String getMSG26() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0059");
        // * @res
        // ������������Ѿ��������޸ģ���ˢ�½��� */;
    }

    /**
     * "��ʼ�ڼ�ӦС�ڽ�ֹ�ڼ䣬���޸ģ�"
     */
    public static final String getMSG27() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0060");
        // * @res
        // ��ʼ�ڼ�ӦС�ڽ�ֹ�ڼ䣬���޸ģ� */;
    }

    /**
     * "�����ֶ�ֵ����Ϊ�գ�"
     */
    public static final String getMSG28() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0061");
        // * @res
        // �����ֶ�ֵ����Ϊ�գ� */;
    }

    /**
     * "�۸���ԴΪ�ֹ�¼�벻��Ҫȡ��!"
     */
    public static final String getMSG29() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0062");
        // * @res
        // �۸���ԴΪ�ֹ�¼�벻��Ҫȡ��! */;
    }

    /**
     * "ȡ�۲���"
     */
    public static final String getMSG30() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0063");
        // * @res
        // ȡ�۲���*/;
    }

    /**
     * "������֯û���������˲�!"
     */
    public static final String getMSG31() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0064");
        // * @res
        // ������֯û���������˲�!*/;
    }

    /**
     * "�������Ķ�Ӧ�Ĳ�����֯û���������˲�! "
     */
    public static final String getMSG32() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0065");
        // * @res
        // �������Ķ�Ӧ�Ĳ�����֯û���������˲�! */;
    }

    /**
     * "��ȡ��λ��ʧ�ܡ�ҵ��Ԫ������в��񡢹�������������ְ�ܣ����޸ģ� "
     */
    public static final String getMSG33() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0066");
        // * @res
        // ��ȡ��λ��ʧ�ܡ�ҵ��Ԫ������в��񡢹�������������ְ�ܣ����޸ģ� */;
    }

    /**
     * "[ �۸���Դ ]Ϊ���ֹ�¼����߷Ǳ�׼�ɱ���[ ҵ��Ԫ ]�ֶα���¼��ֵ �����޸ģ�"
     */
    public static final String getMSG34() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0348");
        // * @res
        // [ �۸���Դ ]Ϊ�ֹ�¼����߱�׼�ɱ���[ ҵ��Ԫ ]�ֶβ���¼��ֵ �����޸ģ� */;
    }

}
