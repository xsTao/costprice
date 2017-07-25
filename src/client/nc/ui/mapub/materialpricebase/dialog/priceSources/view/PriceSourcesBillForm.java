package nc.ui.mapub.materialpricebase.dialog.priceSources.view;

import nc.ui.pubapp.uif2app.view.BillForm;

public class PriceSourcesBillForm extends BillForm {

    private static final long serialVersionUID = -1155294928491150301L;

    @Override
    public void initUI() {
        super.initUI();

        // 过滤有权限的组织
        // BillItem orgbillItem = this.getBillCardPanel().getBodyItem(MaterialPriceSourcesBodyVO.PK_ORG);
        // UIRefPane ref = (UIRefPane) orgbillItem.getComponent();
        // OrgAndOrgTypeCompositeRefTreeModelOnlyEnableData refModel =
        // (OrgAndOrgTypeCompositeRefTreeModelOnlyEnableData) ref.getRefModel();
        // refModel.setFilterPks(this.getNeedShowOrgPks(), IFilterStrategy.INSECTION);
        // String filterWhere = " enablestate =2 ";
        // refModel.setWherePart(filterWhere);
    }

    // private String[] getNeedShowOrgPks() {
    // LoginContext context = this.getModel().getContext();
    // // 业务单元权限
    // String[] pkorgs = context.getFuncInfo().getFuncPermissionPkorgs();
    // // 成本域权限
    // String[] costregions = new String[0];
    //
    // try {
    // costregions =
    // BDAdapter.queryPkorgsByOrgType(IOrgConst.COSTREGIONTYPE, context.getNodeCode(),
    // context.getPk_loginUser(), context.getPk_group());
    // }
    // catch (BusinessException e1) {
    // ExceptionUtils.wrappException(e1);
    // }
    //
    // List<String> result = new ArrayList<String>();
    // if (CMValueCheck.isNotEmpty(pkorgs)) {
    // result.addAll(Arrays.asList(pkorgs));
    // }
    // if (CMValueCheck.isNotEmpty(costregions)) {
    // result.addAll(Arrays.asList(costregions));
    // }
    //
    // // String[] result = null;
    // // if (CMValueCheck.isNotEmpty(pkorgs) && CMValueCheck.isNotEmpty(costregions)) {
    // // result = new String[pkorgs.length + costregions.length];
    // // System.arraycopy(pkorgs, 0, result, 0, pkorgs.length);
    // // System.arraycopy(costregions, 0, result, pkorgs.length, costregions.length);
    // // }
    //
    // // return result == null ? new String[0] : result;
    // return result.toArray(new String[result.size()]);
    // }
}
