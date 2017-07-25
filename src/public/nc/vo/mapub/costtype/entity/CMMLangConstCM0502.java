package nc.vo.mapub.costtype.entity;

/**
 * �ɱ����ͽڵ��� ����
 *
 * @author ��¼ 2010-04-15
 */
public class CMMLangConstCM0502 {

    /**
     * ���ʧЧ����
     */
    public static final String MAX_DATE = "9999-12-31 23:59:59";

    /**
     * ��ʾʧЧ ��ťTips��
     */
    public static final String GET_BTN_LEGAL() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0092")/* @res "��ʾʧЧ" */;
    }

    /**
     * ��ʾʧЧ ��ťTips��
     */
    public static final String GET_ERR_LEGAL() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0093")/*
         * @res
         * "����[��Ч�ڼ�]��������[ʧЧ�ڼ�]����"
         */;
    }

    /**
     * ��ǰϵͳ����ڼ�Ϊ��ʱ����ʾ��Ϣ��
     */
    public static final String GET_ERR_ACCTIMENULL() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0094")/*
         * @res
         * "��������ϵͳ����ڼ��쳣��"
         */;
    }

    /**
     * ��ǰϵͳ����ڼ�Ϊ��ʱ����ʾ��Ϣ��
     */
    public static final String GET_ERRO_BDEFAULT() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0095")/*
         * @res
         * "����ʧ�ܣ���������Ч���������ҽ���һ���ɱ���������ΪĬ�ϣ�"
         */;
    }

    /**
     * ɾ��ʧ�ܣ���������Ч����ʱ��ɾ������Ҫ����һ����Ч��Ĭ�ϵĳɱ�����
     */
    public static final String GET_ERRO_DEFAULT() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0096")/*
         * @res
         * "ɾ��ʧ�ܣ���������Ч����ʱ��ɾ������Ҫ����һ����Ч��Ĭ�ϵĳɱ����ͣ�"
         */;
    }

    /**
     * ɾ���ɹ�
     */
    public static final String GET_ERRO_DELETE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0297")/*
         * @res
         * "ɾ���ɹ�"
         */;
    }

    /**
     * �Ѿ�ʧЧ�ĳɱ������а���Ĭ�ϳɱ�����
     */
    public static final String GET_ERRO_INLEGALHASDEFAULT() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0098")/*
         * @res
         * "����ʧ�ܣ���ѡ���Ĭ�ϳɱ������Ѿ�ʧЧ��������ѡ��"
         */;
    }

    /**
     * ���ݲ���������кţ���������Ĵ�����Ϣ��
     *
     * @param erroStr
     *            ����������к�
     * @return String �����Ĵ�����Ϣ
     */
    public static final String GET_ERRORLEGALSTR(String erroStr) {
        String erroStrFianl =
                CMMLangConstCM0502.GET_ERR_LEGAL() + erroStr
                + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0099")/*
                 * @res
                 * "(��)"
                 */;
        return erroStrFianl;
    }

    /**
     * ����ڼ����
     */
    public static final String GET_ACCOUNTCALENDAR_ERROR() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0100")/*
         * @res
         * "��ǰϵͳδ��������ڼ䣬���ȴ�������ڼ䣡"
         */;
    }

    /**
     * �ܿط�Χ
     */
    public static final String GET_BTN_CTRLSCOPE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0101")/* @res "�ܿط�Χ" */;
    }

    /**
     * ����BOM
     */
    public static final String GET_BTN_TOBOM() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "01014364-0102")/* @res "�ɱ�BOM" */;
    }

    /**
     * Ҫ�������϶��ձ�ͺ���Ҫ�ر�ƥ�䣬���������á�
     */
    public static final String GET_ERROR_DIF_DOCVIEW_FACTORCHART(String error) {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0346", null, new String[] {
            error
        });/*
            * @res
            * "Ҫ�������϶��ձ�ͺ���Ҫ�ر�ƥ�䣬���������á�"
            */

    }

    /**
     * Ҫ�������϶��ձ�δ�ҵ�Ҫ�ر�!
     */
    public static final String GET_NODOCVIEWFACTOR(String error) {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0349", null, new String[] {
            error
        });/*
            * @res
            * "Ҫ�������϶��ձ�δ�ҵ�Ҫ�ر�!"
            */

    }
}
