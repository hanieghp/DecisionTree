public class MHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private Entry<K, V>[] table;
    private int size;

    public MHashMap() {
        this.table = new Entry[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public void put(K key, V value) {
        int hash = hash(key);
        int index = hash % table.length;

        Entry<K, V> entry = table[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
            entry = entry.next;
        }

        Entry<K, V> newNode = new Entry<>(key, value);
        newNode.next = table[index];
        table[index] = newNode;
        size++;

        // Check if rehashing is needed
        if (size > table.length * 0.75) {
            resize();
        }
    }

    public V get(K key) {
        int hash = hash(key);
        int index = hash % table.length;

        Entry<K, V> entry = table[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    private int hash(K key) {
        return key.hashCode();
    }

    private void resize() {
        Entry<K, V>[] oldTable = table;
        int newCapacity = oldTable.length * 2;
        table = new Entry[newCapacity];
        size = 0;

        for (Entry<K, V> entry : oldTable) {
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
}
