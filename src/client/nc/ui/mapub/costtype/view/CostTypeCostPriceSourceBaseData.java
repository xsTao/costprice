package nc.ui.mapub.costtype.view;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bd.framework.base.CMValueCheck;
import nc.bs.framework.common.NCLocator;
import nc.cmpub.business.enumeration.CMPubPriceSourceEnum;
import nc.ui.cmpub.framework.view.RefMutilChooseAbstractBaseData;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.beans.constenum.IConstEnum;
import nc.vo.mapub.costtype.entity.CostTypeVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;

@SuppressWarnings("unchecked")
public class CostTypeCostPriceSourceBaseData extends RefMutilChooseAbstractBaseData {

    @Override
    public List<IConstEnum> getInitValues(String pkorg) {
        List<IConstEnum> dataList = new ArrayList<IConstEnum>();
        dataList.add(new DefaultConstEnum(CMPubPriceSourceEnum.MANUAL.getEnumValue().getValue(),
                CMPubPriceSourceEnum.MANUAL.getName()));
        dataList.add(new DefaultConstEnum(CMPubPriceSourceEnum.CIRRO.getEnumValue().getValue(),
                CMPubPriceSourceEnum.CIRRO.getName()));
        // UFDate businessDate = WorkbenchEnvironment.getInstance().getBusiDate();
        // 利用反射获取物料价格库的查询服务
        try {
            Class<?> clazz =
                    Class.forName("nc.pubitf.mapub.materialpricebase.pub.IMaterialPriceBasePubQueryServiceForPrice");
            Object service1 = NCLocator.getInstance().lookup(clazz);
            Method m = clazz.getMethod("queryMaterialPriceBaseHeadVO", String[].class, String.class, UFDate.class);
            Object result = m.invoke(service1, new String[] {
                    pkorg
            }, AppContext.getInstance().getPkGroup(), AppContext.getInstance().getBusiDate());
            List<IConstEnum> list = new ArrayList<IConstEnum>();
            if (result.getClass().isInstance(list)) {
                if (CMValueCheck.isNotEmpty(result)) {
                    list = (ArrayList<IConstEnum>) result;
                }
                for (IConstEnum enumData : list) {
                    dataList.add(enumData);
                }
            }
        }

        catch (Exception e) {
        }
        return dataList;
    }

    public List<IConstEnum> getRefreshValues(String pkorg) {
        List<IConstEnum> dataList = new ArrayList<IConstEnum>();
        dataList.add(new DefaultConstEnum(CMPubPriceSourceEnum.MANUAL.getEnumValue().getValue(),
                CMPubPriceSourceEnum.MANUAL.getName()));
        dataList.add(new DefaultConstEnum(CMPubPriceSourceEnum.CIRRO.getEnumValue().getValue(),
                CMPubPriceSourceEnum.CIRRO.getName()));
        // UFDate businessDate = WorkbenchEnvironment.getInstance().getBusiDate();
        // 利用反射获取物料价格库的查询服务
        try {
            Class<?> clazz =
                    Class.forName("nc.pubitf.mapub.materialpricebase.pub.IMaterialPriceBasePubQueryServiceForPrice");
            Object service1 = NCLocator.getInstance().lookup(clazz);
            Method m = clazz.getMethod("queryMaterialPriceBaseHeadVO", String[].class, String.class, UFDate.class);
            Object result = m.invoke(service1, new String[] {
                    pkorg
            }, AppContext.getInstance().getPkGroup(), null);
            List<IConstEnum> list = new ArrayList<IConstEnum>();
            if (result.getClass().isInstance(list)) {
                if (CMValueCheck.isNotEmpty(result)) {
                    list = (ArrayList<IConstEnum>) result;
                }
                for (IConstEnum enumData : list) {
                    dataList.add(enumData);
                }
            }
        }

        catch (Exception e) {
        }
        return dataList;
    }

    @Override
    public String getRefPaneTitile() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0308")/* 费用价格来源 */;
    }

    @Override
    public String getDialogTitile() {
        return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3810006_0", "03810006-0308")/* 费用价格来源 */;
    }

    @Override
    public Map<String, String> getRefColumn() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(CostTypeVO.VCOSTPRICESOURCE, CostTypeVO.VCOSTPRICESOURCENUM);
        return map;
    }

    @Override
    public int getRefColumnPos() {
        return 1;
    }

    public List<IConstEnum> getInitValues4Group() {
        List<IConstEnum> dataList = new ArrayList<IConstEnum>();
        dataList.add(new DefaultConstEnum(CMPubPriceSourceEnum.MANUAL.getEnumValue().getValue(),
                CMPubPriceSourceEnum.MANUAL.getName()));
        dataList.add(new DefaultConstEnum(CMPubPriceSourceEnum.CIRRO.getEnumValue().getValue(),
                CMPubPriceSourceEnum.CIRRO.getName()));
        return dataList;
    }

}
