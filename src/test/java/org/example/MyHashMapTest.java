package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {
    MyHashMap<Character, String> str;
    MyHashMap<Integer, Double> num;
    @BeforeEach
    void setUp() {
        str=new MyHashMap<>();
        num=new MyHashMap<>();
    }

    @Test
    void replace() {
        str.put('a',"a");
        str.replace('a',"replaced");
        str.replace('c',"replaced");
        num.put(1,1.0);
        num.replace(1,0.0);
        num.replace(1,null);
        num.replace(null,10.0);
        assertEquals("replaced",str.get('a'));
        assertEquals(0.0,num.get(1));
    }

    @Test
    void get() {
        str.put('a',"a");
        str.put('b',"b");
        num.put(1,1.0);
        num.put(2,2.0);
        assertEquals("b",str.get('b'));
        assertEquals(2.0,num.get(2));
    }

    @Test
    void delete() {
        str.put('a',"a");
        str.replace('a',"replaced");
        str.put('c',"put");
        assertEquals("replaced",str.delete('a'));
        assertEquals(null,str.delete('a'));
        assertEquals("put",str.get('c'));
    }

    @Test
    void remove() {
        str.put('a',"a");
        str.replace('a',"replaced");
        str.put('c',"put");
        assertEquals('a',str.remove("replaced"));
        assertEquals(null,str.remove("replaced"));
        assertEquals("put",str.get('c'));
    }

    @Test
    void values() {
        for(double i=0; i<10.0; i+=1.0)
            num.put((int)i,i);
        num.delete(0);
        Object[] n=num.values();
        for(int i=1; i<=num.size(); ++i) {
            //System.out.println(n[i-1]);
            assertEquals((double) i, n[i-1]);
        }
    }

    @Test
    void keyset() {
        for(int i=0; i<10.0; i+=1.0)
            num.put(i,(double)i);
        num.delete(0);
        Object[] n=num.keyset();
        for(int i=1; i<=num.size(); ++i) {
            //System.out.println(n[i-1]);
            assertEquals(i, n[i-1]);
        }
    }

    @Test
    void entryset() {
        for(int i=0; i<10.0; i+=1.0)
        num.put(i,(double)i);
        num.delete(0);
        String[] n=num.entryset();
        for(int i=1; i<=num.size(); ++i) {
            //System.out.println(n[i-1]);
            assertEquals(i+"="+(double)i, n[i-1]);
        }
    }

    @Test
    void capacity() {
        str.put('a',"a"); str.put('b',"b"); str.put('c',"c");
        str.put('a',"a"); str.put('b',"b"); str.put('c',"c");
        str.put('a',"a"); str.put('b',"b"); str.put('c',"c");
        str.put('a',"a"); str.put('b',"b"); str.put('c',"c");
        num.put(1,1.0);
        num.put(2,2.0);
        assertEquals(20,str.capacity());
        assertEquals(10,num.capacity());
    }

    @Test
    void size() {
        str.put('a',"a");
        str.put('b',"b");
        str.delete('a');
        str.delete('a');
        num.put(1,1.0);
        num.put(2,2.0);
        assertEquals(1,str.size());
        assertEquals(2,num.size());
    }

    @Test
    void isEmpty() {
        assertTrue(str.isEmpty());
        str.put('a',"a");
        assertFalse(str.isEmpty());
        str.delete('a');
        assertTrue(str.isEmpty());
        str.put('c',"c");
        str.clean();
        assertTrue(str.isEmpty());
    }
}