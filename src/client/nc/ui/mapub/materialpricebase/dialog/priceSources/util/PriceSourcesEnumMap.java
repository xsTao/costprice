package nc.ui.mapub.materialpricebase.dialog.priceSources.util;

import java.util.HashMap;
import java.util.Map;

import nc.cmpub.business.enumeration.CMPubPriceSourceEnum;

public class PriceSourcesEnumMap {

    // 采用公共枚举-zhangshyb
    public static Map<Integer, String> getPriceSourcesEnum() {
        Map<Integer, String> priceSourcesEnumMap = new HashMap<Integer, String>();
        priceSourcesEnumMap.put(Integer.valueOf(CMPubPriceSourceEnum.MANUAL.toIntValue()),
                CMPubPriceSourceEnum.MANUAL.getName());
        priceSourcesEnumMap.put(Integer.valueOf(CMPubPriceSourceEnum.PLAN.toIntValue()),
                CMPubPriceSourceEnum.PLAN.getName());
        priceSourcesEnumMap.put(Integer.valueOf(CMPubPriceSourceEnum.REFERENCE.toIntValue()),
                CMPubPriceSourceEnum.REFERENCE.getName());
        priceSourcesEnumMap.put(Integer.valueOf(CMPubPriceSourceEnum.PINGJUNCAIGOURUKU.toIntValue()),
                CMPubPriceSourceEnum.PINGJUNCAIGOURUKU.getName());
        priceSourcesEnumMap.put(Integer.valueOf(CMPubPriceSourceEnum.FORWARD.toIntValue()),
                CMPubPriceSourceEnum.FORWARD.getName());
        priceSourcesEnumMap.put(Integer.valueOf(CMPubPriceSourceEnum.STDCOST.toIntValue()),
                CMPubPriceSourceEnum.STDCOST.getName());
        return priceSourcesEnumMap;
    }

    // 采用公共枚举-zhangshyb
    public static Map<String, String> getPullPriceDisplayEnum() {
        Map<String, String> priceSourcesEnumMap = new HashMap<String, String>();
        priceSourcesEnumMap.put(String.valueOf(CMPubPriceSourceEnum.PINGJUNCAIGOURUKU.toIntValue()),
                "pingjuncaigouruku");
        priceSourcesEnumMap.put(String.valueOf(CMPubPriceSourceEnum.FORWARD.toIntValue()), "zuixinjiecunjia");
        priceSourcesEnumMap.put(String.valueOf(CMPubPriceSourceEnum.STDCOST.toIntValue()), "biaozhunchengben");
        return priceSourcesEnumMap;
    }
}
