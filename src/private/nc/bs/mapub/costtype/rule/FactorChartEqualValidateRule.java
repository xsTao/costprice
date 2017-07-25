/**
 *
 */
package nc.bs.mapub.costtype.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMStringUtil;
import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.fip.docview.IDocViewListService;
import nc.itf.org.ISetOfBookQryService;
import nc.pubitf.resa.factor.chart.IFactorVersionQueryService;
import nc.ui.resa.refmodel.FactorRefModelUtil;
import nc.vo.fip.docview.DocViewListVO;
import nc.vo.mapub.costtype.entity.CMMLangConstCM0502;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.org.SetOfBookVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.resa.factor.FactorChartVO;

/**
 * @since v6.3
 * @version 2015年5月18日 上午9:22:14
 * @author zhangshyb
 */
public class FactorChartEqualValidateRule implements IRule<CostTypeVO> {

    @Override
    public void process(CostTypeVO[] vos) {
        // 校验：要素与物料对照表中要素所对应的要素表与单据中要素表保持一致
        try {
            if (CMArrayUtil.isNotEmpty(vos)) {
                this.factorChartEqualValidate(vos);
            }
        }
        catch (ValidationException e) {
            ExceptionUtils.wrappException(e);
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
        try {
            Set<String> docviewSet = new HashSet<String>();
            for (CostTypeVO costTypeVO : costTypeVOs) {
                if (CMStringUtil.isNotEmpty(costTypeVO.getPk_materialdocview())) {
                    docviewSet.add(costTypeVO.getPk_materialdocview());
                }
            }
            // 获得对照表
            List<DocViewListVO> orgTypeDocViewList = this.getOrgTypeDocViewList(docviewSet);
            // 获得对照表与要素表对应关系
            Map<String, String> docViewFactorChartMap = this.getDocViewFactorChartMap(orgTypeDocViewList);
            // 校验
            List<ValidationFailure> failure = new ArrayList<ValidationFailure>();
            StringBuffer errDifDocFactor = new StringBuffer();
            for (CostTypeVO costtypevo : costTypeVOs) {
                if (CMStringUtil.isNotEmpty(costtypevo.getPk_materialdocview())) {
                    if (!costtypevo.getPk_factorchart().equals(
                            docViewFactorChartMap.get(costtypevo.getPk_materialdocview()))) {
                        errDifDocFactor.append(costtypevo.getVcosttypecode() + "[" + costtypevo.getVcosttypename()
                                + "]");
                        errDifDocFactor.append(",");
                    }
                }
            }
            // 要素与物料对照表和核算要素表不匹配，请重新设置。
            if (errDifDocFactor.length() > 0) {
                errDifDocFactor.deleteCharAt(errDifDocFactor.length() - 1); // 要去掉最后一个","
                failure.add(new ValidationFailure(CMMLangConstCM0502.GET_ERROR_DIF_DOCVIEW_FACTORCHART(errDifDocFactor
                        .toString())));
                throw new ValidationException(failure);
            }
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }
    }

    /**
     * 得到对照表与要素表对应关系
     *
     * @param docViewList
     * @return Map<对照表, 要素表>
     * @throws BusinessException
     */
    private Map<String, String> getDocViewFactorChartMap(List<DocViewListVO> docViewList) throws BusinessException {
        // 对照表与要素表对应关系
        Map<String, String> classviewFactorChartRelation = new HashMap<String, String>();
        // key:组织与账簿类型联合;value:要素表
        Map<String, String> orgSetOrg1FactorChartMap = new HashMap<String, String>();
        for (DocViewListVO vo : docViewList) {
            if (orgSetOrg1FactorChartMap.containsKey(vo.getPk_org() + vo.getPk_setorg1())) {
                classviewFactorChartRelation.put(vo.getPk_classview(),
                        orgSetOrg1FactorChartMap.get(vo.getPk_org() + vo.getPk_setorg1()));
                continue;
            }
            String factorchart = null;
            if (vo.getPk_group().equals(vo.getPk_org())) {
                factorchart = this.getPolicyFactorChart(vo);
            }
            else {
                factorchart = FactorRefModelUtil.getFactorchartByOrg(vo.getPk_org(), null, vo.getOrgtype());
            }
            if (CMStringUtil.isEmpty(factorchart)) {
                continue;
            }
            orgSetOrg1FactorChartMap.put(vo.getPk_org() + vo.getPk_setorg1(), factorchart);
            classviewFactorChartRelation.put(vo.getPk_classview(), factorchart);
        }
        return classviewFactorChartRelation;
    }

    /**
     * 查找集团政策性要素表
     *
     * @param vo
     * @return
     * @throws BusinessException
     */
    private String getPolicyFactorChart(DocViewListVO vo) throws BusinessException {
        ISetOfBookQryService bookQryService = NCLocator.getInstance().lookup(ISetOfBookQryService.class);
        SetOfBookVO setOfBook = bookQryService.querySetOfBookVOByPK(vo.getPk_setorg1());
        if (CMStringUtil.isEmpty(setOfBook.getPk_checkelemsystem())) {
            return null;
        }
        IFactorVersionQueryService queryService = NCLocator.getInstance().lookup(IFactorVersionQueryService.class);
        FactorChartVO factorChart =
                queryService.getPolicyFactorChart(vo.getPk_group(), setOfBook.getPk_checkelemsystem(), null,
                        new String[] {
                            FactorChartVO.PK_FACTORCHART
                        });
        String pk_factorChart = "";
        if (factorChart != null) {
            pk_factorChart = factorChart.getPk_factorchart();
        }
        return pk_factorChart;
    }

    /**
     * 获得对照表
     *
     * @param docviewSet
     * @return
     * @throws BusinessException
     */
    private List<DocViewListVO> getOrgTypeDocViewList(Set<String> docviewSet) throws BusinessException {
        // 根据要素与获取vo
        List<DocViewListVO> docViewListVOs = new ArrayList<DocViewListVO>();
        // 由于批量查询接口未实现，故先注掉循环查询
        // docViewListVOs =
        // NCLocator.getInstance().lookup(IDocViewListService.class)
        // .queryByPKs(docviewSet.toArray(new String[docviewSet.size()]));
        for (String Pk_materialdocview : docviewSet) {
            DocViewListVO vo = NCLocator.getInstance().lookup(IDocViewListService.class).queryByPK(Pk_materialdocview);
            docViewListVOs.add(vo);
        }
        return docViewListVOs;
    }
}
