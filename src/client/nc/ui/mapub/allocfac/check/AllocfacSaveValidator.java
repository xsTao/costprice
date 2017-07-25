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
 * ����ϵ���ı���У��
 * <p>
 * �ӱ��ֶΡ�ϵ��������Ϊ0
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
        // У����������У�����ѡ���Ƿ�Ϊ�գ�ϵ���Ƿ�Ϊ��,���Ϊ�գ���ʾ�յ���Ϣ
        this.bodyTableDataNullValidate(aggVO);
    }

    /**
     * ������Ҫ������кš������ʹ�����Ϣ�����������Ĵ�����ʾ��Ϣ
     *
     * @param strList
     *            ��Ҫ������кţ���1��2���У�
     * @param item
     *            ��Ҫ�������������[ϵ��]
     * @param erroStr1
     *            ��Ҫ����Ĵ�����Ϣ���� ������󣺱�����ϵ������Ϊ��
     * @return String �����Ĵ�����Ϣ ����Ϊ��
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
             * "(��)"
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
     * ���� ����ϵ��Ϊ0ʱ����ʾ��Ϣ����ʾ��+�ֶ���+�ֶ�ֵ��
     *
     * @param item
     *            Ԫ�����ֶΡ�
     * @return String
     */
    public String getErroStr(BillItem item) {
        String erroStr = "[" + item.getName() + "]";
        return erroStr;
    }

    /**
     * ��������ϵ���ķǿ�У��(û��ʹ�ÿ�ܵı���ǿ�У�飬ԭ���Ǹ��ӱ���һ�ӱ���ҳǩ)
     *
     * @param aggVO
     *            �ۺ�VO
     * @throws ValidationException
     *             �쳣
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
     * ������ѡ������ݽ��зǿ�У�飬һ��Ҫ����ϵ����
     *
     * @param headvo
     *            ����VO
     * @param itemVOs
     *            �ӱ�VOs
     * @param itemKey
     *            У�����
     * @return У����Ϣ
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
     * ��ȡУ�����Ϣ��
     *
     * @param objectID
     *            У������id
     * @param nfactory
     *            У������֮һ��ϵ��
     * @param lineNum
     *            У������
     * @param allocType
     *            У������
     * @return ������Ϣ
     */
    public String getBodyTableDataNullValidateStr(String objectID, UFDouble nfactory, int lineNum, int allocType) {
        StringBuilder erroStr = new StringBuilder();
        if (objectID == null) {
            if (nfactory == null) {
                // ϵ��Ϊ�յ����
                // �����ʾ��Ϣ����n�У�[ĳ���룬ϵ��]
                erroStr.append(String.format(CMMLangConstAllocfac.getERROBODYITEMNULLSTR(),
                        String.valueOf(lineNum + 1), CMMLangConstAllocfac.getITEMNAMESTR()[allocType]
                                + CMMLangConstAllocfac.getCOMMASYMBOL() + CMMLangConstAllocfac.getITEMNAMESTR()[0]));

                erroStr.append(CMMLangConstAllocfac.getERROBODYITEMNULL());
            }
            else {
                /** ����ϵ���������ж� */
                if (nfactory.getDouble() <= 0) {
                    // ϵ�����������
                    // �����ʾ��Ϣ����n�У�[ĳ����]
                    erroStr.append(String.format(CMMLangConstAllocfac.getERROBODYITEMNULLSTR(),
                            String.valueOf(lineNum + 1), CMMLangConstAllocfac.getITEMNAMESTR()[0]));

                    erroStr.append(CMMLangConstAllocfac.getERRONFACTORY());
                }
                else {
                    // ϵ����Ϊ�յ����
                    // �����ʾ��Ϣ����n�У�[ĳ����]
                    erroStr.append(String.format(CMMLangConstAllocfac.getERROBODYITEMNULLSTR(),
                            String.valueOf(lineNum + 1), CMMLangConstAllocfac.getITEMNAMESTR()[allocType]));

                    erroStr.append(CMMLangConstAllocfac.getERROBODYITEMNULL());
                }
            }
        }
        else {
            if (nfactory == null) {
                // ϵ��Ϊ�յ����
                // �����ʾ��Ϣ����n�У�[ĳ���룬ϵ��]
                erroStr.append(String.format(CMMLangConstAllocfac.getERROBODYITEMNULLSTR(),
                        String.valueOf(lineNum + 1), CMMLangConstAllocfac.getITEMNAMESTR()[0]));

                erroStr.append(CMMLangConstAllocfac.getERROBODYITEMNULL());
            }
            /** ����ϵ���������ж� */
            else if (nfactory.getDouble() <= 0) {
                // ϵ���������������
                // �����ʾ��Ϣ����n�У�[ĳ����]
                erroStr.append(String.format(CMMLangConstAllocfac.getERROBODYITEMNULLSTR(),
                        String.valueOf(lineNum + 1), CMMLangConstAllocfac.getITEMNAMESTR()[0]));
                erroStr.append(CMMLangConstAllocfac.getERRONFACTORY());
            }
        }
        return erroStr.toString();
    }

    /**
     * ������մ�����Ϣ�����
     *
     * @param erroStr
     *            ������Ϣ��
     * @exception ValidationException
     *                �쳣
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
                             * "Excel�ĵ��д��ڳ��ȴ���24����ֵ�����޸�"
                             */;
                    ValidationFailure failure = new ValidationFailure(msg);
                    throw new ValidationException(Arrays.asList(failure));
                }
            }
        }
    }
}
