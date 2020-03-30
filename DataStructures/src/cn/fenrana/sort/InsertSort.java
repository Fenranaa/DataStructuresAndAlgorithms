package cn.fenrana.sort;

import java.time.LocalDateTime;
import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toLocalTime());
        insertSort(arr);
        LocalDateTime localDateTime2 = LocalDateTime.now();
        System.out.println(localDateTime2.toLocalTime());

    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i ++) {
            int insertVal = arr[i];
            int insertIndex = i - 1;

            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            arr[insertIndex+1] = insertVal;
        }
    }
}
