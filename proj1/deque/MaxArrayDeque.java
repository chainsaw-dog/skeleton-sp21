package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private T[] content;
    private int size;
    private Comparator<T> inner_c;
    public MaxArrayDeque (Comparator<T> c) {
        content = (T[]) new Object[8];
        size = 0;
        inner_c = c;
    }

    public  T max(){
        if(isEmpty()){
            return null;
        }
        return max(inner_c);
    }


    public  T max(Comparator<T> c){
        if(isEmpty()){
            return null;
        }
        T max = this.get(0);
        for (T temp : this){
            if (c.compare(temp,max) > 0) {
                max = temp;
            }
        }
        return max;
    }
}
