package cn.fenrana.sort;

import java.time.LocalDateTime;
import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {

        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));

/*
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        int[] temp = new int[arr.length];
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toLocalTime());
        mergeSort(arr, 0, arr.length - 1, temp);
        LocalDateTime localDateTime2 = LocalDateTime.now();
        System.out.println(localDateTime2.toLocalTime());*/
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左分治
            mergeSort(arr, left, mid, temp);
            //向右分治
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * @param left  数组开始的下标
     * @param right 数组结束的下标
     * @param mid   中间
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;  //左序列开始的下标
        int j = mid + 1; //右序列开始的下标
        int t = 0; // 临时数组的下标
        /**
         * 比较两个数组,
         * */
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                i += 1;
            } else {
                temp[t] = arr[j];
                j += 1;
            }
            t += 1;

        }
        //把两个数组中剩余的数放到临时数组中
        while (i <= mid) {
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while (j <= right) {
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        //把临时数组的值复制到数组
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            tempLeft++;
            t++;
        }

    }
}
