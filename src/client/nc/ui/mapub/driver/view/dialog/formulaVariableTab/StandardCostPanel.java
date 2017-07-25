package nc.ui.mapub.driver.view.dialog.formulaVariableTab;

import java.util.List;

import nc.bd.framework.base.CMCollectionUtil;
import nc.bs.framework.common.NCLocator;
import nc.pubitf.mapub.materialpricebase.sca.driver.IMaterialPriceBasePubQueryServiceForDriver;
import nc.ui.pub.beans.UIDialog;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.ml.MultiLangUtil;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.uif2.LoginContext;

/**
 * <b> ��Ҫ�������� </b>
 * <p>
 * ��ϸ��������
 * </p>
 * ��������:2010-4-15
 *
 * @author:wangtf
 */
@SuppressWarnings("serial")
public class StandardCostPanel extends TwoListSelectPanel {

    /**
     * ���췽��
     *
     * @param context
     *            ��������
     * @param dialog
     *            �����Ի���
     */
    public StandardCostPanel(LoginContext context, UIDialog dialog) {
        super(context, dialog);
    }

    /**
     * ʹ�ü۸������ѡ�б�
     */
    @SuppressWarnings("unchecked")
    private void fillNotSelectWithPriceLibrary() {
        // ��ȡ�۸������
        IMaterialPriceBasePubQueryServiceForDriver service =
                NCLocator.getInstance().lookup(IMaterialPriceBasePubQueryServiceForDriver.class);
        List<MaterialPriceBaseHeadVO> listPricebase = null;
        try {
            listPricebase =
                    service.queryMaterialPriceBaseHeadVOByOrg(this.getContext().getPk_org(), AppContext.getInstance()
                            .getBusiDate());
        }
        catch (Exception e) {
            ExceptionUtils.wrappException(e);
        }
        if (CMCollectionUtil.isEmpty(listPricebase)) {
            return;
        }

        // �������
        for (MaterialPriceBaseHeadVO pricebaseheadvo : listPricebase) {
            ListItem item =
                    new ListItem(MultiLangUtil.getSuperVONameOfCurrentLang(pricebaseheadvo,
                            MaterialPriceBaseHeadVO.VPRICENAME, pricebaseheadvo.getVpricename()),
                            pricebaseheadvo.getVpricecode(), pricebaseheadvo.getCmaterialpriceid(), null);
            this.getListNotSelectModel().addElement(item);
        }
    }

    @Override
    protected void initUI() {
        super.initUI();
        this.fillNotSelectWithPriceLibrary();
    }

}
