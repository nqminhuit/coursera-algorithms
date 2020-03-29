import java.util.Iterator;
import java.util.NoSuchElementException;

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

    private void validateItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    // add the item
    public void enqueue(Item item) {
        validateItem(item);
    }

    private void validateEmptyQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    // remove and return a random item
    public Item dequeue() {
        validateEmptyQueue();
        return null;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        validateEmptyQueue();
        return null;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return null;
    }

    private class Node {

        private Item item;
        private Node next;

        public Node(Item item) {
            this.item = item;
        }
    }

    private class ListIterator implements Iterator<Item> {

        Node current;

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
    }
}
