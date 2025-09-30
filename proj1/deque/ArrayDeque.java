package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>,Iterable<T>{
    private T[] content;
    private int size;


    public ArrayDeque() {
        content = (T[]) new Object[8];
        size = 0;
    }
    private void  resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(content,0,a,0,size);
        content = a;
    }
    public void addFirst(T item) {
        if (size == content.length) {
            resize(size*2);
        }
        T[] temp = (T[]) new Object[content.length];
        temp[0] = item;
        System.arraycopy(content,0,temp,1,size);
        content = temp;
        size = size + 1;
    }
    public void addLast(T item) {
        if (size == content.length) {
            resize(size*2);
        }
        content[size] = item;
        size = size + 1;
    }


    public int size() {
        return size;
    }


    public void printDeque() {
        for (int i=0;i<size;i++){
            if(i!=0) {
                System.out.print(" ");
            }
            System.out.print(content[i]);
        }
        System.out.println();
    }


    public T get(int index) {
        if(isEmpty()) { return null;}
        return content[index];
    }


    public T removeFirst() {
        if(isEmpty()) { return null;}
        T removed = content[0];
        T[] temp = (T[]) new Object[size];
        System.arraycopy(content,1,temp,0,size-1);
        content = temp;
        size = size - 1;
        return removed;
    }


    public T removeLast() {
        if(isEmpty()) { return null;}
        T removed = content[size-1];
        T[] temp = (T[]) new Object[size];
        System.arraycopy(content,0,temp,0,size-1);
        content = temp;
        size =size - 1;
        return removed;
    }


    public boolean equals(Object o){
        if(!(o instanceof ArrayDeque)){
            return false;
        }else {
            if(((ArrayDeque<?>) o).size!=this.size){
                return false;
            }
            for(int i = 0;i < size;i++){
                if (((ArrayDeque<?>) o).content[i]!=this.content[i]){
                    return false;
                }
            }
        }
        return true;
    }


    private class ADIterator<T> implements Iterator<T> {
        private  int wizpos;

        public  ADIterator() {
            wizpos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizpos < size;
        }

        @Override
        public T next() {
            T out = (T) content[wizpos];
            wizpos = wizpos + 1;
            return out;
        }
    }
    public Iterator<T> iterator(){
        return new ADIterator<>();
    }
}
