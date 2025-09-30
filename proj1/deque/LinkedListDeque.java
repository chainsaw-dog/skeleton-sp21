package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> ,Iterable<T>{
    private class TNode {
        public T content;
        public TNode prev;
        public TNode next;
        public TNode(T i,TNode n,TNode p) {
            content = i;
            next = n;
            prev = p;
        }
    }

    private int size;
    private TNode sentinel;
    /**create an empty deque*/
    public LinkedListDeque() {
        sentinel = new TNode(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**insert last*/
    public void addLast(T x) {
        size = size + 1;
        TNode temp = new TNode(x,null,null);
        temp.prev = sentinel.prev;
        sentinel.prev.next = temp;
        sentinel.prev = temp;
        temp.next = sentinel;
    }

    /**insert first*/
    public void addFirst(T x) {
        size = size + 1;
        TNode temp = new TNode(x,null,null);
        sentinel.next.prev = temp;
        temp.next = sentinel.next;
        temp.prev = sentinel;
        sentinel.next = temp;
    }


    /**return size*/
    public int size() {
        return size;
    }

    /**print the whole deque*/
    public void printDeque() {
        //will be finished later
        TNode p = sentinel.next;
        while (p.content!=null){
            System.out.print(p.content);
            if(p.next != sentinel){
                System.out.print(" ");
            }
            p = p.next;
        }
        System.out.println();
    }

    /**delete first*/
    public T removeFirst() {
        if (size == 0) {return null;}
        size = size - 1;
        T removed = sentinel.next.content;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        return removed;
    }

    /**delete last*/
    public T removeLast() {
        if (size == 0) {return null;}
        size = size - 1;
        T removed = sentinel.prev.content;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return removed;
    }

    /**get index element*/
    public T get(int index) {
        if (size == 0) {return null;}
        TNode p = sentinel;
        for (int i = 0;i <= index;i++){
            p = p.next;
        }
        return p.content;
    }


    private TNode getR(TNode current,int index) {
        if(index == 0){
            return current.next;
        }
        return getR(current.next,index-1);
    }
    public T getRecursive(int index) {
        if (size == 0) {return null;}
        TNode target = getR(sentinel,index);
        return target.content;
    }


    public boolean equals(Object o){
        if(!(o instanceof LinkedListDeque)){
            return false;
        }else {
            if(((LinkedListDeque<?>) o).size!=this.size){
                return false;
            }
            TNode temp1 = this.sentinel.next;
            TNode temp2 = ((LinkedListDeque<T>) o).sentinel.next;
            while(temp1!=sentinel){
                if (temp1.content!=temp2.content){
                    return false;
                }
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
        }
        return true;
    }

    private class LLDIterator<T> implements Iterator<T> {
        private  int wizpos;

        public  LLDIterator() {
            wizpos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizpos < size;
        }

        @Override
        public T next() {
            T out = (T) get(wizpos);
            wizpos = wizpos + 1;
            return out;
        }
    }
    public Iterator<T> iterator(){
        return new LLDIterator<>();
    }
}

