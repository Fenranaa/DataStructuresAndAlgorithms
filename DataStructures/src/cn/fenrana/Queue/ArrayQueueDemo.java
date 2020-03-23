package cn.fenrana.Queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        //初始化一个队列
        var arrayQueue = new ArrayQueue(3);
        char key = ' '; // 接受用户的输入
        var scanner = new Scanner(System.in);
        var loop = true;

        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出队列");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列中取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是:%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("取出的数据是:%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
            }
        }


    }
}

//使用数组模拟队列
class ArrayQueue {
    private int maxSize; //表示数组的最大容量
    private int front; //队列的头
    private int rear; //队列的尾
    private int[] arr; //该数组用于存放数据, 模拟队列

    //创造队列的构造器
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        this.arr = new int[maxSize];
        this.front = -1; //指向队列的头部
        this.rear = -1; //指向队尾,即队列的最后一个数据

    }

    //判断队列数据是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是否已经满了
        if (isFull()) {
            System.out.println("队列满了");
            return;
        }
        rear++;
        arr[rear] = n;

    }

    // 获取队列的数据
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空, 不能取数据");
        }
        front++;
        return arr[front];
    }

    //显示队列的所有的数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列是空的, 不能打印");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    //显示队列头部的信息
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列是空的, 不能打印");
        }
        return arr[front + 1];
    }
}
