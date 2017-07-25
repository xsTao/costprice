package nc.ui.mapub.costtype.model;

import java.util.Vector;

import nc.md.MDBaseQueryFacade;
import nc.md.model.IBean;
import nc.md.model.MetaDataException;
import nc.ui.bd.pub.ref.CMAbstractRefModel;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.vo.bd.pub.NODE_TYPE;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.util.VisibleUtil;

/**
 * <b> ��Ҫ�������� </b>
 * <p>
 * �ɱ����Ͳ���ģ��
 * </p>
 * ��������:2010-10-13
 *
 * @author:wangweir
 */
public class CostTypeRefModel extends CMAbstractRefModel {
    /***
     * �ֶ����ơ���ʾ�ġ�
     */
    public static final String[] GET_FIELDNAME() {
        return new String[] {
                nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("1014364_2", "2101436404-0004")/* @res "�ɱ����ͱ���" */,
                nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("1014364_2", "2101436404-0015")/* @res "�ɱ���������" */,
                nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("1014364_2", "2101436404-0000")/* @res "��Ч����" */,
                nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("1014364_2", "2101436404-0011")
                /* @res "ʧЧ����" */
        };
    }

    /***
     * �ֶα��롾��ʾ�ġ�
     */
    public static final String[] FIELDCODE = {
        CostTypeVO.VCOSTTYPECODE, CostTypeVO.VCOSTTYPENAME, CostTypeVO.CBEGINMONTH, CostTypeVO.CENDMONTH
    };

    /***
     * ���ձ���
     */
    public static final String GET_TITLE() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "0101436401-0548")/* @res "�ɱ�����" */;
    }

    /***
     * PK�ֶ�
     */
    public static final String PKCODE = CostTypeVO.CCOSTTYPEID;

    /***
     * ����
     */
    public static final String TABLENAME = "mapub_costtype";

    /***
     * �����ֶΡ����ɼ��ġ�
     */
    public static final String[] HIDDENFIELDNAME = {
        CostTypeVO.CCOSTTYPEID, CostTypeVO.PK_GROUP, CostTypeVO.PK_ORG, CostTypeVO.VCOSTTYPENAME2,
        CostTypeVO.VCOSTTYPENAME3, CostTypeVO.BDEFAULT, CostTypeVO.BCOMPUTE, CostTypeVO.BCREATETABLE, CostTypeVO.VNOTE,
        CostTypeVO.BSCRAPFACTOR, CostTypeVO.BSHRINKFACTOR
    };

    /**
     * �ɱ�����Ԫ����ID
     */
    private String beanID;

    /**
     * Ĭ�Ϲ��캯��
     */
    public CostTypeRefModel() {
        super();
    }

    @Override
    public int getDefaultFieldCount() {
        return 4;
    }

    @Override
    public java.lang.String[] getFieldCode() {
        return CostTypeRefModel.FIELDCODE;
    }

    @Override
    public java.lang.String[] getFieldName() {
        return CostTypeRefModel.GET_FIELDNAME();
    }

    @Override
    public java.lang.String[] getHiddenFieldCode() {
        return CostTypeRefModel.HIDDENFIELDNAME;
    }

    @Override
    public String getPkFieldCode() {
        return CostTypeRefModel.PKCODE;
    }

    @Override
    public String getRefTitle() {
        return CostTypeRefModel.GET_TITLE();
    }

    @Override
    public String getTableName() {
        return CostTypeRefModel.TABLENAME;
    }

    @Override
    public String getBlurValue() {
        // ��ģ��ƥ��
        return null;
    }

    @Override
    protected String getEnvWherePart() {
        String wherePart = null;
        try {
            wherePart =
                    VisibleUtil.getVisibleCondition(this.getPk_org(), this.getPk_group(), NODE_TYPE.ORG_NODE,
                            this.getBeanID());
        }
        catch (MetaDataException e) {
        }
        catch (BusinessException e) {
        }
        return wherePart;
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
            IBean bean = MDBaseQueryFacade.getInstance().getBeanByFullClassName(CostTypeVO.class.getName());
            this.beanID = bean.getID();
        }
        return this.beanID;
    }

    @Override
    public void filterValueChanged(ValueChangedEvent changedValue) {
        super.filterValueChanged(changedValue);
        String[] pk_orgs = (String[]) changedValue.getNewValue();
        if (pk_orgs != null && pk_orgs.length > 0) {
            this.setPk_org(pk_orgs[0]);
        }
    }

    @Override
    public void reset() {
        this.setFilterRefNodeName(new String[] {/* -=notranslate=- */
                "ҵ��Ԫ"
        });
    }

    /**
     * �ɱ�BOM�гɱ����Ͳ�����Ч����ʱ���ȡ
     *
     * @author leixjc
     */
    @SuppressWarnings("unchecked")
    @Override
    public Vector<Vector<String>> getQueryResultVO() {
        Vector<Vector<String>> costtype = super.getQueryResultVO();
        for (int i = 0; i < costtype.size(); i++) {

            Vector<String> ss = costtype.get(i);

            for (int j = 0; j < ss.size(); j++) {
                if (j == 2 || j == 3) {
                    ss.set(j, ss.get(j).substring(0, 11));
                }
                else {
                    ss.set(j, ss.get(j));

                }
            }
            costtype.set(i, ss);
        }
        return costtype;
    }
}
