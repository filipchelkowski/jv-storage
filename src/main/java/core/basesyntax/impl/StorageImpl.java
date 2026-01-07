package core.basesyntax.impl;

import core.basesyntax.Storage;

public class StorageImpl<K, V> implements Storage<K, V> {
    private static final int MAX_SIZE = 10;
    private int size = 0;
    private Object[] keyArray = new Object[MAX_SIZE];
    private Object[] valueArray = new Object[MAX_SIZE];

    public StorageImpl() {

    }

    @Override
    public void put(K key, V value) {
        int index = findIndex(key);

        if (index >= 0) {
            valueArray[index] = value;
            return;
        }

        if (size == MAX_SIZE) {
            throw new RuntimeException("Array is full");
        }

        keyArray[size] = key;
        valueArray[size] = value;
        size++;
    }

    @Override
    public V get(K key) {
        int index = findIndex(key);

        if (index == -1) {
            return null;
        }

        return (V) valueArray[index];
    }

    @Override
    public int size() {
        return size;
    }

    private int findIndex(K key) {
        for (int i = 0; i < size; i++) {
            if (key == null && keyArray[i] == null) {
                return i;
            }

            if (key != null && key.equals(keyArray[i])) {
                return i;
            }
        }

        return -1;
    }

}
