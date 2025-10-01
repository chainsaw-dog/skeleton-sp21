package deque;


import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>,Iterable<T> {
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
        if (front <= rear) {
            System.arraycopy(content, front, a, 0, size);
        } else {
            int firstPartLength = content.length - front;
            System.arraycopy(content, front, a, 0, firstPartLength);
            System.arraycopy(content, 0, a, firstPartLength, rear);
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
        size = size + 1;
    }

    public void addLast(T item) {
        if (size == content.length) {
            resize(size * 2);
        }
        content[rear] = item;
        rear = (rear + 1) % content.length;
        size = size + 1;
    }


    public int size() {
        return size;
    }


    public void printDeque() {
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                System.out.print(" ");
            }
            int pos = (front + i) % content.length;
            System.out.print(content[pos]);
        }
        System.out.println();
    }


    public T get(int index) {
        if (isEmpty() || index > size) {
            return null;
        }
        return content[(index + front) % content.length];
    }


    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        double factor = (double) content.length / 4;
        if (size - 1 < factor) {
            resize(size);
        }
        T removed = content[front];
        front = (front + 1 + content.length) % content.length;
        size = size - 1;
        return removed;
    }


    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        double factor = (double) content.length / 4;
        if (size - 1 < factor) {
            resize(size);
        }
        rear = (rear - 1 + content.length) % content.length;
        T removed = content[rear];
        size = size - 1;
        return removed;
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

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<?> other = (Deque<?>) o;
        if (size != other.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!elementsEqual(this.get(i), other.get(i))) {
                return false;
            }
        }
        return true;
    }


    private class ADIterator<T> implements Iterator<T> {
        private int wizpos;
        private int count = 0;

        public ADIterator() {
            wizpos = front;
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            T out = (T) content[wizpos];
            wizpos = (wizpos + 1) % content.length;
            count++;
            return out;
        }
    }


    public Iterator<T> iterator(){
        return new ADIterator<T>();
    }
}
