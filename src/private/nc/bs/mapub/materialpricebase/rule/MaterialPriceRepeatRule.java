package nc.bs.mapub.materialpricebase.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMCollectionUtil;
import nc.bd.framework.base.CMValueCheck;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.cmpub.business.adapter.BDAdapter;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bd.material.MaterialVO;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseAggVO;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseBodyVO;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ���ϼ۸��
 * �����ظ�У��
 * 
 * @since 6.36
 * @version 2014-11-12 ����8:57:15
 * @author zhangchd
 */
public class MaterialPriceRepeatRule implements IRule<MaterialPriceBaseAggVO> {

    @Override
    public void process(MaterialPriceBaseAggVO[] vos) {
        if (CMArrayUtil.isEmpty(vos)) {
            return;
        }
        // 1����֤������ϱ���Ψһ��
        this.checkRepeatRule(vos);
    }

    private void checkRepeatRule(MaterialPriceBaseAggVO[] vos) {
        Set<String> errorSet = new HashSet<String>();

        ValidationException exception = new ValidationException();

        Set<String> materialRepeatSet = new HashSet<String>(); // ����+��������id
        List<String> materialRepeatList = new ArrayList<String>();// �ظ�����
        Map<String, Integer> rowAndMaterialMap = new HashMap<String, Integer>();

        for (MaterialPriceBaseAggVO vo : vos) {
            // ����
            MaterialPriceBaseBodyVO[] bodyVOs =
                    (MaterialPriceBaseBodyVO[]) vo.getChildren(MaterialPriceBaseBodyVO.class);

            if (CMArrayUtil.isNotEmpty(bodyVOs)) {
                int rowNO = 1;
                for (int i = 0; i < bodyVOs.length; i++) {
                    if (bodyVOs[i] != null) {
                        if (bodyVOs[i].getStatus() == VOStatus.DELETED) {
                            rowNO++;
                            continue;
                        }
                        if (materialRepeatSet.contains(this.getUniqueKey(bodyVOs[i]))) {
                            materialRepeatList.add(bodyVOs[i].getCmaterialid());
                            rowAndMaterialMap.put(bodyVOs[i].getCmaterialid(), Integer.valueOf(rowNO++));
                        }
                        else {
                            materialRepeatSet.add(this.getUniqueKey(bodyVOs[i]));
                            rowNO++;
                        }
                    }
                }
            }

            if (CMCollectionUtil.isNotEmpty(materialRepeatList)) {
                StringBuffer sb = new StringBuffer();

                Map<String, MaterialVO> matMap =
                        BDAdapter.queryMaterialBaseInfoByPks(
                                materialRepeatList.toArray(new String[materialRepeatList.size()]), new String[] {
                                    MaterialVO.CODE
                                });

                for (Map.Entry<String, MaterialVO> map : matMap.entrySet()) {
                    sb.append(String.format(CMMLangConstMaterialPriceBase.getMsgRowNum(),
                            rowAndMaterialMap.get(map.getKey()))); // ��%s��:���ϱ���

                    sb.append("[");
                    sb.append(map.getValue().getCode());
                    sb.append("]");

                    sb.append(CMMLangConstMaterialPriceBase.ITEMS_REPEAT_INFO()); // �����ظ������޸ģ�
                    sb.append("\r\n");
                }
                errorSet.add(sb.toString());

            }
        }

        ValidationFailure failure =
                new ValidationFailure(nc.vo.jcom.lang.StringUtil.getUnionStr(errorSet.toArray(new String[0]), "\n", ""));

        if (CMValueCheck.isNotEmpty(failure.getMessage())) {
            exception.addValidationFailure(failure);
        }

        if (exception.getFailures() != null && exception.getFailures().size() > 0) {
            ExceptionUtils.wrappException(exception);
        }

    }

    /**
     * �õ�Ψһ��
     * 
     * @param itemvo
     * @return String
     */
    private String getUniqueKey(MaterialPriceBaseBodyVO itemvo) {
        StringBuffer uniqueKey = new StringBuffer();

        if (CMValueCheck.isNotEmpty(itemvo.getCmaterialid())) {
            uniqueKey.append(itemvo.getCmaterialid());
        }
        if (CMValueCheck.isNotEmpty(itemvo.getCprojectid())) {
            uniqueKey.append(itemvo.getCprojectid());
        }
        if (CMValueCheck.isNotEmpty(itemvo.getCvendorid())) {
            uniqueKey.append(itemvo.getCvendorid());
        }
        if (CMValueCheck.isNotEmpty(itemvo.getCproductorid())) {
            uniqueKey.append(itemvo.getCproductorid());
        }
        if (CMValueCheck.isNotEmpty(itemvo.getCcustomerid())) {
            uniqueKey.append(itemvo.getCcustomerid());
        }
        if (CMValueCheck.isNotEmpty(itemvo.getVbfree1())) {
            uniqueKey.append(itemvo.getVbfree1());
        }
        if (CMValueCheck.isNotEmpty(itemvo.getVbfree2())) {
            uniqueKey.append(itemvo.getVbfree2());
        }
        if (CMValueCheck.isNotEmpty(itemvo.getVbfree3())) {
            uniqueKey.append(itemvo.getVbfree3());
        }
        if (CMValueCheck.isNotEmpty(itemvo.getVbfree4())) {
            uniqueKey.append(itemvo.getVbfree4());
        }
        if (CMValueCheck.isNotEmpty(itemvo.getVbfree5())) {
            uniqueKey.append(itemvo.getVbfree5());
        }
        if (CMValueCheck.isNotEmpty(itemvo.getVbfree6())) {
            uniqueKey.append(itemvo.getVbfree6());
        }
        if (CMValueCheck.isNotEmpty(itemvo.getVbfree7())) {
            uniqueKey.append(itemvo.getVbfree7());
        }
        if (CMValueCheck.isNotEmpty(itemvo.getVbfree8())) {
            uniqueKey.append(itemvo.getVbfree8());
        }
        if (CMValueCheck.isNotEmpty(itemvo.getVbfree9())) {
            uniqueKey.append(itemvo.getVbfree9());
        }
        if (CMValueCheck.isNotEmpty(itemvo.getVbfree10())) {
            uniqueKey.append(itemvo.getVbfree10());
        }

        return uniqueKey.toString();

    }

}
