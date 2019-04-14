public class HashMapTest {
    static HashMap map = new HashMap();

    public static void main(String[] args){
        testMapping("Hello", "hi", "World");
        testRemove("Hello", 1);
    }

    private static void testMapping(String k, String badK, String v){
        System.out.println("Testing Mapping:");
        map = new HashMap();
        map.put(k, v);
        System.out.println(String.format("\tContains %s: %b", k, map.containsKey(k)));
        System.out.println(String.format("\t%s's mapping: %s", k, map.get(k)));
        assert map.get(k).equals(v);

        System.out.println(String.format("\tContains %s: %b", badK, map.containsKey(badK)));
        System.out.println(String.format("\t%s's mapping: %s", badK, map.get(badK)));
        assert map.get(badK)  == null;
    }

    private static void testRemove(String k, Integer v){
        System.out.println("Testing Remove:");
        map = new HashMap();

        map.put(k, v);
        System.out.println(String.format("\tContains %s: %b", k, map.containsKey(k)));
        System.out.println(String.format("\t%s's mapping: %s", k, map.get(k)));
        assert map.get(k).equals(v);

        map.remove(k);
        System.out.println(String.format("\tContains %s: %b", k, map.containsKey(k)));
        System.out.println(String.format("\t%s's mapping: %s", k, map.get(k)));
        assert map.get(k)  == null;
    }
}