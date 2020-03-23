package cn.fenrana.Queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        //初始化一个队列
        var arrayQueue = new CircleArrayQueue(3); //2个数据
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

class CircleArrayQueue {
    private int maxSize; //表示数组的最大容量
    private int front; //front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
    private int rear; //rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
    private int[] arr; //该数组用于存放数据, 模拟队列

    public CircleArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        this.arr = new int[maxSize];
    }

    //判断队列数据是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
        arr[rear] = n;
        //将rear后移, 这里考虑取模 防止数组溢出
        rear = (rear + 1) % maxSize;

    }

    // 获取队列的数据
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空, 不能取数据");
        }

        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //返回队列的有效个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的所有的数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列是空的, 不能打印");
            return;
        }

        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //显示队列头部的信息
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列是空的, 不能打印");
        }
        return arr[front];
    }
}
