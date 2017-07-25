package nc.ui.mapub.driver.view.dialog.formulaVariableTab;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.mapub.driver.IDriverQueryService;
import nc.ui.pub.beans.UIDialog;
import nc.vo.mapub.driver.entity.DriverQueryCondition;
import nc.vo.ml.MultiLangUtil;
import nc.vo.org.OrgVO;
import nc.vo.uif2.LoginContext;

/**
 * <b> 组织选择 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-4-15
 *
 * @author:wangtf
 */
public class SaleOrgPanel extends TwoListSelectPanel {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = -1813171139012128490L;

    /**
     * 构造方法
     *
     * @param context
     *            环境变量
     * @param dialog 容器对话框
     */
    public SaleOrgPanel(LoginContext context, UIDialog dialog) {
        super(context, dialog);
    }

    @Override
    protected void initUI() {
        super.initUI();
        this.fillNotSelectWithSaleOrg();
    }

    /**
     * 使用销售组织填充左侧列表
     */
    @SuppressWarnings("unchecked")
    private void fillNotSelectWithSaleOrg() {
        // 获取销售组织数据
        IDriverQueryService service = NCLocator.getInstance().lookup(IDriverQueryService.class);
        DriverQueryCondition condition = new DriverQueryCondition();
        condition.setPk_group(this.getContext().getPk_group());
        condition.setPk_org(this.getContext().getPk_org());
        List<OrgVO> listSaleOrg = service.querySaleOrgs(condition);
        if (listSaleOrg == null || listSaleOrg.isEmpty()) {
            return;

        }

        // 填充数据
        for (OrgVO orgvo : listSaleOrg) {
            ListItem item =
                    new ListItem(MultiLangUtil.getSuperVONameOfCurrentLang(orgvo, OrgVO.NAME, orgvo.getName()),
                            orgvo.getCode(), orgvo.getPk_org(), null
                            // new String[] {
                            // orgvo.getName(), orgvo.getName2(), orgvo.getName3()
                            // }
                            );
            this.getListNotSelectModel().addElement(item);
        }
    }
}
