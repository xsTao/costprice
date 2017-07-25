package nc.ui.mapub.materialpricebase.ref;

import nc.bd.framework.db.CMSqlBuilder;
import nc.md.MDBaseQueryFacade;
import nc.md.model.IBean;
import nc.md.model.MetaDataException;
import nc.ui.bd.ref.AbstractRefModel;
import nc.vo.bd.pub.NODE_TYPE;
import nc.vo.mapub.materialpricebase.entity.CMMLangConstMaterialPriceBase;
import nc.vo.mapub.materialpricebase.entity.MaterialPriceBaseHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.util.VisibleUtil;

/**
 * ���ϼ۸�����
 *
 * @since 6.36
 * @version 2014-11-6 ����3:27:07
 * @author zhangchd
 */
public class PriceBaseRefModel extends AbstractRefModel {

    /***
     * �ֶ����ơ���ʾ�ġ�
     */
    public static final String[] GET_FIELDNAME() {
        return new String[] {
                CMMLangConstMaterialPriceBase.getVPRICEBASE(), CMMLangConstMaterialPriceBase.getVPRICENAME(),
                CMMLangConstMaterialPriceBase.GET_REFFIELDNAME_BEGINDATE(),
                CMMLangConstMaterialPriceBase.GET_REFFIELDNAME_ENDDATE()
        };
    }

    /***
     * �ֶα��롾��ʾ�ġ�
     */
    public static final String[] FIELDCODE = {
        MaterialPriceBaseHeadVO.VPRICECODE, MaterialPriceBaseHeadVO.VPRICENAME, MaterialPriceBaseHeadVO.DBEGINDATE,
        MaterialPriceBaseHeadVO.DENDDATE
    };

    /***
     * ���ձ���
     */
    public static final String GET_TITLE() {
        return CMMLangConstMaterialPriceBase.GET_REFTITLE();
    }

    /***
     * PK�ֶ�
     */
    public static final String PKCODE = MaterialPriceBaseHeadVO.CMATERIALPRICEID;

    /***
     * ����
     */
    public static final String TABLENAME = "bd_materialpricebase";

    /***
     * �����ֶΡ����ɼ��ġ�
     */
    public static final String[] HIDDENFIELDECODE = {
        MaterialPriceBaseHeadVO.CMATERIALPRICEID, MaterialPriceBaseHeadVO.PK_GROUP, MaterialPriceBaseHeadVO.PK_ORG,
        MaterialPriceBaseHeadVO.PK_ORG_V, MaterialPriceBaseHeadVO.VNOTE, MaterialPriceBaseHeadVO.CREATOR,
        MaterialPriceBaseHeadVO.CREATIONTIME, MaterialPriceBaseHeadVO.MODIFIER, MaterialPriceBaseHeadVO.MODIFIEDTIME
    };

    /**
     * Ԫ����ID
     */
    private String beanID;

    /**
     * Ĭ�Ϲ��캯��
     */
    public PriceBaseRefModel() {
        super();
        this.setMatchPkWithWherePart(true);
    }

    @Override
    public int getDefaultFieldCount() {
        return PriceBaseRefModel.FIELDCODE.length;
    }

    @Override
    public java.lang.String[] getFieldCode() {
        return PriceBaseRefModel.FIELDCODE;
    }

    @Override
    public java.lang.String[] getFieldName() {
        return PriceBaseRefModel.GET_FIELDNAME();
    }

    @Override
    public java.lang.String[] getHiddenFieldCode() {
        return PriceBaseRefModel.HIDDENFIELDECODE;
    }

    @Override
    public String getPkFieldCode() {
        return PriceBaseRefModel.PKCODE;
    }

    @Override
    public String getRefTitle() {
        return PriceBaseRefModel.GET_TITLE();
    }

    @Override
    public String getTableName() {
        return PriceBaseRefModel.TABLENAME;
    }

    @Override
    public String getBlurValue() {
        // ��ģ��ƥ��
        return null;
    }

    @Override
    protected String getEnvWherePart() {
        try {
            CMSqlBuilder sql = new CMSqlBuilder();
            sql.append(PriceBaseRefModel.TABLENAME);
            sql.append(".dr=0 ");
            String visualSql =
                    VisibleUtil.getVisibleCondition(this.getPk_org(), this.getPk_group(), NODE_TYPE.ORG_NODE,
                            this.getBeanID());
            if (visualSql != null && visualSql != "") {
                sql.append(" and ");
                sql.append(visualSql);
            }

            return sql.toString();
        }
        catch (BusinessException e) {
            ExceptionUtils.wrappException(e);
        }

        return null;
    }

    /**
     * �õ�Ԫ����
     *
     * @return beanID
     * @throws MetaDataException
     *             Ԫ�����쳣
     */
    private String getBeanID() throws MetaDataException {
        if (this.beanID == null) {
            IBean bean =
                    MDBaseQueryFacade.getInstance().getBeanByFullClassName(MaterialPriceBaseHeadVO.class.getName());
            this.beanID = bean.getID();
        }
        return this.beanID;
    }
}
