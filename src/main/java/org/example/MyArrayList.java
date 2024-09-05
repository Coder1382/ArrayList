package org.example;
import java.io.*;
import java.lang.*;
import java.util.*;

/***
 * Generic class for handling arrays of various types
 * @param <T>
 */
public class MyArrayList<T extends Object> implements Comparator<T> {
    Object[] arr;
    int size=0;
    public MyArrayList(){
        arr=new Object[10];
    }
    public void add(T ob){
        if(size==arr.length)
            moreCapacity();
        arr[size]=ob;
        size+=1;
    }
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
    public T replace(int index,T ob){
        if(index<0 || index>=arr.length)
            throw new IndexOutOfBoundsException("Out of boundaries");
        T prev=(T)arr[index];
        arr[index]=ob;
        return prev;
    }
    void moreCapacity(){
        try {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }catch(Exception e){
            System.out.println("Unexpected Error");
        }
    }
    public T get(int index){
        if(index<0 || index>=arr.length)
            throw new IndexOutOfBoundsException("Out of boundaries");
        return (T)arr[index];
    }
    public T remove(int index){
        if(index<0 || index>=arr.length)
            throw new IndexOutOfBoundsException("Out of boundaries");
        T ob=(T)arr[index];
        if(arr[index]!=null)
            size-=1;
        arr[index] = null;
        return ob;
    }
    public int remove(T ob){
        for(int i=0; i<arr.length; ++i)
            if(arr[i]==ob) {
                arr[i]=null;
                size-=1;
                return i;
            }
        return -1;
    }
    public void clean(){
        for(int i=0; i<arr.length; ++i)
            arr[i]=null;
        size=0;
    }
    @Override
    public int compare(T ob1, T ob2) {
        if(ob1 instanceof Byte || ob1 instanceof Short || ob1 instanceof Integer || ob1 instanceof Character) {
            if ((Integer) ob1 > (Integer) ob2)
                return 1;
            else if ((Integer) ob1 < (Integer) ob2)
                return -1;
            else return 0;
        }
        else if(ob1 instanceof Long) {
            if ((Long) ob1 > (Long) ob2)
                return 1;
            else if ((Long) ob1 < (Long) ob2)
                return -1;
            else return 0;
        }
        else if(ob1 instanceof Float || ob1 instanceof Double){
            if ((Double) ob1 > (Double) ob2)
                return 1;
            else if ((Double) ob1 < (Double) ob2)
                return -1;
            else return 0;
        }
        else if(ob1 instanceof String)
            return ((String) ob1).compareTo((String) ob2);
        else{
            if(ob1.hashCode()>ob2.hashCode())
                return 1;
            if(ob1.hashCode()<ob2.hashCode())
                return -1;
            else return 0;
        }
    }
    public void sort(){
        for(int i=0; i<arr.length-1; ++i)
            if(arr[i]==null && arr[i+1]!=null){
                arr[i]=arr[i+1];
                arr[i+1]=null;
                i=-1;
            }
        Object ob;
        for(int i=0; arr[i+1]!=null; ++i)
            if(compare((T)arr[i],(T)arr[i+1])==1){
                ob=arr[i];
                arr[i]=arr[i+1];
                arr[i+1]=ob;
                i=-1;
            }
    }
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
                if (compare((T) arr[i], (T) pivot)==1) {
                    arr[last]=arr[i];
                    arr[i]=pivot;
                    pivot=arr[last];
                }
        }while(--last>0);
    }
    public void showAll(){
        for(int i=0; i<arr.length; ++i)
            if(arr[i]!=null)
                System.out.println(arr[i]);
    }
    public int capacity(){
        return arr.length;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        for(int i=0; i<arr.length; ++i)
            if(arr[i]!=null)
                return false;
        return true;
    }
}
