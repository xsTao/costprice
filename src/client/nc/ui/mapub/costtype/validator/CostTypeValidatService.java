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
 * <b> 数据检查 </b>
 * <p>
 * 详细描述功能
 */
public class CostTypeValidatService extends DefaultBatchValidationService {

    @Override
    public int[] unNecessaryData(List<Object> rows) {
        // 没有数据
        if (rows == null || rows.size() == 0) {
            return null;
        }
        ArrayList<Integer> filterRowList = new ArrayList<Integer>();
        for (int i = 0; i < rows.size(); i++) {
            CostTypeVO vo = (CostTypeVO) rows.get(i);
            // 编码为空，就过滤空行
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
        // // 判断标准成本模块（3860）是否启用
        // if (InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance().getGroupId(), "3860") == true
        // // 由于CMElemRelationConst.java文件中没有SCA，故如此处理，以后要删除
        // || InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance().getGroupId(), "3860") == false) {
        // if (!CMCollectionUtil.isEmpty(updateCostTypeVOs)) {
        // // 可编辑性校验
        // this.editableValidate(updateCostTypeVOs);
        // }
        // }
        // }
        // catch (BusinessException e) {
        // ExceptionUtils.wrappException(e);
        // }
        // // 取出当前所有VO
        // CostTypeVO[] costTypeVOs = this.getAllCostTypeVOs();
        // if (costTypeVOs == null) {
        // return;
        // }
        // // 校验：要素与物料对照表中要素所对应的要素表与单据中要素表保持一致
        // this.factorChartEqualValidate(costTypeVOs);
        // // 首先进行数据校验，判断是否 失效期间 >= 生效期间 ————纪录2010-04-15修改
        // this.accPeriodValidate(costTypeVOs);
        // // 物料与要素对照表来源档案校验
        // this.docViewValidate(costTypeVOs);
        //
        // // 如果当前工厂下所有的成本类型的“是否默认”都为否，则报错，有且只有一个为“是”
        // // this.bDefaultValidate(costTypeVOs);

    }

    /**
     * 字段可编辑校验
     * 1.存在成本BOM数据的成本类型，只允许修改成本类型编码、成本类型名称、物料价格来源、生效日期、失效日期和备注，其他字段不允许编辑；
     * 2.存在成本BOM数据的成本类型，且成本BOM存在已计算的，不允许修改物料价格来源，否则可以修改；
     */
    public void editableValidate(List<CostTypeVO> updateCostTypeVOs) throws ValidationException {
        List<ValidationFailure> failure = new ArrayList<ValidationFailure>();
        StringBuffer errStr = new StringBuffer();
        StringBuffer errStr0 = new StringBuffer();
        StringBuffer errStr1 = new StringBuffer();
        ICostTypeService service = NCLocator.getInstance().lookup(ICostTypeService.class);
        // 存在bom数据但未计算
        Set<String> data = new HashSet<String>();
        // 存在bom数据且已经计算
        Set<String> calcDate = new HashSet<String>();
        // 初始化
        data = service.haveDate();
        calcDate = service.haveCalcDate();
        // 判空
        for (CostTypeVO costTypeVO : updateCostTypeVOs) {
            String ccosttypeid = costTypeVO.getCcosttypeid();
            String vmaterialpricesourcenum = costTypeVO.getVmaterialpricesourcenum();
            String scrapfactor = costTypeVO.getBscrapfactor().toString();
            String shrinkfactor = costTypeVO.getBshrinkfactor().toString();
            String pk_elementsystem = costTypeVO.getPk_elementsystem();
            String pk_factorchart = costTypeVO.getPk_factorchart();
            String pk_materialdocview = costTypeVO.getPk_materialdocview();
            // 存在已计算bom数据
            if (calcDate != null && calcDate.contains(ccosttypeid)) {
                if (this.isMaterialSrcChanged(ccosttypeid, scrapfactor, shrinkfactor, pk_elementsystem, pk_factorchart,
                        pk_materialdocview, vmaterialpricesourcenum)) {
                    // 编码[名称]
                    errStr1.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                    errStr1.append(",");
                }
            }
            else {
                // 存在bom数据
                if (data != null && data.contains(ccosttypeid)) {
                    if (this.isValueChanged(ccosttypeid, scrapfactor, shrinkfactor, pk_elementsystem, pk_factorchart,
                            pk_materialdocview)) {
                        // 编码[名称]
                        errStr0.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                        errStr0.append(",");
                    }
                }
            }
        }
        if (errStr0.length() > 0 || errStr1.length() > 0) {
            errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0301")/*
                                                                                                              * 存在不合法编辑！\
                                                                                                              * n
                                                                                                              */);
        }
        if (errStr0.length() > 0) {
            errStr0.deleteCharAt(errStr0.length() - 1); // 要去掉最后一个","
            errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0302")/*
                                                                                                              * 存在成本BOM数据的成本类型
                                                                                                              * ，
                                                                                                              * 只允许修改成本类型编码
                                                                                                              * 、成本类型名称、
                                                                                                              * 材料价格来源
                                                                                                              * 、生效日期
                                                                                                              * 、失效日期和备注
                                                                                                              * ，
                                                                                                              * 其他字段不允许编辑
                                                                                                              * :
                                                                                                              */);
            errStr.append(errStr0.toString());
            errStr.append("\n");
        }
        if (errStr1.length() > 0) {
            errStr1.deleteCharAt(errStr1.length() - 1); // 要去掉最后一个","
            errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0303")/*
                                                                                                              * 存在成本BOM数据的成本类型
                                                                                                              * ，
                                                                                                              * 且成本BOM已计算的
                                                                                                              * ，
                                                                                                              * 只允许修改成本类型编码
                                                                                                              * 、
                                                                                                              * 成本类型名称、生效日期
                                                                                                              * 、
                                                                                                              * 失效日期和备注，
                                                                                                              * 其他字段不允许编辑
                                                                                                              * :
                                                                                                              */);
            errStr.append(errStr1.toString());
            errStr.append("\n");
        }
        if (errStr.length() > 0) {
            // 返回完整的错误警告信息,创建校验警告
            failure.add(new ValidationFailure(errStr.toString()));
            throw new ValidationException(failure); // 校验警告输出
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
     * 保存前校验
     * 要素与物料对照表的来源档案字段校验：
     * 1.物料基本信息、物料基本分类、物料成本分类、单据类型和成本中心五选多；
     * 2.物料基本信息、物料基本分类、物料成本分类必选其一；
     * 3.物料基本分类、物料成本分类互斥。
     */
    public void docViewValidate(CostTypeVO[] costTypeVOs) throws ValidationException {
        List<ValidationFailure> failure = new ArrayList<ValidationFailure>();
        StringBuffer errStr = new StringBuffer();
        StringBuffer errStr0 = new StringBuffer();
        StringBuffer errStr1 = new StringBuffer();
        StringBuffer errStr2 = new StringBuffer();
        if (CMArrayUtil.isNotEmpty(costTypeVOs)) {
            // 可选的基本档案
            Set<String> setEnableSrcdoc = new HashSet<String>();
            setEnableSrcdoc = this.getEnableSrcdocid();
            // 数据校验，只要发现有错误的，跳出并输出错误
            for (CostTypeVO costTypeVO : costTypeVOs) {
                // 已选的基本档案
                Set<String> setSrcdoc = new HashSet<String>();
                String Pk_materialdocview = costTypeVO.getPk_materialdocview();
                if (CMStringUtil.isNotEmpty(Pk_materialdocview)) {
                    setSrcdoc = this.getSrcdocgroup(costTypeVO.getPk_materialdocview());
                    if (CMCollectionUtil.isEmpty(setSrcdoc)) {
                        errStr1.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                        errStr1.append(",");
                    }
                    else {
                        // 校验-五选多
                        if (this.srcdocNotLegal0(setEnableSrcdoc, setSrcdoc)) {
                            // 编码[名称]
                            errStr0.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                            errStr0.append(",");
                        }
                        // 校验-三选一
                        if (this.srcdocNotLegal1(setSrcdoc)) {
                            // 编码[名称]
                            errStr1.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                            errStr1.append(",");
                        }
                        // 校验-互斥
                        if (this.srcdocNotLegal2(setSrcdoc)) {
                            // 编码[名称]
                            errStr2.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                            errStr2.append(",");
                        }
                    }
                    // if (this.srcdocNotLegal(setEnableSrcdoc, setSrcdoc)) {
                    // // 编码[名称]
                    // errStr.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                    // errStr.append(",");
                    // // ExceptionUtils.wrappBusinessException("编码：" + costTypeVO.getVcosttypecode() + "名称"
                    // // + costTypeVO.getVcosttypename() + "要素与物料对照表不合法！");
                    // }
                }
            }
        }
        if (errStr0.length() > 0 || errStr1.length() > 0 || errStr2.length() > 0) {
            errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0304")/*
                                                                                                              * 要素与物料对照表不合法！
                                                                                                              * \n
                                                                                                              */);
        }
        if (errStr0.length() > 0) {
            errStr0.deleteCharAt(errStr0.length() - 1); // 要去掉最后一个","
            errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0305")/*
                                                                                                              * 物料基本信息、
                                                                                                              * 物料基本分类
                                                                                                              * 、物料成本分类、
                                                                                                              * 单据类型和成本中心五者选多个
                                                                                                              * :
                                                                                                              */);
            errStr.append(errStr0.toString());
            errStr.append("\n");
        }
        if (errStr1.length() > 0) {
            errStr1.deleteCharAt(errStr1.length() - 1); // 要去掉最后一个","
            errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0306")/*
                                                                                                              * 物料基本信息、
                                                                                                              * 物料基本分类、
                                                                                                              * 物料成本分类三者必选其一
                                                                                                              * :
                                                                                                              */);
            errStr.append(errStr1.toString());
            errStr.append("\n");
        }
        if (errStr2.length() > 0) {
            errStr2.deleteCharAt(errStr2.length() - 1); // 要去掉最后一个","
            errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0307")/*
                                                                                                              * 物料基本分类、
                                                                                                              * 物料成本分类互斥
                                                                                                              * :
                                                                                                              */);
            errStr.append(errStr2.toString());
            errStr.append("\n");
        }
        if (errStr.length() > 0) {
            // 返回完整的错误警告信息,创建校验警告
            failure.add(new ValidationFailure(errStr.toString()));
            throw new ValidationException(failure); // 校验警告输出
        }
    }

    /**
     * 校验要素与物料对照表的来源档案是否合法
     */
    // public boolean srcdocNotLegal(Set<String> enableSrcdoc, Set<String> srcdoc) {
    // // 长度：1<= length <=4
    // if (srcdoc.size() < 1 || srcdoc.size() > 4) {
    // return true;
    // }
    // // 三必选一
    // if (!srcdoc.contains("eae040f4-3c88-413d-abc9-b15774463d46")
    // && !srcdoc.contains("c099f7d7-52a9-4b98-bee7-2e3a6c3ea263")
    // && !srcdoc.contains("fbb3c484-63bc-4624-9f2e-3627221f0036")) {
    // return true;
    // }
    // // 互斥
    // if (srcdoc.contains("c099f7d7-52a9-4b98-bee7-2e3a6c3ea263")
    // && srcdoc.contains("fbb3c484-63bc-4624-9f2e-3627221f0036")) {
    // return true;
    // }
    // // 五选多
    // for (String docid : srcdoc) {
    // if (!enableSrcdoc.contains(docid)) {
    // return true;
    // }
    // }
    // return false;
    // }

    public boolean srcdocNotLegal0(Set<String> enableSrcdoc, Set<String> srcdoc) {
        // // 长度：1<= length <=4
        // if (srcdoc.size() > 4) {
        // return true;
        // }
        // 五选多
        for (String docid : srcdoc) {
            if (!enableSrcdoc.contains(docid)) {
                return true;
            }
        }
        return false;
    }

    public boolean srcdocNotLegal1(Set<String> srcdoc) {
        // 三必选一
        if (!srcdoc.contains("eae040f4-3c88-413d-abc9-b15774463d46")
                && !srcdoc.contains("c099f7d7-52a9-4b98-bee7-2e3a6c3ea263")
                && !srcdoc.contains("fbb3c484-63bc-4624-9f2e-3627221f0036")) {
            return true;
        }
        return false;
    }

    public boolean srcdocNotLegal2(Set<String> srcdoc) {
        // 互斥
        if (srcdoc.contains("c099f7d7-52a9-4b98-bee7-2e3a6c3ea263")
                && srcdoc.contains("fbb3c484-63bc-4624-9f2e-3627221f0036")) {
            return true;
        }
        return false;
    }

    /**
     * 保存时进行数据校验，判断是否 失效期间 >= 生效期间
     *
     * @param costTypeVOs
     *            CostTypeVO[]
     * @throws ValidationException
     *             异常
     */
    public void accPeriodValidate(CostTypeVO[] costTypeVOs) throws ValidationException {

        List<ValidationFailure> failure = new ArrayList<ValidationFailure>();
        StringBuffer errStrNum = new StringBuffer();

        // 数据校验，只要发现有错误的，跳出并输出错误
        for (int i = 0; i < costTypeVOs.length; i++) {

            String t1 = costTypeVOs[i].getCbeginmonth().toString();
            UFDate endDate = costTypeVOs[i].getCendmonth();
            // 失效日期为空则设置默认为9999-12-31 23：59：59
            if (CMValueCheck.isEmpty(endDate)) {
                UFDateTime maxDate = new UFDateTime(CMMLangConstCM0502.MAX_DATE);
                costTypeVOs[i].setCendmonth(maxDate.getDate());
            }
            String t2 = costTypeVOs[i].getCendmonth().toString();

            // t1不需要判空处理，因为t1首先进行保存的非空校验，此处t1必非空
            if (t2 != null && t2.length() > 0) {
                int compResult = t1.compareTo(t2);

                if (compResult > 0) {
                    // 为输出错误的行号做准备,每个行号后面以","连接如1,2,3(行)
                    errStrNum.append(costTypeVOs[i].getVcosttypecode() + "[" + costTypeVOs[i].getVcosttypename() + "]");
                    errStrNum.append(",");
                }
            }
        }

        if (errStrNum.length() > 0) {
            errStrNum.deleteCharAt(errStrNum.length() - 1); // 要去掉最后一个","

            // 返回完整的错误警告信息,创建校验警告
            failure.add(new ValidationFailure(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                    "03810006-0312")/*
                                     * @res
                                     * "错误：[生效期间]不能晚于[失效期间]："
                                     */
                    + errStrNum.toString()));
            throw new ValidationException(failure); // 校验警告输出
        }

    }

    /**
     * 要素表一致性校验
     *
     * @param itemVOs
     *            CostTypeVO[]
     * @throws ValidationException
     *             异常
     */
    public void factorChartEqualValidate(CostTypeVO[] costTypeVOs) throws ValidationException {
        List<ValidationFailure> failure = new ArrayList<ValidationFailure>();
        StringBuffer errStr = new StringBuffer();
        // 只对业务单元级节点进行校验
        if ("38100106".equals(this.getEditor().getModel().getContext().getNodeCode())) {
            // 遍历获取所有要素与物料对照表主键
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
                // 根据要素与获取vo
                List<DocViewListVO> docViewListVOs = new ArrayList<DocViewListVO>();

                try {
                    // 由于批量查询接口未实现，故先注掉循环查询
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
                // 构建要素与物料对照表主键与要素表的对应关系
                Map<String, String> pkDocview_pkFactorchart = new HashMap<String, String>();
                if (CMCollectionUtil.isNotEmpty(docViewListVOs)) {
                    for (DocViewListVO docViewListVO : docViewListVOs) {
                        for (String pkDocview : docviewSet) {
                            if (CMStringUtil.isEqual(pkDocview, docViewListVO.getPk_classview())) {
                                // 业务类型为组织
                                if (CMStringUtil.isEqual(docViewListVO.getOrgtype(),
                                        "985be8a4-3a36-4778-8afe-2d8ed3902659")) {
                                    pkDocview_pkFactorchart.put(pkDocview, factorChartList.get(0));
                                }
                                else
                                // 账簿_责任核算账簿
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
                            // 编码[名称]
                            errStr.append(costTypeVO.getVcosttypecode() + "[" + costTypeVO.getVcosttypename() + "]");
                            errStr.append(",");
                        }
                    }
                }
            }
            if (errStr.length() > 0) {
                errStr.deleteCharAt(errStr.length() - 1); // 要去掉最后一个","
                errStr.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0346")/*
                                                                                                                  * @res
                                                                                                                  * "要素与物料对照表和核算要素表不匹配，请重新设置。"
                                                                                                                  */
                );
                // 返回完整的错误警告信息,创建校验警告
                failure.add(new ValidationFailure(errStr.toString()));
                throw new ValidationException(failure); // 校验警告输出
            }
        }

    }

    private DocViewListVO queryDocViewListVO(String pk_materialdocview) throws BusinessException {
        return NCLocator.getInstance().lookup(IDocViewListService.class).queryByPK(pk_materialdocview);
    }

    /**
     * 如果当前工厂下所有的成本类型的“是否默认”都为否，则报错，有且只有一个为“是”
     *
     * @param itemVOs
     *            CostTypeVO[]
     * @throws ValidationException
     *             异常
     */

    public void bDefaultValidate(CostTypeVO[] vos) throws ValidationException {
        // 获得当前业务日期
        UFDate clientDate = AppContext.getInstance().getBusiDate();
        String pk_org = vos[0].getPk_org();
        String accYearMonth = null;
        try {
            AccperiodmonthVO vo = FIUtil.getAccPeriodMonthVO(pk_org, clientDate);
            // 获取日期对应的会计年月
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
            // 如果该vo是生效的
            if (this.isLegal(vos[i], accYearMonth)) {
                // 如果该VO是默认的
                if (vos[i].getBdefault().booleanValue()) {
                    defaultRowCount = defaultRowCount + 1;
                }
            }
        }
        /** 增加没有默认行时不报错 */

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
     * 判断是否为生效数据
     *
     * @param itemVO
     *            CostTypeVO
     * @param accPeriod
     *            当前会计期间
     */
    private boolean isLegal(CostTypeVO itemVO, String accPeriod) {
        return CostTypeValidatService.isLegal(itemVO.getCbeginmonth().toString(), itemVO.getCendmonth().toString(),
                accPeriod);

    }

    /**
     * 判断是否为生效数据
     *
     * @param beginMonth
     *            生效期间
     * @param endMonth
     *            失效期间
     * @param accPeriod
     *            当前会计期间
     */
    public static boolean isLegal(String beginMonth, String endMonth, String accPeriod) {
        if (endMonth == null) {
            return false;
        }
        return beginMonth.compareTo(accPeriod) <= 0 && endMonth.compareTo(accPeriod) >= 0;

    }

    /**
     * 取出当前界面中的所有VO
     */
    public CostTypeVO[] getAllCostTypeVOs() {
        // 返回当前单表内所有VO（object对象）
        List<Object> nowAllObjects = this.getEditor().getModel().getRows();

        if (nowAllObjects == null || nowAllObjects.size() <= 0) {
            return null;
        }

        // 转化类型
        CostTypeVO[] costTypeVOs = new CostTypeVO[nowAllObjects.size()];
        for (int i = 0; i < nowAllObjects.size(); i++) {
            costTypeVOs[i] = (CostTypeVO) nowAllObjects.get(i);
        }
        // 业务单元级成本类型
        List<CostTypeVO> listCostTypeVO = new ArrayList<CostTypeVO>();
        // 集团级成本类型
        List<CostTypeVO> listGroupCostTypeVO = new ArrayList<CostTypeVO>();
        for (CostTypeVO costTypeVO : costTypeVOs) {
            if (!costTypeVO.getPk_group().equals(costTypeVO.getPk_org())) {
                listCostTypeVO.add(costTypeVO);
            }
            else {
                listGroupCostTypeVO.add(costTypeVO);
            }
        }
        // 成本类型-集团（38100105） 成本类型-业务单元（38100106）
        if ("38100106".equals(this.getEditor().getModel().getContext().getNodeCode())) {
            return listCostTypeVO.toArray(new CostTypeVO[listCostTypeVO.size()]);
        }
        return listGroupCostTypeVO.toArray(new CostTypeVO[listGroupCostTypeVO.size()]);
    }

    /**
     * 取出当前界面中的所有编辑VO
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
        // 业务单元级成本类型
        List<CostTypeVO> listCostTypeVO = new ArrayList<CostTypeVO>();
        // 集团级成本类型
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
        // 成本类型-集团（38100105） 成本类型-业务单元（38100106）
        if ("38100106".equals(this.getEditor().getModel().getContext().getNodeCode())) {
            return listCostTypeVO;
        }
        return listGroupCostTypeVO;
    }

    /**
     * 获取日期对应的会计期间的VO
     *
     * @param date
     *            日期
     * @return 会计期间
     * @throws BusinessException
     *             异常
     */
    public static AccperiodmonthVO getAccPeriodMonthVO(UFDate date) throws BusinessException {
        if (date == null) {
            return null;
        }
        // 返回会计日历
        AccountCalendar ac = nc.pubitf.accperiod.AccountCalendar.getInstance();
        if (ac.getMonthVO() == null) {
            throw new BusinessException(CMMLangConstCM0502.GET_ACCOUNTCALENDAR_ERROR());
        }
        // 根据自然日期设置会计日历
        ac.setDate(date);
        return ac.getMonthVO();
    }

    /**
     * 获取日期对应的会计年月
     *
     * @param date
     *            日期
     * @return 日期对应的会计年月
     * @throws BusinessException
     *             异常
     */
    public static String getAccYearMonthByDate(UFDate date) throws BusinessException {
        // 获取日期对应的会计期间的VO
        AccperiodmonthVO accPeriodMonthVO = CostTypeValidatService.getAccPeriodMonthVO(date);
        if (accPeriodMonthVO == null) {
            return null;
        }
        return accPeriodMonthVO.getYearmth();
    }

    /**
     * 获取指定对照表的来源档案，并将所选基本档案存放在Set中
     *
     * @param pk_org
     * @return
     */
    private Set<String> getSrcdocgroup(String pk_docviewid) {
        // 存储已选基本档案的元数据ID
        Set<String> setSrcdoc = new HashSet<String>();
        DocViewListVO docViewListVO = this.getDocViewListVO(pk_docviewid);
        // 要素对照表来源档案信息
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
     * 根据工厂和组织参数得到要素对照表的vo值
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
     * 获取所有可选的来源档案id
     */
    private Set<String> getEnableSrcdocid() {
        Set<String> setEnableSrcdocid = new HashSet<String>();
        // 基本档案-物料基本信息
        setEnableSrcdocid.add("eae040f4-3c88-413d-abc9-b15774463d46");
        // 基本档案-物料基本分类
        setEnableSrcdocid.add("c099f7d7-52a9-4b98-bee7-2e3a6c3ea263");
        // 基本档案-物料成本分类
        setEnableSrcdocid.add("fbb3c484-63bc-4624-9f2e-3627221f0036");
        // 基本档案-单据类型
        setEnableSrcdocid.add("b0fa41cd-a649-4309-b685-d341a5d1b0ed");
        // 基本档案-成本中心
        setEnableSrcdocid.add("de9796b5-bccd-42a1-97dd-808847bfddbd");
        return setEnableSrcdocid;
    }

}
