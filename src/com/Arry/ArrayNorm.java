package com.Arry;


/**
 *
 * 使用泛型
 * *让我们的数据结构可以放置“任何”数据类型
 * @不可以是基本数据类型，只能是对象
 * @基本数据类型：boolean,byte,char,short,int,long,float,double
 * @每个基本类型对应的包装类：Boolean,Byte,Character,Short,Int,Long,Float,Double
 *
 *
 * */
public class ArrayNorm<E> {
    private E[] data;
    private int size;


    /**
     * @param capacity
     * 构造函数，传入数组的容量capacity构造arry
     * */
    public ArrayNorm(int capacity){
        data=(E[]) new Object[capacity];//不支持直接构造泛型数组，先构造Object数组转换成泛型数组
        size=0;
    }
    //无参数的构造函数，默认数组的容量capacity=10
    public ArrayNorm(){
        this(10);
    }
    //获取数组中的元素个数
    public int getSize(){
        return size;
    }
    //获取数组的容量
    public int getCapacity(){
        return data.length;
    }
    //在数组最后添加元素
    public void addLast(E e){
        add(size,e);
    }
    //在数组初始添加元素
    public void addFirst(E e){
        add(0,e);
    }
    /**
     * 向数组中添加元素 从后往前遍历，知道index 后移一位数据，在index添加数据
     * */
    public void add(int index,E e){

        if (index<0 || index>size){
            throw  new IllegalArgumentException("Add failed.Require index>=0 and <=size");
        }

        if (size==data.length){                     /**数组扩容*/
            resize(2* data.length);
        }
        for (int i=size-1;i>=index;i--){
            data[i+1]=data[i];
        }
        data[index]=e;
        size++;
    }
    //获得index索引位置的元素
   public E get(int index){
        if (index<0||index>=size){
            throw new IllegalArgumentException("Get failed.Index is illegal");
        }
        return data[index];
    }
    //设置index索引位置的元素
    void set(int index,E e){
        if (index <0 ||index>=size){
            throw new IllegalArgumentException("Set failed .Index is illegal");
        }
        data[index]=e;
    }
    //查找数组中是否有元素e
    public boolean contains(E e){
        for (E arr:data){
            if (arr==e){
                return true;
            }
        }
        return false;
    }
    //查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(E e){
        for (int i=0;i<size;i++){
            if (data[i]==e)
                return i;
        }
        return -1;
    }
    //删除元素e
    public E remove(int index){
        if(index<0||index >=size){
            throw new IllegalArgumentException("Remove failed. Index is illegal");
        }
        E ret=data[index];
        for (int i=index+1;i<size;i++){
            data[i-1]=data[i];
        }
        size--;
        data[size]=null;

        if (size<=data.length/2){
            resize(data.length/2);
        }

        return ret;
    }
    public E removeFirst(){
        return remove(0);
    }
    public E removeLast(){
        return remove(size-1);
    }
    //从数组中删除元素e
    public void removeElement(E e){
        int index=find(e);
        if (index!=-1){
            remove(index);
        }
    }

    public void resize(int newCapacity){
        E[] newData=(E[])new Object[newCapacity];   /**新建一个object对象数组 转换为E*/
        for (int i=0;i<size;i++){
            newData[i]=data[i];
        }
        data=newData;
        System.out.println("resize");
    }
    public boolean isEmpty(){
        return size==0;
    }

    @Override
    public String toString(){
        StringBuilder res =new StringBuilder();
        res.append(String.format("Arry:size=%d,capacity=%d\n",size,data.length));
        res.append("[");
        for (int i=0;i<size;i++){
            res.append(data[i]);
            if(i!=size-1){
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }
}
