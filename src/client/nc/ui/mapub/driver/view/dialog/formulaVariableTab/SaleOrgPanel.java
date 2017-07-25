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
 * <b> ��֯ѡ�� </b>
 * <p>
 * ��ϸ��������
 * </p>
 * ��������:2010-4-15
 *
 * @author:wangtf
 */
public class SaleOrgPanel extends TwoListSelectPanel {

    /**
     * ���л�id
     */
    private static final long serialVersionUID = -1813171139012128490L;

    /**
     * ���췽��
     *
     * @param context
     *            ��������
     * @param dialog �����Ի���
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
     * ʹ��������֯�������б�
     */
    @SuppressWarnings("unchecked")
    private void fillNotSelectWithSaleOrg() {
        // ��ȡ������֯����
        IDriverQueryService service = NCLocator.getInstance().lookup(IDriverQueryService.class);
        DriverQueryCondition condition = new DriverQueryCondition();
        condition.setPk_group(this.getContext().getPk_group());
        condition.setPk_org(this.getContext().getPk_org());
        List<OrgVO> listSaleOrg = service.querySaleOrgs(condition);
        if (listSaleOrg == null || listSaleOrg.isEmpty()) {
            return;

        }

        // �������
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
