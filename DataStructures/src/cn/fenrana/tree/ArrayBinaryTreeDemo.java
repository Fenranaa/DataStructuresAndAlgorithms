package cn.fenrana.tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();
    }


}

class ArrayBinaryTree {
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        this.preOrder(0);
    }

    public void preOrder(int index) {
        if (arr == null || arr.length < 0) {
            System.out.println("数组为空");
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左遍历
        if ((index * 2) + 1 < arr.length) {
            preOrder((index * 2) + 1);
        }
        //向右遍历
        if ((index * 2) + 2 < arr.length) {
            preOrder((index * 2) + 2);
        }
    }
}
