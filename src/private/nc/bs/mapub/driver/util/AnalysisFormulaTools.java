package nc.bs.mapub.driver.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.lang.UFDouble;

/**
 * ��ʽ����������
 * 
 * @since 6.0
 * @version 2012-3-20 ����10:38:24
 * @author liyjf
 */
public class AnalysisFormulaTools {

    /**
     * ������ʽ����ȡ����Map
     * ���ڼ������׼������(ȡΨһֵ)
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
     * ������ʽ����ȡ��������
     * ���ڱ���У������ݻ�ȡ(ȡΨһֵ)
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
     * ������ʽ�еı���Ͳ���
     * 
     * @param formula
     *            ��ʽ
     * @return ����
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

        // ֧�ֱ�׼��ҵ��˫����
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
