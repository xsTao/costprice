package nc.bs.mapub.materialpricebase.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bd.framework.db.CMSqlBuilder;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 价格库被成本动因引用不能删除
 *
 * @since 6.36
 * @version 2014-11-10 下午2:42:30
 * @author zhangchd
 */
public class MaterialPriceBaseIsUsedCheck implements IRule<MaterialPriceBaseAggVO> {

    @Override
    public void process(MaterialPriceBaseAggVO[] vos) {
        if (vos == null || vos.length == 0) {
            return;
        }
        String pk_org = vos[0].getParentVO().getPk_org();
        List<String> idList = new ArrayList<String>();
        for (MaterialPriceBaseAggVO vo : vos) {
            MaterialPriceBaseHeadVO hvo = (MaterialPriceBaseHeadVO) vo.getParent();
            idList.add(hvo.getCmaterialpriceid());
        }
        boolean isLegal = false;
        try {
            // TODO:暂时代替成本动因接口
            isLegal = this.isUsedInDriver(pk_org, "PRICE_LIBRARY", idList);
            // NCLocator.getInstance().lookup(IDriverQueryForPub.class)
            // .isUsedInDriver(pk_org, CMDriverParameterEnum.PRICE_LIBRARY.getName(), idList);
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
        if (!isLegal) {
            // //分配系数已被成本动因引用，不能进行该操作
            // ExceptionUtils.wrappBusinessException(CMMLangConstAllocfac.getUSED_IN_DRIVER());
            // 价格库已被成本动因引用，不能进行该操作
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                    "03810006-0325")/* 价格库已被成本动因引用，不能进行该操作！ */);
        }
    }

    private boolean isUsedInDriver(String pk_org, String driverType, List<String> idList) throws BusinessException {
        CMSqlBuilder sql = new CMSqlBuilder();
        sql.append("select distinct vformulacode from cm_driver where pk_org ='" + pk_org
                + "' and vformulacode like '%" + driverType + "%' and dr=0");

        IRowSet rows = new DataAccessUtils().query(sql.toString());
        while (rows.next()) {
            for (String id : idList) {
                if (rows.getString(0).indexOf(id) != -1) {
                    return false;
                }
            }
        }
        return true;
    }
}
