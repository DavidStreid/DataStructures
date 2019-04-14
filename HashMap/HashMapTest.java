import java.util.*;

public class HashMapTest {
    static boolean shouldLog = true;
    static HashMap map = new HashMap();

    public static void main(String[] args){
        testMapping("Hello", "hi", "World");
        testNullKey("test");
        testRemove("Hello", 1);
        testResize(16, 0.75F);
        testResize(4, 0.75F);
    }

    private static void testMapping(String k, String badK, String v){
        log("Testing Mapping:\n");
        map = new HashMap();
        map.put(k, v);
        log(String.format("\tContains %s: %b\n", k, map.containsKey(k)));
        log(String.format("\t%s's mapping: %s\n", k, map.get(k)));
        assert map.get(k).equals(v);

        log(String.format("\tContains %s: %b\n", badK, map.containsKey(badK)));
        log(String.format("\t%s's mapping: %s\n", badK, map.get(badK)));
        assert map.get(badK)  == null;
    }

    private static void testNullKey(Object k) {
        log("Testing Null Mapping\n");
        map = new HashMap();

        map.put(null, k);
        Object mappedValue = map.get(null);

        assert mappedValue == k;
        log(String.format("\tNull mapped to %s\n", mappedValue));
    }

    private static void testRemove(String k, Integer v){
        log("Testing Remove:\n");
        map = new HashMap();

        map.put(k, v);
        log(String.format("\tContains %s: %b\n", k, map.containsKey(k)));
        log(String.format("\t%s's mapping: %s\n", k, map.get(k)));
        assert map.get(k).equals(v);

        map.remove(k);
        log(String.format("\tContains %s: %b\n", k, map.containsKey(k)));
        log(String.format("\t%s's mapping: %s\n", k, map.get(k)));
        assert map.get(k)  == null;
    }

    private static void testResize(int capacity, float lf){
        map = new HashMap(capacity, lf);

        log(String.format("Testing Resize - capacity: %d, lf: %f):\n", capacity, lf));

        String[] strings = new String[] {   "a", "b", "c", "d", "e", "f",
                                            "g", "h", "i", "j", "k", "l",
                                            "m", "n", "o", "p", "q", "r",
                                            "s", "t", "u", "v", "w", "x",
                                            "y", "z" };

        int s;
        int c;
        float currLoad;
        String str;

        // This is the letter on which the hashMap should resize itself
        int indexOfResize = (int) (lf*capacity)-1;
        for( int i = 0; i<strings.length; i++ ){
            str = strings[i];
            map.put( str, Boolean.TRUE );
            log("\tAdded " + str);

            if( i == indexOfResize-1 ) {
                assert map.getCapacity() == capacity;
                log(String.format("\t(Capacity: %d) - Resize will occur on next put", map.getCapacity()));
            }

            if( i == indexOfResize ) {
                // Map's capacity should have doubled after put
                capacity *= 2;
                indexOfResize = (int) (lf*capacity)-1;

                log(String.format("\t(Capacity: %d)", map.getCapacity()));
                log(String.format(" - New Capacity (%d) & will resize on index %d", capacity, indexOfResize));

                assert map.getCapacity() == capacity;
            }

            // The size/capcity should always be less than the load factor due to resizing
            assert ( map.getSize()/ (float) map.getCapacity()  ) < lf;

            log("\n");
        }
    }

    private static void log(String logMsg){
        if( shouldLog ){
            System.out.print(logMsg);
        }
    }
}