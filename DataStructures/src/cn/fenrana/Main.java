package cn.fenrana;

import cn.fenrana.stack.FixedCapacityStack;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        FixedCapacityStack<String> a = new FixedCapacityStack<>(10);

        a.push("aaa");
        a.push("bbb");
        a.push("ccc");
        a.push("ddd");

        for (String s: a) {
            System.out.println(s);
        }
        var aa = new Hashtable<String, String>();
        var b = new HashMap<String, String>();

        List<String> list = new ArrayList<>();
        list.add("aa");

        Map<String, String> treeMap = new TreeMap<>();

        Stack<String> aaaa  = new Stack<>();

    }
}
