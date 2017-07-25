package nc.ui.mapub.allocfac.ref;

import nc.itf.org.IOrgResourceCodeConst;
import nc.md.MDBaseQueryFacade;
import nc.ui.bd.pub.ref.CMAbstractRefModel;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.vo.mapub.allocfac.entity.AllocfacHeadVO;
import nc.vo.mapub.allocfac.util.CMMLangConstAllocfac;
import nc.vo.pub.BusinessException;
import nc.vo.util.VisibleUtil;

public class AllocfacRefModel extends CMAbstractRefModel {

    /***
     * �ֶ����ơ���ʾ�ġ�
     */
    public static final String[] GET_FIELDNAME() {
        return new String[] {
                CMMLangConstAllocfac.getREFFIELDNAME_CODE(), CMMLangConstAllocfac.getREFFIELDNAME_NAME(),
        };
    }

    /***
     * �ֶα��롾��ʾ�ġ�
     */
    public static final String[] FIELDCODE = {
        AllocfacHeadVO.VCODE, AllocfacHeadVO.VNAME
    };

    /***
     * ���ձ���
     */
    public static final String GET_TITLE() {
        return CMMLangConstAllocfac.getREFTITLE();
    }

    /***
     * PK�ֶ�
     */
    public static final String PKCODE = AllocfacHeadVO.CALLOCFACID;

    /***
     * ����
     */
    public static final String TABLENAME = "cm_allocfac";

    /***
     * �����ֶΡ����ɼ��ġ�
     */
    public static final String[] HIDDENFIELDECODE = {
        AllocfacHeadVO.CALLOCFACID, AllocfacHeadVO.IALLOCTYPE, AllocfacHeadVO.PK_GROUP, AllocfacHeadVO.PK_ORG,
        AllocfacHeadVO.PK_ORG_V, AllocfacHeadVO.VNOTE, AllocfacHeadVO.CREATOR, AllocfacHeadVO.CREATIONTIME,
        AllocfacHeadVO.MODIFIER, AllocfacHeadVO.MODIFIEDTIME
    };

    /**
     * Ĭ�Ϲ��캯��
     */
    public AllocfacRefModel() {
        super();
        this.setMatchPkWithWherePart(true);
    }

    @Override
    public int getDefaultFieldCount() {
        return AllocfacRefModel.FIELDCODE.length;
    }

    @Override
    public java.lang.String[] getFieldCode() {
        return AllocfacRefModel.FIELDCODE;
    }

    @Override
    public java.lang.String[] getFieldName() {
        return AllocfacRefModel.GET_FIELDNAME();
    }

    @Override
    public java.lang.String[] getHiddenFieldCode() {
        return AllocfacRefModel.HIDDENFIELDECODE;
    }

    @Override
    public String getPkFieldCode() {
        return AllocfacRefModel.PKCODE;
    }

    @Override
    public String getRefTitle() {
        return AllocfacRefModel.GET_TITLE();
    }

    @Override
    public String getTableName() {
        return AllocfacRefModel.TABLENAME;
    }

    @Override
    public String getBlurValue() {
        // ��ģ��ƥ��
        return null;
    }

    @Override
    protected String getEnvWherePart() {
        try {
            return " dr=0 and "
                    + VisibleUtil.getRefVisibleCondition(this.getPk_group(), this.getPk_org(), MDBaseQueryFacade
                            .getInstance().getBeanByFullClassName(AllocfacHeadVO.class.getName()).getID());
        }
        catch (BusinessException e) {
        }
        return null;
    }

    @Override
    public void reset() {
        super.reset();
        this.setResourceID(IOrgResourceCodeConst.FACTORY);
        String fac = CMMLangConstAllocfac.getORG();
        this.setFilterRefNodeName(new String[] {
                fac
        });
    }

    @Override
    public void filterValueChanged(ValueChangedEvent changedValue) {
        super.filterValueChanged(changedValue);
        String[] pk_orgs = (String[]) changedValue.getNewValue();
        if (pk_orgs != null && pk_orgs.length > 0) {
            this.setPk_org(pk_orgs[0]);
        }
    }
}
