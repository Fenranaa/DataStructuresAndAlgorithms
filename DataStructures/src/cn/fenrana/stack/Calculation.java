package cn.fenrana.stack;

import java.util.Stack;

public class Calculation {
    public static void main(String[] args) {

        String expression = "55+2*5-3";
        int index = 0; //索引
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> operStack = new Stack<>();

        int num1 = 0;
        int num2 = 0;
        int res = 0;
        char oper = ' ';
        char ch = ' '; //每次从表达式里遍历的结果
        StringBuilder keepNum = new StringBuilder(); //用于拼接两位数
        int temp = 0;
        //开始遍历表达式
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);

            //判断是不是运算符
            if (isOper(ch)) {
                //是运算符, 判断符号栈是否为空
                if (!operStack.isEmpty()) {
                    //不为空,判断符号的优先级
                    if (priority(ch) <= priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();

                        res = call(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                } else {
                    //为空
                    operStack.push(ch);
                }

            } else {
                //不是运算符加入到数字栈中
                keepNum.append(ch);
                if (!(index == expression.length() - 1)) {
                    for (int i = index; i <= expression.length(); i++) {
                        var a = expression.substring(i + 1, i + 2).charAt(0);
                        if (!isOper(a)) {

                            keepNum.append(a);
                            temp++;
                        } else {
                            break;
                        }
                    }

                }
                numStack.push(Integer.parseInt(keepNum.toString()));
                keepNum = new StringBuilder();

            }
            if (temp > 0) {
                index++;
                for (int i = 0; i < temp; i++) {
                    index++;
                }
                temp = 0;
            } else {
                index++;
            }

            if (index >= expression.length()) {
                break;
            }


        }

        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = call(num1, num2, oper);
            numStack.push(res);
        }

        int res2 = numStack.peek();

        System.out.printf("表达式%s = %d\n", expression, res2);
        numStack.forEach(System.out::println);

    }

    //返回运算符的优先级
    public static int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    // 判断是不是操作符
    public static boolean isOper(char oper) {
        return oper == '+' || oper == '-' || oper == '*' || oper == '/';
    }

    //根据操作符计算结果
    public static int call(int num1, int num2, char oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }

        return res;
    }
}
