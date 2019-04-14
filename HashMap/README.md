# HASH MAP IMPLEMENTATION
.put(K k, V v)
.get(K k)
.containsKey(K k)

## Implementation Notes
* Internal Data Structure: Array of Entry objects
* Indexing: Index of an entry is determined by the `Objects.hashcode(Object o)` of the key modulo'd by the total capacity
* Private "Entry" class: K key, V value, Entry nxt, and `hasKey(K k)`

⋅⋅⋅ * `hasKey(K k)` - helper method used when finding mapped node of input key

⋅⋅⋅ * `Entry nxt` - On collision, colliding node will be assigned to nxt like a linked list

* Finding Nodes: Uses the input Key to find the index and then traverse the node

⋅⋅⋅ * Finding Index: `int idx = Objects.hashCode(k) % capacity`

⋅⋅⋅ * Iterate over nodes: `while(curr != null){...curr = curr.nxt...}`

* Resizing: Use a load factor & capacity. Keep track of size and resize when size/capacity exceeds loadFactor

## Testing Output
```
$ javac HashMapTest.java && java -ea HashMapTest
Note: Some input files use unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.
Testing Mapping:
        Contains Hello: true
        Hello's mapping: World
        Contains hi: false
        hi's mapping: null
Testing Null Mapping
        Null mapped to test
Testing Remove:
        Contains Hello: true
        Hello's mapping: 1
        Contains Hello: false
        Hello's mapping: null
Testing Resize - capacity: 16, lf: 0.750000):
        Added a
        Added b
        Added c
        Added d
        Added e
        Added f
        Added g
        Added h
        Added i
        Added j
        Added k (Capacity: 16) - Resize will occur on next put
        Added l (Capacity: 32) - New Capacity (32) & will resize on index 23
        Added m
        Added n
        Added o
        Added p
        Added q
        Added r
        Added s
        Added t
        Added u
        Added v
        Added w (Capacity: 32) - Resize will occur on next put
        Added x (Capacity: 64) - New Capacity (64) & will resize on index 47
        Added y
        Added z
Testing Resize - capacity: 4, lf: 0.750000):
        Added a
        Added b (Capacity: 4) - Resize will occur on next put
        Added c (Capacity: 8) - New Capacity (8) & will resize on index 5
        Added d
        Added e (Capacity: 8) - Resize will occur on next put
        Added f (Capacity: 16) - New Capacity (16) & will resize on index 11
        Added g
        Added h
        Added i
        Added j
        Added k (Capacity: 16) - Resize will occur on next put
        Added l (Capacity: 32) - New Capacity (32) & will resize on index 23
        Added m
        Added n
        Added o
        Added p
        Added q
        Added r
        Added s
        Added t
        Added u
        Added v
        Added w (Capacity: 32) - Resize will occur on next put
        Added x (Capacity: 64) - New Capacity (64) & will resize on index 47
        Added y
        Added z
```