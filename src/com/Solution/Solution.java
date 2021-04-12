package com.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Solution{


        public int[][] reconstructQueue(int[][] people) {
            quickSort(people, 0, people.length - 1);
            List<int[]> list = new ArrayList<>();
            for (int[] p: people) list.add(p[1], p);
            return list.toArray(new int[0][2]);
        }

        private void quickSort(int[][] arr, int left, int right) {
            if (left >= right) return;
            int pivot = partition1(arr, left, right);
            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }

        private int partition1(int[][] arr,int l,int r){
            int[] pivolt=arr[l];
            while (l<r){
                while (arr[l][0]>pivolt[0]&&arr[l][1]<pivolt[1]){l++;}
                while (arr[r][0]<pivolt[0]&&arr[r][1]>pivolt[1]){r++;}
                if (l<r){
                    int[] a=new int[2];
                    a=arr[l];
                    arr[l]=arr[r];
                    arr[r]=a;
                }
            }
            arr[l]=pivolt;
            return l;
        }
        private int partition(int[][] arr, int l, int r) {
            int[] pivot = arr[l];
            while (l < r) {
                while (l < r) {
                    if (arr[r][0] > pivot[0] || arr[r][0] == pivot[0] && arr[r][1] < pivot[1]) {
                        arr[l++] = arr[r];
                        break;
                    }
                    r--;
                }
                while (l < r) {
                    if (arr[l][0] < pivot[0] || arr[l][0] == pivot[0] && arr[l][1] > pivot[1]) {
                        arr[r--] = arr[l];
                        break;
                    }
                    l++;
                }
            }
            arr[l] = pivot;
            return l;
        }
    }




