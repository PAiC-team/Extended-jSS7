package org.restcomm.protocols.ss7.scheduler;

/**
 *
 * @author oifa yulian
 */
public class IntConcurrentHashMap<E> {

    private IntConcurrentLinkedList<E>[] lists = new IntConcurrentLinkedList[16];

    public IntConcurrentHashMap() {
        for (int i = 0; i < 16; i++)
            lists[i] = new IntConcurrentLinkedList<E>();
    }

    public boolean contains(int key) {
        return lists[getHash(key)].contains(key);
    }

    public boolean add(E value, int key) {
        if (value == null)
            return false;

        lists[getHash(key)].add(value, key);
        return true;
    }

    public boolean offer(E value, int key) {
        if (value == null)
            return false;

        lists[getHash(key)].offer(value, key);
        return true;
    }

    public E get(int key) {
        return lists[getHash(key)].get(key);
    }

    public E remove(int key) {
        return lists[getHash(key)].remove(key);
    }

    private int getHash(int key) {
        // 4 bits allows 16 values
        // use bits 1,2 and 6,7
        return key & 0x3 + (key >> 3) & 0xC;
    }
}
