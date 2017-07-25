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
 * @version 2015��5��18�� ����9:22:14
 * @author zhangshyb
 */
public class FactorChartEqualValidateRule implements IRule<CostTypeVO> {

    @Override
    public void process(CostTypeVO[] vos) {
        // У�飺Ҫ�������϶��ձ���Ҫ������Ӧ��Ҫ�ر��뵥����Ҫ�ر���һ��
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
     * Ҫ�ر�һ����У��
     *
     * @param itemVOs
     *            CostTypeVO[]
     * @throws ValidationException
     *             �쳣
     */
    public void factorChartEqualValidate(CostTypeVO[] costTypeVOs) throws ValidationException {
        try {
            Set<String> docviewSet = new HashSet<String>();
            for (CostTypeVO costTypeVO : costTypeVOs) {
                if (CMStringUtil.isNotEmpty(costTypeVO.getPk_materialdocview())) {
                    docviewSet.add(costTypeVO.getPk_materialdocview());
                }
            }
            // ��ö��ձ�
            List<DocViewListVO> orgTypeDocViewList = this.getOrgTypeDocViewList(docviewSet);
            // ��ö��ձ���Ҫ�ر��Ӧ��ϵ
            Map<String, String> docViewFactorChartMap = this.getDocViewFactorChartMap(orgTypeDocViewList);
            // У��
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
            // Ҫ�������϶��ձ�ͺ���Ҫ�ر�ƥ�䣬���������á�
            if (errDifDocFactor.length() > 0) {
                errDifDocFactor.deleteCharAt(errDifDocFactor.length() - 1); // Ҫȥ�����һ��","
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
     * �õ����ձ���Ҫ�ر��Ӧ��ϵ
     *
     * @param docViewList
     * @return Map<���ձ�, Ҫ�ر�>
     * @throws BusinessException
     */
    private Map<String, String> getDocViewFactorChartMap(List<DocViewListVO> docViewList) throws BusinessException {
        // ���ձ���Ҫ�ر��Ӧ��ϵ
        Map<String, String> classviewFactorChartRelation = new HashMap<String, String>();
        // key:��֯���˲���������;value:Ҫ�ر�
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
     * ���Ҽ���������Ҫ�ر�
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
     * ��ö��ձ�
     *
     * @param docviewSet
     * @return
     * @throws BusinessException
     */
    private List<DocViewListVO> getOrgTypeDocViewList(Set<String> docviewSet) throws BusinessException {
        // ����Ҫ�����ȡvo
        List<DocViewListVO> docViewListVOs = new ArrayList<DocViewListVO>();
        // ����������ѯ�ӿ�δʵ�֣�����ע��ѭ����ѯ
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
