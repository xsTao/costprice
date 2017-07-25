package nc.ui.mapub.costtype.view;

import nc.bd.accperiod.AccperiodAccessor;
import nc.pubitf.accperiod.AccountCalendar;
import nc.ui.bd.ref.model.AccperiodRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.itemeditors.UFRefBillItemEditor;
import nc.vo.bd.period.AccperiodVO;
import nc.vo.bd.period2.AccperiodmonthVO;

/**
 * <b> ����ڼ��billitemeditor </b>
 * <p>
 * ��ϸ��������
 * </p>
 * ��������:2010-4-13
 * 
 * @author:wangtf
 */
public class AccPeriodBillItemEditor extends UFRefBillItemEditor {
    /**
     * ���췽��
     * 
     * @param item
     *            billitem
     */
    public AccPeriodBillItemEditor(BillItem item) {
        super(item);

    }

    public AccPeriodBillItemEditor(BillItem item, String pk_group, String pk_org) {
        super(item);

        // this.pk_group = pk_group;
        // this.pk_org = pk_org;

    }

    // private String pk_group = "";

    // private String pk_org = "";

    @Override
    protected void initRefPane(UIRefPane ref) {
        AccperiodRefModel model = new AccperiodRefModel();
        ref.setRefModel(model);
        ref.setReturnCode(true); // ���ý����뿪ʱ������ʾ���ƣ���Ϊtrue���������ڼ�ֻ��ʾ�·�

        if (AccountCalendar.getInstance().getMonthVO() == null) {
            return;
        }

        String currentCheme = AccountCalendar.getInstance().getMonthVO().getPk_accperiodscheme();
        ((AccperiodRefModel) ref.getRefModel()).setDefaultpk_accperiodscheme(currentCheme);

    }

    /**
     * ���������ֶ�
     * 
     * @see nc.ui.pub.bill.itemeditors.UFRefBillItemEditor#getValue()
     * @return ֵ
     */
    @Override
    public Object getValue() {
        String strValue = null;

        if (this.getRefPane().getRefModel() != null) {
            strValue = this.getRefPane().getRefModel().getRefNameValue();
            // strValue =(String) getRefPane().getRefModel().getValue("bd_accperiodmonth.yearmth");
        }

        return strValue;
    }

    @Override
    public void setValue(Object newValue) {

        if (newValue == null) {
            return;
        }

        // ������еĻ���ڼ�
        String currentCheme = AccountCalendar.getInstance().getMonthVO().getPk_accperiodscheme();
        AccperiodVO[] accperiodVos = AccperiodAccessor.getInstance().queryAllAccperiodVOs(currentCheme);
        // �����·ݵ��ڴ���ֵ������
        for (int i = 0; i < accperiodVos.length; i++) {
            AccperiodmonthVO[] monthVos = accperiodVos[i].getAccperiodmonth();
            if (monthVos == null) {
                continue;
            }

            for (int j = 0; j < monthVos.length; j++) {
                String yearMonth = monthVos[j].getYearmth();
                if (yearMonth == null || yearMonth.trim().length() <= 0) {
                    continue;
                }

                if (newValue.toString().equals(yearMonth)) {
                    super.setValue(monthVos[j].getPk_accperiod());

                    return;
                }
            }
        }
        // ת��Ϊ����ֵ
        super.setValue(newValue);
    }

}
