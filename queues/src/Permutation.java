import edu.princeton.cs.algs4.StdIn;

/**
 * Takes an integer k as a command-line argument; reads a sequence of strings from standard input
 * and prints exactly k of them, uniformly at random. Print each item from the sequence at most
 * once. {@code 0 ≤ k ≤ n}
 */
public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        if (k < 0) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = new StringBuilder();
        while (!StdIn.isEmpty()) {
            sb.append(StdIn.readString() + " ");
        }
        String sequence = sb.toString();

        String[] sequences = sequence.split(" ");
        System.out.println(sequences.length);
        if (k > sequences.length) {
            throw new IllegalArgumentException();
        }
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        for (String s : sequences) {
            randomizedQueue.enqueue(s);
        }

        for (int i = 0; i < k; ++i) {
            System.out.print(randomizedQueue.sample() + " ");
        }
        System.out.println();
    }

}
