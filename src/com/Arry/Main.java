package com.Arry;

public class Main {



    public static void main(String[] args) {
	// write your code here

        ArrayNorm arrayNorm =new ArrayNorm<Integer>(10);
        System.out.println(arrayNorm);
        for (int i=0;i<11;i++){
            arrayNorm.addLast(i);
        }
        System.out.println(arrayNorm);
        arrayNorm.removeLast();
        System.out.println(arrayNorm);
    }
}
