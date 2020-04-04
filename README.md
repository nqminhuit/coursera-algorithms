### How to compile and execute java class from command line:

```bash
$ mkdir build/classes/java/main/ -p
$ javac -cp libs/algs4.jar src/Percolation.java -d build/classes/java/main/
$ java -cp build/classes/java/main/:libs/algs4.jar Percolation
```

### How to compile and execute java class that requires input file:

```bash
$ javac -cp percolation/libs/algs4.jar percolation/src/*.java -d percolation/build/classes/java/main/
$ java -cp percolation/build/classes/java/main/:percolation/libs/algs4.jar UnionFind < percolation/test-set/tinyUF
```

* quick: for single command:
```bash
$ javac -cp percolation/libs/algs4.jar percolation/src/*.java -d percolation/build/classes/java/main/; java -cp percolation/build/classes/java/main/:percolation/libs/algs4.jar UnionFind < percolation/test-set/tinyUF
```
* note: for the multiple classpaths, linux uses colon (while windows use semi-colon)

### How to add referenced library manually:
1. open "Java Dependencies" viewer
2. copy "Relative Path" of the project
3. open the file .classpath in that relative path
4. add this line:
```
<classpathentry kind="lib" path="absolute/path/to/file.jar"/>
```

### How to compile and execute JUnit 4.12 from commandline:
```
$ javac queues/src/Deque.java -d queues/build/classes/java/main/; javac -cp libs/junit-4.12.jar:queues/build/classes/java/main/ queues/test/DequeTest.java -d queues/build/classes/java/test/; java -cp libs/junit-4.12.jar:libs/hamcrest-core-1.3.jar:queues/build/classes/java/main/:queues/build/classes/java/test/ org.junit.runner.JUnitCore DequeTest

$ javac -cp libs/algs4.jar queues/src/RandomizedQueue.java -d queues/build/classes/java/main/; javac -cp libs/junit-4.12.jar:queues/build/classes/java/main/ queues/test/RandomizedQueueTest.java -d queues/build/classes/java/test/; java -cp libs/junit-4.12.jar:libs/hamcrest-core-1.3.jar:queues/build/classes/java/main/:queues/build/classes/java/test/:libs/algs4.jar org.junit.runner.JUnitCore RandomizedQueueTest

$ javac -cp libs/algs4.jar:queues/build/classes/java/main/ queues/src/*.java -d queues/build/classes/java/main/; java -cp queues/build/classes/java/main/:libs/algs4.jar Permutation 3 < queues/test/test-set/permutation1

$ javac -cp libs/algs4.jar collinear/src/Point.java -d collinear/build/classes/java/main/; javac -cp libs/junit-4.12.jar:libs/hamcrest-core-1.3.jar:collinear/build/classes/java/main/ collinear/test/PointTest.java -d collinear/build/classes/java/test; java -cp libs/junit-4.12.jar:libs/hamcrest-core-1.3.jar:collinear/build/classes/java/test:collinear/build/classes/java/main/ org.junit.runner.JUnitCore PointTest

$ javac -cp libs/algs4.jar collinear/src/*.java -d collinear/build/classes/java/main/; javac -cp libs/algs4.jar:libs/junit-4.12.jar:libs/hamcrest-core-1.3.jar:collinear/build/classes/java/main/ collinear/test/*.java -d collinear/build/classes/java/test; java -cp libs/algs4.jar:libs/junit-4.12.jar:libs/hamcrest-core-1.3.jar:collinear/build/classes/java/test:collinear/build/classes/java/main/ org.junit.runner.JUnitCore BruteCollinearPointsTest

$ javac -cp libs/algs4.jar collinear/src/*.java -d collinear/build/classes/java/main/; javac -cp libs/algs4.jar:libs/junit-4.12.jar:libs/hamcrest-core-1.3.jar:collinear/build/classes/java/main/ collinear/test/*.java -d collinear/build/classes/java/test; java -cp libs/algs4.jar:libs/junit-4.12.jar:libs/hamcrest-core-1.3.jar:collinear/build/classes/java/test:collinear/build/classes/java/main/ org.junit.runner.JUnitCore FastCollinearPointsTest
```
