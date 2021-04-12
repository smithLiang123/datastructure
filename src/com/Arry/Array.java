package com.Arry;

public class Array {
    private int[] data;
    private int size;


    /**
     * @param capacity
     * 构造函数，传入数组的容量capacity构造arry
     * */
    public Array(int capacity){
        data=new int[capacity];
        size=0;
    }
    //无参数的构造函数，默认数组的容量capacity=10
    public Array(){
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
    public void addLast(int e){
//        if(size== data.length) {
//            throw new IllegalArgumentException("AddLast failed.Arry is full");
//        }
//        data[size]=e;
//        size++;
        add(size,e);
    }
    //在数组初始添加元素
    public void addFirst(int e){
        add(0,e);
    }
    //向数组中添加元素
    public void add(int index,int e){
        if (size==data.length){
            throw new IllegalArgumentException("Add failed .Arry is full");
        }
        if (index<0 || index>size){
            throw  new IllegalArgumentException("Add failed.Require index>=0 and <=size");
        }

        for (int i=size-1;i>=index;i--){
            data[i+1]=data[i];
        }
        data[index]=e;
        size++;
    }
    //获得index索引位置的元素
    int get(int index){
        if (index<0||index>=size){
            throw new IllegalArgumentException("Get failed.Index is illegal");
        }
        return data[index];
    }
    //设置index索引位置的元素
    void set(int index,int e){
        if (index <0 ||index>=size){
            throw new IllegalArgumentException("Set failed .Index is illegal");
        }
        data[index]=e;
    }
    //查找数组中是否有元素e
    public boolean contains(int e){
        for (int arr:data){
            if (arr==e){
                return true;
            }
        }
        return false;
    }
    //查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(int e){
        for (int i=0;i<size;i++){
            if (data[i]==e)
                return i;
        }
        return -1;
    }
    //删除元素e
    public int remove(int index){
        if(index<0||index >=size){
            throw new IllegalArgumentException("Remove failed. Index is illegal");
        }
        int ret=data[index];
        for (int i=index+1;i<size;i++){
            data[i-1]=data[i];
        }
        size--;
        return ret;
    }
    public int removeFirst(){
        return remove(0);
    }
    public int removeLast(){
        return remove(size-1);
    }
    //从数组中删除元素e
    public void removeElement(int e){
        int index=find(e);
        if (index!=-1){
            remove(index);
        }
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
