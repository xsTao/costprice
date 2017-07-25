/**
 *
 */
package nc.bs.mapub.materialpricebase.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMCollectionUtil;
import nc.bd.framework.base.CMStringUtil;
import nc.bd.framework.base.CMValueCheck;
import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.cmpub.business.adapter.BDAdapter;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.mapub.materialpricebase.IMaterialPriceBaseQueryService;
import nc.itf.org.IOrgConst;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.mapub.materialpricebase.enumeration.PriceSourceEnum;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2015-1-13 下午4:13:02
 * @author zhangshyb
 */
public class PriceSourceRule implements IRule<MaterialPriceBaseAggVO> {
    private IMaterialPriceBaseQueryService service = null;

    @Override
    public void process(MaterialPriceBaseAggVO[] vos) {
        if (CMArrayUtil.isEmpty(vos)) {
            return;
        }
        // 1、验证表头价格库编码、价格库名称、币种、生效日期
        // 2、验证表体材料编码、单价(价格来源为：手工录入)

        this.checkPriceSource(vos);
    }

    /**
     * 1、验证表头价格库编码、价格库名称、币种、生效日期
     * 2、验证表体材料编码、单价(价格来源为：手工录入)
     *
     * @param vos
     */
    private void checkPriceSource(MaterialPriceBaseAggVO[] vos) {

        // 按插入顺序排序
        List<String> errorSet = new ArrayList<String>();
        ValidationException exception = new ValidationException();
        for (MaterialPriceBaseAggVO vo : vos) {
            // 表头价格来源-begain
            MaterialPriceBaseHeadVO head = vo.getParentVO();
            String headPriceSource = head.getVpricesource();
            if (CMStringUtil.isNotEmpty(headPriceSource)) {
                String[] priceSources = headPriceSource.toString().split("\\,");
                Set<String> setPriceSource = new HashSet<String>();
                StringBuffer converedString = new StringBuffer();
                Map<String, String> mapBusiUnit = new HashMap<String, String>();
                Map<String, String> mapCostRegion = new HashMap<String, String>();
                mapBusiUnit = this.getLeagalBusiUnit();
                mapCostRegion = this.getLeagalCostRegion();
                for (String stringPriceSource : priceSources) {
                    if (stringPriceSource.contains(PriceSourceEnum.MANUAL.getName())) {
                        if (!stringPriceSource.equals(PriceSourceEnum.MANUAL.getName())) {
                            errorSet.add(this.errorInfo1(stringPriceSource));
                        }
                        else {
                            // 判断是否重复
                            if (setPriceSource.contains(PriceSourceEnum.MANUAL.getName())) {
                                errorSet.add(this.errorInfo2());
                            }
                            else {
                                setPriceSource.add(PriceSourceEnum.MANUAL.getName());
                                converedString.append(PriceSourceEnum.MANUAL.getEnumValue().getValue());
                                converedString.append(",");
                            }
                        }
                    }
                    else if (stringPriceSource.contains(PriceSourceEnum.STDCOST.getName())) {
                        if (!stringPriceSource.equals(PriceSourceEnum.STDCOST.getName())) {
                            errorSet.add(this.errorInfo1(stringPriceSource));
                        }
                        else {
                            // 判断是否重复
                            if (setPriceSource.contains(PriceSourceEnum.STDCOST.getName())) {
                                errorSet.add(this.errorInfo2());
                            }
                            else {
                                setPriceSource.add(PriceSourceEnum.STDCOST.getName());
                                converedString.append(PriceSourceEnum.STDCOST.getEnumValue().getValue());
                                converedString.append(",");
                            }
                        }
                    }
                    else if (stringPriceSource.contains("[") && stringPriceSource.contains("]")
                            && stringPriceSource.endsWith("]")) {
                        if (stringPriceSource.contains(PriceSourceEnum.PLAN.getName())) {
                            if (!stringPriceSource.subSequence(0, stringPriceSource.indexOf("[")).equals(
                                    PriceSourceEnum.PLAN.getName())) {
                                errorSet.add(this.errorInfo1(stringPriceSource));
                            }
                            else {
                                String org =
                                        stringPriceSource.subSequence(stringPriceSource.indexOf("[") + 1,
                                                stringPriceSource.length() - 1).toString();
                                if (mapBusiUnit.containsKey(org)) {
                                    // 判断是否重复
                                    if (setPriceSource.contains(PriceSourceEnum.PLAN.getName())) {
                                        errorSet.add(this.errorInfo2());
                                    }
                                    else {
                                        setPriceSource.add(PriceSourceEnum.PLAN.getName());
                                    }
                                    converedString.append(PriceSourceEnum.PLAN.getEnumValue().getValue());
                                    converedString.append("[");
                                    converedString.append(mapBusiUnit.get(org));
                                    converedString.append("]");
                                    converedString.append(",");
                                }
                                else if (mapCostRegion.containsKey(org)) {
                                    // 判断是否重复
                                    if (setPriceSource.contains(PriceSourceEnum.PLAN.getName())) {
                                        errorSet.add(this.errorInfo2());
                                    }
                                    else {
                                        setPriceSource.add(PriceSourceEnum.PLAN.getName());
                                    }
                                    converedString.append(PriceSourceEnum.PLAN.getEnumValue().getValue());
                                    converedString.append("[");
                                    converedString.append(mapCostRegion.get(org));
                                    converedString.append("]");
                                    converedString.append(",");
                                }
                                else {
                                    errorSet.add(this.errorInfo1(stringPriceSource));
                                }
                            }
                        }
                        else if (stringPriceSource.contains(PriceSourceEnum.REFERENCE.getName())) {
                            if (!stringPriceSource.subSequence(0, stringPriceSource.indexOf("[")).equals(
                                    PriceSourceEnum.REFERENCE.getName())) {
                                errorSet.add(this.errorInfo1(stringPriceSource));
                            }
                            else {
                                String org =
                                        stringPriceSource.subSequence(stringPriceSource.indexOf("[") + 1,
                                                stringPriceSource.length() - 1).toString();
                                if (mapBusiUnit.containsKey(org)) {
                                    // 判断是否重复
                                    if (setPriceSource.contains(PriceSourceEnum.REFERENCE.getName())) {
                                        errorSet.add(this.errorInfo2());
                                    }
                                    else {
                                        setPriceSource.add(PriceSourceEnum.REFERENCE.getName());
                                    }
                                    converedString.append(PriceSourceEnum.REFERENCE.getEnumValue().getValue());
                                    converedString.append("[");
                                    converedString.append(mapBusiUnit.get(org));
                                    converedString.append("]");
                                    converedString.append(",");
                                }
                                else if (mapCostRegion.containsKey(org)) {
                                    // 判断是否重复
                                    if (setPriceSource.contains(PriceSourceEnum.REFERENCE.getName())) {
                                        errorSet.add(this.errorInfo2());
                                    }
                                    else {
                                        setPriceSource.add(PriceSourceEnum.REFERENCE.getName());
                                    }
                                    converedString.append(PriceSourceEnum.REFERENCE.getEnumValue().getValue());
                                    converedString.append("[");
                                    converedString.append(mapCostRegion.get(org));
                                    converedString.append("]");
                                    converedString.append(",");
                                }
                                else {
                                    errorSet.add(this.errorInfo1(stringPriceSource));
                                }
                            }
                        }
                        else if (stringPriceSource.contains(PriceSourceEnum.PINGJUNCAIGOURUKU.getName())) {
                            if (!stringPriceSource.subSequence(0, stringPriceSource.indexOf("[")).equals(
                                    PriceSourceEnum.PINGJUNCAIGOURUKU.getName())) {
                                errorSet.add(this.errorInfo1(stringPriceSource));
                            }
                            else {
                                String org =
                                        stringPriceSource.subSequence(stringPriceSource.indexOf("[") + 1,
                                                stringPriceSource.length() - 1).toString();
                                if (mapCostRegion.containsKey(org)) {
                                    // 判断是否重复
                                    if (setPriceSource.contains(PriceSourceEnum.PINGJUNCAIGOURUKU.getName())) {
                                        errorSet.add(this.errorInfo2());
                                    }
                                    else {
                                        setPriceSource.add(PriceSourceEnum.PINGJUNCAIGOURUKU.getName());
                                    }
                                    converedString.append(PriceSourceEnum.PINGJUNCAIGOURUKU.getEnumValue().getValue());
                                    converedString.append("[");
                                    converedString.append(mapCostRegion.get(org));
                                    converedString.append("]");
                                    converedString.append(",");
                                }
                                else {
                                    errorSet.add(this.errorInfo1(stringPriceSource));
                                }
                            }
                        }
                        else if (stringPriceSource.contains(PriceSourceEnum.FORWARD.getName())) {
                            if (!stringPriceSource.subSequence(0, stringPriceSource.indexOf("[")).equals(
                                    PriceSourceEnum.FORWARD.getName())) {
                                errorSet.add(this.errorInfo1(stringPriceSource));
                            }
                            else {
                                String org =
                                        stringPriceSource.subSequence(stringPriceSource.indexOf("[") + 1,
                                                stringPriceSource.length() - 1).toString();
                                if (mapCostRegion.containsKey(org)) {
                                    // 判断是否重复
                                    if (setPriceSource.contains(PriceSourceEnum.FORWARD.getName())) {
                                        errorSet.add(this.errorInfo2());
                                    }
                                    else {
                                        setPriceSource.add(PriceSourceEnum.FORWARD.getName());
                                    }
                                    converedString.append(PriceSourceEnum.FORWARD.getEnumValue().getValue());
                                    converedString.append("[");
                                    converedString.append(mapCostRegion.get(org));
                                    converedString.append("]");
                                    converedString.append(",");
                                }
                                else {
                                    errorSet.add(this.errorInfo1(stringPriceSource));
                                }
                            }
                        }
                        else {
                            errorSet.add(this.errorInfo1(stringPriceSource));
                        }
                    }
                    else {
                        errorSet.add(this.errorInfo1(stringPriceSource));
                    }
                }
                if (setPriceSource.contains(PriceSourceEnum.MANUAL.getName()) && setPriceSource.size() > 1) {
                    errorSet.add(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0319")/*
                                                                                                                     * 默认价格来源
                                                                                                                     * :
                                                                                                                     * 手工录入与其他价格来源互斥
                                                                                                                     * ！
                                                                                                                     */);
                }
                if (CMStringUtil.isNotEmpty(converedString.toString())) {
                    String converedStrings = converedString.subSequence(0, converedString.length() - 1).toString();
                    head.setVpricesourcenum(converedStrings);
                }
            }
            else {
                errorSet.add(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0322")/*
                                                                                                                 * 默认价格来源不可为空
                                                                                                                 * ！
                                                                                                                 */);
            }
            // 表头价格来源-end
            // 表体价格来源-begain
            MaterialPriceBaseBodyVO[] bodys = vo.getItemVO();
            int i = 0;
            for (MaterialPriceBaseBodyVO body : bodys) {
                List<String> bodyErrorSet = new ArrayList<String>();
                String msg =
                        String.format(
                                nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0345"),
                                Integer.toString(i + 1));
                i++;
                // 实际价格来源不能手工录入，只能在取价后自动获取-begain
                body.setVpricesourcereal(null);
                body.setVpricesourcerealnum(null);
                // 实际价格来源不能手工录入，只能在取价后自动获取-end
                String bodyPriceSource = body.getVpricesource();
                if (CMStringUtil.isNotEmpty(bodyPriceSource)) {
                    if (bodyPriceSource.equals(PriceSourceEnum.MANUAL.getName())) {
                        if (CMValueCheck.isEmpty(body.getNprice())) {
                            bodyErrorSet.add(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                                    "03810006-0320")/* 表体价格来源为手工录入时，单价必输！ */);
                        }
                    }
                    else {
                        body.setNprice(null);
                    }
                }
                else if (headPriceSource.equals(PriceSourceEnum.MANUAL.getName())) {
                    if (CMValueCheck.isEmpty(body.getNprice())) {
                        bodyErrorSet.add(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                                "03810006-0321")/*
                                                 * 表体价格来源为空表头默认价格来源为手工录入时
                                                 * ，
                                                 * 单价必输
                                                 * ！
                                                 */);
                    }
                }
                else {
                    body.setNprice(null);
                }

                if (CMStringUtil.isNotEmpty(bodyPriceSource)) {
                    String[] priceSources = bodyPriceSource.toString().split("\\,");
                    Set<String> setPriceSource = new HashSet<String>();
                    StringBuffer bodyConveredString = new StringBuffer();
                    Map<String, String> mapBusiUnit = new HashMap<String, String>();
                    Map<String, String> mapCostRegion = new HashMap<String, String>();
                    mapBusiUnit = this.getLeagalBusiUnit();
                    mapCostRegion = this.getLeagalCostRegion();
                    for (String stringPriceSource : priceSources) {
                        if (stringPriceSource.contains(PriceSourceEnum.MANUAL.getName())) {
                            if (!stringPriceSource.equals(PriceSourceEnum.MANUAL.getName())) {
                                bodyErrorSet.add(this.errorInfo0(stringPriceSource));
                            }
                            else {
                                // 判断是否重复
                                if (setPriceSource.contains(PriceSourceEnum.MANUAL.getName())) {
                                    bodyErrorSet.add(this.errorInfo3());
                                }
                                else {
                                    setPriceSource.add(PriceSourceEnum.MANUAL.getName());
                                    bodyConveredString.append(PriceSourceEnum.MANUAL.getEnumValue().getValue());
                                    bodyConveredString.append(",");
                                }
                            }
                        }
                        else if (stringPriceSource.contains(PriceSourceEnum.STDCOST.getName())) {
                            if (!stringPriceSource.equals(PriceSourceEnum.STDCOST.getName())) {
                                bodyErrorSet.add(this.errorInfo0(stringPriceSource));
                            }
                            else {
                                // 判断是否重复
                                if (setPriceSource.contains(PriceSourceEnum.STDCOST.getName())) {
                                    bodyErrorSet.add(this.errorInfo3());
                                }
                                else {
                                    setPriceSource.add(PriceSourceEnum.STDCOST.getName());
                                    bodyConveredString.append(PriceSourceEnum.STDCOST.getEnumValue().getValue());
                                    bodyConveredString.append(",");
                                }
                            }
                        }
                        else if (stringPriceSource.contains("[") && stringPriceSource.contains("]")
                                && stringPriceSource.endsWith("]")) {
                            if (stringPriceSource.contains(PriceSourceEnum.PLAN.getName())) {
                                if (!stringPriceSource.subSequence(0, stringPriceSource.indexOf("[")).equals(
                                        PriceSourceEnum.PLAN.getName())) {
                                    bodyErrorSet.add(this.errorInfo0(stringPriceSource));
                                }
                                else {
                                    String org =
                                            stringPriceSource.subSequence(stringPriceSource.indexOf("[") + 1,
                                                    stringPriceSource.length() - 1).toString();
                                    if (mapBusiUnit.containsKey(org)) {
                                        // 判断是否重复
                                        if (setPriceSource.contains(PriceSourceEnum.PLAN.getName())) {
                                            bodyErrorSet.add(this.errorInfo3());
                                        }
                                        else {
                                            setPriceSource.add(PriceSourceEnum.PLAN.getName());
                                        }
                                        bodyConveredString.append(PriceSourceEnum.PLAN.getEnumValue().getValue());
                                        bodyConveredString.append("[");
                                        bodyConveredString.append(mapBusiUnit.get(org));
                                        bodyConveredString.append("]");
                                        bodyConveredString.append(",");
                                    }
                                    else if (mapCostRegion.containsKey(org)) {
                                        // 判断是否重复
                                        if (setPriceSource.contains(PriceSourceEnum.PLAN.getName())) {
                                            bodyErrorSet.add(this.errorInfo3());
                                        }
                                        else {
                                            setPriceSource.add(PriceSourceEnum.PLAN.getName());
                                        }
                                        bodyConveredString.append(PriceSourceEnum.PLAN.getEnumValue().getValue());
                                        bodyConveredString.append("[");
                                        bodyConveredString.append(mapCostRegion.get(org));
                                        bodyConveredString.append("]");
                                        bodyConveredString.append(",");
                                    }
                                    else {
                                        bodyErrorSet.add(this.errorInfo0(stringPriceSource));
                                    }
                                }
                            }
                            else if (stringPriceSource.contains(PriceSourceEnum.REFERENCE.getName())) {
                                if (!stringPriceSource.subSequence(0, stringPriceSource.indexOf("[")).equals(
                                        PriceSourceEnum.REFERENCE.getName())) {
                                    bodyErrorSet.add(this.errorInfo0(stringPriceSource));
                                }
                                else {

                                    String org =
                                            stringPriceSource.subSequence(stringPriceSource.indexOf("[") + 1,
                                                    stringPriceSource.length() - 1).toString();
                                    if (mapBusiUnit.containsKey(org)) {
                                        // 判断是否重复
                                        if (setPriceSource.contains(PriceSourceEnum.REFERENCE.getName())) {
                                            bodyErrorSet.add(this.errorInfo3());
                                        }
                                        else {
                                            setPriceSource.add(PriceSourceEnum.REFERENCE.getName());
                                        }
                                        bodyConveredString.append(PriceSourceEnum.REFERENCE.getEnumValue().getValue());
                                        bodyConveredString.append("[");
                                        bodyConveredString.append(mapBusiUnit.get(org));
                                        bodyConveredString.append("]");
                                        bodyConveredString.append(",");
                                    }
                                    else if (mapCostRegion.containsKey(org)) {
                                        // 判断是否重复
                                        if (setPriceSource.contains(PriceSourceEnum.REFERENCE.getName())) {
                                            bodyErrorSet.add(this.errorInfo3());
                                        }
                                        else {
                                            setPriceSource.add(PriceSourceEnum.REFERENCE.getName());
                                        }
                                        bodyConveredString.append(PriceSourceEnum.REFERENCE.getEnumValue().getValue());
                                        bodyConveredString.append("[");
                                        bodyConveredString.append(mapCostRegion.get(org));
                                        bodyConveredString.append("]");
                                        bodyConveredString.append(",");
                                    }
                                    else {
                                        bodyErrorSet.add(this.errorInfo0(stringPriceSource));
                                    }

                                }
                            }
                            else if (stringPriceSource.contains(PriceSourceEnum.PINGJUNCAIGOURUKU.getName())) {
                                if (!stringPriceSource.subSequence(0, stringPriceSource.indexOf("[")).equals(
                                        PriceSourceEnum.PINGJUNCAIGOURUKU.getName())) {
                                    bodyErrorSet.add(this.errorInfo0(stringPriceSource));
                                }
                                else {

                                    String org =
                                            stringPriceSource.subSequence(stringPriceSource.indexOf("[") + 1,
                                                    stringPriceSource.length() - 1).toString();
                                    if (mapCostRegion.containsKey(org)) {
                                        // 判断是否重复
                                        if (setPriceSource.contains(PriceSourceEnum.PINGJUNCAIGOURUKU.getName())) {
                                            bodyErrorSet.add(this.errorInfo3());
                                        }
                                        else {
                                            setPriceSource.add(PriceSourceEnum.PINGJUNCAIGOURUKU.getName());
                                        }
                                        bodyConveredString.append(PriceSourceEnum.PINGJUNCAIGOURUKU.getEnumValue()
                                                .getValue());
                                        bodyConveredString.append("[");
                                        bodyConveredString.append(mapCostRegion.get(org));
                                        bodyConveredString.append("]");
                                        bodyConveredString.append(",");
                                    }
                                    else {
                                        bodyErrorSet.add(this.errorInfo0(stringPriceSource));
                                    }

                                }
                            }
                            else if (stringPriceSource.contains(PriceSourceEnum.FORWARD.getName())) {
                                if (!stringPriceSource.subSequence(0, stringPriceSource.indexOf("[")).equals(
                                        PriceSourceEnum.FORWARD.getName())) {
                                    bodyErrorSet.add(this.errorInfo0(stringPriceSource));
                                }
                                else {

                                    String org =
                                            stringPriceSource.subSequence(stringPriceSource.indexOf("[") + 1,
                                                    stringPriceSource.length() - 1).toString();
                                    if (mapCostRegion.containsKey(org)) {
                                        // 判断是否重复
                                        if (setPriceSource.contains(PriceSourceEnum.FORWARD.getName())) {
                                            bodyErrorSet.add(this.errorInfo3());
                                        }
                                        else {
                                            setPriceSource.add(PriceSourceEnum.FORWARD.getName());
                                        }
                                        bodyConveredString.append(PriceSourceEnum.FORWARD.getEnumValue().getValue());
                                        bodyConveredString.append("[");
                                        bodyConveredString.append(mapCostRegion.get(org));
                                        bodyConveredString.append("]");
                                        bodyConveredString.append(",");
                                    }
                                    else {
                                        bodyErrorSet.add(this.errorInfo0(stringPriceSource));
                                    }

                                }
                            }
                            else {
                                bodyErrorSet.add(this.errorInfo0(stringPriceSource));
                            }
                        }
                        else {
                            bodyErrorSet.add(this.errorInfo0(stringPriceSource));
                        }
                    }
                    if (setPriceSource.contains(PriceSourceEnum.MANUAL.getName()) && setPriceSource.size() > 1) {
                        bodyErrorSet.add(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                                "03810006-0344")/*
                                 * 价格来源
                                 * :
                                 * 手工录入与其他价格来源互斥
                                 * ！
                                 */);
                    }
                    // 不为空加入
                    if (CMCollectionUtil.isNotEmpty(bodyErrorSet)) {
                        errorSet.add(msg);
                        errorSet.addAll(bodyErrorSet);
                        continue;
                    }
                    if (CMStringUtil.isNotEmpty(bodyConveredString.toString())) {
                        String bodyConveredStrings =
                                bodyConveredString.subSequence(0, bodyConveredString.length() - 1).toString();
                        body.setVpricesourcenum(bodyConveredStrings);
                    }
                }
            }
            // 表体价格来源-end
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

    public Map<String, String> getLeagalBusiUnit() {
        Map<String, String> mapBusiUnit = new HashMap<String, String>();
        String[] pkorgs;
        try {
            pkorgs =
                    BDAdapter.queryPkorgsByOrgType(IOrgConst.BUSINESSUNITORGTYPE, "38100110", AppContext.getInstance()
                            .getPkUser(), AppContext.getInstance().getPkGroup());
            mapBusiUnit = this.getService().queryOrgCodeOid(pkorgs);
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
        return mapBusiUnit;
    }

    public Map<String, String> getLeagalCostRegion() {
        Map<String, String> mapCostRegion = new HashMap<String, String>();
        String[] costregions = new String[0];
        try {
            costregions =
                    BDAdapter.queryPkorgsByOrgType(IOrgConst.COSTREGIONTYPE, "38100110", AppContext.getInstance()
                            .getPkUser(), AppContext.getInstance().getPkGroup());
            mapCostRegion = this.getService().queryOrgCodeOid(costregions);
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
        return mapCostRegion;
    }

    private String errorInfo0(String stringPriceSource) {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0324")/*
                                                                                                   * 价格来源:
                                                                                                   */
                + stringPriceSource
                + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0316")/*
                                                                                                      * 不合法！
                                                                                                      */;
    }

    private String errorInfo1(String stringPriceSource) {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0315")/*
                                                                                                   * 默认价格来源:
                                                                                                   */
                + stringPriceSource
                + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0316")/*
                                                                                                      * 不合法！
                                                                                                      */;
    }

    private String errorInfo2() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0317")/*
                                                                                                   * 默认价格来源不可重复！
                                                                                                   */;
    }

    private String errorInfo3() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0323")/* 价格来源不可重复！ */;
    }

    public IMaterialPriceBaseQueryService getService() {
        if (this.service == null) {
            this.service = NCLocator.getInstance().lookup(IMaterialPriceBaseQueryService.class);
        }
        return this.service;

    }
}
