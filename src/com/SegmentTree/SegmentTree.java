package com.SegmentTree;

public class SegmentTree<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){

        this.merger = merger;

        data = (E[])new Object[arr.length];
        for(int i = 0 ; i < arr.length ; i ++)
            data[i] = arr[i];

        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0, 0, arr.length - 1);
    }

    // 在treeIndex的位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r){

        if(l == r){
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // int mid = (l + r) / 2;
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return 2*index + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return 2*index + 2;
    }

    public E query(int queryL,int queryR){

        if (queryL<0||queryL>= data.length||queryR<0||queryR> data.length)
            throw new IllegalArgumentException("Index is out of bound");

        return query(0,0, data.length-1,queryL,queryR);
    }

    private E query(int treeIndex,int l,int r,int queryL,int queryR){
        if (l==queryL&&r==queryR)
            return tree[treeIndex];
        int mid=l+(r-l)/2;
        int leftTreeIndex=leftChild(treeIndex);
        int rightTreeIndex=rightChild(treeIndex);
        if (queryL>=mid+1){
            return query(rightTreeIndex,mid+1,r,queryL,queryR);
        }else if (queryR<=mid){
            return query(leftTreeIndex,l,mid,queryL,queryR);
        }
        E leftResult=query(leftTreeIndex,l,mid,queryL,queryR);
        E rightResult=query(rightTreeIndex,mid+1,r,queryL,queryR);
        return merger.merge(leftResult,rightResult);
    }

    public void set(int index,E e){
        if (index<0||index>= data.length){
            throw new IllegalArgumentException("Index is Illegal");
        }
        data[index]=e;
        set(0,0, data.length-1,index,e );
    }

    private void set(int treeIndex,int l,int r,int index,E e){
        if (l==r){
            tree[treeIndex]=e;
            return;
        }
        int mid=l+(r-l)/2;
        int leftIndex=leftChild(treeIndex);
        int rightIndex=rightChild(treeIndex);
        if (index>=mid+1){set(rightIndex,mid+1,r,index,e);}
        else {set(leftIndex,l,mid,index,e);}

        tree[treeIndex]=merger.merge(tree[leftIndex],tree[rightIndex]);
    }
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i = 0 ; i < tree.length ; i ++){
            if(tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if(i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}
