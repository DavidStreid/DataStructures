import java.util.*;
import java.text.*;

/* TRIE
	.insert(string)
	.search(string)

	Implementation Notes:
		* Have a TrieNode w/ a data value & children map
		* Good Helper methods: 
			.has
			.get
			.add
			.isLeaf

*/

public class Trie {
	private TrieNode root;

	public Trie(){
		this.root = new TrieNode(null);
	}

	private class TrieNode {
		Character data;
		Map<Character,TrieNode> children;
	
		TrieNode( Character data ){
			this.data = data;
			this.children = new HashMap<>();
		}	

		boolean has(Character c){
			return children.containsKey( c );
		}
		
		TrieNode get(Character c) {
			return children.get( c );
		}

		void add( Character c, TrieNode n ){
			children.put( c, n );
		}

		boolean isLeaf( ){
			return children.size() == 0;
		}
	}

	public void insert(String str) {
		char[] chars = str.toCharArray();
		TrieNode curr = root;
		for( char c : chars ){
			TrieNode nxt;
			if( curr.has(c) ){
				nxt = curr.get(c);
			} else {
				nxt = new TrieNode( (Character) c );
				curr.add( c, nxt );
			}
			curr = nxt;
		}
	}

	public void bfs() {
            Queue<TrieNode> q = new LinkedList<>();
            q.add(this.root);
            q.add(null);
            TrieNode nxt;
            while(!q.isEmpty()){
                nxt = q.remove();
                if( nxt == null ){  
                    System.out.println();
                    if( !q.isEmpty() ){
                        q.add(null);
                    }
                } else {
                    System.out.print(nxt.data + "\t");
                    Set<Character> characters = nxt.children.keySet();
                    if( characters != null ){
                        for( Character c : characters ){
                            q.add(nxt.children.get(c));
                        }                    
                    }
                }
            }
	}

	public boolean contains(String str) {
		System.out.println(String.format("Searching for %s", str) );
		TrieNode curr = root;
		CharacterIterator it = new StringCharacterIterator( str );

		while( curr.has( it.current() ) && it.current() != CharacterIterator.DONE ){
			System.out.print(it.current() + "\t" );
			curr = curr.get( it.current() );
			it.next();	
		}
		System.out.println();

		// Check - String fully iterated and at leaf node
		return ( it.current() == CharacterIterator.DONE && curr.isLeaf() ); 
	}
}
