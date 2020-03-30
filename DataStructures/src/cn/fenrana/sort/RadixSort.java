package cn.fenrana.sort;

import java.time.LocalDateTime;
import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
//        int[] arr = {23, 54, 21, 3, 45, 64, 12, 33,66, 55};
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));
//        radixSort(arr);
//        System.out.println("排序后的");
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toLocalTime());
       radixSort(arr);
        LocalDateTime localDateTime2 = LocalDateTime.now();
        System.out.println(localDateTime2.toLocalTime());
    }

    public static void radixSort(int[] arr) {
        //得到数组中的最大数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        //得到最大的是几位数
        int length = (max + "").length();

        //定义一个二维数组, 表示十个桶
        //说明
        //1. 二维数组包含10个一维数组
        //2. 为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为arr.length
        //3. 名明确，基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少个数据,我们定义一个一维数组来记录各个桶的每次放入的数据个数
        //可以这里理解
        //比如：bucketElementCounts[0] , 记录的就是  bucket[0] 桶的放入数据个数
        int[] bucketElementCounts = new int[10];


        for (int i = 0, n = 1; i < length; i++, n *= 10) {
            // 往桶中放数据
            for (int value : arr) {
                int digitOfElement = value / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = value;
                bucketElementCounts[digitOfElement]++;
            }
            // 从桶中取出数据
            int index = 0;
            for (int a = 0; a < bucketElementCounts.length; a++) {
                if (bucketElementCounts[a] != 0) {
                    for (int l = 0; l < bucketElementCounts[a]; l++) {
                        arr[index] = bucket[a][l];
                        index++;
                    }
                }
                bucketElementCounts[a] = 0;
            }
        }
    }
}
