package com.Queue;

public interface Queue <E>{
    int getSize();
    boolean isEmpty();
    void enqueue(E e);
    E deQueue();
    E getFront();

}
