package nc.bs.mapub.driver.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import nc.bs.mapub.driver.util.Calculate;
import nc.bs.mapub.driver.util.Stacks;
import nc.vo.pub.lang.UFDouble;

/**
 * 四则运算工具类
 * 参照逆波兰算法实现
 * 
 * @since 6.0
 * @version 2012-5-7 下午01:56:12
 * @author liyjf
 */
public class ExpressionTools {
    Calculate calculate;

    private ArrayList<String> expression = new ArrayList<String>();// 存储中序表达式

    private ArrayList<String> right = new ArrayList<String>();// 存储右序表达式

    private Stacks transStacks = new Stacks();// 转序堆栈

    private Stacks calculateStacks = new Stacks();// 计算堆栈

    // 依据输入信息创建对象，将数值与操作符放入ArrayList中
    public ExpressionTools(String input) {
        StringTokenizer st = new StringTokenizer(input, "+-*/()", true);
        this.expression.clear();
        while (st.hasMoreElements()) {
            this.expression.add(st.nextToken());
        }
        this.calculate = new Calculate();
    }

    public String getVariable(String variable, Map<String, UFDouble> variableMap) {
        UFDouble value = variableMap.get(variable);
        if (value != null) {
            return value.toString();
        }
        return variable;
    }

    // 对右序表达式进行求值
    public String getResult(Map<String, UFDouble> variableMap) {
        this.toRight();
        this.calculateStacks.clear();
        String item = null;
        String op1, op2;
        Iterator<String> iterator = this.right.iterator();
        while (iterator.hasNext()) {
            item = iterator.next();
            if (this.calculate.isOperator(item)) {
                // 变量赋值、转换
                op1 = this.getVariable(this.calculateStacks.pop(), variableMap);
                op2 = this.getVariable(this.calculateStacks.pop(), variableMap);
                this.calculateStacks.push(this.calculate.twoResult(item, op1, op2));
            }
            else {
                this.calculateStacks.push(this.getVariable(item, variableMap));
            }
        }
        return this.calculateStacks.pop();
    }

    // 将中序表达式转换为右序表达式
    private void toRight() {
        this.right.clear();
        this.transStacks.clear();
        String operator;
        int position = 0;
        while (true) {
            if (this.calculate.isOperator(this.expression.get(position))) {
                if (this.transStacks.top == -1 || this.expression.get(position).equals("(")) {
                    this.transStacks.push(this.expression.get(position));
                }
                else {
                    if (this.expression.get(position).equals(")")) {
                        while (!this.transStacks.top().equals("(")) {
                            operator = this.transStacks.pop();
                            this.right.add(operator);
                        }
                        this.transStacks.pop();
                    }
                    else {
                        if (this.calculate.priority(this.expression.get(position)) <= this.calculate
                                .priority(this.transStacks.top()) && this.transStacks.top != -1) {
                            if (!this.transStacks.top().equals("(")) {
                                operator = this.transStacks.pop();
                                this.right.add(operator);
                            }
                        }
                        this.transStacks.push(this.expression.get(position));
                    }
                }
            }
            else {
                this.right.add(this.expression.get(position));
            }
            position++;
            if (position >= this.expression.size()) {
                break;
            }
        }

        while (this.transStacks.top != -1) {
            operator = this.transStacks.pop();
            this.right.add(operator);
        }
    }
}
