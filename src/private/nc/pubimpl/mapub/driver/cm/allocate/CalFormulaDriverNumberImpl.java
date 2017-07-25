package nc.pubimpl.mapub.driver.cm.allocate;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bd.framework.base.CMStringUtil;
import nc.bs.mapub.driver.util.AnalysisFormulaTools;
import nc.bs.mapub.driver.util.ExpressionTools;
import nc.bs.mapub.driver.util.VariableMapTreeUtil;
import nc.pubitf.mapub.driver.cm.allocate.ICalFormulaDriverNumber;
import nc.pubitf.mapub.driver.cm.allocate.IDriverFormulaAquireData;
import nc.vo.cmpub.framework.assistant.CMMarAssInfoVO;
import nc.vo.mapub.allocfac.enumeration.AllocfacEnum;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.CMDriverParameterEnum;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <b> ���ݹ�ʽ���㶯��ʵ���� </b>
 * <p>
 * ��ϸ��������
 * </p>
 * ��������:2010-4-20
 *
 * @author:wangtf
 */
public class CalFormulaDriverNumberImpl implements ICalFormulaDriverNumber {

    AnalysisFormulaTools analysisTools;

    private AnalysisFormulaTools getAnalysisTools() {
        if (this.analysisTools == null) {
            this.analysisTools = new AnalysisFormulaTools();
        }
        return this.analysisTools;
    }

    @Override
    public void aquireDriverNum(DriverVO driver, IDriverFormulaAquireData aquireData, String costType)
            throws BusinessException {
        String formula = driver.getVformulacode();
        Set<String> variableList = this.getAnalysisTools().analyseFormula(formula);
        for (String variable : variableList) {
            this.aquireVariableNum(variable, aquireData, costType);
        }
    }

    @Override
    public UFDouble calculate(String formula, IDriverFormulaAquireData aquireData, String costcenterId,
            String costobjId, String celementId, CMMarAssInfoVO itemMarAssInfoVO) throws BusinessException {
        if (aquireData == null) {
            throw new BusinessException(CMDriverLangConst.getERR_NO_FETCHDATA_CLASS());
        }
        if (null == aquireData.getVariableMap() || aquireData.getVariableMap().size() == 0) {
            // ����ʽ�б���ֵΪ�յ�ʱ���ܼ������м��㣬�����
            throw new BusinessException(CMDriverLangConst.getERR_EXIST_NULL_VARIABLE());
        }

        Map<String, UFDouble> variableMap = this.getAnalysisTools().analysis(formula);
        for (Entry<String, UFDouble> entry : variableMap.entrySet()) {
            UFDouble value =
                    this.getVariable(aquireData, entry.getKey(), costcenterId, costobjId, celementId, itemMarAssInfoVO);
            if (value == null) {
                // ����ʽ�б���ֵΪ�յ�ʱ���ܼ������м��㣬�����
                throw new BusinessException(CMDriverLangConst.getERR_EXIST_NULL_VARIABLE());
            }
            entry.setValue(value);
        }

        // ���㹫ʽ��ֵ
        UFDouble retValue = this.calFormula(formula, variableMap);

        return retValue;
    }

    /**
     * ׼����ʽ����ֵ
     *
     * @param formula
     *            ��ʽ
     * @param aquireData
     *            ȡ����
     * @param costType
     *            �ɱ�����
     */
    private void aquireVariableNum(String variable, IDriverFormulaAquireData aquireData, String costType)
            throws BusinessException {
        // ��ȡ��ʽ�еı���
        Map<String, Object> formulaMap = this.getAnalysisTools().analyseCode(variable);
        String paramcode = (String) formulaMap.get("paramCode");
        String[] paramValue1 = (String[]) formulaMap.get("paramValue1");
        String[] paramValue2 = (String[]) formulaMap.get("paramValue2");
        String singelValue1 = null;
        if (paramValue1 != null) {
            singelValue1 = paramValue1[0];
        }
        String singelValue2 = null;
        if (paramValue2 != null) {
            singelValue2 = paramValue2[0];
        }
        if (paramcode.equals(CMDriverParameterEnum.QUALIFIED_NUMBER.getCode())) { // �ϸ�Ʒ
            aquireData.aquireQualifiedNumber(variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.ON_PRODUCT_RATE.getCode())) { // �ڲ�
            aquireData.aquireOnProductRate(variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.JOINT_QUALIFIED_NUMBER.getCode())) { // ����Ʒ
            aquireData.aquireJointQualifiedNumber(variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.BY_PRODUCT_NUMBER.getCode())) { // ����Ʒ
            aquireData.aquireByProductNumber(variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.WASTEPRODUCT_NUMBER.getCode())) { // ��Ʒ
            aquireData.aquireWasteProductNumber(variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.WASTEPRODUCT_RATE.getCode())) { // ��ƷԼ��
            aquireData.aquireWasteProductRate(variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.JOINT_WASTEPRODUCT_NUMBER.getCode())) { // ����Ʒ
            aquireData.aquireJointWasteProductNumber(variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.JOINT_WASTEPRODUCT_RATE.getCode())) { // ����ƷԼ��
            aquireData.aquireJointWasteProductRate(variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.BOM_STUFF_CONSUME_QUOTA.getCode())) { // Bom�������Ķ���
            aquireData.aquireBomStuffConsumeQuota(variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.RT_STUFF_CONSUME_QUOTA.getCode())) { // ����·�߲������Ķ���
            aquireData.aquireRTStuffConsumeQuota(variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.MO_STUFF_CONSUME_QUOTA.getCode())) { // ���������������Ķ���
            aquireData.aquireMOStuffConsumeQuota(variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.MAIN_STUFF_CONSUME_QUOTA.getCode())) { // ���������Ķ���
            aquireData.aquireMainStuffConsumeQuota(variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.ACTUAL_STUFF_NUMBER.getCode())) { // ��������ʵ����������
            aquireData.aquireActualStuffNumber(variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.ASSIGN_STUFF_ACTUAL_NUMBER.getCode())) { // ָ����������ʵ����������
            aquireData.aquireAssignStuffActualNumber(singelValue1, variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.ASSIGN_STUFF_ACTUAL_MONEY.getCode())) { // ָ����������ʵ�����Ľ��
            aquireData.aquireAssignStuffActualMoney(singelValue1, variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.PLAN_PRICE.getCode())) { // �ƻ���
            aquireData.aquirePlanPrice(variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.REFERENCE_COST.getCode())) { // �ο��ɱ�
            aquireData.aquireReferenceCost(variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.REFERENCE_SALE_PRICE.getCode())) { // �ο����ۼ�
            aquireData.aquireReferenceSalePrice(paramValue1, variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.STANDARD_COST.getCode())) { // ��׼�ɱ�
            aquireData.aquireStandardCost(costType, variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.PRICE_LIBRARY.getCode())) { // �۸��
            aquireData.aquirePriceLibrary(paramValue1, variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.ACTUAL_ACTIVITY_NUMBER.getCode())) { // ʵ����ҵ��
            aquireData.aquireActualActivityNumber(singelValue1, variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.ACTUAL_ACTIVITY_FINISH_NUMBER.getCode())) { // ʵ����ҵ�깤��
            aquireData.aquireActualActivityFinishNumber(singelValue1, variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.STANDARD_ACTIVITY_NUMBER.getCode())) { // �ɱ�BOM��׼��ҵ��
            aquireData.aquireStandardActivityNumber(singelValue1, costType, variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.BOM_ACTIVITY_NUMBER.getCode())) { // ����BOM��׼��ҵ��
            aquireData.aquireBOMActivityNumber(singelValue1, variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.ALLOC_FAC.getCode())) { // ����ϵ��
            if (singelValue1.equals("6")) {
                ExceptionUtils.wrappBusinessException(CMDriverLangConst.getERR_NO_SUPORT_DRIVER()
                        + CMDriverLangConst.getCOSTOBJECT_ALLOCFAC() + CMDriverLangConst.getALLOCFAC());
            }
            aquireData.aquireAllocfac(singelValue2, singelValue1, variable);
        }
        else if (paramcode.equals(CMDriverParameterEnum.FACTOR.getCode())) { // �ɱ�Ҫ��
            aquireData.aquireFactor(singelValue1, variable);
        }
        else if (paramcode.equals(CMDriverLangConst.OTHER)) { // ����
            aquireData.aquireOther(singelValue1, variable);
        }
        else if (CMDriverParameterEnum.BOM_JOINTBY_OUTPUT_QUOTA.getCode().equals(paramcode)) {// BOM������Ʒ��������
            aquireData.aquireBomJointByOutputQuota(variable);
        }
        else if (CMDriverParameterEnum.RT_ACTIVITY_NUMBER.getCode().equals(paramcode)) {// ����·�ߵ�λ��׼��ҵ��
            aquireData.aquireRTActivityNumber(singelValue1, variable);
        }
        else if (CMDriverParameterEnum.INPRO_APPRONUM.getCode().equals(paramcode)) {// �ڳ��ڲ�ƷԼ������
            aquireData.aquireInproApproNum(variable);
        }
        else if (CMDriverParameterEnum.ASSIN_STUFF_BOM_QUOTA.getCode().equals(paramcode)) {// ָ����������BOM���Ķ���
            aquireData.aquireAssinStuffBomQuota(singelValue1, variable);
        }
        else if (CMDriverParameterEnum.STANDARD_ACTIVITY_NUMBER.getCode().equals(paramcode)) {
            ExceptionUtils.wrappBusinessException(CMDriverLangConst.getERR_NO_SUPORT_DRIVER()
                    + CMDriverLangConst.getSTANDARD_ACTIVITY_NUMBER());
        }
        else {
            ExceptionUtils.wrappBusinessException(CMDriverLangConst.getERR_NO_SUPORT_DRIVER() + paramcode);
        }
    }

    /**
     * ��ñ�����ֵ
     *
     * @param formula
     *            ��ʽ
     * @param variableMap
     *            ����Map
     * @return ������ֵ
     */
    private UFDouble getVariable(IDriverFormulaAquireData aquireData, String variable, String costcenterId,
            String costobjId, String celementId, CMMarAssInfoVO itemMarAssInfoVO) {
        // ��ȡ��ʽ�еı���
        Map<String, Object> formulaMap = this.getAnalysisTools().analyseCode(variable);
        List<Object> variableKeyList =
                aquireData.getVariableKeyList(variable, costcenterId, costobjId, celementId, itemMarAssInfoVO,
                        formulaMap);

        return new VariableMapTreeUtil(aquireData.getVariableMap(), variableKeyList).getValue(
                aquireData.hasMaterialId(), aquireData.isProductAllocate());
    }

    /**
     * ���㹫ʽ
     *
     * @param formula
     *            ��ʽ
     * @param variable
     *            ����
     * @return ������ֵ
     */
    private UFDouble calFormula(String formula, Map<String, UFDouble> variableMap) {
        String newformula = formula.replaceAll("\\{", "");
        newformula = newformula.replaceAll("\\}", "");

        // ���������࣬��ʱ��֧�ָ������㣬Ϊ���Ч�ʣ�����
        // FormulaParseFather f = new FormulaParse();
        // f.setExpress(newformula);
        //
        // for (String[] strings : variable) {
        // String fml = strings[0];
        // fml = fml.replaceAll("\\[", "\\$");
        // fml = fml.replaceAll("\\]", "\\$");
        // f.addVariable(fml, new UFDouble(strings[1]));
        // }
        //
        // String result = f.getValue();
        // ��������򻯼�����
        ExpressionTools et = new ExpressionTools(newformula);
        String result = et.getResult(variableMap);
        return new UFDouble(result);
    }

    @Override
    public UFBoolean checkOnlyCenter(String formula, UFBoolean beCoIsNull) {
        UFBoolean onlyCenter = UFBoolean.FALSE;
        if (CMStringUtil.isEmpty(formula)) {
            ExceptionUtils.wrappBusinessException(CMDriverLangConst.getERR_NO_FOMULA());
        }
        else {
            Set<String> itemList = this.getAnalysisTools().analyseFormula(formula);
            for (String item : itemList) {
                if (item.startsWith(CMDriverParameterEnum.ALLOC_FAC.getCode() + "["
                        + AllocfacEnum.costcenter.toIntValue() + "]")) {
                    onlyCenter = UFBoolean.TRUE;
                }
                else if (item.equals(CMDriverParameterEnum.ACTUAL_STUFF_NUMBER.getCode())
                        || item.startsWith(CMDriverParameterEnum.ACTUAL_ACTIVITY_NUMBER.getCode())
                        || item.startsWith(CMDriverParameterEnum.FACTOR.getCode())
                        || item.startsWith(CMDriverParameterEnum.ASSIGN_STUFF_ACTUAL_NUMBER.getCode())
                        || item.startsWith(CMDriverParameterEnum.ASSIGN_STUFF_ACTUAL_MONEY.getCode())) {
                    onlyCenter = beCoIsNull;
                }
                else {
                    onlyCenter = UFBoolean.FALSE;
                    break;
                }
            }
        }
        return onlyCenter;
    }

}
