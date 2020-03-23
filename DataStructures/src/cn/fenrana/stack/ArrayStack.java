package cn.fenrana.stack;

public class ArrayStack {


    private int maxSize;

    private int[] stack;

    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //打印
    public void list() {
        if(isEmpty()) {
            System.out.println("栈空, 没有数据");
            return;
        }
        for (int i = top; top >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}
