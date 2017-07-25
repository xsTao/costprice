package nc.ui.mapub.driver.view.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.mapub.driver.entity.FormulaItemVO;

public class DriverFormulaToolUtil {
    private boolean itemLock = false;

    public Map<FormulaItemVO, FormulaItemVO> generateFormulaMap(String formula, String formulacode) {
        List<FormulaItemVO> formulaList = this.parseFormula(formula);
        List<FormulaItemVO> formulacodeList = this.parseFormula(formulacode);
        Map<FormulaItemVO, FormulaItemVO> formulaMap = new HashMap<FormulaItemVO, FormulaItemVO>();
        for (int i = 0; i < formulaList.size(); i++) {
            formulaMap.put(formulaList.get(i), formulacodeList.get(i));
        }
        return formulaMap;
    }

    public List<FormulaItemVO> parseFormula(String formula) {
        List<FormulaItemVO> formulaList = new ArrayList<FormulaItemVO>();
        FormulaItemVO formulaItemVO = new FormulaItemVO();
        FormulaItemVO formulaSingelVO = null;
        // ���ü�¼�����ʼֵ
        formulaItemVO.setStartIndex(0);
        StringBuffer itemStrBuffer = new StringBuffer();
        for (int i = 0; i < formula.length(); i++) {
            char item = formula.charAt(i);
            if (this.checkCharIsSingel(item)) {
                if (itemStrBuffer.length() == 0) {
                    // ��¼λ�ú���
                    formulaItemVO.setStartIndex(i + 1);
                }
                else {
                    // ��¼����λ��
                    formulaItemVO.setEndIndex(i - 1);
                    // ��¼����ֵ
                    formulaItemVO.setValue(itemStrBuffer.toString());
                    // ����б�
                    formulaList.add(formulaItemVO);
                    formulaItemVO = new FormulaItemVO();
                    formulaItemVO.setStartIndex(i + 1);
                    itemStrBuffer = new StringBuffer();
                }
                // ��¼����
                formulaSingelVO = new FormulaItemVO();
                formulaSingelVO.setStartIndex(i);
                formulaSingelVO.setEndIndex(i);
                formulaSingelVO.setValue(String.valueOf(item));
                formulaList.add(formulaSingelVO);
            }
            else {
                if (item == '}') {
                    formulaItemVO.setEndIndex(i);
                    itemStrBuffer.append(item);
                    formulaItemVO.setValue(itemStrBuffer.toString());
                    formulaList.add(formulaItemVO);
                    formulaItemVO = new FormulaItemVO();
                    formulaItemVO.setStartIndex(i + 1);
                    itemStrBuffer = new StringBuffer();
                }
                else if (item == '{' && itemStrBuffer.length() != 0) {
                    formulaItemVO.setEndIndex(i - 1);
                    formulaItemVO.setValue(itemStrBuffer.toString());
                    formulaList.add(formulaItemVO);
                    formulaItemVO = new FormulaItemVO();
                    formulaItemVO.setStartIndex(i);
                    itemStrBuffer = new StringBuffer();
                }
                else {
                    itemStrBuffer.append(item);
                }
            }
        }
        if (itemStrBuffer.length() > 0) {
            formulaItemVO.setEndIndex(-1);
            formulaItemVO.setValue(itemStrBuffer.toString());
            formulaList.add(formulaItemVO);
        }
        return formulaList;
    }

    private boolean checkCharIsSingel(char aChar) {
        if (aChar == '{') {
            this.itemLock = true;
            return false;
        }
        if (aChar == '}') {
            this.itemLock = false;
            return false;
        }
        if (!this.itemLock
                && (aChar == '+' || aChar == '-' || aChar == '*' || aChar == '/' || aChar == '(' || aChar == ')')) {
            return true;
        }
        return false;
    }
}
