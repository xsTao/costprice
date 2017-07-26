/**
 *
 */
package nc.bs.mapub.costpricebase.plugin.bpplugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

/**
 * 标准单据的扩展插入点
 */
public enum CostPriceBasePluginPoint implements IPluginPoint {

    /**
     * 删除
     */
    DELETE,
    /**
     * 新增
     */
    INSERT,

    /**
     * 更新
     */
    UPDATE,
    /**
     * 打印
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
