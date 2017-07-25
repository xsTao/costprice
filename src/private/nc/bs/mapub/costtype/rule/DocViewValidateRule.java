/**
 *
 */
package nc.bs.mapub.costtype.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMCollectionUtil;
import nc.bd.framework.base.CMStringUtil;
import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.fip.docview.IDocViewListService;
import nc.vo.fip.docview.DocViewListVO;
import nc.vo.fip.docview.SrcDocGroupVO;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since v6.3
 * @version 2015年5月18日 上午9:23:03
 * @author zhangshyb
 */
public class DocViewValidateRule implements IRule<CostTypeVO> {

    @Override
    public void process(CostTypeVO[] vos) {
        try {
            if (CMArrayUtil.isNotEmpty(vos)) {
                // 物料与要素对照表来源档案校验
                this.docViewValidate(vos);
            }
        }
        catch (ValidationException e) {
            ExceptionUtils.wrappException(e);
        }
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

}
