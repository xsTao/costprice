/**
 *
 */
package nc.vo.mapub.driver.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

@SuppressWarnings("serial")
public class DriverVO extends SuperVO {
    private java.lang.String cdriverid;

    private java.lang.String pk_group;

    private java.lang.String pk_org;

    private java.lang.String pk_org_v;

    private java.lang.String vcode;

    private java.lang.String vname;

    private java.lang.String vname2;

    private java.lang.String vname3;

    private java.lang.String vname4;

    private java.lang.String vname5;

    private java.lang.String vname6;

    private java.lang.String creator;

    private nc.vo.pub.lang.UFDateTime creationtime;

    private java.lang.String modifier;

    private nc.vo.pub.lang.UFDateTime modifiedtime;

    private java.lang.String vformulavalue;

    private java.lang.String vformulavalue2;

    private java.lang.String vformulavalue3;

    private java.lang.String vformulavalue4;

    private java.lang.String vformulavalue5;

    private java.lang.String vformulavalue6;

    private java.lang.String vjavapath;

    private java.lang.String vformulacode;

    private java.lang.String vnote;

    private java.lang.Integer dr = 0;

    private nc.vo.pub.lang.UFDateTime ts;

    public static final String CDRIVERID = "cdriverid";

    public static final String PK_GROUP = "pk_group";

    public static final String PK_ORG = "pk_org";

    public static final String PK_ORG_V = "pk_org_v";

    public static final String VCODE = "vcode";

    public static final String VNAME = "vname";

    public static final String VNAME2 = "vname2";

    public static final String VNAME3 = "vname3";

    public static final String VNAME4 = "vname4";

    public static final String VNAME5 = "vname5";

    public static final String VNAME6 = "vname6";

    public static final String CREATOR = "creator";

    public static final String CREATIONTIME = "creationtime";

    public static final String MODIFIER = "modifier";

    public static final String MODIFIEDTIME = "modifiedtime";

    public static final String VFORMULAVALUE = "vformulavalue";

    public static final String VFORMULAVALUE2 = "vformulavalue2";

    public static final String VFORMULAVALUE3 = "vformulavalue3";

    public static final String VFORMULAVALUE4 = "vformulavalue4";

    public static final String VFORMULAVALUE5 = "vformulavalue5";

    public static final String VFORMULAVALUE6 = "vformulavalue6";

    public static final String VJAVAPATH = "vjavapath";

    public static final String VFORMULACODE = "vformulacode";

    public static final String VNOTE = "vnote";

    @Override
    public IVOMeta getMetaData() {
        return VOMetaFactory.getInstance().getVOMeta("cm.cm_driver");
    }

    /**
     * ����cdriverid��Getter����. ��������:2010-03-29 11:35:30
     *
     * @return java.lang.String
     */
    public java.lang.String getCdriverid() {
        return this.cdriverid;
    }

    /**
     * ����cdriverid��Setter����. ��������:2010-03-29 11:35:30
     *
     * @param newCdriverid
     *            java.lang.String
     */
    public void setCdriverid(java.lang.String newCdriverid) {
        this.cdriverid = newCdriverid;
    }

    /**
     * ����pk_group��Getter����. ��������:2010-03-29 11:35:30
     *
     * @return java.lang.String
     */
    public java.lang.String getPk_group() {
        return this.pk_group;
    }

    /**
     * ����pk_group��Setter����. ��������:2010-03-29 11:35:30
     *
     * @param newPk_group
     *            java.lang.String
     */
    public void setPk_group(java.lang.String newPk_group) {
        this.pk_group = newPk_group;
    }

    /**
     * ����pk_org��Getter����. ��������:2010-03-29 11:35:30
     *
     * @return java.lang.String
     */
    public java.lang.String getPk_org() {
        return this.pk_org;
    }

    /**
     * ����pk_org��Setter����. ��������:2010-03-29 11:35:30
     *
     * @param newPk_org
     *            java.lang.String
     */
    public void setPk_org(java.lang.String newPk_org) {
        this.pk_org = newPk_org;
    }

    /**
     * ����pk_org_v��Getter����. ��������:2010-03-29 11:35:30
     *
     * @return java.lang.String
     */
    public java.lang.String getPk_org_v() {
        return this.pk_org_v;
    }

    /**
     * ����pk_org_v��Setter����. ��������:2010-03-29 11:35:30
     *
     * @param newPk_org_v
     *            java.lang.String
     */
    public void setPk_org_v(java.lang.String newPk_org_v) {
        this.pk_org_v = newPk_org_v;
    }

    /**
     * ����vcode��Getter����. ��������:2010-03-29 11:35:30
     *
     * @return java.lang.String
     */
    public java.lang.String getVcode() {
        return this.vcode;
    }

    /**
     * ����vcode��Setter����. ��������:2010-03-29 11:35:30
     *
     * @param newVcode
     *            java.lang.String
     */
    public void setVcode(java.lang.String newVcode) {
        this.vcode = newVcode;
    }

    /**
     * ����vname��Getter����. ��������:2010-03-29 11:35:30
     *
     * @return java.lang.String
     */
    public java.lang.String getVname() {
        return this.vname;
    }

    /**
     * ����vname��Setter����. ��������:2010-03-29 11:35:30
     *
     * @param newVname
     *            java.lang.String
     */
    public void setVname(java.lang.String newVname) {
        this.vname = newVname;
    }

    /**
     * ����vname2��Getter����. ��������:2010-03-29 11:35:30
     *
     * @return java.lang.String
     */
    public java.lang.String getVname2() {
        return this.vname2;
    }

    /**
     * ����vname2��Setter����. ��������:2010-03-29 11:35:30
     *
     * @param newVname2
     *            java.lang.String
     */
    public void setVname2(java.lang.String newVname2) {
        this.vname2 = newVname2;
    }

    public java.lang.String getVname3() {
        return this.vname3;
    }

    public void setVname3(java.lang.String vname3) {
        this.vname3 = vname3;
    }

    public java.lang.String getVname4() {
        return this.vname4;
    }

    public void setVname4(java.lang.String vname4) {
        this.vname4 = vname4;
    }

    public java.lang.String getVname5() {
        return this.vname5;
    }

    public void setVname5(java.lang.String vname5) {
        this.vname5 = vname5;
    }

    public java.lang.String getVname6() {
        return this.vname6;
    }

    public void setVname6(java.lang.String vname6) {
        this.vname6 = vname6;
    }

    /**
     * ����creator��Getter����. ��������:2010-03-29 11:35:30
     *
     * @return java.lang.String
     */
    public java.lang.String getCreator() {
        return this.creator;
    }

    /**
     * ����creator��Setter����. ��������:2010-03-29 11:35:30
     *
     * @param newCreator
     *            java.lang.String
     */
    public void setCreator(java.lang.String newCreator) {
        this.creator = newCreator;
    }

    /**
     * ����creationtime��Getter����. ��������:2010-03-29 11:35:30
     *
     * @return nc.vo.pub.lang.UFDate
     */
    public nc.vo.pub.lang.UFDateTime getCreationtime() {
        return this.creationtime;
    }

    /**
     * ����creationtime��Setter����. ��������:2010-03-29 11:35:30
     *
     * @param newCreationtime
     *            nc.vo.pub.lang.UFDate
     */
    public void setCreationtime(nc.vo.pub.lang.UFDateTime newCreationtime) {
        this.creationtime = newCreationtime;
    }

    /**
     * ����modifier��Getter����. ��������:2010-03-29 11:35:30
     *
     * @return java.lang.String
     */
    public java.lang.String getModifier() {
        return this.modifier;
    }

    /**
     * ����modifier��Setter����. ��������:2010-03-29 11:35:30
     *
     * @param newModifier
     *            java.lang.String
     */
    public void setModifier(java.lang.String newModifier) {
        this.modifier = newModifier;
    }

    /**
     * ����modifiedtime��Getter����. ��������:2010-03-29 11:35:30
     *
     * @return nc.vo.pub.lang.UFDate
     */
    public nc.vo.pub.lang.UFDateTime getModifiedtime() {
        return this.modifiedtime;
    }

    /**
     * ����modifiedtime��Setter����. ��������:2010-03-29 11:35:30
     *
     * @param newModifiedtime
     *            nc.vo.pub.lang.UFDate
     */
    public void setModifiedtime(nc.vo.pub.lang.UFDateTime newModifiedtime) {
        this.modifiedtime = newModifiedtime;
    }

    /**
     * ����vformulavalue��Getter����. ��������:2010-03-29 11:35:30
     *
     * @return java.lang.String
     */
    public java.lang.String getVformulavalue() {
        return this.vformulavalue;
    }

    /**
     * ����vformulavalue��Setter����. ��������:2010-03-29 11:35:30
     *
     * @param newVformulavalue
     *            java.lang.String
     */
    public void setVformulavalue(java.lang.String newVformulavalue) {
        this.vformulavalue = newVformulavalue;
    }

    public java.lang.String getVformulavalue2() {
        return this.vformulavalue2;
    }

    public void setVformulavalue2(java.lang.String vformulavalue2) {
        this.vformulavalue2 = vformulavalue2;
    }

    public java.lang.String getVformulavalue3() {
        return this.vformulavalue3;
    }

    public void setVformulavalue3(java.lang.String vformulavalue3) {
        this.vformulavalue3 = vformulavalue3;
    }

    public java.lang.String getVformulavalue4() {
        return this.vformulavalue4;
    }

    public void setVformulavalue4(java.lang.String vformulavalue4) {
        this.vformulavalue4 = vformulavalue4;
    }

    public java.lang.String getVformulavalue5() {
        return this.vformulavalue5;
    }

    public void setVformulavalue5(java.lang.String vformulavalue5) {
        this.vformulavalue5 = vformulavalue5;
    }

    public java.lang.String getVformulavalue6() {
        return this.vformulavalue6;
    }

    public void setVformulavalue6(java.lang.String vformulavalue6) {
        this.vformulavalue6 = vformulavalue6;
    }

    /**
     * ����vjavapath��Getter����. ��������:2010-03-29 11:35:30
     *
     * @return java.lang.String
     */
    public java.lang.String getVjavapath() {
        return this.vjavapath;
    }

    /**
     * ����vjavapath��Setter����. ��������:2010-03-29 11:35:30
     *
     * @param newVjavapath
     *            java.lang.String
     */
    public void setVjavapath(java.lang.String newVjavapath) {
        this.vjavapath = newVjavapath;
    }

    /**
     * ����vformulacode��Getter����. ��������:2010-03-29 11:35:30
     *
     * @return java.lang.String
     */
    public java.lang.String getVformulacode() {
        return this.vformulacode;
    }

    /**
     * ����vformulacode��Setter����. ��������:2010-03-29 11:35:30
     *
     * @param newVformulacode
     *            java.lang.String
     */
    public void setVformulacode(java.lang.String newVformulacode) {
        this.vformulacode = newVformulacode;
    }

    /**
     * ����vnote��Getter����. ��������:2010-03-29 11:35:30
     *
     * @return java.lang.String
     */
    public java.lang.String getVnote() {
        return this.vnote;
    }

    /**
     * ����vnote��Setter����. ��������:2010-03-29 11:35:30
     *
     * @param newVnote
     *            java.lang.String
     */
    public void setVnote(java.lang.String newVnote) {
        this.vnote = newVnote;
    }

    /**
     * ����dr��Getter����. ��������:2010-03-29 11:35:30
     *
     * @return java.lang.Integer
     */
    public java.lang.Integer getDr() {
        return this.dr;
    }

    /**
     * ����dr��Setter����. ��������:2010-03-29 11:35:30
     *
     * @param newDr
     *            java.lang.Integer
     */
    public void setDr(java.lang.Integer newDr) {
        this.dr = newDr;
    }

    /**
     * ����ts��Getter����. ��������:2010-03-29 11:35:30
     *
     * @return nc.vo.pub.lang.UFDateTime
     */
    public nc.vo.pub.lang.UFDateTime getTs() {
        return this.ts;
    }

    /**
     * ����ts��Setter����. ��������:2010-03-29 11:35:30
     *
     * @param newTs
     *            nc.vo.pub.lang.UFDateTime
     */
    public void setTs(nc.vo.pub.lang.UFDateTime newTs) {
        this.ts = newTs;
    }

    /**
     * <p>
     * ȡ�ø�VO�����ֶ�.
     * <p>
     * ��������:2010-03-29 11:35:30
     *
     * @return java.lang.String
     */
    @Override
    public java.lang.String getParentPKFieldName() {
        return null;
    }

    /**
     * <p>
     * ȡ�ñ�����.
     * <p>
     * ��������:2010-03-29 11:35:30
     *
     * @return java.lang.String
     */
    @Override
    public java.lang.String getPKFieldName() {
        return "cdriverid";
    }

    /**
     * <p>
     * ���ر�����.
     * <p>
     * ��������:2010-03-29 11:35:30
     *
     * @return java.lang.String
     */
    @Override
    public java.lang.String getTableName() {
        return "cm_driver";
    }

    /**
     * <p>
     * ���ر�����.
     * <p>
     * ��������:2010-03-29 11:35:30
     *
     * @return java.lang.String
     */
    public static java.lang.String getDefaultTableName() {
        return "cm_driver";
    }

    // ϵͳԤ�óɱ��������
    public static final String[] INIT_CODES = new String[] {
        "Sys001", "Sys002", "Sys003", "Sys004", "Sys005", "Sys006", "Sys007", "Sys008", "Sys009", "Sys010", "Sys011",
        "Sys012", "Sys013", "Sys014", "Sys015"
    };

    /**
     * ����Ĭ�Ϸ�ʽ����������. ��������:2010-03-29 11:35:30
     */
    public DriverVO() {
        super();
    }
}
