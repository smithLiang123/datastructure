package com.LinkedList;

public class LinkedList<E> {
    /**
     * linkedList内部类 Node
     * E e;          元素e
     * Node next;    next node后一个元素
     * */
    private class Node{
        public E e;
        public Node next;
        public Node(E e,Node next){
            this.e=e;
            this.next=next;
        }
        public Node(E e){
            this(e,null);
        }
        public Node(){
            this(null,null);
        }
        @Override
        public String toString(){
            return e.toString();
        }
    }
    //设置哑节点 dummyHead
    private Node dummyHead;   //LinkedList的头结点
    int size;                 //LinkedList的size大小
    //linkedList的无参构造器，设置了一个哑节点。
    public LinkedList(){
        dummyHead=new Node(null,null);
        size=0;
    }
    public int getSize(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    /**
     *
     * */
    public void add(int index,E e){
        if(index<0||index>size)
            throw new IllegalArgumentException("Add failed.Illegal index");
            Node prev=dummyHead;
            for (int i=0;i<index;i++)//不断后延，直到index的位置
                prev=prev.next;
            Node node=new Node(e);    //新建一个node节点，prev-> new node(e) ->prev.next
            node.next=prev.next;
            prev.next=node;
            size++;
    }

    public void addLast(E e){
        add(size,e);
    }
    public void addFirst(E e){
        add(0,e);
    }

    public E get(int index){
        if (index<0||index>=size){
            throw new IllegalArgumentException("Failed. illegal index");
        }

        Node cur=dummyHead.next;
        for (int i=0;i<index;i++){
            cur=cur.next;
        }
        return cur.e;
    }
    public E getFirst(){
        return get(0);
    }
    public E getLast(E e){
        return get(size-1);
    }

    public void set(int index,E e){
        if (index<0||index>=size)
            throw new IllegalArgumentException("Update failed. Illegal index");

        Node cur=dummyHead.next;
        for (int i=0;i<index;i++){
            cur=cur.next;
        }
        cur.e=e;
    }

    public boolean contains(E e){
        Node cur=dummyHead.next;
        while (cur.next!=null){
            if (cur.e.equals(e))
                return true;
            cur=cur.next;
        }
        return false;
    }
    /**
     * 删除node节点
     *
     *
     * */
    public E remove(int index){
        if (index<0||index>=size)
            throw new IllegalArgumentException("Removed failed. Index is Illegal");

        Node prev=dummyHead;
        for (int i=0;i<index;i++)
            prev=prev.next;

        Node retNode=prev.next;
        prev.next=retNode.next;
        retNode.next=null;

        size--;
        return retNode.e;
    }

    public E removeFirst(){
        return remove(0);
    }



    @Override
    public String toString(){
        StringBuilder res=new StringBuilder();
        Node cur=dummyHead.next;
        res.append("Dummy Head->");
        while(cur!=null){
            res.append(cur+"->");
            cur=cur.next;
        }
        res.append("Null");
        return res.toString();
    }

}
