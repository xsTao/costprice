package nc.bs.mapub.coprodcoef.rule;

import java.util.HashSet;
import java.util.Set;

import nc.bd.framework.base.CMArrayUtil;
import nc.bd.framework.base.CMCollectionUtil;
import nc.bd.framework.base.CMStringUtil;
import nc.bd.framework.db.CMSqlBuilder;
import nc.cmpub.business.adapter.BDAdapter;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bd.material.MaterialVersionVO;
import nc.vo.bd.material.prod.MaterialProdVO;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefAggVO;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefHeadVO;
import nc.vo.mapub.coprodcoef.entity.CoprodcoefItemVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * Ψһ��У��
 * 1.ҵ��Ԫ+������+����Ҫ�ر�ΨһУ��
 * 2.���壺��Ʒ+�ɱ�����+����Ҫ��Ψһ��У��
 * 3.�����������һ�µĲ�Ʒ����Ϊ����Ʒ
 * 4.��Ӧ��Ʒ����Ϊ����Ʒ�Ĳ�Ʒ�����������������뱣��һ��
 * 5.ͬһ��Ʒֻ�ܶ�Ӧһ�ֲ�Ʒ����
 *
 * @since 6.0
 * @version 2014-10-11 ����7:23:21
 * @author zhangshyb
 */
public class CoprodcoefRepeatCheckRule implements IRule<CoprodcoefAggVO> {

    @Override
    public void process(CoprodcoefAggVO[] vos) {
        // ԭΪmap���ͣ���key��valueֵһ������Ϊset 2014-12-4
        // Map<String, String> relationMap = new HashMap<String, String>();
        Set<String> relationSet = new HashSet<String>();
        CoprodcoefHeadVO head = vos[0].getParentVO();
        CoprodcoefItemVO[] bodys = (CoprodcoefItemVO[]) vos[0].getChildren(CoprodcoefItemVO.class);
        // // ��ȡ����Ʒ����Ӧ��bom�汾
        // Set<String> setBomID = new HashSet<String>();
        // setBomID = this.queryBomID(head);
        // this.legalBomID(head, setBomID);
        Set<String> itemmatoids = new HashSet<String>();
        for (CoprodcoefItemVO item : bodys) {
            itemmatoids.add(item.getCmaterialid());
        }
        // ��ȡ���гɱ�����Ǹ������������pk
        Set<String> setMaterialPK = new HashSet<String>();
        setMaterialPK = this.queryLegalMaterial(head, itemmatoids);
        // ҵ��Ԫ+������+����Ҫ�ر�Ψһ��У��
        this.repeatFactoryMat(head);

        for (int i = 0; i < bodys.length; i++) {
            String msg = "";
            // String.format(
            // nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("1014364_0", "0101436401-0482"),
            // Integer.toString(i + 1));
            // ���壺��Ʒ����Ϊ�ɱ������ҷǸ�������
            this.legalMaterial(bodys[i], setMaterialPK, msg);
            // ���壺��Ʒ+�ɱ�����+����Ҫ��Ψһ��У��
            this.repeatMEO(bodys[i], relationSet, msg);
            // �����������һ�µĲ�Ʒ����Ϊ����Ʒ!
            this.repeatMProduct(head, bodys[i], msg);
            // ��Ӧ��Ʒ����Ϊ����Ʒ�Ĳ�Ʒ�����������������뱣��һ��!
            this.repeatTMproduct(head, bodys[i], msg);
            // ͬһ��Ʒֻ�ܶ�Ӧһ�ֲ�Ʒ����!
            this.repeatProductType(i, bodys, msg);
        }
    }

    // ���壺��Ʒ����Ϊ�ɱ������ҷǸ�������
    public void legalMaterial(CoprodcoefItemVO body, Set<String> setMaterialPK, String msg) {
        if (CMCollectionUtil.isNotEmpty(setMaterialPK)) {
            if (!setMaterialPK.contains(BDAdapter.convertMaterialid2Vid(body.getCmaterialid()))) {
                ExceptionUtils.wrappBusinessException(msg
                        + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0289"));
            }
        }
    }

    // bom�汾У��
    public void legalBomID(CoprodcoefHeadVO head, Set<String> setBomID) {
        if (CMCollectionUtil.isNotEmpty(setBomID)) {
            if (!setBomID.contains(head.getCbomid())) {
                head.setCbomid(null);
            }
        }
    }

    public Set<String> queryBomID(CoprodcoefHeadVO head) {
        CMSqlBuilder sql = new CMSqlBuilder();
        sql.select();
        sql.append("cbomid");
        sql.from("bd_bom");
        sql.where();
        sql.append("dr = 0");
        sql.and();
        sql.append("hcmaterialid =");
        sql.append(head.getCmaterialid());
        sql.append("' ");
        sql.and();
        sql.append("fbomtype='1'");
        sql.and();
        sql.append(" (");
        sql.append("pk_group");
        sql.append(" = '");
        sql.append(head.getPk_group());
        sql.append("' ");
        sql.or();
        sql.append("pk_org");
        sql.append(" = '");
        sql.append(head.getPk_org());
        sql.append("')");
        DataAccessUtils dao = new DataAccessUtils();
        IRowSet rowset = dao.query(sql.toString());
        String[] arrayBomID = rowset.toOneDimensionStringArray();
        if (CMArrayUtil.isNotEmpty(arrayBomID)) {
            Set<String> setBomID = new HashSet<String>();
            for (String MaterialPK : arrayBomID) {
                setBomID.add(MaterialPK);
            }
            return setBomID;
        }
        return null;
    }

    public Set<String> queryLegalMaterial(CoprodcoefHeadVO head, Set<String> itemmatoids) {
        CMSqlBuilder sql = new CMSqlBuilder();
        sql.select();
        sql.append("pk_material");
        sql.from("bd_material_v");
        sql.where();
        sql.append("dr = 0");
        sql.and();
        sql.append(MaterialVersionVO.PK_MATERIAL);
        sql.append(" in (");
        sql.select();
        sql.append(MaterialProdVO.PK_MATERIAL);
        sql.from(MaterialProdVO.getDefaultTableName());
        sql.where();
        sql.append(MaterialProdVO.SFCBDX);
        sql.append(" = 'Y'");
        sql.and();
        sql.append(MaterialProdVO.SFFZFW);
        sql.append(" = 'N'");
        sql.and();
        sql.append(MaterialProdVO.PK_MATERIAL, itemmatoids.toArray(new String[itemmatoids.size()]));
        sql.and();
        sql.append(" (");
        sql.append(MaterialProdVO.PK_ORG);
        sql.append(" = '");
        sql.append(head.getPk_group());
        sql.append("' ");
        sql.or();
        sql.append(MaterialProdVO.PK_ORG);
        sql.append(" = '");
        sql.append(head.getPk_org());
        sql.append("' ");
        sql.append(") ");
        sql.and();
        sql.append("dr = 0 )");
        sql.and();
        sql.append("enablestate = 2");
        sql.and();
        sql.append(" (");
        sql.append("pk_org");
        sql.append(" = '");
        sql.append(head.getPk_group());
        sql.append("' ");
        sql.or();
        sql.append("pk_org");
        sql.append(" = '");
        sql.append(head.getPk_org());
        sql.append("')");
        DataAccessUtils dao = new DataAccessUtils();
        IRowSet rowset = dao.query(sql.toString());
        String[] arrayMaterialPK = rowset.toOneDimensionStringArray();
        if (CMArrayUtil.isNotEmpty(arrayMaterialPK)) {
            Set<String> setMaterialPK = new HashSet<String>();
            for (String MaterialPK : arrayMaterialPK) {
                setMaterialPK.add(MaterialPK);
            }
            return setMaterialPK;
        }
        return null;
    }

    // ���壺��Ʒ+�ɱ�����+����Ҫ��Ψһ��У��
    public void repeatMEO(CoprodcoefItemVO body, Set<String> relationSet, String msg) {
        String strMaterialid = (String) body.getAttributeValue(CoprodcoefItemVO.CCOSTCENTERID);
        String strCcostcenterid = (String) body.getAttributeValue(CoprodcoefItemVO.CELEMENTID);
        String strCelementid = (String) body.getAttributeValue(CoprodcoefItemVO.CMATERIALID);
        if (relationSet.contains(strMaterialid + strCcostcenterid + strCelementid)) {
            ExceptionUtils.wrappBusinessException(msg
                    + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0290"));
        }
        else {
            relationSet.add(strMaterialid + strCcostcenterid + strCelementid);
        }
    }

    // �����������һ�µĲ�Ʒ����Ϊ����Ʒ!
    public void repeatMProduct(CoprodcoefHeadVO head, CoprodcoefItemVO body, String msg) {
        if (body.getCmaterialid().equals(head.getCmaterialid()) && !body.getIproducttype().equals(Integer.valueOf(1))) {
            ExceptionUtils.wrappBusinessException(msg
                    + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0291"));
        }
    }

    // ��Ӧ��Ʒ����Ϊ����Ʒ�Ĳ�Ʒ�����������������뱣��һ��!
    public void repeatTMproduct(CoprodcoefHeadVO head, CoprodcoefItemVO body, String msg) {
        if (body.getCmaterialid() != null && body.getIproducttype().equals(Integer.valueOf(1))
                && !body.getCmaterialid().equals(head.getCmaterialid())) {
            ExceptionUtils.wrappBusinessException(msg
                    + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0292"));
        }
    }

    // ͬһ��Ʒֻ�ܶ�Ӧһ�ֲ�Ʒ����!
    public void repeatProductType(int i, CoprodcoefItemVO[] bodys, String msg) {
        for (int k = i + 1; k < bodys.length; k++) {// ͬһ��Ʒֻ�ܶ�Ӧһ����Ʒ����
            if (bodys[k].getAttributeValue(CoprodcoefItemVO.CMATERIALID) == null) {
                continue;
            }
            if (bodys[k].getAttributeValue(CoprodcoefItemVO.CMATERIALID).equals(
                    bodys[i].getAttributeValue(CoprodcoefItemVO.CMATERIALID))
                    && !bodys[k].getAttributeValue(CoprodcoefItemVO.IPRODUCTTYPE).equals(
                            bodys[i].getAttributeValue(CoprodcoefItemVO.IPRODUCTTYPE))) {
                ExceptionUtils.wrappBusinessException(msg
                        + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0293"));
                break;
            }
        }
    }

    // ����+������ΨһУ��
    public void repeatFactoryMat(CoprodcoefHeadVO head) {
        this.getExistData(head);
    }

    // ����+������ΨһУ��
    // select ��������� from sca_productcoefficient where dr = '0' and pk_group = '...' and pk_org = '...' and cmatetialid =
    // '...' and cmcoefficientid = '...'
    private void getExistData(CoprodcoefHeadVO vo) {
        CMSqlBuilder sql = new CMSqlBuilder();
        sql.append(" select 0");
        sql.append(" from ");
        sql.append(CoprodcoefHeadVO.getDefaultTableName());
        sql.append(" where dr=0 ");
        sql.append(" and ");
        sql.append(CoprodcoefHeadVO.PK_GROUP);
        sql.append("='");
        sql.append(vo.getPk_group());
        sql.append("' ");
        sql.append(" and ");
        sql.append(CoprodcoefHeadVO.PK_ORG);
        sql.append("='");
        sql.append(vo.getPk_org());
        sql.append("' ");
        sql.append(" and ");
        sql.append(CoprodcoefHeadVO.CMATERIALID);
        sql.append("='");
        sql.append(vo.getCmaterialid());
        sql.append("' ");
        sql.append(" and ");
        sql.append(CoprodcoefHeadVO.PK_FACTORCHART);
        sql.append("='");
        if (CMStringUtil.isNotEmpty(vo.getPk_factorchart())) {
            sql.append(vo.getPk_factorchart());
        }
        else {
            sql.append("~");
        }
        sql.append("' ");
        sql.append(" and ");
        sql.append(CoprodcoefHeadVO.CCOPRODCOEFID);
        sql.append(" not in ('");
        sql.append(vo.getCcoprodcoefid());
        sql.append("') ");
        DataAccessUtils dao = new DataAccessUtils();
        IRowSet rowset = dao.query(sql.toString());
        if (rowset.size() > 0) {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0",
                    "03810006-0296"));
        }

    }
}
