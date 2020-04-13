package cn.fenrana.search;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i ++) {
            arr[i] = i + 1;
        }
        int i = insertValueSearch(arr, 0, arr.length - 1, 1);
        System.out.println(i);
    }

    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("我只系");
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);

        if (arr[mid] > findVal) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else if (arr[mid] < findVal) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else {
            return mid;
        }
    }
}
