package nc.pubimpl.mapub.coprodcoef.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bd.framework.db.CMSqlBuilder;
import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.cmpub.business.adapter.BDAdapter;
import nc.impl.pubapp.pattern.data.bill.EfficientBillQuery;
import nc.pubitf.coprodcoef.pub.ICoprodcoefPubQuery;
import nc.pubitf.coprodcoef.pub.QueryCofficientInfoDTO;
import nc.pubitf.coprodcoef.pub.QueryDetailinfo;
import nc.pubitf.coprodcoef.pub.QueryDto;
import nc.pubitf.coprodcoef.pub.QueryField;
import nc.pubitf.coprodcoef.pub.QueryResultto;
import nc.pubitf.mapub.costtype.pub.ICostTypePubQueryService;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefHeadVO;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefItemVO;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.resa.factor.FactorChartVO;

public class CoprodcoefPubQueryImpl implements ICoprodcoefPubQuery {

    @Override
    public List<QueryCofficientInfoDTO> queryCoefficient(List<QueryDto> queryMsg) throws BusinessException {
        List<QueryCofficientInfoDTO> qryDetail = new ArrayList<QueryCofficientInfoDTO>();
        if (queryMsg == null || queryMsg.size() < 1) {
            return qryDetail;
        }
        new BDAdapter();
        // Ҫ�ر�
        String factorchart = BDAdapter.query2FactorChart(queryMsg.get(0).getPk_org()).get(0);
        // Ҫ����ϵ
        String factorsystem =
                ((FactorChartVO) new BaseDAO().retrieveByPK(FactorChartVO.class, factorchart)).getPk_factorsystem();
        List<QueryResultto> getAllInfo = this.f_AllCofficient(queryMsg, factorchart, factorsystem);
        return this.getQryDetail(getAllInfo, queryMsg);
    }

    @Override
    public List<QueryCofficientInfoDTO> queryCoefficientForSca(List<QueryDto> queryMsg, String ccosttypeid)
            throws BusinessException {
        List<QueryCofficientInfoDTO> qryDetail = new ArrayList<QueryCofficientInfoDTO>();
        if (queryMsg == null || queryMsg.size() < 1) {
            return qryDetail;
        }
        // �ɱ����͵�Ҫ�ر�
        CostTypeVO vo = null;
        vo = (CostTypeVO) NCLocator.getInstance().lookup(ICostTypePubQueryService.class).getCostTypeVoByPK(ccosttypeid);
        List<QueryResultto> getAllInfo =
                this.f_AllCofficient(queryMsg, vo.getPk_factorchart(), vo.getPk_elementsystem());
        return this.getQryDetail(getAllInfo, queryMsg);
    }

    private List<QueryCofficientInfoDTO> getQryDetail(List<QueryResultto> getAllInfo, List<QueryDto> queryMsg) {
        List<QueryCofficientInfoDTO> qryDetail = new ArrayList<QueryCofficientInfoDTO>();
        // ��Ϊ������
        Map<String, QueryResultto> getZhuInfo = new LinkedHashMap<String, QueryResultto>();
        Map<String, QueryResultto> getLianInfo = new LinkedHashMap<String, QueryResultto>();
        Map<String, QueryResultto> getFuInfo = new LinkedHashMap<String, QueryResultto>();
        for (QueryResultto queryResultto : getAllInfo) {
            if (UFDouble.ONE_DBL.equals(queryResultto.getProducttype())) {// ������123
                // org+������+����Ʒ+��Ʒ+�ɱ�����+����Ҫ��
                getZhuInfo.put(
                        queryResultto.getPk_org() + queryResultto.getM_material() + queryResultto.getM_material_b()
                                + queryResultto.getCcostcenterid() + queryResultto.getCelementid(), queryResultto);
            }
            else if (new UFDouble(2).equals(queryResultto.getProducttype())) {
                // org+������+����Ʒ+��Ʒ+�ɱ�����+����Ҫ��
                getLianInfo.put(
                        queryResultto.getPk_org() + queryResultto.getM_material() + queryResultto.getM_material_b()
                                + queryResultto.getCcostcenterid() + queryResultto.getCelementid(), queryResultto);
            }
            else if (new UFDouble(3).equals(queryResultto.getProducttype())) {
                // org+������+����Ʒ+��Ʒ+�ɱ�����+����Ҫ��
                getFuInfo.put(
                        queryResultto.getPk_org() + queryResultto.getM_material() + queryResultto.getM_material_b()
                                + queryResultto.getCcostcenterid() + queryResultto.getCelementid(), queryResultto);
            }
        }
        for (QueryDto queryMsg1 : queryMsg) {// ��ѯ����
            QueryCofficientInfoDTO oneDto = new QueryCofficientInfoDTO();
            oneDto.setM_material(queryMsg1.getM_material());
            oneDto.setPk_org(queryMsg1.getPk_org());
            if (queryMsg1.isCmrule()) {// ���ɱ����ļ���=Y
                // 1 ��װ ��ϵ��
                if (queryMsg1.getM_materials() != null) {
                    for (QueryField queryfield : queryMsg1.getM_materials()) {// ��װ��ϵ��
                        QueryDetailinfo filldt = new QueryDetailinfo();
                        // ƴװ���
                        filldt.setMaterial(queryfield.getMaterial());
                        filldt.setQry_CostCenter(queryfield.getCostCenter());
                        filldt.setQry_factor(queryfield.getFactor());

                        if (getZhuInfo.containsKey(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                + queryfield.getMaterial() + queryfield.getCostCenter() + queryfield.getFactor())) {
                            // �ɱ�����+����Ҫ��
                            QueryResultto currentDto =
                                    getZhuInfo.get(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                            + queryfield.getMaterial() + queryfield.getCostCenter()
                                            + queryfield.getFactor());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setResult_CostCenter(currentDto.getCcostcenterid());
                            filldt.setResult_factor(currentDto.getCelementid());
                        }
                        else if (getZhuInfo.containsKey(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                + queryfield.getMaterial() + queryfield.getCostCenter())) {
                            // �ɱ�����+��
                            QueryResultto currentDto =
                                    getZhuInfo.get(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                            + queryfield.getMaterial() + queryfield.getCostCenter());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setResult_CostCenter(currentDto.getCcostcenterid());
                            filldt.setResult_factor(currentDto.getCelementid());
                        }
                        else if (getZhuInfo.containsKey(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                + queryfield.getMaterial() + queryfield.getFactor())) {
                            // ��+����Ҫ��
                            QueryResultto currentDto =
                                    getZhuInfo.get(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                            + queryfield.getMaterial() + queryfield.getFactor());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setResult_CostCenter(currentDto.getCcostcenterid());
                            filldt.setResult_factor(currentDto.getCelementid());
                        }
                        else if (getZhuInfo.containsKey(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                + queryfield.getMaterial())) {
                            // ��+��
                            QueryResultto currentDto =
                                    getZhuInfo.get(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                            + queryfield.getMaterial());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setResult_CostCenter(currentDto.getCcostcenterid());
                            filldt.setResult_factor(currentDto.getCelementid());
                        }

                        if (oneDto.getM_Cofficient() != null) {
                            if (filldt.getCofficient() == null) {
                                filldt.setCofficient(UFDouble.ONE_DBL);
                            }
                            oneDto.getM_Cofficient().add(filldt);
                        }
                        else {
                            if (filldt.getCofficient() == null) {
                                filldt.setCofficient(UFDouble.ONE_DBL);
                            }
                            List<QueryDetailinfo> newList = new ArrayList<QueryDetailinfo>();
                            newList.add(filldt);
                            oneDto.setM_Cofficient(newList);
                        }

                    }
                }
                // 2 ��װ��ϵ��
                if (queryMsg1.getL_material() != null) {
                    for (QueryField queryfield : queryMsg1.getL_material()) {// ��װ��ϵ��
                        QueryDetailinfo filldt = new QueryDetailinfo();
                        // ƴװ���
                        filldt.setMaterial(queryfield.getMaterial());
                        filldt.setQry_CostCenter(queryfield.getCostCenter());
                        filldt.setQry_factor(queryfield.getFactor());

                        if (getLianInfo.containsKey(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                + queryfield.getMaterial() + queryfield.getCostCenter() + queryfield.getFactor())) {
                            // �ɱ�����+����Ҫ��
                            QueryResultto currentDto =
                                    getLianInfo.get(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                            + queryfield.getMaterial() + queryfield.getCostCenter()
                                            + queryfield.getFactor());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setResult_CostCenter(currentDto.getCcostcenterid());
                            filldt.setResult_factor(currentDto.getCelementid());
                        }
                        else if (getLianInfo.containsKey(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                + queryfield.getMaterial() + queryfield.getCostCenter())) {
                            // �ɱ�����+��
                            QueryResultto currentDto =
                                    getLianInfo.get(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                            + queryfield.getMaterial() + queryfield.getCostCenter());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setResult_CostCenter(currentDto.getCcostcenterid());
                            filldt.setResult_factor(currentDto.getCelementid());
                        }
                        else if (getLianInfo.containsKey(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                + queryfield.getMaterial() + queryfield.getFactor())) {
                            // ��+����Ҫ��
                            QueryResultto currentDto =
                                    getLianInfo.get(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                            + queryfield.getMaterial() + queryfield.getFactor());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setResult_CostCenter(currentDto.getCcostcenterid());
                            filldt.setResult_factor(currentDto.getCelementid());
                        }
                        else if (getLianInfo.containsKey(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                + queryfield.getMaterial())) {
                            // ��+��
                            QueryResultto currentDto =
                                    getLianInfo.get(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                            + queryfield.getMaterial());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setResult_CostCenter(currentDto.getCcostcenterid());
                            filldt.setResult_factor(currentDto.getCelementid());
                        }

                        if (oneDto.getL_material() != null) {

                            oneDto.getL_material().add(filldt);
                        }
                        else {

                            List<QueryDetailinfo> newList = new ArrayList<QueryDetailinfo>();
                            newList.add(filldt);
                            oneDto.setL_material(newList);
                        }
                    }
                }
                // 3 ��װ��ϵ��
                if (queryMsg1.getF_material() != null) {
                    for (QueryField queryfield : queryMsg1.getF_material()) {// ��װ��ϵ��
                        QueryDetailinfo filldt = new QueryDetailinfo();
                        // ƴװ���
                        filldt.setMaterial(queryfield.getMaterial());
                        filldt.setQry_CostCenter(queryfield.getCostCenter());
                        filldt.setQry_factor(queryfield.getFactor());

                        if (getFuInfo.containsKey(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                + queryfield.getMaterial() + queryfield.getCostCenter() + queryfield.getFactor())) {
                            // �ɱ�����+����Ҫ��
                            QueryResultto currentDto =
                                    getFuInfo.get(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                            + queryfield.getMaterial() + queryfield.getCostCenter()
                                            + queryfield.getFactor());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setResult_CostCenter(currentDto.getCcostcenterid());
                            filldt.setResult_factor(currentDto.getCelementid());
                        }
                        else if (getFuInfo.containsKey(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                + queryfield.getMaterial() + queryfield.getCostCenter())) {
                            // �ɱ�����+��
                            QueryResultto currentDto =
                                    getFuInfo.get(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                            + queryfield.getMaterial() + queryfield.getCostCenter());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setResult_CostCenter(currentDto.getCcostcenterid());
                            filldt.setResult_factor(currentDto.getCelementid());
                        }
                        else if (getFuInfo.containsKey(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                + queryfield.getMaterial() + queryfield.getFactor())) {
                            // ��+����Ҫ��
                            QueryResultto currentDto =
                                    getFuInfo.get(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                            + queryfield.getMaterial() + queryfield.getFactor());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setResult_CostCenter(currentDto.getCcostcenterid());
                            filldt.setResult_factor(currentDto.getCelementid());
                        }
                        else if (getFuInfo.containsKey(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                + queryfield.getMaterial())) {
                            // ��+��
                            QueryResultto currentDto =
                                    getFuInfo.get(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                            + queryfield.getMaterial());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setResult_CostCenter(currentDto.getCcostcenterid());
                            filldt.setResult_factor(currentDto.getCelementid());
                        }

                        if (oneDto.getF_material() != null) {

                            oneDto.getF_material().add(filldt);
                        }
                        else {

                            List<QueryDetailinfo> newList = new ArrayList<QueryDetailinfo>();
                            newList.add(filldt);
                            oneDto.setF_material(newList);
                        }
                    }
                }

            }
            else {// ���ɱ����ļ���=N
                if (queryMsg1.getM_materials() != null) {
                    for (QueryField queryfield : queryMsg1.getM_materials()) {// ��װ��ϵ��
                        QueryDetailinfo filldt = new QueryDetailinfo();
                        // ƴװ���
                        filldt.setMaterial(queryfield.getMaterial());
                        filldt.setQry_CostCenter(queryfield.getCostCenter());
                        filldt.setQry_factor(queryfield.getFactor());

                        if (getZhuInfo.containsKey(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                + queryfield.getMaterial() + queryfield.getFactor())) {
                            // ��+����Ҫ��
                            QueryResultto currentDto =
                                    getZhuInfo.get(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                            + queryfield.getMaterial() + queryfield.getFactor());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setResult_CostCenter(currentDto.getCcostcenterid());
                            filldt.setResult_factor(currentDto.getCelementid());
                        }

                        else if (getZhuInfo.containsKey(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                + queryfield.getMaterial())) {
                            // ��+��
                            QueryResultto currentDto =
                                    getZhuInfo.get(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                            + queryfield.getMaterial());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setResult_CostCenter(currentDto.getCcostcenterid());
                            filldt.setResult_factor(currentDto.getCelementid());
                        }

                        if (oneDto.getM_Cofficient() != null) {
                            if (filldt.getCofficient() == null) {
                                filldt.setCofficient(UFDouble.ONE_DBL);
                            }
                            oneDto.getM_Cofficient().add(filldt);
                        }
                        else {
                            if (filldt.getCofficient() == null) {
                                filldt.setCofficient(UFDouble.ONE_DBL);
                            }
                            List<QueryDetailinfo> newList = new ArrayList<QueryDetailinfo>();
                            newList.add(filldt);
                            oneDto.setM_Cofficient(newList);
                        }

                    }
                }
                // 2 ��װ��ϵ��
                if (queryMsg1.getL_material() != null) {
                    for (QueryField queryfield : queryMsg1.getL_material()) {// ��װ��ϵ��
                        QueryDetailinfo filldt = new QueryDetailinfo();
                        // ƴװ���
                        filldt.setMaterial(queryfield.getMaterial());
                        filldt.setQry_CostCenter(queryfield.getCostCenter());
                        filldt.setQry_factor(queryfield.getFactor());

                        if (getLianInfo.containsKey(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                + queryfield.getMaterial() + queryfield.getFactor())) {
                            // ��+����Ҫ��
                            QueryResultto currentDto =
                                    getLianInfo.get(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                            + queryfield.getMaterial() + queryfield.getFactor());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setResult_CostCenter(currentDto.getCcostcenterid());
                            filldt.setResult_factor(currentDto.getCelementid());
                        }
                        else if (getLianInfo.containsKey(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                + queryfield.getMaterial())) {
                            // ��+��
                            QueryResultto currentDto =
                                    getLianInfo.get(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                            + queryfield.getMaterial());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setResult_CostCenter(currentDto.getCcostcenterid());
                            filldt.setResult_factor(currentDto.getCelementid());
                        }

                        if (oneDto.getL_material() != null) {

                            oneDto.getL_material().add(filldt);
                        }
                        else {

                            List<QueryDetailinfo> newList = new ArrayList<QueryDetailinfo>();
                            newList.add(filldt);
                            oneDto.setL_material(newList);
                        }
                    }
                }
                // 3 ��װ��ϵ��
                if (queryMsg1.getF_material() != null) {
                    for (QueryField queryfield : queryMsg1.getF_material()) {// ��װ��ϵ��
                        QueryDetailinfo filldt = new QueryDetailinfo();
                        // ƴװ���
                        filldt.setMaterial(queryfield.getMaterial());
                        filldt.setQry_CostCenter(queryfield.getCostCenter());
                        filldt.setQry_factor(queryfield.getFactor());

                        if (getFuInfo.containsKey(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                + queryfield.getMaterial() + queryfield.getFactor())) {
                            // ��+����Ҫ��
                            QueryResultto currentDto =
                                    getFuInfo.get(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                            + queryfield.getMaterial() + queryfield.getFactor());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setResult_CostCenter(currentDto.getCcostcenterid());
                            filldt.setResult_factor(currentDto.getCelementid());
                        }
                        else if (getFuInfo.containsKey(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                + queryfield.getMaterial())) {
                            // ��+��
                            QueryResultto currentDto =
                                    getFuInfo.get(queryMsg1.getPk_org() + queryMsg1.getM_material()
                                            + queryfield.getMaterial());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setCofficient(currentDto.getOppositenum());
                            filldt.setResult_CostCenter(currentDto.getCcostcenterid());
                            filldt.setResult_factor(currentDto.getCelementid());
                        }

                        if (oneDto.getF_material() != null) {

                            oneDto.getF_material().add(filldt);
                        }
                        else {

                            List<QueryDetailinfo> newList = new ArrayList<QueryDetailinfo>();
                            newList.add(filldt);
                            oneDto.setF_material(newList);
                        }
                    }
                }
            }
            qryDetail.add(oneDto);
        }
        return qryDetail;
    }

    /**
     * �õ�UAP
     * 37767(��Ӣ��) 15:31:39
     * ��ѯ�ӿ�
     *
     * @return IUAPQueryBS
     */
    private List<QueryResultto> f_AllCofficient(List<QueryDto> queryDtoList, String pk_factorchart, String elementsystem)
            throws BusinessException {
        List<String> m_materials = new ArrayList<String>();
        for (QueryDto queryDto : queryDtoList) {
            m_materials.add(queryDto.getM_material());
        }
        CMSqlBuilder inbuild = new CMSqlBuilder();
        inbuild.append(CoprodcoefHeadVO.getDefaultTableName() + "." + CoprodcoefHeadVO.PK_ORG, queryDtoList.get(0)
                .getPk_org());
        inbuild.and();
        inbuild.append(CoprodcoefHeadVO.getDefaultTableName() + "." + CoprodcoefHeadVO.CMATERIALID, m_materials);
        CMSqlBuilder querySql = new CMSqlBuilder();
        querySql.append(" from ");
        querySql.append(CoprodcoefHeadVO.getDefaultTableName() + " " + CoprodcoefHeadVO.getDefaultTableName());
        querySql.append("  inner join ");
        querySql.append(CoprodcoefItemVO.getDefaultTableName() + " " + CoprodcoefItemVO.getDefaultTableName());
        querySql.append("  on ");
        querySql.append(CoprodcoefHeadVO.getDefaultTableName() + "." + CoprodcoefHeadVO.CCOPRODCOEFID + "="
                + CoprodcoefItemVO.getDefaultTableName() + "." + CoprodcoefItemVO.CCOPRODCOEFID);
        querySql.append(" where ");
        querySql.append(inbuild.toString());
        querySql.append(" and " + CoprodcoefHeadVO.getDefaultTableName() + ".dr=0" + " and "
                + CoprodcoefItemVO.getDefaultTableName() + ".dr=0");
        EfficientBillQuery<CoprodcoefAggVO> query = new EfficientBillQuery<CoprodcoefAggVO>(CoprodcoefAggVO.class);
        CoprodcoefAggVO[] aggVOs = query.query(querySql.toString());
        List<QueryResultto> resultVOs = new ArrayList<QueryResultto>();
        // ����+����Ҫ����ϵ+Ҫ�ر��
        Map<String, Set<QueryResultto>> factorchartNullMap = new HashMap<String, Set<QueryResultto>>();
        // ����+��ϵ��+Ҫ�ر��
        Map<String, Set<QueryResultto>> resultNullMap = new HashMap<String, Set<QueryResultto>>();
        // ����+��ϵ+Ҫ�ر�
        Map<String, Set<QueryResultto>> resultNotNullMap = new HashMap<String, Set<QueryResultto>>();
        for (CoprodcoefAggVO aggVO : aggVOs) {
            CoprodcoefHeadVO headVO = aggVO.getParentVO();
            if (!(headVO.getPk_factorchart() == null) && !pk_factorchart.equals(headVO.getPk_factorchart())) {
                continue;
            }
            CoprodcoefItemVO[] itemVOs = (CoprodcoefItemVO[]) aggVO.getChildrenVO();
            for (CoprodcoefItemVO itemVO : itemVOs) {
                QueryResultto dto1 = new QueryResultto();
                dto1.setPk_org(headVO.getPk_org() == null ? "" : headVO.getPk_org());
                dto1.setM_material(headVO.getCmaterialid() == null ? "" : headVO.getCmaterialid());
                dto1.setM_material_b(itemVO.getCmaterialid() == null ? "" : itemVO.getCmaterialid());
                dto1.setCcostcenterid(itemVO.getCcostcenterid() == null ? "" : itemVO.getCcostcenterid());
                dto1.setCelementid(itemVO.getCelementid() == null ? "" : itemVO.getCelementid());
                dto1.setOppositenum(itemVO.getNrelcoefficient());
                dto1.setProducttype(new UFDouble(itemVO.getIproducttype()));
                if (!(headVO.getPk_factorchart() == null)) {
                    if (!(headVO.getPk_elementsystem() == null) && elementsystem.equals(headVO.getPk_elementsystem())) {
                        if (resultNotNullMap.get(headVO.getCmaterialid()) == null) {
                            Set<QueryResultto> innerSet = new HashSet<QueryResultto>();
                            innerSet.add(dto1);
                            resultNotNullMap.put(headVO.getCmaterialid(), innerSet);
                        }
                        else {
                            resultNotNullMap.get(headVO.getCmaterialid()).add(dto1);
                        }
                    }
                }
                else {
                    if (!(headVO.getPk_elementsystem() == null) && elementsystem.equals(headVO.getPk_elementsystem())) {
                        if (factorchartNullMap.get(headVO.getCmaterialid()) == null) {
                            Set<QueryResultto> innerSet = new HashSet<QueryResultto>();
                            innerSet.add(dto1);
                            factorchartNullMap.put(headVO.getCmaterialid(), innerSet);
                        }
                        else {
                            factorchartNullMap.get(headVO.getCmaterialid()).add(dto1);
                        }
                    }
                    else if (headVO.getPk_elementsystem() == null) {
                        if (resultNullMap.get(headVO.getCmaterialid()) == null) {
                            Set<QueryResultto> innerSet = new HashSet<QueryResultto>();
                            innerSet.add(dto1);
                            resultNullMap.put(headVO.getCmaterialid(), innerSet);
                        }
                        else {
                            resultNullMap.get(headVO.getCmaterialid()).add(dto1);
                        }
                    }
                }
            }
        }

        for (QueryDto queryDto : queryDtoList) {
            if (resultNotNullMap.get(queryDto.getM_material()) != null) {
                resultVOs.addAll(resultNotNullMap.get(queryDto.getM_material()));
            }
            else if (factorchartNullMap.get(queryDto.getM_material()) != null) {
                resultVOs.addAll(factorchartNullMap.get(queryDto.getM_material()));
            }
            else if (resultNullMap.get(queryDto.getM_material()) != null) {
                resultVOs.addAll(resultNullMap.get(queryDto.getM_material()));
            }
        }

        return resultVOs;
    }
}
