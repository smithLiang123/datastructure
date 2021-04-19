package com.RBTree;

import java.util.ArrayList;

/**
 * 《算法导论》中的红黑树   （黑平衡的二叉树）
 * 1.每个节点或是红色的或是黑色的。
 * 2.根节点是黑色的。
 * 3.每一个叶子节点（最后的空节点）是黑色的
 * 4.如果一个节点是红色的，那么他的孩子节点都是黑色的
 * 5.从任意一个节点到叶子节点，经过的黑色子节点是一样的
 *
 * Robert Sedgewick 红黑树发明人
 * 红黑树 与 2-3树的等价性    2节点 就是一个黑节点   3节点 由一红一黑两个节点组成
 * 2-3树：两种节点    3节点          (4节点)      2节点
 *         a       a b            a b c        b
 *        / \     / | \                       / \
 *        2节点      3节点                     a  c
 *
 *          4节点向上融合 然后提中位数 变成三个2节点
 *
 * 红黑树的逻辑实际上和 2-3树一样  2-3树是绝对平衡的
 * 把 3节点中 值小一点的节点 染成红色
 * 红黑树会向左倾斜
 * 红节点的孩子一定是黑色的
 * 黑节点的左子节点有可能是红色的，右子节点一定是黑色的
 *
 * 所有叶子节点走过黑色叶子节点深度一样:
 *
 * 最大高度:2logn   O(logn)
 *
 * 红黑树：增加数据的速度比AVL快
 * AVL：查询数据速度比红黑树快
 * */
public class RBT<K extends Comparable<K>,V>{


    private static final boolean RED=true;
    private static final boolean BLACK=false;
    private class Node{
        public K key;
        public V value;
        public Node left,right;
        public boolean color;       //

        public Node(K key,V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color=RED;                    /**初始节点默认为红色，永远要和子节点融合*/
        }
    }

    private Node root;
    private int size;

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**判断节点的颜色*/
    private boolean isRed(Node node){
        if (node==null){
            return BLACK;
        }
        return node.color;
    }

    /**  左旋转过程   在 2节点添加元素
     *   node                   x
     *   /  \       左旋转      / \
     *  T1   x   ------->   node  T3
     *      / \             /  \
     *    T2   T3         T1   T2
     * */
    private Node leftRotate(Node node){
        Node x=node.left;
        node.right=x.left;
        x.left=node;
        x.color=node.color;
        node.color=RED;
        return x;
    }

    /**
     * 右旋转过程
     *       node               x
     *      /   \              / \
     *     x    T2            y   node
     *   /  \        右旋转        /  \
     *  y   T1       ------>     T1   T2
     *
     * */
    private Node rightRotate(Node node){
        Node x=node.left;
        //右旋转
        node.left=x.right;
        x.right=node;

        x.color=node.color;
        node.color=RED;

        return x;
    }

    /**  颜色翻转 */
    private void flipColors(Node node){
        node.color=RED;
        node.left.color=BLACK;
        node.right.color=BLACK;
    }




    /**
     * 向红黑树中添加元素
     * 保持红黑树的根节点为黑色
     * */
    public void add(K key,V value){
        root=add(root,key,value);
        root.color=BLACK;
    }
    /**                 左旋转        右旋转    颜色翻转
     * 添加新元素          black       black
     *                 /            /
     *   black --->  red   --->   red  ---> black  --->red
     *   /             \          /          /  \      /  \
     * red               red     red       red  red  black black
     *
     * */
    private Node add(Node node,K key,V value){

        if (node==null){
            size++;
            return new Node(key,value);
        }

        if (key.compareTo(node.key)<0){
            node.left=add(node.left,key,value);
        }
        else if (key.compareTo(node.key)>0){
            node.right=add(node.right,key,value);
        }
        else {
            node.value=value;
        }


        //三个红黑树性质维护
        if (isRed(node.right)&& !isRed(node.left)){
            node=leftRotate(node);
        }
        if (isRed(node.left)&& isRed(node.left.left)){
            node=rightRotate(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }

        return node;
    }

    private Node getNode(Node node, K key){

        if(node == null)
            return null;

        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public V get(K key){

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 从二分搜索树中删除键为key的节点
    public V remove(K key){

        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){

        if( node == null )
            return null;

        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            return node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            return node;
        }
        else{   // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }



    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            RBT<String, Integer> map = new RBT<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }


}

















