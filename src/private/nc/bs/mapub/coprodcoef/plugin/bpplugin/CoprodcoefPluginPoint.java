package nc.bs.mapub.coprodcoef.plugin.bpplugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

/**
 * ��׼���ݵ���չ�����
 */
public enum CoprodcoefPluginPoint implements IPluginPoint {
    /**
     * ����
     */
    APPROVE,
    /**
     * ɾ��
     */
    DELETE,
    /**
     * ����
     */
    INSERT,

    /**
     * ����
     */
    SEND_APPROVE,

    /**
     * ȡ�����
     */
    UNAPPROVE,

    /**
     * �ջ�
     */
    UNSEND_APPROVE,

    /**
     * ����
     */
    UPDATE;

    @Override
    public String getComponent() {
        return "STRD";
    }

    @Override
    public String getModule() {
        return "SCMPUB";
    }

    @Override
    public String getPoint() {
        return this.getClass().getName() + "." + this.name();
    }

}
