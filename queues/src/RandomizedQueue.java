import java.util.Iterator;

/**
 * A randomized queue is similar to a stack or queue, except that the item removed is chosen
 * uniformly at random among items in the data structure.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    // construct an empty randomized queue
    public RandomizedQueue() {
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return false;
    }

    // return the number of items on the randomized queue
    public int size() {
        return 0;
    }

    // add the item
    public void enqueue(Item item) {
    }

    // remove and return a random item
    public Item dequeue() {
        return null;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        return null;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return null;
    }

    // unit testing (required)
    public static void main(String[] args) {
    }
}
