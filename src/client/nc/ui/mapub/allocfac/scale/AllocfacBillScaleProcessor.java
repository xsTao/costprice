/**
 *
 */
package nc.ui.mapub.allocfac.scale;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.CurrtypeFromOrgScaleObject;
import nc.vo.pubapp.scale.PosEnum;

public class AllocfacBillScaleProcessor extends BillVOScaleProcessor {

    public AllocfacBillScaleProcessor(String pk_group, AggregatedValueObject[] bills) {
        super(pk_group, bills);
    }

    @Override
    public void setMnyFromOrgLocCurrCtlInfo(String[] mnykeys, PosEnum pos, String tabcode, String orgkey,
            PosEnum currpos, String currtabcode) {
        // ³öÅÌ±¨´í£¬×¢ÊÍµô
        this.setCtlInfo(mnykeys, pos, tabcode, orgkey, currpos, currtabcode, new CurrtypeFromOrgScaleObject(8, 4));
    }
}
