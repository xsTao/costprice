package nc.bs.mapub.materialpricebase.plugin.bpplugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

/**
 * 标准单据的扩展插入点
 */
public enum MaterialPriceBasePluginPoint implements IPluginPoint {
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
     * 导入
     */
    IMPORT_INSERT,

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
    UPDATE,

    /**
     * 锁定和解冻
     */
    LOCK,

    /**
     * 解冻
     */
    DEBLOCKING,

    /**
     * 取价
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
