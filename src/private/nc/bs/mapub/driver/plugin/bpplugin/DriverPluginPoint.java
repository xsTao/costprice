package nc.bs.mapub.driver.plugin.bpplugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

/**
 * ��׼���ݵ���չ�����
 */
public enum DriverPluginPoint implements IPluginPoint {
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
        return "basedoc";
    }

    @Override
    public String getModule() {
        return "cm";
    }

    @Override
    public String getPoint() {
        return this.getClass().getName() + "." + this.name();
    }

}
