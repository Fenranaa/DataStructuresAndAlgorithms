package cn.fenrana.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node huffman = createHuffman(arr);
        preOrder(huffman);
    }

    public static Node createHuffman(int[] arr) {
        List<Node> nodes = new ArrayList<>();
        for (int i : arr) {
            nodes.add(new Node(i));
        }

        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node node1 = nodes.get(0);
            Node node2 = nodes.get(1);
            Node root = new Node(node1.value + node2.value);
            root.left = node1;
            root.right = node2;

            nodes.remove(node1);
            nodes.remove(node2);
            nodes.add(root);
        }
        return nodes.get(0);
    }

    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        }else {
            System.out.printf("sss");
        }
    }

}

class Node implements Comparable<Node> {

    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
