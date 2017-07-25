/**
 * 
 */
package nc.bs.mapub.allocfac.plugin.bpplugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

/**
 * ����ϵ����չ�����
 */
public enum AllocfacPluginPoint implements IPluginPoint {
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
        return "CM";
    }

    @Override
    public String getPoint() {
        return this.getClass().getName() + "." + this.name();
    }
}
