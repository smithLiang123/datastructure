package com.BinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<E extends Comparable<E>> {

    private class Node{
        public E e;
        public Node leftChild,rightChild;
        public Node(E e){
            this.e=e;
            leftChild=null;
            rightChild=null;
        }
    }
    private Node root;
    private int size;
    public BinarySearchTree(){
        root=null;
        size=0;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    /**
     * add逻辑
     * root 不动 其他相对root 动
     * */
    public void add(E e){
        root=add(root,e);
    }
    /**
     * 递归直到叶子节点出现空缺，当到NULL的时候直接 new 一个node
     * */
    private Node add(Node node,E e){
        //是否为空
        if (node==null){
            size++;
            return new Node(e);
        }
        //比较插入元素
        if (e.compareTo(node.e)<0){
            node.leftChild=add(node.leftChild,e);
        }else {
            node.rightChild=add(node.rightChild,e);
        }
        //相等不变
        return node;
    }

    public boolean contains(E e){
        return contains(root,e);
    }
    /**
     * 不断 下沉 式搜索 比较
     * */
    private boolean contains(Node node,E e){
        if (node==null)
            return false;
        if (node.e.compareTo(e)==0)
            return true;
        else if (e.compareTo(node.e)<0)
            return contains(node.leftChild,e);
        else
            return contains(node.rightChild,e);

    }

    /**
     * 深度优先
     * */
    public void preOrder(){
        preOrder(root);
    }
    private void preOrder(Node node){
        if (node==null)                              //遇到NULL的时候 返回
            return;
        System.out.println(node.e);                  //1.打印node元素
        preOrder(node.leftChild);                    //2.往left 探测
        preOrder(node.rightChild);                   //3.往right 探测
    }
    public void midOrder(){
        midOrder(root);
    }
    private void midOrder(Node node){                 //也是一种排序了
        if (node==null)
            return;
        midOrder(node.leftChild);                      //1.往左探测
        System.out.println(node.e);                    //2.打印本身node 元素
        midOrder(node.rightChild);                     //3.往右探测
    }
    public void postOrder(){
        postOrder(root);
    }
    private void postOrder(Node node){
        if (node==null)
            return;
        postOrder(node.leftChild);                    //1.往左探测
        postOrder(node.rightChild);                   //2.往右探测
        System.out.println(node.e);                   //3.打印本身 node
    }
    public void preOrderUseStack(){                    //使用stack辅助 先序遍历
        Stack<Node> stack=new Stack();
        stack.push(root);                                //1.push（root）
        while (!stack.isEmpty()){  //当栈为空表明遍历完了
            Node cur=stack.pop();                         //2.pop（root）
            System.out.println(cur.e);

            if (cur.rightChild!=null)                     //非空
                stack.push(cur.rightChild);                //3.push（cur.rightChild）
            if (cur.leftChild!=null)                       //非空
                stack.push(cur.leftChild);                  //4.push(cur.leftChild)

        }
    }

    /**
     * 广度优先
     * */
    public void sequenceTravel(){
        Queue<Node> q=new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){ //层序遍历，利用了queue 入队 出队cur 入队（cur.left，cur.right） 打印cur
            Node cur=q.remove();
            if (cur.leftChild!=null)
                q.add(cur.leftChild);
            if (cur.rightChild!=null)
                q.add(cur.rightChild);
            System.out.print(cur.e+" ");
        }
    }
    public E minimum(){
        if (size==0)
            throw new IllegalArgumentException("BST is empty");
        return minimum(root).e;
    }
    private Node minimum(Node node){
        if (node.leftChild==null){
            return node;
        }
        return minimum(node.leftChild);
    }
    public E maximum(){
        if (size==0)
            throw new IllegalArgumentException("BST is empty");
        return maximum(root).e;
    }
    private Node maximum(Node node){
        if (node.rightChild==null)
            return node;
        return maximum(node.rightChild);
    }
    public E removeMin(){
        E ret=minimum();
        removeMin(root);
        return ret;
    }
    //删除以node为跟的二分搜索树中的最小节点
    //返回删除后的根节点
    private Node removeMin(Node node){
        if (node.leftChild==null){                 //当前node就是最小 node
            Node rightNode=node.rightChild;
            node.rightChild=null;
            size--;
            return rightNode;
        }
        node.leftChild=removeMin(node.leftChild);    //此处有递归，将删除节点的右子树，接到了原位置
        return node;
    }
    public E removeMax(){
        E ret=maximum();
        removeMax(root);
        return ret;
    }
    private Node removeMax(Node node){
        if (node.rightChild==null){
            Node leftNode=node.leftChild;
            node.leftChild=null;
            size--;
            return leftNode;
        }
        node.rightChild=removeMax(node.rightChild);
        return node;
    }
    public void remove(E e){
        root=remove(root,e);
    }

    private Node remove(Node node,E e){
        if (node==null)
            return null;
        if (e.compareTo(node.e)<0){
            node.leftChild=remove(node.leftChild,e);
            return node;
        }else if (e.compareTo(node.e)>0){
            node.rightChild=remove(node.rightChild,e);
            return node;
        }else {
            //e==node.e
            if (node.leftChild==null){
                Node rightNode=node.rightChild;
                node.rightChild=null;
                size--;
                return rightNode;
            }
            if (node.rightChild==null){
                Node leftNode=node.leftChild;
                node.leftChild=null;
                size--;
                return leftNode;
            }
            //删除节点左右子树均不为空
            //找到比待删除节点大的最小节点，使用这个节点顶替待删除节点
            Node successor=minimum(node.rightChild);
            successor.rightChild=removeMin(node.rightChild);
            successor.leftChild=node.leftChild;
            node.leftChild=node.rightChild=null;
            return successor;
        }

    }
/*前序遍历*/
    @Override
    public String toString(){
        StringBuilder res=new StringBuilder();
        generateBSTString(root,0,res);
        return res.toString();
    }
    private void generateBSTString(Node node,int depth,StringBuilder res){
        if (node==null){
            res.append(generateDeepthString(depth)+"null\n");
            return;
        }
        res.append(generateDeepthString(depth)+node.e+"\n");
        generateBSTString(node.leftChild,depth+1,res);
        generateBSTString(node.rightChild,depth+1,res);
    }
    private String generateDeepthString(int depth){
        StringBuilder res=new StringBuilder();
        for (int i=0;i<depth;i++){
            res.append("--");
        }
        return res.toString();
    }
    public static void main(String[] args){
        BinarySearchTree<Integer> binarySearchTree=new BinarySearchTree<>();
        int[] nums={5,3,4,6,8,2};
        /*
        *                   5
        *              3          6
        *          2      4             8
        *
        * */
        for (int num:nums){
            binarySearchTree.add(num);
        }
        binarySearchTree.preOrder();
        System.out.println("---------------------");
        binarySearchTree.midOrder();
        System.out.println("---------------------");
        binarySearchTree.postOrder();
        binarySearchTree.sequenceTravel();
       // binarySearchTree.removeMin();
//        binarySearchTree.removeMax();
//        System.out.println();
//        binarySearchTree.sequenceTravel();
        binarySearchTree.removeMin();
        System.out.println();
        binarySearchTree.sequenceTravel();

//        System.out.println();
//        binarySearchTree.preOrderUseStack();
//        System.out.println();
//        binarySearchTree.midOrder();
//        System.out.println();
//        binarySearchTree.postOrder();
//        binarySearchTree.sequenceTravel();
       // System.out.println(binarySearchTree);
    }
}
