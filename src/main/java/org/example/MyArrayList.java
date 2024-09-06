package org.example;
import java.io.*;
import java.lang.*;
import java.util.*;

public class MyArrayList<T> implements Comparator<T> {
    Object[] arr;
    int size=0;

    /***
     * The class constructor to create Object of type T, initially 10 elements long
     */
    public MyArrayList(){
        arr=new Object[10];
    }

    /***
     * This function adds an element to the array
     * @param ob Object parameter of type T to be added to the array
     */
    public void add(T ob){
        if(size==arr.length)
            moreCapacity();
        arr[size]=ob;
        size+=1;
    }

    /***
     * This function adds an element to the array at a given index
     * @param index Primitive parameter of type int, indicating the index whereat the second parameter is to be inserted
     * @param ob Object parameter of type T to be inserted
     */
    public void add(int index,T ob){
        if(index<0)
            throw new IndexOutOfBoundsException("Out of boundaries");
        while(index>=arr.length)
            moreCapacity();
        if(arr[index]!=null)
            throw new RuntimeException("Already occupied");
        arr[index]=ob;
        size+=1;
    }

    /***
     * This function replaces the array element at a given index
     * @param index Primitive parameter of type int, indicating the index whereat the array element shall be replaced
     * @param ob Object parameter of type T to be inserted instead of existing that at the given index
     * @return This function returns the replaced array element
     */
    public T replace(int index,T ob){
        if(index<0 || index>=arr.length)
            throw new IndexOutOfBoundsException("Out of boundaries");
        T prev=(T)arr[index];
        arr[index]=ob;
        return prev;
    }

    /***
     * This function provides the array element at a given index
     * @param index Primitive parameter of type int, indicating the index where the array element shall be fetched from
     * @return This function return the fetched element of the array
     */
    public T get(int index){
        if(index<0 || index>=arr.length)
            throw new IndexOutOfBoundsException("Out of boundaries");
        return (T)arr[index];
    }

    /***
     * This function removes the array element at a given index
     * @param index Primitive parameter of type int, indicating the index whereat the array element is to be removed
     * @return This function returns the removed array element if found, or exception otherwise
     */
    public T remove(int index){
        if(index<0 || index>=arr.length)
            throw new IndexOutOfBoundsException("Out of boundaries");
        T ob=(T)arr[index];
        if(arr[index]!=null)
            size-=1;
        arr[index] = null;
        return ob;
    }

    /***
     * This function removes the array element passed by value
     * @param ob Object parameter of type T to be removed once found
     * @return This function returns 1 if the array element found and removed, or -1 otherwise
     */
    public int remove(T ob){
        for(int i=0; i<arr.length; ++i)
            if(arr[i]==ob) {
                arr[i]=null;
                size-=1;
                return i;
            }
        return -1;
    }

    /***
     * This function sets all array elements to null
     */
    public void clean(){
        for(int i=0; i<arr.length; ++i)
            arr[i]=null;
        size=0;
    }

    /***
     * This function sorts the array in ascending order
     */
    public void sort(){
        for(int i=0; i<arr.length-1; ++i)
            if(arr[i]==null && arr[i+1]!=null){
                arr[i]=arr[i+1];
                arr[i+1]=null;
                i=-1;
            }
        Object ob;
        for(int i=0; arr[i+1]!=null; ++i)
            if(compare((T)arr[i],(T)arr[i+1])>0){
                ob=arr[i];
                arr[i]=arr[i+1];
                arr[i+1]=ob;
                i=-1;
            }
    }

    /***
     * This function also sorts the array in ascending order
     */
    public void quicksort(){
        int i, last;
        for(i=0; arr[i]==null && i<arr.length; ++i);
        if(i==arr.length)
            return;
        for(last=-1; i<arr.length; ++i)
            if(arr[i]!=null){
                last+=1;
                if(i-last>0){
                    arr[last]=arr[i];
                    arr[i]=null;
                }
            }
        do {
            Object pivot=arr[last];
            for (i = 0; i < last; ++i)
                if (compare((T) arr[i], (T) pivot)>0) {
                    arr[last]=arr[i];
                    arr[i]=pivot;
                    pivot=arr[last];
                }
        }while(--last>0);
    }

    /***
     * This function displays all array elements not equal to null
     */
    public void showAll(){
        for(int i=0; i<arr.length; ++i)
            if(arr[i]!=null)
                System.out.println(arr[i]);
    }

    /***
     * This function returns the array capacity
     * @return Primitive type of int
     */
    public int capacity(){
        return arr.length;
    }

    /***
     * This function return the number of array elements not equal to null
     * @return Primitive type of int
     */
    public int size(){
        return size;
    }

    /***
     * This function indicates whether the array contains any element not equal to null
     * @return Primitive type of boolean
     */
    public boolean isEmpty(){
        for(int i=0; i<arr.length; ++i)
            if(arr[i]!=null)
                return false;
        return true;
    }

    /***
     * This function increases the array capacity twice a time
     */
    void moreCapacity(){
        arr = Arrays.copyOf(arr, arr.length * 2);
    }

    /***
     * This is my implementation of compare function of Comparator Interface
     * @param ob1 The first object parameter of type T to be compared
     * @param ob2 The second object parameter of type T to be compared
     * @return This function returns 1 if the first parameter is greater than second that, 0 if the first parameter is equal to the second that, ot -1 otherwise
     */
    @Override
    public int compare(T ob1, T ob2) {
        if(ob1 instanceof Number || ob1 instanceof String || ob1 instanceof Character)
            return ((Comparable<T>)ob1).compareTo((T)ob2);
        else {
            if (ob1.hashCode() > ob2.hashCode())
                return 1;
            if (ob1.hashCode() < ob2.hashCode())
                return -1;
            return 0;
        }
    }
}
