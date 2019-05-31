import java.util.*;

public class Structure{
	private Element root;

	/**
	 * Initialize structure with null-value root
	 */
	public Structure(){
		this.root = new Element(null);
	}

	/**
	 * Internal structure that keeps track of values and pointers to other values
	 */
	private class Element {
		private Object val;
		private Map<String, Element> map;

		/**
		 * Initializes an element w/ a value and empty map to elements
		 *
		 * @param val
		 * @param map
		 */
		public Element(Object val){
			this.val = val;
			this.map = new HashMap<String, Element>();
		}

		public Object getVal(){
			return this.val;
		}

		/**
		 * Traverses to the next element pointed to by the key
		 */
		public Element traverse(String k){
			return map.get(k);
		}

		/**
		 * Overrides current value for the element
		 * @param val
		 */
		public void set(Object val){
			this.val = val;
		}

		/**
		 * Maps val using the input key
		 * @param k
		 * @param val
		 */
		public void map(String k, Element val){
			this.map.put(k, val);
		}
	}

	private String[] getPath(String path){
		String[] split = path.split(",");

		return split;
	}

	public void set(String path, Object val){
		String[] pathArray = getPath(path);

		Element curr = this.root;
		Element nxt;
		for( String k : pathArray ){
			nxt = curr.traverse(k);
			// Link does not exist yet - create it
			if( nxt == null ){
				curr.map(k, new Element(null));
				nxt = curr.traverse(k);
			}
			curr = nxt;
		}
		curr.set(val);
	}

	public Object get(String path) {
		String[] pathArray = getPath(path);

		Element curr = this.root;
		for( String k : pathArray ){
			curr = curr.traverse(k);
			if( curr == null ){
				return null;
			}
		}

		return curr.getVal();
	}
}
