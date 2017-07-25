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
 * 材料价格库参照
 *
 * @since 6.36
 * @version 2014-11-6 下午3:27:07
 * @author zhangchd
 */
public class PriceBaseRefModel extends AbstractRefModel {

    /***
     * 字段名称【显示的】
     */
    public static final String[] GET_FIELDNAME() {
        return new String[] {
                CMMLangConstMaterialPriceBase.getVPRICEBASE(), CMMLangConstMaterialPriceBase.getVPRICENAME(),
                CMMLangConstMaterialPriceBase.GET_REFFIELDNAME_BEGINDATE(),
                CMMLangConstMaterialPriceBase.GET_REFFIELDNAME_ENDDATE()
        };
    }

    /***
     * 字段编码【显示的】
     */
    public static final String[] FIELDCODE = {
        MaterialPriceBaseHeadVO.VPRICECODE, MaterialPriceBaseHeadVO.VPRICENAME, MaterialPriceBaseHeadVO.DBEGINDATE,
        MaterialPriceBaseHeadVO.DENDDATE
    };

    /***
     * 参照标题
     */
    public static final String GET_TITLE() {
        return CMMLangConstMaterialPriceBase.GET_REFTITLE();
    }

    /***
     * PK字段
     */
    public static final String PKCODE = MaterialPriceBaseHeadVO.CMATERIALPRICEID;

    /***
     * 表名
     */
    public static final String TABLENAME = "bd_materialpricebase";

    /***
     * 隐藏字段【不可见的】
     */
    public static final String[] HIDDENFIELDECODE = {
        MaterialPriceBaseHeadVO.CMATERIALPRICEID, MaterialPriceBaseHeadVO.PK_GROUP, MaterialPriceBaseHeadVO.PK_ORG,
        MaterialPriceBaseHeadVO.PK_ORG_V, MaterialPriceBaseHeadVO.VNOTE, MaterialPriceBaseHeadVO.CREATOR,
        MaterialPriceBaseHeadVO.CREATIONTIME, MaterialPriceBaseHeadVO.MODIFIER, MaterialPriceBaseHeadVO.MODIFIEDTIME
    };

    /**
     * 元数据ID
     */
    private String beanID;

    /**
     * 默认构造函数
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
        // 不模糊匹配
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
     * 得到元数据
     *
     * @return beanID
     * @throws MetaDataException
     *             元数据异常
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
