import java.util.*;

public class HashMap <K,V> {

    // TODO - handle null case
    // TODO - handle loadFactor

    private float loadFactor;
    private Node<K,V>[] buckets;

    static final private int defaultInitialSize = 16;
    static final private float defaultLoadFactor = 0.75f;
    private int size;

    private class Node <K,V> {
        K key;
        V value;

        // Only populated on collissions
        Node nxt;

        Node( K k, V v ){
            this.key = k;
            this.value = v;
        }

        @Override
        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (Node.class.isInstance(o)) {
                final Node n = (Node)o;
                if (Objects.equals(n.key, key) &&
                    Objects.equals(n.value, value))
                    return true;
            }
            return false;
        }
    }

    // CONSTRUCTORS
    public HashMap(){
        this(defaultInitialSize, defaultLoadFactor);
    }
    public HashMap(float loadFactor){
        this(defaultInitialSize, loadFactor);
    }
    public HashMap(int initialSize){
        this(initialSize, defaultLoadFactor);
    }
    public HashMap(int initialSize, float loadFactor){
        this.buckets = new Node[initialSize];
        this.loadFactor = loadFactor;
        this.size = initialSize;
    }

    private int getIndex(K k) {
        int idx = Objects.hashCode(k) % size;
        return idx;
    }

    public boolean containsKey(K k){
        int idx = getIndex(k);
        Node curr = buckets[idx];
        while( curr != null ){
            if( curr.key.equals(k) ){
                return true;
            }
            curr = curr.nxt;
        }
        return false;
    }

    public void put(K k, V v){
        // increment size, length of bucket won't be size when collissions occur
        int idx = getIndex(k);
        Node curr = buckets[idx];

        if(curr == null){
            // No collission - create new entry
            buckets[idx] = new Node(k,v);
            return;
        } else {
            // collission - traverse linked list
            while(curr != null){
                if( curr.key.equals(k) ){
                    // key is mapped - overwrite
                    curr.value = v;
                    return;
                }
                if( curr.nxt != null ){
                    curr = curr.nxt;
                } else {
                    // Mapping for k does not exist - create new Node & increment size
                    Node n = new Node(k, v);
                    curr.nxt = n;
                    size++;
                    return;
                }
            }
        }

        return;
    }

    public V get(K k){
        int idx = getIndex(k);
        Node curr = buckets[idx];
        while( curr != null ){
            if( curr.key.equals(k) ){
                return (V) curr.value;
            }
            curr = curr.nxt;
        }
        return null;
    }

    public V remove(K k) {
        // decrement size, length of bucket won't be size when collissions occur
        int idx = getIndex(k);
        Node n1 = buckets[idx];

        if( n1 == null ){
            // No elment to remove
            return null;
        }

        if( n1.key.equals(k) ){
            buckets[idx] = n1.nxt;
            size--;
            return (V) n1.value;
        }

        Node n2 = n1.nxt;
        while( n2 != null ){
            if( n2.key.equals(k) ){
                n1.nxt = n2.nxt;
                size--;
                return (V) n2.value;
            } else {
                n1 = n2;
                n2 = n2.nxt;
            }
        }

        // No element to return
        return null;
    }
}