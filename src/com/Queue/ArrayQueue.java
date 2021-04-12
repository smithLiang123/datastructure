package com.Queue;


import com.Arry.ArrayNorm;

public class ArrayQueue<E>implements Queue<E> {
    private ArrayNorm<E> arr;

    public static void main(String[] args){
        ArrayQueue<Integer> arrayQueue=new ArrayQueue<>();
        for (int i=0;i<10;i++){
            arrayQueue.enqueue(i);
        }
        System.out.println(arrayQueue);
        arrayQueue.enqueue(1);
        System.out.println(arrayQueue);
        arrayQueue.deQueue();
        System.out.println(arrayQueue);
    }

    public ArrayQueue(int capacity){
        arr=new ArrayNorm<>(capacity);
    }
    public ArrayQueue(){
        arr=new ArrayNorm<>();
    }

    @Override
    public int getSize() {
        return arr.getSize();
    }

    public int getCapacity(){
        return arr.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return arr.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        arr.addLast(e);
    }

    @Override
    public E deQueue() {
        return arr.removeFirst();
    }

    @Override
    public E getFront() {
        return arr.get(0);
    }

    @Override
    public String toString() {
       StringBuilder res=new StringBuilder();
       res.append("Queue: front[");
       for (int i=0;i<arr.getSize();i++){
           res.append(arr.get(i));
           if (i!=arr.getSize()-1){
               res.append(",");
           }
       }
       res.append("]tail");
       return res.toString();
    }

}
