package com.Queue;

import java.util.Random;

//测试使用queue运行opCount个enqueue和dequeue操作所需要的时间，单位：秒
public class Main {

    private static double testQueue(Queue<Integer> q,int opCount){

        long startTime=System.nanoTime();
        Random random=new Random();
        for (int i=0;i<opCount;i++){
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i=0;i<opCount;i++){
            q.deQueue();
        }

        long endTime=System.nanoTime();
        return (endTime-startTime)/1000000000.0;

    }

    public static void main(String[] args){
        int opCount=100000;

        ArrayQueue<Integer> arrayQueue=new ArrayQueue<>();
        double time1=testQueue(arrayQueue,opCount);
        System.out.println("ArrayQueue,time:"+time1+"s");

        LoopQueue<Integer> loopQueue=new LoopQueue<>();
        double time2=testQueue(loopQueue,opCount);
        System.out.println("loopQueue,time:"+time2+"s");
    }

}
