package com.LinkedList;

import com.Stack.Stack;

public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> list;
    public LinkedListStack(){
        list=new LinkedList<>();
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }


    @Override
    public int grtSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public String toString(){
        StringBuilder res=new StringBuilder();
        res.append("Stack :top ");
        res.append(list);
        return res.toString();
    }

    public static void main(String[] args){
        LinkedListStack<Integer> linkedList=new LinkedListStack<>();
        for (int i=0;i<5;i++){
            linkedList.push(i);
            System.out.println(linkedList);
        }



        linkedList.pop();
        System.out.println(linkedList);
    }
}
