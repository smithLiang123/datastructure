package com.Stack;
import com.Arry.ArrayNorm;
public class ArrayStack<E> implements Stack<E>{
    @Override
    public int grtSize() {
        return arr.getSize();
    }

    @Override
    public boolean isEmpty() {
        return arr.isEmpty();
    }

    @Override
    public void push(E e) {
        arr.addLast(e);
    }

    @Override
    public E pop() {
        return arr.removeLast();
    }

    @Override
    public E peek() {
        return arr.get(grtSize()-1);
    }

    @Override
    public String toString() {
        StringBuilder res=new StringBuilder();
        res.append("Stack:[");
        for (int i=0;i<arr.getSize();i++){
            res.append(arr.get(i));
            if (i!=arr.getSize()-1){
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }

    ArrayNorm<E> arr;
    public ArrayStack(int capacity){
        arr=new ArrayNorm<>(capacity);
    }
    public ArrayStack(){
        arr=new ArrayNorm<>();
    }

}
