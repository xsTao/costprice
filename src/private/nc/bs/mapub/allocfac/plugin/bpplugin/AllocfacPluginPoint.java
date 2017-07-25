/**
 * 
 */
package nc.bs.mapub.allocfac.plugin.bpplugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

/**
 * 分配系数扩展插入点
 */
public enum AllocfacPluginPoint implements IPluginPoint {
    /**
     * 审批
     */
    APPROVE,
    /**
     * 删除
     */
    DELETE,
    /**
     * 新增
     */
    INSERT,

    /**
     * 送审
     */
    SEND_APPROVE,

    /**
     * 取消审核
     */
    UNAPPROVE,

    /**
     * 收回
     */
    UNSEND_APPROVE,

    /**
     * 更新
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
