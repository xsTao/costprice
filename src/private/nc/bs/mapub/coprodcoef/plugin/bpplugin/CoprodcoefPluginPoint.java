package nc.bs.mapub.coprodcoef.plugin.bpplugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

/**
 * 标准单据的扩展插入点
 */
public enum CoprodcoefPluginPoint implements IPluginPoint {
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
