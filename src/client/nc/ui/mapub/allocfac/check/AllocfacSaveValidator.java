package nc.ui.mapub.allocfac.check;

import java.util.ArrayList;
import java.util.Arrays;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMStringUtil;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.ui.cmpub.framework.validation.CMValidationException;
import nc.ui.mapub.allocfac.view.AllocfacBillForm;
import nc.ui.pub.bill.BillItem;
import nc.vo.mapub.allocfac.entity.AllocfacAggVO;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.allocfac.entity.AllocfacItemVO;
import nc.vo.mapub.allocfac.enumeration.AllocfacEnum;
import nc.vo.mapub.allocfac.util.CMMLangConstAllocfac;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;

/**
 * 分配系数的保存校验
 * <p>
 * 子表字段“系数”不能为0
 */
public class AllocfacSaveValidator implements nc.bs.uif2.validation.IValidationService {
    private static String RN = "\r\n";

    private AllocfacBillForm editor = null;

    public AllocfacBillForm getEditor() {
        return this.editor;
    }

    public void setEditor(AllocfacBillForm editor) {
        this.editor = editor;
    }

    @Override
    public void validate(Object obj) throws ValidationException {
        AllocfacAggVO aggVO = (AllocfacAggVO) obj;
        this.lengthCheck(aggVO, null, new String[] {
                "nfactor"
        });
        // 校验表体数据中，参照选择是否为空，系数是否为空,如果为空，提示空的信息
        this.bodyTableDataNullValidate(aggVO);
    }

    /**
     * 根据需要输出的行号、列名和错误信息，返回完整的错误提示信息
     *
     * @param strList
     *            需要输出的行号，如1、2（行）
     * @param item
     *            需要输出的列名，如[系数]
     * @param erroStr1
     *            需要输出的错误信息，如 保存错误：表体行系数不能为空
     * @return String 完整的错误信息 或者为空
     */
    public String getErroStr(ArrayList<String> strList, BillItem item, String erroStr1) {
        StringBuilder apps = new StringBuilder();
        if (strList != null) {
            for (int i = 0; i < strList.size(); i++) {
                apps.append(strList.get(i));
                apps.append(',');
            }
            apps.deleteCharAt(apps.length() - 1);
            apps.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0286")/*
             * @res
             * "(行)"
             */);
            apps.append(this.getErroStr(item));
        }
        if (apps.length() != 0) {
            return erroStr1 + AllocfacSaveValidator.RN + apps;
        }
        else {
            return null;
        }
    }

    /**
     * 返回 表体系数为0时的提示信息：提示语+字段名+字段值。
     *
     * @param item
     *            元数据字段。
     * @return String
     */
    public String getErroStr(BillItem item) {
        String erroStr = "[" + item.getName() + "]";
        return erroStr;
    }

    /**
     * 表体编码和系数的非空校验(没有使用框架的表体非空校验，原因是该子表是一子表，多页签)
     *
     * @param aggVO
     *            聚合VO
     * @throws ValidationException
     *             异常
     */
    public void bodyTableDataNullValidate(AllocfacAggVO aggVO) throws ValidationException {
        AllocfacHeadVO headvo = (AllocfacHeadVO) aggVO.getParent();
        AllocfacItemVO[] itemVOs = (AllocfacItemVO[]) aggVO.getChildrenVO();

        String itemKey = null;
        if (AllocfacEnum.costcenter.getEnumValue().getValue().equals(String.valueOf(headvo.getIalloctype()))) {
            itemKey = AllocfacItemVO.CCOSTCENTERID;
        }
        else if (AllocfacEnum.product.getEnumValue().getValue().equals(String.valueOf(headvo.getIalloctype()))) {
            itemKey = AllocfacItemVO.CMATERIALID;
        }
        else if (AllocfacEnum.costclass.getEnumValue().getValue().equals(String.valueOf(headvo.getIalloctype()))) {
            itemKey = AllocfacItemVO.CMARCOSTCLASSID;
        }
        else if (AllocfacEnum.baseclass.getEnumValue().getValue().equals(String.valueOf(headvo.getIalloctype()))) {
            itemKey = AllocfacItemVO.CMARBASECLASSID;
        }
        else if (AllocfacEnum.productvbfree.getEnumValue().getValue().equals(String.valueOf(headvo.getIalloctype()))) {
            itemKey = AllocfacItemVO.CMATERIALID;
        }
        else if (AllocfacEnum.activity.getEnumValue().getValue().equals(String.valueOf(headvo.getIalloctype()))) {
            itemKey = AllocfacItemVO.CACTIVITYID;
        }
        else if (AllocfacEnum.stuff.getEnumValue().getValue().equals(String.valueOf(headvo.getIalloctype()))) {
            itemKey = AllocfacItemVO.CSTUFFID;
        }

        String erroMessage = this.checkItem(headvo, itemVOs, itemKey);
        if (erroMessage == null) {
            return;
        }
        this.outputErroInfo(erroMessage.toString());
    }

    /**
     * 根据所选择的内容进行非空校验，一定要交验系数列
     *
     * @param headvo
     *            主表VO
     * @param itemVOs
     *            子表VOs
     * @param itemKey
     *            校验的列
     * @return 校验信息
     */
    private String checkItem(AllocfacHeadVO headvo, AllocfacItemVO[] itemVOs, String itemKey) {
        if (CMStringUtil.isEmpty(itemKey)) {
            return null;
        }
        StringBuilder erroStr = new StringBuilder();
        for (int i = 0; i < itemVOs.length; i++) {
            String erro =
                    this.getBodyTableDataNullValidateStr((String) itemVOs[i].getAttributeValue(itemKey),
                            itemVOs[i].getNfactor(), i, headvo.getIalloctype());
            if (itemKey.equals(AllocfacItemVO.CMATERIALID) && CMStringUtil.isEmpty(erro)) {
                erro =
                        this.getBodyTableDataNullValidateStr((String) itemVOs[i].getAttributeValue(itemKey + ".name"),
                                itemVOs[i].getNfactor(), i, headvo.getIalloctype());

            }
            if (erro != null && erro.length() > 0) {
                erroStr.append(erro);
                erroStr.append(AllocfacSaveValidator.RN);
            }
        }
        return erroStr.toString();
    }

    /**
     * 获取校验的信息体
     *
     * @param objectID
     *            校验属性id
     * @param nfactory
     *            校验属性之一的系数
     * @param lineNum
     *            校验行数
     * @param allocType
     *            校验类型
     * @return 错误信息
     */
    public String getBodyTableDataNullValidateStr(String objectID, UFDouble nfactory, int lineNum, int allocType) {
        StringBuilder erroStr = new StringBuilder();
        if (objectID == null) {
            if (nfactory == null) {
                // 系数为空的情况
                // 组成提示信息：第n行：[某编码，系数]
                erroStr.append(String.format(CMMLangConstAllocfac.getERROBODYITEMNULLSTR(),
                        String.valueOf(lineNum + 1), CMMLangConstAllocfac.getITEMNAMESTR()[allocType]
                                + CMMLangConstAllocfac.getCOMMASYMBOL() + CMMLangConstAllocfac.getITEMNAMESTR()[0]));

                erroStr.append(CMMLangConstAllocfac.getERROBODYITEMNULL());
            }
            else {
                /** 增加系数大于零判断 */
                if (nfactory.getDouble() <= 0) {
                    // 系数必须大于零
                    // 组成提示信息：第n行：[某编码]
                    erroStr.append(String.format(CMMLangConstAllocfac.getERROBODYITEMNULLSTR(),
                            String.valueOf(lineNum + 1), CMMLangConstAllocfac.getITEMNAMESTR()[0]));

                    erroStr.append(CMMLangConstAllocfac.getERRONFACTORY());
                }
                else {
                    // 系数不为空的情况
                    // 组成提示信息：第n行：[某编码]
                    erroStr.append(String.format(CMMLangConstAllocfac.getERROBODYITEMNULLSTR(),
                            String.valueOf(lineNum + 1), CMMLangConstAllocfac.getITEMNAMESTR()[allocType]));

                    erroStr.append(CMMLangConstAllocfac.getERROBODYITEMNULL());
                }
            }
        }
        else {
            if (nfactory == null) {
                // 系数为空的情况
                // 组成提示信息：第n行：[某编码，系数]
                erroStr.append(String.format(CMMLangConstAllocfac.getERROBODYITEMNULLSTR(),
                        String.valueOf(lineNum + 1), CMMLangConstAllocfac.getITEMNAMESTR()[0]));

                erroStr.append(CMMLangConstAllocfac.getERROBODYITEMNULL());
            }
            /** 增加系数大于零判断 */
            else if (nfactory.getDouble() <= 0) {
                // 系数必须大于零的情况
                // 组成提示信息：第n行：[某编码]
                erroStr.append(String.format(CMMLangConstAllocfac.getERROBODYITEMNULLSTR(),
                        String.valueOf(lineNum + 1), CMMLangConstAllocfac.getITEMNAMESTR()[0]));
                erroStr.append(CMMLangConstAllocfac.getERRONFACTORY());
            }
        }
        return erroStr.toString();
    }

    /**
     * 组成最终错误信息并输出
     *
     * @param erroStr
     *            错误信息体
     * @exception ValidationException
     *                异常
     */
    public void outputErroInfo(String erroStr) throws ValidationException {
        if (erroStr != null && erroStr.length() > 0) {
            ValidationFailure failure = new ValidationFailure(erroStr.toString());
            throw new CMValidationException(failure);
        }
    }

    private void lengthCheck(AggregatedValueObject bill, String[] headItemCode, String[] bodyItemCode)
            throws ValidationException {
        if (CMArrayUtil.isNotEmpty(headItemCode)) {
            CircularlyAccessibleValueObject headVO = bill.getParentVO();
            AllocfacSaveValidator.checkNum(headVO, headItemCode);
        }
        if (CMArrayUtil.isNotEmpty(bodyItemCode)) {
            CircularlyAccessibleValueObject[] itemVOs = bill.getChildrenVO();
            for (CircularlyAccessibleValueObject itemVO : itemVOs) {
                AllocfacSaveValidator.checkNum(itemVO, bodyItemCode);
            }
        }
    }

    private static void checkNum(CircularlyAccessibleValueObject vo, String[] codes) throws ValidationException {
        for (String code : codes) {
            if (vo.getAttributeValue(code) instanceof UFDouble) {
                UFDouble num = (UFDouble) vo.getAttributeValue(code);
                String str = num.abs().toString();
                str = str.replaceAll("\\..*", "");
                if (str.length() > 24) {
                    String msg =
                            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("101436401_0", "0101436401-0546")/*
                             * @res
                             * "Excel文档中存在长度大于24的数值，请修改"
                             */;
                    ValidationFailure failure = new ValidationFailure(msg);
                    throw new ValidationException(Arrays.asList(failure));
                }
            }
        }
    }
}
