import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A randomized queue is similar to a stack or queue, except that the item removed is chosen
 * uniformly at random among items in the data structure.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first;

    private Node last;

    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        first = new Node(null);
        last = first;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.size;
    }

    private void validateItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    // add the item
    public void enqueue(Item item) {
        validateItem(item);

        if (last.item == null) {
            last = new Node(item);
            first = last;
        }
        else {
            Node newNode = new Node(item);
            last.next = newNode;
            last = newNode;
        }
        size++;
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
        return new ListIterator();
    }

    private class Node {

        private Item item;
        private Node next;

        public Node(Item item) {
            this.item = item;
        }
    }

    private class ListIterator implements Iterator<Item> {

        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;

            return item;
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
