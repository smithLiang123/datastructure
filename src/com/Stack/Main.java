package com.Stack;

public class Main {

    public static void main(String[] args){
        ArrayStack arryStack=new ArrayStack<Integer>(10);
        for (int i=0;i<10;i++){
            arryStack.push(i);
        }
        System.out.println(arryStack);
        arryStack.pop();
        System.out.println(arryStack);

    }
}
