package cn.fenrana.sparsearray;

public class sparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组 11*11
        //0 表示没有棋子 1 表示黑子 2 表示篮子
        int[][] chess = new int[11][11];
        chess[1][2] = 1;
        chess[2][3] = 2;
        chess[10][10] = 2;

        /*
         * 打印稀疏数组
         */
        for (int[] row : chess) {
            for (int a : row) {
                System.out.printf("%d\t", a);
            }
            System.out.println();
        }
        //二维数组转稀疏数组的思路: 先遍历二维数组,得到非零数组的个数
        var sum = 0;
        for (int[] row : chess) {
            for (int j = 0; j < 11; j++) {
                if (row[j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum=" + sum);
        //初始化稀疏数组
        int[][] sparseArray = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        // 二维数组赋值给稀疏数组
        var cont = 0;
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < 11; j++) {
                if (chess[i][j] != 0) {
                    cont++;
                    sparseArray[cont][0] = i;
                    sparseArray[cont][1] = j;
                    sparseArray[cont][2] = chess[i][j];

                }
            }
        }
        //遍历稀疏数组
        System.out.println("二维数组转稀疏数组的结果..");
        for (int[] ints : sparseArray) {
            System.out.printf("%d\t%d\t%d\n", ints[0], ints[1], ints[2]);
        }

        //将稀疏数组转成二维数组
        int[][] chess2 = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            chess2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        System.out.println("打印稀疏数组转二维数组..");
        for (int[] row : chess2) {
            for (int a : row) {
                System.out.printf("%d\t", a);
            }
            System.out.println();
        }
    }
}
