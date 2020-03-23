package cn.fenrana.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
//        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
//        List<String> listString = getListString(suffixExpression);
//        int calculate = calculate(listString);
//        System.out.printf("表达式:%s= %d ", suffixExpression, calculate);


        String a = "1+((2+3)×4)-5";
        List<String> list = toInfixExpressionList(a);
        System.out.println(list);
    }

    public static List<String> getListString(String suffixExpression) {
        String[] s = suffixExpression.split(" ");
        return Arrays.asList(s);
    }

    public static int calculate(List<String> suffixExpression) {
        Stack<String> statck = new Stack<>();
        var res = 0;

        for (String el : suffixExpression) {
            if (el.matches("\\d+")) {
                statck.push(el);
            } else {
                var num1 = Integer.parseInt(statck.pop());
                var num2 = Integer.parseInt(statck.pop());

                switch (el) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num2 - num1;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num2 / num1;
                        break;
                    default:
                        throw new RuntimeException("不支持的操作");
                }
                statck.push(res + "");
            }
        }
        return Integer.parseInt(statck.peek());
    }

    //即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  =》 ArrayList [1,2,3,+,4,*,+,5,–]
    //方法：将得到的中缀表达式对应的List => 后缀表达式对应的List
    public static List<String> parseSuffixExpreesionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>(); // 符号栈
        //说明：因为s2 这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们就不用 Stack<String> 直接使用 List<String> s2
        //Stack<String> s2 = new Stack<String>(); // 储存中间结果的栈s2
        List<String> s2 = new ArrayList<>(); // 储存中间结果的Lists2

        //遍历ls
        for (String item : ls) {
            //如果是一个数，加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if ("(".equals(item)) {
                s1.push(item);
            } else if (")".equals(item)) {
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.pop();//!!! 将 ( 弹出 s1栈， 消除小括号
            } else {
                //当item的优先级小于等于s1栈顶运算符, 将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较
                //问题：我们缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }
        }

        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2; //注意因为是存放到List, 因此按顺序输出就是对应的后缀表达式对应的List

    }

    //将中缀表达式转化为后缀表达式
    // 1+((2+3)×4)-5 => 1 2 3 + 4 × + 5 –
    public static List<String> toInfixExpressionList(String str) {
        List<String> list = new ArrayList<>();
        int index = 0;
        StringBuilder s;
        char c; //没遍历一个字符
        do {
            c = str.charAt(index);
            if (c < 48 || c > 57) {
                list.add(c + "");
                index++;
            } else {
                s = new StringBuilder();

                while (index < str.length() && str.charAt(index) >= 48 && str.charAt(index) <= 57) {
                    s.append(str.charAt(index));
                    index++;
                }
                list.add(s.toString());

            }
        } while (index < str.length());


        return list;
    }
}

//编写一个类 Operation 可以返回一个运算符 对应的优先级
class Operation {
    private final static int ADD = 1;
    private final static int SUB = 1;
    private final static int MUL = 2;
    private final static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符" + operation);
                break;
        }
        return result;
    }

}