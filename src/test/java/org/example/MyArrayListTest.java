package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {
    MyArrayList<Double> num;
    MyArrayList<Character> ch;
    MyArrayList<String> str;
    @BeforeEach
    void setUp() {
        num=new MyArrayList<>();
        ch=new MyArrayList<>();
        str=new MyArrayList<>();
    }

    @Test
    void replace() {
        num.add(7.0);
        assertEquals(7,num.get(0));
        num.add(10,3.0);
        assertEquals(7,num.replace(0,5.0));
        assertEquals(5,num.get(0));
    }

    @Test
    void get() {
        str.add("kot");
        assertEquals("kot",str.get(0));
        str.add(2,"cat");
        assertEquals("cat",str.get(2));
        ch.add('x');
        assertEquals('x',ch.get(0));
        ch.add(1,'a');
        assertEquals('a',ch.get(1));
        MyArrayList<Integer> dub=new MyArrayList<>();
        num.add(1000,0.0);
        dub.add(1000,0);
        for(int i=0; i<1000; i+=2) {
            num.add(i, 1000.0 - i);
            dub.add(i, 1000 - i);
        }
        for(int i=0; i<1000; i+=2)
            if(num.get(i+1)!=null)
                assertFalse(num.get(i)<num.get(i+1));
        num.sort();
        dub.quicksort();
        str.quicksort();
        str.sort();
        assertEquals("cat",str.get(0));
        assertEquals("kot",str.get(1));
        for(int i=0; i<=1000; ++i)
            if(num.get(i+1)!=null)
                assertTrue(num.get(i)<num.get(i+1));
        for(int i=0; i+1<num.size(); ++i)
            assertTrue(num.get(i)<num.get(i+1));
        for(int i=0; i<=1000; ++i)
            if(dub.get(i+1)!=null)
                assertTrue(dub.get(i)<dub.get(i+1));
        for(int i=0; i+1<dub.size(); ++i)
            assertTrue(dub.get(i)<dub.get(i+1));
        assertTrue(num.size()==dub.size());
        for(int i=0; i<dub.size(); ++i)
            //if(num.get(i)!=dub.get(i))
                //System.out.printf("%d %d\n",num.get(i),dub.get(i));
            assertTrue((double)num.get(i)==(int)dub.get(i));
    }

    @Test
    void remove() {
        num.add(5,10.0);
        assertEquals(10,num.get(5));
        assertEquals(null,num.get(0));
        str.add("dog"); str.add(5,"cat");
        assertEquals(5,str.remove("cat"));
        assertEquals(0,str.remove("dog"));
        assertEquals(-1,str.remove("horse"));
    }

    @Test
    void capacity() {
        assertEquals(10,num.capacity());
        num.add(10,0.0);
        assertEquals(20,num.capacity());
    }

    @Test
    void size() {
        assertEquals(0,ch.size());
        ch.add(10,'c');
        assertEquals(1,ch.size());
        ch.add('t');
        assertEquals(2,ch.size());
        ch.remove(10);
        assertEquals(1,ch.size());
        ch.remove(9);
        assertEquals(1,ch.size());
    }

    @Test
    void isEmpty() {
        assertEquals(true,str.isEmpty());
        str.add("cat");
        assertEquals(false,str.isEmpty());
        str.remove(0);
        assertEquals(true,str.isEmpty());
        for(int i=0; i<1000; ++i)
            num.add((double)i);
        for(int i=0; i<1000; ++i)
            assertEquals(i,num.get(i));
        num.clean();
        assertEquals(true,num.isEmpty());
        for(int i=0; i<num.capacity(); ++i)
            assertEquals(null,num.get(i));
    }
}