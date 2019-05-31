# Structure Data Structure

## Overview
```
Struct = {
	'k1': {
		'k2': {
			'k4': 42,
			'k5': 'Answer'
		}
		'k3': 'Hello World' 
	}
}
```

Operations
	
	get(path) - Returns the val at the path in the structure. If it doesn't exist, return null
	
	set(path, val) - Sets val at path in datastructure. If path doesn't exist, creates it.


## Testing Output
```
$ javac -Xlint StructureTest.java  && java -ea StructureTest
Testing simpleSetGet
        Setting "a" to 1
        Setting "a.b" to 2
        Setting "a.b.c" to three
        Setting "a.b.c.d" to null
        Retrieved 1 at "a"
        Retrieved 2 at "a.b"
        Retrieved three at "a.b.c"
        Retrieved null at "a.b.c.d"
```
