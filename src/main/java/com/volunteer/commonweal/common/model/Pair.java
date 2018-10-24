package com.volunteer.commonweal.common.model;

public class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Pair() {
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if ((o instanceof Pair) && (((Pair) o).getKey().equals(this.key)) && (((Pair) o).getValue().equals(this.value))) {
            return true;
        }
        return false;
    }
}
