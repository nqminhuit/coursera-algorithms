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
$ javac queues/src/Deque.java -d queues/build/classes/java/main/; javac -cp /opt/minh/projects/coursera-algorithms/libs/junit-4.12.jar:queues/build/classes/java/main/ queues/test/DequeTest.java -d queues/build/classes/java/test/; java -cp /opt/minh/projects/coursera-algorithms/libs/junit-4.12.jar:/opt/minh/projects/coursera-algorithms/libs/hamcrest-core-1.3.jar:queues/build/classes/java/main/:queues/build/classes/java/test/ org.junit.runner.JUnitCore  DequeTest

$ javac -cp /opt/minh/projects/coursera-algorithms/libs/algs4.jar queues/src/RandomizedQueue.java -d queues/build/classes/java/main/; javac -cp /opt/minh/projects/coursera-algorithms/libs/junit-4.12.jar:queues/build/classes/java/main/ queues/test/RandomizedQueueTest.java -d queues/build/classes/java/test/; java -cp /opt/minh/projects/coursera-algorithms/libs/junit-4.12.jar:/opt/minh/projects/coursera-algorithms/libs/hamcrest-core-1.3.jar:queues/build/classes/java/main/:queues/build/classes/java/test/:/opt/minh/projects/coursera-algorithms/libs/algs4.jar org.junit.runner.JUnitCore  RandomizedQueueTest

$ javac -cp /opt/minh/projects/coursera-algorithms/libs/algs4.jar:queues/build/classes/java/main/ queues/src/*.java -d queues/build/classes/java/main/; java -cp queues/build/classes/java/main/:/opt/minh/projects/coursera-algorithms/libs/algs4.jar Permutation 3 < queues/test/test-set/permutation1
```
