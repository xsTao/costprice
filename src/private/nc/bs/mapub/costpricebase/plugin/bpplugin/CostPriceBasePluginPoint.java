/**
 *
 */
package nc.bs.mapub.costpricebase.plugin.bpplugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

/**
 * ��׼���ݵ���չ�����
 */
public enum CostPriceBasePluginPoint implements IPluginPoint {

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
    UPDATE,
    /**
     * ��ӡ
     */
    PRINT;

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
