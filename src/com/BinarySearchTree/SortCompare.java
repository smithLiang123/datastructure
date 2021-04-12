package com.BinarySearchTree;

import java.util.ArrayList;
import java.util.Random;

public class SortCompare {
    public static void quickSort(int[] arr,int low,int high){
        int i,j,temp,t;
        if (low>high){
            return;
        }
        i=low;
        j=high;
        temp=arr[low];
        while (i<j){
            //1.从右往左扫描，找到比基准值”小“的数
            while (temp<=arr[j]&&i<j){
                j--;
            }
            //2.从左往右扫描，找到比基准值”大“的数
            while (temp>=arr[i]&&i<j){
                i++;
            }
            //3.都找到了，两数交换
            if (i<j){
                t=arr[j];
                arr[j]=arr[i];
                arr[i]=t;
            }
        }
        arr[low]=arr[i];
        arr[i]=temp;
        quickSort(arr,low,j-1);
        quickSort(arr,j+1,high);
    }
    public static void main(String[] args){
        BinarySearchTree<Integer> bst=new BinarySearchTree<>();
        Random random=new Random();
        int n=100000;
        long startTime=System.nanoTime();
        for (int i=0;i<n;i++){
            bst.add(random.nextInt(1000));
        }
        ArrayList<Integer> nums=new ArrayList<>();
        while (!bst.isEmpty()){
            nums.add(bst.removeMin());
        }
        long endTime=System.nanoTime();
        System.out.println("Time for binary tree sort is :"+(endTime-startTime)/1000000000.0);
        int[] arr=new int[n];
        for (int i=0;i<n;i++){
            arr[i]=random.nextInt();
        }
        startTime=System.nanoTime();
        quickSort(arr,0,arr.length-1);
        endTime=System.nanoTime();
        System.out.println("Time for quick sort is :"+(endTime-startTime)/1000000000.0);

    }

}
