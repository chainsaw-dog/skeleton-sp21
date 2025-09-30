package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] content;
    private int size;
    private int front;
    private int rear;

    public ArrayDeque() {
        content = (T[]) new Object[8];
        size = 0;
        front = 0;
        rear = 0;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int oldCapacity = content.length;
        int index = 0;
        for (int i = front; i < oldCapacity; i++) {
            a[index++] = content[i];
        }
        if (front > rear) {
            for (int i = 0; i < rear; i++) {
                a[index++] = content[i];
            }
        }
        content = a;
        front = 0;
        rear = size;
    }

    public void addFirst(T item) {
        if (size == content.length) {
            resize(size * 2);
        }
        front = (front - 1 + content.length) % content.length;
        content[front] = item;
        size++;
    }

    public void addLast(T item) {
        if (size == content.length) {
            resize(size * 2);
        }
        content[rear] = item;
        rear = (rear + 1) % content.length;
        size++;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            int pos = (front + i) % content.length;
            if (i != 0) {
                System.out.print(" ");
            }
            System.out.print(content[pos]);
        }
        System.out.println();
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int pos = (front + index) % content.length;
        return content[pos];
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T removed = content[front];
        front = (front + 1) % content.length;
        size--;
        if (size > 0 && size == content.length / 4) {
            resize(content.length / 2);
        }
        return removed;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        rear = (rear - 1 + content.length) % content.length;
        T removed = content[rear];
        size--;
        if (size > 0 && size == content.length / 4) {
            resize(content.length / 2);
        }
        return removed;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        ArrayDeque<?> other = (ArrayDeque<?>) o;
        if (other.size() != this.size) {
            return false;
        }
        Iterator<T> thisIter = this.iterator();
        Iterator<?> otherIter = other.iterator();
        while (thisIter.hasNext()) {
            T thisElem = thisIter.next();
            Object otherElem = otherIter.next();
            if (!elementsEqual(thisElem, otherElem)) {
                return false;
            }
        }
        return true;
    }

    private boolean elementsEqual(Object a, Object b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.equals(b);
    }

    private class ADIterator implements Iterator<T> {
        private int wizpos;

        public ADIterator() {
            wizpos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizpos < size;
        }

        @Override
        public T next() {
            T out = get(wizpos);
            wizpos++;
            return out;
        }
    }

    public Iterator<T> iterator() {
        return new ADIterator();
    }
}
