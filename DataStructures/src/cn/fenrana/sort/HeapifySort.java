package cn.fenrana.sort;

import java.time.LocalDateTime;
import java.util.Arrays;

public class HeapifySort {
    public static void main(String[] args) {
      /*  int[] tree = {2, 5, 3, 1, 10, 4};
        heapSort(tree, tree.length);
        System.out.println(Arrays.toString(tree));*/

        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = (int) (Math.random() * 300);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toLocalTime());
        heapSort(arr, arr.length);
        LocalDateTime localDateTime2 = LocalDateTime.now();
        System.out.println(localDateTime2.toLocalTime());
        System.out.println(Arrays.toString(arr));
    }

    /**
     * @param i 从哪个节点开始
     * @param n 一共有多少个节点
     */
    public static void heapify(int[] tree, int n, int i) {
        if (i >= n) {
            return;
        }
        int c1 = 2 * i + 1;
        int c2 = 2 * i + 2;
        int max = i;

        if (c1 < n && tree[c1] > tree[max]) {
            max = c1;
        }

        if (c2 < n && tree[c2] > tree[max]) {
            max = c2;
        }

        if (max != i) {
            swap(tree, max, i);
            heapify(tree, n, max);
        }
    }

    public static void swap(int[] tree, int i, int j) {
        int temp = tree[i];
        tree[i] = tree[j];
        tree[j] = temp;

    }

    public static void buildHeap(int[] tree, int n) {
        int laseNode = n - 1;
        int parent = (laseNode - 1) / 2;
        for (int i = parent; i >= 0; i--) {
            heapify(tree, n, i);
        }
    }

    public static void heapSort(int[] tree, int n) {
        buildHeap(tree, n);
        for (int i = n -1; i >= 0; i--) {
            swap(tree, i, 0);
            heapify(tree, i, 0);
        }
    }
}
