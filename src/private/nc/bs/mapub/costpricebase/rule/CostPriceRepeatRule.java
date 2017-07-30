/**
 *
 */
package nc.bs.mapub.costpricebase.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMCollectionUtil;
import nc.bd.framework.base.CMValueCheck;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.cmpub.business.adapter.BDAdapter;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mapub.costpricebase.entity.CostPriceAggVO;
import nc.vo.mapub.costpricebase.entity.CostPriceBodyVO;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.resa.factor.FactorVO;

/**
 * @since v6.3
 * @version 2017年7月28日 下午9:08:28
 * @author Administrator
 */
public class CostPriceRepeatRule implements IRule<CostPriceAggVO> {

    /*
     * (non-Javadoc)
     * @see nc.impl.pubapp.pattern.rule.IRule#process(java.lang.Object[])
     */
    @Override
    public void process(CostPriceAggVO[] vos) {
        // TODO Auto-generated method stub
        if (CMArrayUtil.isEmpty(vos)) {
            return;
        }

        try {
            this.checkRepeatRule(vos);
        }
        catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @param vos
     * @throws BusinessException
     */
    private void checkRepeatRule(CostPriceAggVO[] vos) throws BusinessException {
        // TODO Auto-generated method stub
        Set<String> errorSet = new HashSet<String>();

        ValidationException exception = new ValidationException();
        Set<String> elementIdRepeatSet = new HashSet<String>();
        List<String> elementIdRepeatList = new ArrayList<String>();// 重复的核算要素编码
        Map<String, Integer> rowAndelementIdMap = new HashMap<String, Integer>();

        for (CostPriceAggVO vo : vos) {
            CostPriceBodyVO[] bodyVOs = (CostPriceBodyVO[]) vo.getChildren(CostPriceBodyVO.class);

            if (CMArrayUtil.isNotEmpty(bodyVOs)) {
                int rowNO = 1;
                for (int i = 0; i < bodyVOs.length; i++) {
                    if (bodyVOs[i] != null) {
                        if (bodyVOs[i].getStatus() == VOStatus.DELETED) {
                            rowNO++;
                            continue;
                        }
                        if (elementIdRepeatSet.contains(this.getUniqueKey(bodyVOs[i]))) {
                            elementIdRepeatList.add(bodyVOs[i].getCelementid());
                            rowAndelementIdMap.put(bodyVOs[i].getCelementid(), Integer.valueOf(rowNO++));
                        }
                        else {
                            elementIdRepeatSet.add(this.getUniqueKey(bodyVOs[i]));
                            rowNO++;
                        }
                    }
                }
            }

            if (CMCollectionUtil.isNotEmpty(elementIdRepeatList)) {
                StringBuffer sb = new StringBuffer();

                Map<String, FactorVO> matMap =
                        BDAdapter.queryFactorsFromAsoaPks(
                                elementIdRepeatList.toArray(new String[elementIdRepeatList.size()]), "");

                for (Entry<String, FactorVO> map : matMap.entrySet()) {
                    sb.append(String.format(CMMLangConstMaterialPriceBase.getMsgRowNum(),
                            rowAndelementIdMap.get(map.getKey()))); // 第%s行:核算要素编码

                    sb.append("[");
                    sb.append(map.getValue().getFactorname());
                    sb.append("]");

                    sb.append(CMMLangConstMaterialPriceBase.ITEMS_REPEAT_INFO()); // 出现重复，请修改！
                    sb.append("\r\n");
                }
                errorSet.add(sb.toString());
            }

            ValidationFailure failure =
                    new ValidationFailure(nc.vo.jcom.lang.StringUtil.getUnionStr(errorSet.toArray(new String[0]), "\n",
                            ""));

            if (CMValueCheck.isNotEmpty(failure.getMessage())) {
                exception.addValidationFailure(failure);
            }

            if (exception.getFailures() != null && exception.getFailures().size() > 0) {
                ExceptionUtils.wrappException(exception);
            }
        }
    }

    // 获得唯一键
    private String getUniqueKey(CostPriceBodyVO itemvo) {
        StringBuffer uniqueKey = new StringBuffer();

        if (CMValueCheck.isNotEmpty(itemvo.getCelementid())) {
            uniqueKey.append(itemvo.getCelementid());
        }

        if (CMValueCheck.isNotEmpty(itemvo.getDprice())) {
            uniqueKey.append(itemvo.getDprice());
        }

        return uniqueKey.toString();
    }
}
