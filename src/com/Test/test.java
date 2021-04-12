package com.Test;

import java.util.HashMap;
import java.util.LinkedList;

public class test {
    public static void main(String[] args){
        LinkedList<Integer> list=new LinkedList();
        list.add(5);
        list.add(7);
        HashMap<String,Integer> map=new HashMap();
        map.put("hello",1);
        map.put("fuck",2);
        System.out.println(list);
        System.out.println(map);
    }

}
