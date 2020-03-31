import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

/**
 * A randomized queue is similar to a stack or queue, except that the item removed is chosen
 * uniformly at random among items in the data structure.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;

    private int size;

    // construct an empty randomized queue
    public RandomizedQueue(int size) {
        this.size = 0;
        items = (Item[]) new Object[size];
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
        if (size == items.length) {
            resizeArray(size * 2);
        }
        items[size++] = item;
    }

    private void resizeArray(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];

        for (int i = 0; i < items.length; ++i) {
            copy[i] = items[i];
        }

        items = copy;
    }

    private void validateEmptyQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    // remove and return a random item
    public Item dequeue() {
        validateEmptyQueue();
        if (items.length <= size / 4) {
            resizeArray(size / 2);
        }
        Item item = items[--size];
        items[size] = null;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        validateEmptyQueue();
        return items[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        int current = 0;

        public ListIterator() {
            StdRandom.shuffle(items);
        }

        @Override
        public boolean hasNext() {
            return items[current] != null;
        }

        @Override
        public Item next() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }

            Item item = items[current++];

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
