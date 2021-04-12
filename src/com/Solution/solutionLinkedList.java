package com.Solution;

public class solutionLinkedList {
    public  ListNode removeElements(ListNode head,int val){
        while (head.val==val&&head!=null){
            ListNode delNode=head;
            head=head.next;
            delNode=null;
        }
        if (head==null)
            return head;

        ListNode prev=head;
        while (prev.next!=null){
            if (prev.next.val==val){
                ListNode delNode=prev.next;
                prev.next=delNode.next;
                delNode=null;
            }else
                prev=prev.next;
        }
        return head;

    }
    public ListNode RemoveElements(ListNode head,int val){
        if (head==null)
            return head;
        ListNode res=removeElements(head.next,val);
        if (head.val==val)
            return res;
        else {
            head.next=res;
            return head;
        }
    }
    public static void main(String[] args){
        int[] nums={99,2,3,4,6,5,6};
        ListNode head=new ListNode(nums);
        System.out.println(head);

        ListNode res=(new solutionLinkedList()).RemoveElements(head,6);
        System.out.println(res);
    }
}
