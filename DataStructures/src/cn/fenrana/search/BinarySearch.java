package cn.fenrana.search;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {12, 22, 33, 44, 55, 66, 77, 88};
        int i = binarySearch2(arr, 0, arr.length - 1, 44);
        System.out.println(i);
    }

    //递归实现
    public static int binarySearch(int[] arr, int left, int right, int target) {
        if (left >= right) {
            return -1;
        }

        int mid = (left + right) / 2;

        if (arr[mid] > target) {
            //向左
            return binarySearch(arr, left, mid - 1, target);
        } else if (arr[mid] < target) {
            //向右
            return binarySearch(arr, mid + 1, right, target);
        } else if (arr[mid] == target) {
            return mid;
        }

        return -1;

    }

    //while循环实现
    public static int binarySearch2(int[] arr, int left, int right, int target) {

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] == target) {
                return mid;
            }
        }
        return -1;
    }
}
