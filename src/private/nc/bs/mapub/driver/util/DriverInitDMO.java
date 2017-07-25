package nc.bs.mapub.driver.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.vo.mapub.driver.entity.CMDriverParameterEnum;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.ml.LanguageVO;
import nc.vo.ml.MultiLangContext;

/**
 * 成本动因预置
 *
 * @since 6.0
 * @version 2011-8-18 上午09:49:55
 * @author hupeng
 */
public class DriverInitDMO {
    // 成本动因编码和公式名称对应，支持多语
    private Map<String, String[]> nameMap = new HashMap<String, String[]>();

    // 成本动因编码和公式变量对应，支持多语
    private Map<String, String[]> formulaValueMap = new HashMap<String, String[]>();

    // 成本动因编码和公式变量对应
    private Map<String, String> formulaCodeMap = new HashMap<String, String>();

    // 系统预置成本动因VO
    private List<DriverVO> driverVOList = new ArrayList<DriverVO>();

    // 多语言
    private LanguageVO[] languages = null;

    public List<DriverVO> getDriverVOList() {
        return this.driverVOList;
    }

    public void setDriverVOList(List<DriverVO> driverVOList) {
        this.driverVOList = driverVOList;
    }

    /**
     * 成本动因预置VO生成
     *
     * @param pk_group
     * @param pk_org
     * @param pk_org_v
     */
    public void initDatas() {
        this.languages = MultiLangContext.getInstance().getEnableLangVOs();
        if (this.languages == null || this.languages.length == 0) {
            this.languages = new LanguageVO[1];
            this.languages[0] = new LanguageVO();
            this.languages[0].setLangcode("simpchn");
            this.languages[0].setLangseq(1);
            // ExceptionUtils.wrappBusinessException(CMDriverLangConst.getERR_LANGUAGE());
        }
        this.setNameMap();
        this.setFormulaValueMap();
        this.setFormulaCodeMap();
        for (int i = 0; i < DriverVO.INIT_CODES.length; i++) {
            DriverVO vo = new DriverVO();
            vo.setPk_group("@@@@");
            vo.setVcode(DriverVO.INIT_CODES[i]);
            vo.setVname(this.nameMap.get(DriverVO.INIT_CODES[i])[1]);
            vo.setVname2(this.nameMap.get(DriverVO.INIT_CODES[i])[2]);
            vo.setVname3(this.nameMap.get(DriverVO.INIT_CODES[i])[3]);
            vo.setVname4(this.nameMap.get(DriverVO.INIT_CODES[i])[4]);
            vo.setVname5(this.nameMap.get(DriverVO.INIT_CODES[i])[5]);
            vo.setVname6(this.nameMap.get(DriverVO.INIT_CODES[i])[6]);
            vo.setVformulavalue(this.formulaValueMap.get(DriverVO.INIT_CODES[i])[1]);
            vo.setVformulavalue2(this.formulaValueMap.get(DriverVO.INIT_CODES[i])[2]);
            vo.setVformulavalue3(this.formulaValueMap.get(DriverVO.INIT_CODES[i])[3]);
            vo.setVformulavalue4(this.formulaValueMap.get(DriverVO.INIT_CODES[i])[4]);
            vo.setVformulavalue5(this.formulaValueMap.get(DriverVO.INIT_CODES[i])[5]);
            vo.setVformulavalue6(this.formulaValueMap.get(DriverVO.INIT_CODES[i])[6]);
            vo.setVformulacode(this.formulaCodeMap.get(DriverVO.INIT_CODES[i]));
            this.driverVOList.add(vo);
        }

    }

    /**
     * 预置成本动因名称
     */
    private void setNameMap() {
        for (LanguageVO language : this.languages) {
            if (language.getLangseq() == null) {
                continue;
            }
            /* @res "完工产量" */
            this.setNameToMap(
                    DriverVO.INIT_CODES[0],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0254"), language.getLangseq().intValue());
            /* @res "主联完工量" */
            this.setNameToMap(
                    DriverVO.INIT_CODES[1],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0255"), language.getLangseq().intValue());
            /* @res "总产量" */
            this.setNameToMap(
                    DriverVO.INIT_CODES[2],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0256"), language.getLangseq().intValue());
            /* @res "约当在产量" */
            this.setNameToMap(
                    DriverVO.INIT_CODES[3],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0257"), language.getLangseq().intValue());
            /* @res "合格品产量+约当在产量" */
            this.setNameToMap(
                    DriverVO.INIT_CODES[4],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0258"), language.getLangseq().intValue());
            /* @res "BOM材料消耗定额" */
            this.setNameToMap(
                    DriverVO.INIT_CODES[5],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0259"), language.getLangseq().intValue());
            /* @res "主联BOM材料消耗定额" */
            this.setNameToMap(
                    DriverVO.INIT_CODES[6],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0260"), language.getLangseq().intValue());
            /* @res "生产BOM主材消耗定额" */
            this.setNameToMap(
                    DriverVO.INIT_CODES[7],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0261"), language.getLangseq().intValue());
            /* @res "材料子项实际消耗数量" */
            this.setNameToMap(
                    DriverVO.INIT_CODES[8],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0262"), language.getLangseq().intValue());
            /* @res "主计划价" */
            this.setNameToMap(
                    DriverVO.INIT_CODES[9],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0263"), language.getLangseq().intValue());
            /* @res "主联计划价" */
            this.setNameToMap(
                    DriverVO.INIT_CODES[10],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0264"), language.getLangseq().intValue());
            /* @res "主参考成本" */
            this.setNameToMap(
                    DriverVO.INIT_CODES[11],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0265"), language.getLangseq().intValue());
            /* @res "主联参考成本" */
            this.setNameToMap(
                    DriverVO.INIT_CODES[12],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0266"), language.getLangseq().intValue());
            /* @res "生产订单材料消耗定额" */
            this.setNameToMap(
                    DriverVO.INIT_CODES[13],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0267"), language.getLangseq().intValue());
            /* @res "工艺路线材料消耗定额" */
            this.setNameToMap(
                    DriverVO.INIT_CODES[14],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0268"), language.getLangseq().intValue());
            /* @res "BOM联副产品产出定额" */
            // this.setNameToMap(
            // DriverVO.INIT_CODES[15],
            // NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
            // "03810006-0269"), language.getLangseq().intValue());
        }
    }

    private void setNameToMap(String initCode, String name, int seq) {
        String[] names = this.nameMap.get(initCode);
        if (names == null) {
            names = new String[7];
            this.nameMap.put(initCode, names);
        }
        if (seq > 6) {
            return;
        }
        names[seq] = name;
    }

    /**
     * 预置成本动因公式
     */
    private void setFormulaValueMap() {

        for (LanguageVO language : this.languages) {
            if (language.getLangseq() == null) {
                continue;
            }
            /* @res "{合格品产量}+{废品产量*废品约当量}" */
            this.setFormulaToMap(
                    DriverVO.INIT_CODES[0],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0270"), language.getLangseq().intValue());
            /* @res "{合格品产量}+{废品产量*废品约当量}+{联合格品量}+{联废品产量*废品约当系数}" */
            this.setFormulaToMap(
                    DriverVO.INIT_CODES[1],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0271"), language.getLangseq().intValue());
            /* @res "{合格品量}+{废品产量*废品约当系数}+{在产品产量*在产约当系数}-{上期在产品约当产量}+{联合格品量}+{联废品产量*废品约当系数}+{副产品产量}" */
            this.setFormulaToMap(
                    DriverVO.INIT_CODES[2],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0272"), language.getLangseq().intValue());
            /* @res "{在产品产量*在产约当系数}" */
            this.setFormulaToMap(
                    DriverVO.INIT_CODES[3],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0273"), language.getLangseq().intValue());
            /* @res "{合格品产量}+{废品产量*废品约当量}+{在产品产量*在产约当系数}-{上期在产品约当产量}" */
            this.setFormulaToMap(
                    DriverVO.INIT_CODES[4],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0274"), language.getLangseq().intValue());
            /* @res "{BOM材料消耗定额}*({合格品产量}+{废品产量*废品约当量}) */
            this.setFormulaToMap(
                    DriverVO.INIT_CODES[5],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0275"), language.getLangseq().intValue());
            /* @res "{BOM材料消耗定额}*({合格品产量}+{废品产量*废品约当量} + {联合格品量}+{联废品产量*废品约当系数})" */
            this.setFormulaToMap(
                    DriverVO.INIT_CODES[6],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0276"), language.getLangseq().intValue());
            /* @res "{生产BOM主材消耗定额}*({合格品产量}+{废品产量*废品约当量})" */
            this.setFormulaToMap(
                    DriverVO.INIT_CODES[7],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0277"), language.getLangseq().intValue());
            /* @res "{材料子项实际消耗数量}" */
            this.setFormulaToMap(
                    DriverVO.INIT_CODES[8],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0278"), language.getLangseq().intValue());
            /* @res "{计划价}*({合格品产量}+{废品产量}*{废品约当系数})" */
            this.setFormulaToMap(
                    DriverVO.INIT_CODES[9],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0279"), language.getLangseq().intValue());
            /* @res "{计划价}*({合格品产量}+{废品产量}*{废品约当系数}+{联合格品量}+{联废品产量*废品约当系数})" */
            this.setFormulaToMap(
                    DriverVO.INIT_CODES[10],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0280"), language.getLangseq().intValue());
            /* @res "{参考成本}*({合格品产量}+{废品产量}*{废品约当系数})" */
            this.setFormulaToMap(
                    DriverVO.INIT_CODES[11],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0281"), language.getLangseq().intValue());
            /* @res "{参考成本}*({合格品产量}+{废品产量}*{废品约当系数} + {联合格品量}+{联废品产量*废品约当系数})" */
            this.setFormulaToMap(
                    DriverVO.INIT_CODES[12],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0282"), language.getLangseq().intValue());
            /* @res "{生产订单材料消耗定额}*({合格品产量}+{废品产量*废品约当量})" */
            this.setFormulaToMap(
                    DriverVO.INIT_CODES[13],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0283"), language.getLangseq().intValue());
            /* @res "{工艺路线材料消耗定额}*({合格品产量}+{废品产量*废品约当量})" */
            this.setFormulaToMap(
                    DriverVO.INIT_CODES[14],
                    NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
                            "03810006-0284"), language.getLangseq().intValue());
            /* @res "{(合格品量+废品产量)*BOM联副产品产出定额}" */
            // this.setFormulaToMap(
            // DriverVO.INIT_CODES[15],
            // NCLangResOnserver.getInstance().getString(language.getLangcode(), "3810006_0", null,
            // "03810006-0285"), language.getLangseq().intValue());
        }
    }

    private void setFormulaToMap(String initCode, String formula, int seq) {
        String[] formulas = this.formulaValueMap.get(initCode);
        if (formulas == null) {
            formulas = new String[7];
            this.formulaValueMap.put(initCode, formulas);
        }
        if (seq > 6) {
            return;
        }
        formulas[seq] = formula;
    }

    /**
     * 预置成本动因CODE
     */
    private void setFormulaCodeMap() {
        // 合格品产量+废品产量*废品约当系数
        this.formulaCodeMap.put(DriverVO.INIT_CODES[0], "{" + CMDriverParameterEnum.QUALIFIED_NUMBER.getCode() + "}+{"
                + CMDriverParameterEnum.WASTEPRODUCT_RATE.getCode() + "}");
        // 合格品产量+废品产量*废品约当系数+联合格品量+联废品约当量
        this.formulaCodeMap.put(DriverVO.INIT_CODES[1], "{" + CMDriverParameterEnum.QUALIFIED_NUMBER.getCode() + "}+{"
                + CMDriverParameterEnum.WASTEPRODUCT_RATE.getCode() + "}+{"
                + CMDriverParameterEnum.JOINT_QUALIFIED_NUMBER.getCode() + "}+{"
                + CMDriverParameterEnum.JOINT_WASTEPRODUCT_RATE.getCode() + "}");
        // 合格品产量+废品产量*废品约当系数+在产约当量+联合格品量+联废品约当量+副产品产量
        this.formulaCodeMap.put(
                DriverVO.INIT_CODES[2],
                "{" + CMDriverParameterEnum.QUALIFIED_NUMBER.getCode() + "}+{"
                        + CMDriverParameterEnum.WASTEPRODUCT_RATE.getCode() + "}+{"
                        + CMDriverParameterEnum.ON_PRODUCT_RATE.getCode() + "}-{"
                        + CMDriverParameterEnum.INPRO_APPRONUM + "}+{"
                        + CMDriverParameterEnum.JOINT_QUALIFIED_NUMBER.getCode() + "}+{"
                        + CMDriverParameterEnum.JOINT_WASTEPRODUCT_RATE.getCode() + "}+{"
                        + CMDriverParameterEnum.BY_PRODUCT_NUMBER.getCode() + "}");
        // 在产约当量
        this.formulaCodeMap.put(DriverVO.INIT_CODES[3], "{" + CMDriverParameterEnum.ON_PRODUCT_RATE.getCode() + "}");
        // 合格品产量+废品产量*废品约当系数+在产约当量
        this.formulaCodeMap.put(DriverVO.INIT_CODES[4],
                "{" + CMDriverParameterEnum.QUALIFIED_NUMBER.getCode() + "}+{"
                        + CMDriverParameterEnum.WASTEPRODUCT_RATE.getCode() + "}+{"
                        + CMDriverParameterEnum.ON_PRODUCT_RATE.getCode() + "}-{"
                        + CMDriverParameterEnum.INPRO_APPRONUM + "}");
        // BOM材料消耗定额 *(合格品产量+废品产量*废品约当系数)
        this.formulaCodeMap.put(DriverVO.INIT_CODES[5], "{" + CMDriverParameterEnum.BOM_STUFF_CONSUME_QUOTA.getCode()
                + "}*({" + CMDriverParameterEnum.QUALIFIED_NUMBER.getCode() + "}+{"
                + CMDriverParameterEnum.WASTEPRODUCT_RATE.getCode() + "})");
        // BOM材料消耗定额 *(合格品产量+废品产量*废品约当系数 + 联合格品量+联废品约当量)
        this.formulaCodeMap.put(DriverVO.INIT_CODES[6], "{" + CMDriverParameterEnum.BOM_STUFF_CONSUME_QUOTA.getCode()
                + "}*({" + CMDriverParameterEnum.QUALIFIED_NUMBER.getCode() + "}+{"
                + CMDriverParameterEnum.WASTEPRODUCT_RATE.getCode() + "}+{"
                + CMDriverParameterEnum.JOINT_QUALIFIED_NUMBER.getCode() + "}+{"
                + CMDriverParameterEnum.JOINT_WASTEPRODUCT_RATE.getCode() + "})");
        // BOM主材消耗定额 * (合格品产量+废品产量*废品约当系数)
        this.formulaCodeMap.put(DriverVO.INIT_CODES[7], "{" + CMDriverParameterEnum.MAIN_STUFF_CONSUME_QUOTA.getCode()
                + "}*({" + CMDriverParameterEnum.QUALIFIED_NUMBER.getCode() + "}+{"
                + CMDriverParameterEnum.WASTEPRODUCT_RATE.getCode() + "})");
        // "材料子项实际消耗数量"
        this.formulaCodeMap
                .put(DriverVO.INIT_CODES[8], "{" + CMDriverParameterEnum.ACTUAL_STUFF_NUMBER.getCode() + "}");
        // "计划价 * (合格品产量+废品产量*废品约当系数)"
        this.formulaCodeMap.put(
                DriverVO.INIT_CODES[9],
                "{" + CMDriverParameterEnum.PLAN_PRICE.getCode() + "}*({"
                        + CMDriverParameterEnum.QUALIFIED_NUMBER.getCode() + "}+{"
                        + CMDriverParameterEnum.WASTEPRODUCT_RATE.getCode() + "})");
        // 计划价 *(合格品产量+废品产量*废品约当系数 + 联合格品量+联废品约当量)
        this.formulaCodeMap.put(
                DriverVO.INIT_CODES[10],
                "{" + CMDriverParameterEnum.PLAN_PRICE.getCode() + "}*({"
                        + CMDriverParameterEnum.QUALIFIED_NUMBER.getCode() + "}+{"
                        + CMDriverParameterEnum.WASTEPRODUCT_RATE.getCode() + "}+{"
                        + CMDriverParameterEnum.JOINT_QUALIFIED_NUMBER.getCode() + "}+{"
                        + CMDriverParameterEnum.JOINT_WASTEPRODUCT_RATE.getCode() + "})");
        // "参考成本 * (合格品产量+废品产量*废品约当系数)"
        this.formulaCodeMap.put(
                DriverVO.INIT_CODES[11],
                "{" + CMDriverParameterEnum.REFERENCE_COST.getCode() + "}*({"
                        + CMDriverParameterEnum.QUALIFIED_NUMBER.getCode() + "}+{"
                        + CMDriverParameterEnum.WASTEPRODUCT_RATE.getCode() + "})");
        // "参考成本 *(合格品产量+废品产量*废品约当系数 + 联合格品量+联废品约当量)"
        this.formulaCodeMap.put(
                DriverVO.INIT_CODES[12],
                "{" + CMDriverParameterEnum.REFERENCE_COST.getCode() + "}*({"
                        + CMDriverParameterEnum.QUALIFIED_NUMBER.getCode() + "}+{"
                        + CMDriverParameterEnum.WASTEPRODUCT_RATE.getCode() + "}+{"
                        + CMDriverParameterEnum.JOINT_QUALIFIED_NUMBER.getCode() + "}+{"
                        + CMDriverParameterEnum.JOINT_WASTEPRODUCT_RATE.getCode() + "})");
        // "生产订单材料消耗定额*(合格品产量+废品产量*废品约当系数)"
        this.formulaCodeMap.put(DriverVO.INIT_CODES[13], "{" + CMDriverParameterEnum.MO_STUFF_CONSUME_QUOTA.getCode()
                + "}*({" + CMDriverParameterEnum.QUALIFIED_NUMBER.getCode() + "}+{"
                + CMDriverParameterEnum.WASTEPRODUCT_RATE.getCode() + "})");
        // "工艺路线材料消耗定额*(合格品产量+废品产量*废品约当系数)"
        this.formulaCodeMap.put(DriverVO.INIT_CODES[14], "{" + CMDriverParameterEnum.RT_STUFF_CONSUME_QUOTA.getCode()
                + "}*({" + CMDriverParameterEnum.QUALIFIED_NUMBER.getCode() + "}+{"
                + CMDriverParameterEnum.WASTEPRODUCT_RATE.getCode() + "})");
        // // "(合格品量+废品产量)*BOM联副产品产出定额"
        // this.formulaCodeMap.put(DriverVO.INIT_CODES[15], "({" + CMDriverParameterEnum.QUALIFIED_NUMBER.getCode()
        // + "}+{" + CMDriverParameterEnum.WASTEPRODUCT_NUMBER.getCode() + "})*{"
        // + CMDriverParameterEnum.BOM_JOINTBY_OUTPUT_QUOTA.getCode() + "}");

    }

}
