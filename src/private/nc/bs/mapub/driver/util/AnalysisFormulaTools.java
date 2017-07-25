package nc.bs.mapub.driver.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.lang.UFDouble;

/**
 * 公式分析工具类
 * 
 * @since 6.0
 * @version 2012-3-20 上午10:38:24
 * @author liyjf
 */
public class AnalysisFormulaTools {

    /**
     * 解析公式，获取变量Map
     * 用于计算变量准备数据(取唯一值)
     * 
     * @param formula
     * @return
     */
    public Map<String, UFDouble> analysis(String formula) {
        Map<String, UFDouble> itemMap = new HashMap<String, UFDouble>();
        boolean lock = false;
        StringBuffer itemstr = new StringBuffer();
        for (int i = 0; i < formula.length(); i++) {
            char aChar = formula.charAt(i);
            if (aChar == '{') {
                itemstr = new StringBuffer();
                lock = true;
                continue;
            }
            if (aChar == '}') {
                lock = false;
                itemMap.put(itemstr.toString(), null);
                continue;
            }
            if (lock) {
                itemstr.append(aChar);
            }
        }
        return itemMap;
    }

    /**
     * 解析公式，获取变量数组
     * 用于变量校验和数据获取(取唯一值)
     * 
     * @param formula
     * @return
     */
    public Set<String> analyseFormula(String formula) {
        Set<String> itemSet = new HashSet<String>();
        boolean lock = false;
        StringBuffer itemstr = new StringBuffer();
        for (int i = 0; i < formula.length(); i++) {
            char aChar = formula.charAt(i);
            if (aChar == '{') {
                itemstr = new StringBuffer();
                lock = true;
                continue;
            }
            if (aChar == '}') {
                lock = false;
                itemSet.add(itemstr.toString());
                continue;
            }
            if (lock) {
                itemstr.append(aChar);
            }
        }
        return itemSet;
    }

    /**
     * 解析公式中的编码和参数
     * 
     * @param formula
     *            公式
     * @return 编码
     */
    public Map<String, Object> analyseCode(String formula) {
        Map<String, Object> formulaMap = new HashMap<String, Object>();
        formulaMap.put("paramCode", formula);
        String str = formula;
        int startPos = str.indexOf("[");
        if (startPos < 0) {
            return formulaMap;
        }
        formulaMap.put("paramCode", str.substring(0, startPos));
        int endPos = str.indexOf("]", startPos + 1);
        String[] retStrings1 = str.substring(startPos + 1, endPos).split(",");
        formulaMap.put("paramValue1", retStrings1);

        // 支持标准作业的双参数
        str = str.substring(endPos + 1);
        startPos = str.indexOf("[");
        if (startPos < 0) {
            return formulaMap;
        }
        endPos = str.indexOf("]", startPos + 1);
        String[] retStrings2 = str.substring(startPos + 1, endPos).split(",");
        formulaMap.put("paramValue2", retStrings2);

        return formulaMap;
    }
}
