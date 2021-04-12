package com.Arry;

public class Student {
    private String name;
    private int score;

    public Student(String studentName,int studentScore){
        name=studentName;
        score=studentScore;
    }

    @Override
    public String toString(){
        return String.format("Student(name:%s,score:%d)",name,score);
    }
    public static void main(String[] args){
        ArrayNorm<Student> arr=new ArrayNorm<>();
        arr.addLast(new Student("Tom",100));
        arr.addLast(new Student("Tim",99));

       // arr.addLast(new Student("Tim",99));
      //  arr.addLast(new Student("Tim",99));

        System.out.println(arr);
    }

}
