package nc.bs.mapub.materialpricebase.plugin.bpplugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

/**
 * ��׼���ݵ���չ�����
 */
public enum MaterialPriceBasePluginPoint implements IPluginPoint {
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
    IMPORT_INSERT,

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
    UPDATE,

    /**
     * �����ͽⶳ
     */
    LOCK,

    /**
     * �ⶳ
     */
    DEBLOCKING,

    /**
     * ȡ��
     */
    TAKEPRICE;

    @Override
    public String getComponent() {
        return "cmbasedoc";
    }

    @Override
    public String getModule() {
        return "uapbd";
    }

    @Override
    public String getPoint() {
        return this.getClass().getName() + "." + this.name();
    }

}
