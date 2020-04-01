package cn.fenrana.stack;


import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 根据<<算法第四版>>实现的栈
 */
public class FixedCapacityStack<Item> implements Iterable<Item> {
    private Item[] a;
    private int N;

    public FixedCapacityStack(int cap) {
        a = (Item[]) new Object[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        //入栈时数组的长度不够时, 增加数组的长度
        if (a.length == N) {
            reSize(2 * a.length);
        }
        a[N++] = item;
    }

    public Item pop() {
        Item item = a[--N];
        //将弹出的数置为空, 防止对象的游离
        a[N] = null;
        //防止数组过长
        if (N > 0 && N == a.length / 4) {
            reSize(a.length / 2);
        }
        return item;
    }

    //修改数组的长度
    private void reSize(int max) {
        Item[] temp = (Item[]) new Objects[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    public class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
