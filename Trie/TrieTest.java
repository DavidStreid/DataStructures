public class TrieTest {
	public static void main(String[] args) {
		String[] w1 = new String[] { "bird", "dog", "cat", "dingo" };
		String[] w2 = new String[] { "ab", "ba", "aa", "bb" };

		Trie t1 = createTrie( w1 );
		t1.bfs();
		System.out.println( t1.contains( "bird" ) );
		System.out.println( t1.contains( "bird2" ) );
		System.out.println();
		
		Trie t2 = createTrie( w2 ); 
		t2.bfs();
		System.out.println( t2.contains( "ab" ) );
		System.out.println( t2.contains( "aa" ) );
		System.out.println( t2.contains( "bb" ) );
		System.out.println( t2.contains( "bc" ) );
		System.out.println();
	}

	private static Trie createTrie( String[] words ){
		Trie trie = new Trie();
		for( String w : words ) {
			trie.insert(w);
		}
		return trie;
	}
}
