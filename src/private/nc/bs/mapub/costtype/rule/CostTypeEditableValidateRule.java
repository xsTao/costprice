/**
 *
 */
package nc.bs.mapub.costtype.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bd.framework.base.CMArrayUtil;
import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.mapub.costtype.ICostTypeService;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2015��5��18�� ����9:20:59
 * @author zhangshyb
 */
public class CostTypeEditableValidateRule implements IRule<CostTypeVO> {

    @Override
    public void process(CostTypeVO[] vos) {
        try {
            if (CMArrayUtil.isNotEmpty(vos)) {
                this.editableValidate(vos);
            }
        }
        catch (ValidationException e) {
            ExceptionUtils.wrappException(e);
        }
    }

    /**
     * �ֶοɱ༭У��
     * 1.���ڳɱ�BOM���ݵĳɱ����ͣ�ֻ�����޸ĳɱ����ͱ��롢�ɱ��������ơ����ϼ۸���Դ����Ч���ڡ�ʧЧ���ںͱ�ע�������ֶβ�����༭��
     * 2.���ڳɱ�BOM���ݵĳɱ����ͣ��ҳɱ�BOM�����Ѽ���ģ��������޸����ϼ۸���Դ����������޸ģ�
     */
    public void editableValidate(CostTypeVO[] updateCostTypeVOs) throws ValidationException {
        List<ValidationFailure> failure = new ArrayList<ValidationFailure>();
        StringBuffer errStr = new StringBuffer();
        StringBuffer errStr0 = new StringBuffer();
        StringBuffer errStr1 = new StringBuffer();
        ICostTypeService service = NCLocator.getInstance().lookup(ICostTypeService.class);
        // ����bom���ݵ�δ����
        Set<String> data = new HashSet<String>();
        // ����bom�������Ѿ�����
        Set<String> calcDate = new HashSet<String>();
        // ��ʼ��
        data = service.haveDate();
        calcDate = service.haveCalcDate();
        // �п�
        for (CostTypeVO costTypeVO : updateCostTypeVOs) {
            String ccosttypeid = costTypeVO.getCcosttypeid();
            String vmaterialpricesourcenum = costTypeVO.getVmaterialpricesourcenum();
            String scrapfactor = costTypeVO.getBscrapfactor().toString();
            String shrinkfactor = costTypeVO.getBshrinkfactor().toString();
            String pk_elementsystem = costTypeVO.getPk_elementsystem();
            String pk_factorchart = costTypeVO.getPk_factorchart();
            String pk_materialdocview = costTypeVO.getPk_materialdocview();
            // �����Ѽ���bom����
            if (calcDate != null && calcDate.contains(ccosttypeid)) {
                if (service.isMaterialSrcChanged(ccosttypeid, scrapfactor, shrinkfactor, pk_elementsystem,
                        pk_factorchart, pk_materialdocview, vmaterialpricesourcenum)) {
                    // ����[����]
                    errStr1.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                    errStr1.append(",");
                }
            }
            else {
                // ����bom����
                if (data != null && data.contains(ccosttypeid)) {
                    if (service.isValueChanged(ccosttypeid, scrapfactor, shrinkfactor, pk_elementsystem,
                            pk_factorchart, pk_materialdocview)) {
                        // ����[����]
                        errStr0.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                        errStr0.append(",");
                    }
                }
            }
        }
        if (errStr0.length() > 0 || errStr1.length() > 0) {
            errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0301")/*
             * ���ڲ��Ϸ��༭��\
             * n
             */);
        }
        if (errStr0.length() > 0) {
            errStr0.deleteCharAt(errStr0.length() - 1); // Ҫȥ�����һ��","
            errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0302")/*
             * ���ڳɱ�BOM���ݵĳɱ�����
             * ��
             * ֻ�����޸ĳɱ����ͱ���
             * ���ɱ��������ơ�
             * ���ϼ۸���Դ
             * ����Ч����
             * ��ʧЧ���ںͱ�ע
             * ��
             * �����ֶβ�����༭
             * :
             */);
            errStr.append(errStr0.toString());
            errStr.append("\n");
        }
        if (errStr1.length() > 0) {
            errStr1.deleteCharAt(errStr1.length() - 1); // Ҫȥ�����һ��","
            errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0303")/*
                                                                                                              * ���ڳɱ�BOM���ݵĳɱ�����
                                                                                                              * ��
                                                                                                              * �ҳɱ�BOM�Ѽ����
                                                                                                              * ��
                                                                                                              * ֻ�����޸ĳɱ����ͱ���
                                                                                                              * ��
                                                                                                              * �ɱ��������ơ���Ч����
                                                                                                              * ��
                                                                                                              * ʧЧ���ںͱ�ע��
                                                                                                              * �����ֶβ�����༭
                                                                                                              * :
                                                                                                              */);
            errStr.append(errStr1.toString());
            errStr.append("\n");
        }
        if (errStr.length() > 0) {
            // ���������Ĵ��󾯸���Ϣ,����У�龯��
            failure.add(new ValidationFailure(errStr.toString()));
            throw new ValidationException(failure); // У�龯�����
        }
    }

}
