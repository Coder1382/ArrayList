package org.example;
import javax.swing.*;
import java.io.*;
import java.lang.*;
import java.util.*;

public class MyHashMap<K, T extends Object> {
    Object[] keys;
    Object[] vals;
    int size;
    /***
     * The class constructor to create Objects of types K and T, initially capable of 10 elements
     */
    public MyHashMap(){
        keys=new Object[10];
        vals=new Object[10];
        size=0;
    }

    /***
     * This function adds an element with value of val under the specified key
     * @param key Object parameter of type K, indicating the key
     * @param val Object parameter of type T, indicating the value
     * @return This function returns the previous value under the specified key if found, or nul otherwise
     */
    public T put(K key, T val){
        int i;
        T ob=null;
        if(key==null || val==null)
            throw new IllegalArgumentException("Provide valid values");
        for (i = 0; i < keys.length && keys[i] != null && keys[i]!=key; ++i) ;
        if (i == keys.length)
            moreCapacity();
        else if(keys[i]==key)
            ob=(T)vals[i];
        else{
            keys[i]=key;
            size+=1;
        }
        vals[i] = val;
        return ob;
    }

    /***
     * This function replaces value under the specified key with the value of val
     * @param key Object parameter of type K, indicating the key under which the value is to be replaces
     * @param val Object parameter of type T, indicating the value to replace the existing that if found
     * @return This function returns the replaced value, or null otherwise
     */
    public T replace(K key,T val){
        T prev=null;
        if(key!=null && val!=null)
            for(int i=0; i<keys.length; ++i)
                if(keys[i]==key){
                    prev=(T)vals[i];
                    vals[i]=val;
                }
        return prev;
    }

    /***
     * This function provides the value under the specified key
     * @param key Object parameter of type K, indicating the key
     * @return This function returns the value under the specified key if found, or null otherwise
     */
    public T get(K key){
        if(key==null)
            throw new IllegalArgumentException("Provide valid value");
        for(int i=0; i<keys.length; ++i)
            if(keys[i]==key)
                return (T)vals[i];
        return null;
    }

    /***
     * This function deletes the entry under the specified key
     * @param key Object parameter of type K, indicating the key
     * @return This function returns the value under the specified key if found, or null otherwise
     */
    public T delete(K key){
        if(key==null)
            throw new IllegalArgumentException("Provide valid value");
        T ob=null;
        for(int i=0; i<keys.length; ++i)
            if(keys[i]==key){
                ob=(T)vals[i];
                keys[i]=null;
                vals[i]=null;
                size-=1;
            }
        return ob;
    }

    /**
     * This function deletes the first entry with the specified value
     * @param val Object parameter of type T, indicating the value to be removed
     * @return This function returns the key under which the specified value is found, or null otherwise
     */
    public K remove(T val){
        if(val==null)
            throw new IllegalArgumentException("Provide valid value");
        K ob=null;
        for(int i=0; i<keys.length; ++i)
            if(vals[i]==val){
                ob=(K)keys[i];
                keys[i]=null;
                vals[i]=null;
                size-=1;
            }
        return ob;
    }

    /**
     * This function returns the collection of value if any
     * @return array of values
     */
    public Object[] values(){
        Object[] arr=new Object[size];
        for(int i=0, j=0; i<keys.length; ++i)
            if(keys[i]!=null){
                arr[j]=vals[i];
                j+=1;
            }
        return arr;
    }

    /**
     * This function returns the collection of keys if any
     * @return array of keys
     */
    public Object[] keyset(){
        Object[] arr=new Object[size];
        for(int i=0, j=0; i<keys.length; ++i)
            if(keys[i]!=null){
                arr[j]=keys[i];
                j+=1;
            }
        return arr;
    }

    /***
     * This function return the collection of strings containing keys and respective values
     * @return array of strings
     */
    public String[] entryset(){
        String[] arr=new String[size];
        for(int i=0, j=0; i<keys.length; ++i)
            if(keys[i]!=null){
                arr[j]=keys[i]+"="+vals[i];
                j+=1;
            }
        return arr;
    }
    /***
     * This function sets all array elements to null
     */
    public void clean(){
        for(int i=0; i<keys.length; ++i)
            if(keys[i]!=null){
                keys[i]=null;
                vals[i]=null;
                size-=1;
            }
    }

    /***
     * This function displays all array elements not equal to null
     */
    public void showAll(){
        for(int i=0; i<keys.length; ++i)
            if(keys[i]!=null){
                System.out.println(keys[i]+"="+vals[i]);
            }
    }

    /***
     * This function returns the array capacity
     * @return Primitive type of int
     */
    public int capacity(){
        return keys.length;
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
        for(int i=0; i<keys.length; ++i)
            if(keys[i]!=null)
                return false;
        return true;
    }

    /***
     * This function increases the array capacity twice a time
     */
    void moreCapacity(){
        try {
            keys = Arrays.copyOf(keys, keys.length * 2);
            vals = Arrays.copyOf(vals, vals.length * 2);
        }catch(Exception e){
            System.out.println("Unexpected Error");
        }
    }
}
