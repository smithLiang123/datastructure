package com.Map;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        System.out.println("Pride and Prejudice");

        ArrayList<String> words=new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt",words)){
            System.out.println(words.size());
        }
        LinkedListMap<String,Integer>map=new LinkedListMap<>();
        BSTMap<String,Integer>map1=new BSTMap<>();
        for (String word:words) {
            if (map1.contains(word))
                map1.set(word, map1.get(word) + 1);
            else
                map1.add(word, 1);
        }
        System.out.println("Total different words"+map1.getSize());
        System.out.println("Frequency of PRIDE "+map1.get("pride"));
    }
}
