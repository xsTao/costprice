package nc.bs.mapub.driver.util;

import nc.vo.mapub.driver.entity.CMDriverLangConst;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class Calculate {
    // �ж��Ƿ�Ϊ��������
    public boolean isOperator(String operator) {
        if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/")
                || operator.equals("(") || operator.equals(")")) {
            return true;
        }
        return false;
    }

    // ���ò������ŵ����ȼ���
    public int priority(String operator) {
        if (operator.equals("+") || operator.equals("-") || operator.equals("(")) {
            return 1;
        }
        else if (operator.equals("*") || operator.equals("/")) {
            return 2;
        }
        return 0;
    }

    // ��2ֵ֮��ļ���
    public String twoResult(String operator, String a, String b) {
        String op = operator;
        String rs = new String();
        try {
            double x = Double.parseDouble(b);
            double y = Double.parseDouble(a);
            double z = 0;
            if (op.equals("+")) {
                z = x + y;
            }
            else if (op.equals("-")) {
                z = x - y;
            }
            else if (op.equals("*")) {
                z = x * y;
            }
            else if (op.equals("/")) {
                if (Double.valueOf(y).equals(Double.valueOf(0))) {
                    throw new BusinessException(CMDriverLangConst.getERR_EXIST_ZERO_DENOMINATOR());
                }
                z = x / y;
            }
            else {
                z = 0;
            }
            return rs + z;
        }
        catch (Exception e) {
            ExceptionUtils.wrappException(e);
            return null;
        }
    }
}
