package nc.ui.mapub.driver.ref;

import nc.bd.framework.db.CMSqlBuilder;
import nc.ui.bd.pub.ref.CMAbstractRefModel;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.mapub.driver.entity.DriverVO;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * <b> 成本动因的参照类 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-7-23
 *
 * @author:jilu
 */
public class DriverRefModel extends CMAbstractRefModel {
    /***
     * 字段名称【显示的】
     */
    public static final String[] GET_FIELDNAME() {
        return new String[] {
                CMDriverLangConst.getREFMODELCODE(), CMDriverLangConst.getREFMODELNAME()
        };
    }

    /***
     * 字段编码【显示的】
     */
    public static final String[] FIELDCODE = {
        DriverVO.VCODE, DriverVO.VNAME
    };

    /***
     * 参照标题
     */
    public static final String GET_TITLE() {
        return CMDriverLangConst.getREFMODELTITLE();
    }

    /***
     * PK字段
     */
    public static final String PKCODE = DriverVO.CDRIVERID;

    /***
     * 表名
     */
    public static final String TABLENAME = "cm_driver";

    /***
     * 隐藏字段【不可见的】
     */
    public static final String[] HIDDENFIELDECODE = {
        DriverVO.VFORMULAVALUE, DriverVO.VFORMULACODE, DriverVO.VJAVAPATH, DriverVO.CDRIVERID, DriverVO.VNOTE,
        DriverVO.PK_GROUP, DriverVO.PK_ORG, DriverVO.PK_ORG_V, DriverVO.CREATOR, DriverVO.CREATIONTIME,
        DriverVO.MODIFIER, DriverVO.MODIFIEDTIME
    };

    /**
     * 默认构造函数
     */
    public DriverRefModel() {
        super();
        // this.setMatchPkWithWherePart(true);
    }

    @Override
    public int getDefaultFieldCount() {
        return DriverRefModel.FIELDCODE.length;
    }

    @Override
    public java.lang.String[] getFieldCode() {
        return DriverRefModel.FIELDCODE;
    }

    @Override
    public java.lang.String[] getFieldName() {
        return DriverRefModel.GET_FIELDNAME();
    }

    @Override
    public java.lang.String[] getHiddenFieldCode() {
        return DriverRefModel.HIDDENFIELDECODE;
    }

    @Override
    public String getPkFieldCode() {
        return DriverRefModel.PKCODE;
    }

    @Override
    public String getRefTitle() {
        return DriverRefModel.GET_TITLE();
    }

    @Override
    public String getTableName() {
        return DriverRefModel.TABLENAME;
    }

    @Override
    public String getBlurValue() {
        // 不模糊匹配
        return null;
    }

    @Override
    protected String getEnvWherePart() {
        // modified by shangzhm at 2014-04-29参照可参照到全局级和工厂级
        CMSqlBuilder whereSql = new CMSqlBuilder();
        whereSql.append(" (");
        whereSql.append(DriverVO.PK_GROUP, CMDriverLangConst.GLFLAG);
        if (!PubAppTool.isNull(this.getPk_org())) {
            whereSql.append(" or " + DriverVO.PK_ORG, this.getPk_org());
        }
        whereSql.append(")");

        return whereSql.toString();
    }

    @Override
    public void filterValueChanged(ValueChangedEvent changedValue) {
        super.filterValueChanged(changedValue);
        String[] pkOrgs = (String[]) changedValue.getNewValue();
        if (pkOrgs != null && pkOrgs.length > 0) {
            this.setPk_org(pkOrgs[0]);
        }
    }

    @Override
    public void reset() {
        this.setFilterRefNodeName(new String[] {/* -=notranslate=- */
                "业务单元"
        });
    }

}
