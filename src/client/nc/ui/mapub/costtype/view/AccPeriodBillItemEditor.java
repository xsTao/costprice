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
 * <b> 会计期间的billitemeditor </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-4-13
 * 
 * @author:wangtf
 */
public class AccPeriodBillItemEditor extends UFRefBillItemEditor {
    /**
     * 构造方法
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
        ref.setReturnCode(true); // 设置焦点离开时参照显示名称，设为true，否则会计期间只显示月份

        if (AccountCalendar.getInstance().getMonthVO() == null) {
            return;
        }

        String currentCheme = AccountCalendar.getInstance().getMonthVO().getPk_accperiodscheme();
        ((AccperiodRefModel) ref.getRefModel()).setDefaultpk_accperiodscheme(currentCheme);

    }

    /**
     * 返回名称字段
     * 
     * @see nc.ui.pub.bill.itemeditors.UFRefBillItemEditor#getValue()
     * @return 值
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

        // 获得所有的会计期间
        String currentCheme = AccountCalendar.getInstance().getMonthVO().getPk_accperiodscheme();
        AccperiodVO[] accperiodVos = AccperiodAccessor.getInstance().queryAllAccperiodVOs(currentCheme);
        // 查找月份等于传入值的数据
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
        // 转化为主键值
        super.setValue(newValue);
    }

}
