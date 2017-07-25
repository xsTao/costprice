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
 * <b> 简要描述功能 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-4-15
 *
 * @author:wangtf
 */
@SuppressWarnings("serial")
public class StandardCostPanel extends TwoListSelectPanel {

    /**
     * 构造方法
     *
     * @param context
     *            环境变量
     * @param dialog
     *            容器对话框
     */
    public StandardCostPanel(LoginContext context, UIDialog dialog) {
        super(context, dialog);
    }

    /**
     * 使用价格库填充可选列表
     */
    @SuppressWarnings("unchecked")
    private void fillNotSelectWithPriceLibrary() {
        // 获取价格库数据
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

        // 填充数据
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
