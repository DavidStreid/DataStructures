import java.util.*;

public class StructureTest{
    private static boolean outputLog = true;

    private static void log(String s){
        if( outputLog ) System.out.println(s);
    }

    private static String toString(Object o){
        if( o == null ){
            return "null";
        }
        return o.toString();
    }

    public static void main(String[] args){
        simpleSetGet();
    }

    private static void simpleSetGet(){
        log("Testing simpleSetGet");
        Structure s = new Structure();

        List<Map.Entry<String,Object>> setters = new ArrayList<>(Arrays.asList(
                new AbstractMap.SimpleEntry<>("a", 1),
                new AbstractMap.SimpleEntry<>("a.b", "2"),
                new AbstractMap.SimpleEntry<>("a.b.c", "three"),
                new AbstractMap.SimpleEntry<>("a.b.c.d", null)
        ));

        for( Map.Entry<String,Object> setter : setters ){
            s.set( setter.getKey(), setter.getValue() );
            log(String.format("\tSetting \"%s\" to %s", setter.getKey(), toString(s.get(setter.getKey()))));
        }

        for( Map.Entry<String,Object> setter : setters ){
            String p = setter.getKey();
            Object o = setter.getValue();
            Object r = s.get(p);

            if( o == null ) {
                assert r == null;
                log( String.format("\tRetrieved %s at \"%s\"", r, p) );
            } else {
                assert s.get( p ).equals( o );
                log( String.format("\tRetrieved %s at \"%s\"", r, p) );
            }
        }
    }
}
