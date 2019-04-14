import java.util.*;

public class HashMap <K,V> {
    static final private int defaultInitialCapacity = 16;
    static final private float defaultLoadFactor = 0.75f;

    private float loadFactor;
    private Entry<K,V>[] buckets;
    private int size;
    private int capacity;

    private class Entry <K,V> {
        K key;
        V value;
        Entry nxt;

        Entry( K k, V v ){
            this.key = k;
            this.value = v;
        }

        /**
         * Checks equality of input key w/ entry's key
         */
        boolean hasKey( K k ){
            if( key == null ){
                // For when a null key is mapped
                return (k == null);
            }
            return key.equals(k);
        }
    }

    /**
     * CONSTRUCTORS
     */
    public HashMap(){
        this(defaultInitialCapacity, defaultLoadFactor);
    }
    public HashMap(float loadFactor){
        this(defaultInitialCapacity, loadFactor);
    }
    public HashMap(int initialCapacity){
        this(initialCapacity, defaultLoadFactor);
    }
    public HashMap(int initialCapacity, float loadFactor){
        this.buckets = new Entry[initialCapacity];
        this.loadFactor = loadFactor;
        this.capacity = initialCapacity;
        this.size = 0;
    }

    /**
     * Gets index of Entry
     */
    private int getIndex(K k) {
        int idx = Objects.hashCode(k) % capacity;
        return idx;
    }

    /**
     * Increments size of hashMap
     */
    private void incrementSize() {
        size++;
        float currLoad = size/(float) capacity;
        if( currLoad >= defaultLoadFactor ){
            Entry<K,V>[] oldBuckets = Arrays.copyOf(buckets, buckets.length);
            capacity = capacity *2;
            size = 0;
            buckets = new Entry[capacity];
            for( Entry<K,V> n : oldBuckets ){
                while( n != null ){
                    put( n.key, n.value );
                    n = n.nxt;
                }

            }
        }
    }

    /**
     * Returns whether the map contains the input key
     */
    public boolean containsKey(K k){
        int idx = getIndex(k);
        Entry curr = buckets[idx];
        while( curr != null ){
            if( curr.hasKey(k) ){
                return true;
            }
            curr = curr.nxt;
        }
        return false;
    }

    /**
     * Maps a value to an input key
     */
    public void put(K k, V v){
        // increment size, length of bucket won't be size when collissions occur
        int idx = getIndex(k);
        Entry curr = buckets[idx];

        if(curr == null){
            // No collission - create new entry
            buckets[idx] = new Entry(k,v);
            incrementSize();
            return;
        } else {
            // collission - traverse linked list
            while(curr != null){
                if( curr.hasKey(k) ){
                    // key is mapped - overwrite
                    curr.value = v;
                    return;
                }
                if( curr.nxt != null ){
                    curr = curr.nxt;
                } else {
                    // Mapping for k does not exist - create new Entry & increment size
                    Entry n = new Entry(k, v);
                    curr.nxt = n;
                    incrementSize();
                    return;
                }
            }
        }

        return;
    }

    /**
     * Returns the value mapped to by an input key. Returns null if no value is mapped
     */
    public V get(K k){
        int idx = getIndex(k);
        Entry curr = buckets[idx];
        while( curr != null ){
            if( curr.hasKey(k) ){
                return (V) curr.value;
            }
            curr = curr.nxt;
        }
        return null;
    }

    /**
     * Removes and returns the value mapped by an input key. Retruns null if no value is mapped
     */
    public V remove(K k) {
        int idx = getIndex(k);
        Entry e1 = buckets[idx];

        if( e1 == null ){
            // No elment to remove
            return null;
        }

        if( e1.hasKey(k) ){
            buckets[idx] = e1.nxt;
            size--;
            return (V) e1.value;
        }

        // Iterate over entries mapped to bucket
        Entry e2 = e1.nxt;
        while( e2 != null ){
            if( e2.hasKey(k) ){
                e1.nxt = e2.nxt;
                size--;
                return (V) e2.value;
            } else {
                e1 = e2;
                e2 = e2.nxt;
            }
        }

        // No element to return
        return null;
    }

    /**
     * USED ONLY FOR TESTING
     */
    public int getCapacity(){
        return capacity;
    }
    public int getSize(){
        return size;
    }
}