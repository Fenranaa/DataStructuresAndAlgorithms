package cn.fenrana.search;

public class SeqSearch {
    public static void main(String[] args) {

    }

    public static int seqSearch(int[] arr, int target) {
        for (int i =0; i < arr.length; i ++ ) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
