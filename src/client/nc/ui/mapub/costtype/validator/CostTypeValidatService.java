package nc.ui.mapub.costtype.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bd.business.util.FIUtil;
import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMCollectionUtil;
import nc.bd.framework.base.CMStringUtil;
import nc.bd.framework.base.CMValueCheck;
import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.cmpub.business.adapter.BDAdapter;
import nc.itf.fip.docview.IDocViewListService;
import nc.itf.mapub.costtype.ICostTypeService;
import nc.pubitf.accperiod.AccountCalendar;
import nc.ui.uif2.model.DefaultBatchValidationService;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.bd.period2.AccperiodmonthVO;
import nc.vo.fip.docview.DocViewListVO;
import nc.vo.fip.docview.SrcDocGroupVO;
import nc.vo.mapub.costtype.entity.CMMLangConstCM0502;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * <b> ���ݼ�� </b>
 * <p>
 * ��ϸ��������
 */
public class CostTypeValidatService extends DefaultBatchValidationService {

    @Override
    public int[] unNecessaryData(List<Object> rows) {
        // û������
        if (rows == null || rows.size() == 0) {
            return null;
        }
        ArrayList<Integer> filterRowList = new ArrayList<Integer>();
        for (int i = 0; i < rows.size(); i++) {
            CostTypeVO vo = (CostTypeVO) rows.get(i);
            // ����Ϊ�գ��͹��˿���
            if (StringUtils.isBlank(vo.getVcosttypecode())) {
                filterRowList.add(Integer.valueOf(i));
            }
        }
        if (filterRowList.size() > 0) {
            int[] filterRows = new int[filterRowList.size()];
            for (int i = 0; i < filterRows.length; i++) {
                filterRows[i] = filterRowList.get(i).intValue();
            }
            return filterRows;
        }
        return null;
    }

    @Override
    public void validate(Object obj) throws ValidationException {
        super.validate(obj);
        // List<CostTypeVO> updateCostTypeVOs = new ArrayList<CostTypeVO>();
        // updateCostTypeVOs = this.getAllEditedCostTypeVOs(obj);
        // try {
        // // �жϱ�׼�ɱ�ģ�飨3860���Ƿ�����
        // if (InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance().getGroupId(), "3860") == true
        // // ����CMElemRelationConst.java�ļ���û��SCA������˴����Ժ�Ҫɾ��
        // || InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance().getGroupId(), "3860") == false) {
        // if (!CMCollectionUtil.isEmpty(updateCostTypeVOs)) {
        // // �ɱ༭��У��
        // this.editableValidate(updateCostTypeVOs);
        // }
        // }
        // }
        // catch (BusinessException e) {
        // ExceptionUtils.wrappException(e);
        // }
        // // ȡ����ǰ����VO
        // CostTypeVO[] costTypeVOs = this.getAllCostTypeVOs();
        // if (costTypeVOs == null) {
        // return;
        // }
        // // У�飺Ҫ�������϶��ձ���Ҫ������Ӧ��Ҫ�ر��뵥����Ҫ�ر���һ��
        // this.factorChartEqualValidate(costTypeVOs);
        // // ���Ƚ�������У�飬�ж��Ƿ� ʧЧ�ڼ� >= ��Ч�ڼ� ����������¼2010-04-15�޸�
        // this.accPeriodValidate(costTypeVOs);
        // // ������Ҫ�ض��ձ���Դ����У��
        // this.docViewValidate(costTypeVOs);
        //
        // // �����ǰ���������еĳɱ����͵ġ��Ƿ�Ĭ�ϡ���Ϊ���򱨴�����ֻ��һ��Ϊ���ǡ�
        // // this.bDefaultValidate(costTypeVOs);

    }

    /**
     * �ֶοɱ༭У��
     * 1.���ڳɱ�BOM���ݵĳɱ����ͣ�ֻ�����޸ĳɱ����ͱ��롢�ɱ��������ơ����ϼ۸���Դ����Ч���ڡ�ʧЧ���ںͱ�ע�������ֶβ�����༭��
     * 2.���ڳɱ�BOM���ݵĳɱ����ͣ��ҳɱ�BOM�����Ѽ���ģ��������޸����ϼ۸���Դ����������޸ģ�
     */
    public void editableValidate(List<CostTypeVO> updateCostTypeVOs) throws ValidationException {
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
                if (this.isMaterialSrcChanged(ccosttypeid, scrapfactor, shrinkfactor, pk_elementsystem, pk_factorchart,
                        pk_materialdocview, vmaterialpricesourcenum)) {
                    // ����[����]
                    errStr1.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                    errStr1.append(",");
                }
            }
            else {
                // ����bom����
                if (data != null && data.contains(ccosttypeid)) {
                    if (this.isValueChanged(ccosttypeid, scrapfactor, shrinkfactor, pk_elementsystem, pk_factorchart,
                            pk_materialdocview)) {
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

    private boolean isMaterialSrcChanged(String ccosttypeid, String scrapfactor, String shrinkfactor,
            String pk_elementsystem, String pk_factorchart, String pk_materialdocview, String vmaterialpricesourcenum) {
        ICostTypeService service = NCLocator.getInstance().lookup(ICostTypeService.class);
        return service.isMaterialSrcChanged(ccosttypeid, scrapfactor, shrinkfactor, pk_elementsystem, pk_factorchart,
                pk_materialdocview, vmaterialpricesourcenum);

    }

    private boolean isValueChanged(String ccosttypeid, String scrapfactor, String shrinkfactor,
            String pk_elementsystem, String pk_factorchart, String pk_materialdocview) {
        ICostTypeService service = NCLocator.getInstance().lookup(ICostTypeService.class);
        return service.isValueChanged(ccosttypeid, scrapfactor, shrinkfactor, pk_elementsystem, pk_factorchart,
                pk_materialdocview);
    }

    /**
     * ����ǰУ��
     * Ҫ�������϶��ձ����Դ�����ֶ�У�飺
     * 1.���ϻ�����Ϣ�����ϻ������ࡢ���ϳɱ����ࡢ�������ͺͳɱ�������ѡ�ࣻ
     * 2.���ϻ�����Ϣ�����ϻ������ࡢ���ϳɱ������ѡ��һ��
     * 3.���ϻ������ࡢ���ϳɱ����໥�⡣
     */
    public void docViewValidate(CostTypeVO[] costTypeVOs) throws ValidationException {
        List<ValidationFailure> failure = new ArrayList<ValidationFailure>();
        StringBuffer errStr = new StringBuffer();
        StringBuffer errStr0 = new StringBuffer();
        StringBuffer errStr1 = new StringBuffer();
        StringBuffer errStr2 = new StringBuffer();
        if (CMArrayUtil.isNotEmpty(costTypeVOs)) {
            // ��ѡ�Ļ�������
            Set<String> setEnableSrcdoc = new HashSet<String>();
            setEnableSrcdoc = this.getEnableSrcdocid();
            // ����У�飬ֻҪ�����д���ģ��������������
            for (CostTypeVO costTypeVO : costTypeVOs) {
                // ��ѡ�Ļ�������
                Set<String> setSrcdoc = new HashSet<String>();
                String Pk_materialdocview = costTypeVO.getPk_materialdocview();
                if (CMStringUtil.isNotEmpty(Pk_materialdocview)) {
                    setSrcdoc = this.getSrcdocgroup(costTypeVO.getPk_materialdocview());
                    if (CMCollectionUtil.isEmpty(setSrcdoc)) {
                        errStr1.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                        errStr1.append(",");
                    }
                    else {
                        // У��-��ѡ��
                        if (this.srcdocNotLegal0(setEnableSrcdoc, setSrcdoc)) {
                            // ����[����]
                            errStr0.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                            errStr0.append(",");
                        }
                        // У��-��ѡһ
                        if (this.srcdocNotLegal1(setSrcdoc)) {
                            // ����[����]
                            errStr1.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                            errStr1.append(",");
                        }
                        // У��-����
                        if (this.srcdocNotLegal2(setSrcdoc)) {
                            // ����[����]
                            errStr2.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                            errStr2.append(",");
                        }
                    }
                    // if (this.srcdocNotLegal(setEnableSrcdoc, setSrcdoc)) {
                    // // ����[����]
                    // errStr.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                    // errStr.append(",");
                    // // ExceptionUtils.wrappBusinessException("���룺" + costTypeVO.getVcosttypecode() + "����"
                    // // + costTypeVO.getVcosttypename() + "Ҫ�������϶��ձ��Ϸ���");
                    // }
                }
            }
        }
        if (errStr0.length() > 0 || errStr1.length() > 0 || errStr2.length() > 0) {
            errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0304")/*
                                                                                                              * Ҫ�������϶��ձ��Ϸ���
                                                                                                              * \n
                                                                                                              */);
        }
        if (errStr0.length() > 0) {
            errStr0.deleteCharAt(errStr0.length() - 1); // Ҫȥ�����һ��","
            errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0305")/*
                                                                                                              * ���ϻ�����Ϣ��
                                                                                                              * ���ϻ�������
                                                                                                              * �����ϳɱ����ࡢ
                                                                                                              * �������ͺͳɱ���������ѡ���
                                                                                                              * :
                                                                                                              */);
            errStr.append(errStr0.toString());
            errStr.append("\n");
        }
        if (errStr1.length() > 0) {
            errStr1.deleteCharAt(errStr1.length() - 1); // Ҫȥ�����һ��","
            errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0306")/*
                                                                                                              * ���ϻ�����Ϣ��
                                                                                                              * ���ϻ������ࡢ
                                                                                                              * ���ϳɱ��������߱�ѡ��һ
                                                                                                              * :
                                                                                                              */);
            errStr.append(errStr1.toString());
            errStr.append("\n");
        }
        if (errStr2.length() > 0) {
            errStr2.deleteCharAt(errStr2.length() - 1); // Ҫȥ�����һ��","
            errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0307")/*
                                                                                                              * ���ϻ������ࡢ
                                                                                                              * ���ϳɱ����໥��
                                                                                                              * :
                                                                                                              */);
            errStr.append(errStr2.toString());
            errStr.append("\n");
        }
        if (errStr.length() > 0) {
            // ���������Ĵ��󾯸���Ϣ,����У�龯��
            failure.add(new ValidationFailure(errStr.toString()));
            throw new ValidationException(failure); // У�龯�����
        }
    }

    /**
     * У��Ҫ�������϶��ձ����Դ�����Ƿ�Ϸ�
     */
    // public boolean srcdocNotLegal(Set<String> enableSrcdoc, Set<String> srcdoc) {
    // // ���ȣ�1<= length <=4
    // if (srcdoc.size() < 1 || srcdoc.size() > 4) {
    // return true;
    // }
    // // ����ѡһ
    // if (!srcdoc.contains("eae040f4-3c88-413d-abc9-b15774463d46")
    // && !srcdoc.contains("c099f7d7-52a9-4b98-bee7-2e3a6c3ea263")
    // && !srcdoc.contains("fbb3c484-63bc-4624-9f2e-3627221f0036")) {
    // return true;
    // }
    // // ����
    // if (srcdoc.contains("c099f7d7-52a9-4b98-bee7-2e3a6c3ea263")
    // && srcdoc.contains("fbb3c484-63bc-4624-9f2e-3627221f0036")) {
    // return true;
    // }
    // // ��ѡ��
    // for (String docid : srcdoc) {
    // if (!enableSrcdoc.contains(docid)) {
    // return true;
    // }
    // }
    // return false;
    // }

    public boolean srcdocNotLegal0(Set<String> enableSrcdoc, Set<String> srcdoc) {
        // // ���ȣ�1<= length <=4
        // if (srcdoc.size() > 4) {
        // return true;
        // }
        // ��ѡ��
        for (String docid : srcdoc) {
            if (!enableSrcdoc.contains(docid)) {
                return true;
            }
        }
        return false;
    }

    public boolean srcdocNotLegal1(Set<String> srcdoc) {
        // ����ѡһ
        if (!srcdoc.contains("eae040f4-3c88-413d-abc9-b15774463d46")
                && !srcdoc.contains("c099f7d7-52a9-4b98-bee7-2e3a6c3ea263")
                && !srcdoc.contains("fbb3c484-63bc-4624-9f2e-3627221f0036")) {
            return true;
        }
        return false;
    }

    public boolean srcdocNotLegal2(Set<String> srcdoc) {
        // ����
        if (srcdoc.contains("c099f7d7-52a9-4b98-bee7-2e3a6c3ea263")
                && srcdoc.contains("fbb3c484-63bc-4624-9f2e-3627221f0036")) {
            return true;
        }
        return false;
    }

    /**
     * ����ʱ��������У�飬�ж��Ƿ� ʧЧ�ڼ� >= ��Ч�ڼ�
     *
     * @param costTypeVOs
     *            CostTypeVO[]
     * @throws ValidationException
     *             �쳣
     */
    public void accPeriodValidate(CostTypeVO[] costTypeVOs) throws ValidationException {

        List<ValidationFailure> failure = new ArrayList<ValidationFailure>();
        StringBuffer errStrNum = new StringBuffer();

        // ����У�飬ֻҪ�����д���ģ��������������
        for (int i = 0; i < costTypeVOs.length; i++) {

            String t1 = costTypeVOs[i].getCbeginmonth().toString();
            UFDate endDate = costTypeVOs[i].getCendmonth();
            // ʧЧ����Ϊ��������Ĭ��Ϊ9999-12-31 23��59��59
            if (CMValueCheck.isEmpty(endDate)) {
                UFDateTime maxDate = new UFDateTime(CMMLangConstCM0502.MAX_DATE);
                costTypeVOs[i].setCendmonth(maxDate.getDate());
            }
            String t2 = costTypeVOs[i].getCendmonth().toString();

            // t1����Ҫ�пմ�����Ϊt1���Ƚ��б���ķǿ�У�飬�˴�t1�طǿ�
            if (t2 != null && t2.length() > 0) {
                int compResult = t1.compareTo(t2);

                if (compResult > 0) {
                    // Ϊ���������к���׼��,ÿ���кź�����","������1,2,3(��)
                    errStrNum.append(costTypeVOs[i].getVcosttypecode() + "[" + costTypeVOs[i].getVcosttypename() + "]");
                    errStrNum.append(",");
                }
            }
        }

        if (errStrNum.length() > 0) {
            errStrNum.deleteCharAt(errStrNum.length() - 1); // Ҫȥ�����һ��","

            // ���������Ĵ��󾯸���Ϣ,����У�龯��
            failure.add(new ValidationFailure(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                    "03810006-0312")/*
                                     * @res
                                     * "����[��Ч�ڼ�]��������[ʧЧ�ڼ�]��"
                                     */
                    + errStrNum.toString()));
            throw new ValidationException(failure); // У�龯�����
        }

    }

    /**
     * Ҫ�ر�һ����У��
     *
     * @param itemVOs
     *            CostTypeVO[]
     * @throws ValidationException
     *             �쳣
     */
    public void factorChartEqualValidate(CostTypeVO[] costTypeVOs) throws ValidationException {
        List<ValidationFailure> failure = new ArrayList<ValidationFailure>();
        StringBuffer errStr = new StringBuffer();
        // ֻ��ҵ��Ԫ���ڵ����У��
        if ("38100106".equals(this.getEditor().getModel().getContext().getNodeCode())) {
            // ������ȡ����Ҫ�������϶��ձ�����
            if (CMArrayUtil.isNotEmpty(costTypeVOs)) {
                List<String> factorChartList = new ArrayList<String>();
                factorChartList = BDAdapter.query2FactorChart(this.getEditor().getModel().getContext().getPk_org());
                Set<String> docviewSet = new HashSet<String>();
                for (CostTypeVO costTypeVO : costTypeVOs) {
                    String Pk_materialdocview = costTypeVO.getPk_materialdocview();
                    if (CMStringUtil.isNotEmpty(Pk_materialdocview)) {
                        docviewSet.add(Pk_materialdocview);
                    }
                }
                // ����Ҫ�����ȡvo
                List<DocViewListVO> docViewListVOs = new ArrayList<DocViewListVO>();

                try {
                    // ����������ѯ�ӿ�δʵ�֣�����ע��ѭ����ѯ
                    // docViewListVOs =
                    // NCLocator.getInstance().lookup(IDocViewListService.class)
                    // .queryByPKs(docviewSet.toArray(new String[docviewSet.size()]));
                    for (String Pk_materialdocview : docviewSet) {
                        DocViewListVO vo = this.queryDocViewListVO(Pk_materialdocview);
                        if (CMStringUtil.isEqual(vo.getPk_group(), vo.getPk_org())) {
                            docviewSet.remove(Pk_materialdocview);
                        }
                        else {
                            docViewListVOs.add(vo);
                        }
                    }
                }
                catch (BusinessException e) {
                    ExceptionUtils.wrappException(e);
                }
                // ����Ҫ�������϶��ձ�������Ҫ�ر�Ķ�Ӧ��ϵ
                Map<String, String> pkDocview_pkFactorchart = new HashMap<String, String>();
                if (CMCollectionUtil.isNotEmpty(docViewListVOs)) {
                    for (DocViewListVO docViewListVO : docViewListVOs) {
                        for (String pkDocview : docviewSet) {
                            if (CMStringUtil.isEqual(pkDocview, docViewListVO.getPk_classview())) {
                                // ҵ������Ϊ��֯
                                if (CMStringUtil.isEqual(docViewListVO.getOrgtype(),
                                        "985be8a4-3a36-4778-8afe-2d8ed3902659")) {
                                    pkDocview_pkFactorchart.put(pkDocview, factorChartList.get(0));
                                }
                                else
                                // �˲�_���κ����˲�
                                if (CMStringUtil.isEqual(docViewListVO.getOrgtype(),
                                        "13a0d3b2-4d5b-4314-9e75-481193f993f2")) {
                                    pkDocview_pkFactorchart.put(pkDocview, factorChartList.get(1));
                                }
                            }
                        }
                    }
                }
                for (CostTypeVO costTypeVO : costTypeVOs) {
                    if (CMStringUtil.isNotEmpty(costTypeVO.getPk_materialdocview())
                            && docviewSet.contains(costTypeVO.getPk_materialdocview())) {
                        if (!CMStringUtil.isEqual(costTypeVO.getPk_factorchart(),
                                pkDocview_pkFactorchart.get(costTypeVO.getPk_materialdocview()))) {
                            // ����[����]
                            errStr.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                            errStr.append(",");
                        }
                    }
                }
            }
            if (errStr.length() > 0) {
                errStr.deleteCharAt(errStr.length() - 1); // Ҫȥ�����һ��","
                errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0346")/*
                                                                                                                  * @res
                                                                                                                  * "Ҫ�������϶��ձ�ͺ���Ҫ�ر�ƥ�䣬���������á�"
                                                                                                                  */
                );
                // ���������Ĵ��󾯸���Ϣ,����У�龯��
                failure.add(new ValidationFailure(errStr.toString()));
                throw new ValidationException(failure); // У�龯�����
            }
        }

    }

    private DocViewListVO queryDocViewListVO(String pk_materialdocview) throws BusinessException {
        return NCLocator.getInstance().lookup(IDocViewListService.class).queryByPK(pk_materialdocview);
    }

    /**
     * �����ǰ���������еĳɱ����͵ġ��Ƿ�Ĭ�ϡ���Ϊ���򱨴�����ֻ��һ��Ϊ���ǡ�
     *
     * @param itemVOs
     *            CostTypeVO[]
     * @throws ValidationException
     *             �쳣
     */

    public void bDefaultValidate(CostTypeVO[] vos) throws ValidationException {
        // ��õ�ǰҵ������
        UFDate clientDate = AppContext.getInstance().getBusiDate();
        String pk_org = vos[0].getPk_org();
        String accYearMonth = null;
        try {
            AccperiodmonthVO vo = FIUtil.getAccPeriodMonthVO(pk_org, clientDate);
            // ��ȡ���ڶ�Ӧ�Ļ������
            accYearMonth = vo.getYearmth();
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
        if (accYearMonth == null || this.checkVO(vos, accYearMonth)) {
            return;
        }
        int defaultRowCount = 0;
        for (int i = 0; i < vos.length; i++) {
            // �����vo����Ч��
            if (this.isLegal(vos[i], accYearMonth)) {
                // �����VO��Ĭ�ϵ�
                if (vos[i].getBdefault().booleanValue()) {
                    defaultRowCount = defaultRowCount + 1;
                }
            }
        }
        /** ����û��Ĭ����ʱ������ */

        if (defaultRowCount != 1) {
            List<ValidationFailure> failures = new ArrayList<ValidationFailure>();
            failures.add(new ValidationFailure(CMMLangConstCM0502.GET_ERRO_BDEFAULT()));
            throw new ValidationException(failures);
        }
    }

    private boolean checkVO(CostTypeVO[] vos, String cperiod) {
        boolean isLegal = true;
        for (CostTypeVO vo : vos) {
            if (vo.getCbeginmonth() == null) {
                return isLegal;
            }
            String endMonth = vo.getCendmonth().toString();
            if (endMonth == null) {
                endMonth = "9999-12";
            }
            if (vo.getCbeginmonth().toString().compareTo(cperiod) <= 0 && endMonth.compareTo(cperiod) >= 0) {
                isLegal = false;
            }
        }
        return isLegal;
    }

    /**
     * �ж��Ƿ�Ϊ��Ч����
     *
     * @param itemVO
     *            CostTypeVO
     * @param accPeriod
     *            ��ǰ����ڼ�
     */
    private boolean isLegal(CostTypeVO itemVO, String accPeriod) {
        return CostTypeValidatService.isLegal(itemVO.getCbeginmonth().toString(), itemVO.getCendmonth().toString(),
                accPeriod);

    }

    /**
     * �ж��Ƿ�Ϊ��Ч����
     *
     * @param beginMonth
     *            ��Ч�ڼ�
     * @param endMonth
     *            ʧЧ�ڼ�
     * @param accPeriod
     *            ��ǰ����ڼ�
     */
    public static boolean isLegal(String beginMonth, String endMonth, String accPeriod) {
        if (endMonth == null) {
            return false;
        }
        return beginMonth.compareTo(accPeriod) <= 0 && endMonth.compareTo(accPeriod) >= 0;

    }

    /**
     * ȡ����ǰ�����е�����VO
     */
    public CostTypeVO[] getAllCostTypeVOs() {
        // ���ص�ǰ����������VO��object����
        List<Object> nowAllObjects = this.getEditor().getModel().getRows();

        if (nowAllObjects == null || nowAllObjects.size() <= 0) {
            return null;
        }

        // ת������
        CostTypeVO[] costTypeVOs = new CostTypeVO[nowAllObjects.size()];
        for (int i = 0; i < nowAllObjects.size(); i++) {
            costTypeVOs[i] = (CostTypeVO) nowAllObjects.get(i);
        }
        // ҵ��Ԫ���ɱ�����
        List<CostTypeVO> listCostTypeVO = new ArrayList<CostTypeVO>();
        // ���ż��ɱ�����
        List<CostTypeVO> listGroupCostTypeVO = new ArrayList<CostTypeVO>();
        for (CostTypeVO costTypeVO : costTypeVOs) {
            if (!costTypeVO.getPk_group().equals(costTypeVO.getPk_org())) {
                listCostTypeVO.add(costTypeVO);
            }
            else {
                listGroupCostTypeVO.add(costTypeVO);
            }
        }
        // �ɱ�����-���ţ�38100105�� �ɱ�����-ҵ��Ԫ��38100106��
        if ("38100106".equals(this.getEditor().getModel().getContext().getNodeCode())) {
            return listCostTypeVO.toArray(new CostTypeVO[listCostTypeVO.size()]);
        }
        return listGroupCostTypeVO.toArray(new CostTypeVO[listGroupCostTypeVO.size()]);
    }

    /**
     * ȡ����ǰ�����е����б༭VO
     */
    public List<CostTypeVO> getAllEditedCostTypeVOs(Object obj) {
        if (CMValueCheck.isEmpty(obj)) {
            return null;
        }
        BatchOperateVO operateVO = new BatchOperateVO();
        if (obj instanceof BatchOperateVO) {
            operateVO = (BatchOperateVO) obj;
        }
        Object[] updateCostTypeVOs = operateVO.getUpdObjs();
        if (CMArrayUtil.isEmpty(updateCostTypeVOs)) {
            return null;
        }
        // ҵ��Ԫ���ɱ�����
        List<CostTypeVO> listCostTypeVO = new ArrayList<CostTypeVO>();
        // ���ż��ɱ�����
        List<CostTypeVO> listGroupCostTypeVO = new ArrayList<CostTypeVO>();
        for (Object o : updateCostTypeVOs) {
            if (o instanceof CostTypeVO) {
                CostTypeVO costTypeVO = (CostTypeVO) o;
                if (!costTypeVO.getPk_group().equals(costTypeVO.getPk_org())) {
                    listCostTypeVO.add(costTypeVO);
                }
                else {
                    listGroupCostTypeVO.add(costTypeVO);
                }
            }
        }
        // �ɱ�����-���ţ�38100105�� �ɱ�����-ҵ��Ԫ��38100106��
        if ("38100106".equals(this.getEditor().getModel().getContext().getNodeCode())) {
            return listCostTypeVO;
        }
        return listGroupCostTypeVO;
    }

    /**
     * ��ȡ���ڶ�Ӧ�Ļ���ڼ��VO
     *
     * @param date
     *            ����
     * @return ����ڼ�
     * @throws BusinessException
     *             �쳣
     */
    public static AccperiodmonthVO getAccPeriodMonthVO(UFDate date) throws BusinessException {
        if (date == null) {
            return null;
        }
        // ���ػ������
        AccountCalendar ac = nc.pubitf.accperiod.AccountCalendar.getInstance();
        if (ac.getMonthVO() == null) {
            throw new BusinessException(CMMLangConstCM0502.GET_ACCOUNTCALENDAR_ERROR());
        }
        // ������Ȼ�������û������
        ac.setDate(date);
        return ac.getMonthVO();
    }

    /**
     * ��ȡ���ڶ�Ӧ�Ļ������
     *
     * @param date
     *            ����
     * @return ���ڶ�Ӧ�Ļ������
     * @throws BusinessException
     *             �쳣
     */
    public static String getAccYearMonthByDate(UFDate date) throws BusinessException {
        // ��ȡ���ڶ�Ӧ�Ļ���ڼ��VO
        AccperiodmonthVO accPeriodMonthVO = CostTypeValidatService.getAccPeriodMonthVO(date);
        if (accPeriodMonthVO == null) {
            return null;
        }
        return accPeriodMonthVO.getYearmth();
    }

    /**
     * ��ȡָ�����ձ����Դ������������ѡ�������������Set��
     *
     * @param pk_org
     * @return
     */
    private Set<String> getSrcdocgroup(String pk_docviewid) {
        // �洢��ѡ����������Ԫ����ID
        Set<String> setSrcdoc = new HashSet<String>();
        DocViewListVO docViewListVO = this.getDocViewListVO(pk_docviewid);
        // Ҫ�ض��ձ���Դ������Ϣ
        SrcDocGroupVO[] srcDocGroupVOs = docViewListVO.getSrcdocgroup();
        if (CMArrayUtil.isNotEmpty(srcDocGroupVOs)) {
            for (SrcDocGroupVO srcDocGroup : srcDocGroupVOs) {
                setSrcdoc.add(srcDocGroup.getPk_srcdocid());
            }
            return setSrcdoc;
        }
        return null;
    }

    /**
     * ���ݹ�������֯�����õ�Ҫ�ض��ձ��voֵ
     *
     * @param pk_org
     * @return DocViewListVO
     */
    protected DocViewListVO getDocViewListVO(String pk_docviewid) {
        DocViewListVO docViewListVO = null;
        try {
            docViewListVO = NCLocator.getInstance().lookup(IDocViewListService.class).queryByPK(pk_docviewid);
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }

        return docViewListVO;
    }

    /**
     * ��ȡ���п�ѡ����Դ����id
     */
    private Set<String> getEnableSrcdocid() {
        Set<String> setEnableSrcdocid = new HashSet<String>();
        // ��������-���ϻ�����Ϣ
        setEnableSrcdocid.add("eae040f4-3c88-413d-abc9-b15774463d46");
        // ��������-���ϻ�������
        setEnableSrcdocid.add("c099f7d7-52a9-4b98-bee7-2e3a6c3ea263");
        // ��������-���ϳɱ�����
        setEnableSrcdocid.add("fbb3c484-63bc-4624-9f2e-3627221f0036");
        // ��������-��������
        setEnableSrcdocid.add("b0fa41cd-a649-4309-b685-d341a5d1b0ed");
        // ��������-�ɱ�����
        setEnableSrcdocid.add("de9796b5-bccd-42a1-97dd-808847bfddbd");
        return setEnableSrcdocid;
    }

}
