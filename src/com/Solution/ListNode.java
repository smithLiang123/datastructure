package com.Solution;

public class ListNode {

    public int val;
    public ListNode next;
    public ListNode(int x){
        val=x;
    }

    public ListNode(int[] arr){
        if (arr==null||arr.length==0)
            throw new IllegalArgumentException("arr cannot be empty");

        this.val=arr[0];
        ListNode cur=this;
        for (int i=1;i<arr.length;i++){
            cur.next=new ListNode(arr[i]);
            cur=cur.next;
        }
    }
    @Override
    public String toString(){
        StringBuilder res=new StringBuilder();
        ListNode cur=this;
        res.append("head ");
        while (cur!=null){
            res.append(cur.val+"->");
            cur=cur.next;
        }
        res.append("Null");
        return res.toString();
    }
}
