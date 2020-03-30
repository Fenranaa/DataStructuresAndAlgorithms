package cn.fenrana.sort;

import java.time.LocalDateTime;
import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
  /*      int[] arr = {2, 8, 4, 5, 6, 4, 8, 3};
        System.out.println("排序前的");
        System.out.println(Arrays.toString(arr));
        quickSort1(arr, 0, arr.length - 1);
        System.out.println("排序后的");
        System.out.println(Arrays.toString(arr));*/

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toLocalTime());
        quickSort1(arr, 0, arr.length - 1);
        LocalDateTime localDateTime2 = LocalDateTime.now();
        System.out.println(localDateTime2.toLocalTime());

    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left; //左下标
        int r = right; // 右下标
        //中轴值
        int pivot = arr[(left + right) / 2];
        int tem = 0;
        // while 循环的目的是把比中轴值的数小的放在左边, 大的放在右边.
        while (l < r) {
            //在pivot的左边找, 找到大于等于pivot才退出
            while (arr[l] < pivot) {
                l++;
            }

            //在pivot的右边找, 找到小于等于pivot才退出
            while (arr[r] > pivot) {
                r--;
            }
            //结束while循环的条件,
            if (l >= r) {
                break;
            }
            tem = arr[l];
            arr[l] = arr[r];
            arr[r] = tem;

            //如果交换完后，发现这个arr[l] == pivot值 相等 r--， 前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后，发现这个arr[r] == pivot值 相等 l++， 后移
            if (arr[r] == pivot) {
                l += 1;
            }

        }

        if (l == r) {
            l++;
            r--;
        }

        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }

        if (right > l) {
            quickSort(arr, l, right);
        }
    }

    public static void quickSort1(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;

        int pivot = arr[right];
        while (l < r) {
            while (arr[l] <= pivot && l < r) {
                l++;
            }
            if (l < r) {
                arr[r] = arr[l];
            }
            while (arr[r] >= pivot && l < r) {
                r--;
            }

            if (l < r) {
                arr[l] = arr[r];
            }


            if (l >= r) {
                arr[r] = pivot;
            }
        }
        quickSort1(arr, left, r - 1);
        quickSort1(arr, l + 1, right);
    }
}
