# How to compile and execute java class from command line:

```bash
$ mkdir build/classes/java/main/ -p
$ javac -cp libs/algs4.jar src/Percolation.java -d build/classes/java/main/
$ java -cp build/classes/java/main/:libs/algs4.jar Percolation
```
