package cn.fenrana.sort;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, 20};

        int[] arr = new int[80000];
        for (int i =0; i < 80000; i ++){
            arr[i] = (int) (Math.random() * 80000);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toLocalTime());
        bubbleSort(arr);
        LocalDateTime localDateTime2 = LocalDateTime.now();
        System.out.println(localDateTime2.toLocalTime());
    }

    public static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }

            if (!flag) {
                break;
            } else {
                flag = false;
            }
//            System.out.println("第次" + (i + 1) + "次排序");
//            System.out.println(Arrays.toString(arr));
        }
    }
}
