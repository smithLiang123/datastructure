package com.Stack;

//Last in first out
//LIFO
//用动态数组实现栈
public interface Stack <E>{
    int grtSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
