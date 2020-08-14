# Probabilistic Bag
## Description
Part 1)
Design a data structure w/ two methods
```
.put( obj ), return void - Puts obj into data structure where obj is { val: any, weight: int } where weight is [0, infinity)
.get( ), return val (any) - Retrieves random val (from one of the obj), with probability = obj.weight/(Sum of all object weights in data structure)
```

Part 2)
With that same data structure, write a remove method
```
.remove(), return val (any) - retrieves random val (from one of the obj), with probability = obj.weight/(Sum of all object weights in data structure) and then subtracts that weight from the total weight
```
E.g.
Part 1
```
bag = ProbabilisticBag()
bag.put( { v: 'A', w: 5 } )
bag.put( { v: 'B', w: 2 } )
bag.put( { v: 'C', w: 3 } )
bag.get()  // Should return B 20%, C 30%, A, 50%
```

Part 2
```
bag.remove() // Returns random, let's say "A"
bag.get() // Should return B 40% & C 60%
```

## Run
```
$ javac ProbabilisticBagTest.java && java ProbabilisticBagTest
Testing Observed Frequencies - Count: 100000
        A       Expected: 0.01282051282051282, Actual: 0.01324
        B       Expected: 0.02564102564102564, Actual: 0.02539
        C       Expected: 0.038461538461538464, Actual: 0.03799
        D       Expected: 0.05128205128205128, Actual: 0.051
        E       Expected: 0.0641025641025641, Actual: 0.06484
        F       Expected: 0.07692307692307693, Actual: 0.07766
        G       Expected: 0.08974358974358974, Actual: 0.09107
        H       Expected: 0.10256410256410256, Actual: 0.10165
        I       Expected: 0.11538461538461539, Actual: 0.11534
        J       Expected: 0.1282051282051282, Actual: 0.12772
        K       Expected: 0.14102564102564102, Actual: 0.14165
        L       Expected: 0.15384615384615385, Actual: 0.15245
DELETING - Should see more lower-weighted objects (beginning of alphabet)
        B->J->F->C->D->E->E->B->A->A->A->null
```

## Reference
* http://mishranam.blogspot.com/2015/04/randomized-probabilistic-bag-of-objects.html
