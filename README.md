# How to compile and execute java class from command line:

```bash
$ mkdir build/classes/java/main/ -p
$ javac -cp libs/algs4.jar src/Percolation.java -d build/classes/java/main/
$ java -cp build/classes/java/main/:libs/algs4.jar Percolation
```

# How to compile and execute java class that requires input file:

```bash
$ javac -cp percolation/libs/algs4.jar percolation/src/*.java -d percolation/build/classes/java/main/
$ java -cp percolation/build/classes/java/main/:percolation/libs/algs4.jar UnionFind < percolation/test-set/tinyUF
```

* quick: for single command:
```bash
$ javac -cp percolation/libs/algs4.jar percolation/src/*.java -d percolation/build/classes/java/main/; java -cp percolation/build/classes/java/main/:percolation/libs/algs4.jar UnionFind < percolation/test-set/tinyUF
```
