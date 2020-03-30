package cn.fenrana.sort;

import java.time.LocalDateTime;
import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        /*int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        System.out.println("排序前的");
        System.out.println(Arrays.toString(arr));
        shellSort2(arr);
        System.out.println("排序后的的");
        System.out.println(Arrays.toString(arr));*/
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toLocalTime());
        shellSort2(arr);
        LocalDateTime localDateTime2 = LocalDateTime.now();
        System.out.println(localDateTime2.toLocalTime());

    }

    //交换法
    public static void shellSort(int[] arr) {
        int temp = 0;

        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j = j - gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    //位移法
    public static void shellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //插入排序
            for (int i = gap; i < arr.length; i++) {
                int insertVal = arr[i];
                int insertIndex = i - gap;

                while (insertIndex >= 0 && arr[insertIndex] > insertVal) {
                    arr[insertIndex + gap] = arr[insertIndex];
                    insertIndex -= gap;
                }

                arr[insertIndex + gap] = insertVal;
            }
        }
    }
}
